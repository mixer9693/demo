package ru.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "history_items")
@Data
public class HistoryItem {
    @Id
    @SequenceGenerator(name = "history_item_seq", sequenceName = "history_item_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "history_item_seq")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "history_item_type_id", referencedColumnName = "id")
    private HistoryItemType type;
    private String message;
    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private User author;
    private LocalDateTime dateTime;
}
