package glavniPaket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;


import glavniPaket.service.adresa.AdresaService;
import glavniPaket.service.adresa.DrzavaService;
import glavniPaket.service.adresa.MestoService;
import glavniPaket.service.korisnika.DodeljenoPravoPristupaService;
import glavniPaket.service.korisnika.PravoPristupaService;
import glavniPaket.service.korisnika.StudentService;

import glavniPaket.model.adresa.Adresa;
import glavniPaket.model.adresa.Drzava;
import glavniPaket.model.adresa.Mesto;
import glavniPaket.model.korisnika.PravoPristupa;
import glavniPaket.model.korisnika.RegistrovaniKorisnik;
import glavniPaket.model.korisnika.Student;




import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
	public class App {
	    public static void main(String[] args) {
	        SpringApplication.run(App.class, args);
	    }
	

  

    }
