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
"lang",
"currency",
"time",
"id",
"server",
"pid",
"key",
"params",
"version",
"method",
"client"
})
public class Request {

@JsonProperty("lang")
private String lang;
@JsonProperty("currency")
private String currency;
@JsonProperty("time")
private Integer time;
@JsonProperty("id")
private Long id;
@JsonProperty("server")
private String server;
@JsonProperty("pid")
private Integer pid;
@JsonProperty("key")
private Key key;
@JsonProperty("params")
private Params params;
@JsonProperty("version")
private Integer version;
@JsonProperty("method")
private String method;
@JsonProperty("client")
private Client client;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("lang")
public String getLang() {
return lang;
}

@JsonProperty("lang")
public void setLang(String lang) {
this.lang = lang;
}

@JsonProperty("currency")
public String getCurrency() {
return currency;
}

@JsonProperty("currency")
public void setCurrency(String currency) {
this.currency = currency;
}

@JsonProperty("time")
public Integer getTime() {
return time;
}

@JsonProperty("time")
public void setTime(Integer time) {
this.time = time;
}

@JsonProperty("id")
public Long getId() {
return id;
}

@JsonProperty("id")
public void setId(Long id) {
this.id = id;
}

@JsonProperty("server")
public String getServer() {
return server;
}

@JsonProperty("server")
public void setServer(String server) {
this.server = server;
}

@JsonProperty("pid")
public Integer getPid() {
return pid;
}

@JsonProperty("pid")
public void setPid(Integer pid) {
this.pid = pid;
}

@JsonProperty("key")
public Key getKey() {
return key;
}

@JsonProperty("key")
public void setKey(Key key) {
this.key = key;
}

@JsonProperty("params")
public Params getParams() {
return params;
}

@JsonProperty("params")
public void setParams(Params params) {
this.params = params;
}

@JsonProperty("version")
public Integer getVersion() {
return version;
}

@JsonProperty("version")
public void setVersion(Integer version) {
this.version = version;
}

@JsonProperty("method")
public String getMethod() {
return method;
}

@JsonProperty("method")
public void setMethod(String method) {
this.method = method;
}

@JsonProperty("client")
public Client getClient() {
return client;
}

@JsonProperty("client")
public void setClient(Client client) {
this.client = client;
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
return new HashCodeBuilder().append(lang).append(currency).append(time).append(id).append(server).append(pid).append(key).append(params).append(version).append(method).append(client).append(additionalProperties).toHashCode();
}

@Override
public boolean equals(Object other) {
if (other == this) {
return true;
}
if ((other instanceof Request) == false) {
return false;
}
Request rhs = ((Request) other);
return new EqualsBuilder().append(lang, rhs.lang).append(currency, rhs.currency).append(time, rhs.time).append(id, rhs.id).append(server, rhs.server).append(pid, rhs.pid).append(key, rhs.key).append(params, rhs.params).append(version, rhs.version).append(method, rhs.method).append(client, rhs.client).append(additionalProperties, rhs.additionalProperties).isEquals();
}

}
