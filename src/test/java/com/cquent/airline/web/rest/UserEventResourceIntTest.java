package com.cquent.airline.web.rest;

import com.cquent.airline.AirlineApp;

import com.cquent.airline.domain.UserEvent;
import com.cquent.airline.repository.UserEventRepository;
import com.cquent.airline.service.UserEventService;

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
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the UserEventResource REST controller.
 *
 * @see UserEventResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AirlineApp.class)
public class UserEventResourceIntTest {

    private static final String DEFAULT_BEST_ONWARD_PATH = "AAAAAAAAAA";
    private static final String UPDATED_BEST_ONWARD_PATH = "BBBBBBBBBB";

    private static final String DEFAULT_BEST_RETURN_PATH = "AAAAAAAAAA";
    private static final String UPDATED_BEST_RETURN_PATH = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_BEST_PRICE = new BigDecimal(1);
    private static final BigDecimal UPDATED_BEST_PRICE = new BigDecimal(2);

    private static final LocalDate DEFAULT_RUN_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_RUN_DATE = LocalDate.now(ZoneId.systemDefault());

    @Inject
    private UserEventRepository userEventRepository;

    @Inject
    private UserEventService userEventService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restUserEventMockMvc;

    private UserEvent userEvent;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        UserEventResource userEventResource = new UserEventResource();
        ReflectionTestUtils.setField(userEventResource, "userEventService", userEventService);
        this.restUserEventMockMvc = MockMvcBuilders.standaloneSetup(userEventResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserEvent createEntity(EntityManager em) {
        UserEvent userEvent = new UserEvent()
                .bestOnwardPath(DEFAULT_BEST_ONWARD_PATH)
                .bestReturnPath(DEFAULT_BEST_RETURN_PATH)
                .bestPrice(DEFAULT_BEST_PRICE)
                .runDate(DEFAULT_RUN_DATE);
        return userEvent;
    }

    @Before
    public void initTest() {
        userEvent = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserEvent() throws Exception {
        int databaseSizeBeforeCreate = userEventRepository.findAll().size();

        // Create the UserEvent

        restUserEventMockMvc.perform(post("/api/user-events")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userEvent)))
            .andExpect(status().isCreated());

        // Validate the UserEvent in the database
        List<UserEvent> userEventList = userEventRepository.findAll();
        assertThat(userEventList).hasSize(databaseSizeBeforeCreate + 1);
        UserEvent testUserEvent = userEventList.get(userEventList.size() - 1);
        assertThat(testUserEvent.getBestOnwardPath()).isEqualTo(DEFAULT_BEST_ONWARD_PATH);
        assertThat(testUserEvent.getBestReturnPath()).isEqualTo(DEFAULT_BEST_RETURN_PATH);
        assertThat(testUserEvent.getBestPrice()).isEqualTo(DEFAULT_BEST_PRICE);
        assertThat(testUserEvent.getRunDate()).isEqualTo(DEFAULT_RUN_DATE);
    }

    @Test
    @Transactional
    public void createUserEventWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userEventRepository.findAll().size();

        // Create the UserEvent with an existing ID
        UserEvent existingUserEvent = new UserEvent();
        existingUserEvent.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserEventMockMvc.perform(post("/api/user-events")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingUserEvent)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<UserEvent> userEventList = userEventRepository.findAll();
        assertThat(userEventList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkBestOnwardPathIsRequired() throws Exception {
        int databaseSizeBeforeTest = userEventRepository.findAll().size();
        // set the field null
        userEvent.setBestOnwardPath(null);

        // Create the UserEvent, which fails.

        restUserEventMockMvc.perform(post("/api/user-events")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userEvent)))
            .andExpect(status().isBadRequest());

        List<UserEvent> userEventList = userEventRepository.findAll();
        assertThat(userEventList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBestReturnPathIsRequired() throws Exception {
        int databaseSizeBeforeTest = userEventRepository.findAll().size();
        // set the field null
        userEvent.setBestReturnPath(null);

        // Create the UserEvent, which fails.

        restUserEventMockMvc.perform(post("/api/user-events")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userEvent)))
            .andExpect(status().isBadRequest());

        List<UserEvent> userEventList = userEventRepository.findAll();
        assertThat(userEventList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBestPriceIsRequired() throws Exception {
        int databaseSizeBeforeTest = userEventRepository.findAll().size();
        // set the field null
        userEvent.setBestPrice(null);

        // Create the UserEvent, which fails.

        restUserEventMockMvc.perform(post("/api/user-events")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userEvent)))
            .andExpect(status().isBadRequest());

        List<UserEvent> userEventList = userEventRepository.findAll();
        assertThat(userEventList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRunDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = userEventRepository.findAll().size();
        // set the field null
        userEvent.setRunDate(null);

        // Create the UserEvent, which fails.

        restUserEventMockMvc.perform(post("/api/user-events")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userEvent)))
            .andExpect(status().isBadRequest());

        List<UserEvent> userEventList = userEventRepository.findAll();
        assertThat(userEventList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllUserEvents() throws Exception {
        // Initialize the database
        userEventRepository.saveAndFlush(userEvent);

        // Get all the userEventList
        restUserEventMockMvc.perform(get("/api/user-events?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userEvent.getId().intValue())))
            .andExpect(jsonPath("$.[*].bestOnwardPath").value(hasItem(DEFAULT_BEST_ONWARD_PATH.toString())))
            .andExpect(jsonPath("$.[*].bestReturnPath").value(hasItem(DEFAULT_BEST_RETURN_PATH.toString())))
            .andExpect(jsonPath("$.[*].bestPrice").value(hasItem(DEFAULT_BEST_PRICE.intValue())))
            .andExpect(jsonPath("$.[*].runDate").value(hasItem(DEFAULT_RUN_DATE.toString())));
    }

    @Test
    @Transactional
    public void getUserEvent() throws Exception {
        // Initialize the database
        userEventRepository.saveAndFlush(userEvent);

        // Get the userEvent
        restUserEventMockMvc.perform(get("/api/user-events/{id}", userEvent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(userEvent.getId().intValue()))
            .andExpect(jsonPath("$.bestOnwardPath").value(DEFAULT_BEST_ONWARD_PATH.toString()))
            .andExpect(jsonPath("$.bestReturnPath").value(DEFAULT_BEST_RETURN_PATH.toString()))
            .andExpect(jsonPath("$.bestPrice").value(DEFAULT_BEST_PRICE.intValue()))
            .andExpect(jsonPath("$.runDate").value(DEFAULT_RUN_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingUserEvent() throws Exception {
        // Get the userEvent
        restUserEventMockMvc.perform(get("/api/user-events/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserEvent() throws Exception {
        // Initialize the database
        userEventService.save(userEvent);

        int databaseSizeBeforeUpdate = userEventRepository.findAll().size();

        // Update the userEvent
        UserEvent updatedUserEvent = userEventRepository.findOne(userEvent.getId());
        updatedUserEvent
                .bestOnwardPath(UPDATED_BEST_ONWARD_PATH)
                .bestReturnPath(UPDATED_BEST_RETURN_PATH)
                .bestPrice(UPDATED_BEST_PRICE)
                .runDate(UPDATED_RUN_DATE);

        restUserEventMockMvc.perform(put("/api/user-events")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedUserEvent)))
            .andExpect(status().isOk());

        // Validate the UserEvent in the database
        List<UserEvent> userEventList = userEventRepository.findAll();
        assertThat(userEventList).hasSize(databaseSizeBeforeUpdate);
        UserEvent testUserEvent = userEventList.get(userEventList.size() - 1);
        assertThat(testUserEvent.getBestOnwardPath()).isEqualTo(UPDATED_BEST_ONWARD_PATH);
        assertThat(testUserEvent.getBestReturnPath()).isEqualTo(UPDATED_BEST_RETURN_PATH);
        assertThat(testUserEvent.getBestPrice()).isEqualTo(UPDATED_BEST_PRICE);
        assertThat(testUserEvent.getRunDate()).isEqualTo(UPDATED_RUN_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingUserEvent() throws Exception {
        int databaseSizeBeforeUpdate = userEventRepository.findAll().size();

        // Create the UserEvent

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restUserEventMockMvc.perform(put("/api/user-events")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userEvent)))
            .andExpect(status().isCreated());

        // Validate the UserEvent in the database
        List<UserEvent> userEventList = userEventRepository.findAll();
        assertThat(userEventList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteUserEvent() throws Exception {
        // Initialize the database
        userEventService.save(userEvent);

        int databaseSizeBeforeDelete = userEventRepository.findAll().size();

        // Get the userEvent
        restUserEventMockMvc.perform(delete("/api/user-events/{id}", userEvent.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<UserEvent> userEventList = userEventRepository.findAll();
        assertThat(userEventList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
