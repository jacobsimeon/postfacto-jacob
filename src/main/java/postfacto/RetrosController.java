package postfacto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/retros")
public class RetrosController {

  private final RetroRepository retroRepository;

  @Autowired
  public RetrosController(RetroRepository retroRepository) {
    this.retroRepository = retroRepository;
  }

  @PostMapping
  public ResponseEntity<Retro> create(@RequestBody CreateRetroRequest createRetroRequest) {
    Retro retro = new Retro();
    retro.setName(createRetroRequest.getName());

    retroRepository.save(retro);

    return new ResponseEntity<>(retro, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<Iterable<Retro>> index() {
    return new ResponseEntity<>(retroRepository.findAll(), HttpStatus.OK);
  }
}
