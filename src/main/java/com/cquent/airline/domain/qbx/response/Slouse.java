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
"segment"
})
public class Slouse {

@JsonProperty("kind")
private String kind;
@JsonProperty("duration")
private Integer duration;
@JsonProperty("segment")
private List<Segment> segment = null;

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

@JsonProperty("segment")
public List<Segment> getSegment() {
return segment;
}

@JsonProperty("segment")
public void setSegment(List<Segment> segment) {
this.segment = segment;
}

@Override
public String toString() {
return ToStringBuilder.reflectionToString(this);
}

@Override
public int hashCode() {
return new HashCodeBuilder().append(kind).append(duration).append(segment).toHashCode();
}

@Override
public boolean equals(Object other) {
if (other == this) {
return true;
}
if ((other instanceof Slouse) == false) {
return false;
}
Slouse rhs = ((Slouse) other);
return new EqualsBuilder().append(kind, rhs.kind).append(duration, rhs.duration).append(segment, rhs.segment).isEquals();
}

}
