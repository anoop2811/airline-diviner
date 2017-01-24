package com.cquent.airline.service;

import com.cquent.airline.AirlineApp;
import com.cquent.airline.checker.GoogleFlightChecker;
import com.cquent.airline.domain.UserPreference;
import com.cquent.airline.repository.AuthorityRepository;
import com.cquent.airline.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AirlineApp.class)
@Transactional
public class GoogleCheckerIntTest {
    @Inject
    private RestTemplate restTemplate;

    private GoogleFlightChecker flightChecker;

    @Before
    public void setup() {
        flightChecker = new GoogleFlightChecker();
        ReflectionTestUtils.setField(flightChecker, "restTemplate", restTemplate);

    }


    @Test
    public void testApi() {
        UserPreference u = new UserPreference();
        u.setToCity("COK");
        u.setFromCity("SFO");
        u.setDepartDate(LocalDate.now().plusDays(30));
        u.setReturnDate(LocalDate.now().plusDays(40));
        u.setThreshold(new BigDecimal("1500"));
        flightChecker.bestFlight(u);

    }

}
