package com.cquent.airline.service;



import com.cquent.airline.domain.AirportResponse;
import com.cquent.airline.domain.IATAResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Service for getting IATA services.

 */
@Service
public class IATAService {

    @Value("${iata.resource}/cities?api_key=${iata.api_key}")
    private String resource;
    @Value("${iata.resource}/cities?api_key=${iata.api_key}&code={code}")
    private String idResource;
    @Autowired
    private RestTemplate restTemplate;

    public List<AirportResponse> findAllAirports() {
        IATAResponse iataResponse = restTemplate.getForObject(resource, IATAResponse.class);
        List<AirportResponse> airports = iataResponse.getResponse();
        airports.sort((p1, p2) -> p1.getName().compareTo(p2.getName()));
        return airports;
    }
}
