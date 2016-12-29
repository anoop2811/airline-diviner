package com.cquent.airline.web.rest;

import com.cquent.airline.AirlineApp;

import com.cquent.airline.domain.UserPreference;
import com.cquent.airline.repository.UserPreferenceRepository;
import com.cquent.airline.service.UserPreferenceService;

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

import com.cquent.airline.domain.enumeration.Currency;
/**
 * Test class for the UserPreferenceResource REST controller.
 *
 * @see UserPreferenceResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AirlineApp.class)
public class UserPreferenceResourceIntTest {

    private static final String DEFAULT_FROM_CITY = "AAAAAAAAAA";
    private static final String UPDATED_FROM_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_TO_CITY = "AAAAAAAAAA";
    private static final String UPDATED_TO_CITY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DEPART_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DEPART_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_RETURN_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_RETURN_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Currency DEFAULT_CURRENCY_ID = Currency.USD;
    private static final Currency UPDATED_CURRENCY_ID = Currency.INR;

    private static final BigDecimal DEFAULT_THRESHOLD = new BigDecimal(1);
    private static final BigDecimal UPDATED_THRESHOLD = new BigDecimal(2);

    private static final Integer DEFAULT_FREQUENCY = 3;
    private static final Integer UPDATED_FREQUENCY = 4;

    private static final LocalDate DEFAULT_NEXT_RUN_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NEXT_RUN_DATE = LocalDate.now(ZoneId.systemDefault());

    @Inject
    private UserPreferenceRepository userPreferenceRepository;

    @Inject
    private UserPreferenceService userPreferenceService;

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
        UserPreferenceResource userPreferenceResource = new UserPreferenceResource();
        ReflectionTestUtils.setField(userPreferenceResource, "userPreferenceService", userPreferenceService);
        this.restUserPreferenceMockMvc = MockMvcBuilders.standaloneSetup(userPreferenceResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserPreference createEntity(EntityManager em) {
        UserPreference userPreference = new UserPreference()
                .fromCity(DEFAULT_FROM_CITY)
                .toCity(DEFAULT_TO_CITY)
                .departDate(DEFAULT_DEPART_DATE)
                .returnDate(DEFAULT_RETURN_DATE)
                .currencyId(DEFAULT_CURRENCY_ID)
                .threshold(DEFAULT_THRESHOLD)
                .frequency(DEFAULT_FREQUENCY)
                .nextRunDate(DEFAULT_NEXT_RUN_DATE);
        return userPreference;
    }

    @Before
    public void initTest() {
        userPreference = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserPreference() throws Exception {
        int databaseSizeBeforeCreate = userPreferenceRepository.findAll().size();

        // Create the UserPreference

        restUserPreferenceMockMvc.perform(post("/api/user-preferences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userPreference)))
            .andExpect(status().isCreated());

        // Validate the UserPreference in the database
        List<UserPreference> userPreferenceList = userPreferenceRepository.findAll();
        assertThat(userPreferenceList).hasSize(databaseSizeBeforeCreate + 1);
        UserPreference testUserPreference = userPreferenceList.get(userPreferenceList.size() - 1);
        assertThat(testUserPreference.getFromCity()).isEqualTo(DEFAULT_FROM_CITY);
        assertThat(testUserPreference.getToCity()).isEqualTo(DEFAULT_TO_CITY);
        assertThat(testUserPreference.getDepartDate()).isEqualTo(DEFAULT_DEPART_DATE);
        assertThat(testUserPreference.getReturnDate()).isEqualTo(DEFAULT_RETURN_DATE);
        assertThat(testUserPreference.getCurrencyId()).isEqualTo(DEFAULT_CURRENCY_ID);
        assertThat(testUserPreference.getThreshold()).isEqualTo(DEFAULT_THRESHOLD);
        assertThat(testUserPreference.getFrequency()).isEqualTo(DEFAULT_FREQUENCY);
        assertThat(testUserPreference.getNextRunDate()).isEqualTo(DEFAULT_NEXT_RUN_DATE);
    }

    @Test
    @Transactional
    public void createUserPreferenceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userPreferenceRepository.findAll().size();

        // Create the UserPreference with an existing ID
        UserPreference existingUserPreference = new UserPreference();
        existingUserPreference.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserPreferenceMockMvc.perform(post("/api/user-preferences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingUserPreference)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<UserPreference> userPreferenceList = userPreferenceRepository.findAll();
        assertThat(userPreferenceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkFromCityIsRequired() throws Exception {
        int databaseSizeBeforeTest = userPreferenceRepository.findAll().size();
        // set the field null
        userPreference.setFromCity(null);

        // Create the UserPreference, which fails.

        restUserPreferenceMockMvc.perform(post("/api/user-preferences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userPreference)))
            .andExpect(status().isBadRequest());

        List<UserPreference> userPreferenceList = userPreferenceRepository.findAll();
        assertThat(userPreferenceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkToCityIsRequired() throws Exception {
        int databaseSizeBeforeTest = userPreferenceRepository.findAll().size();
        // set the field null
        userPreference.setToCity(null);

        // Create the UserPreference, which fails.

        restUserPreferenceMockMvc.perform(post("/api/user-preferences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userPreference)))
            .andExpect(status().isBadRequest());

        List<UserPreference> userPreferenceList = userPreferenceRepository.findAll();
        assertThat(userPreferenceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDepartDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = userPreferenceRepository.findAll().size();
        // set the field null
        userPreference.setDepartDate(null);

        // Create the UserPreference, which fails.

        restUserPreferenceMockMvc.perform(post("/api/user-preferences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userPreference)))
            .andExpect(status().isBadRequest());

        List<UserPreference> userPreferenceList = userPreferenceRepository.findAll();
        assertThat(userPreferenceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkReturnDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = userPreferenceRepository.findAll().size();
        // set the field null
        userPreference.setReturnDate(null);

        // Create the UserPreference, which fails.

        restUserPreferenceMockMvc.perform(post("/api/user-preferences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userPreference)))
            .andExpect(status().isBadRequest());

        List<UserPreference> userPreferenceList = userPreferenceRepository.findAll();
        assertThat(userPreferenceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkThresholdIsRequired() throws Exception {
        int databaseSizeBeforeTest = userPreferenceRepository.findAll().size();
        // set the field null
        userPreference.setThreshold(null);

        // Create the UserPreference, which fails.

        restUserPreferenceMockMvc.perform(post("/api/user-preferences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userPreference)))
            .andExpect(status().isBadRequest());

        List<UserPreference> userPreferenceList = userPreferenceRepository.findAll();
        assertThat(userPreferenceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllUserPreferences() throws Exception {
        // Initialize the database
        userPreferenceRepository.saveAndFlush(userPreference);

        // Get all the userPreferenceList
        restUserPreferenceMockMvc.perform(get("/api/user-preferences?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userPreference.getId().intValue())))
            .andExpect(jsonPath("$.[*].fromCity").value(hasItem(DEFAULT_FROM_CITY.toString())))
            .andExpect(jsonPath("$.[*].toCity").value(hasItem(DEFAULT_TO_CITY.toString())))
            .andExpect(jsonPath("$.[*].departDate").value(hasItem(DEFAULT_DEPART_DATE.toString())))
            .andExpect(jsonPath("$.[*].returnDate").value(hasItem(DEFAULT_RETURN_DATE.toString())))
            .andExpect(jsonPath("$.[*].currencyId").value(hasItem(DEFAULT_CURRENCY_ID.toString())))
            .andExpect(jsonPath("$.[*].threshold").value(hasItem(DEFAULT_THRESHOLD.intValue())))
            .andExpect(jsonPath("$.[*].frequency").value(hasItem(DEFAULT_FREQUENCY)))
            .andExpect(jsonPath("$.[*].nextRunDate").value(hasItem(DEFAULT_NEXT_RUN_DATE.toString())));
    }

    @Test
    @Transactional
    public void getUserPreference() throws Exception {
        // Initialize the database
        userPreferenceRepository.saveAndFlush(userPreference);

        // Get the userPreference
        restUserPreferenceMockMvc.perform(get("/api/user-preferences/{id}", userPreference.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(userPreference.getId().intValue()))
            .andExpect(jsonPath("$.fromCity").value(DEFAULT_FROM_CITY.toString()))
            .andExpect(jsonPath("$.toCity").value(DEFAULT_TO_CITY.toString()))
            .andExpect(jsonPath("$.departDate").value(DEFAULT_DEPART_DATE.toString()))
            .andExpect(jsonPath("$.returnDate").value(DEFAULT_RETURN_DATE.toString()))
            .andExpect(jsonPath("$.currencyId").value(DEFAULT_CURRENCY_ID.toString()))
            .andExpect(jsonPath("$.threshold").value(DEFAULT_THRESHOLD.intValue()))
            .andExpect(jsonPath("$.frequency").value(DEFAULT_FREQUENCY))
            .andExpect(jsonPath("$.nextRunDate").value(DEFAULT_NEXT_RUN_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingUserPreference() throws Exception {
        // Get the userPreference
        restUserPreferenceMockMvc.perform(get("/api/user-preferences/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserPreference() throws Exception {
        // Initialize the database
        userPreferenceService.save(userPreference);

        int databaseSizeBeforeUpdate = userPreferenceRepository.findAll().size();

        // Update the userPreference
        UserPreference updatedUserPreference = userPreferenceRepository.findOne(userPreference.getId());
        updatedUserPreference
                .fromCity(UPDATED_FROM_CITY)
                .toCity(UPDATED_TO_CITY)
                .departDate(UPDATED_DEPART_DATE)
                .returnDate(UPDATED_RETURN_DATE)
                .currencyId(UPDATED_CURRENCY_ID)
                .threshold(UPDATED_THRESHOLD)
                .frequency(UPDATED_FREQUENCY)
                .nextRunDate(UPDATED_NEXT_RUN_DATE);

        restUserPreferenceMockMvc.perform(put("/api/user-preferences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedUserPreference)))
            .andExpect(status().isOk());

        // Validate the UserPreference in the database
        List<UserPreference> userPreferenceList = userPreferenceRepository.findAll();
        assertThat(userPreferenceList).hasSize(databaseSizeBeforeUpdate);
        UserPreference testUserPreference = userPreferenceList.get(userPreferenceList.size() - 1);
        assertThat(testUserPreference.getFromCity()).isEqualTo(UPDATED_FROM_CITY);
        assertThat(testUserPreference.getToCity()).isEqualTo(UPDATED_TO_CITY);
        assertThat(testUserPreference.getDepartDate()).isEqualTo(UPDATED_DEPART_DATE);
        assertThat(testUserPreference.getReturnDate()).isEqualTo(UPDATED_RETURN_DATE);
        assertThat(testUserPreference.getCurrencyId()).isEqualTo(UPDATED_CURRENCY_ID);
        assertThat(testUserPreference.getThreshold()).isEqualTo(UPDATED_THRESHOLD);
        assertThat(testUserPreference.getFrequency()).isEqualTo(UPDATED_FREQUENCY);
        assertThat(testUserPreference.getNextRunDate()).isEqualTo(UPDATED_NEXT_RUN_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingUserPreference() throws Exception {
        int databaseSizeBeforeUpdate = userPreferenceRepository.findAll().size();

        // Create the UserPreference

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restUserPreferenceMockMvc.perform(put("/api/user-preferences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userPreference)))
            .andExpect(status().isCreated());

        // Validate the UserPreference in the database
        List<UserPreference> userPreferenceList = userPreferenceRepository.findAll();
        assertThat(userPreferenceList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteUserPreference() throws Exception {
        // Initialize the database
        userPreferenceService.save(userPreference);

        int databaseSizeBeforeDelete = userPreferenceRepository.findAll().size();

        // Get the userPreference
        restUserPreferenceMockMvc.perform(delete("/api/user-preferences/{id}", userPreference.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<UserPreference> userPreferenceList = userPreferenceRepository.findAll();
        assertThat(userPreferenceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
