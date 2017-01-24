package com.cquent.airline.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"code",
"icao",
"name",
"alias",
"callsign",
"country_code",
"country_name",
"account_code",
"age",
"founded",
"phone",
"website",
"carryon_weight",
"carryon_size",
"free_luggage_weight",
"on_time_percent",
"late_percent",
"very_late_percent",
"excessive_percent",
"canceled_percent",
"diverted_percent",
"avg_delay",
"observations",
"min_delay",
"max_delay",
"status",
"type",
"city_code",
"facebook",
"twitter",
"wikipedia"
})
public class AirlineCodeResponse {

@JsonProperty("code")
private String code;
@JsonProperty("icao")
private String icao;
@JsonProperty("name")
private String name;
@JsonProperty("alias")
private String alias;
@JsonProperty("callsign")
private String callsign;
@JsonProperty("country_code")
private String countryCode;
@JsonProperty("country_name")
private String countryName;
@JsonProperty("account_code")
private String accountCode;
@JsonProperty("age")
private Double age;
@JsonProperty("founded")
private Integer founded;
@JsonProperty("phone")
private String phone;
@JsonProperty("website")
private String website;
@JsonProperty("carryon_weight")
private Integer carryonWeight;
@JsonProperty("carryon_size")
private String carryonSize;
@JsonProperty("free_luggage_weight")
private Integer freeLuggageWeight;
@JsonProperty("on_time_percent")
private Integer onTimePercent;
@JsonProperty("late_percent")
private Integer latePercent;
@JsonProperty("very_late_percent")
private Integer veryLatePercent;
@JsonProperty("excessive_percent")
private Integer excessivePercent;
@JsonProperty("canceled_percent")
private Integer canceledPercent;
@JsonProperty("diverted_percent")
private Integer divertedPercent;
@JsonProperty("avg_delay")
private Object avgDelay;
@JsonProperty("observations")
private Integer observations;
@JsonProperty("min_delay")
private Integer minDelay;
@JsonProperty("max_delay")
private Integer maxDelay;
@JsonProperty("status")
private String status;
@JsonProperty("type")
private String type;
@JsonProperty("city_code")
private String cityCode;
@JsonProperty("facebook")
private String facebook;
@JsonProperty("twitter")
private String twitter;
@JsonProperty("wikipedia")
private String wikipedia;

@JsonProperty("code")
public String getCode() {
return code;
}

@JsonProperty("code")
public void setCode(String code) {
this.code = code;
}

@JsonProperty("icao")
public String getIcao() {
return icao;
}

@JsonProperty("icao")
public void setIcao(String icao) {
this.icao = icao;
}

@JsonProperty("name")
public String getName() {
return name;
}

@JsonProperty("name")
public void setName(String name) {
this.name = name;
}

@JsonProperty("alias")
public String getAlias() {
return alias;
}

@JsonProperty("alias")
public void setAlias(String alias) {
this.alias = alias;
}

@JsonProperty("callsign")
public String getCallsign() {
return callsign;
}

@JsonProperty("callsign")
public void setCallsign(String callsign) {
this.callsign = callsign;
}

@JsonProperty("country_code")
public String getCountryCode() {
return countryCode;
}

@JsonProperty("country_code")
public void setCountryCode(String countryCode) {
this.countryCode = countryCode;
}

@JsonProperty("country_name")
public String getCountryName() {
return countryName;
}

@JsonProperty("country_name")
public void setCountryName(String countryName) {
this.countryName = countryName;
}

@JsonProperty("account_code")
public String getAccountCode() {
return accountCode;
}

@JsonProperty("account_code")
public void setAccountCode(String accountCode) {
this.accountCode = accountCode;
}

@JsonProperty("age")
public Double getAge() {
return age;
}

@JsonProperty("age")
public void setAge(Double age) {
this.age = age;
}

@JsonProperty("founded")
public Integer getFounded() {
return founded;
}

@JsonProperty("founded")
public void setFounded(Integer founded) {
this.founded = founded;
}

@JsonProperty("phone")
public String getPhone() {
return phone;
}

@JsonProperty("phone")
public void setPhone(String phone) {
this.phone = phone;
}

@JsonProperty("website")
public String getWebsite() {
return website;
}

@JsonProperty("website")
public void setWebsite(String website) {
this.website = website;
}

@JsonProperty("carryon_weight")
public Integer getCarryonWeight() {
return carryonWeight;
}

@JsonProperty("carryon_weight")
public void setCarryonWeight(Integer carryonWeight) {
this.carryonWeight = carryonWeight;
}

@JsonProperty("carryon_size")
public String getCarryonSize() {
return carryonSize;
}

@JsonProperty("carryon_size")
public void setCarryonSize(String carryonSize) {
this.carryonSize = carryonSize;
}

@JsonProperty("free_luggage_weight")
public Integer getFreeLuggageWeight() {
return freeLuggageWeight;
}

@JsonProperty("free_luggage_weight")
public void setFreeLuggageWeight(Integer freeLuggageWeight) {
this.freeLuggageWeight = freeLuggageWeight;
}

@JsonProperty("on_time_percent")
public Integer getOnTimePercent() {
return onTimePercent;
}

@JsonProperty("on_time_percent")
public void setOnTimePercent(Integer onTimePercent) {
this.onTimePercent = onTimePercent;
}

@JsonProperty("late_percent")
public Integer getLatePercent() {
return latePercent;
}

@JsonProperty("late_percent")
public void setLatePercent(Integer latePercent) {
this.latePercent = latePercent;
}

@JsonProperty("very_late_percent")
public Integer getVeryLatePercent() {
return veryLatePercent;
}

@JsonProperty("very_late_percent")
public void setVeryLatePercent(Integer veryLatePercent) {
this.veryLatePercent = veryLatePercent;
}

@JsonProperty("excessive_percent")
public Integer getExcessivePercent() {
return excessivePercent;
}

@JsonProperty("excessive_percent")
public void setExcessivePercent(Integer excessivePercent) {
this.excessivePercent = excessivePercent;
}

@JsonProperty("canceled_percent")
public Integer getCanceledPercent() {
return canceledPercent;
}

@JsonProperty("canceled_percent")
public void setCanceledPercent(Integer canceledPercent) {
this.canceledPercent = canceledPercent;
}

@JsonProperty("diverted_percent")
public Integer getDivertedPercent() {
return divertedPercent;
}

@JsonProperty("diverted_percent")
public void setDivertedPercent(Integer divertedPercent) {
this.divertedPercent = divertedPercent;
}

@JsonProperty("avg_delay")
public Object getAvgDelay() {
return avgDelay;
}

@JsonProperty("avg_delay")
public void setAvgDelay(Object avgDelay) {
this.avgDelay = avgDelay;
}

@JsonProperty("observations")
public Integer getObservations() {
return observations;
}

@JsonProperty("observations")
public void setObservations(Integer observations) {
this.observations = observations;
}

@JsonProperty("min_delay")
public Integer getMinDelay() {
return minDelay;
}

@JsonProperty("min_delay")
public void setMinDelay(Integer minDelay) {
this.minDelay = minDelay;
}

@JsonProperty("max_delay")
public Integer getMaxDelay() {
return maxDelay;
}

@JsonProperty("max_delay")
public void setMaxDelay(Integer maxDelay) {
this.maxDelay = maxDelay;
}

@JsonProperty("status")
public String getStatus() {
return status;
}

@JsonProperty("status")
public void setStatus(String status) {
this.status = status;
}

@JsonProperty("type")
public String getType() {
return type;
}

@JsonProperty("type")
public void setType(String type) {
this.type = type;
}

@JsonProperty("city_code")
public String getCityCode() {
return cityCode;
}

@JsonProperty("city_code")
public void setCityCode(String cityCode) {
this.cityCode = cityCode;
}

@JsonProperty("facebook")
public String getFacebook() {
return facebook;
}

@JsonProperty("facebook")
public void setFacebook(String facebook) {
this.facebook = facebook;
}

@JsonProperty("twitter")
public String getTwitter() {
return twitter;
}

@JsonProperty("twitter")
public void setTwitter(String twitter) {
this.twitter = twitter;
}

@JsonProperty("wikipedia")
public String getWikipedia() {
return wikipedia;
}

@JsonProperty("wikipedia")
public void setWikipedia(String wikipedia) {
this.wikipedia = wikipedia;
}

}