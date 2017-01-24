package com.cquent.airline.domain.qbx;

import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({
    "adultCount",
    "infantInLapCount",
    "infantInSeatCount",
    "childCount",
    "seniorCount"
})
public class Passengers {

    @JsonProperty("adultCount")
    private Integer adultCount;
    @JsonProperty("infantInLapCount")
    private Integer infantInLapCount;
    @JsonProperty("infantInSeatCount")
    private Integer infantInSeatCount;
    @JsonProperty("childCount")
    private Integer childCount;
    @JsonProperty("seniorCount")
    private Integer seniorCount;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("adultCount")
    public Integer getAdultCount() {
        return adultCount;
    }

    @JsonProperty("adultCount")
    public void setAdultCount(Integer adultCount) {
        this.adultCount = adultCount;
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

    @JsonProperty("childCount")
    public Integer getChildCount() {
        return childCount;
    }

    @JsonProperty("childCount")
    public void setChildCount(Integer childCount) {
        this.childCount = childCount;
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
        return new HashCodeBuilder().append(adultCount).append(infantInLapCount).append(infantInSeatCount).append(childCount).append(seniorCount).append(additionalProperties).toHashCode();
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
        return new EqualsBuilder().append(adultCount, rhs.adultCount).append(infantInLapCount, rhs.infantInLapCount).append(infantInSeatCount, rhs.infantInSeatCount).append(childCount, rhs.childCount).append(seniorCount, rhs.seniorCount).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
