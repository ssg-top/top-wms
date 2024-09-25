package com.top.effitopia.controller;

import com.top.effitopia.dto.ContractDTO;
import com.top.effitopia.dto.PageRequestDTO;
import com.top.effitopia.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ContractController {

    private final ContractService contractService;

    @GetMapping("/warehouse-contracts/warehouse-list")
    public void getWarehouseList(Model model, PageRequestDTO pageRequestDTO, BindingResult bindingResult){
    }

    @GetMapping("/warehouse-contracts/register")
    public void registerForm(@PathVariable("id") Long id, Model model, BindingResult bindingResult, RedirectAttributes redirectAttributes){
    }

    @PostMapping("/warehouse-contracts/register")
    public void register(ContractDTO contractDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
    }

    @GetMapping("/warehouse-contracts/request-list")
    public void getRequestList(Model model, PageRequestDTO pageRequestDTO, BindingResult bindingResult){
    }

    @PostMapping("/warehouse-contracts/approve")
    public void approveWarehouseContracts(@RequestParam("items") List<Integer> items, BindingResult bindingResult, RedirectAttributes redirectAttributes){
    }

    @PostMapping("/warehouse-contracts/reject")
    public void rejectWarehouseContracts(@RequestParam("items") List<Integer> items, BindingResult bindingResult, RedirectAttributes redirectAttributes){
    }

    @GetMapping("/warehouse-contracts/list")
    public void getList(Model model, PageRequestDTO pageRequestDTO, BindingResult bindingResult){
    }
}
