package com.cquent.airline.tasks;

import com.cquent.airline.checker.GoogleFlightChecker;
import com.cquent.airline.domain.FlightOption;
import com.cquent.airline.domain.UserEvent;
import com.cquent.airline.domain.UserEvents;
import com.cquent.airline.domain.UserPreference;
import com.cquent.airline.repository.UserEventRepository;
import com.cquent.airline.repository.UserPreferenceRepository;
import com.cquent.airline.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.CollectionUtils;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * Created by Anoop on 1/8/17.
 */


public class ScheduledChecker {

    private final Logger log = LoggerFactory.getLogger(ScheduledChecker.class);

    @Inject
    UserPreferenceRepository userPreferenceRepository;

    @Inject
    GoogleFlightChecker flightChecker;

    @Inject
    UserEventRepository userEventsRepository;

    @Inject
    MailService mailService;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void runChecker() {
        log.info("Going to run scheduled task");
        Set<UserPreference> userPreferenceSet = userPreferenceRepository.findFirst50ByOrderByNextRunDateAsc();
        for(UserPreference userPref: userPreferenceSet) {
            FlightOption flightOption = flightChecker.bestFlight(userPref);
            UserEvent event = mapToUserEvent(flightOption, userPref);
            ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("runDate");
            List<UserEvent> existingEvents = userEventsRepository.findAll(Example.of(event, matcher));
            if(CollectionUtils.isEmpty(existingEvents)) {
                log.debug("Going to save user event [ {} ]", event);
                userEventsRepository.save(event);
                if (!flightOption.getOnwardFlightPath().equals("N/A")) {
                    mailService.sendBestFlightEmail(userPref.getUser(), flightOption, userPref);
                }
            }
        }
    }


    private UserEvent mapToUserEvent(FlightOption flightOption, UserPreference userPref) {
        UserEvent event = new UserEvent();
        event.setRunDate(LocalDate.now());
        event.setUserPreference(userPref);
        event.setBestOnwardPath(flightOption.getOnwardFlightPath());
        event.setBestReturnPath(flightOption.getReturnFlightPath());
        event.setBestPrice(new BigDecimal(flightOption.getPrice().substring(3)));
        return event;
    }
}
