package com.cquent.airline.domain;

import java.util.HashMap;
import java.util.List;
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
"id",
"api_key",
"type",
"expired",
"registered",
"limits_by_hour",
"limits_by_minute",
"demo_methods",
"usage_by_hour",
"usage_by_minute"
})
public class Key {

@JsonProperty("id")
private Integer id;
@JsonProperty("api_key")
private String apiKey;
@JsonProperty("type")
private String type;
@JsonProperty("expired")
private Object expired;
@JsonProperty("registered")
private String registered;
@JsonProperty("limits_by_hour")
private Integer limitsByHour;
@JsonProperty("limits_by_minute")
private Integer limitsByMinute;
@JsonProperty("demo_methods")
private List<Object> demoMethods = null;
@JsonProperty("usage_by_hour")
private Integer usageByHour;
@JsonProperty("usage_by_minute")
private Integer usageByMinute;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("id")
public Integer getId() {
return id;
}

@JsonProperty("id")
public void setId(Integer id) {
this.id = id;
}

@JsonProperty("api_key")
public String getApiKey() {
return apiKey;
}

@JsonProperty("api_key")
public void setApiKey(String apiKey) {
this.apiKey = apiKey;
}

@JsonProperty("type")
public String getType() {
return type;
}

@JsonProperty("type")
public void setType(String type) {
this.type = type;
}

@JsonProperty("expired")
public Object getExpired() {
return expired;
}

@JsonProperty("expired")
public void setExpired(Object expired) {
this.expired = expired;
}

@JsonProperty("registered")
public String getRegistered() {
return registered;
}

@JsonProperty("registered")
public void setRegistered(String registered) {
this.registered = registered;
}

@JsonProperty("limits_by_hour")
public Integer getLimitsByHour() {
return limitsByHour;
}

@JsonProperty("limits_by_hour")
public void setLimitsByHour(Integer limitsByHour) {
this.limitsByHour = limitsByHour;
}

@JsonProperty("limits_by_minute")
public Integer getLimitsByMinute() {
return limitsByMinute;
}

@JsonProperty("limits_by_minute")
public void setLimitsByMinute(Integer limitsByMinute) {
this.limitsByMinute = limitsByMinute;
}

@JsonProperty("demo_methods")
public List<Object> getDemoMethods() {
return demoMethods;
}

@JsonProperty("demo_methods")
public void setDemoMethods(List<Object> demoMethods) {
this.demoMethods = demoMethods;
}

@JsonProperty("usage_by_hour")
public Integer getUsageByHour() {
return usageByHour;
}

@JsonProperty("usage_by_hour")
public void setUsageByHour(Integer usageByHour) {
this.usageByHour = usageByHour;
}

@JsonProperty("usage_by_minute")
public Integer getUsageByMinute() {
return usageByMinute;
}

@JsonProperty("usage_by_minute")
public void setUsageByMinute(Integer usageByMinute) {
this.usageByMinute = usageByMinute;
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
return new HashCodeBuilder().append(id).append(apiKey).append(type).append(expired).append(registered).append(limitsByHour).append(limitsByMinute).append(demoMethods).append(usageByHour).append(usageByMinute).append(additionalProperties).toHashCode();
}

@Override
public boolean equals(Object other) {
if (other == this) {
return true;
}
if ((other instanceof Key) == false) {
return false;
}
Key rhs = ((Key) other);
return new EqualsBuilder().append(id, rhs.id).append(apiKey, rhs.apiKey).append(type, rhs.type).append(expired, rhs.expired).append(registered, rhs.registered).append(limitsByHour, rhs.limitsByHour).append(limitsByMinute, rhs.limitsByMinute).append(demoMethods, rhs.demoMethods).append(usageByHour, rhs.usageByHour).append(usageByMinute, rhs.usageByMinute).append(additionalProperties, rhs.additionalProperties).isEquals();
}

}
