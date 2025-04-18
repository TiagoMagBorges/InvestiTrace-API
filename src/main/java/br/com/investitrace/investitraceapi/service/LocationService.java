package br.com.investitrace.investitraceapi.service;

import br.com.investitrace.investitraceapi.domain.model.Location;

import java.util.List;

public interface LocationService {
    Location createLocation(Location location);

    void updateLocation(Location location);

    void deleteLocation(Long locationId, Long userId);

    Location getLocationById(Long locationId);

    List<Location> getAllLocations();
}
