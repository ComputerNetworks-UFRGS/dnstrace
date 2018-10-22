import React, { Component } from 'react';
import './App.css';
import Home from './mainView/index/Home';
import Results from './results/Results'
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';

class App extends Component {
    render() {
        return (
            <Router>
                <Switch>
                    <Route path='/' exact={true} component={Home}/>
                    <Route path='/results' exact={true} component={Results}/>
                </Switch>
            </Router>
        )
    }
}

export default App;