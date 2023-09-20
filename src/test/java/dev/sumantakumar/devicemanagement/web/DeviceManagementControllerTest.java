package dev.sumantakumar.devicemanagement.web;

import com.google.gson.Gson;
import dev.sumantakumar.devicemanagement.BaseTest;
import dev.sumantakumar.devicemanagement.dto.DeviceDto;
import dev.sumantakumar.devicemanagement.dto.UpdateDto;
import dev.sumantakumar.devicemanagement.utils.Utility;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class DeviceManagementControllerTest extends BaseTest {

    @Test
    public void getDeviceById() throws Exception {
        mockMvc.perform(get("/device/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Desktop Network Hub"));
    }

    @Test
    public void updateDevice() throws Exception {
        UpdateDto updateDto = new UpdateDto();
        updateDto.setConnectivity("WIRELESS");
        updateDto.setType("SWITCH");
        mockMvc.perform(put("/device/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(updateDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.connectivity").value("WIRELESS"));
    }

    @Test
    public void updateDeviceFound() throws Exception {
        mockMvc.perform(put("/device/1"))
                .andExpect(status().isBadRequest());
    }

    @Disabled
    @Test
    public void postDeviceOk() throws Exception {
        DeviceDto deviceDto = new DeviceDto();
        deviceDto.setName("Desktop Network Hub");
        deviceDto.setBrand("TP-LINK");
        deviceDto.setVersion("v4");
        deviceDto.setConnectivity("WIRED");
        deviceDto.setModel("TL-SF1008P");
        deviceDto.setType("SWITCH");
        mockMvc.perform(post("/device")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(deviceDto)))
                .andExpect(status().isOk());
    }

    @Test
    public void postDeviceFound() throws Exception {
        mockMvc.perform(post("/device"))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void getDeviceFound() throws Exception {
        mockMvc.perform(get("/device"))
                .andExpect(status().isOk());
    }

    @Test
    public void removeDevice() throws Exception {
        mockMvc.perform(delete("/device/5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(Utility.STATUS_SUCCESS));
    }

    @Test
    public void statisticFound() throws Exception {
        mockMvc.perform(get("/device/statistics"))
                .andExpect(status().isOk());

    }

    @Disabled
    @Test
    public void statisticNotFound() throws Exception {
        mockMvc.perform(get("/device/statistics"))
                .andExpect(status().isNotFound());
    }

    @Disabled
    @Test
    public void removeDeviceNotExist() throws Exception {
        mockMvc.perform(delete("/device/500"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(Utility.STATUS_FAILED));
    }

    @Disabled
    @Test
    public void removeDeviceNotFound() throws Exception {
        mockMvc.perform(delete("/device/1"))
                .andExpect(status().isForbidden());
    }

    @Disabled
    @Test
    public void updateDeviceNotFound() throws Exception {
        mockMvc.perform(put("/device/1"))
                .andExpect(status().isForbidden());
    }


    @Disabled
    @Test
    public void getDeviceByIdNotFound() throws Exception {
        mockMvc.perform(get("/device/1"))
                .andExpect(status().isNotFound());
    }

    @Disabled
    @Test
    public void postDeviceNotFound() throws Exception {
        mockMvc.perform(post("/device"))
                .andExpect(status().isMethodNotAllowed());
    }

    @Disabled
    @Test
    public void getDeviceNotFound() throws Exception {
        mockMvc.perform(get("/device"))
                .andExpect(status().isNotFound());
    }
}
