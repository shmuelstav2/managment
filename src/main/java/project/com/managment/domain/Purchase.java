package project.com.managment.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
        import lombok.*;

        import javax.persistence.*;
        import java.awt.*;
        import java.util.ArrayList;

        import org.hibernate.annotations.Fetch;
        import org.hibernate.annotations.FetchMode;

        import java.util.HashSet;
        import java.util.List;
        import java.util.Set;

@Data
@Entity
@Table(name = "Purchases",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class Purchase {


    public Purchase() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String operatorId;
    private  String description;
    private int  amount;
    @Enumerated(EnumType.STRING)
    private Payment payment;
    @Enumerated(EnumType.STRING)
    private PaymentDetination paymentDetination;
    private int invoiceNum;
    private String  imageLink;
    private String comment;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}