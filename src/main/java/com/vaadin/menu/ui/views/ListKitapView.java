package com.vaadin.menu.ui.views;

import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.menu.dao.KitapDao;
import com.vaadin.menu.domain.*;
import com.vaadin.menu.ui.components.DeleteButton;
import com.vaadin.shared.ui.MultiSelectMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;

import java.util.List;

public class ListKitapView extends BaseListView {
    private Table table;
    private IndexedContainer indexedContainer;
    private AddKitapView addKitapView;
    private DeleteButton deleteButton;
    Kitap kitap;

    public ListKitapView() {
        setSpacing(true);
        buildTableContainer();

        buildTableContainer();
        buildTable();
        addComponent(table);
        deleteButton = new DeleteButton();
        deleteButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                KitapDao kitapDao = new KitapDao();
                kitapDao.deleteKitap(kitap);
                Notification.show("Kitap Silindi");
            }
        });
        addComponent(deleteButton);
        addKitapView = new AddKitapView();
        addComponent(addKitapView);

        fillTable();
    }

    public void fillTable() {

        KitapDao kitapDao = new KitapDao();
        List<Kitap> kitapList = kitapDao.findAllKitap();
        for (Kitap kitap : kitapList) {
            Item item = indexedContainer.addItem(kitap);
            item.getItemProperty("id").setValue(kitap.getId());
            item.getItemProperty("kitapAdi").setValue(kitap.getKitapAdi());
            item.getItemProperty("enumKitapKategori").setValue(kitap.getEnumKitapKategori());
            item.getItemProperty("id_yazar").setValue(kitap.getYazar());
        }
    }

    public void buildTableContainer() {

        indexedContainer = new IndexedContainer();
        indexedContainer.addContainerProperty("id", Long.class, null);
        indexedContainer.addContainerProperty("kitapAdi", String.class, null);
        indexedContainer.addContainerProperty("enumKitapKategori", EnumKitapKategori.class, null);
        indexedContainer.addContainerProperty("id_yazar", Yazar.class, null);
    }

    public void buildTable() {
        table = new Table();
        table.setHeight("250px");
        table.setContainerDataSource(indexedContainer);
        table.setColumnHeaders("NO", "KİTAP ADI"," KİTAP KATEGORİ" ,"YAZAR ADI");
        table.setSelectable(true);
        table.setMultiSelectMode(MultiSelectMode.SIMPLE);
        table.setMultiSelect(false);
        table.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent itemClickEvent) {
                kitap = (Kitap) itemClickEvent.getItemId();
                addKitapView.fillViewByKitap(kitap);
            }
        });
    }
}
