package uwave.bus.station.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class BusLineInfoResponse {

    @Builder.Default
    Integer status = 1000000;

    Data_4 payload;

    @Builder
    @Getter
    public static class Data_4 {
        String id;
        List<BusLinesResponse.Data_2> busStops;
        List<List<Double>> path;//bus lines
    }
}
