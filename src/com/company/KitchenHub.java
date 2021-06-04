package com.company;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class KitchenHub {
    private Map<String, Cook> cookSet = new HashMap<>();
    private List<Cook> cooks = new ArrayList<>();
    private String hubAddress;
    private String hubName;
    private long hubId;


    public KitchenHub(@NotNull String hubAddress, @NotNull String hubName) {
        setHubAddress(hubAddress);
        setHubName(hubName);
    }

    public KitchenHub() {
    }


    //-------------Getters and Setters-------------
    public long getHubId() {
        return hubId;
    }

    public void setHubId(long hubId) {
        this.hubId = hubId;
    }


    public List<Cook> getCooks() {
        return cooks;
    }

    public String getHubAddress() {
        return hubAddress;
    }

    public String getHubName() {
        return hubName;
    }

    public void setCooks(List<Cook> cooks) {
        this.cooks = cooks;
    }

    public void setHubAddress(String hubAddress) {
        this.hubAddress = hubAddress;
    }

    public void setHubName(String hubName) {
        this.hubName = hubName;
    }

    @Override
    public String toString() {
        return "KitchenHub{" + getHubName() +
                ", " + getHubAddress() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KitchenHub that = (KitchenHub) o;
        return hubAddress.equals(that.hubAddress) && hubName.equals(that.hubName);
    }


    public void addCook(Cook cook) {
        cookSet.put(cook.getCookID(), cook);
    }

    public void removeCook(String cookID) {
        cookSet.remove(cookID);
    }
}
