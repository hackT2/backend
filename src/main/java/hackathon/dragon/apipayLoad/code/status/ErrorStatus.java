package hackathon.dragon.apipayLoad.code.status;

import hackathon.dragon.apipayLoad.code.BaseErrorCode;
import hackathon.dragon.apipayLoad.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),
    // For test
    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP4001", "이거는 테스트"),
    LOGIN_ERROR_ID(HttpStatus.BAD_REQUEST, "LOGIN4001", "존재하지 않는 유저입니다"),
    LOGIN_ERROR_PW(HttpStatus.BAD_REQUEST, "LOGIN4002", "올바르지 않은 비밀번호입니다."),
    TOKEN_UNVALID(HttpStatus.UNAUTHORIZED, "TOKEN4001", "유효하지 않은 토큰입니다"),
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "TOKEN4002", "토큰이 만료되었습니다"),
    TOKEN_NOT_FOUND(HttpStatus.UNAUTHORIZED, "TOKEN4003", "인증이 필요한 요청입니다"),
    TOKEN_UNKNOWN_ERROR(HttpStatus.UNAUTHORIZED, "TOKEN500", "토큰이 존재하지 않습니다."),
    TOKEN_WRONG_TYPE_ERROR(HttpStatus.BAD_REQUEST, "TOKEN4006", "변조된 토큰입니다."),
    TOKEN_UNSUPPORTED_ERROR(HttpStatus.BAD_REQUEST, "TOKEN4007", "변조된 토큰입니다."),
    JSON_PARSING_ERROR(HttpStatus.BAD_REQUEST, "JSON4001", "JSON 파싱이 잘못되었습니다."),


    // Tone
    TONE_NOT_FOUND(HttpStatus.BAD_REQUEST, "TONE4001", "존재하지않는 TONE입니다."),

    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                //            .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                //             .isSuccess(false)
                .httpStatus(httpStatus)
                .build()
                ;
    }

}