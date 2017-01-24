package com.cquent.airline.repository;

import com.cquent.airline.AirlineApp;
import com.cquent.airline.domain.UserPreference;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by Anoop on 1/8/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AirlineApp.class)
@Transactional
public class UserPreferencesRepositoryTest {
    @Autowired
    UserPreferenceRepository userPreferenceRepository;


    @Before
    public void setUp() {

        for (int i =0; i < 55; i++) {
            UserPreference u = new UserPreference();
            u.setToCity("COK");
            u.setDepartDate(LocalDate.now().plusDays(i));
            u.setReturnDate(LocalDate.now().plusDays(i+2));
            u.setThreshold(new BigDecimal("1500"));
            u.setFromCity("FROM"+i);
            u.setNextRunDate(LocalDate.now().plusDays(i));
            userPreferenceRepository.save(u);
        }
        List<UserPreference> prefs = userPreferenceRepository.findAll();
        assertThat(prefs.size()).isEqualTo(55);

    }

    @Test
    public void testFirst50() {
        Set<UserPreference> prefs = userPreferenceRepository.findFirst50ByOrderByNextRunDateAsc();
        for(UserPreference userPref: prefs) {
            System.out.println("====>" + userPref.getNextRunDate().format(DateTimeFormatter.ofPattern("MM-dd-yyyy")));
        }
        assertThat(prefs.size()).isEqualTo(50);
        assertThat(prefs.iterator().next().getNextRunDate()).isEqualTo(LocalDate.now());
    }

    @After
    public void tearDown() {
        userPreferenceRepository.deleteAll();
    }


}
