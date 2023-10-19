package uwave.bus.station.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum ErrorInfo {

    ENTITY_QUERY_FAILED("ENTITY_QUERY_FAILED", "ENTITY_QUERY_FAILED"),
    METHOD_NOT_SUPPORTED("METHOD_NOT_SUPPORTED", "METHOD_NOT_SUPPORTED"),
    VALIDATION_ERROR("VALIDATION_ERROR", "VALIDATION_ERROR"),
    PERMISSION_DENIED("PERMISSION_DENIED", "PERMISSION_DENIED")
    ;

    final String code;
    final String message;

    ErrorInfo(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
