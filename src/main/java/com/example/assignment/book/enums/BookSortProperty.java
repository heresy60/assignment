package com.example.assignment.book.enums;

import com.example.assignment.global.controller.request.SortingProperty;

public enum BookSortProperty implements SortingProperty {
    createDate("id"), price("rentalPrice"), rentalCount("rentalInfo.rentalCount");

    private final String column;

    BookSortProperty(String column) {
        this.column = column;
    }

    @Override
    public String getProperty() {
        return column;
    }
}
