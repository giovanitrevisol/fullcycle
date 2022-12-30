package com.gt.admin.catalogo.application.retrieve.list;

import com.gt.admin.catalogo.application.UseCase;
import com.gt.admin.catalogo.domain.category.CategorySearchQuery;
import com.gt.admin.catalogo.domain.pagination.Pagination;

public abstract class ListCategoriesUseCase
        extends UseCase<CategorySearchQuery, Pagination<CategoryListOutput>> {
}
