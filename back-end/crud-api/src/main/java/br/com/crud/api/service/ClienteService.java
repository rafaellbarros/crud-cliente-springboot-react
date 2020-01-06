package br.com.crud.api.service;

import br.com.crud.api.exceptionhandler.exception.ResourceNotFoundException;
import br.com.crud.api.model.entity.Cliente;
import br.com.crud.api.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class ClienteService {

    private ClienteRepository clienteRepository;

    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id).map(cliente -> {
            return cliente;
        }).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com id: " + id));
    }

    @Transactional
    public Cliente salvar(Cliente cliente) {
        cliente.getTelefones().forEach(c -> c.setCliente(cliente));
        cliente.getEmails().forEach(c -> c.setCliente(cliente));
        return clienteRepository.save(cliente);
    }

    public void deletarPorId(Long id) {
        Cliente cliente = buscarPorId(id);
        clienteRepository.deleteById(id);
    }

    public Cliente atualizar(Long id, Cliente cliente) {
        Cliente clienteSalvo = buscarPorId(id);

        clienteSalvo.getTelefones().clear();
        clienteSalvo.getEmails().clear();

        clienteSalvo.getTelefones().addAll(cliente.getTelefones());
        clienteSalvo.getEmails().addAll(cliente.getEmails());

        clienteSalvo.getTelefones().forEach(c -> c.setCliente(clienteSalvo));
        clienteSalvo.getEmails().forEach(c -> c.setCliente(clienteSalvo));

        BeanUtils.copyProperties(cliente, clienteSalvo, "id", "telefones", "emails");

        return clienteRepository.save(clienteSalvo);
    }
}
