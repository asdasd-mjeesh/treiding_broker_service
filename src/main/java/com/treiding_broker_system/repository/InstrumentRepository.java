package com.treiding_broker_system.repository;

import com.treiding_broker_system.model.instrument.Instrument;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InstrumentRepository {
    private static Long ITEMS_COUNT = 0L;
    private final List<Instrument> instruments = new ArrayList<>();

    @PostConstruct
    private void init() {
        instruments.addAll(List.of(
                new Instrument(++ITEMS_COUNT, "Gold"),
                new Instrument(++ITEMS_COUNT, "Gas"),
                new Instrument(++ITEMS_COUNT, "Agro-product"),
                new Instrument(++ITEMS_COUNT, "Securities"),
                new Instrument(++ITEMS_COUNT, "Oil"),
                new Instrument(++ITEMS_COUNT, "CPU")
        ));
    }

    public Optional<Instrument> getById(Long id) {
        return this.instruments.stream()
                .filter(instrument -> instrument.getId().equals(id))
                .findAny();
    }

    public List<Instrument> getAll() {
        return this.instruments;
    }
}
