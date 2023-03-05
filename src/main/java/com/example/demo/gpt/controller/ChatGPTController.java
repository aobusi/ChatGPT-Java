package com.example.demo.gpt.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.example.demo.gpt.entity.GPTResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/chat")
public class ChatGPTController {

    @PostMapping("/GPT")
    public void chatGPTHutool(@RequestBody String question) {
        HashMap<Object, Object> turboRequestBody = new HashMap<>();
        HashMap<Object, Object> message = new HashMap<>();
        ArrayList<HashMap> hashMaps = new ArrayList<>();
        message.put("role", "user");
        message.put("content", question);
        hashMaps.add(message);
        turboRequestBody.put("model", "gpt-3.5-turbo");
        turboRequestBody.put("messages", hashMaps);
        
        /********************* openai key ************************/ 
        String openAIKey = "your openai key";

        String url = "https://api.openai.com/v1/chat/completions";
        String response = HttpRequest.post(url)
                .setProxy(
                        new Proxy(Proxy.Type.HTTP,
                                new InetSocketAddress(
                                        "127.0.0.1", 7890
                                ))
                )
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + openAIKey)
                .timeout(200000)
                .body(JSONUtil.toJsonPrettyStr(turboRequestBody))
                .execute().body();
        GPTResponse gptResponse = JSONUtil.toBean(response.toString(), GPTResponse.class);
        System.out.println("gptResponse = " + gptResponse);
    }

}