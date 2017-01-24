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
"fare",
"segmentPricing",
"baseFareTotal",
"saleFareTotal",
"saleTaxTotal",
"saleTotal",
"passengers",
"tax",
"fareCalculation",
"latestTicketingTime",
"ptc",
"refundable"
})
public class Pricing {

@JsonProperty("kind")
private String kind;
@JsonProperty("fare")
private List<Fare> fare = null;
@JsonProperty("segmentPricing")
private List<SegmentPricing> segmentPricing = null;
@JsonProperty("baseFareTotal")
private String baseFareTotal;
@JsonProperty("saleFareTotal")
private String saleFareTotal;
@JsonProperty("saleTaxTotal")
private String saleTaxTotal;
@JsonProperty("saleTotal")
private String saleTotal;
@JsonProperty("passengers")
private Passengers passengers;
@JsonProperty("tax")
private List<Tax_> tax = null;
@JsonProperty("fareCalculation")
private String fareCalculation;
@JsonProperty("latestTicketingTime")
private String latestTicketingTime;
@JsonProperty("ptc")
private String ptc;
@JsonProperty("refundable")
private Boolean refundable;

@JsonProperty("kind")
public String getKind() {
return kind;
}

@JsonProperty("kind")
public void setKind(String kind) {
this.kind = kind;
}

@JsonProperty("fare")
public List<Fare> getFare() {
return fare;
}

@JsonProperty("fare")
public void setFare(List<Fare> fare) {
this.fare = fare;
}

@JsonProperty("segmentPricing")
public List<SegmentPricing> getSegmentPricing() {
return segmentPricing;
}

@JsonProperty("segmentPricing")
public void setSegmentPricing(List<SegmentPricing> segmentPricing) {
this.segmentPricing = segmentPricing;
}

@JsonProperty("baseFareTotal")
public String getBaseFareTotal() {
return baseFareTotal;
}

@JsonProperty("baseFareTotal")
public void setBaseFareTotal(String baseFareTotal) {
this.baseFareTotal = baseFareTotal;
}

@JsonProperty("saleFareTotal")
public String getSaleFareTotal() {
return saleFareTotal;
}

@JsonProperty("saleFareTotal")
public void setSaleFareTotal(String saleFareTotal) {
this.saleFareTotal = saleFareTotal;
}

@JsonProperty("saleTaxTotal")
public String getSaleTaxTotal() {
return saleTaxTotal;
}

@JsonProperty("saleTaxTotal")
public void setSaleTaxTotal(String saleTaxTotal) {
this.saleTaxTotal = saleTaxTotal;
}

@JsonProperty("saleTotal")
public String getSaleTotal() {
return saleTotal;
}

@JsonProperty("saleTotal")
public void setSaleTotal(String saleTotal) {
this.saleTotal = saleTotal;
}

@JsonProperty("passengers")
public Passengers getPassengers() {
return passengers;
}

@JsonProperty("passengers")
public void setPassengers(Passengers passengers) {
this.passengers = passengers;
}

@JsonProperty("tax")
public List<Tax_> getTax() {
return tax;
}

@JsonProperty("tax")
public void setTax(List<Tax_> tax) {
this.tax = tax;
}

@JsonProperty("fareCalculation")
public String getFareCalculation() {
return fareCalculation;
}

@JsonProperty("fareCalculation")
public void setFareCalculation(String fareCalculation) {
this.fareCalculation = fareCalculation;
}

@JsonProperty("latestTicketingTime")
public String getLatestTicketingTime() {
return latestTicketingTime;
}

@JsonProperty("latestTicketingTime")
public void setLatestTicketingTime(String latestTicketingTime) {
this.latestTicketingTime = latestTicketingTime;
}

@JsonProperty("ptc")
public String getPtc() {
return ptc;
}

@JsonProperty("ptc")
public void setPtc(String ptc) {
this.ptc = ptc;
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
return new HashCodeBuilder().append(kind).append(fare).append(segmentPricing).append(baseFareTotal).append(saleFareTotal).append(saleTaxTotal).append(saleTotal).append(passengers).append(tax).append(fareCalculation).append(latestTicketingTime).append(ptc).append(refundable).toHashCode();
}

@Override
public boolean equals(Object other) {
if (other == this) {
return true;
}
if ((other instanceof Pricing) == false) {
return false;
}
Pricing rhs = ((Pricing) other);
return new EqualsBuilder().append(kind, rhs.kind).append(fare, rhs.fare).append(segmentPricing, rhs.segmentPricing).append(baseFareTotal, rhs.baseFareTotal).append(saleFareTotal, rhs.saleFareTotal).append(saleTaxTotal, rhs.saleTaxTotal).append(saleTotal, rhs.saleTotal).append(passengers, rhs.passengers).append(tax, rhs.tax).append(fareCalculation, rhs.fareCalculation).append(latestTicketingTime, rhs.latestTicketingTime).append(ptc, rhs.ptc).append(refundable, rhs.refundable).isEquals();
}

}
