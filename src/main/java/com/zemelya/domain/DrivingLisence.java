package com.zemelya.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.sql.Timestamp;

@Setter
@Getter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DrivingLisence {

  private Long id;
  private String serialNumber;
  private Timestamp dateOfIssue;
  private Timestamp expirationDate;
  private String category;
  private Integer userId;
  private Boolean isDeleted;
  private Timestamp creationDate;
  private Timestamp modificationDate;

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }
}
