package com.example.Exercise9;

public interface OpenEmployeeView {
    String getFirstName();
    String getLastName();

    default String getFullName(){
        return getFirstName()+" "+getLastName();
    }

}
