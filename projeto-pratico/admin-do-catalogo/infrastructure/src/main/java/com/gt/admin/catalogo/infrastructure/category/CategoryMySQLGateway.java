package com.gt.admin.catalogo.infrastructure.category;

import com.gt.admin.catalogo.domain.category.Category;
import com.gt.admin.catalogo.domain.category.CategoryGateway;
import com.gt.admin.catalogo.domain.category.CategoryID;
import com.gt.admin.catalogo.domain.category.CategorySearchQuery;
import com.gt.admin.catalogo.domain.pagination.Pagination;
import com.gt.admin.catalogo.infrastructure.category.persistence.CategoryRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CategoryMySQLGateway implements CategoryGateway {

    private final CategoryRepository repository;

    public CategoryMySQLGateway(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Category create(Category aCategory) {
        return null;
    }

    @Override
    public void deleteById(CategoryID anId) {

    }

    @Override
    public Optional<Category> findById(CategoryID anId) {
        return Optional.empty();
    }

    @Override
    public Category update(Category aCategory) {
        return null;
    }

    @Override
    public Pagination<Category> findAll(CategorySearchQuery aQuery) {
        return null;
    }
}
