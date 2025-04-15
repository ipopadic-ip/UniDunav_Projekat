//package controller.predmet;
//
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import model.fakultet.Fakultet;
//import model.predmet.Predmet;
//import service.fakultet.FakultetService;
//import service.predmet.PredmetService;
//
//@RestController
//@RequestMapping("/predmet")
//public class PredmetController {
//	@Autowired
//	private PredmetService predmetService;
//	
//	public PredmetController(PredmetService predmetService) {
//        this.predmetService = predmetService;
//    }
//	
//	@GetMapping("/{id}")
//	public ResponseEntity<Optional<Predmet>> getById(@PathVariable Integer id) {
//        Optional<Predmet> predmet = predmetService.findById(id);
//        return predmet != null ? ResponseEntity.ok(predmet) : ResponseEntity.notFound().build();
//    }
//	
//	@GetMapping
//    public ResponseEntity<Iterable<Predmet>> getAll() {
//        Iterable<Predmet> predmet = predmetService.findAll();
//        return ResponseEntity.ok(predmet);
//    }
//	
//	@PostMapping
//    public ResponseEntity<Predmet> create(@RequestBody Predmet predmet) {
//		Predmet savedPredmet = predmetService.save(predmet);
//        return ResponseEntity.status(HttpStatus.CREATED).body(savedPredmet);
//    }
//	
//	@PutMapping("/{id}")
//    public ResponseEntity<Predmet> update(@PathVariable Integer id, @RequestBody Predmet predmet) {
//        if (predmetService.findById(id) == null) {
//            return ResponseEntity.notFound().build();
//        }
//        predmet.setId(id);
//        Predmet updatedPredmet = predmetService.save(predmet);
//        return ResponseEntity.ok(updatedPredmet);
//    }
//	
//	@DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Integer id) {
//        if (predmetService.findById(id) == null) {
//            return ResponseEntity.notFound().build();
//        }
//        predmetService.deleteById(id);
//        return ResponseEntity.noContent().build();
//    }
//}
