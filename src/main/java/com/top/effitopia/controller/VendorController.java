package com.top.effitopia.controller;

import com.top.effitopia.dto.PageRequestDTO;
import com.top.effitopia.dto.PageResponseDTO;
import com.top.effitopia.dto.VendorDTO;
import com.top.effitopia.service.VendorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Log4j2
@Controller
@RequestMapping("/vendors")
@RequiredArgsConstructor
public class VendorController {

    private final VendorService vendorService;

    @GetMapping("/list")
    public String getVendors(@ModelAttribute PageRequestDTO<VendorDTO> pageRequestDTO, Model model) {
        PageResponseDTO<VendorDTO> vendorPage = vendorService.getList(pageRequestDTO);
        model.addAttribute("vendors", vendorPage.getDtoList());
        model.addAttribute("page", vendorPage);
        return "vendor/list";  // Thymeleaf 템플릿
    }



    @GetMapping("/register")
    public String registerVendorForm() {
        return "vendor/register";
    }

    @PostMapping("/register")
    public String registerVendor(@ModelAttribute VendorDTO vendorDTO) {
        vendorService.save(vendorDTO);
        return "redirect:/vendors/list";
    }

    @GetMapping("/edit/{id}")
    public String editVendorForm(@PathVariable int id, Model model) {
        model.addAttribute("vendor", vendorService.get(id).orElseThrow(() -> new IllegalArgumentException("Invalid vendor ID")));
        return "vendor/edit";
    }

    @PostMapping("/edit/{id}")
    public String editVendor(@PathVariable int id, @ModelAttribute VendorDTO vendorDTO) {
        vendorService.modify(vendorDTO);
        return "redirect:/vendors/list";
    }

    @PostMapping("/delete/{id}")
    public String deleteVendor(@PathVariable int id) {
        vendorService.remove(id);
        return "redirect:/vendors/list";
    }
}
