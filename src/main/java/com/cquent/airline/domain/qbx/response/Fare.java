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
"id",
"carrier",
"origin",
"destination",
"basisCode",
"private"
})
public class Fare {

@JsonProperty("kind")
private String kind;
@JsonProperty("id")
private String id;
@JsonProperty("carrier")
private String carrier;
@JsonProperty("origin")
private String origin;
@JsonProperty("destination")
private String destination;
@JsonProperty("basisCode")
private String basisCode;
@JsonProperty("private")
private Boolean _private;

@JsonProperty("kind")
public String getKind() {
return kind;
}

@JsonProperty("kind")
public void setKind(String kind) {
this.kind = kind;
}

@JsonProperty("id")
public String getId() {
return id;
}

@JsonProperty("id")
public void setId(String id) {
this.id = id;
}

@JsonProperty("carrier")
public String getCarrier() {
return carrier;
}

@JsonProperty("carrier")
public void setCarrier(String carrier) {
this.carrier = carrier;
}

@JsonProperty("origin")
public String getOrigin() {
return origin;
}

@JsonProperty("origin")
public void setOrigin(String origin) {
this.origin = origin;
}

@JsonProperty("destination")
public String getDestination() {
return destination;
}

@JsonProperty("destination")
public void setDestination(String destination) {
this.destination = destination;
}

@JsonProperty("basisCode")
public String getBasisCode() {
return basisCode;
}

@JsonProperty("basisCode")
public void setBasisCode(String basisCode) {
this.basisCode = basisCode;
}

@JsonProperty("private")
public Boolean getPrivate() {
return _private;
}

@JsonProperty("private")
public void setPrivate(Boolean _private) {
this._private = _private;
}

@Override
public String toString() {
return ToStringBuilder.reflectionToString(this);
}

@Override
public int hashCode() {
return new HashCodeBuilder().append(kind).append(id).append(carrier).append(origin).append(destination).append(basisCode).append(_private).toHashCode();
}

@Override
public boolean equals(Object other) {
if (other == this) {
return true;
}
if ((other instanceof Fare) == false) {
return false;
}
Fare rhs = ((Fare) other);
return new EqualsBuilder().append(kind, rhs.kind).append(id, rhs.id).append(carrier, rhs.carrier).append(origin, rhs.origin).append(destination, rhs.destination).append(basisCode, rhs.basisCode).append(_private, rhs._private).isEquals();
}

}
