package com.stackroute.favouriteservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.favouriteservice.exception.FavBookNotFoundException;
import com.stackroute.favouriteservice.model.Book;
import com.stackroute.favouriteservice.service.FavouriteService;

@RestController
public class FavouriteServiceController {
	
	@Autowired
	FavouriteService favouriteService;

	FavouriteServiceController(FavouriteService favouriteService) {
		this.favouriteService = favouriteService;
	}

	@PostMapping("/api/v1/book")
	public ResponseEntity<?> addFavBook(@RequestBody Book book) {

		boolean result = favouriteService.addFavBook(book);
		if (result) {
			return new ResponseEntity<Book>(book, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Book not added to Favourite list", HttpStatus.CONFLICT);
		}
	}
	
	@DeleteMapping("/api/v1/book/deletebook/{userId}/{id}")
	public ResponseEntity<?> deleteFavBook(@PathVariable("userId") int userId, @PathVariable("id") String id) {
		if (favouriteService.deleteFavBook(userId, id)) {
			return new ResponseEntity<String>("Deleted", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Favourite book not found", HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/api/v1/book/{userId}")
	public ResponseEntity<?> deleteallfavBooks(@PathVariable("userId") int userId) {

		try {
			favouriteService.deleteallfavBooks(userId);
			return new ResponseEntity<String>("Deleted", HttpStatus.OK);
		} catch (FavBookNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/api/v1/book/{userId}")
	public ResponseEntity<?> getFavBooks(@PathVariable("userId") int userId) {
		List<Book> favBooks;
		try {
			favBooks = favouriteService.getFavBooks(userId);
			if (favBooks == null || favBooks.size()==0) {
				return new ResponseEntity<String>("Books Not Found", HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<List<Book>>(favBooks, HttpStatus.OK);
		} catch (FavBookNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
