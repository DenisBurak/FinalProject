package com.zemelya.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "driving_licenses")
public class HibernateDrivingLicense {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name ="serial_number")
  private String serialNumber;

  @Column(name ="date_of_issue")
  private Timestamp dateOfIssue;

  @Column(name ="expiration_date")
  private Timestamp expirationDate;

  @Column(name ="category")
  private String category;

  @Column(name ="is_deleted")
  private Boolean isDeleted;

  @Column(name ="creation_date")
  private Timestamp creationDate;

  @Column(name ="modification_date")
  private Timestamp modificationDate;

  @OneToOne
  @JoinColumn(name = "user_id")
  @JsonBackReference
  private HibernateUser user;

}
