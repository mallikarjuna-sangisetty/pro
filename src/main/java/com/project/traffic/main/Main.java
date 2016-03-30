package com.project.traffic.main;

import com.project.traffic.ui.UI;
import com.project.traffic.uiImpl.UIImpl;

public class Main {


    private static UI ui;
    public static void main(String[] args) {

        ui = new UIImpl();
        ui.homeMenu();

    }
}
