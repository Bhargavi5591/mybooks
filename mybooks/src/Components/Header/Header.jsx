import React from 'react'

export default function Header() {

    const logout = () => {
        localStorage.clear();
        window.location.href = "/";
    }


    return (
        <nav className="navbar navbar-expand-lg py-3 navbar-dark bg-dark shadow-sm">
            <div className="container">
                <a href="#" className="navbar-brand">

                    <img src="logo.jpg" width="50" alt="" className="d-inline-block align-middle mr-2" />

                    <span className="text-uppercase font-weight-bold">MyBook</span>
                </a>


                {
                    localStorage.getItem("page") === 'login' ? '' :
                        <div>
                            <button type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation" className="navbar-toggler"><span className="navbar-toggler-icon"></span></button>
                            <div id="navbarSupportedContent" className="collapse navbar-collapse">
                                <ul className="navbar-nav ml-auto">
                                    <li className="nav-item active"><a href="/booklist" className="nav-link">Home <span className="sr-only">(current)</span></a></li>
                                    {
                                        localStorage.getItem("page") === 'favbooks' ? '' :
                                            <li className="nav-item"><a href="/favBooklist" className="nav-link">Favourite Books</a></li>
                                    }
                                    <li className="nav-item"><a href="#" onClick={logout} className="nav-link">Logout</a></li>
                                </ul>
                            </div>
                        </div>
                }


            </div>

        </nav>

    );
}
