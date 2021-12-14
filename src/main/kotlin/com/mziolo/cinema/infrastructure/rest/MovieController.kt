package com.mziolo.cinema.infrastructure.rest

import com.mziolo.cinema.domain.MovieFacade
import com.mziolo.cinema.domain.MovieWithRating
import com.mziolo.cinema.domain.catalog.InvalidMovieId
import com.mziolo.cinema.domain.catalog.MovieId
import com.mziolo.cinema.domain.rating.Rating.NoRatingYet
import com.mziolo.cinema.domain.rating.Rating.RatingValue
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/movie")
class MovieController(private val movieFacade: MovieFacade) {

    @GetMapping("/")
    suspend fun getMovies(): ResponseEntity<Collection<MovieDto>> =
        movieFacade.getAllMovies()
            .map { it.toDto() }
            .let { ResponseEntity.ok(it) }

    @GetMapping("/{id}")
    suspend fun getMovie(@PathVariable id: UUID): ResponseEntity<MovieDto> =
        movieFacade.getMovie(MovieId(id))
            .let { it.toDto() }
            .let { ResponseEntity.ok(it) }

    @ExceptionHandler(InvalidMovieId::class)
    fun handleInvalidMovieId(): ResponseEntity<Unit> = ResponseEntity.status(404).build()

    private fun MovieWithRating.toDto(): MovieDto {
        val movie = this.movie
        val userRating = when (this.rating) {
            is RatingValue -> this.rating.value.toString()
            is NoRatingYet -> "n/a"
        }
        return MovieDto(
            id = movie.movieId.id,
            name = movie.name,
            description = movie.description,
            releaseYear = movie.releaseYear,
            imdbRating = movie.imdbRating,
            userRating = userRating,
            runtimeInMinutes = movie.runtime
        )
    }
}