import React from 'react'
import { Redirect, Route } from 'react-router-dom';


export default function PrivateRoute({ component: Component, ...restall }) {
console.log("private",localStorage.getItem("isAuthenticated"))
    return (<Route {...restall} render={
        (routeprops) => {
            return localStorage.getItem("isAuthenticated") === "true" ?
                (<Component {...routeprops} />)
                :
                (<Redirect to="/login" />)
        }
    }
    />);
}