package uwave.bus.station.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class BusInfoDataConfig {

    @Value("${data.available-bus-line.url}")
    String availableBusLine;

    @Value("${data.bus-locations.url}")
    String busLocations;
}
