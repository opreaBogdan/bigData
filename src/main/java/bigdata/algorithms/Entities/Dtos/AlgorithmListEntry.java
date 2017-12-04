package bigdata.algorithms.Entities.Dtos;

public class AlgorithmListEntry{
	
	public String name;
	public String description;
	
	public AlgorithmListEntry(String name, String description){
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
