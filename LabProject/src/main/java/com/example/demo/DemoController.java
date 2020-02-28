/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import java.text.SimpleDateFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
/**
 *
 * @author Tiago Feitor
 */
@Controller
public class DemoController {
    
   @RequestMapping("/demo") 
   public ModelAndView demo(){
       System.out.println("\nFrom the demo controller\n");
       ModelAndView mv = new ModelAndView("demo.html");
       mv.addObject("demoTitle", "demoText");
       return mv;
    }
   
   @RequestMapping("/")
   public ModelAndView home(){
       
       System.out.println("\nFrom the demo controller\n");
       
       ModelAndView mv = new ModelAndView("home.html");
       
       String URL = "http://api.ipma.pt/open-data/forecast/meteorology/cities/daily/hp-daily-forecast-day0.json";
       RestTemplate rt1 = new RestTemplate();
       DailyForecast bcp = rt1.getForObject(URL, DailyForecast.class);
       
       
       mv.addObject("forecast", bcp );

       return mv;
    }
}
