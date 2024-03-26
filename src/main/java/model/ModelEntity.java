package model;

import javax.persistence.*;

@Entity
@Table(name = "models")
public class ModelEntity {

    @Id
    @Column(name = "nameid")
    private String modelName;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    public ModelEntity() {
    }

    public ModelEntity(CategoryEntity category, String modelName) {
        this.category = category;
        this.modelName = modelName;
    }

    @Override
    public String toString() {
        return modelName;
    }

}
