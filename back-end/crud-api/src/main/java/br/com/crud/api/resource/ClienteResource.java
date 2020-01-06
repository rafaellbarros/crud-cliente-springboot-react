package br.com.crud.api.resource;

import br.com.crud.api.model.entity.Cliente;
import br.com.crud.api.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clientes")
@AllArgsConstructor
public class ClienteResource {

    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Void> salvar(@Valid @RequestBody Cliente cliente) {
        cliente = clienteService.salvar(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
        Cliente clienteSalvo = clienteService.atualizar(id, cliente);
        return ResponseEntity.status(HttpStatus.OK).body(clienteSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
        clienteService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

}
