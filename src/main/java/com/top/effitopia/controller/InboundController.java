package com.top.effitopia.controller;

import com.top.effitopia.domain.ProductMajorCategory;
import com.top.effitopia.domain.ProductMiddleCategory;
import com.top.effitopia.domain.ProductSubclassCategory;
import com.top.effitopia.dto.InboundDTO;
import com.top.effitopia.dto.InboundSearchCond;
import com.top.effitopia.dto.PageRequestDTO;
import com.top.effitopia.dto.PageResponseDTO;
import com.top.effitopia.dto.ProductDTO;
import com.top.effitopia.dto.ProductSearchCond;
import com.top.effitopia.service.CategoryService;
import com.top.effitopia.service.InboundService;
import com.top.effitopia.service.ProductService;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Log4j2
@Controller
@RequestMapping("/inbound")
@RequiredArgsConstructor
public class InboundController {

    private final ProductService productService;
    private final InboundService inboundService;
    private final CategoryService categoryService;

    @GetMapping("/inbound")
    public String getProducts(Model model, @ModelAttribute PageRequestDTO<ProductSearchCond> pageRequestDTO) {

        /*List<ProductMajorCategory> majorCategories = categoryService.getAllMajorCategories();
        List<ProductMiddleCategory> middleCategories = categoryService.getAllMiddleCategories();
        List<ProductSubclassCategory> subclassCategories = categoryService.getAllSubclassCategories();

        model.addAttribute("majorCategories", majorCategories);
        model.addAttribute("middleCategories", middleCategories);
        model.addAttribute("subclassCategories", subclassCategories);*/

        PageResponseDTO<ProductDTO> productPage = productService.getList(pageRequestDTO);
        model.addAttribute("productPage", productPage);
        return "inbound/inbound";
    }

    @GetMapping("/inboundRequestStatus")
    public void getInboundRequests(Model model, @ModelAttribute PageRequestDTO<InboundSearchCond> pageRequestDTO) {
        PageResponseDTO<InboundDTO> inboundPage = inboundService.getList(pageRequestDTO);

       /* List<ProductMajorCategory> majorCategories = categoryService.getAllMajorCategories();
        List<ProductMiddleCategory> middleCategories = categoryService.getAllMiddleCategories();
        List<ProductSubclassCategory> subclassCategories = categoryService.getAllSubclassCategories();

        model.addAttribute("majorCategories", majorCategories != null ? majorCategories : Collections.emptyList());
        model.addAttribute("middleCategories", middleCategories != null ? middleCategories : Collections.emptyList());
        model.addAttribute("subclassCategories", subclassCategories != null ? subclassCategories : Collections.emptyList());
        */
        model.addAttribute("inboundPage", inboundPage);

    }

    @PostMapping("/save")
    public String saveInbound(@ModelAttribute InboundDTO inboundDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "inbound/inboundRequestStatus";
        }

        boolean success = inboundService.save(inboundDTO);

        if (success) {
            return "redirect:/inbound/inbound";
        } else {
            return "error";
        }

    }
    
}
