package com.example.teamproject;

import java.util.ArrayList;

public class DayData {
    private int day;
    private ArrayList<PlanData> plans;

    public DayData(int day, ArrayList<PlanData> plans) {
        this.day = day;
        this.plans = plans;
    }

    public int getDay() { return day; }
    public ArrayList<PlanData> getPlans() { return plans; }
}
