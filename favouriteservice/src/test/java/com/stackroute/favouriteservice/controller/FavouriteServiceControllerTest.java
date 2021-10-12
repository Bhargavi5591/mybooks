package com.stackroute.favouriteservice.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.stackroute.favouriteservice.exception.FavBookNotFoundException;
import com.stackroute.favouriteservice.model.Book;
import com.stackroute.favouriteservice.service.FavouriteService;

@SpringBootTest
public class FavouriteServiceControllerTest {
	private MockMvc mockMvc;
	private Book book;
	private List<Book> bookList;

	@Mock
	FavouriteService favouriteService;
	@InjectMocks
	FavouriteServiceController favouriteServiceController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		bookList = new ArrayList<Book>();
		mockMvc = MockMvcBuilders.standaloneSetup(favouriteServiceController).build();		 
		  
		book = new Book("b1", "sample bookkey", 1,"sample title","sample authorskey","sample authorsname"
				);
		Book book1 = new Book("b2", "sample bookkey", 1,"sample title","sample authorskey","sample authorsname");
		Book book2 = new Book("b1", "sample bookkey", 2,"sample title","sample authorskey","sample authorsname");
		bookList.add(book1);
		bookList.add(book2);
	}

	@Test
	public void getFavBooks() throws Exception {
		when(favouriteService.getFavBooks(1)).thenReturn(bookList);
		mockMvc.perform(get("/api/v1/book/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void getFavBooksFailure() throws Exception {
		when(favouriteService.getFavBooks(2)).thenThrow(FavBookNotFoundException.class);
		mockMvc.perform(get("/api/v1/book/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void addFavBookSuccess() throws Exception {

		when(favouriteService.addFavBook(any())).thenReturn(true);
		mockMvc.perform(post("/api/v1/book").contentType(MediaType.APPLICATION_JSON).content(asJsonString(book)))
				.andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());

	}

	@SuppressWarnings("unchecked")
	@Test
	public void addFavBookFailure() throws Exception {
		when(favouriteService.addFavBook(any())).thenReturn(false);
		mockMvc.perform(post("/api/v1/book").contentType(MediaType.APPLICATION_JSON).content(asJsonString(book)))
				.andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());

	}
	
	   @Test
	    public void deleteFavBookSuccess() throws Exception {

	        when(favouriteService.deleteFavBook(1, "12345")).thenReturn(true);
	        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/book/deletebook/1/12345")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(MockMvcResultMatchers.status().isOk())
	                .andDo(MockMvcResultHandlers.print());
	    }


	    @Test
	    public void deleteallfavBooksSuccess() throws Exception {

	        when(favouriteService.deleteallfavBooks(1)).thenReturn(false);
	        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/book/1")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(MockMvcResultMatchers.status().isOk())
	                .andDo(MockMvcResultHandlers.print());
	    }



	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
