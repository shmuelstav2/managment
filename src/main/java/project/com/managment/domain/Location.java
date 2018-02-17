package project.com.managment.domain;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;

@Data
@NoArgsConstructor
@Embeddable
public class Location {
  private int longitude;
  private int latitude;
}
