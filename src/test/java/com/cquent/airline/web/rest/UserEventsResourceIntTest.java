package com.cquent.airline.web.rest;

import com.cquent.airline.AirlineApp;

import com.cquent.airline.domain.UserEvents;
import com.cquent.airline.repository.UserEventsRepository;
import com.cquent.airline.service.UserEventsService;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the UserEventsResource REST controller.
 *
 * @see UserEventsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AirlineApp.class)
public class UserEventsResourceIntTest {

    private static final LocalDate DEFAULT_RUN_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_RUN_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_FROM_CITY = "AAAAAAAAAA";
    private static final String UPDATED_FROM_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_TO_CITY = "AAAAAAAAAA";
    private static final String UPDATED_TO_CITY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DEPART_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DEPART_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_RETURN_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_RETURN_DATE = LocalDate.now(ZoneId.systemDefault());

    @Inject
    private UserEventsRepository userEventsRepository;

    @Inject
    private UserEventsService userEventsService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restUserEventsMockMvc;

    private UserEvents userEvents;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        UserEventsResource userEventsResource = new UserEventsResource();
        ReflectionTestUtils.setField(userEventsResource, "userEventsService", userEventsService);
        this.restUserEventsMockMvc = MockMvcBuilders.standaloneSetup(userEventsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserEvents createEntity(EntityManager em) {
        UserEvents userEvents = new UserEvents()
                .runDate(DEFAULT_RUN_DATE)
                .fromCity(DEFAULT_FROM_CITY)
                .toCity(DEFAULT_TO_CITY)
                .departDate(DEFAULT_DEPART_DATE)
                .returnDate(DEFAULT_RETURN_DATE);
        return userEvents;
    }

    @Before
    public void initTest() {
        userEvents = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserEvents() throws Exception {
        int databaseSizeBeforeCreate = userEventsRepository.findAll().size();

        // Create the UserEvents

        restUserEventsMockMvc.perform(post("/api/user-events")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userEvents)))
            .andExpect(status().isCreated());

        // Validate the UserEvents in the database
        List<UserEvents> userEventsList = userEventsRepository.findAll();
        assertThat(userEventsList).hasSize(databaseSizeBeforeCreate + 1);
        UserEvents testUserEvents = userEventsList.get(userEventsList.size() - 1);
        assertThat(testUserEvents.getRunDate()).isEqualTo(DEFAULT_RUN_DATE);
        assertThat(testUserEvents.getFromCity()).isEqualTo(DEFAULT_FROM_CITY);
        assertThat(testUserEvents.getToCity()).isEqualTo(DEFAULT_TO_CITY);
        assertThat(testUserEvents.getDepartDate()).isEqualTo(DEFAULT_DEPART_DATE);
        assertThat(testUserEvents.getReturnDate()).isEqualTo(DEFAULT_RETURN_DATE);
    }

    @Test
    @Transactional
    public void createUserEventsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userEventsRepository.findAll().size();

        // Create the UserEvents with an existing ID
        UserEvents existingUserEvents = new UserEvents();
        existingUserEvents.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserEventsMockMvc.perform(post("/api/user-events")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingUserEvents)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<UserEvents> userEventsList = userEventsRepository.findAll();
        assertThat(userEventsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkRunDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = userEventsRepository.findAll().size();
        // set the field null
        userEvents.setRunDate(null);

        // Create the UserEvents, which fails.

        restUserEventsMockMvc.perform(post("/api/user-events")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userEvents)))
            .andExpect(status().isBadRequest());

        List<UserEvents> userEventsList = userEventsRepository.findAll();
        assertThat(userEventsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFromCityIsRequired() throws Exception {
        int databaseSizeBeforeTest = userEventsRepository.findAll().size();
        // set the field null
        userEvents.setFromCity(null);

        // Create the UserEvents, which fails.

        restUserEventsMockMvc.perform(post("/api/user-events")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userEvents)))
            .andExpect(status().isBadRequest());

        List<UserEvents> userEventsList = userEventsRepository.findAll();
        assertThat(userEventsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkToCityIsRequired() throws Exception {
        int databaseSizeBeforeTest = userEventsRepository.findAll().size();
        // set the field null
        userEvents.setToCity(null);

        // Create the UserEvents, which fails.

        restUserEventsMockMvc.perform(post("/api/user-events")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userEvents)))
            .andExpect(status().isBadRequest());

        List<UserEvents> userEventsList = userEventsRepository.findAll();
        assertThat(userEventsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDepartDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = userEventsRepository.findAll().size();
        // set the field null
        userEvents.setDepartDate(null);

        // Create the UserEvents, which fails.

        restUserEventsMockMvc.perform(post("/api/user-events")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userEvents)))
            .andExpect(status().isBadRequest());

        List<UserEvents> userEventsList = userEventsRepository.findAll();
        assertThat(userEventsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkReturnDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = userEventsRepository.findAll().size();
        // set the field null
        userEvents.setReturnDate(null);

        // Create the UserEvents, which fails.

        restUserEventsMockMvc.perform(post("/api/user-events")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userEvents)))
            .andExpect(status().isBadRequest());

        List<UserEvents> userEventsList = userEventsRepository.findAll();
        assertThat(userEventsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllUserEvents() throws Exception {
        // Initialize the database
        userEventsRepository.saveAndFlush(userEvents);

        // Get all the userEventsList
        restUserEventsMockMvc.perform(get("/api/user-events?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userEvents.getId().intValue())))
            .andExpect(jsonPath("$.[*].runDate").value(hasItem(DEFAULT_RUN_DATE.toString())))
            .andExpect(jsonPath("$.[*].fromCity").value(hasItem(DEFAULT_FROM_CITY.toString())))
            .andExpect(jsonPath("$.[*].toCity").value(hasItem(DEFAULT_TO_CITY.toString())))
            .andExpect(jsonPath("$.[*].departDate").value(hasItem(DEFAULT_DEPART_DATE.toString())))
            .andExpect(jsonPath("$.[*].returnDate").value(hasItem(DEFAULT_RETURN_DATE.toString())));
    }

    @Test
    @Transactional
    public void getUserEvents() throws Exception {
        // Initialize the database
        userEventsRepository.saveAndFlush(userEvents);

        // Get the userEvents
        restUserEventsMockMvc.perform(get("/api/user-events/{id}", userEvents.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(userEvents.getId().intValue()))
            .andExpect(jsonPath("$.runDate").value(DEFAULT_RUN_DATE.toString()))
            .andExpect(jsonPath("$.fromCity").value(DEFAULT_FROM_CITY.toString()))
            .andExpect(jsonPath("$.toCity").value(DEFAULT_TO_CITY.toString()))
            .andExpect(jsonPath("$.departDate").value(DEFAULT_DEPART_DATE.toString()))
            .andExpect(jsonPath("$.returnDate").value(DEFAULT_RETURN_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingUserEvents() throws Exception {
        // Get the userEvents
        restUserEventsMockMvc.perform(get("/api/user-events/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserEvents() throws Exception {
        // Initialize the database
        userEventsService.save(userEvents);

        int databaseSizeBeforeUpdate = userEventsRepository.findAll().size();

        // Update the userEvents
        UserEvents updatedUserEvents = userEventsRepository.findOne(userEvents.getId());
        updatedUserEvents
                .runDate(UPDATED_RUN_DATE)
                .fromCity(UPDATED_FROM_CITY)
                .toCity(UPDATED_TO_CITY)
                .departDate(UPDATED_DEPART_DATE)
                .returnDate(UPDATED_RETURN_DATE);

        restUserEventsMockMvc.perform(put("/api/user-events")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedUserEvents)))
            .andExpect(status().isOk());

        // Validate the UserEvents in the database
        List<UserEvents> userEventsList = userEventsRepository.findAll();
        assertThat(userEventsList).hasSize(databaseSizeBeforeUpdate);
        UserEvents testUserEvents = userEventsList.get(userEventsList.size() - 1);
        assertThat(testUserEvents.getRunDate()).isEqualTo(UPDATED_RUN_DATE);
        assertThat(testUserEvents.getFromCity()).isEqualTo(UPDATED_FROM_CITY);
        assertThat(testUserEvents.getToCity()).isEqualTo(UPDATED_TO_CITY);
        assertThat(testUserEvents.getDepartDate()).isEqualTo(UPDATED_DEPART_DATE);
        assertThat(testUserEvents.getReturnDate()).isEqualTo(UPDATED_RETURN_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingUserEvents() throws Exception {
        int databaseSizeBeforeUpdate = userEventsRepository.findAll().size();

        // Create the UserEvents

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restUserEventsMockMvc.perform(put("/api/user-events")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userEvents)))
            .andExpect(status().isCreated());

        // Validate the UserEvents in the database
        List<UserEvents> userEventsList = userEventsRepository.findAll();
        assertThat(userEventsList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteUserEvents() throws Exception {
        // Initialize the database
        userEventsService.save(userEvents);

        int databaseSizeBeforeDelete = userEventsRepository.findAll().size();

        // Get the userEvents
        restUserEventsMockMvc.perform(delete("/api/user-events/{id}", userEvents.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<UserEvents> userEventsList = userEventsRepository.findAll();
        assertThat(userEventsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
