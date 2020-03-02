/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Stack;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author andre
 */
@Component
public class ScheduledTasks {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        static public Stack<BitCoinPrice> bcp_stack = new Stack<BitCoinPrice>();
        
	
        @Scheduled(fixedRate = 30000) //UPDATE BCP EVERY 30 SEC
	public void updateBCP() {
          System.out.println("Updating BCP\nThe time is now " + dateFormat.format(new Date()));

            String URL = "https://api.cryptonator.com/api/full/btc-eur";
            RestTemplate rt1 = new RestTemplate();
            BitCoinPrice bcp = rt1.getForObject(URL, BitCoinPrice.class);
            bcp_stack.add(bcp);
       }
      
        
}
