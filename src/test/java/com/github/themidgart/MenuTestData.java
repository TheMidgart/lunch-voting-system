package com.github.themidgart;

import com.github.themidgart.model.Dish;
import com.github.themidgart.model.Menu;
import com.github.themidgart.model.Restaurant;
import org.assertj.core.api.Assertions;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MenuTestData {
    public static MatcherFactory.Matcher<Menu> MENU_MATCHER =
            MatcherFactory.usingAssertions(Menu.class,
                    (a, e) -> assertThat(a).usingRecursiveComparison()
                            .ignoringFields("dishes", "restaurant", "votes").isEqualTo(e),
                    (a, e) -> Assertions.assertThat(a).
                            usingRecursiveFieldByFieldElementComparatorIgnoringFields("dishes", "restaurant", "votes").isEqualTo(e));
    public static final LocalDate TODAY = LocalDate.now();
    public static final LocalDate TOMORROW = LocalDate.now().plusDays(1);
    public static final int RESTAURANT_1_ID = 100004;
    public static final int RESTAURANT_2_ID = 100005;
    public static final int MENU_TO_VOTE_ID = 100011;
    public static final int MENU_2_ID = 100012;
    public static final int DISH_1_ID = 100019;
    public static final int DISH_2_ID = 100018;
    public static final int DISH_3_ID = 100013;

    public static final Restaurant RESTAURANT_1 = new Restaurant(RESTAURANT_1_ID, "Shustriy shmel", null);
    public static final Restaurant RESTAURANT_2 = new Restaurant(RESTAURANT_2_ID, "Hmel", null);

    public static final Dish DISH_1 = new Dish(DISH_1_ID, "Burito", new BigDecimal(200));
    public static final Dish DISH_2 = new Dish(DISH_2_ID, "Club-sandwich", new BigDecimal(300));
    public static final Dish DISH_3 = new Dish(DISH_3_ID, "Lager", new BigDecimal(250));

    public static final List<Dish> DISHES_FOR_MENU_1 = List.of(DISH_1, DISH_2);
    public static final List<Dish> DISHES_FOR_MENU_2 = List.of(DISH_3);

    public static final Menu MENU_TO_VOTE = new Menu(MENU_TO_VOTE_ID, RESTAURANT_1, TOMORROW, DISHES_FOR_MENU_1);
    public static final Menu MENU_2 = new Menu(MENU_2_ID, RESTAURANT_2, TOMORROW, DISHES_FOR_MENU_2);
    public static final List<Menu> MENU_LIST = List.of(MENU_TO_VOTE, MENU_2);
}