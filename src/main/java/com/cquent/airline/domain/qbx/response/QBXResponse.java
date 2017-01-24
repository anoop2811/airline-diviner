package com.cquent.airline.domain.qbx.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"kind",
"trips"
})
public class QBXResponse {

@JsonProperty("kind")
private String kind;
@JsonProperty("trips")
private Trips trips;

@JsonProperty("kind")
public String getKind() {
return kind;
}

@JsonProperty("kind")
public void setKind(String kind) {
this.kind = kind;
}

@JsonProperty("trips")
public Trips getTrips() {
return trips;
}

@JsonProperty("trips")
public void setTrips(Trips trips) {
this.trips = trips;
}

@Override
public String toString() {
return ToStringBuilder.reflectionToString(this);
}

@Override
public int hashCode() {
return new HashCodeBuilder().append(kind).append(trips).toHashCode();
}

@Override
public boolean equals(Object other) {
if (other == this) {
return true;
}
if ((other instanceof QBXResponse) == false) {
return false;
}
QBXResponse rhs = ((QBXResponse) other);
return new EqualsBuilder().append(kind, rhs.kind).append(trips, rhs.trips).isEquals();
}

}
