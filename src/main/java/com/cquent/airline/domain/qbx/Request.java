package com.cquent.airline.domain.qbx;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({
    "slice",
    "passengers",
    "solutions",
    "refundable"
})
public class Request {

    @JsonProperty("slice")
    @Valid
    private List<Slouse> slice = new ArrayList<Slouse>();
    @JsonProperty("passengers")
    @Valid
    private Passengers passengers;
    @JsonProperty("solutions")
    private Integer solutions = 20;
    @JsonProperty("refundable")
    private Boolean refundable = true;
    @JsonProperty("maxPrice")
    private String maxPrice;

    @JsonProperty("slice")
    public List<Slouse> getSlice() {
        return slice;
    }

    @JsonProperty("slice")
    public void setSlice(List<Slouse> slices) {
        this.slice = slices;
    }

    @JsonProperty("maxPrice")
    public String getMaxPrice() {
        return maxPrice;
    }

    @JsonProperty("maxPrice")
    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    public void addSlice(Slouse slouse) {
        slice.add(slouse);
    }

    @JsonProperty("passengers")
    public Passengers getPassengers() {
        return passengers;
    }

    @JsonProperty("passengers")
    public void setPassengers(Passengers passengers) {
        this.passengers = passengers;
    }

    @JsonProperty("solutions")
    public Integer getSolutions() {
        return solutions;
    }

    @JsonProperty("solutions")
    public void setSolutions(Integer solutions) {
        this.solutions = solutions;
    }

    @JsonProperty("refundable")
    public Boolean getRefundable() {
        return refundable;
    }

    @JsonProperty("refundable")
    public void setRefundable(Boolean refundable) {
        this.refundable = refundable;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(slice).append(passengers).append(solutions).append(refundable).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Request) == false) {
            return false;
        }
        Request rhs = ((Request) other);
        return new EqualsBuilder().append(slice, rhs.slice).append(passengers, rhs.passengers).append(solutions, rhs.solutions).append(refundable, rhs.refundable).isEquals();
    }
}
