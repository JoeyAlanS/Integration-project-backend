package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "linesup")
public class LineupEntity {

    @Id
    @Column(name = "nameid")
    private String lineupName;

    public LineupEntity() {
    }

    LineupEntity(String lineupName) {

        this.lineupName = lineupName;
    }

    @Override
    public String toString() {
        return lineupName;
    }


}