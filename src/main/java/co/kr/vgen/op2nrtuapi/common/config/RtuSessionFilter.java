package co.kr.vgen.op2nrtuapi.common.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class RtuSessionFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request
            , ServletResponse response
            , FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        if(session == null){
            System.out.println("session 없음");
            chain.doFilter(request, response);
        }
        System.out.println("session 있음");
        chain.doFilter(request, response);
    }
}
