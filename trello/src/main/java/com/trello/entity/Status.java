package com.trello.trello.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "S_STATUS")
@NoArgsConstructor

public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rowId;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "status")
    private Set<StatusTask> statusTasks;

}
