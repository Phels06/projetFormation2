package formation.sopra.projetFormation.entity;

public enum SexeChien {
	F("Feminin"), M("Masculin");

	private String sexeChien;

	private SexeChien(String sexeChien) {
	}

	public String getSexeChien() {
		return sexeChien;
	}

	public void setSexeChien(String sexeChien) {
		this.sexeChien = sexeChien;
	}
	
	

}
