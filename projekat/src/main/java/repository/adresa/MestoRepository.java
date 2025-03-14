package repository.adresa;

import org.springframework.data.repository.PagingAndSortingRepository;

import model.adresa.Mesto;

public interface MestoRepository extends PagingAndSortingRepository<Mesto, Integer>{

}
