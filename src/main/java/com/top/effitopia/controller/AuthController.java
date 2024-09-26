package com.top.effitopia.controller;

import com.top.effitopia.dto.JoinDTO;
import com.top.effitopia.security.CustomUserDetails;
import com.top.effitopia.service.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceImpl authServiceImpl;

    @PostMapping("/join")
    public String join(JoinDTO joinDTO) {
        authServiceImpl.save(joinDTO);
        return "join ok";
    }

    @GetMapping("/login")
    public void loginPage() {
        log.info("login page ok");
    }

    @GetMapping("/user")
    public void admin(@AuthenticationPrincipal CustomUserDetails userDetails) {
        if(userDetails == null) {
            log.info("user details is null");
        } else {
            log.info("user: {}", userDetails.getUsername());
            userDetails.getAuthorities().forEach(authority -> log.info("role: {}", authority.getAuthority()));
        }
    }
}
