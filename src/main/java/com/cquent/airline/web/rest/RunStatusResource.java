package com.cquent.airline.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cquent.airline.domain.RunStatus;
import com.cquent.airline.service.RunStatusService;
import com.cquent.airline.web.rest.util.HeaderUtil;
import com.cquent.airline.web.rest.util.PaginationUtil;

import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing RunStatus.
 */
@RestController
@RequestMapping("/api")
public class RunStatusResource {

    private final Logger log = LoggerFactory.getLogger(RunStatusResource.class);
        
    @Inject
    private RunStatusService runStatusService;

    /**
     * POST  /run-statuses : Create a new runStatus.
     *
     * @param runStatus the runStatus to create
     * @return the ResponseEntity with status 201 (Created) and with body the new runStatus, or with status 400 (Bad Request) if the runStatus has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/run-statuses")
    @Timed
    public ResponseEntity<RunStatus> createRunStatus(@Valid @RequestBody RunStatus runStatus) throws URISyntaxException {
        log.debug("REST request to save RunStatus : {}", runStatus);
        if (runStatus.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("runStatus", "idexists", "A new runStatus cannot already have an ID")).body(null);
        }
        RunStatus result = runStatusService.save(runStatus);
        return ResponseEntity.created(new URI("/api/run-statuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("runStatus", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /run-statuses : Updates an existing runStatus.
     *
     * @param runStatus the runStatus to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated runStatus,
     * or with status 400 (Bad Request) if the runStatus is not valid,
     * or with status 500 (Internal Server Error) if the runStatus couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/run-statuses")
    @Timed
    public ResponseEntity<RunStatus> updateRunStatus(@Valid @RequestBody RunStatus runStatus) throws URISyntaxException {
        log.debug("REST request to update RunStatus : {}", runStatus);
        if (runStatus.getId() == null) {
            return createRunStatus(runStatus);
        }
        RunStatus result = runStatusService.save(runStatus);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("runStatus", runStatus.getId().toString()))
            .body(result);
    }

    /**
     * GET  /run-statuses : get all the runStatuses.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of runStatuses in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/run-statuses")
    @Timed
    public ResponseEntity<List<RunStatus>> getAllRunStatuses(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of RunStatuses");
        Page<RunStatus> page = runStatusService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/run-statuses");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /run-statuses/:id : get the "id" runStatus.
     *
     * @param id the id of the runStatus to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the runStatus, or with status 404 (Not Found)
     */
    @GetMapping("/run-statuses/{id}")
    @Timed
    public ResponseEntity<RunStatus> getRunStatus(@PathVariable Long id) {
        log.debug("REST request to get RunStatus : {}", id);
        RunStatus runStatus = runStatusService.findOne(id);
        return Optional.ofNullable(runStatus)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /run-statuses/:id : delete the "id" runStatus.
     *
     * @param id the id of the runStatus to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/run-statuses/{id}")
    @Timed
    public ResponseEntity<Void> deleteRunStatus(@PathVariable Long id) {
        log.debug("REST request to delete RunStatus : {}", id);
        runStatusService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("runStatus", id.toString())).build();
    }

}
