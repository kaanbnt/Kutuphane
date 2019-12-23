package com.vaadin.menu.ui.components;

import com.vaadin.menu.MyUI;
import com.vaadin.ui.VerticalLayout;

public class General extends VerticalLayout {
    public General() {
        Header header = new Header();
        addComponent(header);
        MyUI myUI = (MyUI) getUI().getCurrent();
        myUI.setHeader(header);

        Container container = new Container();
        addComponent(container);
    }
}
