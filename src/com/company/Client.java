package com.company;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Client {
    private final List<Reservation> clientReservations = new ArrayList<>();
    private String firstName;
    private String lastName;
    private String phone;

    public Client(@NotNull String firstName, @NotNull String lastName, @NotNull String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public Reservation reserveTable(@NotNull Cook cook, @NotNull LocalDateTime startTime) throws Exception {


        return Reservation.tryToReserve(this, cook, startTime);
    }

    public void removeReservation(@NotNull Reservation reservation){
        if (clientReservations.contains(reservation)){
            clientReservations.remove(reservation);
            Reservation.removeReservation(reservation);
        }
    }

    public void addReservation(Reservation reservation) {
        if(!clientReservations.contains(reservation))
            clientReservations.add(reservation);
    }

    public List<Reservation> getReservations() {
        return clientReservations;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientReservations count=" + clientReservations.size() +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

}
