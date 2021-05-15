package com.company;


import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Table {
    private static final Set<String> allTables = new HashSet<>();
    private final List<Reservation> tableReservations = new ArrayList<>();
    private final String tableID;
    private final int seats;
    private Place place;
    private Branch location;

    public Table(int seats, @NotNull Place place) {
        this.seats = seats;
        this.place = place;
        tableID = GenerateStringTableID();
    }

    private String GenerateStringTableID(){
        String idTmp;
        do {
            String uniqueID = UUID.randomUUID().toString().substring(0, 5);
            idTmp = "TbID" + uniqueID;
        }while (allTables.contains(idTmp));

        return idTmp;
    }


    public void addReservation(@NotNull Reservation reservation) {
        if (!tableReservations.contains(reservation))
            tableReservations.add(reservation);
    }

    public void removeReservation(@NotNull Reservation reservation) {
        if(tableReservations.contains(reservation)){
            tableReservations.remove(reservation);
            Reservation.removeReservation(reservation);
        }
    }

    //-------------Getters and setters-------------

    public List<Reservation> getReservations() {
        return tableReservations;
    }

    public String getTableID() {
        return tableID;
    }

    public Branch getBranch() {
        return location;
    }

    public void setBranch(@NotNull Branch branch) {
        this.location = branch;
    }

    @Override
    public String toString() {
        return "Table{" +
                "tableReservations count=" + tableReservations.size() +
                ", tableID='" + tableID + '\'' +
                ", seats=" + seats +
                ", place=" + place +
                ", location=" + location +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Table table = (Table) o;
        return tableID.equals(table.tableID) && location.equals(table.location);
    }
}
