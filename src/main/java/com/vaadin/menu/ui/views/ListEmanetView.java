package com.vaadin.menu.ui.views;

import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.menu.Dao.EmanetDao;
import com.vaadin.menu.domain.Emanet;
import com.vaadin.menu.domain.Kitap;
import com.vaadin.menu.domain.Uye;
import com.vaadin.menu.ui.components.DeleteButton;
import com.vaadin.shared.ui.MultiSelectMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import java.util.List;

public class ListEmanetView extends BaseListView {
    private Table table;
    private IndexedContainer indexedContainer;
    private AddEmanetView addEmanetView;
    private DeleteButton deleteButton;
    Emanet emanet;

    public ListEmanetView() {
        setSpacing(true);
        buildTableContainer();

        buildTableContainer();
        buildTable();
        addComponent(table);
        deleteButton = new DeleteButton();
        deleteButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                EmanetDao emanetDao = new EmanetDao();
                emanetDao.deleteEmanet(emanet);
                Notification.show("Emanet Silindi");
            }
        });
        addComponent(deleteButton);

        addEmanetView= new AddEmanetView();
        addComponent(addEmanetView);

        fillTable();
    }

    public void fillTable() {

        EmanetDao emanetDao = new EmanetDao();
        List<Emanet> emanetList = emanetDao.findAllEmanet();
        for (Emanet emanet : emanetList) {
            Item item = indexedContainer.addItem(emanet);
            item.getItemProperty("id").setValue(emanet.getId());
            item.getItemProperty("id_kitap").setValue(emanet.getKitap());
            item.getItemProperty("id_uye").setValue(emanet.getUye());
        }
    }

    public void buildTableContainer() {

        indexedContainer = new IndexedContainer();
        indexedContainer.addContainerProperty("id", Long.class, null);
        indexedContainer.addContainerProperty("id_kitap", Kitap.class, null);
        indexedContainer.addContainerProperty("id_uye", Uye.class, null);
    }

    public void buildTable() {
        table = new Table();
        table.setHeight("250px");
        table.setContainerDataSource(indexedContainer);
        table.setColumnHeaders("NO", "KİTAP ADI", "YAZAR ADI");
        table.setSelectable(true);
        table.setMultiSelectMode(MultiSelectMode.SIMPLE);
        table.setMultiSelect(false);
        table.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent itemClickEvent) {
                emanet = (Emanet) itemClickEvent.getItemId();
                addEmanetView.fillViewByEmanet(emanet);
            }
        });
    }
}
