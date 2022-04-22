package com.inai.kindergartenapp.enums;

public enum AccountType {
    DIRECTOR("director"),
    STUDENT("student"),
    TEACHER("teacher");

    private final String accountType;

    private AccountType(String accountType){
        this.accountType=accountType;
    }

    public String getAccountType() {
        return accountType;
    }
}
