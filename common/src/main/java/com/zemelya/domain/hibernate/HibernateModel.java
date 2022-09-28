package com.zemelya.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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
        "cars"
})
@ToString(exclude = {
        "cars"
})
@Table(name = "models")
public class HibernateModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name ="model_name")
  private String modelName;

  @Column(name ="is_available")
  private Boolean isAvailable;

  @Column(name ="creation_date")
  private Timestamp creationDate;

  @Column(name ="modification_date")
  private Timestamp modificationDate;

  @ManyToOne
  @JoinColumn(name = "brand_id")
  @JsonBackReference
  private HibernateBrand brand;

  @ManyToOne
  @JoinColumn(name = "body_type_id")
  @JsonBackReference
  private HibernateBodyType bodyType;

  @OneToMany(mappedBy = "model", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  @JsonManagedReference
  private Set<HibernateCar> cars;

}
