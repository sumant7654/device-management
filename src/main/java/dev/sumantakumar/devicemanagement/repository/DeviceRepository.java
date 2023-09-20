package dev.sumantakumar.devicemanagement.repository;

import dev.sumantakumar.devicemanagement.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
}
