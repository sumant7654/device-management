package dev.sumantakumar.devicemanagement.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class UpdateDto {
    private String name;
    private String brand;
    private String version;
    @Pattern(regexp = "^(WIRELESS|WIRED)$", message = "Please provide valid device type i.e. WIRELESS or WIRED")
    private String connectivity;
    private String model;
    @Pattern(regexp = "^(ROUTER|SWITCH)$", message = "Please provide valid device type i.e. ROUTER or SWITCH")
    private String type;

}
