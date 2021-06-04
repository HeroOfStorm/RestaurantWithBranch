package com.company;


import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Cook {
    private static final Set<String> allCookId = new HashSet<>();
    private final List<Reservation> tableReservations = new ArrayList<>();

    private final List<KitchenHub> leadOfKitchenHubs = new ArrayList<>();

    private final List<KitchenHub> kitchenHubs = new ArrayList<>();
    private final List<ExternalCookFirm> externalCookFirms = new ArrayList<>();
    private String firstName;
    private String lastName;
    private String email;
    private final String cookID;

    public Cook(@NotNull String firstName, @NotNull String lastName,
                @NotNull String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        cookID = GenerateStringTableID();
    }

    private String GenerateStringTableID(){
        String idTmp;
        do {
            String uniqueID = UUID.randomUUID().toString().substring(0, 5);
            idTmp = "TbID" + uniqueID;
        }while (allCookId.contains(idTmp));
        allCookId.add(idTmp);
        return idTmp;
    }

    public void addKitchenHub(KitchenHub newKitchenHub) throws Exception {
        //XOR condition
        if (externalCookFirms.isEmpty()) {
            // Check if we have the information already
            if (!kitchenHubs.contains(newKitchenHub)) {
                kitchenHubs.add(newKitchenHub);

                // Add the reverse connection
                newKitchenHub.addCook(this);
            }
        }else
            throw new Exception("Cook can have only at one (agent)firm at time(kitchenHub xor External)");
    }

    public void removeKitchenHub(KitchenHub kitchenHubToRemove) {
        if (kitchenHubs.contains(kitchenHubToRemove)) {
            kitchenHubs.remove(kitchenHubToRemove);

            // Remove the reverse link
            kitchenHubToRemove.removeCook(this.cookID);
        }
    }


    public void addExternalCookFirm(ExternalCookFirm firmToAdd) throws Exception {
        //XOR condition
        if (kitchenHubs.isEmpty()){
            // Check if we have the information already
            if (!externalCookFirms.contains(firmToAdd)) {
                externalCookFirms.add(firmToAdd);

                // Add the reverse connection
                firmToAdd.addCook(this);

            }
        }
        else
            throw new Exception("Cook can have only at one (agent)firm at time(kitchenHub xor External)");
    }

    public void removeExternalCookFirm(ExternalCookFirm firmToRemove) {

        if (externalCookFirms.contains(firmToRemove)) {
            externalCookFirms.remove(firmToRemove);

            // Remove the reverse link
            firmToRemove.removeCook(this);
        }
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


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<KitchenHub> getLeadOfKitchenHubs() {
        return leadOfKitchenHubs;
    }

    public List<KitchenHub> getKitchenHubs() {
        return kitchenHubs;
    }

    public List<ExternalCookFirm> getExternalCookFirms() {
        return externalCookFirms;
    }

    public List<Reservation> getReservations() {
        return tableReservations;
    }

    public String getCookID() {
        return cookID;
    }

    public List<KitchenHub> getHubs() {
        return kitchenHubs;
    }


    @Override
    public String toString() {
        return "Table{" +
                "tableReservations count=" + tableReservations.size() +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", cookID='" + cookID + '\'' +
                ", email='" + email + '\'' +

                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cook cook = (Cook) o;
        return cookID.equals(cook.cookID) &&
                this.firstName.equals(cook.getFirstName()) &&
                this.lastName.equals(cook.getLastName()) &&
                this.email.equals(cook.getEmail()) ;
    }
}
