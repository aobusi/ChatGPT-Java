package com.example.demo.gpt.entity;

import lombok.Data;

import java.util.List;

@Data
public class GPTResponse {

    private String id;
    private Long created;
    private String model;
    private Usage usage;
    private List<Choice> choices;

}
