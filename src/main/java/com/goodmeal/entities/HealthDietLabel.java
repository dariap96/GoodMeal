package com.goodmeal.entities;

import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiResource;
import lombok.Getter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@JsonApiResource(type = "healthDietLabel")
@Entity
@Table(name = "Health_Diet_Labels", schema = "goodmeal")
@Getter
public class HealthDietLabel {

    @Id
    @JsonApiId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String label;

    @ManyToOne
    @JoinColumn(name = "id_HD_Label_Types", referencedColumnName = "id")
    private HdLabelType hdLabelType;

    @ManyToMany(mappedBy = "labelsSet")
    private Set<Recipe> recipesSet = new HashSet<>();

    public HealthDietLabel(String label, HdLabelType hdLabelType) {
        this.label = label;
        this.hdLabelType = hdLabelType;
    }

    public HealthDietLabel() {
    }
}
