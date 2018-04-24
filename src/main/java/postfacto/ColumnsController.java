package postfacto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ColumnsController {
  private final RetroRepository retroRepository;
  private final ColumnRepository columnRepository;

  @Autowired
  public ColumnsController(RetroRepository retroRepository, ColumnRepository columnRepository) {
    this.retroRepository = retroRepository;
    this.columnRepository = columnRepository;
  }

  @RequestMapping(path = "/retros/{retroId}/columns", method = RequestMethod.POST)
  public ResponseEntity<RetroColumn> create(@PathVariable Integer retroId, @RequestBody CreateColumnRequest request) {
    Optional<Retro> optionalRetro = retroRepository.findById(retroId);
    if (!optionalRetro.isPresent()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    Retro retro = optionalRetro.get();

    RetroColumn retroColumn = new RetroColumn();
    retroColumn.setName(request.getName());

    retro.getColumns().add(retroColumn);
    retroRepository.save(retro);

    retroColumn = retro.getColumns().get(retro.getColumns().size() - 1);
    return new ResponseEntity<>(retroColumn, HttpStatus.CREATED);
  }

  @RequestMapping(path = "/columns/{columnId}", method = RequestMethod.DELETE)
  public ResponseEntity destroy(@PathVariable Integer columnId) {
    columnRepository.deleteById(columnId);
    return ResponseEntity.noContent().build();
  }
}
