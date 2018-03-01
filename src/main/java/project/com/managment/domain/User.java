package project.com.managment.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import javax.persistence.*;


@Data
@Entity
@NoArgsConstructor
@Table(name = "users",uniqueConstraints={@UniqueConstraint(columnNames = {"idNumber"})})
public class User {
    public User( String firstName, String lastName, Role role, Boolean active, String idNumber, Date createdDate, int payDay, int additionHourPay) {

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

    private Boolean active;
    @Column(nullable = false)
    private String idNumber;
    private Date createdDate;
    private int payDay;
    private int additionHourPay;
    private String password;

}
