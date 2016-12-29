package com.cquent.airline.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cquent.airline.domain.AirportResponse;
import com.cquent.airline.service.IATAService;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.net.URISyntaxException;
import java.util.List;

/**
 * REST controller for getting IATA Airport Cities.
 */
@RestController
@RequestMapping("/api")
public class AirportCitiesResource {

    private final Logger log = LoggerFactory.getLogger(AirportCitiesResource.class);

    @Inject
    private IATAService iataService;


    /**
     * GET  /airport-cities : get all the airport cities.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of airport cities in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/airport-cities")
    @Timed
    public ResponseEntity<List<AirportResponse>> getAllRunStatuses(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a list of airport cities");
        List<AirportResponse> airports = iataService.findAllAirports();
        log.debug("Total cities is [ {} ]", airports.size());
        return new ResponseEntity<>(airports, HttpStatus.OK);
    }
}
