package edu.sm.app.springai.service1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AiServiceFewShotPrompt2 {
    // ##### 필드 #####
    private final ChatClient chatClient;

    // ##### 생성자 #####
    public AiServiceFewShotPrompt2(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    // ##### 메소드 #####
    public String fewShotPrompt2(String order) {
        // 프롬프트 생성
        String strPrompt = """
            고객 주문을 유효한 JSON 형식으로 바꿔주세요.
            아래 규칙을 절대로 위반해서는 안 됩니다:
            1. 출력 형식: 최종 응답은 반드시 `order_items`라는 단일 키를 가진 JSON 객체여야 합니다. JSON 배열 `[]`이나 다른 형식으로 시작해서는 안 됩니다.
            2. 필드 이름: 각 메뉴 객체에는 `menu_name`, `quantity`, `price`, `image_name` 네 가지 키만 포함해야 합니다. `type`과 같은 다른 키를 사용하지 마세요.
            3. 메뉴 이름 (`menu_name`): 메뉴 이름은 반드시 '메뉴판'에 있는 한글 이름 그대로 사용해야 합니다. 영어로 번역하지 마세요.
            4. 가격 (`price`): 가격은 '메뉴판'에 명시된 숫자만 사용해야 합니다.
            5. 이미지 이름 (`image_name`): 모든 메뉴의 `image_name`은 'kimbab1.jpg'로 고정입니다.
            6. 수량 (`quantity`): 고객이 언급한 수량을 정확히 반영하세요.
            7. 메뉴판에 없는 메뉴는 응답에 포함시키지 마세요.
            8. 추가 설명, 주석, 그리고 ```json 같은 마크다운 형식 없이 순수한 JSON 문자열로만 응답해야 합니다.
            

            --- 메뉴판 ---
            [김밥]
            - 원조김밥 / 가격: 3500 / 이미지: kimbab1.jpg
            - 치즈김밥 / 가격: 4500 / 이미지: kimbab1.jpg
            - 참치김밥 / 가격: 6000 / 이미지: kimbab1.jpg
            - 김치김밥 / 가격: 5000 / 이미지: kimbab1.jpg
            - 샐러드김밥 / 가격: 5000 / 이미지: kimbab1.jpg
            - 소고기김밥 / 가격: 5000 / 이미지: kimbab1.jpg
            - 계란말이김밥 / 가격: 5500 / 이미지: kimbab1.jpg
            - 고추김밥 / 가격: 5000 / 이미지: kimbab1.jpg
            - 누드김밥 / 가격: 4500 / 이미지: kimbab1.jpg
            - 모듬김밥 / 가격: 6000 / 이미지: kimbab1.jpg
            - 참땡김밥 / 가격: 6000 / 이미지: kimbab1.jpg
            - 계김김밥 / 가격: 6000 / 이미지: kimbab1.jpg
            --- 메뉴판 끝 ---

            

            고객 주문: %s""".formatted(order);

        Prompt prompt = Prompt.builder()
                .content(strPrompt)
                .build();

        // LLM으로 요청하고 응답을 받음
        String kookbobOrderJson = chatClient.prompt(prompt)
                .options(ChatOptions.builder()
                        .build())
                .call()
                .content();
        return kookbobOrderJson;
    }
}