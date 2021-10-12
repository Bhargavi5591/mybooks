package com.stackroute.favouriteservice.service;

import java.util.List;

import com.stackroute.favouriteservice.exception.FavBookNotFoundException;
import com.stackroute.favouriteservice.model.Book;

public interface FavouriteService {

	boolean addFavBook(Book book);

	List<Book> getFavBooks(int userId) throws FavBookNotFoundException;;

	boolean deleteFavBook(int userId, String id);

	boolean deleteallfavBooks(int userId) throws FavBookNotFoundException;;

}
