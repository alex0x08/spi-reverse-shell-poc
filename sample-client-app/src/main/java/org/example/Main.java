package org.example;

import java.net.InetAddress;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class Main {

    public static void main(String[] args) throws Exception {

        System.out.println("call 1");
        ResourceBundle.getBundle("Resources", Locale.getDefault());


        System.out.println("call 2");
        Preferences.systemRoot();

        System.out.println("call 3");
        InetAddress.getAllByName("localhost");

        System.out.println("Press <anykey> to exit");
        System.in.read();
    }
}