package com.gt.admin.catalogo.domain.category;

public record CategorySearchQuery(int page, int perPage, String terms, String sort, String direction) {

}
