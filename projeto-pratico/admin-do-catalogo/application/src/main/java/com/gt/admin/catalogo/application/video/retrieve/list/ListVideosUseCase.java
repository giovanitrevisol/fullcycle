package com.gt.admin.catalogo.application.video.retrieve.list;

import com.gt.admin.catalogo.application.UseCase;
import com.gt.admin.catalogo.domain.pagination.Pagination;
import com.gt.admin.catalogo.domain.video.VideoSearchQuery;

public abstract class ListVideosUseCase
                extends UseCase<VideoSearchQuery, Pagination<VideoListOutput>> {
}
