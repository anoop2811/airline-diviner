package com.cquent.airline.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cquent.airline.domain.UserEvents;
import com.cquent.airline.service.UserEventsService;
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
 * REST controller for managing UserEvents.
 */
@RestController
@RequestMapping("/api")
public class UserEventsResource {

    private final Logger log = LoggerFactory.getLogger(UserEventsResource.class);
        
    @Inject
    private UserEventsService userEventsService;

    /**
     * POST  /user-events : Create a new userEvents.
     *
     * @param userEvents the userEvents to create
     * @return the ResponseEntity with status 201 (Created) and with body the new userEvents, or with status 400 (Bad Request) if the userEvents has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/user-events")
    @Timed
    public ResponseEntity<UserEvents> createUserEvents(@Valid @RequestBody UserEvents userEvents) throws URISyntaxException {
        log.debug("REST request to save UserEvents : {}", userEvents);
        if (userEvents.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("userEvents", "idexists", "A new userEvents cannot already have an ID")).body(null);
        }
        UserEvents result = userEventsService.save(userEvents);
        return ResponseEntity.created(new URI("/api/user-events/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("userEvents", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /user-events : Updates an existing userEvents.
     *
     * @param userEvents the userEvents to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated userEvents,
     * or with status 400 (Bad Request) if the userEvents is not valid,
     * or with status 500 (Internal Server Error) if the userEvents couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/user-events")
    @Timed
    public ResponseEntity<UserEvents> updateUserEvents(@Valid @RequestBody UserEvents userEvents) throws URISyntaxException {
        log.debug("REST request to update UserEvents : {}", userEvents);
        if (userEvents.getId() == null) {
            return createUserEvents(userEvents);
        }
        UserEvents result = userEventsService.save(userEvents);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("userEvents", userEvents.getId().toString()))
            .body(result);
    }

    /**
     * GET  /user-events : get all the userEvents.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of userEvents in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/user-events")
    @Timed
    public ResponseEntity<List<UserEvents>> getAllUserEvents(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of UserEvents");
        Page<UserEvents> page = userEventsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/user-events");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /user-events/:id : get the "id" userEvents.
     *
     * @param id the id of the userEvents to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the userEvents, or with status 404 (Not Found)
     */
    @GetMapping("/user-events/{id}")
    @Timed
    public ResponseEntity<UserEvents> getUserEvents(@PathVariable Long id) {
        log.debug("REST request to get UserEvents : {}", id);
        UserEvents userEvents = userEventsService.findOne(id);
        return Optional.ofNullable(userEvents)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /user-events/:id : delete the "id" userEvents.
     *
     * @param id the id of the userEvents to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/user-events/{id}")
    @Timed
    public ResponseEntity<Void> deleteUserEvents(@PathVariable Long id) {
        log.debug("REST request to delete UserEvents : {}", id);
        userEventsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("userEvents", id.toString())).build();
    }

}
