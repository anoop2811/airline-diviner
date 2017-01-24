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
"airport",
"city",
"aircraft",
"tax",
"carrier"
})
public class Data {

@JsonProperty("kind")
private String kind;
@JsonProperty("airport")
private List<Airport> airport = null;
@JsonProperty("city")
private List<City> city = null;
@JsonProperty("aircraft")
private List<Aircraft> aircraft = null;
@JsonProperty("tax")
private List<Tax> tax = null;
@JsonProperty("carrier")
private List<Carrier> carrier = null;

@JsonProperty("kind")
public String getKind() {
return kind;
}

@JsonProperty("kind")
public void setKind(String kind) {
this.kind = kind;
}

@JsonProperty("airport")
public List<Airport> getAirport() {
return airport;
}

@JsonProperty("airport")
public void setAirport(List<Airport> airport) {
this.airport = airport;
}

@JsonProperty("city")
public List<City> getCity() {
return city;
}

@JsonProperty("city")
public void setCity(List<City> city) {
this.city = city;
}

@JsonProperty("aircraft")
public List<Aircraft> getAircraft() {
return aircraft;
}

@JsonProperty("aircraft")
public void setAircraft(List<Aircraft> aircraft) {
this.aircraft = aircraft;
}

@JsonProperty("tax")
public List<Tax> getTax() {
return tax;
}

@JsonProperty("tax")
public void setTax(List<Tax> tax) {
this.tax = tax;
}

@JsonProperty("carrier")
public List<Carrier> getCarrier() {
return carrier;
}

@JsonProperty("carrier")
public void setCarrier(List<Carrier> carrier) {
this.carrier = carrier;
}

@Override
public String toString() {
return ToStringBuilder.reflectionToString(this);
}

@Override
public int hashCode() {
return new HashCodeBuilder().append(kind).append(airport).append(city).append(aircraft).append(tax).append(carrier).toHashCode();
}

@Override
public boolean equals(Object other) {
if (other == this) {
return true;
}
if ((other instanceof Data) == false) {
return false;
}
Data rhs = ((Data) other);
return new EqualsBuilder().append(kind, rhs.kind).append(airport, rhs.airport).append(city, rhs.city).append(aircraft, rhs.aircraft).append(tax, rhs.tax).append(carrier, rhs.carrier).isEquals();
}

}
