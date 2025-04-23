package br.com.logistica.wms.repository;

import br.com.logistica.wms.model.Recebimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecebimentoRepository extends JpaRepository<Recebimento, Long> {
}
