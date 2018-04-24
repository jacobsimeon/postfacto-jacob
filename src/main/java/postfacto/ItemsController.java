package postfacto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController

public class ItemsController {
  private final ColumnRepository columns;
  private ItemRepository itemRepository;

  @Autowired
  public ItemsController(ColumnRepository columns, ItemRepository itemRepository) {
    this.columns = columns;
    this.itemRepository = itemRepository;
  }

  @RequestMapping(path = "/columns/{columnId}/items", method = RequestMethod.POST)
  public ResponseEntity<RetroItem> create(@PathVariable Integer columnId, @RequestBody CreateItemRequest request) {
    Optional<RetroColumn> optionalColumn = columns.findById(columnId);
    if (!optionalColumn.isPresent()) {
      return ResponseEntity.notFound().build();
    }
    RetroColumn column = optionalColumn.get();

    RetroItem item = new RetroItem();
    item.setDescription(request.getDescription());

    column.getItems().add(item);
    columns.save(column);

    item = column.getItems().get(column.getItems().size() - 1);
    return new ResponseEntity<>(item, HttpStatus.CREATED);
  }

  @RequestMapping(path = "/items/{itemId}", method = RequestMethod.DELETE)
  public ResponseEntity destroy(@PathVariable Integer itemId) {
    itemRepository.deleteById(itemId);
    return ResponseEntity.noContent().build();
  }
}
