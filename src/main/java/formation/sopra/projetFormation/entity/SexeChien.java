package formation.sopra.projetFormation.entity;

public enum SexeChien {
	F("Feminin"), M("Masculin");

	private String label;

	private SexeChien(String label) {
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	

}
