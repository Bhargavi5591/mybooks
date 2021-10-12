import React, { useState } from 'react'
import axios from 'axios';
import { useHistory } from 'react-router';
import Header from '../Header/Header';

export default function Register() {

    const login = () => {
        history.push("/login")
    }

    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [emailId, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState(null);

    let history = useHistory();
    const register = (e) => {
        e.preventDefault();
        axios.post('http://localhost:9098/api/v1/addUser',
            { firstName, lastName, emailId, password },
            {
                headers: {
                    'Content-type': 'application/json'
                }
            }
        ).then((res) => {
            history.push("/login")
        })
            .catch(err => {
                console.log(err)
                setError('Register Failed!!!')
            })
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
                                            register(e);
                                        }
                                    }>

                                        <h2 className="fw-bold mb-2 text-uppercase">Register</h2>

                                        <div className="form-outline form-white mb-4">
                                            <label className="form-label">First Name</label>
                                            <input type="text" id="firstName" placeholder="First Name" className="form-control form-control-lg"
                                                onChange={
                                                    (evt) => setFirstName(evt.target.value)
                                                } required
                                            />
                                        </div>

                                        <div className="form-outline form-white mb-4">
                                            <label className="form-label" >Last Name</label>
                                            <input type="text" id="lastName" placeholder="Last Name" className="form-control form-control-lg"
                                                onChange={
                                                    (evt) => setLastName(evt.target.value)
                                                } required
                                            />
                                        </div>
                                        <div className="form-outline form-white mb-4">
                                            <label>Email</label>
                                            <input type="email" id="email" className="form-control form-control-lg" placeholder="Email"
                                                onChange={
                                                    (evt) => setEmail(evt.target.value)
                                                } required />
                                        </div>
                                        <div className="form-outline form-white mb-4">
                                            <label>Password</label>
                                            <input type="password" id="password" className="form-control form-control-lg" onChange={
                                                (evt) => setPassword(evt.target.value)
                                            } required />
                                        </div>
                                        <button className="btn btn-outline-light btn-lg px-5" type="submit">Register</button>
                                        {
                                            error ? <div className="loginError">{error} </div> : ''
                                        }

                                    </form>
                                </div>


                                <div>
                                    <p className="mb-0">{"Go to login..  "}
                                        <a href="#!" className="text-white-50 fw-bold" onClick={login}>Login</a>
                                    </p>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

    )
}
