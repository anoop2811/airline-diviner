package com.cquent.airline.checker;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.cloudfoundry.com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.cloud.cloudfoundry.com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.cquent.airline.domain.FlightOption;
import com.cquent.airline.domain.UserPreference;
import com.cquent.airline.domain.qbx.Passengers;
import com.cquent.airline.domain.qbx.QBXRequest;
import com.cquent.airline.domain.qbx.Request;
import com.cquent.airline.domain.qbx.Slouse;
import com.cquent.airline.domain.qbx.response.QBXResponse;
import com.cquent.airline.domain.qbx.response.Segment;
import com.cquent.airline.domain.qbx.response.TripOption;
import com.cquent.airline.service.IATAService;

/**
 * Created by Anoop on 12/31/16.
 */
public class GoogleFlightChecker implements AirlinePriceChecker{
    @Value("${qbx.resource}/v1/trips/search?key=${qbx.api_key}")
    private String resource;

    @Value("${qbx.daily_rate_limit:50}")
    private int dailyRateLimit;

    @Inject
    private RestTemplate restTemplate;
    
    @Inject
    private IATAService iataService;

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private Logger logger = LoggerFactory.getLogger(GoogleFlightChecker.class);


    public FlightOption bestFlight(UserPreference userPreference) {
    	logger.info("Resource is [ {} ]", resource);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("ADRUM", "isAjax=true");
        headers.add("Accept", "application/json");
        QBXRequest qbxRequest = mapToQBXRequest(userPreference);
        HttpEntity<QBXRequest> entity = new HttpEntity<>(qbxRequest, headers);

        QBXResponse response = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println("===============");
            System.out.println(mapper.writeValueAsString(qbxRequest));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        response = restTemplate.postForObject(resource,  entity, QBXResponse.class);

        FlightOption flightOption = new FlightOption();
        flightOption.setOnwardFlightPath("N/A");
        flightOption.setReturnFlightPath("N/A");

        if(response.getTrips() != null && response.getTrips().getTripOption() != null) {
           List<TripOption> tripOptions = response.getTrips().getTripOption();
           Optional<TripOption> bestPrice =  tripOptions.stream().min((o1 , o2) -> {
        	   
        	   Double sale1 = new Double(o1.getSaleTotal().substring(3));
        	   Double sale2 = new Double(o2.getSaleTotal().substring(3));
        	   logger.debug("Comparing sale prices {} and {}", sale1, sale2);
        	   return sale1.compareTo(sale2);
           });
           if (bestPrice.isPresent()) {
               TripOption option = bestPrice.get();
               String onwardPath =  appendPath(option.getSlice().get(0).getSegment());
               flightOption.setOnwardFlightPath(onwardPath);
               String returnPath =  appendPath(option.getSlice().get(1).getSegment());
               flightOption.setReturnFlightPath(returnPath);
               flightOption.setPrice(option.getSaleTotal());
           }
        }

        return flightOption;
    }


    private QBXRequest mapToQBXRequest(UserPreference userPreference) {

        Passengers passengers = new Passengers();
        passengers.setAdultCount(1);

        Slouse onwardSlice = new Slouse();
        onwardSlice.setOrigin(userPreference.getFromCity());
        onwardSlice.setDestination(userPreference.getToCity());
        onwardSlice.setDate(userPreference.getDepartDate().format(dateTimeFormatter));

        Slouse returnSlice = new Slouse();
        returnSlice.setOrigin(userPreference.getToCity());
        returnSlice.setDestination(userPreference.getFromCity());
        returnSlice.setDate(userPreference.getReturnDate().format(dateTimeFormatter));

        Request request = new Request();
        request.setPassengers(passengers);
        request.addSlice(onwardSlice);
        request.addSlice(returnSlice);
        request.setMaxPrice(userPreference.getCurrencyId() + String.valueOf(userPreference.getThreshold()));
        request.setRefundable(userPreference.isRefundable());

        QBXRequest qbxRequest = new QBXRequest();
        qbxRequest.setRequest(request);

        return qbxRequest;

    }


    public String appendPath(List<Segment> segments) {
        StringBuilder path = new StringBuilder();
        if(segments != null ) {
            if (segments.size() > 1) {
                for (int i = 0; i < segments.size(); i++) {
                	Segment segment = segments.get(i);
                    path.append(segment.getLeg().get(0).getOrigin());
                    path.append("(");
                    path.append(iataService.getAirline(segment.getFlight().getCarrier()));
                    path.append(")");
                    path.append(" ->");
                    if(i == segments.size() -1) {       
                    	path.append(segment.getLeg().get(0).getDestination());
                    }
                }
            }
           if (segments.size() == 1) {
                path.append(segments.get(0).getLeg().get(0).getDestination());
            } else {
                return path.substring(0, path.length()).toString();
            }
        }

        return "";
    }
    
    public String getAirline(String code) {
    	return code;
    }




}
