package com.codingcat.eatgo.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class Restaurant {
    private final Long id;
    private final String name;
    private final String address;
    private List<MenuItem> menuItems = new ArrayList<MenuItem>();

    public Restaurant(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public String getInformation() {
        return name + " in "+address;
    }

    public void addMenuItem(MenuItem _menuItem) {
        menuItems.add(_menuItem);
    }

    public void setMenuItems(List<MenuItem> _menuItems){
        for(MenuItem menuItem : _menuItems){
            addMenuItem(menuItem);
        }
    }
}
