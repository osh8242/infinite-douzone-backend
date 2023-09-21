//package com.douzone.rest.auth.jwt;
//
//import io.jsonwebtoken.Jwts;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    // HTTP 요청 헤더에서 JWT를 가져오기 위한 키
//    private static final String HEADER_STRING = "Authorization";
//
//    // JWT가 "Bearer " 문자열로 시작한다고 가정
//    private static final String TOKEN_PREFIX = "Bearer ";
//
//    // JwtService 주입 (시크릿 키와 관련된 로직을 이곳에서 관리하기 위해)
//    private final JwtService jwtService;
//
//    public JwtAuthenticationFilter(JwtService jwtService) {
//        this.jwtService = jwtService;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        String header = request.getHeader(HEADER_STRING);
//
//        // 헤더가 없거나 Bearer로 시작하지 않는 경우, 필터 체인을 계속 진행
//        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
//            chain.doFilter(request, response);
//            return;
//        }
//
//        // 토큰에서 인증 정보를 추출
//        Authentication authentication = getAuthentication(request);
//
//        // 인증 정보를 SecurityContext에 설정
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        chain.doFilter(request, response);
//    }
//
//    /**
//     * 요청 헤더에서 JWT 토큰을 추출하고 이를 파싱하여 인증 정보를 반환
//     *
//     * @param request HTTP 요청 객체
//     * @return 인증 정보 객체 또는 null
//     */
//    private Authentication getAuthentication(HttpServletRequest request) {
//        String token = request.getHeader(HEADER_STRING);
//        if (token != null) {
//            // 토큰 파싱
//            String user = Jwts.parser()
//                    .setSigningKey(jwtService.getSecretKey()) // JwtService의 시크릿 키로 파싱
//                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
//                    .getBody()
//                    .getSubject();
//
//            // 파싱된 사용자 정보가 있을 경우 인증 객체 반환
//            if (user != null) {
//                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
//            }
//            return null;
//        }
//        return null;
//    }
//}
