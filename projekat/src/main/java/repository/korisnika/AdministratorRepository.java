package repository.korisnika;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import model.korisnika.Administrator;

public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {

}
