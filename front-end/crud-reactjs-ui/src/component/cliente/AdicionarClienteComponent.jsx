import React, { Component } from 'react'
import ApiClienteService from "../../service/ApiClienteService";
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';

class AdicionarClienteComponent extends Component{

    constructor(props){
        super(props);
        this.state ={
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
    }

    salvarCliente = (e) => {
        e.preventDefault();
        const cliente = {
            nome: this.state.nome, 
            cpf: this.state.cpf, 
            endereco: { 
                ...this.state.endereco
            }
        };

        ApiClienteService.adicionarCliente(cliente)
            .then(res => {
                this.setState({message : 'Cliente adicionado com sucesso.'});
                this.props.history.push('/clientes');
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

    onChange = (e) => {
        this.setState({ [e.target.name]: e.target.value });
    }

    render() {
        return(
            <div>
                <Typography variant="h4" style={style}>Adicionar Cliente</Typography>
                <form style={formContainer}>

                    <TextField type="text" placeholder="Nome" fullWidth margin="normal" name="nome" value={this.state.nome} onChange={this.onChange}/>

                    <TextField type="text" placeholder="Cpf" fullWidth margin="normal" name="cpf" value={this.state.cpf} onChange={this.onChange}/>

                    <TextField type="text" placeholder="Cep" fullWidth margin="normal" name="cep" value={this.state.endereco.cep} onChange={this.onChangeEndereco}/>

                    <TextField type="text" placeholder="Logradouro" fullWidth margin="normal" name="logradouro" value={this.state.endereco.logradouro} onChange={this.onChangeEndereco}/>                   

                    <TextField type="text" placeholder="Bairro" fullWidth margin="normal" name="bairro" value={this.state.endereco.bairro} onChange={this.onChangeEndereco}/>
                    
                    <TextField type="text" placeholder="Cidade" fullWidth margin="normal" name="cidade" value={this.state.endereco.cidade} onChange={this.onChangeEndereco}/>

                    <TextField type="text" placeholder="Uf" fullWidth margin="normal" name="uf" value={this.state.endereco.uf} onChange={this.onChangeEndereco}/>

                    <TextField type="text" placeholder="Complemento" fullWidth margin="normal" name="complemento" value={this.state.endereco.complemento} onChange={this.onChangeEndereco}/>

                    <Button variant="contained" color="primary" onClick={this.salvarCliente}>Salvar</Button>
            </form>
    </div>
        );
    }
}
const formContainer = {
    display: 'flex',
    flexFlow: 'row wrap'
};

const style ={
    display: 'flex',
    justifyContent: 'center'

}

export default AdicionarClienteComponent;