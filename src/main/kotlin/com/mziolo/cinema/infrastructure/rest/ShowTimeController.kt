package com.mziolo.cinema.infrastructure.rest

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/showtime")
class ShowTimeController {

    @PutMapping("/{id}")
    fun putShowTIme(@PathVariable id: UUID, @RequestBody dto: UpdateShowTimeDto ): String {
        return "Test";
    }
}