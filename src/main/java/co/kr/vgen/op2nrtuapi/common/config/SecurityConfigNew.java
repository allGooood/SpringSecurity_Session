package co.kr.vgen.op2nrtuapi.common.config;

import co.kr.vgen.op2nrtuapi.common.config.handler.LoginFailureHandler;
import co.kr.vgen.op2nrtuapi.common.config.handler.LoginSuccessHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Slf4j
@Configuration
//@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfigNew {
//    private final RtuAuthenticationEntryPoint entryPoint;
//    private final RtuAccessDeniedHandler accessDeniedHandler;

    private final ObjectMapper objectMapper;
    private final CustomUserDetailsService loginService;
    private final LoginSuccessHandler loginSuccessHandler;
    private final LoginFailureHandler loginFailureHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        //오직 POST 를 통한 json 로그인만 허용
        http.httpBasic().disable()
                .formLogin().disable();

        //cors 처리를 위해..
        http.cors().configurationSource(corsConfigurationSource());

        //cross site request forgery disable
        http.csrf().disable();


        //인증(정체 확인) 예외처리, 인가(자원 접근) 예외처리 등록
//        http.exceptionHandling()
//                .authenticationEntryPoint(entryPoint)
//                .accessDeniedHandler(accessDeniedHandler);

        http.authorizeRequests()
//                .antMatchers("/login").permitAll()
                .antMatchers(HttpMethod.GET, "/hey").authenticated()
                .anyRequest().permitAll();

        //JWT 필터를 적용
        http.addFilterBefore(jsonUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        //세션이 필요한 경우 생성
//        http.sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);


        return http.build();
    }

    @Bean
    public JsonUsernamePasswordAuthenticationFilter jsonUsernamePasswordAuthenticationFilter() {
        JsonUsernamePasswordAuthenticationFilter jsonUsernamePasswordAuthenticationFilter = new JsonUsernamePasswordAuthenticationFilter(objectMapper, loginSuccessHandler, loginFailureHandler);
        jsonUsernamePasswordAuthenticationFilter.setAuthenticationManager(authenticationManager());
        return jsonUsernamePasswordAuthenticationFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(loginService);

        return new ProviderManager(provider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration conf = new CorsConfiguration();
        conf.addAllowedOrigin("*");
        conf.addAllowedHeader("*");
        conf.addAllowedMethod("*");
//        conf.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", conf);
        return source;
    }

}
