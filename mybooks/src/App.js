
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import React from 'react'
import Login from "./Components/Login/Login";
import Register from "./Components/Register/Register";
import BookList from "./Components/FavBooks/BookList";
import FavBookList from "./Components/FavBooks/favBooklist";
import PrivateRoute from "./PrivateRoute";


class App extends React.Component {
  render() {
    return <Router>
      <Switch>
        <Route exact path="/" component={Login}></Route>
        <Route path="/login" component={Login}></Route>
        <Route path="/register" component={Register}></Route>
        <PrivateRoute exact path= "/booklist" component={BookList} />        
        <PrivateRoute path="/favBooklist" component={FavBookList} />
      </Switch>
    </Router>

  }

}
export default App;
