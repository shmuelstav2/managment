package project.com.managment.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


import lombok.Data;
        import lombok.NoArgsConstructor;

        import java.util.Date;

        import javax.persistence.*;


@Data
@Entity
@NoArgsConstructor
@Table(name = "projects",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int owner;
    private int partner;
    private String projectName;
    @Embedded
    private Address address ;
}
   // private address Address
    //private comment;
      //  image Image
   /*entity Address{
        street String
        homeNumber Integer
        city String
        location Location
    }
  /* entity Location{
        east Integer
        south Integer
    }*/
