
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
"precipitaProb",
"tMin",
"tMax",
"predWindDir",
"idWeatherType",
"classWindSpeed",
"longitude",
"globalIdLocal",
"latitude"
})
public class Datum {

@JsonProperty("precipitaProb")
private String precipitaProb;
@JsonProperty("tMin")
private Integer tMin;
@JsonProperty("tMax")
private Integer tMax;
@JsonProperty("predWindDir")
private String predWindDir;
@JsonProperty("idWeatherType")
private Integer idWeatherType;
@JsonProperty("classWindSpeed")
private Integer classWindSpeed;
@JsonProperty("longitude")
private String longitude;
@JsonProperty("globalIdLocal")
private Integer globalIdLocal;
@JsonProperty("latitude")
private String latitude;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

/**
* No args constructor for use in serialization
*
*/
public Datum() {
}

/**
*
* @param tMax
* @param precipitaProb
* @param globalIdLocal
* @param latitude
* @param idWeatherType
* @param tMin
* @param predWindDir
* @param classWindSpeed
* @param longitude
*/
public Datum(String precipitaProb, Integer tMin, Integer tMax, String predWindDir, Integer idWeatherType, Integer classWindSpeed, String longitude, Integer globalIdLocal, String latitude) {
super();
this.precipitaProb = precipitaProb;
this.tMin = tMin;
this.tMax = tMax;
this.predWindDir = predWindDir;
this.idWeatherType = idWeatherType;
this.classWindSpeed = classWindSpeed;
this.longitude = longitude;
this.globalIdLocal = globalIdLocal;
this.latitude = latitude;
}

@JsonProperty("precipitaProb")
public String getPrecipitaProb() {
return precipitaProb;
}

@JsonProperty("precipitaProb")
public void setPrecipitaProb(String precipitaProb) {
this.precipitaProb = precipitaProb;
}

@JsonProperty("tMin")
public Integer getTMin() {
return tMin;
}

@JsonProperty("tMin")
public void setTMin(Integer tMin) {
this.tMin = tMin;
}

@JsonProperty("tMax")
public Integer getTMax() {
return tMax;
}

@JsonProperty("tMax")
public void setTMax(Integer tMax) {
this.tMax = tMax;
}

@JsonProperty("predWindDir")
public String getPredWindDir() {
return predWindDir;
}

@JsonProperty("predWindDir")
public void setPredWindDir(String predWindDir) {
this.predWindDir = predWindDir;
}

@JsonProperty("idWeatherType")
public Integer getIdWeatherType() {
return idWeatherType;
}

@JsonProperty("idWeatherType")
public void setIdWeatherType(Integer idWeatherType) {
this.idWeatherType = idWeatherType;
}

@JsonProperty("classWindSpeed")
public Integer getClassWindSpeed() {
return classWindSpeed;
}

@JsonProperty("classWindSpeed")
public void setClassWindSpeed(Integer classWindSpeed) {
this.classWindSpeed = classWindSpeed;
}

@JsonProperty("longitude")
public String getLongitude() {
return longitude;
}

@JsonProperty("longitude")
public void setLongitude(String longitude) {
this.longitude = longitude;
}

@JsonProperty("globalIdLocal")
public Integer getGlobalIdLocal() {
return globalIdLocal;
}

@JsonProperty("globalIdLocal")
public void setGlobalIdLocal(Integer globalIdLocal) {
this.globalIdLocal = globalIdLocal;
}

@JsonProperty("latitude")
public String getLatitude() {
return latitude;
}

@JsonProperty("latitude")
public void setLatitude(String latitude) {
this.latitude = latitude;
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