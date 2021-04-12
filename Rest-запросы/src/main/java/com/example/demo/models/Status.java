package com.example.demo.models;

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

    public long getRowId() {
        return rowId;
    }

    public void setRowId(long rowId) {
        this.rowId = rowId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<StatusTask> getStatusTasks() {
        return statusTasks;
    }

    public void setStatusTasks(Set<StatusTask> statusTasks) {
        this.statusTasks = statusTasks;
    }

    public Status(String name) {
        this.name = name;
    }
}