package com.project.traffic.util;

import com.project.traffic.exception.StandardException;

public class Util {

    public static void isNumber(long value, boolean required) throws StandardException
    {
        if (value == 0) {
            if (required) {
                throw new StandardException("%1 is a required field.");
            }
            return;
        }
        if (value >= 0) {
            return;
        }
        throw new StandardException("%1 must be a positive integer value.");
    }




    public static boolean checkLogin(String login, boolean new_user)  {
        String loginStr = login;
        loginStr = loginStr.trim();
        if (loginStr.isEmpty()) {
            System.out.println("Login is a required field.");
            return false;
        }                               
        if (loginStr.length() < 3 || loginStr.length() > 60) {                  
            System.out.println("Login must be between 3 and 60 characters.");
            return false;
        }               
        char[] chArr = loginStr.toCharArray();
        for (int i = 0; i < chArr.length; ++i) {
            char ch = chArr[i];
            if (i == 0
                    && (!((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')))) {
                System.out.println(
                        "Login must start with an a-z or A-Z.");
                return false;
            }
            if (!((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')
                    || (ch >= '0' && ch <= '9') || (ch == '_') || (ch == '.'))) {
                System.out.println(
                        "Login must only contain a-z, A-Z, 0-9, underscores(_) or periods.");
                return false;
            }
        }

        boolean loginUnique = true;

        loginUnique = isLoginUnique(login);
        if (!loginUnique && new_user) {
            System.out.println(
                    "The chosen Login is already being used.  Please choose another.");
            return false;
        }
        else if (!new_user && loginUnique) {
            System.out.println(
                    "The chosen Login does not exist.  Please enter a correct one.");
            return false;
        }
        return true;
    }

    public static boolean isString(String str, boolean required)
    {
        if (required && str.isEmpty()) {
            System.out.println("%1 is a required field.");
            return false;
        }
        return true;
    }

    public static void isGender(String str, boolean required)
            throws StandardException {
        if (required && str.isEmpty())
            System.out.println("Gender is a required field ");
        else
            if(!(str.equalsIgnoreCase("male")||str.equalsIgnoreCase("female"))){
                System.out.println("Please enter valid gender");        
            }
    }

    public static boolean isLoginUnique(String login){
        return false;

    }

    public static boolean quantityCheck(int quantity){
        return ((quantity < 1000) && (quantity > 0) ? true : false);

    }

}
