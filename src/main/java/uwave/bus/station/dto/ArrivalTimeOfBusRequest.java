package uwave.bus.station.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ArrivalTimeOfBusRequest {

    String id;

    @NotNull(message = "Lat is not null")
    Double lat;

    @NotNull(message = "Lng is not null")
    Double lng;

    String name;
}
