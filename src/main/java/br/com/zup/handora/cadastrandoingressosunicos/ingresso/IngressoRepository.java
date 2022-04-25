package br.com.zup.handora.cadastrandoingressosunicos.ingresso;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IngressoRepository extends JpaRepository<Ingresso, Long> {

    boolean existsByNumeroAndDataHoraEvento(Integer numero, LocalDateTime dataHoraEvento);

}
