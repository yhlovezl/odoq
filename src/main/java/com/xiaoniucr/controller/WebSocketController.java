package com.xiaoniucr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yanghl
 * @date 2019/11/12 15:53
 */
@Controller
public class WebSocketController {

    @RequestMapping("/websocket.html")
    public String upload(){
        return "websocket";
    }
}
