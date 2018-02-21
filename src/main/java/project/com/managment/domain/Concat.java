package project.com.managment.domain;

//import lombok.*;

import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.NoArgsConstructor;

import java.util.Objects;

import javax.persistence.*;



@Entity
//@NoArgsConstructor

@Table(name = "concats")
public class Concat {

    public Concat() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //private int owner;
    private String firstName;
    private String lastName;
    private int phoneNunber;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getPhoneNunber() {
        return phoneNunber;
    }

    public void setPhoneNunber(int phoneNunber) {
        this.phoneNunber = phoneNunber;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Concat concat = (Concat) o;
        return phoneNunber == concat.phoneNunber &&
                Objects.equals(id, concat.id) &&
                Objects.equals(firstName, concat.firstName) &&
                Objects.equals(lastName, concat.lastName) &&
                Objects.equals(project, concat.project);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), id, firstName, lastName, phoneNunber, project);
    }

    @Override
    public String toString() {
        return "Concat [id=" + id + ", name="
                + "]";
    }
}

