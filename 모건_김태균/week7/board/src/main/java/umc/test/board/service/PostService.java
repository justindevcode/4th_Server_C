package umc.test.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.test.board.domain.Post;
import umc.test.board.dto.post.PostCreateRequestDto;
import umc.test.board.dto.post.PostListResponseDto;
import umc.test.board.dto.post.PostResponseDto;
import umc.test.board.dto.post.PostUpdateRequestDto;
import umc.test.board.repository.PostRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Long save(PostCreateRequestDto requestDto){
        return postRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostUpdateRequestDto requestDto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        post.update(requestDto.getTitle(),
                requestDto.getContent());

        return id;
    }

    @Transactional
    public PostResponseDto searchById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));

        return new PostResponseDto(post);
    }

    @Transactional
    public List<PostListResponseDto> searchAllDesc(){
        return postRepository.findAll().stream()
                .map(PostListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));
        postRepository.delete(post);
    }

}
