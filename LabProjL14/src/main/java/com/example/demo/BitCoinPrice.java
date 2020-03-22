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
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "ticker",
    "timestamp",
    "success",
    "error"
})

@Entity
public class BitCoinPrice {

    @Id
    @GeneratedValue
    @JsonProperty("id")
    private int id;
    @OneToOne
    @JsonProperty("ticker")
    private Ticker ticker;
    //@JsonProperty("ticker_id")
    //private int ticker_id;
    @JsonProperty("timestamp")
    private Integer timestamp;
    @JsonProperty("success")
    private Boolean success;
    @Transient
    @JsonProperty("error")
    private String error;
    @Transient
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     *
     */
    public BitCoinPrice() {
    }

    /**
     * @param id
     * @param ticker_id
     * @param ticker
     * @param success
     * @param error
     * @param timestamp
     */
    public BitCoinPrice(int id,int ticker_id, Ticker ticker, Integer timestamp, Boolean success, String error) {
        super();
        this.id = id;
        //this.ticker_id = ticker_id;
        this.ticker = ticker;
        this.timestamp = timestamp;
        this.success = success;
        this.error = error;
    }

    @JsonProperty("ticker")
    public Ticker getTicker() {
        return ticker;
    }

    @JsonProperty("ticker")
    public void setTicker(Ticker ticker) {
        this.ticker = ticker;
    }
  @JsonProperty("id")
    public int getID() {
        return id;
    }

    @JsonProperty("id")
    public void setID(int tid) {
        this.id = tid;
    }
  /*  @JsonProperty("ticker_id")
    public int getTicker_ID() {
        return ticker_id;
    }

    @JsonProperty("ticker_id")
    public void setTicker_ID(int tid) {
        this.ticker_id = tid;
    }*/
    @JsonProperty("timestamp")
    public Integer getTimestamp() {
        return timestamp;
    }

    @JsonProperty("timestamp")
    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    @JsonProperty("success")
    public Boolean getSuccess() {
        return success;
    }

    @JsonProperty("success")
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    @JsonProperty("error")
    public String getError() {
        return error;
    }

    @JsonProperty("error")
    public void setError(String error) {
        this.error = error;
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
        return "BitCoinPrice{" + "id=" + id + ", ticker=" + ticker + ", timestamp=" + timestamp + ", success=" + success + ", error=" + error + '}';
    }

}
