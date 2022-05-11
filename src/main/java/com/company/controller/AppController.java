package com.company.controller;

import com.company.model.Product;
import com.company.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AppController {

    @Autowired
    private ProductService service;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        List<Product> listProducts = service.listAll();
        model.addAttribute("listProducts", listProducts);

        return "index";
    }

    @GetMapping("/new")
    public String showNewProduct(Model model) {
        Product product=new Product();
        model.addAttribute("product",product);
        return "new_product";
    }

    @PostMapping("/save")
    public String showNewProduct(@ModelAttribute("product") Product product) {
        service.save(product);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showEditProductPage(@PathVariable("id") Long id,Model model){
        Product product =service.get(id);
        model.addAttribute("product",product);
        return "edit_product";
    }

    @GetMapping("/delete/{id}")
    public String showProductPage(@PathVariable("id") Long id,Model model){
        service.delete(id);
        return "redirect:/";
    }



}