package com.goodmeal.entities;

import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiRelation;
import io.crnk.core.resource.annotations.JsonApiResource;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@JsonApiResource(type = "healthDietLabel")
@Entity
@Table(name = "Health_Diet_Labels", schema = "goodmeal")
@Getter
@Setter
public class HealthDietLabel {
    public static final String DEFAULT_HEALTHS_NAME = "No healths label";
    public static final String DEFAULT_CAUTIONS_NAME = "No cautions label";
    public static final String DEFAULT_DIET_NAME = "No diet label";

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

    public HealthDietLabel() {
    }
}
