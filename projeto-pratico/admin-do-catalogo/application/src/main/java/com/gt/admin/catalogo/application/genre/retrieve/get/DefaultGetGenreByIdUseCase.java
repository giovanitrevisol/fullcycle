package com.gt.admin.catalogo.application.genre.retrieve.get;

import java.util.Objects;

import com.gt.admin.catalogo.domain.exceptions.NotFoundException;
import com.gt.admin.catalogo.domain.genre.Genre;
import com.gt.admin.catalogo.domain.genre.GenreGateway;
import com.gt.admin.catalogo.domain.genre.GenreID;

public class DefaultGetGenreByIdUseCase extends GetGenreByIdUseCase {

    private final GenreGateway genreGateway;

    public DefaultGetGenreByIdUseCase(final GenreGateway genreGateway) {
        this.genreGateway = Objects.requireNonNull(genreGateway);
    }

    @Override
    public GenreOutput execute(final String anIn) {
        final var aGenreId = GenreID.from(anIn);
        return this.genreGateway.findById(aGenreId)
                .map(GenreOutput::from)
                .orElseThrow(() -> NotFoundException.with(Genre.class, aGenreId));
    }
}
