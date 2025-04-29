package com.solid.entities;

import java.time.LocalDateTime;

import com.solid.entities.composites.OrderTableId;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "restaurant_order_table")
public class OrderTableEntity {

    @EmbeddedId
    private OrderTableId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderId")
    @JoinColumn(name = "orderId")
    private OrderEntity order;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("tableId")
    @JoinColumn(name = "tableId")
    private TableEntity table;

    @Column(insertable = false, updatable = false)
    private LocalDateTime creationDate;

}
