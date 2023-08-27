package com.gridnine.testing.service;

import com.gridnine.testing.model.Flight;

import java.util.List;

public interface FlightFilter {

    List<Flight> departureBeforeTheCurrentTime(List<Flight> flights);

    List<Flight> arrivalBeforeDeparture(List<Flight> flights);

    List<Flight> longTimeSpentOnEarth(List<Flight> flights);
}
