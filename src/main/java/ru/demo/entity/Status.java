package ru.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "statuses")
@Data
public class Status {
    @Id
    @SequenceGenerator(name = "status_seq", sequenceName = "status_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "status_seq")
    private Integer id;
    private String name;
}
