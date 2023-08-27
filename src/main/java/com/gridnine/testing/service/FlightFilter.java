package com.gridnine.testing.service;

import com.gridnine.testing.model.Flight;

import java.util.List;

public interface FlightFilter {
    /**
     * Method of displaying departures to the current point in time.
     * @param flights list of flights
     * @return filtered list of flights
     */
    List<Flight> departureBeforeTheCurrentTime(List<Flight> flights);

    /**
     * Method of displaying flights, with arrival date earlier than departure date.
     * @param flights list of flights
     * @return filtered list of flights
     */
    List<Flight> arrivalBeforeDeparture(List<Flight> flights);

    /**
     * Method of displaying flights, time spent on the ground more than two hours.
     * @param flights list of flights
     * @return filtered list of flights
     */
    List<Flight> longTimeSpentOnEarth(List<Flight> flights);
}
