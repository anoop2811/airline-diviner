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
"commercialName",
"count",
"description",
"subcode"
})
public class BagDescriptor {

@JsonProperty("kind")
private String kind;
@JsonProperty("commercialName")
private String commercialName;
@JsonProperty("count")
private Integer count;
@JsonProperty("description")
private List<String> description = null;
@JsonProperty("subcode")
private String subcode;

@JsonProperty("kind")
public String getKind() {
return kind;
}

@JsonProperty("kind")
public void setKind(String kind) {
this.kind = kind;
}

@JsonProperty("commercialName")
public String getCommercialName() {
return commercialName;
}

@JsonProperty("commercialName")
public void setCommercialName(String commercialName) {
this.commercialName = commercialName;
}

@JsonProperty("count")
public Integer getCount() {
return count;
}

@JsonProperty("count")
public void setCount(Integer count) {
this.count = count;
}

@JsonProperty("description")
public List<String> getDescription() {
return description;
}

@JsonProperty("description")
public void setDescription(List<String> description) {
this.description = description;
}

@JsonProperty("subcode")
public String getSubcode() {
return subcode;
}

@JsonProperty("subcode")
public void setSubcode(String subcode) {
this.subcode = subcode;
}

@Override
public String toString() {
return ToStringBuilder.reflectionToString(this);
}

@Override
public int hashCode() {
return new HashCodeBuilder().append(kind).append(commercialName).append(count).append(description).append(subcode).toHashCode();
}

@Override
public boolean equals(Object other) {
if (other == this) {
return true;
}
if ((other instanceof BagDescriptor) == false) {
return false;
}
BagDescriptor rhs = ((BagDescriptor) other);
return new EqualsBuilder().append(kind, rhs.kind).append(commercialName, rhs.commercialName).append(count, rhs.count).append(description, rhs.description).append(subcode, rhs.subcode).isEquals();
}

}
