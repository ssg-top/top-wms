package com.top.effitopia.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.top.effitopia.dto.*;
import com.top.effitopia.service.ContractService;
import com.top.effitopia.service.WarehouseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/contract")
@RequiredArgsConstructor
@Log4j2
public class ContractController {

    private final ContractService contractService;

    private final WarehouseService warehouseService;

    @GetMapping("/list")
    public void getContractList(Model model, @Valid @ModelAttribute("pageRequestDTO") PageRequestDTO<WarehouseDTO> pageRequestDTO, BindingResult bindingResult) throws JsonProcessingException {
        log.info("get list");
        if (bindingResult.hasErrors()){
            model.addAttribute("errorMessages", bindingResult.getAllErrors());
            log.info("Errors: " + bindingResult.getAllErrors());
        }
        else {
            PageResponseDTO<WarehouseDTO> pageResponseDTO = warehouseService.getWarehouseList(pageRequestDTO);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            String jsonUtilizationList = objectMapper.writeValueAsString(pageResponseDTO.getDtoList());
            model.addAttribute("jsonUtilizationList", jsonUtilizationList);
            model.addAttribute("responseDTO", pageResponseDTO);
            log.info("여기여기여기여기여기여기여기여기여기여기여기여기여기여기여기여기" + pageResponseDTO);
            log.info("여기여기여기2432432432432432432432432423423" + jsonUtilizationList);
        }
    }

    @GetMapping("/contract")
    public void getList(Model model, @Valid @ModelAttribute("pageRequestDTO") PageRequestDTO<ContractDTO> pageRequestDTO, BindingResult bindingResult){
        log.info("get list");
        if (bindingResult.hasErrors()){
            model.addAttribute("errorMessages", bindingResult.getAllErrors());
            log.info("Errors: " + bindingResult.getAllErrors());
        }
        else {
            PageResponseDTO<ContractDTO> pageResponseDTO = contractService.getListAll(pageRequestDTO);
            model.addAttribute("responseDTO", pageResponseDTO);
            log.info("여기여기여기여기여기여기여기여기여기여기여기여기여기여기여기여기" + pageResponseDTO);
        }
    }

    @GetMapping("/register")
    public void registerForm(@RequestParam("id")Integer id, Model model){
        log.info("드루와");

        WarehouseCostDTO warehouseCostDTO = contractService.get(id);

        model.addAttribute("warehouseCostDTO", warehouseCostDTO);
        log.info(warehouseCostDTO);
    }

    @PostMapping("/contract")
    public String register(@RequestParam("name")String name, @RequestParam("id") String id, @ModelAttribute ContractDTO contractDTO, Model model){
        log.info("ㅇ우ㅜㅜㅇㄴ창ㄴㄹㅇ룽ㄴ렁ㄴㄹㅇㄴㄹㅇㄴ루ㅏㅓㄴ우렁누러누러ㅜㄴ룬어ㅏㄹㅇ나ㅓㄹ운");
        int warehouseId = Integer.parseInt(id);

        log.info("sdhsakdhskhsahdkjsadh"+warehouseId);

        if(name == null)
            model.addAttribute("error","에러");

        else {
            Integer memberId = contractService.check(name);
            log.info(memberId);
            if (memberId == null) {
                MemberDTO memberDTO = new MemberDTO();
                WarehouseDTO warehouseDTO = new WarehouseDTO();
                Integer getId = contractService.getUserId(name);
                memberDTO.setId(getId);
                warehouseDTO.setId(warehouseId);
                contractDTO.setMemberDTO(memberDTO);
                contractDTO.setWarehouseDTO(warehouseDTO);
                boolean result = contractService.save(contractDTO);
                if (result)
                    model.addAttribute("clear", "완료");
                else
                    model.addAttribute("fail", "실패");
            }
        }
        return "contract/contract";
    }

//    @PostMapping("/approve")
//    public String approveWarehouseContracts(@RequestParam("items") List<Integer> items, BindingResult bindingResult, RedirectAttributes redirectAttributes){
//        if(bindingResult.hasErrors())
//            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
//        else{
//            contractService.modifyApprovalList(items);
//            redirectAttributes.addAttribute("message","완료");
//        }
//        return "redirect:/warehouse-contracts/list";
//    }

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

//    @PostMapping("/approve")
//    @ResponseBody
//    public ResponseEntity<String> handlePostRequest(@RequestBody Map<String, List<Integer>> requestData) {
//        // items 리스트를 받아옴
//        List<Integer> items = requestData.get("items");
//
//        // 선택된 체크박스의 ID 값들을 확인
//        for (Integer itemId : items) {
//            System.out.println("선택된 항목 ID: " + itemId);
//        }
//
//        return ResponseEntity.ok("데이터 수신 성공");
//    }
}
