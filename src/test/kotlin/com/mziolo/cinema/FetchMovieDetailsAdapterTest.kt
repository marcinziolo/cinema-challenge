package com.mziolo.cinema

import com.marcinziolo.kotlin.wiremock.contains
import com.marcinziolo.kotlin.wiremock.equalTo
import com.marcinziolo.kotlin.wiremock.get
import com.marcinziolo.kotlin.wiremock.returnsJson
import com.mziolo.cinema.domain.catalog.FetchMovieDetails
import com.mziolo.cinema.domain.catalog.ImdbMovieId
import com.mziolo.cinema.domain.catalog.InitializeMovieCatalog
import com.mziolo.cinema.domain.catalog.MovieDetails
import com.mziolo.cinema.infrastructure.ImdbMovies
import com.mziolo.cinema.infrastructure.catalog.FetchMovieDetailsAdapter
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock
import org.springframework.context.annotation.Profile
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDate

@SpringBootTest
@AutoConfigureWireMock
class FetchMovieDetailsAdapterTest: AbstractTest() {

	@Autowired
	private lateinit var imdbMovies: ImdbMovies

	@Autowired
	private lateinit var fetchMovieDetails: FetchMovieDetails

	@Test
	fun contextLoads() {

		//given
		val imdbMovieId = ImdbMovieId("tt0232500")
		val expectedMovieDetails = MovieDetails(
			name = "The Fast and the Furious",
			releaseYear = 2001,
			description = plot,
			imdbRating = 6.8,
			runtime = runtime
		)

		wiremock.get {
			url equalTo "/"
			queryParams contains "i" equalTo imdbMovieId.id
		} returnsJson {
			body = sampleResponse
		}

		runBlocking {
			assertEquals(expectedMovieDetails,fetchMovieDetails(imdbMovieId))
		}
	}

	private val runtime = "106 min"
	private val title = "The Fast and the Furious"
	private val plot = "Los Angeles police officer Brian O'Conner must decide where his loyalty really lies when he becomes enamored with the street racing world he has been sent undercover to destroy."


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
