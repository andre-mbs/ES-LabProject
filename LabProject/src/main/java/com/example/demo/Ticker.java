
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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"base",
"target",
"price",
"volume",
"change",
"markets"
})
public class Ticker {

@JsonProperty("base")
private String base;
@JsonProperty("target") //EUR USD
private String target;
@JsonProperty("price")
private String price;
@JsonProperty("volume")
private String volume;
@JsonProperty("change")
private String change;
@JsonProperty("markets")
private List<Market> markets = null;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

/**
* No args constructor for use in serialization
*
*/
public Ticker() {
}

/**
*
* @param volume
* @param markets
* @param price
* @param change
* @param base
* @param target
*/
public Ticker(String base, String target, String price, String volume, String change, List<Market> markets) {
super();
this.base = base;
this.target = target;
this.price = price;
this.volume = volume;
this.change = change;
this.markets = markets;
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

@JsonProperty("volume")
public void setVolume(String volume) {
this.volume = volume;
}

@JsonProperty("change")
public String getChange() {
return change;
}

@JsonProperty("change")
public void setChange(String change) {
this.change = change;
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