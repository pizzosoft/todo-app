package br.com.pizzosoft.entity;


import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@Entity
@Table(name = "todos")
public class Todo extends PanacheEntity {

    @Column(nullable = false)
    public String description;
    public boolean done;
    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    public ZonedDateTime createdAt;
}
