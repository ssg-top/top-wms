package com.top.effitopia.enumeration;

public enum CheckoutStatus {
    PASS(1),//양호
    FAIL(2);//불량

    private final int id;

    CheckoutStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
