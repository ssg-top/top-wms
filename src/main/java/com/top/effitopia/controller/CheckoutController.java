package com.top.effitopia.controller;

import com.top.effitopia.dto.CheckoutAnswerDTO;
import com.top.effitopia.dto.CheckoutDTO;
import com.top.effitopia.dto.CheckoutQuestionDTO;
import com.top.effitopia.dto.PageRequestDTO;
import com.top.effitopia.service.CheckoutService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/checkouts")
@Log4j2
@RequiredArgsConstructor
public class CheckoutController {

    private final CheckoutService checkoutService;

//    @GetMapping("/list")
//    public void showList(@Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult, Model model) {
//        log.info("CheckoutController showList GetMapping");
//        if (bindingResult.hasErrors()) {
//            log.info("CheckoutController showList GetMapping has Errors");
//            pageRequestDTO = PageRequestDTO.builder().build();
//        }
//        model.addAttribute("responseDTO", checkoutService.getList(pageRequestDTO));
//    }
//
//    @GetMapping("/register")
//    public void showRegisterForm() {
//        log.info("CheckoutController showRegisterForm GetMapping");
//    }
//
//    @PostMapping("/register")
//    public String registerForm(@Valid CheckoutDTO checkoutDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
//        log.info("CheckoutController registerForm PostMapping");
//        if (bindingResult.hasErrors()) {
//            log.info("CheckoutController registerForm PostMapping has Errors");
//            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
//            return "redirect:/checkouts/register";
//        }
//        log.info("checkoutDTO{}", checkoutDTO);
//        checkoutService.save(checkoutDTO);
//        return "redirect:/checkouts/list";
//    }

//    @PostMapping("/register")
//    public String registerForm(@Valid CheckoutDTO checkoutDTO, @Valid CheckoutQuestionDTO checkoutQuestionDTO, @Valid CheckoutAnswerDTO checkoutAnswerDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
//        log.info("CheckoutController registerForm PostMapping");
//        if (bindingResult.hasErrors()) {
//            log.info("CheckoutController registerForm PostMapping has Errors");
//            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
//            return "redirect:/checkouts/register";
//        }
//        log.info("checkoutDTO{}", checkoutDTO);
//        log.info("checkoutQuestionDTO{}", checkoutQuestionDTO);
//        log.info("checkoutAnswerDTO{}", checkoutAnswerDTO);
//        checkoutService.save(checkoutDTO, checkoutQuestionDTO, checkoutAnswerDTO);
//        return "redirect:/checkouts/list";
//    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        log.info("CheckoutController showRegisterForm GetMapping");

        List<CheckoutQuestionDTO> questionList = checkoutService.getAllQuestions();
        model.addAttribute("questions", questionList);
        return "/checkouts/register";
    }

    @PostMapping("/register")
    public String registerForm(
            @Valid CheckoutDTO checkoutDTO,
            @Valid List<CheckoutAnswerDTO> answers,  // 답변 리스트를 따로 받음
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        log.info("CheckoutController registerForm PostMapping");

        if (bindingResult.hasErrors()) {
            log.info("CheckoutController registerForm PostMapping has Errors");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/checkouts/register";
        }

        log.info("checkoutDTO: {}", checkoutDTO);
        log.info("answers: {}", answers);

        checkoutService.save(checkoutDTO, answers);
        return "redirect:/checkouts/list";
    }
}
