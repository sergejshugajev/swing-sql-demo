package ru.kpfu.itis.entities;

import java.util.StringJoiner;

public class Category {

    private int id;
    private String name;
    private Integer parentId; // can be null


    public Category(String name) {
        this(name, null);
    }

    public Category(String name, Integer parentId) {
        this.name = name;
        this.parentId = parentId;
    }

    public Category(int id, String name, Integer parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Category.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("parentId=" + parentId)
                .toString();
    }
}
