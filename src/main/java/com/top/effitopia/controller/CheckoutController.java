package com.top.effitopia.controller;

import com.top.effitopia.dto.CheckoutAnswerDTO;
import com.top.effitopia.dto.CheckoutDTO;
import com.top.effitopia.dto.PageRequestDTO;
import com.top.effitopia.dto.PageResponseDTO;
import com.top.effitopia.service.CheckoutService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/checkouts")
@Log4j2
@RequiredArgsConstructor
public class CheckoutController {

    private final CheckoutService checkoutService;

    @GetMapping
    public void showRegisterForm() {
        log.info("CheckoutController showRegisterForm GetMapping");
    }

    @PostMapping("/register")
    public String registerForm(@ModelAttribute("checkoutDTO") @Valid CheckoutDTO checkoutDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("CheckoutController registerForm PostMapping");
        if (bindingResult.hasErrors()) {
            log.info("CheckoutController registerForm PostMapping has Errors");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/checkouts/register";
        }
        checkoutService.register(checkoutDTO);
        redirectAttributes.addFlashAttribute("registerSuccess", "새로운 안전 점검 데이터를 등록하였습니다.");
        return "redirect:/checkouts/list";
    }

    @GetMapping("/list")
    public void showList(@ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO, BindingResult bindingResult, Model model) {
        log.info("CheckoutController showList GetMapping");
        if (bindingResult.hasErrors()) {
            log.info("CheckoutController showList GetMapping has Errors");
            pageRequestDTO = PageRequestDTO.builder().build();
        }
        model.addAttribute("responseDTO", checkoutService.getList(pageRequestDTO));
    }

    @GetMapping("/checkout/read")
    public void read(@ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO, Integer checkoutId, Model model) {
        log.info("CheckoutController read GetMapping");
        model.addAttribute("checkout", checkoutService.getCheckoutDetail(checkoutId));
    }
}