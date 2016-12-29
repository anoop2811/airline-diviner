package com.cquent.airline.service;

import com.cquent.airline.domain.UserEvents;
import com.cquent.airline.repository.UserEventsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing UserEvents.
 */
@Service
@Transactional
public class UserEventsService {

    private final Logger log = LoggerFactory.getLogger(UserEventsService.class);
    
    @Inject
    private UserEventsRepository userEventsRepository;

    /**
     * Save a userEvents.
     *
     * @param userEvents the entity to save
     * @return the persisted entity
     */
    public UserEvents save(UserEvents userEvents) {
        log.debug("Request to save UserEvents : {}", userEvents);
        UserEvents result = userEventsRepository.save(userEvents);
        return result;
    }

    /**
     *  Get all the userEvents.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<UserEvents> findAll(Pageable pageable) {
        log.debug("Request to get all UserEvents");
        Page<UserEvents> result = userEventsRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one userEvents by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public UserEvents findOne(Long id) {
        log.debug("Request to get UserEvents : {}", id);
        UserEvents userEvents = userEventsRepository.findOne(id);
        return userEvents;
    }

    /**
     *  Delete the  userEvents by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete UserEvents : {}", id);
        userEventsRepository.delete(id);
    }
}
