package com.cquent.airline.service;

import com.cquent.airline.domain.UserPreference;
import com.cquent.airline.repository.UserPreferenceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing UserPreference.
 */
@Service
@Transactional
public class UserPreferenceService {

    private final Logger log = LoggerFactory.getLogger(UserPreferenceService.class);
    
    @Inject
    private UserPreferenceRepository userPreferenceRepository;

    /**
     * Save a userPreference.
     *
     * @param userPreference the entity to save
     * @return the persisted entity
     */
    public UserPreference save(UserPreference userPreference) {
        log.debug("Request to save UserPreference : {}", userPreference);
        UserPreference result = userPreferenceRepository.save(userPreference);
        return result;
    }

    /**
     *  Get all the userPreferences.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<UserPreference> findAll(Pageable pageable) {
        log.debug("Request to get all UserPreferences");
        Page<UserPreference> result = userPreferenceRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one userPreference by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public UserPreference findOne(Long id) {
        log.debug("Request to get UserPreference : {}", id);
        UserPreference userPreference = userPreferenceRepository.findOne(id);
        return userPreference;
    }

    /**
     *  Delete the  userPreference by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete UserPreference : {}", id);
        userPreferenceRepository.delete(id);
    }
}
