package com.unidunav.sluzbenik.service;

import com.unidunav.sluzbenik.model.Trebovanje;
import com.unidunav.sluzbenik.dto.TrebovanjeDTO;
import com.unidunav.sluzbenik.repository.TrebovanjeRepository;
import com.unidunav.user.model.User;
import com.unidunav.user.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrebovanjeService {

    @Autowired
    private TrebovanjeRepository trebovanjeRepository;

    @Autowired
    private UserRepository userRepository;

    public TrebovanjeDTO saveTrebovanje(TrebovanjeDTO dto, String email) {
        User sluzbenik = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Korisnik nije pronađen"));

        Trebovanje trebovanje = new Trebovanje();
        trebovanje.setNazivStavke(dto.getNazivStavke());
        trebovanje.setKolicina(dto.getKolicina());
        trebovanje.setDatumTrebovanja(LocalDate.now());
        trebovanje.setSluzbenik(sluzbenik);

        Trebovanje saved = trebovanjeRepository.save(trebovanje);

        TrebovanjeDTO response = new TrebovanjeDTO();
        response.setId(saved.getId());
        response.setNazivStavke(saved.getNazivStavke());
        response.setKolicina(saved.getKolicina());
        response.setDatumTrebovanja(saved.getDatumTrebovanja());
        response.setSluzbenikId(sluzbenik.getId());

        return response;
    }

    public List<TrebovanjeDTO> getAll() {
        return trebovanjeRepository.findAll().stream().map(t -> {
            TrebovanjeDTO dto = new TrebovanjeDTO();
            dto.setId(t.getId());
            dto.setNazivStavke(t.getNazivStavke());
            dto.setKolicina(t.getKolicina());
            dto.setDatumTrebovanja(t.getDatumTrebovanja());
            dto.setSluzbenikId(t.getSluzbenik().getId());
            dto.setSluzbenikIme(t.getSluzbenik().getIme());
            return dto;
        }).collect(Collectors.toList());
    }

    public void delete(Long id) {
        trebovanjeRepository.deleteById(id);
    }

    public TrebovanjeDTO update(Long id, TrebovanjeDTO dto) {
        Trebovanje trebovanje = trebovanjeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Trebovanje ne postoji"));

        trebovanje.setNazivStavke(dto.getNazivStavke());
        trebovanje.setKolicina(dto.getKolicina());

        Trebovanje updated = trebovanjeRepository.save(trebovanje);

        TrebovanjeDTO response = new TrebovanjeDTO();
        response.setId(updated.getId());
        response.setNazivStavke(updated.getNazivStavke());
        response.setKolicina(updated.getKolicina());
        response.setDatumTrebovanja(updated.getDatumTrebovanja());
        response.setSluzbenikId(updated.getSluzbenik().getId());

        return response;
    }
}