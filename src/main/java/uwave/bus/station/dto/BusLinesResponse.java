package uwave.bus.station.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class BusLinesResponse {

    @Builder.Default
    Integer status = 1000000;

    List<Data_1> payload;

    @Builder
    @Getter
    public static class Data_1 {
        List<Data_2> busStops;
        String fullName;
        String id;
        String origin;
        List<List<Double>> path;
        String shortName;
    }

    @Builder
    @Getter
    public static class Data_2 {
        String id;
        Double lat;
        Double lng;
        String name;
    }
}
