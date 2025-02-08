package hackathon.dragon.service;

import hackathon.dragon.apipayLoad.exception.handler.TempHandler;
import hackathon.dragon.domain.User;
import hackathon.dragon.dto.KakaoTokenResponseDto;
import hackathon.dragon.dto.KakaoUserInfoResponseDto;
import hackathon.dragon.repository.UserRepository;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaderValues;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class KakaoService {

    private String clientId;
    private final String KAUTH_TOKEN_URL_HOST;
    private final String KAUTH_USER_URL_HOST;
    private final UserRepository userRepository;

    @Autowired
    public KakaoService(@Value("${kakao.client_id}") String clientId, UserRepository userRepository) {
        this.clientId = clientId;
        this.userRepository = userRepository;
        KAUTH_TOKEN_URL_HOST ="https://kauth.kakao.com";
        KAUTH_USER_URL_HOST = "https://kapi.kakao.com";
    }

    @Transactional
    public String getAccessTokenFromKakao(String code) {

        KakaoTokenResponseDto kakaoTokenResponseDto = WebClient.create(KAUTH_TOKEN_URL_HOST).post()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/oauth/token")
                        .queryParam("grant_type", "authorization_code")
                        .queryParam("client_id", clientId)
                        .queryParam("code", code)
                        .build(true))
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .retrieve()
                .bodyToMono(KakaoTokenResponseDto.class)
                .block();

/*
        log.info(" [Kakao Service] Access Token ------> {}", kakaoTokenResponseDto.getAccessToken());
        log.info(" [Kakao Service] Refresh Token ------> {}", kakaoTokenResponseDto.getRefreshToken());
        //제공 조건: OpenID Connect가 활성화 된 앱의 토큰 발급 요청인 경우 또는 scope에 openid를 포함한 추가 항목 동의 받기 요청을 거친 토큰 발급 요청인 경우
        log.info(" [Kakao Service] Id Token ------> {}", kakaoTokenResponseDto.getIdToken());
        log.info(" [Kakao Service] Scope ------> {}", kakaoTokenResponseDto.getScope());*/

        return kakaoTokenResponseDto.getAccessToken();
    }
    @Transactional
    public KakaoUserInfoResponseDto getUserInfo(String accessToken) {

        KakaoUserInfoResponseDto userInfo = WebClient.create(KAUTH_USER_URL_HOST)
                .get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/v2/user/me")
                        .build(true))
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken) // access token 인가
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .retrieve()
                //TODO : Custom Exception
                .bodyToMono(KakaoUserInfoResponseDto.class)
                .block();

        //log.info("[ Kakao Service ] Auth ID ---> {} ", userInfo.getId());
        //log.info("[ Kakao Service ] NickName ---> {} ", userInfo.getKakaoAccount().getProfile().getNickName());

        return userInfo;
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ) // 트랜잭션 격리 수준 설정
    public User saveOrUpdateUser(KakaoUserInfoResponseDto userInfo) {

        String email = userInfo.getKakaoAccount().getEmail();
        //String phoneNumber=userInfo.getKakaoAccount().getPhoneNumber();
        String nickname = userInfo.getKakaoAccount().getProfile().getNickName();


        // 동시성 문제 방지를 위해 같은 트랜잭션 내에서 조회 후 처리
        return userRepository.findByKakaoEmail(email)
                .orElseGet(() -> {
                    User user = User.builder()
                            .name(nickname)
                            .kakaoEmail(email)
                            .nickName(nickname)
                            .build();
                    return userRepository.save(user);
                });
    }

}