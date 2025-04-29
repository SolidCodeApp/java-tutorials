package com.solid.entities.composites;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class OrderDishId {

    @Column(name = "orderId")
    private Integer orderId;

    @Column(name = "menuItemId")
    private Integer menuItemId;

}
