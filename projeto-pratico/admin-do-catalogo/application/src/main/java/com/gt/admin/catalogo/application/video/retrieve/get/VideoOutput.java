package com.gt.admin.catalogo.application.video.retrieve.get;

import java.time.Instant;
import java.util.Set;

import com.gt.admin.catalogo.domain.Identifier;
import com.gt.admin.catalogo.domain.utils.CollectionUtils;
import com.gt.admin.catalogo.domain.video.AudioVideoMedia;
import com.gt.admin.catalogo.domain.video.ImageMedia;
import com.gt.admin.catalogo.domain.video.Rating;
import com.gt.admin.catalogo.domain.video.Video;

public record VideoOutput(
        String id,
        Instant createdAt,
        Instant updatedAt,
        String title,
        String description,
        int launchedAt,
        double duration,
        boolean opened,
        boolean published,
        Rating rating,
        Set<String> categories,
        Set<String> genres,
        Set<String> castMembers,
        ImageMedia banner,
        ImageMedia thumbnail,
        ImageMedia thumbnailHalf,
        AudioVideoMedia video,
        AudioVideoMedia trailer) {

    public static VideoOutput from(final Video aVideo) {
        return new VideoOutput(
                aVideo.getId().getValue(),
                aVideo.getCreatedAt(),
                aVideo.getUpdatedAt(),
                aVideo.getTitle(),
                aVideo.getDescription(),
                aVideo.getLaunchedAt().getValue(),
                aVideo.getDuration(),
                aVideo.getOpened(),
                aVideo.getPublished(),
                aVideo.getRating(),
                CollectionUtils.mapTo(aVideo.getCategories(), Identifier::getValue),
                CollectionUtils.mapTo(aVideo.getGenres(), Identifier::getValue),
                CollectionUtils.mapTo(aVideo.getCastMembers(), Identifier::getValue),
                aVideo.getBanner().orElse(null),
                aVideo.getThumbnail().orElse(null),
                aVideo.getThumbnailHalf().orElse(null),
                aVideo.getVideo().orElse(null),
                aVideo.getTrailer().orElse(null));
    }
}
