package com.project.traffic.uiImpl;

import com.project.traffic.ui.UI;

public class UIImpl implements UI{

    private MainMenu mainMenu;
    /* (non-Javadoc)
     * @see com.project.traffic.ui.UI#menu()
     */
    @Override
    public void menu(){
       
        
    }

    /* (non-Javadoc)
     * @see com.project.traffic.ui.UI#homeMenu()
     */
    @Override
    public void homeMenu() {
       mainMenu = new MainMenu();
       mainMenu.mainMenu();
        
    }

}
