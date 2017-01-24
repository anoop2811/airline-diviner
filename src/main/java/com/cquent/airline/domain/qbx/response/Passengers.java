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
"adultCount",
"childCount",
"infantInLapCount",
"infantInSeatCount",
"seniorCount"
})
public class Passengers {

@JsonProperty("kind")
private String kind;
@JsonProperty("adultCount")
private Integer adultCount;
@JsonProperty("childCount")
private Integer childCount;
@JsonProperty("infantInLapCount")
private Integer infantInLapCount;
@JsonProperty("infantInSeatCount")
private Integer infantInSeatCount;
@JsonProperty("seniorCount")
private Integer seniorCount;

@JsonProperty("kind")
public String getKind() {
return kind;
}

@JsonProperty("kind")
public void setKind(String kind) {
this.kind = kind;
}

@JsonProperty("adultCount")
public Integer getAdultCount() {
return adultCount;
}

@JsonProperty("adultCount")
public void setAdultCount(Integer adultCount) {
this.adultCount = adultCount;
}

@JsonProperty("childCount")
public Integer getChildCount() {
return childCount;
}

@JsonProperty("childCount")
public void setChildCount(Integer childCount) {
this.childCount = childCount;
}

@JsonProperty("infantInLapCount")
public Integer getInfantInLapCount() {
return infantInLapCount;
}

@JsonProperty("infantInLapCount")
public void setInfantInLapCount(Integer infantInLapCount) {
this.infantInLapCount = infantInLapCount;
}

@JsonProperty("infantInSeatCount")
public Integer getInfantInSeatCount() {
return infantInSeatCount;
}

@JsonProperty("infantInSeatCount")
public void setInfantInSeatCount(Integer infantInSeatCount) {
this.infantInSeatCount = infantInSeatCount;
}

@JsonProperty("seniorCount")
public Integer getSeniorCount() {
return seniorCount;
}

@JsonProperty("seniorCount")
public void setSeniorCount(Integer seniorCount) {
this.seniorCount = seniorCount;
}

@Override
public String toString() {
return ToStringBuilder.reflectionToString(this);
}

@Override
public int hashCode() {
return new HashCodeBuilder().append(kind).append(adultCount).append(childCount).append(infantInLapCount).append(infantInSeatCount).append(seniorCount).toHashCode();
}

@Override
public boolean equals(Object other) {
if (other == this) {
return true;
}
if ((other instanceof Passengers) == false) {
return false;
}
Passengers rhs = ((Passengers) other);
return new EqualsBuilder().append(kind, rhs.kind).append(adultCount, rhs.adultCount).append(childCount, rhs.childCount).append(infantInLapCount, rhs.infantInLapCount).append(infantInSeatCount, rhs.infantInSeatCount).append(seniorCount, rhs.seniorCount).isEquals();
}

}
