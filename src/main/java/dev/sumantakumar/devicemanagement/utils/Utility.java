package dev.sumantakumar.devicemanagement.utils;

import dev.sumantakumar.devicemanagement.dto.DeviceDto;
import dev.sumantakumar.devicemanagement.entity.Device;
import dev.sumantakumar.devicemanagement.response.DeviceResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Utility {
    public static final String STATUS_SUCCESS = "SUCCESS";
    public static final String STATUS_FAILED = "FAILED";

    public static DeviceResponse objectMapping(Device device){
        log.info("Request to convert Device object to response : "+device);
        DeviceResponse deviceResponse = new DeviceResponse();
        deviceResponse.setId(device.getId());
        deviceResponse.setName(device.getName());
        deviceResponse.setBrand(device.getBrand());
        deviceResponse.setVersion(device.getVersion());
        deviceResponse.setConnectivity(device.getConnectivity());
        deviceResponse.setModel(device.getModel());
        deviceResponse.setType(device.getType());
        log.info("After conversation of Device object to response : "+deviceResponse);
        return deviceResponse;
    }
    public static Device objectMapping(DeviceDto deviceDto){
        log.info("Request to convert Device DTO object to entity : "+deviceDto);
        Device device = new Device();
        device.setName(deviceDto.getName());
        device.setBrand(deviceDto.getBrand());
        device.setVersion(deviceDto.getVersion());
        device.setConnectivity(deviceDto.getConnectivity());
        device.setModel(deviceDto.getModel());
        device.setType(deviceDto.getType());
        log.info("After conversation of Device DTO object to entity : "+device);
        return device;
    }
}
