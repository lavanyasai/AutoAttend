package com.project.android.models;

public class Students {

    private String FirstName;
    private String ImageURL;
    private String LastName;
    private String RollNumber;

    public Students() {

    }

    public Students(String FirstName, String ImageURL, String LastName, String RollNumber) {
        this.FirstName = FirstName;
        this.ImageURL = ImageURL;
        this.LastName = LastName;
        this.RollNumber = RollNumber;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String ImageURL) {
        this.ImageURL = ImageURL;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getRollNumber() {
        return RollNumber;
    }

    public void setRollNumber(String RollNumber) {
        this.RollNumber = RollNumber;
    }
}
