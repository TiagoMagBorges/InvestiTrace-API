package br.com.investitrace.investitraceapi.service.impl;

import br.com.investitrace.investitraceapi.domain.model.Location;
import br.com.investitrace.investitraceapi.domain.repository.LocationRepository;
import br.com.investitrace.investitraceapi.domain.repository.UserRepository;
import br.com.investitrace.investitraceapi.service.LocationService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    private final UserRepository userRepository;

    public LocationServiceImpl(LocationRepository locationRepository, UserRepository userRepository) {
        this.locationRepository = locationRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Location createLocation(Location location) {
        validateLocationDoesNotExist(location);
        validateUserExists(location.getUserId());
        return locationRepository.save(location);
    }

    @Override
    @Transactional
    public void updateLocation(Location location) {
        Location existingLocation = getLocationOrThrow(location.getId());

        if (!existingLocation.getUserId().equals(location.getUserId()))
            throw new IllegalArgumentException("UserId mismatch. Cannot update location with a different user.");

        existingLocation.setName(location.getName());
        existingLocation.setDescription(location.getDescription());
        existingLocation.setImageUrl(location.getImageUrl());

        locationRepository.save(existingLocation);
    }

    @Override
    @Transactional
    public void deleteLocation(Long locationId, Long userId) {
        if(!locationRepository.existsById(locationId))
            throw new EntityNotFoundException("Location not found with id: " + locationId);

        if(!userRepository.existsById(userId))
            throw new EntityNotFoundException("User not found with id: " + userId);

        locationRepository.deleteById(locationId);
    }

    @Override
    @Transactional
    public Location getLocationById(Long locationId) {
        return getLocationOrThrow(locationId);
    }

    @Override
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    private void validateLocationDoesNotExist(Location location) {
        if (locationRepository.existsById(location.getId()))
            throw new IllegalArgumentException("Location already exists with id: " + location.getId());
    }

    private void validateUserExists(Long userId) {
        if (!userRepository.existsById(userId))
            throw new IllegalArgumentException("User does not exist with id: " + userId);
    }

    private Location getLocationOrThrow(Long locationId) {
        return locationRepository.findById(locationId)
                .orElseThrow(() -> new EntityNotFoundException("Location not found with id: " + locationId));
    }
}
