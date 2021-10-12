package com.stackroute.favouriteservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.favouriteservice.model.Book;

@Repository
public interface FavouriteBooksRepository extends MongoRepository<Book, Integer> {
	List<Book> findByUserId(int userId);

	Book findById(String id);
	
	Book findByUserIdAndId(int userId, String id);

	void deleteByUserIdAndId(int userId, String id);

	void deleteAllByUserId(int userId);

}
