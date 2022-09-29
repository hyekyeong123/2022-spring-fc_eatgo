package com.codingcat.eatgo.domain;

import java.util.List;

public interface RestaurantRepository {

    List<Restaurant> findAll();
    Restaurant findById(Long id);
}
