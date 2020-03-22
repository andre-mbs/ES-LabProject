/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Stack;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
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
   
    Connection ct; 
   

    @Scheduled(fixedRate = 10000) //UPDATE BCP EVERY 20 SEC
    public void updateBCP()  {
        System.out.println("Updating BCP\nThe time is now " + dateFormat.format(new Date()));

        String URL = "https://api.cryptonator.com/api/full/btc-eur";
        RestTemplate rt1 = new RestTemplate();
        BitCoinPrice bcp = rt1.getForObject(URL, BitCoinPrice.class);
        bcp_stack.add(bcp);
     
           /*
        em.getTransaction().begin();
        
        em.persist(bcp);
        em.getTransaction().commit();

        em.close();*/
           
        AddToDatabase(bcp);
        

    }
    private void AddToDatabase(BitCoinPrice bcp){
            try{
               
               ct = DriverManager.getConnection("jdbc:mysql://localhost:3307/demo","demo_user","demo_pass");
               Statement st = ct.createStatement();
               PreparedStatement pst;
               
               //ADD BITCOINPRICE
               String query = "INSERT INTO BitCoinPrice (id, timestamp, success) VALUES (? , ? , ? )";
               pst = ct.prepareStatement(query);
               
               //     pst.setInt(1, (int)Math.random()*1000);
               pst.setInt(1, bcp.getID());
               pst.setInt(2, bcp.getTimestamp());
               pst.setBoolean(3, bcp.getSuccess());
               
               pst.execute();
               pst.close();
               
               System.out.println("ADDED BCP");

               //ADD TICKER
               
               query = "INSERT INTO Ticker (id, bcp_id, base, target, price, volume, ticker_change) VALUES (? , ? , ? ,?, ? ,? , ?)";
               pst = ct.prepareStatement(query);
               pst.setInt(1, bcp.getTicker().getID());
               pst.setInt(2, bcp.getID());
               pst.setString(3, bcp.getTicker().getBase());
               pst.setString(4, bcp.getTicker().getTarget());
               pst.setString(5, bcp.getTicker().getPrice());
               pst.setString(6, bcp.getTicker().getVolume());
               pst.setString(7, bcp.getTicker().getChange());

               pst.execute();
               pst.close();
               
               System.out.println("ADDED TICKER");

               //ADD MARKETs
                for(Market m : bcp.getTicker().getMarkets()){
                        query = "INSERT INTO Market (id, ticker_id, market,  price, volume) VALUES (? , ? , ? ,?, ? )";
                        pst = ct.prepareStatement(query);
                        pst.setInt(1, m.getID());
                        pst.setInt(2, bcp.getTicker().getID());
                        pst.setString(3, m.getMarket());
                        pst.setString(4, m.getPrice());
                        pst.setString(5, m.getVolume());

                        pst.execute();
                        pst.close();
                       System.out.println("ADDED MARKET");

                }
               
               ct.close();
              
               System.out.println("FINISHED ADDING to DATABASE");
           }
           catch(SQLException e){
               System.out.println("Exception:\n"+e);
           }
            
            
    }
}
