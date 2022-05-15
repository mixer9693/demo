package ru.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "issues")
@Data
public class Issue {
    @Id
    @SequenceGenerator(name = "issue_seq", sequenceName = "issue_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "issue_seq")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private IssueType type;
    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private Status status;
    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private User author;
    @OneToMany(mappedBy = "issue", fetch = FetchType.LAZY)
    private List<Comment> comments;
}
