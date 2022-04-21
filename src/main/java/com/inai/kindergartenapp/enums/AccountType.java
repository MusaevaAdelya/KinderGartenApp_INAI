package com.inai.kindergartenapp.enums;

public enum AccountType {
    DIRECTOR("Director"),
    STUDENT("Student"),
    TEACHER("Teacher");

    private final String accountType;

    private AccountType(String accountType){
        this.accountType=accountType;
    }

    public String getAccountType() {
        return accountType;
    }
}
