package com.how2java.tmall.pojo;

import java.io.Serializable;

/**
 * @author apple
 */
public class CategoryTry implements Serializable {
    private static final long serialVersionUID = 2987028313148342244L;
    private int id;
    private String name;

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

    @Override
    public String toString() {
        return "Category [id=" + id + ", name=" + name + "]";
    }


}
