package com.project.traffic.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import com.project.traffic.constants.ConsoleMessageConstants;
import com.project.traffic.enums.EnumUtils;
import com.project.traffic.enums.MainEnum;
import com.project.traffic.exception.StandardException;

public class InputFromConsoleUtil {

	public static final int DEFAULT_VALUE = 0;
	public static final String CONFIRMATION_PATTERN = "[y|Y|n|N]";


	/**
	 * Choose an item value from the list of the options on console
	 * @param message
	 * @param maxLength
	 *        represents maximum number from the list of available options
	 * @return
	 */
	public static int getIndex(String message,int maxLength) {

		String num = "";
		do{
			System.out.println(message);
			num = new Scanner(System.in).next().trim();
		}while(!num.matches("^[1-9]\\d*$") || Integer.parseInt(num) > maxLength);
		return Integer.parseInt(num)-1;

	}

	/**
	 * Read a String and is optional by pressing enter.
	 * If enter is pressed with out any text then empty string will be returned
	 * @param message
	 * @return
	 */
	public static String getString(String message,boolean isMandatory){
		String string = "";
		do{
			System.out.println(message);
			string = new Scanner(System.in).nextLine().trim();
		}while(string.isEmpty() && isMandatory);
		return string;
	}

	public static boolean isValidDate(String date) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

		format.setLenient(false);
		try {
			Date temp = format.parse(format.format(new Date()));
			Date entered = format.parse(date.trim());
			if(entered.before(temp))
				return false;
		} catch (Exception pe) {
			return false;
		}
		return true;
	}


	/**
	 * Read a String and is optional by pressing enter.
	 * If enter is pressed with out any text then empty string will be returned
	 * @param message
	 * @return
	 */
	public static String getDate(String message,boolean isMandatory){
		String string = "";
		do{
			System.out.println(message);
			string = new Scanner(System.in).nextLine().trim();

		}while(isMandatory && !isValidDate(string));
		return string;
	}


	/**
	 * Take the confirmation from the user
	 * @param message
	 * @return
	 */
	public static String takeConfirmation(String message){
		String choice = "";
		do{
			System.out.println(message);
			choice = new Scanner(System.in).next().trim();
		}while(!choice.matches(CONFIRMATION_PATTERN));
		return choice;
	}
	/**
	 * Read an int and is optional by pressing enter.
	 * If enter is pressed with out any value then 0 will be returned
	 * @param message
	 * @return
	 */
	public static int getInteger(String message){
		String num = "";
		do{
			System.out.println(message);
			try {
				num = new Scanner(System.in).nextLine().trim();
			} catch (Exception e) {
			}
			if(num.isEmpty())
				return DEFAULT_VALUE;
		}while(!num.matches("^(\\d*)$"));

		return Integer.parseInt(num);
	}


	/**
	 * Read an double and is optional by pressing enter.
	 * If enter is pressed with out any value then 0 will be returned
	 * @param message
	 * @return
	 */
	public static double getDouble(String message){
		String num = "";
		do{
			System.out.println(message);
			num = new Scanner(System.in).nextLine().trim();
			if(num.isEmpty())
				return DEFAULT_VALUE;
		}while(!num.matches("^(\\d*\\.?\\d*)$") && Double.parseDouble(num) < 0);
		return Double.parseDouble(num);
	}

	/**
	 * Iterates over an Enum to print the values and messages as a Menu Choice, which is of type {@link TrainingBmEnum}
	 * @param enumType
	 * @throws BusinessLogicException 
	 */
	public static <T extends Enum<T> & MainEnum> T enumValues(Class<T> enumType) throws StandardException {
		for (MainEnum c : enumType.getEnumConstants()) {
			System.out.println(c.value() + "." + c.getMessage() );
		}
		System.out.println("****************************************************************************************************************************************************");
		int inputChoice ;
		do{
			inputChoice = InputFromConsoleUtil.getInteger(ConsoleMessageConstants.TAKE_CHOICE+" :Range(1-"+enumType.getEnumConstants().length+")");
		}while(!EnumUtils.isValidEnumIntegerValue(enumType,inputChoice));

		return EnumUtils.getEnumObjFromValue(enumType, inputChoice);
	}
}
