
package com.project.traffic.uiImpl;

import java.util.List;
import java.util.Scanner;

import com.project.traffic.constants.ConsoleMessageConstants;
import com.project.traffic.controller.UserController;
import com.project.traffic.enums.EnumUtils;
import com.project.traffic.enums.EnumUtils.GenderEnum;
import com.project.traffic.enums.EnumUtils.StatusType;
import com.project.traffic.enums.EnumUtils.UserType;
import com.project.traffic.enums.MainEnum;
import com.project.traffic.exception.StandardException;
import com.project.traffic.model.User;
import com.project.traffic.util.DateUtil;
import com.project.traffic.util.InputFromConsoleUtil;

public class UserProfileHandler {

	private static UserController userController;

	public UserProfileHandler() {
		userController = new UserController();
	}

	private enum CustomerUpdateEnum implements MainEnum {
		LOGIN(1, "Update Login"), FNAME(2, "Update First Name"), LNAME(3, "Update Last Name"), AGE(4,
				"Update Age"), GENDER(5, "Update Gender");

		private String message;
		private int value;

		private CustomerUpdateEnum(int value, String message) {
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
			return message;
		}
	}

	private enum CustomerListEnum implements MainEnum {
		BY_LOGIN(1, "Find By Login"), BY_FNAME(2, "Find By First Name"), BY_LNAME(3, "Find By Last Name");

		private String message;
		private int value;

		private CustomerListEnum(int value, String message) {
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
			return message;
		}
	}
	private enum CustomerOptionsEnum implements MainEnum {

		CREATE(1, "Create Customer"), UPDATE(2, "Update Customer"), DELETE(3, "Delete Customer"), LIST(4,
				"List All Customers"), FIND(5, "Find Customers"), MAIN_MENU(6, "Go to Previous Menu");
		private String message;
		private int value;

		private CustomerOptionsEnum(int value, String message) {
			this.message = message;
			this.value = value;

		}

		@Override
		public int value() {
			return value;
		}

		public String getMessage() {
			return message;
		}
	}

	/**
	 * getLoginName from the user and validate
	 * 
	 * @param message
	 * @param isValidationRequired
	 * @return
	 * @throws StandardException
	 */
	boolean validateLogin() throws StandardException {
		String loginName = "";
		char[] password;
		System.out.println("Username:");
		loginName = new Scanner(System.in).nextLine();
		//password = System.console().readPassword("Password:");
		//System.out.println("Password:");
		//password = new Scanner(System.in).nextLine();
		return userController.validateLogin(loginName, /*String.valueOf(password)*/"1234");
	}
	
	User forgotPassword() throws StandardException {
		String loginName = "";
		System.out.println("Username:");
		loginName = new Scanner(System.in).nextLine();
		return userController.findByLogin(loginName);
	}
	
	

	/**
	 * Create customer
	 * 
	 * @throws StandardException
	 */
	/*private void create() throws StandardException {

		ProjectCustomerBO customer = custoemrCreateInput();
		boolean createStatus = customerService.createOrUpdateCustomer(customer);

		List<ProjectCustomerBO> list = ListUtil.newList();
		list.add(customer);
		displayUser(list);

		if (createStatus)
			System.out.println(ConsoleMessageConstants.SUCCESS_CREATE);
		else
			System.out.println(ConsoleMessageConstants.FAILURE_CREATE);

	}

	 *//**
	 * Update customer
	 * 
	 * @throws StandardException
	 *//*
	private void update() throws StandardException {

		ProjectCustomerBO customerToUpdate = findToUpdateOrDelete();

		if (customerToUpdate == null) {
			return;
		}
		customerToUpdate = prepareBoByInput(customerToUpdate);

		boolean updateStatus = customerService.createOrUpdateCustomer(customerToUpdate);

		List<ProjectCustomerBO> list = ListUtil.newList();
		list.add(customerToUpdate);
		displayUser(list);

		if (updateStatus) {
			System.out.println(ConsoleMessageConstants.SUCCESS_UPDATE);
		} else {
			System.out.println(ConsoleMessageConstants.FAILURE_UPDATE);
		}

	}

	  *//**
	  * Prepare projectCustomerBO by input data
	  * 
	  * @param customerToUpdate
	  * @return
	  * @throws StandardException
	  *//*
	public ProjectCustomerBO prepareBoByInput(ProjectCustomerBO customerToUpdate) throws StandardException {

		CustomerUpdateEnum inputChoice = null;
		String choice = ConsoleMessageConstants.SELECTED_CHOICE;
		do {
			inputChoice = InputFromConsoleUtil.enumValues(CustomerUpdateEnum.class);
			switch (inputChoice) {
			case LOGIN:
				customerToUpdate.setLogin(getLoginName(ConsoleMessageConstants.VALID_LOGIN, true));
				break;
			case FNAME:
				customerToUpdate.setFirstName(InputFromConsoleUtil.getString(ConsoleMessageConstants.VALID_FNAME));
				break;
			case LNAME:
				customerToUpdate.setLastName(InputFromConsoleUtil.getString(ConsoleMessageConstants.VALID_LNAME));
				break;
			case AGE:
				customerToUpdate.setAge(getAge(ConsoleMessageConstants.VALID_AGE));
				break;
			case GENDER:
				System.out.println(ConsoleMessageConstants.VALID_GENDER);
				customerToUpdate.setGender(InputFromConsoleUtil.enumValues(GenderEnum.class));
				break;
			}
			choice = InputFromConsoleUtil.takeConfirmation(ConsoleMessageConstants.UPDATE_MORE);
		} while (choice.equalsIgnoreCase(ConsoleMessageConstants.SELECTED_CHOICE));

		return customerToUpdate;
	}

	   *//**
	   * Delete a customer
	   * 
	   * @throws StandardException
	   *//*
	private void delete() throws StandardException {

		ProjectCustomerBO customerToDelete = findToUpdateOrDelete();

		if (customerToDelete == null) {
			return;
		}
		customerToDelete.setStatus(StatusType.INACTIVE);
		int deleteStatus = customerService.deleteCustomerByLoginName(customerToDelete);
		if (deleteStatus == 1) {
			System.out.println(ConsoleMessageConstants.SUCCESS_DELETE);
		} else {
			System.out.println(ConsoleMessageConstants.FAILURE_DELETE);
		}
	}

	    *//**
	    * Display all customers
	    * 
	    * @throws StandardException
	    *//*
	public void displayAll() throws StandardException {

		List<User> customerList = customerService.listCustomers();

		if (customerList.isEmpty()) {
			System.out.println(ConsoleMessageConstants.NOTHING_EXISTED);
			return;
		}

		displayUser(customerList);
	}

	     *//**
	     * Find customers with different options
	     * 
	     * @return
	     * @throws StandardException
	     *//*
	private List<ProjectCustomerBO> find() throws StandardException {
		int listChoice = InputFromConsoleUtil.enumValues(CustomerListEnum.class).value();

		List<ProjectCustomerBO> customerList = getCustomerFilteredObject(listChoice);

		if (ListUtil.isEmpty(customerList)) {
			System.out.println(ConsoleMessageConstants.NOTHING_EXISTED);
			return null;
		}

		displayUser(customerList);

		return customerList;
	}

	//**
	      * Find the appropriate customer to update
	      * 
	      * @return
	      * @throws StandardException
	      *//*
	public ProjectCustomerBO findToUpdateOrDelete() throws StandardException {

		int listChoice = InputFromConsoleUtil.enumValues(CustomerListEnum.class).value();

		List<ProjectCustomerBO> customerList = getCustomerFilteredObject(listChoice);

		if (ListUtil.isEmpty(customerList)) {
			System.out.println(ConsoleMessageConstants.NOTHING_EXISTED);
			return null;
		}

		displayUser(customerList);

		int index = InputFromConsoleUtil.getIndex(ConsoleMessageConstants.TAKE_INDEX, customerList.size());
		return customerList.get(index);
	}

	       *//**
	       * Creating projectCustomerBO based on input choice from the user
	       * 
	       * @param listChoice
	       * @return
	       * @throws StandardException
	       *//*
	private List<ProjectCustomerBO> getCustomerFilteredObject(int listChoice) throws StandardException {
		List<ProjectCustomerBO> customers = null;
		switch (listChoice) {
		case 1:
			String loginName = getLoginName(ConsoleMessageConstants.MANDATE_LOGIN, false);
			customers = customerService.findCustomerByLogin(loginName);
			break;

		case 2:
			String fName = InputFromConsoleUtil.getString(ConsoleMessageConstants.VALID_FNAME);
			customers = customerService.findCustomerByFirstName(fName);
			break;

		case 3:
			String lName = InputFromConsoleUtil.getString(ConsoleMessageConstants.VALID_LNAME);
			customers = customerService.findCustomerByLastName(lName);
			break;
		}
		return customers;
	}*/

	/**
	 * getLoginName from the user
	 * 
	 * @param message
	 * @param isValidationRequired
	 * @return
	 * @throws StandardException
	 */
	@SuppressWarnings("resource")
	private String getNewLoginName(String message) throws StandardException {
		String loginName = "";
		boolean isUnique = false;
		do {
			System.out.println(message);
			loginName = new Scanner(System.in).next().trim();
				isUnique = userController.findByUsername(loginName);
				if (!isUnique)
					System.err.println(ConsoleMessageConstants.VALID_UNQ_USER);
		} while (!isUnique);

		return loginName;
	}
	
	private String getPassword(String message)throws StandardException{

		char[] password1;
		char[] password2 ;
		boolean isSame = false;
		do {
			password1 = System.console().readPassword("Password:");
			password2 = System.console().readPassword("Confirm Password:");
			if(String.valueOf(password1).equals(String.valueOf(password2)))
				isSame = true;
			else{
				System.err.println(ConsoleMessageConstants.PASSWORD_MISSMATCH);
			}
		} while(!isSame);

		return String.valueOf(password1);
	
	}


	/**
	 * Take input from customer to create
	 * 
	 * @return
	 * @throws StandardException
	 */
	User signup() throws StandardException {

		String loginName = getNewLoginName(ConsoleMessageConstants.VALID_USER);
		String firstName = InputFromConsoleUtil.getString(ConsoleMessageConstants.VALID_FNAME, true);
		String lastName = InputFromConsoleUtil.getString(ConsoleMessageConstants.VALID_LNAME, false);
		//String password = getPassword(ConsoleMessageConstants.PASSWORD);
		
		User user = new User();
		user.setUserName(loginName);
		user.setFirstName(firstName);
		user.setPassword("1234");
		user.setLastName(lastName);
		user.setCreatedDate(DateUtil.getDate());
		user.setPhone(InputFromConsoleUtil.getString(ConsoleMessageConstants.PHONE, false));
		user.setEmail(InputFromConsoleUtil.getString(ConsoleMessageConstants.EMAIL, true));
		System.out.println(ConsoleMessageConstants.VALID_GENDER);
		user.setGender(InputFromConsoleUtil.enumValues(GenderEnum.class));
		user.setAge(InputFromConsoleUtil.getInteger(ConsoleMessageConstants.VALID_AGE));
		user.setStatus(StatusType.ACTIVE.getStatus());
		if(user.getUserName().contains("admin"))
		    user.setUserType(UserType.ADMIN.getType());
		else
		    user.setUserType(UserType.USER.getType());
		return user;

	}

	/**
	 * Displays all cutsomers
	 * 
	 * @param usersList
	 */
	public void displayUser(List<User> usersList) {
		System.out.println(
				"***********************************************************************************************************************************************************************************");
		String format = "|%1$-5s|%2$-15s|%3$-20s|%4$-10s|%5$-5s|%6$-8s|%7$-11s|%8$-25s|%9$-20s|%10$-10s|%11$-10s|\n";
		System.out.format(format, "SNo", "User Name", "First Name", "Last Name", "Age", "Gender", "Phone", "Email","Created Date","Status","User Type");
		System.out.println(
				"***********************************************************************************************************************************************************************************");
		for (int i = 0; i < usersList.size(); i++) {

			String loginName = (usersList.get(i).getUserName() == null || usersList.get(i).getUserName().isEmpty()) ? "---"
					: usersList.get(i).getUserName();
			String firstName = (usersList.get(i).getFirstName() == null || usersList.get(i).getFirstName().isEmpty())
					? "---" : usersList.get(i).getFirstName();
			String lastName = (usersList.get(i).getLastName() == null || usersList.get(i).getLastName().isEmpty())
					? "---" : usersList.get(i).getLastName();
			String age = (usersList.get(i).getAge() < 0) ? "---" : usersList.get(i).getAge() + "";
			String gender = EnumUtils.getEnumObjFromValue(GenderEnum.class, usersList.get(i).getGender().value()) + "";
			String phone = (usersList.get(i).getPhone() == null) ? "---" : usersList.get(i).getPhone() + "";
			String email = (usersList.get(i).getEmail() == null) ? "---" : usersList.get(i).getEmail() + "";
			String createdDate = (usersList.get(i).getCreatedDate() == null) ? "---" : usersList.get(i).getCreatedDate() + "";
			String status = EnumUtils.getEnumObjFromValue(StatusType.class, usersList.get(i).getStatus()) + "";
			String userType = EnumUtils.getEnumObjFromValue(UserType.class, usersList.get(i).getUserType()) + "";
			System.out.format(format, i + 1, loginName, firstName, lastName, age, gender, phone, email,createdDate,status,userType);
		}
	}
}
