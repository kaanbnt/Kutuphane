package com.vaadin.menu.ui.components;

import com.vaadin.menu.MyUI;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.MouseEventDetails;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;

public class MenuButton extends Button {

    public MenuButton(FontAwesome fontAwesome){
        setIcon(fontAwesome);
        setWidth(75, Unit.PERCENTAGE);
        addStyleName(ValoTheme.BUTTON_PRIMARY);
    }

    @Override
    protected void fireClick(MouseEventDetails details) {
        super.fireClick(details);
        MyUI myUI = (MyUI) getUI().getCurrent();
        myUI.getHeader().setHeaderTitle(this.getCaption());
    }
}
