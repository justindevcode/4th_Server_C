package umc.assac.API_Server.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import umc.assac.API_Server.exception.board.BoardNotFoundException;
import umc.assac.API_Server.exception.board.BoardReservedException;
import umc.assac.API_Server.exception.token.TokenExpiredException;
import umc.assac.API_Server.exception.user.*;
import umc.assac.API_Server.exception.category.CategoryNotFoundException;
import umc.assac.API_Server.exception.comment.CommentNotFoundException;
import umc.assac.API_Server.response.Response;

// API 도중 발생하는 오류들을 관리하기 위한 오류 처리 파일
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response methodArgumentNotValidException(MethodArgumentNotValidException e) {
        return Response.failure(404, e.getBindingResult().getFieldError().getDefaultMessage());
    }

    // 특정 조건에 부합하는 게시글을 찾지 못하였을 경우 오류를 반환
    @ExceptionHandler(BoardNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response boardNotFoundException() {
        return Response.failure(404, "입력하신 정보와 일치하는 게시글이 존재하지 않습니다.");
    }

    // 작성자의 정보와 로직 이용자의 정보가 일치하지 않은 경우 오류 반환
    @ExceptionHandler(UserWriterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response boardWriterException() {
        return Response.failure(400, "현재 사용자와 작성자의 정보가 일치하지 않습니다.");
    }

    // 이미 예약된 게시글을 예약하려고 하는 경우 오류 반환
    @ExceptionHandler(BoardReservedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response boardReservedException() {
        return Response.failure(400, "이미 예약되어있는 게시글입니다.");
    }

    // 특정 카테고리를 찾지 못하였을 경우 오류를 반환
    @ExceptionHandler(CategoryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response categoryNotFoundException() {
        return Response.failure(404, "해당 조건에 부합하는 카테고리를 찾을 수 없습니다.");
    }

    // 특정 사용자를 찾지 못하였을 경우 오류를 반환
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response userNotFoundException() {
        return Response.failure(404, "해당 정보에 해당하는 사용자를 찾지 못하였습니다.");
    }

    // 이미 가입된 정보로 가입하려는 경우 오류 반환
    @ExceptionHandler(UserDuplicateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Response userDuplicateException() {
        return Response.failure(409, "입력하신 정보로 가입된 회원이 존재합니다.");
    }

    // 입력한 정보와 회원의 정보가 일치하지 않은 경우 오류 반환
    @ExceptionHandler(UserInfoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response userInfoException() {
        return Response.failure(400, "입력하신 정보와 회원의 정보가 일치하지 않습니다.");
    }

    // 이미 신고된 사용자를 다시 신고하는 경우 오류 반환
    @ExceptionHandler(UserReportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response userReportedException() {
        return Response.failure(400, "이미 신고된 회원입니다.");
    }

    // 특정 댓글을 찾지 못하였을 경우 오류 반환
    @ExceptionHandler(CommentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response commentNotFoundException() {
        return Response.failure(404, "해당 정보에 해당하는 댓글을 찾지 못하였습니다.");
    }

    @ExceptionHandler(TokenExpiredException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response tokenExpiredException() {
        return Response.failure(400, "이미 만료된 토큰입니다.");
    }
}
