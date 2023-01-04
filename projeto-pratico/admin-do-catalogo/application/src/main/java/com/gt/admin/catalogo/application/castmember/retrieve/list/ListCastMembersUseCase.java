package com.gt.admin.catalogo.application.castmember.retrieve.list;

import com.gt.admin.catalogo.application.UseCase;
import com.gt.admin.catalogo.domain.pagination.Pagination;
import com.gt.admin.catalogo.domain.pagination.SearchQuery;

public sealed abstract class ListCastMembersUseCase
                extends UseCase<SearchQuery, Pagination<CastMemberListOutput>> permits DefaultListCastMembersUseCase {
}
