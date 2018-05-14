package project.com.managment.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor



@Data
@Entity
@Table(name = "WorkerPayments",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class WorkerPayment  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int sum;
    private String comment;


    @JsonBackReference(value="manager-payments")
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private User manager;


    @JsonBackReference(value="worker-payments")
    @ManyToOne
    @JoinColumn(name = "worker_id")
    private User worker;

}