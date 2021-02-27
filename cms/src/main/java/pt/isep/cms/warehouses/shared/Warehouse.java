package pt.isep.cms.warehouses.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Warehouse implements Serializable {
	public String id;
  public String name;
  public String totalCapacity;
  public String location;

	public Warehouse() {}

	public Warehouse(String id, String name, String totalCapacity, String location) {
		this.id = id;
    this.name = name;
    this.totalCapacity = totalCapacity;
		this.location = location;
	}

	public WarehouseDetails getLightWeightWarehouse() {
	  return new WarehouseDetails(id, getFullName());
	}

  public String getId() { return id; }
  public void setId(String id) { this.id = id; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public String getTotalCapacity() { return totalCapacity; }
  public void setTotalCapacity(String totalCapacity) { this.totalCapacity = totalCapacity; }
  public String getLocation() { return location; }
  public void setLocation(String location) { this.location = location; }
  public String getFullName() { return  name; }
}
