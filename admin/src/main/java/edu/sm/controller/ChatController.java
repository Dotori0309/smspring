package edu.sm.controller;

import com.github.pagehelper.PageInfo;
import edu.sm.app.dto.chat;
import edu.sm.app.dto.chatSearch;
import edu.sm.app.service.chatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/chat")
@RequiredArgsConstructor
public class chatController {

    final chatService chatService;


    String dir="chat/";

    @RequestMapping("/add")
    public String add(Model model) {
        model.addAttribute("center",dir+"add");
        return "index";
    }
    @RequestMapping("/addimpl")
    public String addimpl(Model model, chat chat) throws Exception {

        chatService.register(chat);
        return "redirect:/chat/get";
    }
    @RequestMapping("/updateimpl")
    public String updateimpl(Model model, chat chat) throws Exception {
        chatService.modify(chat);
        return "redirect:/chat/detail?id="+chat.getchatId();
    }
    @RequestMapping("/get")
    public String get(Model model) throws Exception {
        model.addAttribute("clist",chatService.get());
        model.addAttribute("center",dir+"get");
        return "index";
    }
    @RequestMapping("/detail")
    public String detail(Model model, @RequestParam("id") String id) throws Exception {
        chat chat = chatService.get(id);

        model.addAttribute("chat",chat);
        model.addAttribute("center",dir+"detail");
        return "index";
    }
    @RequestMapping("/delete")
    public String delete(Model model, @RequestParam("id") String id) throws Exception {
        chatService.remove(id);
        return "redirect:/chat/get";
    }

}