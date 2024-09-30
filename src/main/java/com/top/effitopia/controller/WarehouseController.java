package com.top.effitopia.controller;

import com.top.effitopia.domain.Cell;
import com.top.effitopia.dto.*;
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
@RequestMapping("/warehouses")
@RequiredArgsConstructor
@Log4j2
public class WarehouseController {

    private final WarehouseService warehouseService;

    @GetMapping("/register")
    public void registerForm(Model model) {
        log.info("GET register");
        List<WarehouseTypeDTO> warehouseTypeDTO = warehouseService.getTypeList();

        model.addAttribute("warehouseType",warehouseTypeDTO);

    }

    @GetMapping("/action")
    @ResponseBody
    public ResponseEntity<Map<String, String>> duplicateName(@RequestParam("warehouseName") String warehouseName) {
        Map<String, String> response = new HashMap<>();

        Optional<String> name = warehouseService.get(warehouseName);
        if (name.isPresent())
            response.put("message", "중복된 창고 이름입니다.");
        else
            response.put("message", "사용 가능한 창고 이름입니다.");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public String register(@RequestBody @Valid WarehouseDTO warehouseDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        log.info("GET register");

        if (bindingResult.hasErrors()) {
            log.info("has error......");
            redirectAttributes.addFlashAttribute("warehouseDTO errors", bindingResult.getAllErrors());
            return "redirect:/register";
        }
        log.info("warehouseDTO{}", warehouseDTO);
        warehouseService.save(warehouseDTO);
        return "redirect:/list";
    }

    @GetMapping("/list")
    public void getList(Model model, @Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        log.info("get list");
        if (bindingResult.hasErrors())
            redirectAttributes.addFlashAttribute("pageRequestDTO errors", bindingResult.getAllErrors());
        else
            model.addAttribute("responseDTO", warehouseService.getWarehouseList(pageRequestDTO));
    }

    @GetMapping("/list/{id}")
    public void get(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes){
        log.info("get get");

        Optional<WarehouseDTO> warehouseDTO = warehouseService.get(id);

        if(warehouseDTO.isPresent())
            model.addAttribute("warehouseDTO",warehouseDTO);
        else
            model.addAttribute("errorMessage", "해당 ID에 해당하는 창고를 찾을 수 없습니다.");
    }

    @PostMapping("/{id}")
    public String delete(@PathVariable("id") Integer id, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        return "";
    }

    @PostMapping("/list")
    public String modify(@RequestBody @Valid WarehouseDTO warehouseDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {
            log.info("has error......");
            redirectAttributes.addFlashAttribute("warehouseDTO errors", bindingResult.getAllErrors());
        }
        else{
            boolean result = warehouseService.modify(warehouseDTO);

            if(result)
                redirectAttributes.addFlashAttribute("warehouseDTO Modify", "수정되었습니다.");
            else
                redirectAttributes.addFlashAttribute("warehouseDTO Modify", "수정을 실패하였습니다.");
        }
        return "redirect:/list";
    }
    @GetMapping("/{id}/cell")
    public void getWarehouseCellList(@PathVariable("id") Integer id, PageRequestDTO pageRequestDTO, Model model, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {
            log.info("has error......");
            redirectAttributes.addFlashAttribute("cellDTO errors", bindingResult.getAllErrors());
        }
        else{
            pageRequestDTO.setSearchCond(id);
            PageResponseDTO pageResponseDTO = warehouseService.getCellList(pageRequestDTO);

            model.addAttribute("pageResponseDTO",pageResponseDTO);
        }
    }
}
