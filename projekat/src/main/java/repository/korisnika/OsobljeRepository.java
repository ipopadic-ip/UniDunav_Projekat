package repository.korisnika;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import model.korisnika.*;
public interface OsobljeRepository extends JpaRepository<Osoblje, Integer>{

}
