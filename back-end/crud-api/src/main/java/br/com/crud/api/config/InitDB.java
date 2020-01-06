package br.com.crud.api.config;

import br.com.crud.api.model.entity.Cliente;
import br.com.crud.api.model.entity.Email;
import br.com.crud.api.model.entity.Endereco;
import br.com.crud.api.model.entity.Telefone;
import br.com.crud.api.model.enums.TipoTelefone;
import br.com.crud.api.repository.ClienteRepository;
import br.com.crud.api.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
public class InitDB implements ApplicationListener<ContextRefreshedEvent> {

    private ClienteService service;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initCliente();
    }

    private void initCliente() {
        List<Cliente> clientes = service.listar();

        if (clientes.isEmpty()) {
            Endereco endereco1 = new Endereco("72255516", "EQNO 11/13 Bloco F", "Ceilândia Norte (Ceilândia)", "Brasília", "DF", "(Comércio)");
            Telefone telefone1 = new Telefone(TipoTelefone.COMERCIAL, "6133334488");
            Email email1 = new Email("rafael@gmail.com");
            service.salvar(new Cliente("Rafael", "00011122255", endereco1, Arrays.asList(telefone1), Arrays.asList(email1)));

            Endereco endereco2 = new Endereco("72255200", "QNO 12", "Ceilândia Norte (Ceilândia)", "Brasília", "DF", null);
            Telefone telefone2 = new Telefone(TipoTelefone.CELULAR, "61991557899");
            Telefone telefone3 = new Telefone(TipoTelefone.RESIDENCIAL, "6133330000");
            Email email2 = new Email("xablau@gmail.com");
            service.salvar(new Cliente("Xablau", "99988822255", endereco2, Arrays.asList(telefone2, telefone3), Arrays.asList(email2)));
        }
    }
}
