package formation.sopra.projetFormation.entity;

public enum Note {
	N0("0"), N1("1"), N2("2"), N3("3"), N4("4"), N5("5");

	private String label;

	private Note(String label) {
		this.label = label;
	}

	public String getNote() {
		return label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
}
