package rkm.ecom;

import rkm.ecom.AccountInfo;

public class AccountForm {

    private String name;
    private String username;
    private String password;
    private String address;
    private String email;
    private String phone;

    private boolean valid;

    public AccountForm() {

    }

    public AccountForm(AccountInfo accountInfo) {
        if (accountInfo != null) {
            this.name = accountInfo.getName();
            this.name = accountInfo.getUserName();
            this.name = accountInfo.getPassword();
            this.address = accountInfo.getAddress();
            this.email = accountInfo.getEmail();
            this.phone = accountInfo.getPhone();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

}

