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
"owner",
"country",
"forecastDate",
"data",
"dataUpdate"
})
public class DailyForecast {

@JsonProperty("owner")
private String owner;
@JsonProperty("country")
private String country;
@JsonProperty("forecastDate")
private String forecastDate;
@JsonProperty("data")
private List<Datum> data = null;
@JsonProperty("dataUpdate")
private String dataUpdate;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

/**
* No args constructor for use in serialization
*
*/
public DailyForecast() {
}

/**
*
* @param owner
* @param country
* @param data
* @param dataUpdate
* @param forecastDate
*/
public DailyForecast(String owner, String country, String forecastDate, List<Datum> data, String dataUpdate) {
super();
this.owner = owner;
this.country = country;
this.forecastDate = forecastDate;
this.data = data;
this.dataUpdate = dataUpdate;
}

@JsonProperty("owner")
public String getOwner() {
return owner;
}

@JsonProperty("owner")
public void setOwner(String owner) {
this.owner = owner;
}

@JsonProperty("country")
public String getCountry() {
return country;
}

@JsonProperty("country")
public void setCountry(String country) {
this.country = country;
}

@JsonProperty("forecastDate")
public String getForecastDate() {
return forecastDate;
}

@JsonProperty("forecastDate")
public void setForecastDate(String forecastDate) {
this.forecastDate = forecastDate;
}

@JsonProperty("data")
public List<Datum> getData() {
return data;
}

@JsonProperty("data")
public void setData(List<Datum> data) {
this.data = data;
}

@JsonProperty("dataUpdate")
public String getDataUpdate() {
return dataUpdate;
}

@JsonProperty("dataUpdate")
public void setDataUpdate(String dataUpdate) {
this.dataUpdate = dataUpdate;
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

