package com.usuario.cliente.model.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.xml.bind.v2.TODO;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 6 com essa anotacion tenho acesso a um builder de Cliente
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100) // 2 sempre vai ser obrigatorio colocar um nome, e o tamanho maximo 100 posicoes
    // 12 @NotEmpty nao pode ser vazio nem nulo
    @NotEmpty(message = "{campo.nome.obrigatorio}") // 14 vai buscar a mensagem la no 'messages.properties' e esta configurado na 'InternacionizacaoConfig'
    private String nome;

    // TODO implementar uma exceção que trate dados do cpf repetidos no front e back
    // 50.1 a anotação 'unique' dentro de @Colum determina que aquela informação não pode ser repetida no banco de dados
    @Column(nullable = false, length = 11) //unique = true) // 2 sempre sera obrigatorio um cpf, e o tamanho de 11 caracteres 014.222.221-60
    @NotNull(message = "{campo.cpf.obrigatorio}")// 12 nao pode ser nulo
    @CPF(message = "{campo.cpf.invalido}") // 12 vejo se o CPF e valido Brasileiro
    private String cpf;

    @Column(name = "data_cadastro", updatable = false)// 10 updatable false nao deixa atualizar a data de cadastro // 3 vamos colocar o nome da coluna da tabela para ficar no padrao convencionado
    @JsonFormat(pattern = "dd/MM/yyyy") // 8 para padronizar a data 'formatar'
    private LocalDate dataCadastro; // 2  consigo salvar a data atual do cadastro

    @PrePersist
    // 6 antes de salvar algo no banco ele executa esse metodo
    // 6 esse metodo ja seta a data de cadastro
    public void prePersist () {
            setDataCadastro(LocalDate.now()); // 6 data de cadastro atual
    }


// 2 o lombok que gera automaticamente os get e set da aplicacao deve se instalar o plugin


}
