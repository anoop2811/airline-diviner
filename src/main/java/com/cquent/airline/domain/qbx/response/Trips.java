package com.cquent.airline.domain.qbx.response;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"kind",
"requestId",
"data",
"tripOption"
})
public class Trips {

@JsonProperty("kind")
private String kind;
@JsonProperty("requestId")
private String requestId;
@JsonProperty("data")
private Data data;
@JsonProperty("tripOption")
private List<TripOption> tripOption = null;

@JsonProperty("kind")
public String getKind() {
return kind;
}

@JsonProperty("kind")
public void setKind(String kind) {
this.kind = kind;
}

@JsonProperty("requestId")
public String getRequestId() {
return requestId;
}

@JsonProperty("requestId")
public void setRequestId(String requestId) {
this.requestId = requestId;
}

@JsonProperty("data")
public Data getData() {
return data;
}

@JsonProperty("data")
public void setData(Data data) {
this.data = data;
}

@JsonProperty("tripOption")
public List<TripOption> getTripOption() {
return tripOption;
}

@JsonProperty("tripOption")
public void setTripOption(List<TripOption> tripOption) {
this.tripOption = tripOption;
}

@Override
public String toString() {
return ToStringBuilder.reflectionToString(this);
}

@Override
public int hashCode() {
return new HashCodeBuilder().append(kind).append(requestId).append(data).append(tripOption).toHashCode();
}

@Override
public boolean equals(Object other) {
if (other == this) {
return true;
}
if ((other instanceof Trips) == false) {
return false;
}
Trips rhs = ((Trips) other);
return new EqualsBuilder().append(kind, rhs.kind).append(requestId, rhs.requestId).append(data, rhs.data).append(tripOption, rhs.tripOption).isEquals();
}

}


