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




@SpringBootApplication(scanBasePackages = {
	    "glavniPaket",
	    "service",
	    "repository",
	    "controller",
	    "model",
	    "dto"
	})
	public class App {
	    public static void main(String[] args) {
	        SpringApplication.run(App.class, args);
	    }
	

    @Bean
    public CommandLineRunner run(
            MestoService mestoService,
            AdresaService adresaService,
            DrzavaService drzavaService,
            StudentService studentService,
            PravoPristupaService pravoPristupaService,
            DodeljenoPravoPristupaService dodeljenoService
    ) {
        return args -> {
            System.out.println("Dodavanje podataka u bazu...");

            // 1️⃣ Država i mesta
            Drzava srbija = drzavaService.save(new Drzava(null, "Srbija"));
            Mesto noviSad = mestoService.save(new Mesto(null, "Novi Sad", srbija));
            Mesto beograd = mestoService.save(new Mesto(null, "Beograd", srbija));

            srbija.setMesta(List.of(noviSad, beograd));
            drzavaService.save(srbija);

            // 2️⃣ Adrese
            Adresa adresaNS = adresaService.save(new Adresa(null, "Bulevar Oslobođenja", "30", noviSad));

            // 3️⃣ Pravo pristupa
            PravoPristupa pravo = pravoPristupaService.save(
                    new PravoPristupa("Prijava ispita", "Omogućava studentu da prijavi ispit")
            );

            // 4️⃣ Registrovani korisnik
            RegistrovaniKorisnik regKorisnik = new RegistrovaniKorisnik(
                    null,
                    "Petar",
                    "Petrović",
                    "petar123",
                    java.sql.Date.valueOf("2000-04-15"),
                    noviSad,
                    "1504000712345",
                    "defaultLozinka",
                    "petar@example.com",
                    new HashSet<>() // 👈 Dodajemo prazna prava pristupa
            );

            // 5️⃣ Student
            Student student = new Student(
                    regKorisnik,
                    java.sql.Date.valueOf("2020-09-01"),
                    adresaNS,
                    "RN123/2020"
            );

            student = studentService.save(student);
            System.out.println("✅ Student dodat sa ID: " + student.getId());

            // 6️⃣ Dodeli pravo pristupa studentu
            dodeljenoService.dodeliPravo(
                    student.getKorisnik().getId(),
                    pravo.getId()
            );

            System.out.println("✅ Dodeljeno pravo 'Prijava ispita' korisniku " + student.getKorisnik().getKorisnickoIme());

            // 7️⃣ Provera
            boolean imaPravo = student.getKorisnik().getPravaPristupa().stream()
                    .anyMatch(p -> p.getPravoPristupa().getNaziv().equalsIgnoreCase("Prijava ispita"));

            System.out.println("✅ Student ima pravo 'Prijava ispita'? " + imaPravo);
        };
    }
}