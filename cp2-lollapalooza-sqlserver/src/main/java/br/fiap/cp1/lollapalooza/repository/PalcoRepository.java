package br.fiap.cp1.lollapalooza.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.fiap.cp1.lollapalooza.model.Palco;

public interface PalcoRepository extends JpaRepository< Palco , Long> {
    
}
