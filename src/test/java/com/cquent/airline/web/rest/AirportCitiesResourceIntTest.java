package com.cquent.airline.web.rest;

import com.cquent.airline.AirlineApp;
import com.cquent.airline.domain.AirportResponse;
import com.cquent.airline.domain.UserPreference;
import com.cquent.airline.service.IATAService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for the UserPreferenceResource REST controller.
 *
 * @see UserPreferenceResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AirlineApp.class)
public class AirportCitiesResourceIntTest {



    @Inject
    private AirportCitiesResource airportCitiesResource;

    @Inject
    private IATAService iataService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restUserPreferenceMockMvc;

    private UserPreference userPreference;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        AirportCitiesResource airportCitiesResource = new AirportCitiesResource();
        ReflectionTestUtils.setField(airportCitiesResource, "iataService", iataService);
        this.restUserPreferenceMockMvc = MockMvcBuilders.standaloneSetup(airportCitiesResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Test
    @Transactional
    public void getAllAirportCities() throws Exception {

        // Get all the userPreferenceList
        restUserPreferenceMockMvc.perform(get("/api/airport-cities"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        // Validate the UserPreference in the database
        List<AirportResponse> airports = iataService.findAllAirports();
        assertThat(airports).hasAtLeastOneElementOfType(AirportResponse.class);
    }


}
