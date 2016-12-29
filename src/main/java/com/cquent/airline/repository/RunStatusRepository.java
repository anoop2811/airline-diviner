package com.cquent.airline.repository;

import com.cquent.airline.domain.RunStatus;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the RunStatus entity.
 */
@SuppressWarnings("unused")
public interface RunStatusRepository extends JpaRepository<RunStatus,Long> {

}
