package com.alevel.java.nix.dzenmoney.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "consumption_category")
public class Consumption extends Category {

}
