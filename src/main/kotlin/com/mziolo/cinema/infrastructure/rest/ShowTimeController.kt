package com.mziolo.cinema.infrastructure.rest

import com.mziolo.cinema.domain.ShowTimeFlow
import com.mziolo.cinema.domain.catalog.InvalidMovieId
import com.mziolo.cinema.domain.catalog.MovieId
import com.mziolo.cinema.domain.showtime.ShowTime
import com.mziolo.cinema.domain.showtime.ShowTimeDate
import com.mziolo.cinema.domain.showtime.ShowTimeId
import com.mziolo.cinema.domain.showtime.ShowTimeOverlapped
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

@RestController
@RequestMapping("/showtime")
class ShowTimeController(
    private val showTimeFlow: ShowTimeFlow
) {

    @PutMapping("/{id}")
    suspend fun updateShowTime(@PathVariable id: UUID, @RequestBody dto: UpdateShowTimeDto) =
        ResponseEntity.ok(showTimeFlow.updateShowTime(dto.toShowTime(id)))

    @GetMapping("/{date}")
    suspend fun getShowTimes(@PathVariable date: String) =
        ResponseEntity.ok(showTimeFlow.fetchShowTimes(ShowTimeDate(LocalDate.parse(date))).map(ShowTime::toDto))


    @ExceptionHandler(ShowTimeOverlapped::class)
    fun handleShowTimeOverlapped(): ResponseEntity<Unit> = ResponseEntity.status(409).build()

    @ExceptionHandler(InvalidMovieId::class)
    fun handleInvalidMovieId(): ResponseEntity<Unit> = ResponseEntity.status(404).build()
}

private fun UpdateShowTimeDto.toShowTime(id: UUID): ShowTime {
    return ShowTime(
        showTimeId = ShowTimeId(id),
        movieId = MovieId(this.movieId),
        time = LocalTime.parse(this.time),
        date = ShowTimeDate(LocalDate.parse(this.day)),
        price = BigDecimal(this.price),
        runtime = null
    )
}

private fun ShowTime.toDto(): ShowTimeDto = ShowTimeDto(
    showTimeId = this.showTimeId.id,
    movieId = this.movieId.id,
    runtime = this.runtime!!,
    price = "${this.price} USD",
    day = this.date.date.toString(),
    time = this.time.toString()
)