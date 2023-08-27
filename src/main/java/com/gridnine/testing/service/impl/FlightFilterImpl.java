package com.gridnine.testing.service.impl;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import com.gridnine.testing.service.FlightFilter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FlightFilterImpl implements FlightFilter {
    @Override
    public List<Flight> departureBeforeTheCurrentTime(List<Flight> flights) {
        List<Flight> flightList = new ArrayList<>();
        for (Flight f : flights) {
            boolean flag = true;
            for (Segment s : f.getSegments()) {
                if (s.getDepartureDate().isBefore(LocalDateTime.now())) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                flightList.add(f);
            }
        }
        return flightList;
    }
    @Override
    public List<Flight> arrivalBeforeDeparture(List<Flight> flights) {
        List<Flight> flightList = new ArrayList<>();
        for (Flight f : flights) {
            boolean flag = true;
            for (Segment s : f.getSegments()) {
                if (s.getDepartureDate().isAfter(s.getArrivalDate())) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                flightList.add(f);
            }
        }
        return flightList;
    }
    @Override
    public List<Flight> longTimeSpentOnEarth(List<Flight> flights) {
        List<Flight> flightList = new ArrayList<>();
        for (Flight f : flights) {
            long hours = 0L;
            List<Segment> segmentList = f.getSegments();
            int segmentCount = segmentList.size();
            if (segmentCount < 2) {
                flightList.add(f);
                continue;
            }
            for (int i = 0; i < segmentCount; i++) {
                if (i < segmentCount - 1) {
                    long duration = Duration.between(segmentList.get(i).getArrivalDate(), segmentList.get(i + 1).getDepartureDate()).toMillis();
                    hours += TimeUnit.MILLISECONDS.toHours(duration);
                }
            }
            if (hours <= 2) {
                flightList.add(f);
            }
        }
        return flightList;
    }
}
