package com.treiding_broker_system.service.instrument;

import com.treiding_broker_system.model.instrument.Instrument;

import java.util.List;
import java.util.Optional;

public interface InstrumentService {
    Optional<Instrument> getById(Long id);

    List<Instrument> getAll();
}
