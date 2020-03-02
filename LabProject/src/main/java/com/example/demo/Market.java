
package com.example.demo;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"market",
"price",
"volume"
})
public class Market {

@JsonProperty("market")
private String market;
@JsonProperty("price")
private String price;
@JsonProperty("volume")
private Float volume;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

/**
* No args constructor for use in serialization
*
*/
public Market() {
}

/**
*
* @param market
* @param volume
* @param price
*/
public Market(String market, String price, Float volume) {
super();
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
public Float getVolume() {
return volume;
}

@JsonProperty("volume")
public void setVolume(Float volume) {
this.volume = volume;
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