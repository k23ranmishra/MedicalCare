package rkm.ecom;

public enum Role {
	ROLE_EMPLOYEE("ROLE_EMPLOYEE"),
	ROLE_MANAGER("ROLE_MANAGER"),
	ROLE_CUSTOMER("ROLE_CUSTOMER");

    private final String value;

    private Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}