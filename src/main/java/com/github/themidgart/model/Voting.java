package com.github.themidgart.model;

public class Voting extends AbstractEntity{

    private User user;

    private Menu menu;

    public Voting() {
    }

    public Voting(Integer id, User user, Menu menu) {
        super(id);
        this.user = user;
        this.menu = menu;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
