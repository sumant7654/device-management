package dev.sumantakumar.devicemanagement.service.impl;

import dev.sumantakumar.devicemanagement.dto.DeviceDto;
import dev.sumantakumar.devicemanagement.dto.UpdateDto;
import dev.sumantakumar.devicemanagement.entity.Device;
import dev.sumantakumar.devicemanagement.repository.DeviceRepository;
import dev.sumantakumar.devicemanagement.response.DeviceRemoveResponse;
import dev.sumantakumar.devicemanagement.response.DeviceResponse;
import dev.sumantakumar.devicemanagement.service.DeviceManagementService;
import dev.sumantakumar.devicemanagement.utils.Utility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Pattern;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DeviceManagementServiceImpl implements DeviceManagementService {

    private DeviceRepository deviceRepository;

    @Autowired
    public void setDeviceRepository(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public List<DeviceResponse> getAllDevice() {
        log.info("Request to retrieve all devices from database");
        try {
            List<Device> all = deviceRepository.findAll();
            return all.stream().map(Utility::objectMapping).collect(Collectors.toList());
        } catch (Exception e) {
            log.info("Exception occurred while retrieving data from database : " + e.getMessage());
            throw new RuntimeException("Internal Server Error! Please try again");
        }
    }

    @Override
    public DeviceResponse createDevice(DeviceDto deviceDto) {
        log.info("Request to create device : " + deviceDto);
        Device device = Utility.objectMapping(deviceDto);
        try {
            Device save = deviceRepository.save(device);
            return Utility.objectMapping(save);
        } catch (Exception e) {
            log.info(deviceDto + " Exception occurred while saving data into database : " + e.getMessage());
            throw new RuntimeException("Internal Server Error! Please try again");
        }
    }

    @Override
    public DeviceResponse getDeviceById(String id) {
        log.info("Request to retrieve device from database by id : " + id);
        try {
            Optional<Device> device = deviceRepository.findById(Long.valueOf(id));
            return device.map(Utility::objectMapping).orElse(null);
        } catch (Exception e) {
            log.info(id + " Exception occurred while retrieving data from database : " + e.getMessage());
            throw new RuntimeException("Internal Server Error! Please try again");
        }
    }

    @Override
    public DeviceResponse updateDevice(Long id, UpdateDto updateDto) {
        log.info(updateDto + " Request to update device in database by id : " + id);
        try {
            Optional<Device> deviceOpt = deviceRepository.findById(Long.valueOf(id));
            if (deviceOpt.isPresent()) {
                Device device = deviceOpt.get();
                getUpdatedDevice(device, updateDto);
                Device save = deviceRepository.save(device);
                return Utility.objectMapping(save);
            } else {
                return null;
            }

        } catch (Exception e) {
            log.info(id + " Exception occurred while updating data into database : " + e.getMessage());
            throw new RuntimeException("Internal Server Error! Please try again");
        }
    }

    @Override
    public DeviceRemoveResponse removeDevice(Long id) {
        log.info("Request to remove device from database by id : " + id);
        DeviceRemoveResponse response = new DeviceRemoveResponse();
        try {
            Optional<Device> device = deviceRepository.findById(Long.valueOf(id));
            if (device.isPresent()) {
                response.setDevice(device.get());
                deviceRepository.delete(device.get());
                response.setStatus(Utility.STATUS_SUCCESS);
                response.setStatusCode(1);
                response.setMessage("Device Removed Successfully.");
            } else {
                response.setStatus(Utility.STATUS_FAILED);
                response.setStatusCode(0);
                response.setMessage("Device Removed Successfully.");
            }
        } catch (Exception e) {
            log.info(id + " Exception occurred while removing device from database : " + e.getMessage());
            response.setStatus(Utility.STATUS_FAILED);
            response.setStatusCode(-1);
            response.setMessage("Internal Server Error! Please try again later");
        }
        return response;
    }

    @Override
    public Map<String, Object> getStatistics() {
        Map<String, Object> data = new HashMap<>();
        List<Device> all = deviceRepository.findAll();
        Map<String, Long> byBrand = all.stream()
                .collect(Collectors.groupingBy(Device::getBrand, Collectors.counting()));
        Map<String, Long> byType = all.stream()
                .collect(Collectors.groupingBy(Device::getType, Collectors.counting()));
        Map<String, Long> connectivity = all.stream()
                .collect(Collectors.groupingBy(Device::getConnectivity, Collectors.counting()));
        data.put("brand", byBrand);
        data.put("type", byType);
        data.put("connectivity", connectivity);
        return data;
    }

    private void getUpdatedDevice(Device device, UpdateDto updateDto) {
        if (updateDto.getName() != null && !updateDto.getName().trim().isEmpty()) {
            device.setName(updateDto.getName());
        }
        if (updateDto.getBrand() != null && !updateDto.getBrand().trim().isEmpty()) {
            device.setBrand(updateDto.getBrand());
        }
        if (updateDto.getVersion() != null && !updateDto.getVersion().trim().isEmpty()) {
            device.setVersion(updateDto.getVersion());
        }
        if (updateDto.getConnectivity() != null && !updateDto.getConnectivity().trim().isEmpty()) {
            device.setConnectivity(updateDto.getConnectivity());
        }
        if (updateDto.getModel() != null && !updateDto.getModel().trim().isEmpty()) {
            device.setModel(updateDto.getModel());
        }
        if (updateDto.getType() != null && !updateDto.getType().trim().isEmpty()) {
            device.setType(updateDto.getType());
        }
    }
}
