package com.unidunav.predmet.dto;

import java.time.LocalDateTime;

public class PrijavaIspitaDTO {
	private Long id;
    private LocalDateTime datumPrijave;
    private LocalDateTime datumIspita;
    private boolean status;
    private Long pohadjanjeId;

    // Getteri i setteri
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getDatumPrijave() { return datumPrijave; }
    public void setDatumPrijave(LocalDateTime datumPrijave) { this.datumPrijave = datumPrijave; }

    public LocalDateTime getDatumIspita() { return datumIspita; }
    public void setDatumIspita(LocalDateTime datumIspita) { this.datumIspita = datumIspita; }

    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }

    public Long getPohadjanjeId() { return pohadjanjeId; }
    public void setPohadjanjeId(Long pohadjanjeId) { this.pohadjanjeId = pohadjanjeId; }
}
