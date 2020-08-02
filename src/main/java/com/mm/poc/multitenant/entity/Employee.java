package com.mm.poc.multitenant.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "employee")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Employee implements Serializable {
  private static final long serialVersionUID = -4551953276601557391L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  private String name;
  
  public Long getId() {
    return id;
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("City{");
    sb.append("id=").append(id);
    sb.append(", name='").append(name).append('\'');
    sb.append('}');
    return sb.toString();
  }
}