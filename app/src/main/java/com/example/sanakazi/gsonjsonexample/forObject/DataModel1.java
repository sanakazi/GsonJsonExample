package com.example.sanakazi.gsonjsonexample.forObject;

import java.util.ArrayList;

/**
 * Created by SanaKazi on 9/23/2016.
 */
public class DataModel1
{
    private String statusCode;
    private Data data;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }


    public class Data{
        private ArrayList<Categories> categories;

        public ArrayList<Categories> getCategories() {
            return categories;
        }

        public void setCategories(ArrayList<Categories> categories) {
            this.categories = categories;
        }


    }


    public class Categories{
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
    }

}
