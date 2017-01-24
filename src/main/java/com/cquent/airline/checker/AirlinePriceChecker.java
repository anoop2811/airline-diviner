package com.cquent.airline.checker;

import com.cquent.airline.domain.FlightOption;
import com.cquent.airline.domain.UserPreference;

import java.math.BigDecimal;

/**
 * Created by Anoop on 12/28/16.
 */
public interface AirlinePriceChecker {
    public FlightOption bestFlight(UserPreference userPreference);
}
