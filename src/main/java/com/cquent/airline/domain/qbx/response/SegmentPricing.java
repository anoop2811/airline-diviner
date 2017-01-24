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
"fareId",
"segmentId",
"freeBaggageOption"
})
public class SegmentPricing {

@JsonProperty("kind")
private String kind;
@JsonProperty("fareId")
private String fareId;
@JsonProperty("segmentId")
private String segmentId;
@JsonProperty("freeBaggageOption")
private List<FreeBaggageOption> freeBaggageOption = null;

@JsonProperty("kind")
public String getKind() {
return kind;
}

@JsonProperty("kind")
public void setKind(String kind) {
this.kind = kind;
}

@JsonProperty("fareId")
public String getFareId() {
return fareId;
}

@JsonProperty("fareId")
public void setFareId(String fareId) {
this.fareId = fareId;
}

@JsonProperty("segmentId")
public String getSegmentId() {
return segmentId;
}

@JsonProperty("segmentId")
public void setSegmentId(String segmentId) {
this.segmentId = segmentId;
}

@JsonProperty("freeBaggageOption")
public List<FreeBaggageOption> getFreeBaggageOption() {
return freeBaggageOption;
}

@JsonProperty("freeBaggageOption")
public void setFreeBaggageOption(List<FreeBaggageOption> freeBaggageOption) {
this.freeBaggageOption = freeBaggageOption;
}

@Override
public String toString() {
return ToStringBuilder.reflectionToString(this);
}

@Override
public int hashCode() {
return new HashCodeBuilder().append(kind).append(fareId).append(segmentId).append(freeBaggageOption).toHashCode();
}

@Override
public boolean equals(Object other) {
if (other == this) {
return true;
}
if ((other instanceof SegmentPricing) == false) {
return false;
}
SegmentPricing rhs = ((SegmentPricing) other);
return new EqualsBuilder().append(kind, rhs.kind).append(fareId, rhs.fareId).append(segmentId, rhs.segmentId).append(freeBaggageOption, rhs.freeBaggageOption).isEquals();
}

}
