package com.mziolo.cinema.domain.showtime

typealias UpdateShowTime = (ShowTime) -> Unit
typealias FetchShowTime = (ShowTimeDate) -> List<ShowTime>