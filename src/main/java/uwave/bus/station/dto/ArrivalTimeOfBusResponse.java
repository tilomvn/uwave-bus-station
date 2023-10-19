package uwave.bus.station.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ArrivalTimeOfBusResponse {

    @Builder.Default
    Integer status = 1000000;

    List<Data_5> payload;

    @Builder
    @Getter
    public static class Data_5 {
        String vehiclePlate;
        Double arrivalTime;//Hour
    }
}
