package com.solid.enums;

public enum EOrderStatus {
    PENDING, // La commande est en attente.
    IN_PROGRESS, // La commande est en cours de préparation.
    READY, // La commande est prête à être servie.
    SERVED, // La commande a été servie.
    CANCELLED, // La commande a été annulée.
    COMPLETED; // La commande a été complétée.
}
