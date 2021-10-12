import React from 'react'

export default function Card(props) {
    const addToFav = (e) => {
        props.addtofavEvent(e)
    }
    const deleteBook = (e) => {
        const myObj = JSON.stringify(e);
        const data = JSON.parse(myObj);
        props.deletebookEvent(data.id)
    }
    return (

        <div className="book">
            <img src={
                props.coverpagelink ?
                    props.coverpagelink :
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTZ6nBZmN2eqi1bPJmBSiyDqFdcW7WwWextqw&usqp=CAU"
            } />
            <div className="book-info">
                <h6>{props.title}</h6>
                <span>{props.authorsname}</span>
            </div>

            <div className="book-over">
                <h6>{props.title}</h6>
                <span>{props.authorsname}</span>

                {
                    props.page === 'favourite' ?
                        <div>
                            <a href="#" onClick={
                                () => deleteBook(props)
                            }
                                class="btn btn-outline-danger a-btn-slide-text">
                                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                                <span><strong>Delete</strong></span>
                            </a>
                        </div>
                        :
                        <div>
                            <button type="button" class="btn favbutton" onClick={
                                () => addToFav(props)
                            }>Add to Favourite</button>
                        </div>
                }



            </div>
        </div>




    );
}
