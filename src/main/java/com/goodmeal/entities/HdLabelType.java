package com.goodmeal.entities;

import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiResource;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;



@JsonApiResource(type = "hd_label_type")
@Entity
@Table(name = "HD_Label_Types")
public class HdLabelType {

    @Id
    @JsonApiId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String type;

    @OneToMany(mappedBy = "hdLabelType")
    private Set<HealthDietLabel> labelsSet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<HealthDietLabel> getLabelsSet() {
        return labelsSet;
    }

    public void setLabelsSet(Set<HealthDietLabel> labelsSet) {
        this.labelsSet = labelsSet;
    }

    public HdLabelType(String type, Set<HealthDietLabel> labelsSet) {
        this.type = type;
        this.labelsSet = labelsSet;
    }

    public HdLabelType(){};
}
