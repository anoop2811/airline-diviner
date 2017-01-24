package com.cquent.airline.repository;

import com.cquent.airline.domain.UserEvent;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the UserEvent entity.
 */
@SuppressWarnings("unused")
public interface UserEventRepository extends JpaRepository<UserEvent,Long> {

}
