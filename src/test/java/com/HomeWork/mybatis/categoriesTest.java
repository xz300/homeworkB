package com.HomeWork.mybatis;

import com.gb.db.dao.CategoriesMapper;
import com.gb.db.model.Categories;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class categoriesTest {
    public static CategoriesMapper mapper;

    @BeforeAll
    public static void beforeAll() {
        mybatisHelp mybatisHelp = new mybatisHelp();
        mapper = mybatisHelp.getCategoryMapper();
    }

    @Test
    @DisplayName("Полученик категории")
    void getCategory() {
        Categories category = mapper.selectByPrimaryKey(1L);
        System.out.println(category);
        assertEquals(category.getTitle(), "Food");
    }
}