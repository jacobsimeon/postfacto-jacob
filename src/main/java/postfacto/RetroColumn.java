package postfacto;

import javax.persistence.*;
import java.util.List;

@Entity
public class RetroColumn {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String name;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "column_id")
  private List<RetroItem> items;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<RetroItem> getItems() {
    return items;
  }
}
