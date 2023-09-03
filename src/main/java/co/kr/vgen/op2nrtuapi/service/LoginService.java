package co.kr.vgen.op2nrtuapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {
    public Map<String, String> users = new HashMap<>();

    @PostConstruct
    public void init(){
        users.put("minkyeonng", "minkyeong");
    }

    public boolean login(String name, String password){

        return users.get(name) != null && users.get(name).equals(password);
    }
}




















