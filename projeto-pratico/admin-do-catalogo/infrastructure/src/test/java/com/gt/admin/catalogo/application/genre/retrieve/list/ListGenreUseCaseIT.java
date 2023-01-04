package com.gt.admin.catalogo.application.genre.retrieve.list;

import com.gt.admin.catalogo.IntegrationTest;
import com.gt.admin.catalogo.application.genre.retrieve.list.GenreListOutput;
import com.gt.admin.catalogo.application.genre.retrieve.list.ListGenreUseCase;
import com.gt.admin.catalogo.domain.genre.Genre;
import com.gt.admin.catalogo.domain.genre.GenreGateway;
import com.gt.admin.catalogo.domain.pagination.SearchQuery;
import com.gt.admin.catalogo.infrastructure.genre.persistence.GenreJpaEntity;
import com.gt.admin.catalogo.infrastructure.genre.persistence.GenreRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@IntegrationTest
public class ListGenreUseCaseIT {

        @Autowired
        private ListGenreUseCase useCase;

        @Autowired
        private GenreGateway genreGateway;

        @Autowired
        private GenreRepository genreRepository;

        @Test
        public void givenAValidQuery_whenCallsListGenre_shouldReturnGenres() {
                // given
                final var genres = List.of(
                                Genre.newGenre("Ação", true),
                                Genre.newGenre("Aventura", true));

                genreRepository.saveAllAndFlush(
                                genres.stream()
                                                .map(GenreJpaEntity::from)
                                                .toList());

                final var expectedPage = 0;
                final var expectedPerPage = 10;
                final var expectedTerms = "A";
                final var expectedSort = "createdAt";
                final var expectedDirection = "asc";
                final var expectedTotal = 2;

                final var expectedItems = genres.stream()
                                .map(GenreListOutput::from)
                                .toList();

                final var aQuery = new SearchQuery(expectedPage, expectedPerPage, expectedTerms, expectedSort,
                                expectedDirection);

                // when
                final var actualOutput = useCase.execute(aQuery);

                // then
                Assertions.assertEquals(expectedPage, actualOutput.currentPage());
                Assertions.assertEquals(expectedPerPage, actualOutput.perPage());
                Assertions.assertEquals(expectedTotal, actualOutput.total());
                Assertions.assertTrue(
                                expectedItems.size() == actualOutput.items().size()
                                                && expectedItems.containsAll(actualOutput.items()));
        }

        @Test
        public void givenAValidQuery_whenCallsListGenreAndResultIsEmpty_shouldReturnGenres() {
                // given
                final var genres = List.<Genre>of();

                final var expectedPage = 0;
                final var expectedPerPage = 10;
                final var expectedTerms = "A";
                final var expectedSort = "createdAt";
                final var expectedDirection = "asc";
                final var expectedTotal = 0;

                final var expectedItems = List.<GenreListOutput>of();

                final var aQuery = new SearchQuery(expectedPage, expectedPerPage, expectedTerms, expectedSort,
                                expectedDirection);

                // when
                final var actualOutput = useCase.execute(aQuery);

                // then
                Assertions.assertEquals(expectedPage, actualOutput.currentPage());
                Assertions.assertEquals(expectedPerPage, actualOutput.perPage());
                Assertions.assertEquals(expectedTotal, actualOutput.total());
                Assertions.assertEquals(expectedItems, actualOutput.items());
        }
}
