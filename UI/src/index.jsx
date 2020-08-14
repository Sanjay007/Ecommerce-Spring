import React from "react";
import { Route, Link, BrowserRouter as Router } from 'react-router-dom'
import LoginComponent from './components/LoginComponent';
import AddProduct from './components/AddProduct'

import ReactDOM from "react-dom";

import App from "./App";

const routing = (
    <Router>
        <div>
            <Route path="/" component={<LoginComponent />
            } />


            <Route path="/add" component={<AddProduct />
            } />

        </div>
    </Router>
)

ReactDOM.render(<App/>, document.getElementById("root"));
