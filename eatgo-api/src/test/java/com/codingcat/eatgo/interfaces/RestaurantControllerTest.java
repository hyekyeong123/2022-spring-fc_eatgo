package com.codingcat.eatgo.interfaces;

import com.codingcat.eatgo.EatgoApplication;
import com.codingcat.eatgo.application.RestaurantService;
import com.codingcat.eatgo.domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RestaurantController.class)
class RestaurantControllerTest {

    @Autowired private MockMvc mvc;

    // 의존성 주입 별도로 필요
    @MockBean(RestaurantRepositoryImpl.class)
    private RestaurantRepository restaurantRepository;
    @MockBean(MenuItemRepositoryImpl.class)
    private MenuItemRepository menuItemRepository;

    // Mock 방법(가짜 객체 투입)
    // 실제 서비스에 영향 없음
    @MockBean
    private RestaurantService restaurantService;

//  ****************************************************************************************

    @DisplayName("음식점 목록")
    @Test
    public void restaurantlist() throws Exception {

        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant(1004L, "Bob zip", "Seoul"));
        given(restaurantService.getRestaurants()).willReturn(restaurants);

        mvc.perform(get("/restaurants"))
            .andExpect(status().isOk())
                .andExpect(
                content().string(
                containsString("\"id\":1004")))
            .andExpect(
            content().string(
                containsString("\"name\":\"Bob zip\"")));
    }

    @DisplayName("음식점 상세 정보 : id=1004")
    @Test
    public void restaurantDetail1() throws Exception {

        Restaurant restaurant = new Restaurant(1004L, "Jun zip", "Seoul");
        given(restaurantService.getRestaurantById(1004L)).willReturn(restaurant);

        mvc.perform(get("/restaurants/1004"))
            .andExpect(status().isOk())
            .andExpect(
                    content().string(
                            containsString("\"id\":1004")))
            .andExpect(
                    content().string(
                            containsString("\"name\":\"Jun zip\"")))
            ;
    }

    @DisplayName("3. 음식점 상세 + 메뉴")
    @Test
    public void restaurantMenuDetail() throws Exception {

        Restaurant restaurant = new Restaurant(2020L, "Cyber Food", "Seoul");
        restaurant.addMenuItem(new MenuItem("Kimchi"));
        given(restaurantService.getRestaurantById(2020L)).willReturn(restaurant);

        mvc.perform(get("/restaurants/2020"))
                .andExpect(status().isOk())
                .andExpect(
                        content().string(
                                containsString("\"id\":2020")))
                .andExpect(
                        content().string(
                                containsString("\"name\":\"Cyber Food\"")))
                .andExpect(content().string(containsString("Kimchi")))
        ;
    }
}