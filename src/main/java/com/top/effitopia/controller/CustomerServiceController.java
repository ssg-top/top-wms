package com.top.effitopia.controller;

import com.top.effitopia.dto.CustomerAnswerDTO;
import com.top.effitopia.dto.CustomerInquiryDTO;
import com.top.effitopia.dto.Id;
import com.top.effitopia.dto.PageRequestDTO;
import com.top.effitopia.service.CSCService;
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

/**
 * 고객센터 Controller
 */
@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/cs")
public class CustomerServiceController {

    private final CSCService cscService;

    @GetMapping("/list")
    public void listInquiry(@Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult, Model model) {
        log.info("listInquiry Controller.............");

        if(bindingResult.hasErrors()) {
            log.info("todo List pageRequest error");
            pageRequestDTO = PageRequestDTO.builder().build();
        }
        model.addAttribute("responseDTO", cscService.getListInquiry(pageRequestDTO));

    }

    @GetMapping("/inquiry-read")
    public void readInquiry(Id id, Model model) {
        log.info("readInquiry Controller.............");
        CustomerInquiryDTO customerInquiryDTO = cscService.getInquiry(id.getId());

        model.addAttribute("inquiryDto", customerInquiryDTO);
    }

    @PostMapping("/inquiry-register")
    public String registerInquiry(@Valid CustomerInquiryDTO customerInquiryDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("registerInquiry Controller.............");

        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", bindingResult.getAllErrors());
            return "redirect:/cs/inquiry-read";
        }
        cscService.saveInquiry(customerInquiryDTO);

        return "redirect:/cs/inquiry-read";
    }

    @PostMapping("/inquiry-modify")
    public String modifyInquiry(@Valid CustomerInquiryDTO customerInquiryDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("modifyInquiry Controller.............");

        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", bindingResult.getAllErrors());
            return "redirect:/cs/inquiry-read";
        }
        cscService.modifyInquiry(customerInquiryDTO);

        return  "redirect:/cs/inquiry-read";
    }

    @PostMapping("/inquiry-remove")
    public String removeInquiry(Id id, PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes) {
        log.info("removeInquiry Controller.............");
        cscService.removeInquiry(id.getId());
        redirectAttributes.addAttribute("page", 1);
        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());

        return "redirect:/cs/inquiry-list";
    }


    //==============================================================================================


    @PostMapping("/answer-register")
    String registerAnswer(@Valid CustomerAnswerDTO customerAnswerDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("registerAnswer Controller.............");
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", bindingResult.getAllErrors());
            return "redirect:/cs/answer-read";
        }
        cscService.saveAnswer(customerAnswerDTO);
        return "redirect:/cs/answer-read";
    }

    @GetMapping("answer-read")
    void readAnswer(Id id, Model model) {
        log.info("readAnswer Controller.............");
        CustomerAnswerDTO customerAnswerDTO = cscService.getAnswer(id.getId());
        model.addAttribute("answerDto", customerAnswerDTO);
    }

    @PostMapping("answer-modify")
    String modifyAnswer(@Valid CustomerAnswerDTO customerAnswerDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("modifyAnswer Controller.............");
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", bindingResult.getAllErrors());
            return "redirect:/cs/answer-read";
        }
        cscService.modifyAnswer(customerAnswerDTO);
        return "redirect:/cs/answer-read";
    }

    @PostMapping("answer-remove")
    String removeAnswer(Id id, PageRequestDTO pageRequestDTO , RedirectAttributes redirectAttributes) {
        log.info("removeAnswer Controller.............");
        cscService.removeAnswer(id.getId());
        redirectAttributes.addAttribute("page", 1);
        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
        return "redirect:/cs/inquiry-read";
    }



}
