package com.mziolo.cinema.infrastructure.showtime

import com.mziolo.cinema.domain.showtime.ShowTime
import com.mziolo.cinema.domain.showtime.UpdateShowTime
import org.springframework.stereotype.Component

@Component
class UpdateShowTimeAdapter(

): UpdateShowTime {
    override fun invoke(showTime: ShowTime) {
    }
}