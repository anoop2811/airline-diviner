package com.cquent.airline.service;

import java.util.List;

import javax.cache.CacheManager;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cquent.airline.domain.AirportResponse;
import com.cquent.airline.domain.IATAAirlineResponse;
import com.cquent.airline.domain.IATAAirportResponse;

/**
 * Service for getting IATA services.
 * 
 */
@Service
public class IATAService {

	@Value("${iata.resource}/cities?api_key=${iata.api_key}")
	private String resource;
	@Value("${iata.resource}/cities?api_key=${iata.api_key}&code={code}")
	private String idResource;
	@Value("${iata.resource}/airlines?api_key=${iata.api_key}&code=")
	private String airlineResource;
	@Inject
	private RestTemplate restTemplate;
	@Inject
	private CacheManager cacheManager;

	private Logger logger = LoggerFactory.getLogger(IATAService.class);

	@Cacheable(cacheNames = "airports")
	public List<AirportResponse> findAllAirports() {
		IATAAirportResponse iataResponse = restTemplate.getForObject(resource, IATAAirportResponse.class);
		List<AirportResponse> airports = iataResponse.getResponse();
		airports.sort((p1, p2) -> p1.getName().compareTo(p2.getName()));
		return airports;
	}

	@Cacheable("airline")
	public String getAirline(String code) {
		String airline = code;
		try {
			IATAAirlineResponse response = restTemplate.getForObject(airlineResource + code, IATAAirlineResponse.class);
			if (response.getResponse() != null) {
				airline = response.getResponse().get(0).getName();
			}
			logger.debug("[ {} ] code is for  [ {} ]", code, airline);
		} catch (Exception e) {
			logger.error("Error retrieving airline for code [{}]", code, e);
		}
		return airline;
	}
}
