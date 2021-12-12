package com.mziolo.cinema.infrastructure.rest

import com.mziolo.cinema.domain.RatingFlow
import com.mziolo.cinema.domain.catalog.InvalidMovieId
import com.mziolo.cinema.domain.catalog.MovieId
import com.mziolo.cinema.domain.rating.MovieVote
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/rating")
class RatingController(
    private val ratingFlow: RatingFlow
) {

    @PostMapping("/{id}")
    suspend fun rateMovie(@PathVariable id: UUID, @RequestBody movieVoteDto: MovieVoteDto): ResponseEntity<Unit> =
        ResponseEntity.ok(ratingFlow.voteMovie(MovieVote(MovieId(id), movieVoteDto.value)))

    @ExceptionHandler(InvalidMovieId::class)
    fun handleInvalidMovieId(): ResponseEntity<Unit> = ResponseEntity.status(404).build()

}