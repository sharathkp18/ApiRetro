package com.example.apiretro;

import com.google.gson.annotations.SerializedName;

public class EmployeeLoginRes {
    @SerializedName("Email")
    public String email;
    @SerializedName("Password")
    public String password;
    @SerializedName("EmployeeName")
    public String employeeName;
    @SerializedName("Gender")
    public String gender;
    @SerializedName("Password")
    public String Password;
    @SerializedName("PhoneNumber")
    public String phoneNumber;
    @SerializedName("ResponseCode")
    public String ResponseCode;
    @SerializedName("ResponseMessage")
    public String ResponseMessage;

    public EmployeeLoginRes(String email, String Password) {
        this.email = email;
        this.Password = Password;
    }
    public EmployeeLoginRes(String email, String Password,String phoneNumber,String gender,
                            String employeeName) {
        this.email = email;
        this.Password = Password;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.employeeName = employeeName;
    }
    public class EmployeeData {

        @SerializedName("EmployeeName")
        public String employeeName;
        @SerializedName("email")
        public String email;
        @SerializedName("PhoneNumber")
        public String mobileNo;
        @SerializedName("Gender")
        public String gender;
        @SerializedName("Password")
        public String password;
    }
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getGender() {
        return gender;
    }

    public String getResponseCode() {
        return ResponseCode;
    }

    public String getResponseMessage() {
        return ResponseMessage;
    }


}
