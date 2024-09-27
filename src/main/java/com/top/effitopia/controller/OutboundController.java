package com.top.effitopia.controller;

import com.top.effitopia.dto.OrderDTO;
import com.top.effitopia.dto.OutboundDTO;
import com.top.effitopia.dto.PageRequestDTO;
import com.top.effitopia.dto.PageResponseDTO;
import com.top.effitopia.enumeration.OutboundStatus;
import com.top.effitopia.service.OutboundService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/outbounds")
@Log4j2
@RequiredArgsConstructor
public class OutboundController {

    private final OutboundService outboundService;

    @GetMapping("/register")
    public void showRegisterForm() {
        log.info("OutboundController showRegisterForm GetMapping");
    }

    @PostMapping("/register")
    public String registerForm(@ModelAttribute("orderDTO") @Valid OrderDTO orderDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("OutboundController registerForm PostMapping");

        if (bindingResult.hasErrors()) {
            log.info("OutboundController registerForm PostMapping has Errors");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/outbounds/register";
        }

        outboundService.registerOrderAndOutbound(orderDTO);
        redirectAttributes.addFlashAttribute("registerSuccess", "주문과 출고 정보가 등록되었습니다.");
        return "redirect:/outbounds/list";
    }

    @GetMapping("/list")
    public void showList(@ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO, Model model) {
        log.info("OutboundController showList GetMapping");
        PageResponseDTO<OutboundDTO> responseDTO = outboundService.getList(pageRequestDTO);
        model.addAttribute("responseDTO", responseDTO);
    }

    @GetMapping("/read")
    public void read(@ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO, Integer outboundId, Model model) {
        log.info("OutboundController read GetMapping");
        OutboundDTO outboundDTO = outboundService.getOutboundDetail(outboundId);
        model.addAttribute("outbound", outboundDTO);
    }

    @PostMapping("/updateStatus")
    public String updateStatus(@RequestParam("outboundId") Integer outboundId, @RequestParam("status") OutboundStatus status, RedirectAttributes redirectAttributes) {
        log.info("OutboundController updateStatus PostMapping - outboundId: {}, status: {}", outboundId, status);
        outboundService.updateOutboundStatus(outboundId, status);
        redirectAttributes.addFlashAttribute("updateSuccess", "출고 상태가 변경되었습니다.");
        return "redirect:/outbounds/list";
    }

    @PostMapping("/updateOrder")
    public String updateOrder(@ModelAttribute("orderDTO") @Valid OrderDTO orderDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("OutboundController updateOrder PostMapping");

        if (bindingResult.hasErrors()) {
            log.info("OutboundController updateOrder PostMapping has Errors");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/outbounds/read?outboundId=" + orderDTO.getOrderId();
        }

        outboundService.updateOrder(orderDTO);
        redirectAttributes.addFlashAttribute("updateSuccess", "주문 정보가 수정되었습니다.");
        return "redirect:/outbounds/list";
    }
}
