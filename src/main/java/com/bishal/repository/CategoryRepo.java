package com.bishal.repository;

import com.bishal.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepo extends JpaRepository<Category,Long> {

    public Category findByName(String name);

    @Query("SELECT c from Category c where c.name=:name AND c.parentCategory.name=:parentCategoryName")
    public Category findByNameAndParant(@Param("name") String name,@Param("parentCategoryName") String parentCategoryName);
}
