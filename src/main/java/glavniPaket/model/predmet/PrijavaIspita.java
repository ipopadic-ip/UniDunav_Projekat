package glavniPaket.model.predmet;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "prijave_ispita")
public class PrijavaIspita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime datumPrijave;
    private LocalDateTime datumIspita;

    private boolean status; // npr. true = položio, false = nije položio

    @ManyToOne
    @JoinColumn(name = "pohadjanje_id", nullable = false)
    private PohadjanjePredmeta pohadjanje;

    public PrijavaIspita() {}

    public PrijavaIspita(Long id, LocalDateTime datumPrijave, LocalDateTime datumIspita, boolean status,
                         PohadjanjePredmeta pohadjanje) {
        this.id = id;
        this.datumPrijave = datumPrijave;
        this.datumIspita = datumIspita;
        this.status = status;
        this.pohadjanje = pohadjanje;
    }

    // Getteri i setteri

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getDatumPrijave() { return datumPrijave; }
    public void setDatumPrijave(LocalDateTime datumPrijave) { this.datumPrijave = datumPrijave; }

    public LocalDateTime getDatumIspita() { return datumIspita; }
    public void setDatumIspita(LocalDateTime datumIspita) { this.datumIspita = datumIspita; }

    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }

    public PohadjanjePredmeta getPohadjanje() { return pohadjanje; }
    public void setPohadjanje(PohadjanjePredmeta pohadjanje) { this.pohadjanje = pohadjanje; }
}
