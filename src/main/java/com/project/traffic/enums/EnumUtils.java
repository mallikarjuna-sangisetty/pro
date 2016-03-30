
package com.project.traffic.enums;

import com.project.traffic.exception.StandardException;


/**
 * This class is for utility method related to enum related operations.
 *
 */
public class EnumUtils {

    /**
     * Method to return the enum object which maps to the given name
     * @param <T>
     * @param enumClass the name of the enum class
     * @param name the name of the enum value to get the enum object for
     * @param defaultValue The value to return if the enum object is not found.
     * @return
     */
    public static <T extends Enum<T>> T getEnumTypeFromName(Class<T> enumClass, String name, T defaultValue) {
        T enumObj = Enum.valueOf(enumClass, name);
        return (enumObj !=  null) ? enumObj : defaultValue;
    }

    /**
     * This method gets enum object from the value specified. Assumes that enum class created implements MainEnum interface.
     * @param <T>
     * @param enumClass
     * @param enumIntValue
     * @return
     */
    public static <T extends Enum<T> & MainEnum> T getEnumObjFromValue(Class<T> enumClass, int enumIntValue) {
        T [] enumVals = enumClass.getEnumConstants();
        for(T enumVal : enumVals) {
            if(enumVal.value() == enumIntValue) {
                return enumVal;
            }
        }
        return null;
    }

    /**
     * Checks whether enum option exists in the enumClass with the specified value.
     * Assumes that enum class implements MainEnum interface.
     * @param <T>
     * @param enumClass
     * @param fieldName
     * @param enumIntValue
     * @throws StandardException
     */
    public static <T extends Enum<T> & MainEnum> boolean isValidEnumIntegerValue(Class<T> enumClass, int enumIntValue)
            throws StandardException {
        T [] enumVals = enumClass.getEnumConstants();
        for(T enumVal : enumVals) {
            if(enumVal.value() == enumIntValue) {
                return true;
            }
        }
        return false;
    }

    public static enum GenderEnum implements MainEnum {
        MALE(1, "Male"), FEMALE(2, "Female"), UNKNOWN(3, "Un Known");

        private String message;
        private int value;

        private GenderEnum(int value, String message) {
            this.message = message;
            this.value = value;

        }

        @Override
        public int value() {
            return value;
        }

        /*
         * (non-Javadoc)
         * 
         * @see com.bm.xchange.util.MainEnum#getMessage()
         */
        @Override
        public String getMessage() {
            // TODO Auto-generated method stub
            return message;
        }
    }
    
    public enum StatusType implements MainEnum{
        ACTIVE(1), INACTIVE(0);

        private int status;

        private StatusType(int stat) {
            status = stat;
        }
        public int getStatus() {
            return status;
        }
		@Override
		public int value() {
			// TODO Auto-generated method stub
			return status;
		}
		@Override
		public String getMessage() {
			// TODO Auto-generated method stub
			return "";
		}

    }
    
    public enum UserType implements MainEnum {
        USER(1), ADMIN(2);

        private int type;

        private UserType(int type) {
            this.type = type;
        }
        public int getType() {
            return type;
        }
		@Override
		public int value() {
			return type;
		}
		@Override
		public String getMessage() {
			return "";
		}

    }
}
