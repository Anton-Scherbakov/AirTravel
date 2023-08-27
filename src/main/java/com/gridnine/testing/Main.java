package com.gridnine.testing;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.FlightBuilder;
import com.gridnine.testing.service.FlightFilter;
import com.gridnine.testing.service.impl.FlightFilterImpl;

import java.util.List;

public class Main {
    private static final FlightFilter flightFilter = new FlightFilterImpl();

    public static void main(String[] args) {
        List<Flight> flightList = FlightBuilder.createFlights();

        System.out.println("Входные данные:");
        flightList.forEach(System.out::println);

        System.out.println("Убраны вылеты до текущего момента времени:");
        flightFilter.departureBeforeTheCurrentTime(flightList).forEach(System.out::println);

        System.out.println("Убраны вылеты, где дата прилёта раньше даты вылета:");
        flightFilter.arrivalBeforeDeparture(flightList).forEach(System.out::println);

        System.out.println("Убраны вылеты, где общее время, проведенное на земле превышает 2 часа:");
        flightFilter.longTimeSpentOnEarth(flightList).forEach(System.out::println);
    }
}