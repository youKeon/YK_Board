package com.yukeon.board.domain.board.service;

import com.yukeon.board.domain.board.dto.request.PostCreateRequestDto;
import com.yukeon.board.domain.board.dto.response.PostInfoDto;
import com.yukeon.board.domain.board.dto.response.PostListDto;
import com.yukeon.board.domain.board.entitiy.Post;
import com.yukeon.board.domain.board.entitiy.RelatedPost;
import com.yukeon.board.domain.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public List<PostListDto> getPostList() {
        return postRepository.findAll()
                .stream()
                .map(PostListDto::from)
                .collect(Collectors.toList());
    }

    public PostInfoDto getPost(Long id) {
        Post post = postRepository.findById(id).orElse(null);
        return PostInfoDto.from(post);

    }

    public void savePost(PostCreateRequestDto dto) {
        Post post = Post.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();
        postRepository.save(post);
        analyze(post);
    }

    // ================================= 유사도 분석 메서드 ================================= //
    public void analyze(Post post) {
        List<String> words = getWords(post.getContent());

        // 전체 포스트에서 40% 이하로 등장한 단어 조회
        Map<String, Integer> frequencyMap = getWordFrequency(words);
        List<String> lowFrequencyWords = getLowFrequencyWords(frequencyMap, postRepository.count());

        // Find related posts
        List<Post> relatedPosts = new ArrayList<>();
        for (String word : words) {
            if (lowFrequencyWords.contains(word)) {
                relatedPosts.addAll(postRepository.findByContentContaining(word));
            }
        }

        // 중복 제거 및 정렬
        relatedPosts = removeDuplicates(relatedPosts);
        relatedPosts = sortByRelevance(relatedPosts, words);


        List<RelatedPost> toRelatedPost = relatedPosts
                .stream()
                .map(po -> new RelatedPost(po.getId(), po.getTitle(), po.getContent()))
                .collect(Collectors.toList());

        // 연관 게시글을 연결
        post.addRelatedPost(toRelatedPost);
        postRepository.save(post);
    }

    private List<String> getWords(String content) {
        // 단어를 분리, 공백과 구두점 제거
        String[] wordsArray = content.replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase().split("\\s+");
        List<String> words = Arrays.asList(wordsArray);
        return words;
    }

    private Map<String, Integer> getWordFrequency(List<String> words) {
        Map<String, Integer> frequencyMap = new HashMap<>();

        // 각 단어의 등장 횟수 카운트
        for (String word : words) {
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }

        return frequencyMap;
    }

    private List<String> getLowFrequencyWords(Map<String, Integer> frequencyMap, long totalPostCount) {
        List<String> lowFrequencyWords = new ArrayList<>();

        // 단어 등장 횟수의 임계값을 계산
        double threshold = 0.4 * totalPostCount;

        // 임계값 이하로 등장한 단어들을 찾아 리스트에 추가
        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() <= threshold) {
                lowFrequencyWords.add(entry.getKey());
            }
        }

        return lowFrequencyWords;
    }

    // 중복 제거
    private List<Post> removeDuplicates(List<Post> relatedPosts) {
        Set<Post> postSet = new HashSet<>(relatedPosts);
        return new ArrayList<>(postSet);
    }

    // 정렬
    private List<Post> sortByRelevance(List<Post> relatedPosts, List<String> words) {
        Map<Post, Double> relevanceMap = new HashMap<>();
        for (Post post : relatedPosts) {
            List<String> postWords = getWords(post.getContent());
            double relevanceScore = getRelevanceScore(words, postWords);
            relevanceMap.put(post, relevanceScore);
        }
        relatedPosts.sort(Comparator.comparingDouble(relevanceMap::get).reversed());
        return relatedPosts;
    }


    private double getRelevanceScore(List<String> words1, List<String> words2) {
        Set<String> set1 = new HashSet<>(words1);
        Set<String> set2 = new HashSet<>(words2);

        // 교집합 계산
        Set<String> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);

        // 합집합 계산
        Set<String> union = new HashSet<>(set1);
        union.addAll(set2);

        // Jaccard 유사도 지수를 계산
        double jaccardSimilarityIndex = (double) intersection.size() / (double) union.size();

        return jaccardSimilarityIndex;
    }
}