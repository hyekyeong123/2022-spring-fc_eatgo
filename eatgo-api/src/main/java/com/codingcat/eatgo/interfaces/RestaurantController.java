package com.codingcat.eatgo.interfaces;

import com.codingcat.eatgo.application.RestaurantService;
import com.codingcat.eatgo.domain.MenuItem;
import com.codingcat.eatgo.domain.MenuItemRepository;
import com.codingcat.eatgo.domain.Restaurant;
import com.codingcat.eatgo.domain.RestaurantRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestaurantController {

    // 객체를 만들지 않아도 스프링이 been을 주입해줌(인터페이스 구현체가 아닌 인터페이스를 가져온다.)
    private final RestaurantService restaurantService;
    private final MenuItemRepository menuItemRepository;

    public RestaurantController(RestaurantRepository restaurantRepository, RestaurantService restaurantService, MenuItemRepository menuItemRepository, MenuItemRepository menuItemRepository1) {
        this.restaurantService = restaurantService;
        this.menuItemRepository = menuItemRepository1;
    }

// ******************************************************************


    @GetMapping("/restaurants")
    public List<Restaurant> restaurantList(){
        return restaurantService.getRestaurants();
    }

    // 가게 상세 정보 가져오기
    @GetMapping("/restaurants/{id}")
    public Restaurant restaurantDetail(@PathVariable("id") Long id){
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        return restaurant;
    }
}
