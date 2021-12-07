package com.goodmeal.entities;

import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiResource;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@JsonApiResource(type = "hd_label_type")
@Table(name = "HD_Label_Types", schema = "goodmeal")
public class HdLabelType {

    public static final String CAUTIONS = "cautions";
    public static final String DIETS = "diets";
    public static final String HEALTHS = "healths";

    @Id
    @JsonApiId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String type;

    @OneToMany(mappedBy = "hdLabelType")
    private Set<HealthDietLabel> labelsSet;

    public HdLabelType(String type) {
        this.type = type;
    }

    public HdLabelType() {};
}
