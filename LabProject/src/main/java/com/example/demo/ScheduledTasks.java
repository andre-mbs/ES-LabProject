/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.kafka.core.KafkaTemplate;

/**
 *
 * @author andre
 */
@Component
public class ScheduledTasks {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    static public Stack<BitCoinPrice> bcp_stack = new Stack<BitCoinPrice>();

    @Autowired
    private KafkaTemplate<String, String> publisherTemplate;
    private static final String TOPIC = "Publisher";

    Connection ct;

    @Scheduled(fixedRate = 10000) //UPDATE BCP EVERY 20 SEC
    public void updateBCP() {
        System.out.println("Updating BCP\nThe time is now " + dateFormat.format(new Date()));

        String URL = "https://api.cryptonator.com/api/full/btc-eur";
        RestTemplate rt1 = new RestTemplate();
        BitCoinPrice bcp = rt1.getForObject(URL, BitCoinPrice.class);
        bcp_stack.add(bcp);
        //SET class attribute ID for BitCoinPrice, Ticker and Market classes based on incrementations of the timestamp
        //to use in database and link BitCoinPrice to Ticker and Ticker to Market(s)
        bcp = setIDs(bcp);
        
        //ADD bcp to stac
            
        AddToDatabase(bcp);
        
        publishToKafka(bcp.toString());

    }

    public static BitCoinPrice getBcp() {
        //TODO: Stack should be replaced by DB
        return bcp_stack.peek();
    }

    private void AddToDatabase(BitCoinPrice bcp){
            try{
               
               ct = DriverManager.getConnection("jdbc:mysql://localhost:3307/demo","demo_user","demo_pass");
               Statement st = ct.createStatement();
               PreparedStatement pst;
               
               //ADD BITCOINPRICE
               String query = "INSERT INTO BitCoinPrice (id, timestamp, success) VALUES (? , ? , ? )";
               pst = ct.prepareStatement(query);
               
               pst.setInt(1, bcp.getID());
               pst.setInt(2, bcp.getTimestamp());
               pst.setBoolean(3, bcp.getSuccess());
               
               pst.execute();
               pst.close();
               
               System.out.println("ADDED BCP TO DATABASE");

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
               
               //System.out.println("ADDED TICKER");

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
                       //System.out.println("ADDED MARKET");

                }
               
               ct.close();
              
               //System.out.println("FINISHED ADDING to DATABASE");
           }
           catch(SQLException e){
               System.out.println("Not added, BCP already in database");
           }
            
            
    }
   private BitCoinPrice setIDs(BitCoinPrice bcp){
         //System.out.println("IDs:");
        
        bcp.setID(bcp.getTimestamp());
        //System.out.println("bcp: " +   bcp.getID());
        
       
        bcp.getTicker().setID(bcp.getTimestamp()+ 1 );
        //System.out.println("Ticker: " +   bcp.getTicker().getID());

        for( int i = 0; i< bcp.getTicker().getMarkets().size(); i++){
            Market m = bcp.getTicker().getMarkets().get(i);
            
            m.setTicker_ID(bcp.getTimestamp()+ 1);
            m.setID(bcp.getTimestamp()+ 2 + i);
            
            //System.out.println("Market "+ i +": " +   bcp.getTicker().getMarkets().get(i).getID());

        }
        return bcp;
    }
    public  static BitCoinPrice ReadLatestBCP(){
                try{
                    //Declare temporary variables
                    BitCoinPrice bcp = new BitCoinPrice();
                    Ticker ticker = new Ticker();
                    List<Market> markets = new LinkedList<Market>(); 
                    Market m;
                    //Connection to DB
                    Connection ct = DriverManager.getConnection("jdbc:mysql://localhost:3307/demo","demo_user","demo_pass");
                    Statement st = ct.createStatement();
                   
                    //BitCoinPrice ID = timestamp 
                    //Bigger ID > most recent BitCoinPrice
                    //Get Ticker
                    String queryTICKER = "SELECT * FROM Ticker WHERE bcp_id=(SELECT max(id) FROM BitCoinPrice)";    
                    ResultSet resTicker = st.executeQuery(queryTICKER);
                    resTicker.next();
                    
                    int tickerID = resTicker.getInt("id");
                    ticker.setID(tickerID);
                    ticker.setBase(resTicker.getString("base"));
                    ticker.setTarget(resTicker.getString("target"));
                    ticker.setChange(resTicker.getString("ticker_change"));
                    ticker.setVolume(resTicker.getString("volume"));
                    ticker.setPrice(resTicker.getString("price"));
                    
                    //GET Markets related to that Ticker
                    String queryMARKETS = "SELECT * FROM Market WHERE ticker_id="+tickerID;             
                    ResultSet  resMarkets = st.executeQuery(queryMARKETS);
                    
                    while(resMarkets.next()){
                        m = new Market();
                        m.setID(resMarkets.getInt("id"));
                        m.setTicker_ID(tickerID);
                        m.setMarket(resMarkets.getString("market"));
                        m.setPrice(resMarkets.getString("price"));
                        m.setVolume(resMarkets.getFloat("volume"));
                        
                        markets.add(m);
                    }
                    //SET the Ticker list for Markets
                    ticker.setMarkets(markets);
                    
                    //GET the BitCoinPrice, set the Ticker and return 
                    String queryBCP = "SELECT * FROM BitCoinPrice WHERE id=(SELECT max(id) FROM BitCoinPrice)";
                    ResultSet  resBCP = st.executeQuery(queryBCP);
                    resBCP.next();
                    
                    bcp.setID(resBCP.getInt("id"));
                    bcp.setSuccess(resBCP.getBoolean("success"));
                    bcp.setTimestamp(resBCP.getInt("timestamp"));
                    bcp.setTicker(ticker);   

                    ct.close();
                    return bcp;
                }
                catch(Exception e) {
                    return null;
                }
               
    }
    private void publishToKafka(String bcp) {
        publisherTemplate.send(TOPIC, bcp);
    }
}
