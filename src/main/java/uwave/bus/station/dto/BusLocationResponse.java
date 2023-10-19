package uwave.bus.station.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class BusLocationResponse {

    @Builder.Default
    Integer status = 1000000;

    List<BusLocationsResponse.Data_3> payload;
}
