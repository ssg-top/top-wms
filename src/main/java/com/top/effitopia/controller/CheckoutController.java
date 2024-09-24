package com.top.effitopia.controller;

import com.top.effitopia.dto.PageRequestDTO;
import com.top.effitopia.service.CheckoutService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/checkouts")
@Log4j2
@RequiredArgsConstructor
public class CheckoutController {

    private final CheckoutService checkoutService;

    /**
     *
     * @param pageRequestDTO
     * @param bindingResult
     * @param model
     */
    @GetMapping("/list")
    public void showList(@Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult, Model model) {
        log.info("CheckoutController showList GetMapping");
        if (bindingResult.hasErrors()) {
            pageRequestDTO = PageRequestDTO.builder().build();
        }
        model.addAttribute("responseDTO", checkoutService.getList(pageRequestDTO));
    }
}
