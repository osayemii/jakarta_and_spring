package com.example.demo;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.ArrayList;
import java.util.List;
import static org.springframework.web.reactive.function.server.ServerResponse.notFound;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static org.springframework.web.reactive.function.server.ServerResponse.created;
// import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Component
public class DepartureHandler {
    private final List<Departure> departureList = new ArrayList<>();
    private long nextId = 1; // Counter for generating unique IDs

    public DepartureHandler() {
        // Initialize sample data
        departureList.add(new Departure(nextId++, "British Airways", "ABC123", "CityA", "CityB", "9.20AM", "5", 150));
        departureList.add(new Departure(nextId++, "Singapore", "DEF456", "CityB", "CityC", "10.30AM", "2", 200));
        departureList.add(new Departure(nextId++, "Air France", "GHI789", "CityC", "CityD", "11.45AM", "50 min", 180));
        departureList.add(new Departure(nextId++, "Vistara", "JKL012", "CityD", "CityE", "1.00PM", "3", 120));
    }

    public Mono<ServerResponse> getAllDepartures(ServerRequest request) {
        return ok().contentType(MediaType.APPLICATION_JSON)
                .body(Flux.fromIterable(departureList),
                        Departure.class);
    }

    public Mono<ServerResponse> getDeparture(ServerRequest request) {
        long departureId = Long.parseLong(request.pathVariable("id"));
        for (Departure departure : departureList) {
            if (departure.getId() == departureId) {
                return ok().contentType(MediaType.APPLICATION_JSON)
                        .body(Mono.just(departure),
                                Departure.class);
            }
        }
        return notFound().build();
    }

    public Mono<ServerResponse> createDeparture(ServerRequest request) {
        return request.bodyToMono(Departure.class).flatMap(departure -> {
            departure.setId(nextId++);
            // Set the properties of the Departure object
            departure.setAirlines(departure.getAirlines());
            departure.setFlightNumber(departure.getFlightNumber());
            departure.setSourceCity(departure.getSourceCity());
            departure.setDestinationCity(departure.getDestinationCity());
            departure.setStartTime(departure.getStartTime());
            departure.setDuration(departure.getDuration());
            departure.setNumberOfSeats(departure.getNumberOfSeats());

            departureList.add(departure);
            return created(request.uriBuilder().pathSegment(String.valueOf(departure.getId())).build())
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Mono.just(departure),
                            Departure.class);
        }).switchIfEmpty(ServerResponse.badRequest().build());
        // Handle empty request body
    }

    public Mono<ServerResponse> updateDeparture(ServerRequest request) {
        long departureId = Long.parseLong(request.pathVariable("id"));
        return request.bodyToMono(Departure.class).flatMap(updatedDeparture -> {
            for (Departure departure : departureList) {
                if (departure.getId() == departureId) {

                    departure.setAirlines(updatedDeparture.getAirlines());
                    departure.setFlightNumber(updatedDeparture.getFlightNumber());
                    departure.setSourceCity(updatedDeparture.getSourceCity());
                    departure.setDestinationCity(updatedDeparture.getDestinationCity());
                    departure.setStartTime(updatedDeparture.getStartTime()); // Update startTime directly
                    departure.setDuration(updatedDeparture.getDuration());
                    departure.setNumberOfSeats(updatedDeparture.getNumberOfSeats());

                    return ok().contentType(MediaType.APPLICATION_JSON)
                            .body(Mono.just(departure),
                                    Departure.class);
                }
            }
            return notFound().build();
        });
    }

    public Mono<ServerResponse> deleteDeparture(ServerRequest request) {
        long departureId = Long.parseLong(request.pathVariable("id"));
        for (Departure departure : departureList) {
            if (departure.getId() == departureId) {
                departureList.remove(departure);
                return ServerResponse.noContent().build();
            }
        }
        return notFound().build();
    }
}