package project.com.managment.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;


@Data
@Entity
@NoArgsConstructor
@Table(name = "users",uniqueConstraints={@UniqueConstraint(columnNames = {"idNumber"})})
public class User {
    public User( String firstName, String lastName, Role role, Boolean active, BigInteger idNumber, Date createdDate, int payDay, int additionHourPay) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.active = active;
        this.idNumber = idNumber;
        this.createdDate = createdDate;
        this.payDay = payDay;
        this.additionHourPay = additionHourPay;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Role role;

    @JsonManagedReference(value="user-movement")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<WorkDay> workDays = new HashSet<WorkDay>(
            0);


    @JsonManagedReference(value="worker-payments")
    @OneToMany(mappedBy = "worker", cascade = CascadeType.ALL)
    private Set<WorkerPayment> workerPayments = new HashSet<WorkerPayment>(
            0);

    @JsonManagedReference(value="manager-payments")
    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
    private Set<WorkerPayment> managerPayments = new HashSet<WorkerPayment>(
            0);

    private Boolean active;
    @Column(nullable = false)
    private BigInteger idNumber;
    private Date createdDate;
    private int payDay;
    private int additionHourPay;
    private String password;

}
