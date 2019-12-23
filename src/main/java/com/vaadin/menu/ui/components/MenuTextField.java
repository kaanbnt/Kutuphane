package com.vaadin.menu.ui.components;

import com.vaadin.ui.TextField;

public class MenuTextField extends TextField {
    public MenuTextField(String caption){
        setCaption(caption);
        setNullSettingAllowed(false);
        setMaxLength(25);
    }
}
