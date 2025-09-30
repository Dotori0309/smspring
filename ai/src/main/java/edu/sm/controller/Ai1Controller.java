package edu.sm.controller;

import edu.sm.app.springai.service1.AiServiceByChatClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@Slf4j
@RequestMapping("/ai1")
@RequiredArgsConstructor
public class Ai1Controller {
    final AiServiceByChatClient aiServiceByChatClient;

    @RequestMapping("/ai1/chat-model")
    public String chatModal(@RequestParam("question") String question) {
        return aiServiceByChatClient.generateText(question);
    }
    @RequestMapping("/ai1/chat-model-stream")
    public Flux<String> chatModalStream(@RequestParam("question") String question) {
        return aiServiceByChatClient.generateStreamText(question);
    }



}








