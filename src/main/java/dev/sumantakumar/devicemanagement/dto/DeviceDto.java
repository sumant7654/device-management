package dev.sumantakumar.devicemanagement.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class DeviceDto {
    @NotBlank(message="Please provide device name")
    private String name;
    @NotBlank(message="Please provide device brand")
    private String brand;
    @NotBlank(message="Please provide device version")
    private String version;
    @NotBlank(message="Please provide device connectivity")
    @Pattern(regexp = "^(WIRELESS|WIRED)$", message = "Please provide valid device type i.e. WIRELESS or WIRED")
    private String connectivity;
    @NotBlank(message="Please provide device model")
    private String model;
    @NotBlank(message="Please provide device type")
    @Pattern(regexp = "^(ROUTER|SWITCH)$", message = "Please provide valid device type i.e. ROUTER or SWITCH")
    private String type;
}
