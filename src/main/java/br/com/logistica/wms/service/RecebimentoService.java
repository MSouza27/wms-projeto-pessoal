package br.com.logistica.wms.service;

import br.com.logistica.wms.model.Recebimento;
import br.com.logistica.wms.repository.RecebimentoRepository;
import org.springframework.stereotype.Service;

@Service
public class RecebimentoService {
    private final RecebimentoRepository recebimentoRepository;

    public RecebimentoService(RecebimentoRepository recebimentoRepository) {
        this.recebimentoRepository = recebimentoRepository;
    }

    public Recebimento salvar(Recebimento recebimento){
        return recebimentoRepository.save(recebimento);
    }
}
