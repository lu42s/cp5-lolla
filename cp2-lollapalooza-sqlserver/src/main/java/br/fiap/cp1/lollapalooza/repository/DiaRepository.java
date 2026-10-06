package br.fiap.cp1.lollapalooza.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.fiap.cp1.lollapalooza.model.Dia;


public interface DiaRepository extends JpaRepository< Dia, Long> {
    
}