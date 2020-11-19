package com.example.teamproject;

public class PlanData {
    private int _id, year, month, day, secondOrder;
    private String progress, firstOrder, content;

    public PlanData(int _id, int year, int month, int day, String progress, String firstOrder, int secondOrder, String content) {
        this._id = _id;
        this.year = year;
        this.month = month;
        this.day = day;
        this.progress = progress;
        this.firstOrder = firstOrder;
        this.secondOrder = secondOrder;
        this.content = content;
    }

    public int getId() { return _id; }
    public int getYear() { return year; }
    public int getMonth() { return month; }
    public int getDay() { return day; }
    public String getProgress() { return progress; }
    public String getFirstOrder() { return firstOrder; }
    public int getSecondOrder() { return secondOrder; }
    public String getContent() { return content; }

    public void setYear(int year) { this.year = year;}
    public void setMonth(int month) { this.month = month;}
    public void setDay(int day) { this.day = day;}
    public void setProgress(String progress) { this.progress = progress;}
    public void setFirstOrder(String firstOrder) { this.firstOrder = firstOrder;}
    public void setSecondOrder(int secondOrder) { this.secondOrder = secondOrder;}
    public void setContent(String content) { this.content = content;}
}