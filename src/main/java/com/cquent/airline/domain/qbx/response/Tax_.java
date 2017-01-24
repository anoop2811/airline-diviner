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
"chargeType",
"code",
"country",
"salePrice"
})
public class Tax_ {

@JsonProperty("kind")
private String kind;
@JsonProperty("id")
private String id;
@JsonProperty("chargeType")
private String chargeType;
@JsonProperty("code")
private String code;
@JsonProperty("country")
private String country;
@JsonProperty("salePrice")
private String salePrice;

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

@JsonProperty("chargeType")
public String getChargeType() {
return chargeType;
}

@JsonProperty("chargeType")
public void setChargeType(String chargeType) {
this.chargeType = chargeType;
}

@JsonProperty("code")
public String getCode() {
return code;
}

@JsonProperty("code")
public void setCode(String code) {
this.code = code;
}

@JsonProperty("country")
public String getCountry() {
return country;
}

@JsonProperty("country")
public void setCountry(String country) {
this.country = country;
}

@JsonProperty("salePrice")
public String getSalePrice() {
return salePrice;
}

@JsonProperty("salePrice")
public void setSalePrice(String salePrice) {
this.salePrice = salePrice;
}

@Override
public String toString() {
return ToStringBuilder.reflectionToString(this);
}

@Override
public int hashCode() {
return new HashCodeBuilder().append(kind).append(id).append(chargeType).append(code).append(country).append(salePrice).toHashCode();
}

@Override
public boolean equals(Object other) {
if (other == this) {
return true;
}
if ((other instanceof Tax_) == false) {
return false;
}
Tax_ rhs = ((Tax_) other);
return new EqualsBuilder().append(kind, rhs.kind).append(id, rhs.id).append(chargeType, rhs.chargeType).append(code, rhs.code).append(country, rhs.country).append(salePrice, rhs.salePrice).isEquals();
}

}
