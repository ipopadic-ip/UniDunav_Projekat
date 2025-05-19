package glavniPaket.model.predmet;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
	public class Silabus {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(nullable = false, columnDefinition = "TEXT")
	    private String sadrzaj;

	    @OneToOne
	    @JoinColumn(name = "predmet_id", nullable = false, unique = true)
	    private Predmet predmet;

		public Silabus() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Silabus(Long id, String sadrzaj, Predmet predmet) {
			super();
			this.id = id;
			this.sadrzaj = sadrzaj;
			this.predmet = predmet;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getSadrzaj() {
			return sadrzaj;
		}

		public void setSadrzaj(String sadrzaj) {
			this.sadrzaj = sadrzaj;
		}

		public Predmet getPredmet() {
			return predmet;
		}

		public void setPredmet(Predmet predmet) {
			this.predmet = predmet;
		}

	    
	}


