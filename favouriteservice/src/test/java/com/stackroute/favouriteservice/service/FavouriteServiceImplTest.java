package com.stackroute.favouriteservice.service;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stackroute.favouriteservice.exception.FavBookNotFoundException;
import com.stackroute.favouriteservice.model.Book;
import com.stackroute.favouriteservice.repository.FavouriteBooksRepository;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class FavouriteServiceImplTest {

	private Book book;
	@Mock
	private FavouriteBooksRepository favBooksRepository;
	@InjectMocks
	private FavouriteServiceImpl favouriteServiceImpl;
	private List<Book> list = null;

	@BeforeEach
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);

		book = new Book();
		book.setAuthorskey("sample authors key");
		book.setAuthorsname("sample author name");
		book.setBookkey("sample book key");
		book.setId("book1");
		book.setUserId(1);
		book.setTitle("title");

		list = new ArrayList<>();
		list.add(book);

	}

	@Test
	public void addFavBookSuccess() {
		when(favBooksRepository.save(book)).thenReturn(book);
		boolean status = favouriteServiceImpl.addFavBook(book);
		assertEquals(true, status);
	}

	@Test
	public void deleteFavBooksSuccess() {
		when(favBooksRepository.findByUserId(book.getUserId())).thenReturn(list);
        when(favBooksRepository.findById(book.getId())).thenReturn(book);
		boolean status = favouriteServiceImpl.deleteFavBook(book.getUserId(), "book1");
		assertEquals(true, status);
	}

	@Test
	public void getFavBooksSuccess() throws FavBookNotFoundException {

		when(favBooksRepository.findByUserId(book.getUserId())).thenReturn(list);
		List<Book> booklist1 = favouriteServiceImpl.getFavBooks(book.getUserId());
		assertEquals(list, booklist1);
	}
	
    @Test
    public void getFavBooksTestFailure() throws FavBookNotFoundException {
    	when(favBooksRepository.findByUserId(book.getUserId())).thenThrow(NoSuchElementException.class);
    	Book fecthedBook = (Book) favouriteServiceImpl.getFavBooks(book.getUserId());
        assertNull(fecthedBook);

    }

	@Test
	public void deleteAllBooksSuccess() throws FavBookNotFoundException {
		when(favBooksRepository.findByUserId(book.getUserId())).thenReturn(list);
        when(favBooksRepository.findById(book.getId())).thenReturn(book);
		boolean status = favouriteServiceImpl.deleteallfavBooks(book.getUserId());
		assertEquals(true, status);
	}
}
