package com.cquent.airline.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "code", "country_code", "name" })
public class AirportResponse {

	@JsonProperty("code")
	private String code;
	@JsonProperty("country_code")
	private String countryCode;
	@JsonProperty("name")
	private String name;

	@JsonProperty("code")
	public String getCode() {
		return code;
	}

	@JsonProperty("code")
	public void setCode(String code) {
		this.code = code;
	}

	@JsonProperty("country_code")
	public String getCountryCode() {
		return countryCode;
	}

	@JsonProperty("country_code")
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(code).append(countryCode).append(name).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof AirportResponse) == false) {
			return false;
		}
		AirportResponse rhs = ((AirportResponse) other);
		return new EqualsBuilder().append(code, rhs.code).append(countryCode, rhs.countryCode).append(name, rhs.name)
				.isEquals();
	}

}
