package uwave.bus.station.controller;

import uwave.bus.station.config.BusInfoDataConfig;
import uwave.bus.station.dto.*;
import uwave.bus.station.exception.ErrorInfo;
import uwave.bus.station.exception.SystemRuntimeException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/bus-station")
public class BusStationController implements IBusStationController {

    final RestTemplate restTemplate;
    final BusInfoDataConfig busInfoDataConfig;

    BusStationController(RestTemplateBuilder restTemplateBuilder, BusInfoDataConfig busInfoDataConfig) {
        this.restTemplate = restTemplateBuilder.build();
        this.busInfoDataConfig = busInfoDataConfig;
    }

    @Override
    public ResponseEntity<BusLinesResponse> getBusLines() {
        try {
            var url = String.format(busInfoDataConfig.getAvailableBusLine());
            return ResponseEntity.ok(restTemplate.getForObject(url, BusLinesResponse.class));
        } catch (RestClientException e) {
            throw new SystemRuntimeException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorInfo.ENTITY_QUERY_FAILED, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<BusLocationsResponse> getBusLocationsInfo(String busLineId) {
        try {
            var url = String.format(busInfoDataConfig.getBusLocations(), busLineId);
            return ResponseEntity.ok(restTemplate.getForObject(url, BusLocationsResponse.class));
        } catch (RestClientException e) {
            throw new SystemRuntimeException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorInfo.ENTITY_QUERY_FAILED, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<BusLineInfoResponse> getBusLineInfo(String busLineId) {
        try {
            var url = String.format(busInfoDataConfig.getAvailableBusLine());
            var buslinesInfo = restTemplate.getForObject(url, BusLinesResponse.class);
            var buslineInfo = buslinesInfo.getPayload().stream().filter(p -> p.getId().equals(busLineId)).findFirst();
            if (buslineInfo.isPresent()) {
                return ResponseEntity.ok(BusLineInfoResponse.builder()
                        .payload(BusLineInfoResponse.Data_4.builder()
                                .busStops(buslineInfo.get().getBusStops())
                                .path(buslineInfo.get().getPath())
                                .id(buslineInfo.get().getId())
                                .build())
                        .build());
            } else {
                return ResponseEntity.ok(BusLineInfoResponse.builder().build());
            }
        } catch (RestClientException e) {
            throw new SystemRuntimeException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorInfo.ENTITY_QUERY_FAILED, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<BusLocationResponse> getBusLocation(String busLineId) {
        try {
            var url = String.format(busInfoDataConfig.getBusLocations(), busLineId);
            return ResponseEntity.ok(restTemplate.getForObject(url, BusLocationResponse.class));
        } catch (RestClientException e) {
            throw new SystemRuntimeException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorInfo.ENTITY_QUERY_FAILED, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<ArrivalTimeOfBusResponse> estimateArrivalTimeOfIncomingBuses(String busLineId, ArrivalTimeOfBusRequest arrivalTimeOfBusRequest) {
        try {
            var url = String.format(busInfoDataConfig.getBusLocations(), busLineId);
            var busLocations = restTemplate.getForObject(url, BusLocationsResponse.class);
            var response = ArrivalTimeOfBusResponse.builder()
                    .payload(busLocations.getPayload().stream()
                            .map(b -> ArrivalTimeOfBusResponse.Data_5.builder()
                                    .vehiclePlate(b.getVehiclePlate())
                                    .arrivalTime(Math.ceil(distance(arrivalTimeOfBusRequest.getLat(), arrivalTimeOfBusRequest.getLng(), b.getLat(), b.getLng(), "K") / b.getBearing()))
                                    .build())
                            .collect(Collectors.toList()))
                    .build();

            return ResponseEntity.ok(response);
        } catch (RestClientException e) {
            throw new SystemRuntimeException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorInfo.ENTITY_QUERY_FAILED, e.getMessage());
        }
    }

    private double distance(Double lat1, Double lon1, Double lat2, Double lon2, String unit) {
        Double theta = lon1 - lon2;
        Double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) *
                Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit.equals("K")) {
            dist = dist * 1.609344;
        } else if (unit.equals("N")) {
            dist = dist * 0.8684;
        }
        return dist;
    }

    private double deg2rad(Double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(Double rad) {
        return (rad * 180.0 / Math.PI);
    }
}
