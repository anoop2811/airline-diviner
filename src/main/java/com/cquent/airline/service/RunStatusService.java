package com.cquent.airline.service;

import com.cquent.airline.domain.RunStatus;
import com.cquent.airline.repository.RunStatusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing RunStatus.
 */
@Service
@Transactional
public class RunStatusService {

    private final Logger log = LoggerFactory.getLogger(RunStatusService.class);
    
    @Inject
    private RunStatusRepository runStatusRepository;

    /**
     * Save a runStatus.
     *
     * @param runStatus the entity to save
     * @return the persisted entity
     */
    public RunStatus save(RunStatus runStatus) {
        log.debug("Request to save RunStatus : {}", runStatus);
        RunStatus result = runStatusRepository.save(runStatus);
        return result;
    }

    /**
     *  Get all the runStatuses.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<RunStatus> findAll(Pageable pageable) {
        log.debug("Request to get all RunStatuses");
        Page<RunStatus> result = runStatusRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one runStatus by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public RunStatus findOne(Long id) {
        log.debug("Request to get RunStatus : {}", id);
        RunStatus runStatus = runStatusRepository.findOne(id);
        return runStatus;
    }

    /**
     *  Delete the  runStatus by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete RunStatus : {}", id);
        runStatusRepository.delete(id);
    }
}
