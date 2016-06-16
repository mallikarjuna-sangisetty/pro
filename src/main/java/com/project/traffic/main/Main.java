package com.project.traffic.main;

import com.project.traffic.ui.UI;
import com.project.traffic.uiImpl.UIImpl;

public class Main {


    private static UI testUI;
    public static void main(String[] args) {

    	testUI = new UIImpl();
    	testUI.homeMenu();
        System.out.println("");
    }
}
