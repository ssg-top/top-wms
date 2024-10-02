package com.top.effitopia.controller;

import com.top.effitopia.dto.EmailParam;
import com.top.effitopia.dto.MailAuthDTO;
import com.top.effitopia.dto.MemberDTO;
import com.top.effitopia.exception.BizException;
import com.top.effitopia.exception.ErrorCode;
import com.top.effitopia.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Log4j2
@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/login")
    public void loginPage() {
    }

    @GetMapping("/id-inquiry")
    public void idInquiryForm(Model model) {
        model.addAttribute("emailParam", new EmailParam());
    }

    @GetMapping("/password-inquiry")
    public void passwordInquiryForm() {
    }

    @PostMapping("/id-inquiry")
    public String idInquiry(@Valid EmailParam emailParam, BindingResult bindingResult, Model model) {
        log.info("email: {}", emailParam.getEmail());
        if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> log.error(error.getDefaultMessage()));
            return "/auth/id-inquiry";
        }
        try {
            authService.processIdInquiry(emailParam.getEmail());
            model.addAttribute("success", true);
            return "/auth/id-inquiry";
        } catch(BizException e) {
            model.addAttribute("emailParam", emailParam);
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "/auth/id-inquiry";
    }

    @ResponseBody
    @PostMapping("/id-inquiry/verify")
    public ResponseEntity<String> verifyIdInquiry(@Valid MailAuthDTO mailAuthDTO, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        log.info("email: {}, code: {}", mailAuthDTO.getEmail(), mailAuthDTO.getCode());
        if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> log.error(error.getDefaultMessage()));
            return ResponseEntity.badRequest().build();
        }
        try {
            if(authService.verifyMailCode(mailAuthDTO.getEmail(), mailAuthDTO.getCode())) {
                MemberDTO member = authService.getOneByEmail(mailAuthDTO.getEmail());
                return ResponseEntity.ok().body(member.getUsername());
            } else {
                throw new BizException(ErrorCode.NOT_MATCHES_AUTH_CODE);
            }
        } catch(BizException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/password-inquiry")
    public void passwordInquiry() {
    }
}
