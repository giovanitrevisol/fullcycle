package com.gt.admin.catalogo.application.category.retrieve.list;

import com.gt.admin.catalogo.application.UseCase;
import com.gt.admin.catalogo.domain.pagination.Pagination;
import com.gt.admin.catalogo.domain.pagination.SearchQuery;

public abstract class ListCategoriesUseCase
                extends UseCase<SearchQuery, Pagination<CategoryListOutput>> {
}
