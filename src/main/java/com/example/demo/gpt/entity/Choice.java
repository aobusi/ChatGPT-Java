package com.example.demo.gpt.entity;

import lombok.Data;

@Data
public class Choice {

    private Message message;
    private String finish_reason;
    private Integer index;

}
