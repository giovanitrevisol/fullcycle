package com.gt.admin.catalogo.domain.pagination;

import java.util.List;

public record Pagination<T>(int currentPage,
                            int perpage,
                            long total,
                            List<T> itens) {


}
