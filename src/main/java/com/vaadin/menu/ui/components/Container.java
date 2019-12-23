package com.vaadin.menu.ui.components;

import com.vaadin.ui.HorizontalLayout;

public class Container extends HorizontalLayout {

    public Container() {
        setWidth(100, Unit.PERCENTAGE);

        Content content = new Content();
        SideBar sideBar = new SideBar(content);

        addComponent(sideBar);
        addComponent(content);

        setExpandRatio(sideBar, 2f);
        setExpandRatio(content, 8f);
    }
}
