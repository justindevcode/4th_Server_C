package carrot.backend.exception;

import carrot.backend.exception.situation.*;
import carrot.backend.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.management.relation.RoleNotFoundException;
import java.util.Objects;

import static carrot.backend.response.Response.*;
import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {

    // 500 에러
    @ExceptionHandler(IllegalArgumentException.class) // 지정한 예외가 발생하면 해당 메소드 실행
    @ResponseStatus(INTERNAL_SERVER_ERROR) // 각 예외마다 상태 코드 지정
    public Response illegalArgumentExceptionAdvice(IllegalArgumentException e) {
        log.info("e = {}", e.getMessage());
        return failure(INTERNAL_SERVER_ERROR, e.getMessage());
    }

    // 500 에러
    // 컨버트 실패
    @ExceptionHandler(CannotConvertHelperException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public Response cannotConvertNestedStructureException(CannotConvertHelperException e) {
        log.error("e = {}", e.getMessage());
        return failure(INTERNAL_SERVER_ERROR, e.getMessage());
    }

    // 400 에러
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public Response methodArgumentNotValidException(MethodArgumentNotValidException e) { // 2
        return failure(BAD_REQUEST, Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
    }

    // 400 에러
    @ExceptionHandler(BindException.class)
    @ResponseStatus(BAD_REQUEST)
    public Response bindException(BindException e) {
        return failure(BAD_REQUEST, Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
    }

    // 409 응답
    // username 중복
    @ExceptionHandler(UsernameAlreadyExistsException.class)
    @ResponseStatus(CONFLICT)
    public Response memberEmailAlreadyExistsException(UsernameAlreadyExistsException e) {
        return failure(CONFLICT, e.getMessage() + "은 중복된 아이디 입니다.");
    }

    // 401 응답
    // 아이디 혹은 비밀번호 오류시 발생
    @ExceptionHandler(LoginFailureException.class)
    @ResponseStatus(UNAUTHORIZED)
    public Response loginFailureException() {
        return failure(UNAUTHORIZED, "로그인에 실패하였습니다.");
    }

    // 401 응답
    // 요청자와 요청한 유저의 정보가 일치하지 않을시에 발생
    @ExceptionHandler(MemberNotEqualsException.class)
    @ResponseStatus(UNAUTHORIZED)
    public Response memberNotEqualsException() {
        return failure(UNAUTHORIZED, "유저 정보가 일치하지 않습니다.");
    }

    // 404 응답
    // 요청한 유저를 찾을 수 없음
    @ExceptionHandler(MemberNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public Response memberNotFoundException() {
        return failure(NOT_FOUND, "요청한 회원을 찾을 수 없습니다.");
    }

    // 404 응답
    // 요청한 자원을 찾을 수 없음
    @ExceptionHandler(RoleNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public Response roleNotFoundException() {
        return failure(NOT_FOUND, "요청한 권한 등급을 찾을 수 없습니다.");
    }

    // 404 응답
    // 요청한 게시물을 찾을 수 없음
    @ExceptionHandler(PostNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public Response postNotFoundException() {
        return failure(NOT_FOUND, "요청한 게시물을 찾을 수 없습니다.");
    }


}
