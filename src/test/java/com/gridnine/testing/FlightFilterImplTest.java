package com.gridnine.testing;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import com.gridnine.testing.service.FlightFilter;
import com.gridnine.testing.service.impl.FlightFilterImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightFilterImplTest {
    private final List<Flight> flightList = new ArrayList<>();
    private final FlightFilter flightFilter = new FlightFilterImpl();

    @Test
    @DisplayName("When departure before the current time returns empty answer")
    void departureBeforeTheCurrentTime() {
        List<Segment> segmentList = new ArrayList<>();
        segmentList.add(new Segment(LocalDateTime.now().minusDays(3), LocalDateTime.now()));
        flightList.add(new Flight(segmentList));
        List<Flight> returns = flightFilter.departureBeforeTheCurrentTime(flightList);
        Assertions.assertEquals(new ArrayList<Flight>(), returns);
    }

    @Test
    @DisplayName("When departure after the current time returns correct answer")
    void departureAfterTheCurrentTime() {
        List<Segment> segmentList = new ArrayList<>();
        segmentList.add(new Segment(LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(3)));
        flightList.add(new Flight(segmentList));
        List<Flight> returns = flightFilter.departureBeforeTheCurrentTime(flightList);
        Assertions.assertEquals(flightList, returns);
    }

    @Test
    @DisplayName("When arrival before departure returns empty answer")
    void arrivalBeforeDeparture() {
        List<Segment> segmentList = new ArrayList<>();
        segmentList.add(new Segment(LocalDateTime.now().plusDays(3), LocalDateTime.now()));
        flightList.add(new Flight(segmentList));
        List<Flight> returns = flightFilter.arrivalBeforeDeparture(flightList);
        Assertions.assertEquals(new ArrayList<Flight>(), returns);
    }

    @Test
    @DisplayName("When arrival after departure returns correct answer")
    void arrivalAfterDeparture() {
        List<Segment> segmentList = new ArrayList<>();
        segmentList.add(new Segment(LocalDateTime.now(), LocalDateTime.now().plusDays(3)));
        flightList.add(new Flight(segmentList));
        List<Flight> returns = flightFilter.arrivalBeforeDeparture(flightList);
        Assertions.assertEquals(flightList, returns);
    }

    @Test
    @DisplayName("When the time between flights is too long returns empty answer")
    void longTimeSpentOnEarth() {
        List<Segment> segmentList = new ArrayList<>();
        segmentList.add(new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(2)));
        segmentList.add(new Segment(LocalDateTime.now().plusHours(5), LocalDateTime.now().plusHours(6)));
        flightList.add(new Flight(segmentList));
        List<Flight> returns = flightFilter.longTimeSpentOnEarth(flightList);
        Assertions.assertEquals(new ArrayList<Flight>(), returns);
    }

    @Test
    @DisplayName("When the time between flights is less than 2 hours returns correct answer")
    void notALongTimeSpentOnEarth() {
        List<Segment> segmentList = new ArrayList<>();
        segmentList.add(new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(2)));
        segmentList.add(new Segment(LocalDateTime.now().plusHours(3), LocalDateTime.now().plusHours(6)));
        flightList.add(new Flight(segmentList));
        List<Flight> returns = flightFilter.longTimeSpentOnEarth(flightList);
        Assertions.assertEquals(flightList, returns);
    }
}
