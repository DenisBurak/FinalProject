package com.zemelya.domain.hibernate;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(exclude = {
        "models"
})
@ToString(exclude = {
        "models"
})
@Table(name = "body_types")
public class HibernateBodyType {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "body_type_name")
  private String bodyTypeName;

  @Column(name = "is_available")
  private Boolean isAvailable;

  @OneToMany(mappedBy = "bodyType", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  @JsonManagedReference
  private Set<HibernateModel> models;

}
