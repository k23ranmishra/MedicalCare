package rkm.ecom;


public class AccountInfo {

    private String name;
    private String address;
    private String email;
    private String phone;
    
    private String username;
    private String password;
    
    private boolean valid;

    public AccountInfo() {

    }

    public AccountInfo(AccountInfo accountInfo) {
        if (accountInfo != null) {
            this.name = accountInfo.getName();
            this.username = accountInfo.getUserName();
            this.password = accountInfo.getPassword();
            
            this.address = accountInfo.getAddress();
            this.email = accountInfo.getEmail();
            this.phone = accountInfo.getPhone();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String username) {
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
