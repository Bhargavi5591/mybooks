import axios from 'axios';
import React, { useState, useEffect } from 'react'
import Card from '../Card/Card';
import Header from '../Header/Header';
import { useHistory } from 'react-router';
import favBookService from "../../services/favBookService";
import openlibraryService from '../../services/openlibraryService';


function BookList(props) {

    const [error, setError] = useState(null);
    const [bookdata, setdata] = useState('');
    const [searchTerm, setSearchTerm] = useState('');
    const [optionValue, setOptionValue] = useState('');

    let history = useHistory();

    localStorage.setItem('page', 'home');

    const booklist = () => {
        history.push("/booklist")
    }

    let bookData = [];

    useEffect(() => {
        openlibraryService.getBooks()
            .then((res) => {
                Object.keys(res.data.works).map((k) => {
                    let coverpagelink;
                    if (res.data.works[k].cover_id !== null) {
                        coverpagelink = "http://covers.openlibrary.org/b/id/" + res.data.works[k].cover_id + "-L.jpg"
                    }
                    bookData.push({
                        "title": res.data.works[k].title,
                        "bookkey": res.data.works[k].key,
                        "authorskey": res.data.works[k].authors[0].key,
                        "authorsname": res.data.works[k].authors[0].name,
                        "cover_id": res.data.works[k].cover_id,
                        "coverpagelink": coverpagelink,
                    })
                });
                setdata(bookData)
            }).catch(err => {
                setError('Failed to get Books')
            }
            )
    }, [])


    const handleOnSubmit = (e) => {
        e.preventDefault();
        if (searchTerm) {
            openlibraryService.getSearchBooks(optionValue, searchTerm)
                .then((res) => {
                    getfavBookData(res.data)
                }).catch(err => {
                    setError('Failed to get Books')
                })
        }
        setSearchTerm('');
    }

    const getfavBookData = (data) => {
        Object.keys(data.docs).map((k) => {
            let id = data.docs[k].cover_id === 'undefined' ? data.docs[k].cover_id : data.docs[k].cover_i;
            bookData.push({
                "title": data.docs[k].title,
                "bookkey": data.docs[k].key,
                "authorskey": Array.isArray(data.docs[k].author_key) ? data.docs[k].author_key[0] : data.docs[k].author_key,
                "authorsname": Array.isArray(data.docs[k].author_name) ? data.docs[k].author_name[0] : data.docs[k].author_name,
                "id": id,
                "coverpagelink": typeof id === 'undefined' ? 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTZ6nBZmN2eqi1bPJmBSiyDqFdcW7WwWextqw&usqp=CAU' : "http://covers.openlibrary.org/b/id/" + id + "-L.jpg"
            })
        });
        setdata(bookData)
    }


    const handleOnChange = (e) => {
        setSearchTerm(e.target.value);
    }

    const addToFav = (data) => {
        favBookService.addFavBook(data)
            .then(res => {
                history.push("/favBooklist")
            }).catch(err => {
                console.log(err)
                setError('Failed to Add Books to Fav list')
            })
    }

    const handleSelect = (e) => {
        setOptionValue(e.target.value);
    };

    return (<>
        <Header />
        {
            (error) ?
                <div className="loginError">Error: {error}</div>
                : ''
        }
        <header>
            <form onSubmit={handleOnSubmit} className="d-flex">
                <select className="btn bookdropdown dropdown-toggle" value={optionValue} onChange={handleSelect}>
                    <option value="All">All</option>
                    <option value="Title">Title</option>
                    <option value="Author">Author</option>
                </select>
                <input type="search"
                    className="form-control me-2"
                    placeholder="Search..."
                    value={searchTerm}
                    onChange={handleOnChange}
                    aria-label="Search" />
                <button className="btn btn-outline-success" type="submit">Search</button>
            </form>
        </header>

        <div className="book-container">
            {
                Array.isArray(bookdata) ? bookdata.map((book) => (
                    <Card bookkey={book.bookkey}
                        title={book.title}
                        cover_id={book.cover_id}
                        id={book.cover_id}
                        authorskey={book.authorskey}
                        authorsname={book.authorsname}
                        coverpagelink={book.coverpagelink}
                        userId={localStorage.getItem("userId")}
                        addtofavEvent={addToFav}
                    />
                )) : (
                    "No Books found"
                )
            }
        </div>


    </>
    );
}

export default BookList;