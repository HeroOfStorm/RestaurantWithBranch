package com.company;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class Reservation {
    private static final Set<Reservation> allReservations = new TreeSet<>(Comparator.comparing(t -> t.startTime));
    private static final double reservationCost = 25;
    private LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final Client client;
    private final Cook cook;
    private double price;


    private Reservation(@NotNull Client client, @NotNull Cook cook, @NotNull LocalDateTime startTime) throws Exception {
        this.client = client;
        this.cook = cook;
        this.startTime = startTime;
        this.endTime = this.startTime.plusHours(2);
        if (this.client.getReservations().size()>2)
            setPrice(reservationCost - (reservationCost*0.2));
        else
            setPrice(reservationCost);
    }

    public static Reservation tryToReserve(@NotNull Client client, @NotNull Cook cook, @NotNull LocalDateTime startTime) throws Exception {
        Reservation reservation = new Reservation(client, cook, startTime);
        if (!allReservations.contains(reservation)){
            allReservations.add(reservation);
            reservation.cook.addReservation(reservation);
            reservation.client.addReservation(reservation);
            return reservation;
        }
        else
            throw new Exception("This time is already reserved");
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

    public void setPrice(double price) throws Exception {
        if(price > 0)
            this.price = price;
        else
            throw new Exception("price can't be set to  0($)");
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

    public Cook getTable() {
        return cook;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", client=" + client +
                ", table=" + cook +
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
                this.cook.equals(reservation.getTable()) &&
                this.price == reservation.getPrice();
    }

}
