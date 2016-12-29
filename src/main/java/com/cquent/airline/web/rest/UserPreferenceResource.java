package com.cquent.airline.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cquent.airline.domain.UserPreference;
import com.cquent.airline.service.UserPreferenceService;
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
 * REST controller for managing UserPreference.
 */
@RestController
@RequestMapping("/api")
public class UserPreferenceResource {

    private final Logger log = LoggerFactory.getLogger(UserPreferenceResource.class);

    @Inject
    private UserPreferenceService userPreferenceService;

    /**
     * POST  /user-preferences : Create a new userPreference.
     *
     * @param userPreference the userPreference to create
     * @return the ResponseEntity with status 201 (Created) and with body the new userPreference, or with status 400 (Bad Request) if the userPreference has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/user-preferences")
    @Timed
    public ResponseEntity<UserPreference> createUserPreference(@Valid @RequestBody UserPreference userPreference) throws URISyntaxException {
        log.debug("REST request to save UserPreference");
        if (userPreference.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("userPreference", "idexists", "A new userPreference cannot already have an ID")).body(null);
        }
        UserPreference result = userPreferenceService.save(userPreference);
        return ResponseEntity.created(new URI("/api/user-preferences/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("userPreference", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /user-preferences : Updates an existing userPreference.
     *
     * @param userPreference the userPreference to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated userPreference,
     * or with status 400 (Bad Request) if the userPreference is not valid,
     * or with status 500 (Internal Server Error) if the userPreference couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/user-preferences")
    @Timed
    public ResponseEntity<UserPreference> updateUserPreference(@Valid @RequestBody UserPreference userPreference) throws URISyntaxException {
        log.debug("REST request to update UserPreference");
        if (userPreference.getId() == null) {
            return createUserPreference(userPreference);
        }
        UserPreference result = userPreferenceService.save(userPreference);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("userPreference", userPreference.getId().toString()))
            .body(result);
    }

    /**
     * GET  /user-preferences : get all the userPreferences.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of userPreferences in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/user-preferences")
    @Timed
    public ResponseEntity<List<UserPreference>> getAllUserPreferences(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of UserPreferences");
        Page<UserPreference> page = userPreferenceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/user-preferences");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /user-preferences/:id : get the "id" userPreference.
     *
     * @param id the id of the userPreference to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the userPreference, or with status 404 (Not Found)
     */
    @GetMapping("/user-preferences/{id}")
    @Timed
    public ResponseEntity<UserPreference> getUserPreference(@PathVariable Long id) {
        log.debug("REST request to get UserPreference : {}", id);
        UserPreference userPreference = userPreferenceService.findOne(id);
        return Optional.ofNullable(userPreference)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /user-preferences/:id : delete the "id" userPreference.
     *
     * @param id the id of the userPreference to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/user-preferences/{id}")
    @Timed
    public ResponseEntity<Void> deleteUserPreference(@PathVariable Long id) {
        log.debug("REST request to delete UserPreference : {}", id);
        userPreferenceService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("userPreference", id.toString())).build();
    }

}
