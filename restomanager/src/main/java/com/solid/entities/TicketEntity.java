package com.solid.entities;

import java.time.LocalDateTime;

import com.solid.enums.EClientPriority;

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
@Table(name = "restaurant_ticket", uniqueConstraints = {
        @UniqueConstraint(name = "UK_ticket_restaurantId_identifier", columnNames = { "restaurantId", "identifier" })
})
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(insertable = false, updatable = true)
    private Boolean hasOrdered;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EClientPriority priority;

    @Column(nullable = false, columnDefinition = "char(6)")
    private String identifier;

    @Column(insertable = false, updatable = false)
    private LocalDateTime creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurantId", nullable = false)
    private RestaurantEntity restaurant;
}
