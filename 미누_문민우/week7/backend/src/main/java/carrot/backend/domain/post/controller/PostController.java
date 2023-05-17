package carrot.backend.domain.post.controller;

import carrot.backend.domain.member.service.MemberService;
import carrot.backend.domain.post.dto.CreatePostRequestDto;
import carrot.backend.domain.post.dto.EditPostInfoRequestDto;
import carrot.backend.domain.post.service.PostService;
import carrot.backend.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static carrot.backend.response.Response.success;
import static carrot.backend.response.SuccessMessage.*;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
@Slf4j
public class PostController {

    private final PostService postService;
    private final MemberService memberService;

    @ResponseStatus(OK)
    @PostMapping()
    public Response createPost(@Valid @RequestBody CreatePostRequestDto createPostRequestDto, Long memberId) {
        postService.createPost(createPostRequestDto, memberService.findMember(memberId));
        return success(SUCCESS_TO_CREATE_POST);
    }

    @ResponseStatus(OK)
    @GetMapping("/simple")
    public Response getSimplePostInfoList(Pageable pageable) {
        return success(SUCCESS_TO_GET_POST_LIST, postService.getSimplePostInfoList(pageable));
    }

    @ResponseStatus(OK)
    @GetMapping("/list")
    public Response getMemberSimplePostInfoList(Pageable pageable, Long memberId) {
        return success(SUCCESS_TO_GET_POST_LIST, postService.getMemberSimplePostInfoList(pageable, memberService.findMember(memberId)));
    }

    @ResponseStatus(OK)
    @GetMapping()
    public Response getPostInfo(Long postId) {
        return success(SUCCESS_TO_GET_POST, postService.getPostInfo(postId));
    }

    @ResponseStatus(OK)
    @PatchMapping()
    public Response editPostInfo(@Valid @RequestBody EditPostInfoRequestDto editPostInfoRequestDto, Long postId) {
        postService.editPostInfo(editPostInfoRequestDto, postId);
        return success(SUCCESS_TO_EDIT_POST);
    }

    @ResponseStatus(OK)
    @DeleteMapping()
    public Response deletePost(Long postId) {
        postService.deletePost(postId);
        return success(SUCCESS_TO_DELETE_POST);
    }
}
