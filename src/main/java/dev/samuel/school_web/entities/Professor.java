package dev.samuel.school_web.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(length = 100, nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer age;
    @Column(name = "is_coordinator", nullable = false)
    private boolean isCoordinator;
    @CreatedDate
    private LocalDateTime created;
    @LastModifiedDate
    private LocalDateTime updated;

    @JsonIgnore
    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL)
    private final Set<Classroom> classrooms = new HashSet<>();

    public Professor() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean isCoordinator() {
        return isCoordinator;
    }

    public void setCoordinator(boolean coordinator) {
        isCoordinator = coordinator;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Professor professor = (Professor) o;
        return isCoordinator == professor.isCoordinator && Objects.equals(id, professor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isCoordinator);
    }

    @Override
    public String toString() {
        return "Professor name:" + name + ", age: " + age + ", isCoordinator: " + isCoordinator;
    }
}
