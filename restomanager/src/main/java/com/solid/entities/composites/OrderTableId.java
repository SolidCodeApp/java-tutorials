package com.solid.entities.composites;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class OrderTableId {

    @Column(name = "orderId")
    private Integer orderId;

    @Column(name = "tableId")
    private Integer tableId;
}
