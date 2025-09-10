package edu.sm.controller;

import com.github.pagehelper.PageInfo;
import edu.sm.app.dto.Product;
import edu.sm.app.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String get(Model model) throws Exception {
        java.util.List<edu.sm.app.dto.Product> products = productService.get();
        log.info("Number of products fetched: {}", products.size());
        model.addAttribute("plist", products);
        model.addAttribute("center",dir+"get");
        model.addAttribute("left",dir+"left");
        return "index";
    }

    @RequestMapping("/getpage")
    public String getpage(@RequestParam(value="pageNo", defaultValue = "1") int pageNo, Model model) throws Exception {
        PageInfo<Product> p;
        try {
            p = new PageInfo<>(productService.getPage(pageNo), 5); // 5:하단 네비게이션 개수
        } catch (Exception e) {
            throw new Exception("시스템 장애: ER0001");
        }
        model.addAttribute("pageinfo",p);
        model.addAttribute("pageurl","/product/getpage");
        model.addAttribute("left",dir+"left");
        model.addAttribute("center",dir+"getpage");
        return "index";
    }

    @PostMapping("/registerimpl")
    public String registerImpl(@ModelAttribute Product product) throws Exception {
        log.info("Product received: {}", product);
        productService.register(product);
        return "redirect:/product";
    }
}