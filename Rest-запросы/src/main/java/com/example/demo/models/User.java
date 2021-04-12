package com.example.demo.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "S_USERS")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rowId;

    @Column(nullable = false)
    private String login;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    private String middleName;
    @Column(nullable = false)
    private String passwordHash;

    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<Role> roles;

    @OneToMany(mappedBy = "developer")
    private Set<Task> developerTask;

    @OneToMany(mappedBy = "analyst")
    private Set<Task> analystTask;

    @OneToMany(mappedBy = "tester")
    private Set<Task> testerTask;

    public User(String firstName, String lastName, String middleName, String passwordHash, String login) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.passwordHash = passwordHash;
        this.login = login;
    }

    public long getRowId() {
        return rowId;
    }

    public void setRowId(long rowId) {
        this.rowId = rowId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Task> getDeveloperTask() {
        return developerTask;
    }

    public void setDeveloperTask(Set<Task> developerTask) {
        this.developerTask = developerTask;
    }

    public Set<Task> getAnalystTask() {
        return analystTask;
    }

    public void setAnalystTask(Set<Task> analystTask) {
        this.analystTask = analystTask;
    }

    public Set<Task> getTesterTask() {
        return testerTask;
    }

    public void setTesterTask(Set<Task> testerTask) {
        this.testerTask = testerTask;
    }
}