package edu.sm.controller;

import edu.sm.app.dto.Product;
import edu.sm.app.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    final ProductService productService;

    String dir="product/";

    @RequestMapping("")
    public String main(Model model) throws Exception {
        model.addAttribute("products", productService.get());
        model.addAttribute("center",dir+"center");
        model.addAttribute("left",dir+"left");
        return "index";
    }
    @RequestMapping("/add")
    public String add(Model model) {
        model.addAttribute("center",dir+"add");
        model.addAttribute("left",dir+"left");
        return "index";
    }
    @RequestMapping("/get")
    public String get(Model model) {
        model.addAttribute("center",dir+"get");
        model.addAttribute("left",dir+"left");
        return "index";
    }

    @PostMapping("/registerimpl")
    public String registerImpl(@ModelAttribute Product product) throws Exception {
        log.info("Product received: {}", product);
        productService.register(product);
        return "redirect:/product";
    }
}