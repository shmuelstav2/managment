package project.com.managment.domain;

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


@Entity
@Table(name = "projects",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class Project {


    public Project() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //private int owner;
    private int partner;
    private String projectName;
    @Embedded
    private Address address ;
    @JsonManagedReference
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private Set<Concat> concats = new HashSet<Concat>(
            0);



    @JsonManagedReference
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private Set<Purchase> purchases = new HashSet<Purchase>(
            0);
    private String comment;



    private String imageLink;
    private int tripAhead;
    private int tripBack;
    private Boolean active;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPartner() {
        return partner;
    }

    public void setPartner(int partner) {
        this.partner = partner;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Concat> getConcats() {
        return this.concats;
    }

    public void setConcats(Set<Concat> concats) {
        this.concats = concats;
    }



    public Set<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(Set<Purchase> purchases) {
        this.purchases = purchases;
    }

    public void addConcat(Concat concat) {
        concats.add(concat);
        concat.setProject(this);
    }

    public void removeConcat(Concat concat) {
        concats.remove(concat);
        concat.setProject(null);
    }

    public void addPurcase(Purchase purchase) {
        purchases.add(purchase);
        purchase.setProject(this);
    }

    public void removePurchase(Purchase purchase) {
        purchases.remove(purchase);
        purchase.setProject(null);
    }
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
   /* public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }*/

    public int getTripAhead() {
        return tripAhead;
    }

    public void setTripAhead(int tripAhead) {
        this.tripAhead = tripAhead;
    }

    public int getTripBack() {
        return tripBack;
    }

    public void setTripBack(int tripBack) {
        this.tripBack = tripBack;
    }

    @Override
    public String toString() {
        return "Project [id=" + id + ", name=" + projectName + ", concats=" + concats
                + "]";
    }
}
