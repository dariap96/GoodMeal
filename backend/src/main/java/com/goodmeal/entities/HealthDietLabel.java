package com.goodmeal.entities;

import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiRelation;
import io.crnk.core.resource.annotations.JsonApiResource;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@JsonApiResource(type = "healthDietLabel")
@Table(name = "Health_Diet_Labels", schema = "goodmeal")
public class HealthDietLabel {

    @Getter
    public enum Defaults {
        cautions ("No cautions label"),
        diets ("No diet label"),
        healths ("No healths label");

        String title;

        Defaults(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return title;
        }
    }

    @Id
    @JsonApiId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String label;


    @ManyToOne
    @JoinColumn(name = "id_HD_Label_Types", referencedColumnName = "id")
    private HdLabelType hdLabelType;

    @JsonApiRelation
    @ManyToMany(mappedBy = "labelsSet", fetch = FetchType.LAZY)
    private Set<Recipe> recipesSet;

    public HealthDietLabel(String label, HdLabelType hdLabelType) {
        this.label = label;
        this.hdLabelType = hdLabelType;
    }

    public HealthDietLabel() {}
}
