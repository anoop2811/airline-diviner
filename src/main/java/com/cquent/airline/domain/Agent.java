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
"browser",
"version",
"os",
"platform"
})
public class Agent {

@JsonProperty("browser")
private String browser;
@JsonProperty("version")
private String version;
@JsonProperty("os")
private String os;
@JsonProperty("platform")
private String platform;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("browser")
public String getBrowser() {
return browser;
}

@JsonProperty("browser")
public void setBrowser(String browser) {
this.browser = browser;
}

@JsonProperty("version")
public String getVersion() {
return version;
}

@JsonProperty("version")
public void setVersion(String version) {
this.version = version;
}

@JsonProperty("os")
public String getOs() {
return os;
}

@JsonProperty("os")
public void setOs(String os) {
this.os = os;
}

@JsonProperty("platform")
public String getPlatform() {
return platform;
}

@JsonProperty("platform")
public void setPlatform(String platform) {
this.platform = platform;
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
return new HashCodeBuilder().append(browser).append(version).append(os).append(platform).append(additionalProperties).toHashCode();
}

@Override
public boolean equals(Object other) {
if (other == this) {
return true;
}
if ((other instanceof Agent) == false) {
return false;
}
Agent rhs = ((Agent) other);
return new EqualsBuilder().append(browser, rhs.browser).append(version, rhs.version).append(os, rhs.os).append(platform, rhs.platform).append(additionalProperties, rhs.additionalProperties).isEquals();
}

}
