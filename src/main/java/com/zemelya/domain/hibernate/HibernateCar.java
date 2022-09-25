package com.zemelya.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(exclude = {
        "rentalAgreements"
})
@Table(name = "cars")
public class HibernateCar {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name ="volume")
  private Double volume;

  @Column(name ="vin_number")
  private String vinNumber;

  @Column(name ="is_available")
  private Boolean isAvailable;

  @Column(name ="creation_date")
  private Timestamp creationDate;

  @Column(name ="modification_date")
  private Timestamp modificationDate;

  @Column(name ="price")
  private Double price;

  @ManyToOne
  @JoinColumn(name = "model_id")
  @JsonBackReference
  private HibernateModel model;

  @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  @JsonManagedReference
  private Set<HibernateRentalAgreement> rentalAgreements;

}
