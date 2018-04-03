package project.com.managment.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;


@NoArgsConstructor



@Data
@Entity
@Table(name = "Payments",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class Payments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int sum;
    private String payer;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    private String comment;

    @JsonBackReference(value="project-payments")
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}