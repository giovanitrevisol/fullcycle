package com.gt.admin.catalogo.application.video.media.get;

import java.util.Objects;

import com.gt.admin.catalogo.domain.exceptions.NotFoundException;
import com.gt.admin.catalogo.domain.validation.Error;
import com.gt.admin.catalogo.domain.video.MediaResourceGateway;
import com.gt.admin.catalogo.domain.video.VideoID;
import com.gt.admin.catalogo.domain.video.VideoMediaType;

public class DefaultGetMediaUseCase extends GetMediaUseCase {

    private final MediaResourceGateway mediaResourceGateway;

    public DefaultGetMediaUseCase(final MediaResourceGateway mediaResourceGateway) {
        this.mediaResourceGateway = Objects.requireNonNull(mediaResourceGateway);
    }

    @Override
    public MediaOutput execute(final GetMediaCommand aCmd) {
        final var anId = VideoID.from(aCmd.videoId());
        final var aType = VideoMediaType.of(aCmd.mediaType())
                .orElseThrow(() -> typeNotFound(aCmd.mediaType()));

        final var aResource = this.mediaResourceGateway.getResource(anId, aType)
                .orElseThrow(() -> notFound(aCmd.videoId(), aCmd.mediaType()));

        return MediaOutput.with(aResource);
    }

    private NotFoundException notFound(final String anId, final String aType) {
        return NotFoundException.with(new Error("Resource %s not found for video %s".formatted(aType, anId)));
    }

    private NotFoundException typeNotFound(final String aType) {
        return NotFoundException.with(new Error("Media type %s doesn't exists".formatted(aType)));
    }
}
