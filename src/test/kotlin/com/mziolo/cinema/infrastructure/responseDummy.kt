package com.mziolo.cinema.infrastructure

internal val runtime = "106 min"
internal val title = "The Fast and the Furious"
internal val plot =
    "Los Angeles police officer Brian O'Conner must decide where his loyalty really lies when he becomes enamored with the street racing world he has been sent undercover to destroy."

internal val sampleResponse = """
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