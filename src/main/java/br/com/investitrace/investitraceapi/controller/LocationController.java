package br.com.investitrace.investitraceapi.controller;

import br.com.investitrace.investitraceapi.domain.model.Location;
import br.com.investitrace.investitraceapi.service.LocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/{locationId}")
    public ResponseEntity<Location> getLocationById(@PathVariable Long locationId) {
        Location location = locationService.getLocationById(locationId);
        return ResponseEntity.ok(location);
    }

    @GetMapping
    public ResponseEntity<List<Location>> getAllLocations() {
        List<Location> locations = locationService.getAllLocations();
        return ResponseEntity.ok(locations);
    }

    @PostMapping
    public ResponseEntity<Location> createLocation(@RequestBody Location location) {
        Location createdLocation = locationService.createLocation(location);
        URI locationUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{locationId}")
                .buildAndExpand(createdLocation.getId())
                .toUri();
        return ResponseEntity.created(locationUri).body(createdLocation);
    }

    @PutMapping("/{locationId}")
    public ResponseEntity<Void> updateLocation(@PathVariable Long locationId, @RequestBody Location location) {
        location.setId(locationId);
        locationService.updateLocation(location);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{locationId}/user/{userId}")
    public ResponseEntity<Void> deletePerson(
            @PathVariable Long userId,
            @PathVariable Long locationId) {
        locationService.deleteLocation(locationId, userId);
        return ResponseEntity.noContent().build();
    }

}
