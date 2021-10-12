package com.stackroute.favouriteservice.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.favouriteservice.exception.FavBookNotFoundException;
import com.stackroute.favouriteservice.model.Book;
import com.stackroute.favouriteservice.repository.FavouriteBooksRepository;

@Service
public class FavouriteServiceImpl implements FavouriteService {

	@Autowired
	FavouriteBooksRepository favBookRepo;

	FavouriteServiceImpl(FavouriteBooksRepository favBookRepo) {
		this.favBookRepo = favBookRepo;
	}

	public boolean addFavBook(Book book) {
		Book bookExists = favBookRepo.findByUserIdAndId(book.getUserId(), book.getId());
		if (null == bookExists) {
			favBookRepo.save(book);
			return true;
		}
		return false;
	}

	public boolean deleteFavBook(int userId, String id) {
		List<Book> list = favBookRepo.findByUserId(userId);
		if (list.size() > 0) {
			favBookRepo.deleteByUserIdAndId(userId, id);
			return true;
		}
		return false;

	}

	public boolean deleteallfavBooks(int userId) throws FavBookNotFoundException {
		List<Book> list = favBookRepo.findByUserId(userId);
		if (list.size() > 0) {
			favBookRepo.deleteAllByUserId(userId);
			return true;
		}

		throw new FavBookNotFoundException("Favourite Book Not Found");
	}

	public List<Book> getFavBooks(int userId) throws FavBookNotFoundException {
		try {
			return favBookRepo.findByUserId(userId);
		} catch (NoSuchElementException e) {
			return null;
		}

	}

}
