package com.gt.admin.catalogo.application.genre.create;

import com.gt.admin.catalogo.domain.genre.Genre;

public record CreateGenreOutput(
        String id) {

    public static CreateGenreOutput from(final String anId) {
        return new CreateGenreOutput(anId);
    }

    public static CreateGenreOutput from(final Genre aGenre) {
        return new CreateGenreOutput(aGenre.getId().getValue());
    }
}
