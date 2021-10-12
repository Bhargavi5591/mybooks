import axios from "axios";

class openlibraryService {

    getBooks() {
        return axios.get(`https://openlibrary.org/subjects/picture_books.json`);
    }
    
    getSearchBooks(optionValue,searchTerm){
        if (optionValue === 'Title') {
            return axios.get(`http://openlibrary.org/search.json?title=`+searchTerm);
        } else if (optionValue === 'All') {
            return axios.get(`http://openlibrary.org/search.json?author=`+searchTerm);
        } else {
            return  axios.get(`http://openlibrary.org/search.json?q=`+searchTerm);
        }
    }
}

export default new openlibraryService();
