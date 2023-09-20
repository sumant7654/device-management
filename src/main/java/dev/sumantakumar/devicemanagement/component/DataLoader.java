package dev.sumantakumar.devicemanagement.component;

import dev.sumantakumar.devicemanagement.entity.Device;
import dev.sumantakumar.devicemanagement.repository.DeviceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class DataLoader implements CommandLineRunner {

    private DeviceRepository deviceRepository;

    @Autowired
    public void setDeviceRepository(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    /**
     * Use to load the data into database for First Time
     */
    @Override
    public void run(String... args) {
        log.info("Device Initializing . . . ...");
        List<Device> all = deviceRepository.findAll();
        if (all.isEmpty()) {
            List<Device> data = new ArrayList<>();
            populateDevices(data);
            List<Device> devices = deviceRepository.saveAll(data);
            log.info("Device initialization success : " + devices);
        } else {
            log.info("Device already initialized : " + all);
        }
    }

    private void populateDevices(List<Device> data) {
        Device switch0 = new Device();
        switch0.setName("Engenius Network Switch");
        switch0.setBrand("Engenius");
        switch0.setModel("EWS2928FP-FIT");
        switch0.setConnectivity("WIRED");
        switch0.setVersion("v2");
        switch0.setType("SWITCH");
        data.add(switch0);

        Device switch1 = new Device();
        switch1.setName("Desktop Switch");
        switch1.setBrand("TP-LINK");
        switch1.setModel("TL-SF1005D");
        switch1.setConnectivity("WIRED");
        switch1.setVersion("v1");
        switch1.setType("SWITCH");
        data.add(switch1);

        Device router0 = new Device();
        router0.setName("SIM Based Wireless Router");
        router0.setBrand("D-LINK");
        router0.setModel("N300 DIR-615");
        router0.setConnectivity("WWIRELESS");
        router0.setVersion("v2");
        router0.setType("ROUTER");
        data.add(router0);

        Device router1 = new Device();
        router1.setName("Wireless Router");
        router1.setBrand("TP-LINK");
        router1.setModel("TL-WR820N");
        router1.setConnectivity("WIRELESS");
        router1.setVersion("v1");
        router1.setType("ROUTER");
        data.add(router1);
    }
}
