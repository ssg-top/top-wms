package com.top.effitopia.controller;

import com.top.effitopia.dto.*;
import com.top.effitopia.service.StockCheckService;
import com.top.effitopia.service.StockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

/**
 * 재고실사 Controller
 */
@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/stockcheck")
public class StockCheckController {

    private final StockCheckService stockCheckService;
    private final StockService stockService;


    @GetMapping("/list")
    public String listStock(@Valid @ModelAttribute PageRequestDTO<StockSearchCond> pageRequestDTO, @ModelAttribute StockSearchCond searchCond, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("error", bindingResult.getAllErrors());
            return "redirect:/";
        }

        pageRequestDTO.setSearchCond(searchCond);
        PageResponseDTO<StockCheckDTO> pageResponseDTO = stockCheckService.getListStockCheck(pageRequestDTO);

        model.addAttribute("pageRequestDTO", pageRequestDTO);
        model.addAttribute("searchCond", searchCond);
        model.addAttribute("pageResponseDTO", pageResponseDTO);
        return "stock/register-stockcheck";
    }

    @GetMapping("/search")
    @ResponseBody
    public ResponseEntity<PageResponseDTO<StockDTO>> searchStockCheck(@ModelAttribute PageRequestDTO<StockSearchCond> pageRequestDTO, @ModelAttribute StockSearchCond searchCond) {
        pageRequestDTO.setSearchCond(searchCond);
        PageResponseDTO<StockDTO> pageResponseDTO = stockService.getListStock(pageRequestDTO);
        return ResponseEntity.ok(pageResponseDTO);
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<?> registerStockCheck(@RequestBody List<StockCheckDTO> stockChecks) {
        log.info("registerStockCheck controller...............");
        stockChecks.forEach(check -> log.info("controller check: " + check));
        try {
            stockCheckService.saveListStockCheck(stockChecks);
            return ResponseEntity.ok(Map.of("success", true));
        } catch (Exception e) {
            log.error("Error in registerStockCheck: ", e);
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", e.getMessage()));
        }
    }

    @PostMapping("/modify")
    @ResponseBody
    public ResponseEntity<?> modifyStockCheck(@RequestBody List<StockCheckDTO> stockChecks) {
        // 실사 수정
        return null;
    }

    @PostMapping("/delete")
    @ResponseBody
    public ResponseEntity<?> deleteStockCheck(@RequestBody List<Integer> stockCheckIds) {
        // 실사 삭제
        return null;
    }

//    @PostMapping("/apply")
//    @ResponseBody
//    public ResponseEntity<?> applyStockCheck(@RequestBody List<Integer> stockCheckIds) {
//        // 실사 반영 로직 구현
//        return null;
//    }

    @GetMapping("/apply")
    public String applyStockCheck(Model model){
        log.info("applyStockCheck controller........");
        try {
            model.addAttribute("message", "마감이 성공적으로 처리되었습니다.");
        } catch (Exception e) {
            log.error("Error in updateStock: ", e);
            model.addAttribute("error", "마감 처리 중 오류가 발생했습니다: " + e.getMessage());
        }
        return "stock/list-stockcheck";
    }

    @GetMapping("/list-stockcheck")
    @ResponseBody
    public ResponseEntity<PageResponseDTO<StockCheckDTO>> listStockCheck(@Valid @ModelAttribute PageRequestDTO<StockSearchCond> pageRequestDTO,
                                                                         @ModelAttribute StockSearchCond searchCond, BindingResult bindingResult, Model model) {
        log.info("listStockCheck controller........................");
        if(bindingResult.hasErrors()) {
            model.addAttribute("error", bindingResult.getAllErrors());
            return ResponseEntity.badRequest().body(null);
        }

        pageRequestDTO.setSearchCond(searchCond);
        PageResponseDTO<StockCheckDTO> pageResponseDTO = stockCheckService.getListStockCheck(pageRequestDTO);
        log.info("pageResponseDTO Controller!!!!!!!!!!!!!!!!!!!!: " + pageResponseDTO);

        model.addAttribute("pageRequestDTO", pageRequestDTO);
        model.addAttribute("searchCond", searchCond);
        model.addAttribute("pageResponseDTO", pageResponseDTO);
        return ResponseEntity.ok(pageResponseDTO);
    }
/*




    @GetMapping("/read")
    public void readStockCheck(Id id, PageRequestDTO pageRequestDTO, Model model) {
        log.info("readStockCheck StockCheckController.............");
        StockCheckDTO stockCheckDTO = stockCheckService.getstockCheck(id.getId());
        model.addAttribute("stockCheck", stockCheckDTO);
    }

    @PostMapping("/modify")
    public String modifyStockCheck(@Valid StockCheckDTO stockCheckDTO, PageRequestDTO pageRequestDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("modifyStockCheck StockCheckController.............");
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("error", bindingResult.getAllErrors());
            return "redirect:/list";
        }

        stockCheckService.modifyStockCheck(stockCheckDTO);
        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
        return "redirect:/list";
    }

    @PostMapping("/remove")
    public String removeStockCheck(Id id, PageRequestDTO pageRequestDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("removeStockCheck StockCheckController.............");
        stockCheckService.removeStockCheck(id.getId());
        redirectAttributes.addAttribute("page", 1);
        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
        return "redirect:/list";
    }

    @PostMapping("/apply")
    public String applyStockCheck(PageRequestDTO pageRequestDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("applyStockCheck StockCheckController.............");
        //stockCheckService.applyListStockCheck();
        return "redirect:/list";
    }*/
}
