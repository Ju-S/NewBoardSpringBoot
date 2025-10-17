package com.kedu.newboard.interceptors;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.kedu.newboard.utils.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JWTInterceptor implements HandlerInterceptor {

    @Autowired
    private JWTUtil jwt;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 인터셉터를 거칠 필요가 전혀 없는 로그인, 에러페이지 등은 excludePatterns 에 등록
        // 2. 인터셉터에 의해 인증 체크를 전부 받아야하는 url (ex:board)는 별도의 작업 처리 필요 없음.
        // 3. 같은 url인데, method에 따라 인증을 검사해야하기도 하고, 검사할 필요도 없는 경우
        // ex) /member의 post는 회원가입, get은 마이페이지이므로
        // 인터셉터 내에서 if문으로 예외사항 처리를 해줘야 함.
        // 3번 항목의 불편함은 향후 Spring security로 인증/인가 대체 시점에 해소될 예정

        String path = request.getRequestURI();
        System.out.println(path);

        String method = request.getMethod();

        if (method.equals("OPTIONS")) { // preflight 보안 패킷은 그냥 허용
            return true;
        }

        if (path.equals("/api/member")) { // 요청이 이 url과 같다면
            if (method.equals("POST")) { // method가 post형식이라면 (member의 post는 회원가입이므로 허용)
                return true;
            }
        }

        // 나중엔 url 별로 어떤 url은 인터셉트하고 어떤 url은 인터셉트 안 할건지 지정
        String authHeader = request.getHeader("Authorization");

        // header가 없다면
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Token Error");
        }

        String token = authHeader.substring(7);

        try {
            DecodedJWT djwt = jwt.verifyToken(token);
            // 인터셉터에서 미리 ID를 꺼내서 넘겨주는게 편함. - 가장 많이 사용하는 정보
            request.setAttribute("loginId", djwt.getSubject());
            return true;
        } catch (Exception e) { // 인증 실패시
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token Error");
            return false;
        }

    }
}
