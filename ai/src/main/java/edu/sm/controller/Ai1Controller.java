package edu.sm.controller;

import edu.sm.app.springai.service1.AiServiceByChatClient;
import edu.sm.app.springai.service1.AiServiceChainOfThoughtPrompt;
import edu.sm.app.springai.service1.AiServiceFewShotPrompt;
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
    private final AiServiceChainOfThoughtPrompt aiServiceChainOfThoughtPrompt;
    private final AiServiceFewShotPrompt aiServiceFewShotPrompt;

    @RequestMapping("/chat-model")
    public String chatModal(@RequestParam("question") String question) {
        return aiServiceByChatClient.generateText(question);
    }
    @RequestMapping("/few-shot-prompt")
    public String fewShot(@RequestParam("question") String question) {
        return aiServiceFewShotPrompt.fewShotPrompt(question);
    }
    @RequestMapping("/chat-model-stream")
    public Flux<String> chatModalStream(@RequestParam("question") String question) {
        return aiServiceByChatClient.generateStreamText(question);
    }
    @RequestMapping("/chat-of-thought")
    public Flux<String> chatOfThought(@RequestParam("question") String question) {
        return aiServiceChainOfThoughtPrompt.chainOfThought(question);
    }



}








