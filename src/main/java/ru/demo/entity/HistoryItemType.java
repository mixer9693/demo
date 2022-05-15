package ru.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "history_item_types")
@Data
public class HistoryItemType {
    @Id
    @SequenceGenerator(name = "history_item_type_seq", sequenceName = "history_item_type_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "history_item_type_seq")
    private Integer id;
    private String name;
}
