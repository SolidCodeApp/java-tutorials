package com.solid.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import com.solid.enums.EClientPriority;
import com.solid.enums.EOrderStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "restaurant_order", uniqueConstraints = @UniqueConstraint(name = "UK_order_restaurantId_identifier", columnNames = {
        "restaurantId", "identifier" }))
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Short nbPersons;

    @Column(nullable = false, columnDefinition = "char(6)")
    private String identifier;

    @Column(insertable = false, updatable = false)
    private LocalDateTime creationDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EOrderStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EClientPriority priority;

    @Column(precision = 15, scale = 2)
    private BigDecimal bill;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticketId", nullable = true)
    private TicketEntity ticket;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurantId", nullable = false)
    private RestaurantEntity restaurant;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<OrderDishEntity> dishes = new HashSet<>();

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<OrderTableEntity> tables = new HashSet<>();
}
