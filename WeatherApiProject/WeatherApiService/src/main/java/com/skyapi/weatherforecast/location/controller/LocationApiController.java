package com.skyapi.weatherforecast.location.controller;

import com.skyapi.weatherforecast.common.Location;
import com.skyapi.weatherforecast.location.exception.LocationNotFoundException;
import com.skyapi.weatherforecast.location.service.LocationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/locations")
@Validated
public class LocationApiController {

    private final LocationService locationService;

    public LocationApiController(LocationService locationService) {

        this.locationService = locationService;
    }

    @PostMapping
    public ResponseEntity<Location> addLocation(@Valid @RequestBody Location location) {

        Location addedLocation = this.locationService.addLocation(location);

        return new ResponseEntity<>(addedLocation, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Location>> getLocations() {

        List<Location> locations = this.locationService.getLocations();

        if (locations.isEmpty()) {

            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }

    @GetMapping("/{code}")
    public ResponseEntity<Location> getLocationByCode(@NotBlank @PathVariable String code) {

        Location location = this.locationService.getLocationsByCode(code);

        if (location == null) {

            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(location, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Location> updateLocation(@RequestBody @Valid Location locationInRequest) {

        try {
            Location updatedLocation = this.locationService.update(locationInRequest);

            return new ResponseEntity<>(updatedLocation, HttpStatus.OK);
        }
        catch (LocationNotFoundException exception) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<?> deleteLocation(@PathVariable("code") String code) {

        try {
            this.locationService.delete(code);

            return ResponseEntity.noContent().build();
        }
        catch (LocationNotFoundException e) {

            return ResponseEntity.notFound().build();
        }
    }

}
