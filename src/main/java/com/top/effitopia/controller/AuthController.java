package com.top.effitopia.controller;

import com.top.effitopia.dto.JoinDTO;
import com.top.effitopia.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/join")
    public String join(JoinDTO joinDTO) {
        authService.join(joinDTO);
        return "join ok";
    }
}
