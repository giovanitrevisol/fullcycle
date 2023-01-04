package com.gt.admin.catalogo.application.genre.retrieve.list;

import com.gt.admin.catalogo.application.UseCase;
import com.gt.admin.catalogo.domain.pagination.Pagination;
import com.gt.admin.catalogo.domain.pagination.SearchQuery;

public abstract class ListGenreUseCase
                extends UseCase<SearchQuery, Pagination<GenreListOutput>> {
}
