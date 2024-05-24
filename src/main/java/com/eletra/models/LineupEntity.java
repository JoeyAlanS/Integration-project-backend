package com.eletra.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "linesup")
public class LineupEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Column(name = "name")
    private String lineName;

    public LineupEntity() {
    }

    public Short getId() {
        return id;
    }

    public String getLineName() {
        return lineName;
    }

    public Short setId(Short id) {
        this.id = id;
        return id;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    @Override
    public String toString() {
        return "LineupEntity{" +
                "id=" + id +
                ", lineName='" + lineName + '\'' +
                '}';
    }
}