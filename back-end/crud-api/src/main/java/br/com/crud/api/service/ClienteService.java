package br.com.crud.api.service;

import br.com.crud.api.exceptionhandler.exception.ResourceNotFoundException;
import br.com.crud.api.model.entity.Cliente;
import br.com.crud.api.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

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
        setClienteGetTelefones(cliente);
        setClienteGetEmails(cliente);
        return clienteRepository.save(cliente);
    }

    private void setClienteGetTelefones(Cliente cliente) {
        if (Objects.nonNull(cliente.getTelefones()))
            cliente.getTelefones().forEach(c -> c.setCliente(cliente));
    }

    private void setClienteGetEmails(Cliente cliente) {
        if (Objects.nonNull(cliente.getEmails()))
            cliente.getEmails().forEach(c -> c.setCliente(cliente));
    }

    public void deletarPorId(Long id) {
        Cliente cliente = buscarPorId(id);
        clienteRepository.deleteById(id);
    }

    public Cliente atualizar(Long id, Cliente cliente) {
        Cliente clienteSalvo = buscarPorId(id);

        setTelefonesClienteEClienteSalvo(cliente, clienteSalvo);
        setEmailsClienteEClienteSalvo(cliente, clienteSalvo);

        BeanUtils.copyProperties(cliente, clienteSalvo, "id", "telefones", "emails");

        return clienteRepository.save(clienteSalvo);
    }

    private void setTelefonesClienteEClienteSalvo(Cliente cliente, Cliente clienteSalvo) {
        if (Objects.nonNull(cliente.getTelefones())) {
            clienteSalvo.getTelefones().clear();
            clienteSalvo.getTelefones().addAll(cliente.getTelefones());
            clienteSalvo.getTelefones().forEach(c -> c.setCliente(clienteSalvo));
        }
    }

    private void setEmailsClienteEClienteSalvo(Cliente cliente, Cliente clienteSalvo) {
        if (Objects.nonNull(cliente.getEmails())) {
            clienteSalvo.getEmails().clear();
            clienteSalvo.getEmails().addAll(cliente.getEmails());
            clienteSalvo.getEmails().forEach(c -> c.setCliente(clienteSalvo));
        }
    }
}
