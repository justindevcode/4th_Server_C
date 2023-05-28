package umc.test.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.test.board.dto.post.PostCreateRequestDto;
import umc.test.board.dto.post.PostListResponseDto;
import umc.test.board.dto.post.PostResponseDto;
import umc.test.board.dto.post.PostUpdateRequestDto;
import umc.test.board.service.PostService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostService postService;

    // 글 등록
    @PostMapping("/post")
    public Long create(@RequestBody PostCreateRequestDto requestDto){
        return postService.save(requestDto);
    }

    // 글 제목, 내용 변경
    @PutMapping("/post/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostUpdateRequestDto requestDto){
        return postService.update(id, requestDto);
    }

    // 글 조회
    @GetMapping("/post/{id}")
    public PostResponseDto searchById(@PathVariable Long id) {
        return postService.searchById(id);
    }

    // 글 전체 조회
    @GetMapping("/post")
    public List<PostListResponseDto> searchAlldesc(){
        return postService.searchAllDesc();
    }

    // 글 삭제
    @DeleteMapping("/post/{id}")
    public void delete(@PathVariable Long id){
        postService.delete(id);
    }
}
 