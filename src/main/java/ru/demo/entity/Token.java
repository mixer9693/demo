package ru.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tokens")
@Data
public class Token {
    @Id
    @SequenceGenerator(name = "token_seq", sequenceName = "token_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "token_seq")
    private Integer id;
    private String refreshToken;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
