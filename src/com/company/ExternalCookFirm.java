package com.company;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ExternalCookFirm {
    private String companyName;
    private final List<Cook> cookList = new ArrayList<>();

    public ExternalCookFirm(String companyName) {
        this.companyName = companyName;
    }

    public void addCook(@NotNull Cook newCook) throws Exception {
        if(!cookList.contains(newCook)){
            cookList.add(newCook);

            newCook.addExternalCookFirm(this);
        }
    }

    public void removeCook(@NotNull Cook cookToDelete) {
        if(cookList.contains(cookToDelete)){
            cookList.remove(cookToDelete);

            cookToDelete.removeExternalCookFirm(this);
        }
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<Cook> getCookList() {
        return cookList;
    }

    @Override
    public String toString() {
        return "ExternalCookFirm{" +
                "companyName = '" + companyName + '\'' +
                ",Amount of cooks = " + cookList.size() +
                '}';
    }
}
