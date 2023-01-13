package ru;

import java.sql.*;

public class Main {
    public static void main(String[] args) {

       AuthorizationGUI authorizationInSystem = new AuthorizationGUI();
       authorizationInSystem.authorization();
        //ConnectionBD connBD = new ConnectionBD();
        //connBD.updateLogin("Petr007","Super");

        //System.out.println(connBD.selectUsers());
    }
}