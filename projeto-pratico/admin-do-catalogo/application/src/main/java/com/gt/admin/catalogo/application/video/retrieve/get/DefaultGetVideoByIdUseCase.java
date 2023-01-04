package com.gt.admin.catalogo.application.video.retrieve.get;

import java.util.Objects;

import com.gt.admin.catalogo.domain.exceptions.NotFoundException;
import com.gt.admin.catalogo.domain.video.Video;
import com.gt.admin.catalogo.domain.video.VideoGateway;
import com.gt.admin.catalogo.domain.video.VideoID;

public class DefaultGetVideoByIdUseCase extends GetVideoByIdUseCase {

    private final VideoGateway videoGateway;

    public DefaultGetVideoByIdUseCase(final VideoGateway videoGateway) {
        this.videoGateway = Objects.requireNonNull(videoGateway);
    }

    @Override
    public VideoOutput execute(final String anIn) {
        final var aVideoId = VideoID.from(anIn);
        return this.videoGateway.findById(aVideoId)
                .map(VideoOutput::from)
                .orElseThrow(() -> NotFoundException.with(Video.class, aVideoId));
    }
}
