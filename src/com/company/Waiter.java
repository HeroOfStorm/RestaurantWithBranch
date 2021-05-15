package com.company;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Waiter {
    private static final Set<Duty> allDuties = new HashSet<>();
    private static final List<Waiter> waitersDb = new ArrayList<>();
    private final List<Duty> duties = new ArrayList<>();
    private Branch workPlace;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Boolean isOnDuty;

    public Waiter(@NotNull String firstName, @NotNull String lastName, @NotNull String email, @NotNull String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        isOnDuty = false;
        waitersDb.add(this);
    }


    public static void quit(Waiter waiter) {
        if (waiter.workPlace != null){
            Branch tmp = waiter.workPlace;
            waiter.workPlace = null;
            tmp.fireWaiter(waiter);
        }
        for (Duty duty : waiter.getDuties()) {
            allDuties.remove(duty);
        }
        waiter.duties.clear();

        waitersDb.remove(waiter);
    }

    public void attachToBranch(@NotNull Branch workPlace) {
        if(this.workPlace == null)
            this.workPlace = workPlace;
            this.workPlace.attachWaiter(this);
    }

    public void changeBranch(@NotNull Branch workPlace) {
        Waiter.quit(this);
        attachToBranch(workPlace);
    }

    public void addDuty(Duty duty) throws Exception {
        if (!duties.contains(duty)) {
            duties.add(duty);
            if (!allDuties.contains(duty)) {
                allDuties.add(duty);
            }
            return;
        }
        throw new Exception("Duty already assigned!");
    }

    public void removeDuty(Waiter.Duty duty) {
        if(duties.contains(duty)){
            duties.remove(duty);
            allDuties.remove(duty);
        }
    }

    //-------------Getters and setters-------------
    public static Set<Waiter.Duty> getAllDuties() {
        return allDuties;
    }

    public static List<Waiter> getAllWaiters() {
        return waitersDb;
    }

    public Branch getWorkPlace() { return workPlace; }

    public List<Duty> getDuties() {
        return duties;
    }

    @Override
    public String toString() {
        return "Waiter{" +
                "duties count=" + duties.size() +
                ", workPlace=" + workPlace +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", isOnDuty=" + isOnDuty +
                "}\n";
    }

    public static class Duty {
        private final LocalDateTime startTime;
        private final LocalDateTime endTime;
        private long hoursScheduled;
        private final String description;
        private Waiter assignee;


        private Duty(@NotNull Waiter waiter, @NotNull LocalDateTime startTime, @NotNull LocalDateTime endTime, @NotNull String description) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.assignee = waiter;
            calculateTimeOnDuty();
            this.description = description;
        }

        public static Duty takeDuty(@NotNull Waiter waiter, @NotNull LocalDateTime startTime, @NotNull LocalDateTime endTime, @NotNull String description){
            long hours = startTime.until( endTime, ChronoUnit.HOURS );
            if (hours < 1){
                throw new IllegalArgumentException("Difference between duty end time and start time must be grater than one hour");
            }
            Duty tmpDuty = new Duty(waiter, startTime, endTime, description);
            try {
                tmpDuty.assignee.addDuty(tmpDuty);
                return tmpDuty;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }

        public void calculateTimeOnDuty(){
            this.hoursScheduled = startTime.until( endTime, ChronoUnit.HOURS );
        }

        @Override
        public String toString() {
            return " Duty{" +
                    "assignee=" + assignee +
                    "startTime=" + startTime +
                    ", endTime=" + endTime +
                    ", Scheduled " + hoursScheduled + " hours of work" +
                    ", description='" + description + '\'' +
                    "}\n";
        }
    }

}
