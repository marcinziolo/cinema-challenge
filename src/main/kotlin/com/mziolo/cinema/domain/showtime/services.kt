package com.mziolo.cinema.domain.showtime

typealias UpdateShowTime = (ShowTime) -> Unit //port
typealias FetchShowTime = (ShowTimeDate) -> List<ShowTime> //port