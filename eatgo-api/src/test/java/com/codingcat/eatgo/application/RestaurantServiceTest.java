package com.codingcat.eatgo.application;

import com.codingcat.eatgo.domain.*;
import com.codingcat.eatgo.domain.MenuItem;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RestaurantServiceTest {

    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private MenuItemRepository menuItemRepository;

    @BeforeAll
    public void beforeAll(){
        MockitoAnnotations.openMocks(this);

        mockRestaurantRepository();
        mockMenuItemRepository();
        // 직접 의존성 주입
        restaurantService = new RestaurantService(restaurantRepository, menuItemRepository);
    }

    private void mockMenuItemRepository() {
        List<MenuItem> menuItems = new ArrayList<>();
        MenuItem menuItem = new MenuItem("Kimchi");
        menuItems.add(menuItem);
        given(menuItemRepository.findAllByRestaurantId(1004L)).willReturn(menuItems);
    }

    private void mockRestaurantRepository() {
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = new Restaurant(1004L, "Bob zip", "Seoul");
        restaurants.add(restaurant);

        // 왼쪽의 함수의 결과값을 오른쪽과 같다. (앞으로 이 파일의 모든 테스트에 영향을 받음)
        given(restaurantRepository.findAll()).willReturn(restaurants);
        given(restaurantRepository.findById(1004L)).willReturn(restaurant);
    }

    @DisplayName("1. 레스토랑 조회하여 아이디 가져오기")
    @Test
    public void getRestaurantById(){
        Restaurant restaurant = restaurantService.getRestaurantById(1004L);
        assertEquals(restaurant.getId(), 1004L);
    }

    @DisplayName("2. 모든 레스토랑 목록 가져오기")
    @Test
    public void getRestaurants(){
        List<Restaurant> restaurants =restaurantService.getRestaurants();
        Restaurant restaurant = restaurants.get(0);
        assertEquals(restaurant.getId(), 1004L);

//        MenuItem menuItem = restaurant.getMenuItems().get(0);
//        assertEquals(menuItem.getName(), "Kimchi");
    }

    @DisplayName("3. 레스토랑 메뉴아이템 가져오기")
    @Test
    public void getRestaurantMenu(){
        Restaurant restaurant =restaurantService.getRestaurantById(1004L);
        assertEquals(restaurant.getId(), 1004L);

        MenuItem menuItem = restaurant.getMenuItems().get(0);
        assertEquals(menuItem.getName(), "Kimchi");

//        MenuItem menuItem = restaurant.getMenuItems().get(0);
//        assertEquals(menuItem.getName(), "Kimchi");
    }
}