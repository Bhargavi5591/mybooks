import axios from "axios";



class favBookService {


    getFavBooks(id) {        
        return axios.get(`http://localhost:9097/api/v1/book/${id}`, {
            headers: { "Authorization": `Bearer ${localStorage.getItem("token")}` }
        });
    }

    delete(uid, bid) {
        return axios.delete(`http://localhost:9097/api/v1/book/deletebook/${uid}/${bid}`, {
            headers: { "Authorization": `Bearer ${localStorage.getItem("token")}` }
        });
    }


    addFavBook(data) {        
        return axios.post(`http://localhost:9097/api/v1/book`, data,
            {
                headers: {
                    "Authorization": `Bearer ${localStorage.getItem("token")}`
                }
            });
    }
}

export default new favBookService();
