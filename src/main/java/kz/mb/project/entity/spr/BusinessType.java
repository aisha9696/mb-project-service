package kz.mb.project.entity.spr;

import java.io.Serializable;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kz.mb.project.entity.AbstractLanguageSprValue;

@Entity
@Table(name = "business_type", schema = "spr")
@Getter
@Setter
public class BusinessType extends AbstractLanguageSprValue implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

}

