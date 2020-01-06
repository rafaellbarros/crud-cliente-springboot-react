import React, { Component } from 'react'
import ApiClienteService from "../../service/ApiClienteService";
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';

class EditarClienteComponent extends Component {

    constructor(props){
        super(props);
        this.state ={
            id: '',
            nome: '',
            cpf: '',
            endereco: {
                cep: '',
                logradouro: '',
                bairro: '',
                cidade: '',
                uf: '',
                complemento: ''
            }
        }

        this.onChangeEndereco = this.onChangeEndereco.bind(this);
        this.editarCliente = this.editarCliente.bind(this);
        this.carregarCliente = this.carregarCliente.bind(this);
    }

    componentDidMount() {
        this.carregarCliente();
    }

    carregarCliente() {
        ApiClienteService.buscarClientePorId(window.localStorage.getItem("clienteId"))
            .then((resp) => {
                let cliente = resp.data;
                console.log(cliente);
                this.setState(prevState => ({
                    ...prevState,
                    id: cliente.id,
                    nome: cliente.nome,
                    cpf: cliente.cpf,
                    endereco: { 
                        ...prevState.endereco, 
                        cep: cliente.endereco.cep,
                        logradouro: cliente.endereco.logradouro,
                        bairro: cliente.endereco.bairro,
                        cidade: cliente.endereco.cidade,
                        uf: cliente.endereco.uf,
                        complemento: cliente.endereco.complemento
                    }
                }));
            });
    }

    onChangeEndereco(e) {
        let value = e.target.value;
        let name = e.target.name;
        this.setState(
          prevState => ({
            endereco: {
              ...prevState.endereco,
              [name]: value
            }
          }),
          () => { 
              if (this.state.endereco.cep.length === 8) {
                this.buscarCep();
              } else if (this.state.endereco.cep.length === 0) {
                this.clearStateEndereco();
              }
            }
        );
    }

    buscarCep() {
        ApiClienteService.buscarCep(this.state.endereco.cep)
        .then(resp => {
            if (resp.status === 200) {
                const endereco = resp.data;
                this.setStateEnderecoCepResponse(endereco); 
            }
        });
    }

    setStateEnderecoCepResponse(endereco) {
        this.setState(prevState => ({
            endereco: { 
                ...prevState.endereco, 
                logradouro: endereco.logradouro,
                bairro: endereco.bairro,
                cidade: endereco.localidade,
                uf: endereco.uf,
                complemento: endereco.complemento
            }
        }));
    }

    clearStateEndereco() {
        this.setState(prevState => ({
            endereco: { 
                ...prevState.endereco, 
                logradouro: '',
                bairro: '',
                cidade: '',
                uf: '',
                complemento: ''
            }
        }));
    }


    onChange = (e) =>
        this.setState({ [e.target.name]: e.target.value });

    editarCliente = (e) => {
        e.preventDefault();

        const cliente = {
            id: this.state.id,
            nome: this.state.nome, 
            cpf: this.state.cpf, 
            endereco: { 
                ...this.state.endereco
            }
        };

        ApiClienteService.editarCliente(cliente)
            .then(resp => {
                this.setState({message : 'Cliente editado com sucesso.'});
                this.props.history.push('/clientes');
            });
    }

    render() {
        return (
            <div>
                <Typography variant="h4" style={style}>Editar Cliente</Typography>
                <form>

                    <TextField type="text" placeholder="Nome" fullWidth margin="normal" name="nome" value={this.state.nome} onChange={this.onChange}/>

                    <TextField type="text" placeholder="Cpf" fullWidth margin="normal" name="cpf" value={this.state.cpf} onChange={this.onChange}/>

                    <TextField type="text" placeholder="Cep" fullWidth margin="normal" name="cep" value={this.state.endereco.cep} onChange={this.onChangeEndereco}/>

                    <TextField type="text" placeholder="Logradouro" fullWidth margin="normal" name="logradouro" value={this.state.endereco.logradouro} onChange={this.onChangeEndereco}/>                   

                    <TextField type="text" placeholder="Bairro" fullWidth margin="normal" name="bairro" value={this.state.endereco.bairro} onChange={this.onChangeEndereco}/>

                    <TextField type="text" placeholder="Cidade" fullWidth margin="normal" name="cidade" value={this.state.endereco.cidade} onChange={this.onChangeEndereco}/>

                    <TextField type="text" placeholder="Uf" fullWidth margin="normal" name="uf" value={this.state.endereco.uf} onChange={this.onChangeEndereco}/>

                    <TextField  placeholder="Complemento" fullWidth margin="normal" name="complemento" value={this.state.endereco.complemento} onChange={this.onChangeEndereco}/>

                    <Button variant="contained" color="primary" onClick={this.editarCliente}>Save</Button>

                </form>
            </div>
        );
    }
}

const style ={
    display: 'flex',
    justifyContent: 'center'
}

export default EditarClienteComponent;