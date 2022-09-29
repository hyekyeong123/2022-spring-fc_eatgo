package com.codingcat.eatgo.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MenuItem {
    private final String name;

    public MenuItem(String name) {
        this.name = name;
    }
}
