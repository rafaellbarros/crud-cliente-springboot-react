import axios from 'axios';

const CLIENTE_API_BASE_URL = 'http://localhost:8080/api/clientes';
const VIA_CEP_API_URL = 'https://viacep.com.br/ws/';

class ApiClienteService {

    buscarClientes() {
        return axios.get(CLIENTE_API_BASE_URL);
    }

    buscarCep(cep) {
        return axios.get(`${VIA_CEP_API_URL}/${cep}/json`);
    }

    buscarClientePorId(clienteId) {
        return axios.get(`${CLIENTE_API_BASE_URL}/${clienteId}`);
    }

    deletarCliente(clienteId) {
        return axios.delete(`${CLIENTE_API_BASE_URL}/${clienteId}`);
    }

    adicionarCliente(cliente) {
        return axios.post(""+CLIENTE_API_BASE_URL, cliente);
    }

    editarCliente(cliente) {
        return axios.put(`${CLIENTE_API_BASE_URL}/${cliente.id}`, cliente);
    }

}

export default new ApiClienteService();