import React, { useState } from 'react'
import axios from 'axios';
import { useHistory } from 'react-router';
import Header from '../Header/Header';

export default function Login() {
    const [emailId, setEmailId] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState(null);

    localStorage.setItem('page', 'login');

    let history = useHistory();

    const loginUser = (e) => {
        e.preventDefault();
        localStorage.clear();
        axios.post('http://localhost:9098/api/v1/login',
            { emailId, password },
            {
                headers: {
                    'Content-type': 'application/json'
                }
            }
        ).then((res) => {
            console.log('token', res.data?.key)
            localStorage.setItem('token', res.data?.key);
            localStorage.setItem('isAuthenticated', true);
            localStorage.setItem('userId', res.data?.userId);
            localStorage.setItem('userName', res.data?.userName);
            history.push("/booklist")
        }
        )
            .catch(err => {
                console.log(err)
                setError('Login Failed!!!')
            })
    }
    const register = () => {
        history.push("/register")
    }

    return (

        <section className="vh-100 gradient-custom">
            <Header />
            <div className="container py-5 h-100">
                <div className="row d-flex justify-content-center align-items-center h-100">
                    <div className="col-12 col-md-8 col-lg-6 col-xl-5">

                        <div className="card bg-dark text-white" style={{ "border-radius": "1rem" }}>

                            <div className="card-body p-5 text-center">

                                <div className="mb-md-5 mt-md-4 pb-5">
                                    <form onSubmit={
                                        (e) => {
                                            loginUser(e);
                                        }
                                    }>

                                        <h2 className="fw-bold mb-2 text-uppercase">Login</h2>
                                        <p className="text-white-50 mb-5">Please enter your email and password!</p>

                                        <div className="form-outline form-white mb-4">
                                            <input type="email" id="email" placeholder="Email" className="form-control form-control-lg"
                                                onChange={
                                                    (evt) => setEmailId(evt.target.value)
                                                } required
                                            />
                                            <label className="form-label" for="typeEmailX">Email</label>
                                        </div>

                                        <div className="form-outline form-white mb-4">
                                            <input type="password" id="password" placeholder="Password" className="form-control form-control-lg"
                                                onChange={
                                                    (evt) => setPassword(evt.target.value)
                                                } required
                                            />
                                            <label className="form-label" >Password</label>
                                        </div>

                                        <button className="btn btn-outline-light btn-lg px-5" type="submit">Login</button>
                                        {
                                            error ? <div className="loginError">{error} </div> : ''
                                        }

                                    </form>
                                </div>


                                <div>
                                    <p className="mb-0">Not Registered yet?.Go to registration <a href="#!" className="text-white-50 fw-bold" onClick={register}>Sign Up</a></p>
                                    <div class="footer-copyright text-center py-3">© 2020 Copyright:</div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </section>

    )

}
