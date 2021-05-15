package com.company;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Reservation {
    private static final Set<Reservation> allReservations = new HashSet<>();
    private static final double reservationCost = 25;
    private LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final Client client;
    private final Table table;
    private final double price;


    private Reservation(@NotNull Client client, @NotNull Table table, @NotNull LocalDateTime startTime) {
        this.client = client;
        this.table = table;
        this.startTime = startTime;
        this.endTime = this.startTime.plusHours(2);
        if (this.client.getReservations().size()>2)
            price = reservationCost - (reservationCost*0.2);
        else
            price = reservationCost;
    }

    public static Reservation tryToReserve(@NotNull Client client, @NotNull Table table, @NotNull LocalDateTime startTime) {
        Reservation reservation = new Reservation(client, table, startTime);
        if (!allReservations.contains(reservation)){
            allReservations.add(reservation);
            reservation.table.addReservation(reservation);
            reservation.client.addReservation(reservation);
            return reservation;
        }
        return null;
    }

    public static void removeReservation(@NotNull Reservation reservation) {
        if (allReservations.contains(reservation)) {
            allReservations.remove(reservation);
            reservation.getClient().removeReservation(reservation);
            reservation.getTable().removeReservation(reservation);
        }
    }

    //-------------Getters and setters-------------

    public Client getClient() {
        return client;
    }

    public double getPrice() {
        return price;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public static Set<Reservation> getAllReservations() {
        return allReservations;
    }

    public Table getTable() {
        return table;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", client=" + client +
                ", table=" + table +
                ", price=" + price +
                "}\n";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Reservation reservation = (Reservation) obj;
        return ((this.startTime.isBefore(reservation.getEndTime()) && this.startTime.isAfter(reservation.getStartTime())) &&
                (this.endTime.isBefore(reservation.getEndTime()) && this.endTime.isAfter(reservation.getStartTime()) )) &&
                this.table.equals(reservation.getTable()) &&
                this.price == reservation.getPrice();
    }

}
