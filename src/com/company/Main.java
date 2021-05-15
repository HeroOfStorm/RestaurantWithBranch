package com.company;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        Reservation reservation1, reservation2, reservation3, reservation4, reservation5, reservation6;
        Waiter.Duty duty1, duty2, duty3, duty4, duty5, duty6;

        Branch branch1 = new Branch("Al. Jerozalimske 83");
        Branch branch2 = new Branch("Al. Stanów Zjednoczonych 10");
        Branch branch3 = new Branch("Ul. Kamionkowska 7");

        Table table1 = new Table(3, Place.NEAR_WALL);
        Table table2 = new Table(4, Place.NEAR_WINDOW);
        Table table3 = new Table(5, Place.CORNER);
        Table table4 = new Table(3, Place.NEAR_WINDOW);
        Table table5 = new Table(8, Place.CENTER);
        Table table6 = new Table(5, Place.CORNER);


        Client client1 = new Client("Dominik", "lasRock", "+48913049801");
        Client client2 = new Client("Joker", "Nievasno", "+480937430952");
        Client client3 = new Client("Grabi", "Tebank", "+480458329034");

        Waiter waiter1 = new Waiter("Fry", "kras", "Fry23423@ink.com", "+48912649226");
        Waiter waiter2 = new Waiter("Hary", "Griffin", "Lasddfe@trap.com", "+48975349801");
        Waiter waiter3 = new Waiter("Marthin", "King", "228Ydjas@vy.com", "+48913043463");


        System.out.println("\n==================== (1)Qualified association (tables by ID in Branch)====================");

        //////////////////////////////// (1)Qualified association (tables by ID in Branch) \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
        branch1.addTable(table1);
        branch1.addTable(table2);

        branch2.addTable(table3);
        branch2.addTable(table4);

        branch3.addTable(table5);
        branch3.addTable(table6);

        System.out.println("\n\t------------ (1)Tables of branch  ------------");
        for (Table t : branch1.getTables().values()) {
            System.out.println(t);
        }

        System.out.println("\n\t------------ (1)Branch of table ------------");
        System.out.println(table5.getBranch());

        reservation1 = client1.reserveTable(table1, LocalDateTime.now().plusDays(1));

        client1.removeReservation(reservation1);


        System.out.println("\n\t==================== (2)Association with attribute (client reserves table) ====================");

        //////////////////////////////// (2)Association with attribute (client reserves table) \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
        reservation1 = client1.reserveTable(table1, LocalDateTime.now().plusDays(1));
        reservation2 = client2.reserveTable(table2, LocalDateTime.now().plusDays(1));
        reservation3 = client3.reserveTable(table1, LocalDateTime.now().plusDays(3));
        reservation4 = client1.reserveTable(table1, LocalDateTime.now().plusDays(4));
        reservation5 = client1.reserveTable(table1, LocalDateTime.now().plusDays(5));

        System.out.println("\n\t-------------- (2)All the Reservations --------------");
        for (Reservation t : Reservation.getAllReservations()) {
            System.out.println(t);
        }

        System.out.println("\n\t-------------- (2)Reservations of client --------------");
        for (Reservation t : client1.getReservations()) {
            System.out.println(t);
        }

        System.out.println("\n\t-------------- (2)Reservations from Table --------------");
        for (Reservation t : table1.getReservations()) {
            System.out.println(t);
        }

        Reservation.removeReservation(reservation1);

        System.out.println("\n\t-------------- (2)Reservations of client (removed one) --------------");
        for (Reservation t : client1.getReservations()) {
            System.out.println(t);
        }

        System.out.println("\n\t-------------- (2)Reservations on table (removed one) --------------");
        for (Reservation t : table1.getReservations()) {
            System.out.println(t);
        }


        System.out.println("\n\t==================== (3)Simple association 1-*(worker in one Branch) ====================");

        //////////////////////////////// (3)Simple association 1-*(worker in one Branch) \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
        branch1.attachWaiter(waiter1);
        branch1.attachWaiter(waiter2);
        waiter3.attachToBranch(branch1);


        System.out.println("\n\t-------------- (3)All  Branches --------------");
        for (Branch branch : Branch.getAllBranches()) {
            System.out.println(branch);
        }


        System.out.println("\n\t-------------- (3)Waiters working in Branch -------------- ");
        for (Waiter e : branch1.getWaiters()) {
            System.out.println(e);
        }

        System.out.println("\n\t==================== (4)Composition ====================");

        //////////////////////////////// (4)Composition \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
        duty1 = Waiter.Duty.takeDuty(waiter1, LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(2).plusHours(5), "Dish washing");
        duty2 = Waiter.Duty.takeDuty(waiter1, LocalDateTime.now().plusDays(4), LocalDateTime.now().plusDays(4).plusHours(5), "Serving");
        duty3 = Waiter.Duty.takeDuty(waiter2, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(1), "Table cleaning"); // Verification of duty duration demonstration
        duty4 = Waiter.Duty.takeDuty(waiter2, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(2), "Table cleaning");
        duty5 = Waiter.Duty.takeDuty(waiter2, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(1), "Serving");
        duty6 = Waiter.Duty.takeDuty(waiter3, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(2), "Greeting");



        System.out.println("\n\t------------ (4)Reverse connection ------------");
        System.out.println(waiter1.getDuties());
        System.out.println(waiter2.getDuties());

        System.out.println("\n\t------------ (4)All waiters ------------");
        for (Waiter e : Waiter.getAllWaiters()) {
            System.out.println(e);
        }

        System.out.println("\n\t------------ (4)All Duties ------------");
        for (Waiter.Duty r : Waiter.getAllDuties()) {
            System.out.println(r);
        }

        Waiter.quit(waiter3);
        waiter2.removeDuty(duty4);

        System.out.println("\n\t------------ (4)All waiters (one quit) ------------");
        for (Waiter e : Waiter.getAllWaiters()) {
            System.out.println(e);
        }

        System.out.println("\n\t------------ (4)All duties (one quit) ------------");
        for (Waiter.Duty r : Waiter.getAllDuties()) {
            System.out.println(r);
        }

    }
}
