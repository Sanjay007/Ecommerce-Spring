

import React, { Component } from 'react';
import { BrowserRouter as Router, Switch, Route, Link } from 'react-router-dom';
import LoginComponent from './components/LoginComponent';
import AddProduct from './components/AddProduct'

class App extends Component {
  render() {
    return (
    <Router>
        
          <Switch>
              <Route exact path='/' component={LoginComponent} />
              <Route path='/product' component={AddProduct} />
            
          </Switch>
        
      </Router>
    );
  }
}

export default App;
