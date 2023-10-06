package com.douzone.socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationController {

    @Autowired
    private SimpMessagingTemplate template;

    // 예시: 어떤 조건(여기서는 컬럼 추가)이 충족될 때 이 메서드를 호출해 메시지를 발행
    public void sendNotification(String message) {
        template.convertAndSend("/topic/notification", message);
    }
}
