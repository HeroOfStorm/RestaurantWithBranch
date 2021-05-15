package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Branch {
    private final static List<Branch> allBranches = new ArrayList<>();
    private final Map<String, Table> branchTablesById;
    private List<Waiter> waiters = new ArrayList<>();
    private String branchAddress;


    public Branch(String branchAddress) {
        this.branchTablesById = new HashMap<>();
        this.branchAddress = branchAddress;
        allBranches.add(this);
    }

    public void addTable(Table table) {
        if (!branchTablesById.containsKey(table.getTableID())) {
            branchTablesById.put(table.getTableID(), table);
            table.setBranch(this);
        }
    }

    public void attachWaiter(Waiter waiter){
        if (!waiters.contains(waiter)){
            waiters.add(waiter);
            waiter.attachToBranch(this);
        }
    }

    public void fireWaiter(Waiter waiter){
        if (waiters.contains(waiter)) {
            waiters.remove(waiter);
            Waiter.quit(waiter);
        }
    }

    //-------------Getters-------------
    public static List<Branch> getAllBranches() {
        return allBranches;
    }

    public List<Waiter> getWaiters() {
        return waiters;
    }

    public Map<String, Table> getTables() {
        return branchTablesById;
    }

    @Override
    public String toString() {
        return branchAddress;
    }
}
