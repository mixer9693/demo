package ru.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "issue_types")
@Data
public class IssueType {
    @Id
    @SequenceGenerator(name = "issue_type_seq", sequenceName = "issue_type_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "issue_type_seq")
    private Integer id;
    private String name;
}
