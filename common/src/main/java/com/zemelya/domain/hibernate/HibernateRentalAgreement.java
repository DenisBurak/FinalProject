package com.zemelya.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "rental_agreements")
public class HibernateRentalAgreement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rental_start_date")
    private Timestamp rentalStartDate;

    @Column(name = "total_cost")
    private Double totalCost;

    @Column(name = "creation_date")
    private Timestamp creationDate;

    @Column(name = "modification_date")
    private Timestamp modificationDate;

    @Column(name = "expiration_date")
    private Timestamp expirationDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private HibernateUser user;

    @ManyToOne
    @JoinColumn(name = "car_id")
    @JsonBackReference
    private HibernateCar car;


}
