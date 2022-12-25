package com.treiding_broker_system.service.instrument;

import com.treiding_broker_system.model.instrument.Instrument;
import com.treiding_broker_system.repository.InstrumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InstrumentDatabaseService implements InstrumentService {
    private final InstrumentRepository instrumentRepository;

    @Override
    public Optional<Instrument> getById(Long id) {
        return instrumentRepository.getById(id);
    }

    @Override
    public List<Instrument> getAll() {
        return instrumentRepository.getAll();
    }
}
