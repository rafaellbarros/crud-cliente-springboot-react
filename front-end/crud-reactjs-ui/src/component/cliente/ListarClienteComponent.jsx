import React, { Component } from 'react'
import ApiClienteService from "../../service/ApiClienteService";
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Button from '@material-ui/core/Button';
import CreateIcon from '@material-ui/icons/Create';
import DeleteIcon from '@material-ui/icons/Delete';
import Typography from '@material-ui/core/Typography';

class ListarClienteComponent extends Component {

    constructor(props) {
        super(props)
        this.state = {
            clientes: [],
            message: null
        }
        this.deletarCliente = this.deletarCliente.bind(this);
        this.editarCliente = this.editarCliente.bind(this);
        this.adicionarCliente = this.adicionarCliente.bind(this);
        this.carregarListaClientes = this.carregarListaClientes.bind(this);
    }

    componentDidMount() {
        this.carregarListaClientes();
    }

    carregarListaClientes() {
        ApiClienteService.buscarClientes()
            .then((res) => {
                this.setState({clientes: res.data})
            });
    }

    deletarCliente(clienteId) {
        ApiClienteService.deletarCliente(clienteId)
           .then(res => {
               this.setState({message : 'Cliente deletado com sucesso.'});
               this.setState({clientes: this.state.clientes.filter(cliente => cliente.id !== clienteId)});
           })
    }

    editarCliente(id) {
        window.localStorage.setItem("clienteId", id);
        this.props.history.push('/editar-cliente');
    }

    adicionarCliente() {
        window.localStorage.removeItem("clienteId");
        this.props.history.push('/adicionar-cliente');
    }

    render() {
        return (
            <div>
                <Typography variant="h4" style={style}>Clientes</Typography>
                <Button variant="contained" color="primary" onClick={() => this.adicionarCliente()}>
                    Adicionar
                </Button>

                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>Id</TableCell>
                            <TableCell align="right">Nome</TableCell>
                            <TableCell align="right">Cpf</TableCell>
                            <TableCell align="right">Cep</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {this.state.clientes.map(cliente => (
                            <TableRow key={cliente.id}>
                                <TableCell component="th" scope="cliente">
                                    {cliente.id}
                                </TableCell>
                                <TableCell align="right">{cliente.nome}</TableCell>
                                <TableCell align="right">{cliente.cpf}</TableCell>
                                <TableCell align="right">{cliente.endereco.cep}</TableCell>
                                <TableCell align="right" onClick={() => this.editarCliente(cliente.id)}><CreateIcon /></TableCell>
                                <TableCell align="right" onClick={() => this.deletarCliente(cliente.id)}><DeleteIcon /></TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </div>
        );
    }

}

const style ={
    display: 'flex',
    justifyContent: 'center'
}

export default ListarClienteComponent;