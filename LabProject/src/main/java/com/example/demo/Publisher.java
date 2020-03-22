/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author andre
 */
@RestController
@RequestMapping("publisher")
public class Publisher {
    
    @Autowired
    private KafkaTemplate<String, String> publisherTemplate;
    
    private static final String TOPIC = "Publisher";
    
    @GetMapping("/{message}")
    public String post(@PathVariable("message") final String message){
        
        publisherTemplate.send(TOPIC, message);
        
        return "Message: " + message;
    }
    
}
