/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import java.sql.Timestamp;
import java.util.Date;
import java.text.SimpleDateFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;
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
    public ModelAndView demo() {
        System.out.println("\nFrom the demo controller\n");
        ModelAndView mv = new ModelAndView("demo.html");
        mv.addObject("demoTitle", "demoText");
        return mv;
    }

    @RequestMapping("/")
    public ModelAndView home() {

        ModelAndView mv = new ModelAndView("home.html");

        //GET LATEST BITCOINPRICE UPDATED EVERY 30 SEC
         //GET LATEST BITCOINPRICE UPDATED EVERY 30 SEC
        BitCoinPrice bcp;
       //bpc = ScheduledTasks.bcp_stack.peek();
        bcp = ScheduledTasks.ReadLatestBCP();

        mv.addObject("btcprice", bcp);

        mv.addObject("time", timeStampToDate(bcp.getTimestamp()));
        return mv;
    }

    public String timeStampToDate(int timestamp) {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Date date = ts;
        return date.toString();
    }
}
