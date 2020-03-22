package com.example.demo;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "market",
    "price",
    "volume"
})

@Entity
public class Market {
    
    @Id
    @GeneratedValue
    @JsonProperty("id")
    private int id;
    @JsonProperty("ticker_id")
    private int ticker_id;
    @JsonProperty("market")
    private String market;
    @JsonProperty("price")
    private String price;
    @JsonProperty("volume")
    private Float volume;
    @Transient
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     *
     */
    public Market() {
    }

    /**
     * @param id
     * @param ticker_id
     * @param market
     * @param volume
     * @param price
     * 
     */
    public Market(int id, int ticker_id, String market, String price, Float volume) {
        super();      
        this.ticker_id = id;
        this.id = id;
        this.market = market;
        this.price = price;
        this.volume = volume;
    }

    @JsonProperty("market")
    public String getMarket() {
        return market;
    }

    @JsonProperty("market")
    public void setMarket(String market) {
        this.market = market;
    }

    @JsonProperty("price")
    public String getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(String price) {
        this.price = price;
    }

    @JsonProperty("volume")
    public String getVolume() {
        return volume.toString();
    }

    @JsonProperty("volume")
    public void setVolume(Float volume) {
        this.volume = volume;
    }
    
     @JsonProperty("id")
    public int getID() {
        return id;
    }

    @JsonProperty("id")
    public void setID(int tid) {
        this.id = tid;
    }
    @JsonProperty("ticker_id")
    public int getTicker_ID() {
        return ticker_id;
    }

    @JsonProperty("ticker_id")
    public void setTicker_ID(int tid) {
        this.ticker_id = tid;
    }
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "Market{" + "id=" + id + ", ticker_id=" + ticker_id + ", market=" + market + ", price=" + price + ", volume=" + volume + '}';
    }

}
