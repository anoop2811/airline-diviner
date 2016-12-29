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
"request",
"response"
})
public class IATAResponse {

@JsonProperty("request")
private Request request;
@JsonProperty("response")
private List<AirportResponse> response = null;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("request")
public Request getRequest() {
return request;
}

@JsonProperty("request")
public void setRequest(Request request) {
this.request = request;
}

@JsonProperty("response")
public List<AirportResponse> getResponse() {
return response;
}

@JsonProperty("response")
public void setResponse(List<AirportResponse> response) {
this.response = response;
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
return new HashCodeBuilder().append(request).append(response).append(additionalProperties).toHashCode();
}

@Override
public boolean equals(Object other) {
if (other == this) {
return true;
}
if ((other instanceof IATAResponse) == false) {
return false;
}
IATAResponse rhs = ((IATAResponse) other);
return new EqualsBuilder().append(request, rhs.request).append(response, rhs.response).append(additionalProperties, rhs.additionalProperties).isEquals();
}

}
