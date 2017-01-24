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
"duration",
"flight",
"id",
"cabin",
"bookingCode",
"bookingCodeCount",
"marriedSegmentGroup",
"subjectToGovernmentApproval",
"leg",
"connectionDuration"
})
public class Segment {

@JsonProperty("kind")
private String kind;
@JsonProperty("duration")
private Integer duration;
@JsonProperty("flight")
private Flight flight;
@JsonProperty("id")
private String id;
@JsonProperty("cabin")
private String cabin;
@JsonProperty("bookingCode")
private String bookingCode;
@JsonProperty("bookingCodeCount")
private Integer bookingCodeCount;
@JsonProperty("marriedSegmentGroup")
private String marriedSegmentGroup;
@JsonProperty("subjectToGovernmentApproval")
private Boolean subjectToGovernmentApproval;
@JsonProperty("leg")
private List<Leg> leg = null;
@JsonProperty("connectionDuration")
private Integer connectionDuration;

@JsonProperty("kind")
public String getKind() {
return kind;
}

@JsonProperty("kind")
public void setKind(String kind) {
this.kind = kind;
}

@JsonProperty("duration")
public Integer getDuration() {
return duration;
}

@JsonProperty("duration")
public void setDuration(Integer duration) {
this.duration = duration;
}

@JsonProperty("flight")
public Flight getFlight() {
return flight;
}

@JsonProperty("flight")
public void setFlight(Flight flight) {
this.flight = flight;
}

@JsonProperty("id")
public String getId() {
return id;
}

@JsonProperty("id")
public void setId(String id) {
this.id = id;
}

@JsonProperty("cabin")
public String getCabin() {
return cabin;
}

@JsonProperty("cabin")
public void setCabin(String cabin) {
this.cabin = cabin;
}

@JsonProperty("bookingCode")
public String getBookingCode() {
return bookingCode;
}

@JsonProperty("bookingCode")
public void setBookingCode(String bookingCode) {
this.bookingCode = bookingCode;
}

@JsonProperty("bookingCodeCount")
public Integer getBookingCodeCount() {
return bookingCodeCount;
}

@JsonProperty("bookingCodeCount")
public void setBookingCodeCount(Integer bookingCodeCount) {
this.bookingCodeCount = bookingCodeCount;
}

@JsonProperty("marriedSegmentGroup")
public String getMarriedSegmentGroup() {
return marriedSegmentGroup;
}

@JsonProperty("marriedSegmentGroup")
public void setMarriedSegmentGroup(String marriedSegmentGroup) {
this.marriedSegmentGroup = marriedSegmentGroup;
}

@JsonProperty("subjectToGovernmentApproval")
public Boolean getSubjectToGovernmentApproval() {
return subjectToGovernmentApproval;
}

@JsonProperty("subjectToGovernmentApproval")
public void setSubjectToGovernmentApproval(Boolean subjectToGovernmentApproval) {
this.subjectToGovernmentApproval = subjectToGovernmentApproval;
}

@JsonProperty("leg")
public List<Leg> getLeg() {
return leg;
}

@JsonProperty("leg")
public void setLeg(List<Leg> leg) {
this.leg = leg;
}

@JsonProperty("connectionDuration")
public Integer getConnectionDuration() {
return connectionDuration;
}

@JsonProperty("connectionDuration")
public void setConnectionDuration(Integer connectionDuration) {
this.connectionDuration = connectionDuration;
}

@Override
public String toString() {
return ToStringBuilder.reflectionToString(this);
}

@Override
public int hashCode() {
return new HashCodeBuilder().append(kind).append(duration).append(flight).append(id).append(cabin).append(bookingCode).append(bookingCodeCount).append(marriedSegmentGroup).append(subjectToGovernmentApproval).append(leg).append(connectionDuration).toHashCode();
}

@Override
public boolean equals(Object other) {
if (other == this) {
return true;
}
if ((other instanceof Segment) == false) {
return false;
}
Segment rhs = ((Segment) other);
return new EqualsBuilder().append(kind, rhs.kind).append(duration, rhs.duration).append(flight, rhs.flight).append(id, rhs.id).append(cabin, rhs.cabin).append(bookingCode, rhs.bookingCode).append(bookingCodeCount, rhs.bookingCodeCount).append(marriedSegmentGroup, rhs.marriedSegmentGroup).append(subjectToGovernmentApproval, rhs.subjectToGovernmentApproval).append(leg, rhs.leg).append(connectionDuration, rhs.connectionDuration).isEquals();
}

}
