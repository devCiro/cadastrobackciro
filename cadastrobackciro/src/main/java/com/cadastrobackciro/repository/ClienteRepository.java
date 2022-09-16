package com.usuario.cliente.model.repository;

import com.usuario.cliente.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

// 4 os repositorios sao responsavel por realizar as operacoes nas entidades 'Cliente'
// 4 na interface sempre deve extender a jparepository
// 4 o primeiro parametro e a entidade a qual esse repositorio vai trabalhar, o segundo parametro e o tipo de dado que representa a chave primaria 'id' por exemplo

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
