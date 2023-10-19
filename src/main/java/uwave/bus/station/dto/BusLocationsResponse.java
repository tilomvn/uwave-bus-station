package uwave.bus.station.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class BusLocationsResponse {

    @Builder.Default
    Integer status = 1000000;

    List<Data_3> payload;

    @Builder
    @Getter
    public static class Data_3 {
        Double bearing;
        String crowdLevel;
        Double lat;
        Double lng;
        String vehiclePlate;
    }

}
