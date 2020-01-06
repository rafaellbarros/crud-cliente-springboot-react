import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import ListarClienteComponent from "./cliente/ListarClienteComponent";
import AdicionarClienteComponent from "./cliente/AdicionarClienteComponent";
import EditarClienteComponent from "./cliente/EditarClienteComponent";
import React from "react";

const AppRouter = () => {
    return(
        <div style={style}>
            <Router>
                    <Switch>
                        <Route path="/" exact component={ListarClienteComponent} />
                        <Route path="/clientes" component={ListarClienteComponent} />
                        <Route path="/adicionar-cliente" component={AdicionarClienteComponent} />
                        <Route path="/editar-cliente" component={EditarClienteComponent} />
                    </Switch>
            </Router>
        </div>
    )
}

const style={
    marginTop:'20px'
}

export default AppRouter;