package glavniPaket.dto.korisnika;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import glavniPaket.dto.adresa.MestoDTO;
import glavniPaket.model.adresa.Mesto;
import glavniPaket.model.fakultet.Fakultet;
import glavniPaket.model.katedra.Katedra;
import glavniPaket.model.korisnika.Profesor;
import glavniPaket.model.korisnika.RegistrovaniKorisnik;
import glavniPaket.model.univerzitet.Univerzitet;

public class ProfesorDTO extends RegistrovaniKorisnikDTO {
	private String titula;
	private String biografija;
	private Set<Integer> predmeti;
	private Long univerzitetId;
	private Long fakultetId;
	private Integer katedraId;

	public ProfesorDTO() {
		super();
	}

	public ProfesorDTO(Integer id, String ime, String prezime, String korisnickoIme, Date datumRodjenja,
			MestoDTO mestoRodjenja, String jmbg, String email, String titula, String biografija, Set<Integer> predmeti,
			Long univerzitetId, Long fakultetId, Integer katedraId) {
		super(id, ime, prezime, korisnickoIme, datumRodjenja, mestoRodjenja, jmbg, email);
		this.titula = titula;
		this.biografija = biografija;
		this.predmeti = predmeti;
		this.univerzitetId = univerzitetId;
		this.fakultetId = fakultetId;
		this.katedraId = katedraId;
	}

	public String getTitula() {
		return titula;
	}

	public void setTitula(String titula) {
		this.titula = titula;
	}

	public String getBiografija() {
		return biografija;
	}

	public void setBiografija(String biografija) {
		this.biografija = biografija;
	}

	public Set<Integer> getPredmeti() {
		return predmeti;
	}

	public void setPredmeti(Set<Integer> predmeti) {
		this.predmeti = predmeti;
	}

	public Long getUniverzitetId() {
		return univerzitetId;
	}

	public void setUniverzitetId(Long univerzitetId) {
		this.univerzitetId = univerzitetId;
	}

	public Long getFakultetId() {
		return fakultetId;
	}

	public void setFakultetId(Long fakultetId) {
		this.fakultetId = fakultetId;
	}

	public Integer getKatedraId() {
		return katedraId;
	}

	public void setKatedraId(Integer katedraId) {
		this.katedraId = katedraId;
	}

	public static ProfesorDTO fromEntity(Profesor profesor) {
		if (profesor == null || profesor.getKorisnik() == null)
			return null;

		RegistrovaniKorisnik k = profesor.getKorisnik();

		return new ProfesorDTO(profesor.getId(), k.getIme(), k.getPrezime(), k.getKorisnickoIme(), k.getDatumRodjenja(),
				k.getMestoRodjenja() != null ? new MestoDTO(k.getMestoRodjenja()) : null, k.getJmbg(), k.getEmail(),
				profesor.getTitula(), profesor.getBiografija(),
				profesor.getPredmeti() != null ? profesor.getPredmeti().stream()
						.map(pp -> pp.getPredmet().getId().intValue()).collect(Collectors.toSet()) : null,
				profesor.getUniverzitet() != null ? profesor.getUniverzitet().getId() : null,
				profesor.getFakultet() != null ? profesor.getFakultet().getId() : null,
				profesor.getKatedra() != null ? profesor.getKatedra().getId() : null);
	}

	public Profesor toProfesorEntity() {
		return null;
//        Profesor profesor = new Profesor();
//
//        RegistrovaniKorisnik korisnik = new RegistrovaniKorisnik();
//        korisnik.setId(getId());
//        korisnik.setIme(getIme());
//        korisnik.setPrezime(getPrezime());
//        korisnik.setKorisnickoIme(getKorisnickoIme());
//        korisnik.setDatumRodjenja(getDatumRodjenja());
//        if (getMestoRodjenja() != null) {
//            korisnik.setMestoRodjenja(new Mesto(getMestoRodjenja().getId(), getMestoRodjenja().getNaziv(), null));
//        }
//        korisnik.setJmbg(getJmbg());
//        korisnik.setEmail(getEmail());
//
//        profesor.setKorisnik(korisnik);
//        profesor.setTitula(getTitula());
//        profesor.setBiografija(getBiografija());
//
//        if (univerzitetId != null) {
//            profesor.setUniverzitet(new Univerzitet(univerzitetId));
//        }
//        if (fakultetId != null) {
//            profesor.setFakultet(new Fakultet(fakultetId));
//        }
//        if (katedraId != null) {
//            profesor.setKatedra(new Katedra(katedraId));
//        }
//
//        return profesor;
//    }}
	}
}
