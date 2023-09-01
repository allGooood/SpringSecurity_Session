package co.kr.vgen.op2nrtuapi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/home")
    public ResponseEntity<String> hello(){
        return ResponseEntity.status(HttpStatus.OK).body("hello");
    }

    @GetMapping("/hey")
    public ResponseEntity<String> hey(){
        System.out.println("----------------------HEY");
        return ResponseEntity.status(HttpStatus.OK).body("hey");
    }
}
