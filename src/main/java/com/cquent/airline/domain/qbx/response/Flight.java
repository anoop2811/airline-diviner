package com.cquent.airline.domain.qbx.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"carrier",
"number"
})
public class Flight {

@JsonProperty("carrier")
private String carrier;
@JsonProperty("number")
private String number;

@JsonProperty("carrier")
public String getCarrier() {
return carrier;
}

@JsonProperty("carrier")
public void setCarrier(String carrier) {
this.carrier = carrier;
}

@JsonProperty("number")
public String getNumber() {
return number;
}

@JsonProperty("number")
public void setNumber(String number) {
this.number = number;
}

@Override
public String toString() {
return ToStringBuilder.reflectionToString(this);
}

@Override
public int hashCode() {
return new HashCodeBuilder().append(carrier).append(number).toHashCode();
}

@Override
public boolean equals(Object other) {
if (other == this) {
return true;
}
if ((other instanceof Flight) == false) {
return false;
}
Flight rhs = ((Flight) other);
return new EqualsBuilder().append(carrier, rhs.carrier).append(number, rhs.number).isEquals();
}

}
