package com.cquent.airline.repository;

import com.cquent.airline.domain.UserEvents;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the UserEvents entity.
 */
@SuppressWarnings("unused")
public interface UserEventsRepository extends JpaRepository<UserEvents,Long> {

}
