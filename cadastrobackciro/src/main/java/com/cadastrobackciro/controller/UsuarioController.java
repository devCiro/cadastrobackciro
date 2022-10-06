package com.cadastrobackciro.controller;


import com.cadastrobackciro.domain.Cliente;
import com.cadastrobackciro.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.codehaus.plexus.resource.loader.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/USUARIO")
public class UsuarioController {

    private final ClienteRepository clienteRepository;

//    @PreAuthorize("hasAnyRole('PERMISSAO_USER', 'PERMISSAO_ADMIN')")
    @GetMapping("/buscarTodos")
    public List<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

//    @PreAuthorize("hasAnyRole('PERMISSAO_USER', 'PERMISSAO_ADMIN')")
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Optional<Cliente>> buscarPorId(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return ResponseEntity.ok(cliente);
    }
//    @PreAuthorize("hasRole('PERMISSAO_ADMIN')")
    @PostMapping("salvar")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Cliente> salvar(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteRepository.save(cliente));
    }

//    @PreAuthorize("hasRole('PERMISSAO_ADMIN')")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Map<String, Boolean>> deletar(@PathVariable Long id) throws ResourceNotFoundException {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não existe usuário com esse ID : " + id));
        clienteRepository.delete(cliente);
        Map<String, Boolean> result = new HashMap<>();
        result.put("eliminar", Boolean.TRUE);
        return ResponseEntity.ok().body(result);
    }
//    @PreAuthorize("hasRole('PERMISSAO_ADMIN')")
    @PutMapping("/alterar/{id}")
    public void atualizar( @PathVariable Long id, @RequestBody @Valid Cliente clientes) {
        clienteRepository.findById(id).map( cliente -> {
                    cliente.setLogin(clientes.getLogin());
                    cliente.setEmail(clientes.getEmail());
                    cliente.setPassword(clientes.getPassword());
                    return clienteRepository.save(cliente);
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encotrado"));
    }

}
