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
"aircraft",
"arrivalTime",
"departureTime",
"origin",
"destination",
"originTerminal",
"destinationTerminal",
"duration",
"operatingDisclosure",
"onTimePerformance",
"mileage",
"meal",
"secure",
"connectionDuration",
"changePlane"
})
public class Leg {

@JsonProperty("kind")
private String kind;
@JsonProperty("id")
private String id;
@JsonProperty("aircraft")
private String aircraft;
@JsonProperty("arrivalTime")
private String arrivalTime;
@JsonProperty("departureTime")
private String departureTime;
@JsonProperty("origin")
private String origin;
@JsonProperty("destination")
private String destination;
@JsonProperty("originTerminal")
private String originTerminal;
@JsonProperty("destinationTerminal")
private String destinationTerminal;
@JsonProperty("duration")
private Integer duration;
@JsonProperty("operatingDisclosure")
private String operatingDisclosure;
@JsonProperty("onTimePerformance")
private Integer onTimePerformance;
@JsonProperty("mileage")
private Integer mileage;
@JsonProperty("meal")
private String meal;
@JsonProperty("secure")
private Boolean secure;
@JsonProperty("connectionDuration")
private Integer connectionDuration;
@JsonProperty("changePlane")
private Boolean changePlane;

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

@JsonProperty("aircraft")
public String getAircraft() {
return aircraft;
}

@JsonProperty("aircraft")
public void setAircraft(String aircraft) {
this.aircraft = aircraft;
}

@JsonProperty("arrivalTime")
public String getArrivalTime() {
return arrivalTime;
}

@JsonProperty("arrivalTime")
public void setArrivalTime(String arrivalTime) {
this.arrivalTime = arrivalTime;
}

@JsonProperty("departureTime")
public String getDepartureTime() {
return departureTime;
}

@JsonProperty("departureTime")
public void setDepartureTime(String departureTime) {
this.departureTime = departureTime;
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

@JsonProperty("originTerminal")
public String getOriginTerminal() {
return originTerminal;
}

@JsonProperty("originTerminal")
public void setOriginTerminal(String originTerminal) {
this.originTerminal = originTerminal;
}

@JsonProperty("destinationTerminal")
public String getDestinationTerminal() {
return destinationTerminal;
}

@JsonProperty("destinationTerminal")
public void setDestinationTerminal(String destinationTerminal) {
this.destinationTerminal = destinationTerminal;
}

@JsonProperty("duration")
public Integer getDuration() {
return duration;
}

@JsonProperty("duration")
public void setDuration(Integer duration) {
this.duration = duration;
}

@JsonProperty("operatingDisclosure")
public String getOperatingDisclosure() {
return operatingDisclosure;
}

@JsonProperty("operatingDisclosure")
public void setOperatingDisclosure(String operatingDisclosure) {
this.operatingDisclosure = operatingDisclosure;
}

@JsonProperty("onTimePerformance")
public Integer getOnTimePerformance() {
return onTimePerformance;
}

@JsonProperty("onTimePerformance")
public void setOnTimePerformance(Integer onTimePerformance) {
this.onTimePerformance = onTimePerformance;
}

@JsonProperty("mileage")
public Integer getMileage() {
return mileage;
}

@JsonProperty("mileage")
public void setMileage(Integer mileage) {
this.mileage = mileage;
}

@JsonProperty("meal")
public String getMeal() {
return meal;
}

@JsonProperty("meal")
public void setMeal(String meal) {
this.meal = meal;
}

@JsonProperty("secure")
public Boolean getSecure() {
return secure;
}

@JsonProperty("secure")
public void setSecure(Boolean secure) {
this.secure = secure;
}

@JsonProperty("connectionDuration")
public Integer getConnectionDuration() {
return connectionDuration;
}

@JsonProperty("connectionDuration")
public void setConnectionDuration(Integer connectionDuration) {
this.connectionDuration = connectionDuration;
}

@JsonProperty("changePlane")
public Boolean getChangePlane() {
return changePlane;
}

@JsonProperty("changePlane")
public void setChangePlane(Boolean changePlane) {
this.changePlane = changePlane;
}

@Override
public String toString() {
return ToStringBuilder.reflectionToString(this);
}

@Override
public int hashCode() {
return new HashCodeBuilder().append(kind).append(id).append(aircraft).append(arrivalTime).append(departureTime).append(origin).append(destination).append(originTerminal).append(destinationTerminal).append(duration).append(operatingDisclosure).append(onTimePerformance).append(mileage).append(meal).append(secure).append(connectionDuration).append(changePlane).toHashCode();
}

@Override
public boolean equals(Object other) {
if (other == this) {
return true;
}
if ((other instanceof Leg) == false) {
return false;
}
Leg rhs = ((Leg) other);
return new EqualsBuilder().append(kind, rhs.kind).append(id, rhs.id).append(aircraft, rhs.aircraft).append(arrivalTime, rhs.arrivalTime).append(departureTime, rhs.departureTime).append(origin, rhs.origin).append(destination, rhs.destination).append(originTerminal, rhs.originTerminal).append(destinationTerminal, rhs.destinationTerminal).append(duration, rhs.duration).append(operatingDisclosure, rhs.operatingDisclosure).append(onTimePerformance, rhs.onTimePerformance).append(mileage, rhs.mileage).append(meal, rhs.meal).append(secure, rhs.secure).append(connectionDuration, rhs.connectionDuration).append(changePlane, rhs.changePlane).isEquals();
}

}
