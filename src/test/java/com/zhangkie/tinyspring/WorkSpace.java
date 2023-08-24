package com.zhangkie.tinyspring;

public class WorkSpace {
    private String name;
    private User user;

    public WorkSpace() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "WorkSpace{" +
                "name='" + name + '\'' +
                ", user=" + user +
                '}';
    }
}
