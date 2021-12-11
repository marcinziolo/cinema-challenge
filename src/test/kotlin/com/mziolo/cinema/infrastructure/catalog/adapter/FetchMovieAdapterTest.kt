package com.mziolo.cinema.infrastructure.catalog.adapter

import com.marcinziolo.kotlin.wiremock.contains
import com.marcinziolo.kotlin.wiremock.equalTo
import com.marcinziolo.kotlin.wiremock.get
import com.marcinziolo.kotlin.wiremock.returnsJson
import com.mziolo.cinema.SpringAbstractTest
import com.mziolo.cinema.domain.catalog.FetchMovie
import com.mziolo.cinema.domain.catalog.ImdbId
import com.mziolo.cinema.domain.catalog.Movie
import com.mziolo.cinema.domain.catalog.dummyMovieId
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class FetchMovieAdapterTest : SpringAbstractTest() {

    @Autowired
    private lateinit var fetchMovie: FetchMovie

    @Test
    fun shouldFetchMovieDetails() {

        //given
        val imdbId = ImdbId("tt0232500")
        val expectedMovie = Movie(
            movieId = dummyMovieId,
            name = "The Fast and the Furious",
            releaseYear = 2001,
            description = plot,
            imdbRating = 6.8,
            runtimeInMinutes = 106
        )

        wiremock.get {
            url equalTo "/"
            queryParams contains "i" equalTo imdbId.id
        } returnsJson {
            body = sampleResponse
        }

        runBlocking {
            assertEquals(expectedMovie, fetchMovie(imdbId, dummyMovieId))
        }
    }

    private val runtime = "106 min"
    private val title = "The Fast and the Furious"
    private val plot =
        "Los Angeles police officer Brian O'Conner must decide where his loyalty really lies when he becomes enamored with the street racing world he has been sent undercover to destroy."

    private val sampleResponse = """
		{
		  "Title": "$title",
		  "Year": "2001",
		  "Rated": "PG-13",
		  "Released": "22 Jun 2001",
		  "Runtime": "$runtime",
		  "Genre": "Action, Crime, Thriller",
		  "Director": "Rob Cohen",
		  "Writer": "Ken Li, Gary Scott Thompson, Erik Bergquist",
		  "Actors": "Vin Diesel, Paul Walker, Michelle Rodriguez",
		  "Plot": "$plot",
		  "Language": "English, Spanish",
		  "Country": "United States, Germany",
		  "Awards": "11 wins & 18 nominations",
		  "Poster": "https://m.media-amazon.com/images/M/MV5BNzlkNzVjMDMtOTdhZC00MGE1LTkxODctMzFmMjkwZmMxZjFhXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg",
		  "Ratings": [
		    {
		      "Source": "Internet Movie Database",
		      "Value": "6.8/10"
		    },
		    {
		      "Source": "Rotten Tomatoes",
		      "Value": "54%"
		    },
		    {
		      "Source": "Metacritic",
		      "Value": "58/100"
		    }
		  ],
		  "Metascore": "58",
		  "imdbRating": "6.8",
		  "imdbVotes": "370,116",
		  "imdbID": "tt0232500",
		  "Type": "movie",
		  "DVD": "03 Jun 2003",
		  "BoxOffice": "${'$'}144,533,925",
		  "Production": "N/A",
		  "Website": "N/A",
		  "Response": "True"
		}
	""".trimIndent()
}
