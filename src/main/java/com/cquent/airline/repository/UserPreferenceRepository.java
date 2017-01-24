package com.cquent.airline.repository;

import com.cquent.airline.domain.UserPreference;

import org.springframework.data.jpa.repository.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * Spring Data JPA repository for the UserPreference entity.
 */
@SuppressWarnings("unused")
public interface UserPreferenceRepository extends JpaRepository<UserPreference,Long> {

    @Query("select userPreference from UserPreference userPreference where userPreference.user.login = ?#{principal.username}")
    List<UserPreference> findByUserIsCurrentUser();

    Set<UserPreference> findFirst50ByOrderByNextRunDateAsc();


}
