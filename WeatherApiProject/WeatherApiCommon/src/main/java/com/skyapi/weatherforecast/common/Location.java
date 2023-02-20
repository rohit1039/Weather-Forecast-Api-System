package com.skyapi.weatherforecast.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "locations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Location {

    @Column(name = "location_code", length = 12, nullable = false, unique = true)
    @JsonProperty(value = "location_code")
    @Id
    @NotBlank(message = "locationCode is required")
    private String locationCode;

    @Column(name = "city_name", length = 128, nullable = false)
    @JsonProperty(value = "city_name")
    @NotBlank(message = "cityName is required")
    private String cityName;

    @Column(name = "region_name", length = 128)
    @JsonProperty(value = "region_name")
    private String regionName;

    @Column(name = "country_name", length = 64, nullable = false)
    @JsonProperty(value = "country_name")
    @NotBlank(message = "countryName is required")
    private String countryName;

    @Column(name = "country_code", length = 2, nullable = false)
    @JsonProperty(value = "country_code")
    @NotBlank(message = "countryCode is required")
    private String countryCode;

    @JsonProperty(value = "enabled")
    private boolean enabled;

    @JsonIgnore
    private boolean trashed;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Location location = (Location) o;
        return enabled == location.enabled && trashed == location.trashed && locationCode.equals(
                location.locationCode) && cityName.equals(location.cityName) && regionName.equals(
                location.regionName) && countryName.equals(location.countryName) && countryCode.equals(
                location.countryCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationCode, cityName, regionName, countryName, countryCode, enabled, trashed);
    }
}