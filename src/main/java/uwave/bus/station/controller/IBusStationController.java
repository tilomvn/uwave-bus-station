package uwave.bus.station.controller;

import uwave.bus.station.dto.*;
import uwave.bus.station.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface IBusStationController {

    @Operation(summary = "Get Bus Lines Info", tags = { "Explore Data From The External API Provider" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = BusLinesResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class)) })
    })
    @RequestMapping(path = "/bus-lines", method = { RequestMethod.GET })
    ResponseEntity<BusLinesResponse> getBusLines();


    @Operation(summary = "Get Bus Locations", tags = { "Explore Data From The External API Provider" },
            parameters = {
                @Parameter(in = ParameterIn.PATH, name = "busLineId", description = "Bus Line Id",
                    examples = {
                            @ExampleObject(name = "Bus Line Id", value = "44478")
                    }
                )
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = BusLocationsResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class)) })
    })
    @RequestMapping(path = "/bus-locations/{busLineId}", method = { RequestMethod.GET })
    ResponseEntity<BusLocationsResponse> getBusLocationsInfo(@PathVariable("busLineId") String busLineId);


    @Operation(summary = "Relevant information of the bus stops and bus lines", tags = { "Plan a bus-timing backend service with the required endpoints" },
            parameters = {
                    @Parameter(in = ParameterIn.PATH, name = "busLineId", description = "Bus Line Id",
                            examples = {
                                    @ExampleObject(name = "Bus Line Id", value = "44478")
                            }
                    )
            })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = BusLineInfoResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class)) })
    })
    @RequestMapping(path = "/bus-line/{busLineId}", method = { RequestMethod.GET })
    ResponseEntity<BusLineInfoResponse> getBusLineInfo(@PathVariable("busLineId") String busLineId);


    @Operation(summary = "Locations of the running buses in the requested bus line", tags = { "Plan a bus-timing backend service with the required endpoints" },
            parameters = {
                    @Parameter(in = ParameterIn.PATH, name = "busLineId", description = "Bus Line Id",
                            examples = {
                                    @ExampleObject(name = "Bus Line Id", value = "44478")
                            }
                    )
            })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = BusLocationResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class)) })
    })
    @RequestMapping(path = "/bus-location/{busLineId}", method = { RequestMethod.GET })
    ResponseEntity<BusLocationResponse> getBusLocation(@PathVariable("busLineId") String busLineId);

    @Operation(summary = "Estimated arrival time/duration of the incoming buses at the requested bus stop", tags = { "Plan a bus-timing backend service with the required endpoints" },
            parameters = {
                    @Parameter(in = ParameterIn.PATH, name = "busLineId", description = "Bus Line Id",
                            examples = {
                                    @ExampleObject(name = "Bus Line Id", value = "44478")
                            }
                    )
            })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ArrivalTimeOfBusResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class)) })
    })
    @RequestMapping(path = "/{busLineId}/arrival-time-of-bus", method = { RequestMethod.POST })
    ResponseEntity<ArrivalTimeOfBusResponse> estimateArrivalTimeOfIncomingBuses(
            @PathVariable("busLineId") String busLineId,
            @RequestBody ArrivalTimeOfBusRequest arrivalTimeOfBusRequest
    );
}
