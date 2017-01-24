package com.cquent.airline.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cquent.airline.domain.UserEvent;
import com.cquent.airline.service.UserEventService;
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
 * REST controller for managing UserEvent.
 */
@RestController
@RequestMapping("/api")
public class UserEventResource {

    private final Logger log = LoggerFactory.getLogger(UserEventResource.class);
        
    @Inject
    private UserEventService userEventService;

    /**
     * POST  /user-events : Create a new userEvent.
     *
     * @param userEvent the userEvent to create
     * @return the ResponseEntity with status 201 (Created) and with body the new userEvent, or with status 400 (Bad Request) if the userEvent has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/user-events")
    @Timed
    public ResponseEntity<UserEvent> createUserEvent(@Valid @RequestBody UserEvent userEvent) throws URISyntaxException {
        log.debug("REST request to save UserEvent : {}", userEvent);
        if (userEvent.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("userEvent", "idexists", "A new userEvent cannot already have an ID")).body(null);
        }
        UserEvent result = userEventService.save(userEvent);
        return ResponseEntity.created(new URI("/api/user-events/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("userEvent", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /user-events : Updates an existing userEvent.
     *
     * @param userEvent the userEvent to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated userEvent,
     * or with status 400 (Bad Request) if the userEvent is not valid,
     * or with status 500 (Internal Server Error) if the userEvent couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/user-events")
    @Timed
    public ResponseEntity<UserEvent> updateUserEvent(@Valid @RequestBody UserEvent userEvent) throws URISyntaxException {
        log.debug("REST request to update UserEvent : {}", userEvent);
        if (userEvent.getId() == null) {
            return createUserEvent(userEvent);
        }
        UserEvent result = userEventService.save(userEvent);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("userEvent", userEvent.getId().toString()))
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
    public ResponseEntity<List<UserEvent>> getAllUserEvents(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of UserEvents");
        Page<UserEvent> page = userEventService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/user-events");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /user-events/:id : get the "id" userEvent.
     *
     * @param id the id of the userEvent to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the userEvent, or with status 404 (Not Found)
     */
    @GetMapping("/user-events/{id}")
    @Timed
    public ResponseEntity<UserEvent> getUserEvent(@PathVariable Long id) {
        log.debug("REST request to get UserEvent : {}", id);
        UserEvent userEvent = userEventService.findOne(id);
        return Optional.ofNullable(userEvent)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /user-events/:id : delete the "id" userEvent.
     *
     * @param id the id of the userEvent to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/user-events/{id}")
    @Timed
    public ResponseEntity<Void> deleteUserEvent(@PathVariable Long id) {
        log.debug("REST request to delete UserEvent : {}", id);
        userEventService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("userEvent", id.toString())).build();
    }

}
