package com.company;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        Reservation reservation1, reservation2, reservation3, reservation4, reservation5, reservation6;

        try {
            KitchenHub kitchenHub1 = new KitchenHub("Al. Jerozalimske 83", "JezoHub");
            KitchenHub kitchenHub2 = new KitchenHub("Al. Stanów Zjednoczonych 10", "UnitedHub");
            KitchenHub kitchenHub3 = new KitchenHub("Ul. Kamionkowska 7", "KamionHub");

            Cook cook1 = new Cook( "Fry", "Drastik", "Fry23423@ink.com");
            Cook cook2 = new Cook( "Marthin", "King", "228Ydjas@vy.com");
            Cook cook3 = new Cook( "Hary", "Griffin", "DNWIQD@trap.com");
            Cook cook4 = new Cook( "Bezer", "Slizerin", "Hadwqq@trap.com");
            Cook cook5 = new Cook( "Heller", "McMaffin", "goggo@trap.com");
            Cook cook6 = new Cook( "Jochn", "McGaffin", "Lasddfe@trap.com");


            Client client1 = new Client("Dominik", "lasRock", "+48913049801");
            Client client2 = new Client("Joker", "Nievasno", "+480937430952");
            Client client3 = new Client("Grabi", "Tebank", "+480458329034");


            System.out.println("\n==================== (1)Qualified association (tables by ID in hub)====================");

            //////////////////////////////// (1)Qualified association (tables by ID in hub) \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
//            kitchenHub1.addCook(cook1);
            kitchenHub1.addCook(cook2);

            kitchenHub2.addCook(cook3);
            kitchenHub2.addCook(cook4);

            kitchenHub3.addCook(cook5);
            kitchenHub3.addCook(cook6);

            System.out.println("\n\t------------ (1)Tables of hub  ------------");
//            for (Cook t : kitchenHub1.getCooks().values()) {
//                System.out.println(t);
//            }

            System.out.println("\n\t------------ (1)hub of table ------------");
            System.out.println(cook5.getHubs());

            reservation1 = client1.reserveTable(cook1, LocalDateTime.now().plusDays(1));

            client1.removeReservation(reservation1);


            System.out.println("\n\t==================== (2)Association with attribute (client reserves table) ====================");

            //////////////////////////////// (2)Association with attribute (client reserves table) \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
            reservation1 = client1.reserveTable(cook1, LocalDateTime.now().plusDays(1));
            reservation2 = client2.reserveTable(cook2, LocalDateTime.now().plusDays(1));
//            reservation3 = client3.reserveTable(cook1, LocalDateTime.now().plusDays(3));
//            reservation4 = client1.reserveTable(cook1, LocalDateTime.now().plusDays(4));
//            reservation5 = client1.reserveTable(cook1, LocalDateTime.now().plusDays(5));

            System.out.println("\n\t-------------- (2)All the Reservations --------------");
            for (Reservation t : Reservation.getAllReservations()) {
                System.out.println(t);
            }

            System.out.println("\n\t-------------- (2)Reservations of client --------------");
            for (Reservation t : client1.getReservations()) {
                System.out.println(t);
            }

            System.out.println("\n\t-------------- (2)Reservations from Table --------------");
            for (Reservation t : cook1.getReservations()) {
                System.out.println(t);
            }

            Reservation.removeReservation(reservation1);

            System.out.println("\n\t-------------- (2)Reservations of client (removed one) --------------");
            for (Reservation t : client1.getReservations()) {
                System.out.println(t);
            }

            System.out.println("\n\t-------------- (2)Reservations on table (removed one) --------------");
            for (Reservation t : cook1.getReservations()) {
                System.out.println(t);
            }


            System.out.println("\n\t==================== (3)Simple association 1-*(worker in one hub) ====================");

            //////////////////////////////// (3)Simple association 1-*(worker in one hub) \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\


//            System.out.println("\n\t-------------- (3)All  hubes --------------");
//            for (KitchenHub kitchenHub : KitchenHub.getAllhubes()) {
//                System.out.println(kitchenHub);
//            }
//
//
//            System.out.println("\n\t-------------- (3)Cooks working in hub -------------- ");
//            for (Cook e : kitchenHub1.getChefs()) {
//                System.out.println(e);
//            }

            System.out.println("\n======== Ordered trainings by startTime ========");
            for (Reservation t : Reservation.getAllReservations()) {
                System.out.println(t);
            }


            System.out.println("\n======== Attribute constraint ========");
            try{
                reservation2.setPrice(0); // Error
            } catch (Exception e) {
                System.out.println("----ERROR----");
                System.out.println(e.getMessage());
            }

            System.out.println("\n======== XOR constraint ========");
            ExternalCookFirm cookFirm1 = new ExternalCookFirm("Maestro");
            ExternalCookFirm cookFirm2 = new ExternalCookFirm("Ministrelle");

//            cook1.addExternalCookFirm(cookFirm1);
//            System.out.println(cook1.getHubs() + " " + cook1.getExternalCookFirms());
//            System.out.println(cook2.getHubs() + " " + cook2.getExternalCookFirms());
//            System.out.println(cook3.getHubs() + " " + cook3.getExternalCookFirms());
//
//            try{
//                cook2.addExternalCookFirm(cookFirm2); // Error
//            } catch (Exception e) {
//                System.out.println("----ERROR----");
//                System.out.println(e.getMessage());
//            }
//            cook3.removeKitchenHub(kitchenHub2);
//            cook3.addExternalCookFirm(cookFirm2);
//
//            System.out.println("----After----");
//            System.out.println(cook1.getHubs() + " " + cook1.getExternalCookFirms());
//            System.out.println(cook2.getHubs() + " " + cook2.getExternalCookFirms());
//            System.out.println(cook3.getHubs() + " " + cook3.getExternalCookFirms());
//
//            System.out.println(cookFirm1.getCookList().stream().map(Cook::toString).collect(Collectors.joining("\n ")));
//
//
//            System.out.println("\n======== Subset constraint ========");
//            System.out.println(cook1.getHubs());
//            System.out.println(cook2.getHubs());
//            try{
//                cook1.becomeChef(kitchenHub1); // Error
//            } catch (Exception e) {
//                System.out.println("----ERROR----");
//                System.out.println(e.getMessage());
//            }
//            cook2.becomeChef(kitchenHub1);
//            System.out.println(kitchenHub1.getChefs().stream().map(Cook::toString).collect(Collectors.joining("\n ")));
//
//
//            System.out.println("\n======== Own constraint ========");
//            System.out.println(kitchenHub1.getKitchens());
//            kitchenHub1.installKitchen(); // 1
//            kitchenHub1.installKitchen(); // 2
//            kitchenHub1.installKitchen(); // 3
//            System.out.println(kitchenHub1.getKitchens());
//            kitchenHub1.installKitchen(); // 4
//            kitchenHub1.installKitchen(); // 5
//            try{
//            kitchenHub1.installKitchen(); // (6)Error
//            } catch (Exception e) {
//            System.out.println("----ERROR----");
//            System.out.println(e.getMessage());
//            }
//            System.out.println(kitchenHub1.getKitchens().size());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
