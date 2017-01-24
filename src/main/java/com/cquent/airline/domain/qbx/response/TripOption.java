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
"saleTotal",
"id",
"slice",
"pricing"
})
public class TripOption {

@JsonProperty("kind")
private String kind;
@JsonProperty("saleTotal")
private String saleTotal;
@JsonProperty("id")
private String id;
@JsonProperty("slice")
private List<Slouse> slice = null;
@JsonProperty("pricing")
private List<Pricing> pricing = null;

@JsonProperty("kind")
public String getKind() {
return kind;
}

@JsonProperty("kind")
public void setKind(String kind) {
this.kind = kind;
}

@JsonProperty("saleTotal")
public String getSaleTotal() {
return saleTotal;
}

@JsonProperty("saleTotal")
public void setSaleTotal(String saleTotal) {
this.saleTotal = saleTotal;
}

@JsonProperty("id")
public String getId() {
return id;
}

@JsonProperty("id")
public void setId(String id) {
this.id = id;
}

@JsonProperty("slice")
public List<Slouse> getSlice() {
return slice;
}

@JsonProperty("slice")
public void setSlice(List<Slouse> slice) {
this.slice = slice;
}

@JsonProperty("pricing")
public List<Pricing> getPricing() {
return pricing;
}

@JsonProperty("pricing")
public void setPricing(List<Pricing> pricing) {
this.pricing = pricing;
}

@Override
public String toString() {
return ToStringBuilder.reflectionToString(this);
}

@Override
public int hashCode() {
return new HashCodeBuilder().append(kind).append(saleTotal).append(id).append(slice).append(pricing).toHashCode();
}

@Override
public boolean equals(Object other) {
if (other == this) {
return true;
}
if ((other instanceof TripOption) == false) {
return false;
}
TripOption rhs = ((TripOption) other);
return new EqualsBuilder().append(kind, rhs.kind).append(saleTotal, rhs.saleTotal).append(id, rhs.id).append(slice, rhs.slice).append(pricing, rhs.pricing).isEquals();
}

}
