package com.example.demo;

import java.util.HashMap;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "base",
    "target",
    "price",
    "volume",
    "bcp_change",
    "markets"
})

@Entity
public class Ticker {
    
    @Id
    @GeneratedValue
    @JsonProperty("id")
    private int id ;
    @JsonProperty("base")
    private String base;
    @JsonProperty("target") //EUR USD
    private String target;
    @JsonProperty("price")
    private String price;
    @JsonProperty("volume")
    private String volume;
    @JsonProperty("bcp_change")
    private String bcp_change;
    @OneToMany
    @JsonProperty("markets")
    private List<Market> markets = null;
    @Transient
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     *
     */
    public Ticker() {
    }

    /**
     * @param id
     * @param volume
     * @param markets
     * @param price
     * @param change
     * @param base
     * @param target
     */
    public Ticker(int id, String base, String target, String price, String volume, String change, List<Market> markets) {
        super();
        
        this.id = id;
        this.base = base;
        this.target = target;
        this.price = price;
        this.volume = volume;
        this.bcp_change = change;
        this.markets = markets;
    }

    @JsonProperty("id")
    public void setID(int id) {
        this.id =  id;
    }
    @JsonProperty("base")
    public String getBase() {
        return base;
    }

    @JsonProperty("base")
    public void setBase(String base) {
        this.base = base;
    }

    @JsonProperty("target")
    public String getTarget() {
        return target;
    }

    @JsonProperty("target")
    public void setTarget(String target) {
        this.target = target;
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
        return volume;
    }
    @JsonProperty("id")
    public int getID() {
        return id;
    }
    @JsonProperty("volume")
    public void setVolume(String volume) {
        this.volume = volume;
    }

    @JsonProperty("change")
    public String getChange() {
        return bcp_change;
    }

    @JsonProperty("change")
    public void setChange(String change) {
        this.bcp_change = change;
    }

    @JsonProperty("markets")
    public List<Market> getMarkets() {
        return markets;
    }

    @JsonProperty("markets")
    public void setMarkets(List<Market> markets) {
        this.markets = markets;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
