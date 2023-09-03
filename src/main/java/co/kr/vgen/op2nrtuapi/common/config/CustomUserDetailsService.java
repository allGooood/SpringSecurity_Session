package co.kr.vgen.op2nrtuapi.common.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
//@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
//    private final PasswordEncoder passwordEncoder;
    public Map<String, String> users = new HashMap<>();

    @PostConstruct
    public void init(){
        users.put("minkyeonng", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("minkyeong"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        users.get(name) != null && users.get(name).equals(password)
        if(users.get(username) != null){
            return User.builder()
                    .username(username)
                    .password(users.get(username))
                    .roles("ADMIN")
                    .build();
        }
        throw new UsernameNotFoundException("등록 되지 않은 User 입니다.");
    }
}
