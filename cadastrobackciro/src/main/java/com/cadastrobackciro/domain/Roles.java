package com.cadastrobackciro.domain;

import com.cadastrobackciro.enums.Permissao;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "PERMISSAO")
public class Roles implements GrantedAuthority, Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private Permissao permissao;

    @Override
    public String getAuthority() {
        return this.permissao.toString();
    }
}
