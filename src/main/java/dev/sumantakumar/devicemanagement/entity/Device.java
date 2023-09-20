package dev.sumantakumar.devicemanagement.entity;

import lombok.Data;

import javax.persistence.*;


@Entity(name = "device")
@Data
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String brand;
    private String version;
    private String connectivity;
    private String model;
    private String type;
}
