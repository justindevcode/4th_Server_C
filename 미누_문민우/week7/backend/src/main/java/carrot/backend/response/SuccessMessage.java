package carrot.backend.response;

import lombok.Getter;

@Getter
public class SuccessMessage {
    public static final String SUCCESS = "요청에 성공했습니다";
    public static final String SUCCESS_TO_SIGN_UP = "회원가입에 성공했습니다.";
    public static final String SUCCESS_TO_SIGN_IN = "로그인에 성공했습니다.";
    public static final String SUCCESS_TO_REISSUE = "토큰 재발급에 성공했습니다.";
    public static final String SUCCESS_TO_GET_MEMBER = "회원을 조회하는데 성공했습니다.";
    public static final String SUCCESS_TO_EDIT_MEMBER = "회원 정보를 변경하는데 성공했습니다.";
    public static final String SUCCESS_TO_DELETE_MEMBER = "회원을 삭제하는데 성공했습니다.";
    public static final String SUCCESS_TO_CREATE_POST = "게시물을 생성하는데 성공했습니다.";
    public static final String SUCCESS_TO_GET_POST_LIST = "게시물 목록을 조회하는데 성공했습니다.";
    public static final String SUCCESS_TO_GET_POST = "게시물을 조회하는데 성공했습니다.";
    public static final String SUCCESS_TO_EDIT_POST = "게시물 정보를 변경하는데 성공했습니다.";
    public static final String SUCCESS_TO_DELETE_POST = "게시물을 삭제하는데 성공했습니다.";
    public static final String SUCCESS_TO_GET_OPEN_API_INFO = "공공 데이터를 조회하는데 성공했습니다.";
}