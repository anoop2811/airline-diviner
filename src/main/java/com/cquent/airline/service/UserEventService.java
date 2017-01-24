package com.cquent.airline.service;

import com.cquent.airline.domain.UserEvent;
import com.cquent.airline.repository.UserEventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing UserEvent.
 */
@Service
@Transactional
public class UserEventService {

    private final Logger log = LoggerFactory.getLogger(UserEventService.class);
    
    @Inject
    private UserEventRepository userEventRepository;

    /**
     * Save a userEvent.
     *
     * @param userEvent the entity to save
     * @return the persisted entity
     */
    public UserEvent save(UserEvent userEvent) {
        log.debug("Request to save UserEvent : {}", userEvent);
        UserEvent result = userEventRepository.save(userEvent);
        return result;
    }

    /**
     *  Get all the userEvents.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<UserEvent> findAll(Pageable pageable) {
        log.debug("Request to get all UserEvents");
        Page<UserEvent> result = userEventRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one userEvent by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public UserEvent findOne(Long id) {
        log.debug("Request to get UserEvent : {}", id);
        UserEvent userEvent = userEventRepository.findOne(id);
        return userEvent;
    }

    /**
     *  Delete the  userEvent by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete UserEvent : {}", id);
        userEventRepository.delete(id);
    }
}
