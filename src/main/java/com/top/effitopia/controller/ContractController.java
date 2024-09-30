package com.top.effitopia.controller;

import com.top.effitopia.dto.ContractDTO;
import com.top.effitopia.dto.PageRequestDTO;
import com.top.effitopia.dto.PageResponseDTO;
import com.top.effitopia.dto.WarehouseCostDTO;
import com.top.effitopia.service.ContractService;
import com.top.effitopia.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/warehouse-contracts")
@RequiredArgsConstructor
public class ContractController {

    private final ContractService contractService;

    private final WarehouseService warehouseService;

    @GetMapping("/warehouse-list")
    public void getWarehouseList(Model model, PageRequestDTO pageRequestDTO, BindingResult bindingResult){
        PageResponseDTO pageResponseDTO = warehouseService.getWarehouseList(pageRequestDTO);

        if(bindingResult.hasErrors())
            model.addAttribute("error", "에러");
        else
            model.addAttribute("pageResponseDTO",pageResponseDTO);
    }

    @GetMapping("/{id}")
    public void registerForm(@PathVariable("id") Integer id, Model model, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            model.addAttribute("error", "에러");
        else {
            Optional<WarehouseCostDTO> warehouseCostDTO = contractService.get(id);
            model.addAttribute("warehouseCostDTO", warehouseCostDTO);
        }
    }

    @PostMapping("/register")
    public String register(@RequestBody ContractDTO contractDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors())
            redirectAttributes.addAttribute("error","에러");
        else{
            boolean result = contractService.save(contractDTO);
            if(result)
                redirectAttributes.addAttribute("clear","완료");
            else
                redirectAttributes.addAttribute("fail","실패");
            return "redirect:/warehouse-contracts";
        }
        return "redirect:/warehouse-contracts/warehouse-list";
    }

    @PostMapping("/approve")
    public String approveWarehouseContracts(@RequestParam("items") List<Integer> items, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors())
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
        else{
            contractService.modifyApprovalList(items);
            redirectAttributes.addAttribute("message","완료");
        }
        return "redirect:/warehouse-contracts/list";
    }

    @PostMapping("/reject")
    public String rejectWarehouseContracts(@RequestParam("items") List<Integer> items, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors())
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
        else{
            contractService.modifyRejectList(items);
            redirectAttributes.addAttribute("message","완료");
        }
        return "redirect:/warehouse-contracts/list";
    }

    @GetMapping("/list")
    public void getList(Model model, PageRequestDTO pageRequestDTO){
        PageResponseDTO pageResponseDTO = contractService.getListAll(pageRequestDTO);

        model.addAttribute("pageResponseDTO",pageResponseDTO);
    }
}
