package com.mad.exercise6.model;

public class Train {
    private String mPlatform;
    private String ArrivalTime;
    private String mStatus;
    private String mDestination;
    private String mDestinationTime;


    public Train(){

    }

    public Train(String mPlatform, String arrivalTime, String mStatus, String mDestination, String mDestinationTime) {
        this.mPlatform = mPlatform;
        ArrivalTime = arrivalTime;
        this.mStatus = mStatus;
        this.mDestination = mDestination;
        this.mDestinationTime = mDestinationTime;
    }

    public String getPlatform() {
        return mPlatform;
    }

    public void setPlatform(String mPlatform) {
        this.mPlatform = mPlatform;
    }

    public String getArrivalTime() {
        return ArrivalTime + "\n mins";
    }

    public void setArrivalTime(String arrivalTime) {
        ArrivalTime = arrivalTime;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public String getDestination() {
        return mDestination;
    }

    public void setDestination(String mDestination) {
        this.mDestination = mDestination;
    }

    public String getDestinationTime() {
        return mDestinationTime;
    }

    public void setDestinationTime(String mDestinationTime) {
        this.mDestinationTime = mDestinationTime;
    }
}
