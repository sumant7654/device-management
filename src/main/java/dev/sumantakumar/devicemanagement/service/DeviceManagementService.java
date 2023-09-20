package dev.sumantakumar.devicemanagement.service;

import dev.sumantakumar.devicemanagement.dto.DeviceDto;
import dev.sumantakumar.devicemanagement.dto.UpdateDto;
import dev.sumantakumar.devicemanagement.entity.Device;
import dev.sumantakumar.devicemanagement.response.DeviceRemoveResponse;
import dev.sumantakumar.devicemanagement.response.DeviceResponse;

import java.util.List;
import java.util.Map;

public interface DeviceManagementService {
    List<DeviceResponse> getAllDevice();

    DeviceResponse createDevice(DeviceDto deviceDto);

    DeviceResponse getDeviceById(String id);

    DeviceResponse updateDevice(Long aLong, UpdateDto updateDto);

    DeviceRemoveResponse removeDevice(Long aLong);

    Map<String, Object> getStatistics();
}
