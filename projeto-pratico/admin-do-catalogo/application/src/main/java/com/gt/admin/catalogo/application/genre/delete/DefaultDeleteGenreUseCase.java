package com.gt.admin.catalogo.application.genre.delete;

import java.util.Objects;

import com.gt.admin.catalogo.domain.genre.GenreGateway;
import com.gt.admin.catalogo.domain.genre.GenreID;

public class DefaultDeleteGenreUseCase extends DeleteGenreUseCase {

    private final GenreGateway genreGateway;

    public DefaultDeleteGenreUseCase(final GenreGateway genreGateway) {
        this.genreGateway = Objects.requireNonNull(genreGateway);
    }

    @Override
    public void execute(final String anIn) {
        this.genreGateway.deleteById(GenreID.from(anIn));
    }
}
