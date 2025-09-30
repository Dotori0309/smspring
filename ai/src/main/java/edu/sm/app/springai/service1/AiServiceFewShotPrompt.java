package edu.sm.app.springai.service1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AiServiceFewShotPrompt {

    // ##### 필드 #####
    private final ChatClient chatClient;

    // ##### 생성자 #####
    public AiServiceFewShotPrompt(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    // ##### 메소드 #####
    public String fewShotPrompt(String order) {
        String promptTemplate = """
            고객 주문을 유효한 JSON 형식으로 바꿔주세요.
            추가 설명은 포함하지 마세요. JSON만 응답하세요.

            출력 스키마(예시):
            {
              "items": [
                {
                  "name": "메뉴명",
                  "quantity": 1,
                  "unit_price": 0,            // KRW, 숫자만
                  "modifications": {
                    "remove": [],             // 빼달라 한 재료들
                    "add": [],                // 추가 재료들
                    "options": []             // 맵기/곱빼기/덜맵게 등 옵션
                  }
                }
              ],
              "currency": "KRW"
            }

            예시1:
            주문 문장:
            김치볶음밥 하나(7000원), 계란후라이 추가(500원) 해주세요.
            JSON 응답:
            {
              "items": [
                {
                  "name": "김치볶음밥",
                  "quantity": 1,
                  "unit_price": 7000,
                  "modifications": {
                    "remove": [],
                    "add": ["계란후라이(+500)"],
                    "options": []
                  }
                }
              ],
              "currency": "KRW"
            }

            예시2:
            주문 문장:
            떡볶이 보통맛 2개(각 6000원), 오뎅 추가(1000원).
            JSON 응답:
            {
              "items": [
                {
                  "name": "떡볶이",
                  "quantity": 2,
                  "unit_price": 6000,
                  "modifications": {
                    "remove": [],
                    "add": ["오뎅(+1000)"],
                    "options": ["보통맛"]
                  }
                }
              ],
              "currency": "KRW"
            }

            고객 주문:
            %s
            """;

        String strPrompt = promptTemplate.formatted(order);

        Prompt prompt = Prompt.builder()
            .content(strPrompt)
            .build();

        // LLM으로 요청하고 응답을 받음
        String pizzaOrderJson = chatClient.prompt(prompt)
            .options(ChatOptions.builder()
                .build())
            .call()
            .content();

        return pizzaOrderJson;
    }
}