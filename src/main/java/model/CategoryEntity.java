package model;

import javax.persistence.*;

@Entity
@Table(name = "category")
public class CategoryEntity {

    @Id
    @Column(name = "nameid")
    private String categoryName;

    @ManyToOne
    @JoinColumn(name = "line_id")
    private LineupEntity line;

    public CategoryEntity() {
    }

    public CategoryEntity(LineupEntity line, String name) {
        this.line = line;
        this.categoryName = name;
    }

    @Override
    public String toString() {
        return categoryName;
    }

    public LineupEntity getLine() {
        return line;
    }
}
