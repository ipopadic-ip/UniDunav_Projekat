package glavniPaket.controller.profesorPredmet;

import glavniPaket.dto.profesorPredmet.ProfesorPredmetDTO;
import glavniPaket.service.profesorPredmet.ProfesorPredmetService;
import glavniPaket.model.predmet.Predmet;
import glavniPaket.model.korisnika.Profesor;
import glavniPaket.model.profesorPredmet.ProfesorPredmet;
import glavniPaket.repository.korisnika.ProfesorRepository;
import glavniPaket.repository.predmet.PredmetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/profesorPredmeti")
public class ProfesorPredmetController {

    @Autowired
    private ProfesorPredmetService profesorPredmetService;
    
    @Autowired
    private ProfesorRepository profesorRepository; 

    @Autowired
    private PredmetRepository predmetRepository; 

    @GetMapping("")
    public ResponseEntity<Iterable<ProfesorPredmetDTO>> getAll() {
        Iterable<ProfesorPredmet> profesorPredmeti = profesorPredmetService.findAll();
        ArrayList<ProfesorPredmetDTO> profesorPredmetiDTO = new ArrayList<>();

        for (ProfesorPredmet pp : profesorPredmeti) {
            profesorPredmetiDTO.add(new ProfesorPredmetDTO(
                pp.getId(),
                null, 
                null, null  
            ));
        }

        return new ResponseEntity<>(profesorPredmetiDTO, HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ProfesorPredmetDTO> getOne(@PathVariable Long id) {
        ProfesorPredmet pp = this.profesorPredmetService.findById(id);
        if (pp == null) {
            return new ResponseEntity<ProfesorPredmetDTO>(HttpStatus.NOT_FOUND);
        }
        ProfesorPredmetDTO ppDTO = new ProfesorPredmetDTO(pp.getId(), null, null, null);
        return new ResponseEntity<ProfesorPredmetDTO>(ppDTO, HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ProfesorPredmetDTO> delete(@PathVariable Long id) {
    	ProfesorPredmet pp = this.profesorPredmetService.findById(id);
        if (pp == null) {
            return new ResponseEntity<ProfesorPredmetDTO>(HttpStatus.NOT_FOUND);
        }
        this.profesorPredmetService.deleteById(id);
        
        return new ResponseEntity<ProfesorPredmetDTO>(HttpStatus.OK);
    }
    
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ProfesorPredmetDTO> update(@PathVariable Long id, @RequestBody ProfesorPredmetDTO profesorPredmetDTO) {
        ProfesorPredmet pp = this.profesorPredmetService.findById(id);
        if (pp == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
       
        Profesor profesor = profesorRepository.findById(profesorPredmetDTO.getProfesor().getId()).orElse(null);
        Predmet predmet = predmetRepository.findById(profesorPredmetDTO.getPredmet().getId()).orElse(null);

        if (profesor == null || predmet == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
        }


        pp.setProfesor(profesor);
        pp.setPredmet(predmet);
        

        this.profesorPredmetService.save(pp);
        
        return new ResponseEntity<>(
            new ProfesorPredmetDTO(pp.getId(), null, null, null), 
            HttpStatus.OK
        );
    }
    
    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<ProfesorPredmetDTO> add(@RequestBody ProfesorPredmetDTO profesorPredmetDTO) {
    	ProfesorPredmet novProfesorPredmet = new ProfesorPredmet(null, null, null, null);
        this.profesorPredmetService.save(novProfesorPredmet);
        
        return new ResponseEntity<ProfesorPredmetDTO>(
            new ProfesorPredmetDTO(
            	novProfesorPredmet.getId(),
                null,null, null
            ), HttpStatus.CREATED);
    }

}
