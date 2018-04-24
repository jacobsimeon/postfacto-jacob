package postfacto;

import javax.persistence.*;
import java.util.List;

@Entity
public class Retro {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String name;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "retro_id")
  private List<RetroColumn> retroColumns;

  public String getName() {
    return name;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<RetroColumn> getColumns() {
    return retroColumns;
  }

  public void setColumns(List<RetroColumn> retroColumns) {
    this.retroColumns = retroColumns;
  }
}
