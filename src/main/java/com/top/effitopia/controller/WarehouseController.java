package com.top.effitopia.controller;

import com.top.effitopia.dto.PageRequestDTO;
import com.top.effitopia.dto.WarehouseDTO;
import com.top.effitopia.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class WarehouseController {

    private final WarehouseService warehouseService;

    @GetMapping("/warehouses/register")
    public void registerForm(Model model){

    }

    @PostMapping("/warehouses/register")
    public String register(WarehouseDTO warehouseDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        return "";
    }

    @GetMapping("/warehouses/list")
    public void getList(Model model, PageRequestDTO pageRequestDTO, BindingResult bindingResult){

    }

    @GetMapping("/warehouses/{id}")
    public void get(@PathVariable("id") Long warehouse_id, Model model){

    }

    @PostMapping("/warehouses/{id}")
    public String delete(@PathVariable("id") Long warehouse_id, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        return "";
    }

    @PostMapping("/warehouses")
    public boolean modify(WarehouseDTO warehouseDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        return true;
    }
    @GetMapping("/warehouses/cell-list")
    public void getWarehouseCellList(Long id, PageRequestDTO pageRequestDTO, Model model, RedirectAttributes redirectAttributes){

    }
}
