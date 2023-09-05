package co.kr.vgen.op2nrtuapi.controller.test;

import co.kr.vgen.op2nrtuapi.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody ){
//
////        boolean isLogin = loginService.login(username, password);
////        String sessionId = null;
////        if(isLogin){
////            HttpSession session = request.getSession();
////            session.setAttribute("username", username);
////            session.setMaxInactiveInterval(10 * 60);
////
////            sessionId = session.getId();
////            System.out.println(">>>>>>>>>> SessionId: " + sessionId);
////        }
//
//        return ResponseEntity.status(HttpStatus.OK).body("");
//    }

//    @GetMapping("/session")
//    public ResponseEntity<String> getSession(HttpServletRequest request, @RequestParam(name = "sessionId") String sessionId){
//        HttpSession session = request.getSession(false).getAttribute("name");
//        return ResponseEntity.status(HttpStatus.OK).body("sessionId: " + "");
//    }
}

















