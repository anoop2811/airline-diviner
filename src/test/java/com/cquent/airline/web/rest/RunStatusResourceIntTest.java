package com.cquent.airline.web.rest;

import com.cquent.airline.AirlineApp;

import com.cquent.airline.domain.RunStatus;
import com.cquent.airline.repository.RunStatusRepository;
import com.cquent.airline.service.RunStatusService;

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
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the RunStatusResource REST controller.
 *
 * @see RunStatusResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AirlineApp.class)
public class RunStatusResourceIntTest {

    private static final String DEFAULT_STATUS_CODE = "AAAAAAAAAA";
    private static final String UPDATED_STATUS_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_STATUS_DESCRIPTION = "BBBBBBBBBB";

    @Inject
    private RunStatusRepository runStatusRepository;

    @Inject
    private RunStatusService runStatusService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restRunStatusMockMvc;

    private RunStatus runStatus;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        RunStatusResource runStatusResource = new RunStatusResource();
        ReflectionTestUtils.setField(runStatusResource, "runStatusService", runStatusService);
        this.restRunStatusMockMvc = MockMvcBuilders.standaloneSetup(runStatusResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RunStatus createEntity(EntityManager em) {
        RunStatus runStatus = new RunStatus()
                .statusCode(DEFAULT_STATUS_CODE)
                .statusDescription(DEFAULT_STATUS_DESCRIPTION);
        return runStatus;
    }

    @Before
    public void initTest() {
        runStatus = createEntity(em);
    }

    @Test
    @Transactional
    public void createRunStatus() throws Exception {
        int databaseSizeBeforeCreate = runStatusRepository.findAll().size();

        // Create the RunStatus

        restRunStatusMockMvc.perform(post("/api/run-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(runStatus)))
            .andExpect(status().isCreated());

        // Validate the RunStatus in the database
        List<RunStatus> runStatusList = runStatusRepository.findAll();
        assertThat(runStatusList).hasSize(databaseSizeBeforeCreate + 1);
        RunStatus testRunStatus = runStatusList.get(runStatusList.size() - 1);
        assertThat(testRunStatus.getStatusCode()).isEqualTo(DEFAULT_STATUS_CODE);
        assertThat(testRunStatus.getStatusDescription()).isEqualTo(DEFAULT_STATUS_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createRunStatusWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = runStatusRepository.findAll().size();

        // Create the RunStatus with an existing ID
        RunStatus existingRunStatus = new RunStatus();
        existingRunStatus.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRunStatusMockMvc.perform(post("/api/run-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingRunStatus)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<RunStatus> runStatusList = runStatusRepository.findAll();
        assertThat(runStatusList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkStatusCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = runStatusRepository.findAll().size();
        // set the field null
        runStatus.setStatusCode(null);

        // Create the RunStatus, which fails.

        restRunStatusMockMvc.perform(post("/api/run-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(runStatus)))
            .andExpect(status().isBadRequest());

        List<RunStatus> runStatusList = runStatusRepository.findAll();
        assertThat(runStatusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = runStatusRepository.findAll().size();
        // set the field null
        runStatus.setStatusDescription(null);

        // Create the RunStatus, which fails.

        restRunStatusMockMvc.perform(post("/api/run-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(runStatus)))
            .andExpect(status().isBadRequest());

        List<RunStatus> runStatusList = runStatusRepository.findAll();
        assertThat(runStatusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRunStatuses() throws Exception {
        // Initialize the database
        runStatusRepository.saveAndFlush(runStatus);

        // Get all the runStatusList
        restRunStatusMockMvc.perform(get("/api/run-statuses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(runStatus.getId().intValue())))
            .andExpect(jsonPath("$.[*].statusCode").value(hasItem(DEFAULT_STATUS_CODE.toString())))
            .andExpect(jsonPath("$.[*].statusDescription").value(hasItem(DEFAULT_STATUS_DESCRIPTION.toString())));
    }

    @Test
    @Transactional
    public void getRunStatus() throws Exception {
        // Initialize the database
        runStatusRepository.saveAndFlush(runStatus);

        // Get the runStatus
        restRunStatusMockMvc.perform(get("/api/run-statuses/{id}", runStatus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(runStatus.getId().intValue()))
            .andExpect(jsonPath("$.statusCode").value(DEFAULT_STATUS_CODE.toString()))
            .andExpect(jsonPath("$.statusDescription").value(DEFAULT_STATUS_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRunStatus() throws Exception {
        // Get the runStatus
        restRunStatusMockMvc.perform(get("/api/run-statuses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRunStatus() throws Exception {
        // Initialize the database
        runStatusService.save(runStatus);

        int databaseSizeBeforeUpdate = runStatusRepository.findAll().size();

        // Update the runStatus
        RunStatus updatedRunStatus = runStatusRepository.findOne(runStatus.getId());
        updatedRunStatus
                .statusCode(UPDATED_STATUS_CODE)
                .statusDescription(UPDATED_STATUS_DESCRIPTION);

        restRunStatusMockMvc.perform(put("/api/run-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRunStatus)))
            .andExpect(status().isOk());

        // Validate the RunStatus in the database
        List<RunStatus> runStatusList = runStatusRepository.findAll();
        assertThat(runStatusList).hasSize(databaseSizeBeforeUpdate);
        RunStatus testRunStatus = runStatusList.get(runStatusList.size() - 1);
        assertThat(testRunStatus.getStatusCode()).isEqualTo(UPDATED_STATUS_CODE);
        assertThat(testRunStatus.getStatusDescription()).isEqualTo(UPDATED_STATUS_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingRunStatus() throws Exception {
        int databaseSizeBeforeUpdate = runStatusRepository.findAll().size();

        // Create the RunStatus

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restRunStatusMockMvc.perform(put("/api/run-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(runStatus)))
            .andExpect(status().isCreated());

        // Validate the RunStatus in the database
        List<RunStatus> runStatusList = runStatusRepository.findAll();
        assertThat(runStatusList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteRunStatus() throws Exception {
        // Initialize the database
        runStatusService.save(runStatus);

        int databaseSizeBeforeDelete = runStatusRepository.findAll().size();

        // Get the runStatus
        restRunStatusMockMvc.perform(delete("/api/run-statuses/{id}", runStatus.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RunStatus> runStatusList = runStatusRepository.findAll();
        assertThat(runStatusList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
