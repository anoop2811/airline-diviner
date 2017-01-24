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
"bagDescriptor",
"kilos",
"kilosPerPiece",
"pieces",
"pounds"
})
public class FreeBaggageOption {

@JsonProperty("kind")
private String kind;
@JsonProperty("bagDescriptor")
private List<BagDescriptor> bagDescriptor = null;
@JsonProperty("kilos")
private Integer kilos;
@JsonProperty("kilosPerPiece")
private Integer kilosPerPiece;
@JsonProperty("pieces")
private Integer pieces;
@JsonProperty("pounds")
private Integer pounds;

@JsonProperty("kind")
public String getKind() {
return kind;
}

@JsonProperty("kind")
public void setKind(String kind) {
this.kind = kind;
}

@JsonProperty("bagDescriptor")
public List<BagDescriptor> getBagDescriptor() {
return bagDescriptor;
}

@JsonProperty("bagDescriptor")
public void setBagDescriptor(List<BagDescriptor> bagDescriptor) {
this.bagDescriptor = bagDescriptor;
}

@JsonProperty("kilos")
public Integer getKilos() {
return kilos;
}

@JsonProperty("kilos")
public void setKilos(Integer kilos) {
this.kilos = kilos;
}

@JsonProperty("kilosPerPiece")
public Integer getKilosPerPiece() {
return kilosPerPiece;
}

@JsonProperty("kilosPerPiece")
public void setKilosPerPiece(Integer kilosPerPiece) {
this.kilosPerPiece = kilosPerPiece;
}

@JsonProperty("pieces")
public Integer getPieces() {
return pieces;
}

@JsonProperty("pieces")
public void setPieces(Integer pieces) {
this.pieces = pieces;
}

@JsonProperty("pounds")
public Integer getPounds() {
return pounds;
}

@JsonProperty("pounds")
public void setPounds(Integer pounds) {
this.pounds = pounds;
}

@Override
public String toString() {
return ToStringBuilder.reflectionToString(this);
}

@Override
public int hashCode() {
return new HashCodeBuilder().append(kind).append(bagDescriptor).append(kilos).append(kilosPerPiece).append(pieces).append(pounds).toHashCode();
}

@Override
public boolean equals(Object other) {
if (other == this) {
return true;
}
if ((other instanceof FreeBaggageOption) == false) {
return false;
}
FreeBaggageOption rhs = ((FreeBaggageOption) other);
return new EqualsBuilder().append(kind, rhs.kind).append(bagDescriptor, rhs.bagDescriptor).append(kilos, rhs.kilos).append(kilosPerPiece, rhs.kilosPerPiece).append(pieces, rhs.pieces).append(pounds, rhs.pounds).isEquals();
}

}
