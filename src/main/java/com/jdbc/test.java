package com.jdbc;

public class test {
    public static void main(String[] args) throws Exception {
        H2DbHelper helper = new H2DbHelper();
        helper.connect();
        System.out.println(helper.getProductById(1));
    }
}
