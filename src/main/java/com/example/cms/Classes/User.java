package com.example.cms.Classes;

import java.io.Serializable;

public abstract class User implements Serializable {
    @Override
    public String toString()
    {
        return "userData{" + "firstName=" + firstName + ", lastName=" + lastName  + ", mobileNumber=" + mobileNumber + ", gender=" + gender + ", emailAddress=" + emailAddress +  ", userName=" + userName + ", password=" + password + '}';
    }
    public String firstName ;
    public String lastName ;

    public String mobileNumber ;
    public String gender ;
    public String emailAddress ;
    public String userName ;
    public String password ;



    public User(String firstName, String lastName, String mobileNumber, String gender, String emailAddress, String userName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;


        this.mobileNumber = mobileNumber;
        this.gender = gender;
        this.emailAddress = emailAddress;
        this.userName = userName;
        this.password = password;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
