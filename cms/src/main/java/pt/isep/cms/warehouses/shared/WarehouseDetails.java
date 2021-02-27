package pt.isep.cms.warehouses.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class WarehouseDetails implements Serializable {
  private String id;
  private String displayName;
  
  public WarehouseDetails() {
    new WarehouseDetails("0", "");
  }

  public WarehouseDetails(String id, String displayName) {
    this.id = id;
    this.displayName = displayName;
  }
  
  public String getId() { return id; }
  public void setId(String id) { this.id = id; }
  public String getDisplayName() { return displayName; }
  public void setDisplayName(String displayName) { this.displayName = displayName; } 
}
