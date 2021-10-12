import React, { useState, useEffect } from 'react'
import Card from '../Card/Card';
import Header from '../Header/Header';
import favBookService from "../../services/favBookService";

function FavBookList(props) {
  const [favbookdata, setdata] = useState('');

  localStorage.setItem('page', 'favbooks');

  var userId = localStorage.getItem('userId');

  useEffect(() => {
    favBookService.getFavBooks(userId)
      .then(response => {
        setdata(response.data)
      })
      .catch(err => {
        console.log(err)
      });
  }, [])


  const deleteBook = (id) => {
    favBookService.delete(userId, id)
      .then((res) => {
        if (res.status === 200) {
          console.log("deleted");
          setdata(favbookdata.filter((book) => book.id != id));
        }
      })
      .catch((err) => console.log(err));
  }

  return (<>
    <Header />
    <div className="book-container">

      {
        Array.isArray(favbookdata) ? favbookdata.map((book) => (
          <Card key={book.title}
            title={book.title}
            bookkey={book.bookkey}
            authorskey={book.authorskey}
            authorsname={book.authorsname}
            id={book.id}
            coverpagelink={"http://covers.openlibrary.org/b/id/" + book.id + "-L.jpg"}
            page="favourite"
            deletebookEvent={deleteBook}
          />
        )) : (
          "No Books found"
        )}
    </div>

  </>
  );

}

export default FavBookList;