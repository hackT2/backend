package hackathon.dragon.controller;

import com.google.protobuf.Api;
import hackathon.dragon.apipayLoad.ApiResponse;
import hackathon.dragon.domain.User;
import hackathon.dragon.dto.KakaoTokenResponseDto;
import hackathon.dragon.dto.KakaoUserInfoResponseDto;
import hackathon.dragon.service.KakaoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class KakaoLoginController {

    private final KakaoService kakaoService;

    @GetMapping("/callback")
    @Operation(summary = "유저생성", description = "로그인 성공")
    public ApiResponse<String> callback(@RequestParam("code") String code) {
        String accessToken = kakaoService.getAccessTokenFromKakao(code);

        /// 2️⃣ Access Token을 이용하여 사용자 정보 가져오기
        KakaoUserInfoResponseDto userInfo = kakaoService.getUserInfo(accessToken);

        // 3️⃣ 사용자 정보 저장 (신규 유저는 저장, 기존 유저는 그대로 유지)
        User savedUser = kakaoService.saveOrUpdateUser(userInfo);

        // 4️⃣ 응답 반환
        return ApiResponse.onSuccess("로그인 성공!");
    }
}
