package dev.sumantakumar.devicemanagement.response;
import lombok.Data;

@Data
public class DeviceResponse {
    private Long id;
    private String name;
    private String brand;
    private String version;
    private String connectivity;
    private String model;
    private String type;
}
