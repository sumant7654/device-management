package dev.sumantakumar.devicemanagement.response;

import dev.sumantakumar.devicemanagement.entity.Device;
import lombok.Data;

@Data
public class DeviceRemoveResponse {
    private Integer statusCode;
    private String status;
    private String message;
    private String error;
    private Device device;
}

