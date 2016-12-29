package com.cquent.airline.domain;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"country_code",
"country",
"city",
"lat",
"lng",
"ip",
"device",
"agent"
})
public class Client {

@JsonProperty("country_code")
private String countryCode;
@JsonProperty("country")
private String country;
@JsonProperty("city")
private String city;
@JsonProperty("lat")
private Double lat;
@JsonProperty("lng")
private Double lng;
@JsonProperty("ip")
private String ip;
@JsonProperty("device")
private Device device;
@JsonProperty("agent")
private Agent agent;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("country_code")
public String getCountryCode() {
return countryCode;
}

@JsonProperty("country_code")
public void setCountryCode(String countryCode) {
this.countryCode = countryCode;
}

@JsonProperty("country")
public String getCountry() {
return country;
}

@JsonProperty("country")
public void setCountry(String country) {
this.country = country;
}

@JsonProperty("city")
public String getCity() {
return city;
}

@JsonProperty("city")
public void setCity(String city) {
this.city = city;
}

@JsonProperty("lat")
public Double getLat() {
return lat;
}

@JsonProperty("lat")
public void setLat(Double lat) {
this.lat = lat;
}

@JsonProperty("lng")
public Double getLng() {
return lng;
}

@JsonProperty("lng")
public void setLng(Double lng) {
this.lng = lng;
}

@JsonProperty("ip")
public String getIp() {
return ip;
}

@JsonProperty("ip")
public void setIp(String ip) {
this.ip = ip;
}

@JsonProperty("device")
public Device getDevice() {
return device;
}

@JsonProperty("device")
public void setDevice(Device device) {
this.device = device;
}

@JsonProperty("agent")
public Agent getAgent() {
return agent;
}

@JsonProperty("agent")
public void setAgent(Agent agent) {
this.agent = agent;
}

@Override
public String toString() {
return ToStringBuilder.reflectionToString(this);
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
public int hashCode() {
return new HashCodeBuilder().append(countryCode).append(country).append(city).append(lat).append(lng).append(ip).append(device).append(agent).append(additionalProperties).toHashCode();
}

@Override
public boolean equals(Object other) {
if (other == this) {
return true;
}
if ((other instanceof Client) == false) {
return false;
}
Client rhs = ((Client) other);
return new EqualsBuilder().append(countryCode, rhs.countryCode).append(country, rhs.country).append(city, rhs.city).append(lat, rhs.lat).append(lng, rhs.lng).append(ip, rhs.ip).append(device, rhs.device).append(agent, rhs.agent).append(additionalProperties, rhs.additionalProperties).isEquals();
}

}
