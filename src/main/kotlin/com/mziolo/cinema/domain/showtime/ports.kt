package com.mziolo.cinema.domain.showtime

typealias SaveShowTime = suspend (ShowTime) -> Unit

typealias FetchShowTimes = suspend (ShowTimeDate) -> ShowTimes


