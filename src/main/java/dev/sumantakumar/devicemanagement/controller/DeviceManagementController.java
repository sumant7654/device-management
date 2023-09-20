package dev.sumantakumar.devicemanagement.controller;

import dev.sumantakumar.devicemanagement.dto.DeviceDto;
import dev.sumantakumar.devicemanagement.dto.UpdateDto;
import dev.sumantakumar.devicemanagement.response.DeviceRemoveResponse;
import dev.sumantakumar.devicemanagement.response.DeviceResponse;
import dev.sumantakumar.devicemanagement.service.DeviceManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("device")
public class DeviceManagementController {

    private DeviceManagementService deviceManagementService;

    @Autowired
    public void setDeviceManagementService(DeviceManagementService deviceManagementService) {
        this.deviceManagementService = deviceManagementService;
    }


    /**
     * 1(a).Create APIs for adding device with basic
     * information (name, version, brand, etc.)
     * */
    @PostMapping
    public ResponseEntity<DeviceResponse> createDevice(@RequestBody @Valid DeviceDto deviceDto) {
        return ResponseEntity.ok(deviceManagementService.createDevice(deviceDto));

    }

    /**
     * 1(b).Create APIs to fetch list of devices with basic
     * information (name, version, brand, etc.)
     * */
    @GetMapping
    public ResponseEntity<List<DeviceResponse>> getAllDevice() {
        return ResponseEntity.ok(deviceManagementService.getAllDevice());

    }

    /**
     *2.Create an API for fetching the entire information about a selected
     * device.
     * */
    @GetMapping("{id}")
    public ResponseEntity<DeviceResponse> getDeviceById(@PathVariable String id) {
        return ResponseEntity.ok(deviceManagementService.getDeviceById(id));

    }

    /**
     * 3.Include API for users to update existing device information.
     * */
    @PutMapping("{id}")
    public ResponseEntity<DeviceResponse> updateDevice(@PathVariable String id, @RequestBody @Valid UpdateDto updateDto) {
        return ResponseEntity.ok(deviceManagementService.updateDevice(Long.valueOf(id), updateDto));
    }

    /**
     * 4.Include API for users to delete devices.
     * */
    @DeleteMapping("{id}")
    public ResponseEntity<DeviceRemoveResponse> removeDevice(@PathVariable String id) {
        return ResponseEntity.ok(deviceManagementService.removeDevice(Long.valueOf(id)));
    }





    /**
     * 4. Create APIs to fetch statistics related to the device data (e.g.,
     * number of devices, device distribution by brand, etc.).
     * */
    @GetMapping("statistics")
    public ResponseEntity<Map<String, Object>> statistics() {
        return ResponseEntity.ok(deviceManagementService.getStatistics());
    }


}
