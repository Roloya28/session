package com.example.demo.auth.controller;

import com.example.demo.auth.dto.AuthLoginResponseDto;
import com.example.demo.auth.dto.AuthSignupRequestDto;
import com.example.demo.auth.service.AuthService;
import com.example.demo.auth.dto.AuthLoginRequestDto;
import com.example.demo.common.consts.Const;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public void signup(@RequestBody AuthSignupRequestDto dto) {
        authService.signup(dto);
    }

    @PostMapping("/login")
    public void login(@RequestBody AuthLoginRequestDto dto, HttpServletRequest request) {
        AuthLoginResponseDto result = authService.login(dto);

        // 세션을 생성해야됨. 서비스의 로직보다는 컨트롤러가 해야하는일 (클라이언트와 소통하는 역할에 가까움)
        HttpSession session = request.getSession(); // 신규 세션 생성, JSESSIONID 쿠키 발급
        session.setAttribute(Const.LOGIN_MEMBER, result.getMemberId()); // 서버 메모리에 세션 저장
    }

    // 프론트입장에서도 그냥 JSESSIONID 지워버리면 그게 로그아웃임
    // 프론트에서도 HEADER 값을 설정할수 있음
    // 괜히 API 호출이나 늘어나지 그렇게 의미가 있는지 잘 모르겠음. 듣고보니 그런것 같음. 필요한 경우는 주로 앱
    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }
}
