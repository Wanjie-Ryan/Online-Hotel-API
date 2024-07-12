package com.hotel.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Data
@Entity
@Table(name = "ingredients_category")
@AllArgsConstructor
@NoArgsConstructor
public class IngredientCategory {

    @Id
    private UUID id;

    private String name;

    @JsonIgnore
    @ManyToOne // one restaurant can have multiple ingredient categories
    private Restaurant restaurant;

    @OneToMany(mappedBy ="category", cascade = CascadeType.ALL)
    private List<IngredientsItem> ingredients = new ArrayList<>();

    @PrePersist

    public void generateId(){
        if(this.id == null){
            this.id = UUID.randomUUID();
        }
    }
}
