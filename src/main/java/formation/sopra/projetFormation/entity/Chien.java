package formation.sopra.projetFormation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonView;

import formation.sopra.projetFormation.entity.view.Views;

@Entity
@Table(name = "dog")
@SequenceGenerator(name = "seqChien", sequenceName = "seq_dog", initialValue = 100, allocationSize = 1)
@NamedQueries({ @NamedQuery(query = "select c from Chien c", name = "Chien.findAll"),
		@NamedQuery(query = "select c from Chien c where c.surnom = :surnom", name = "Chien.findBySurnom") })
public class Chien {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqChien")
	@Column(name = "id_dog")
	@JsonView(Views.Common.class)
	private Integer id;
	@JsonView(Views.Common.class)
	@Column(name = "surname", length = 150, nullable = false)
	private String surnom;
	@JsonView(Views.Common.class)
	@Column(name = "sexe", length = 1)
	@Enumerated(EnumType.STRING)
	private SexeChien sexeChien;
	@JsonView(Views.Common.class)
	@Column(name = "age", length = 3)
	private Integer age;
	@JsonView(Views.Common.class)
	@Column(name = "picture", length = 150)
	private String photo;
	@JsonView(Views.Common.class)
	@Column(name = "weigth")
	private Integer poids;
	@JsonView(Views.Common.class)
	@Column(name = "breed", length = 150)
	private String race;
	@ManyToOne
	@JoinColumn(name = "id_ad", foreignKey = @ForeignKey(name = "dog_ad_fk"))
	@JsonView(Views.ChienWithPersonne.class)
	private Annonce annonce;
	@ManyToOne
	@JoinColumn(name = "id_person", foreignKey = @ForeignKey(name = "dog_person_fk"))
	private Personne personne;
	@Version
	private int version;

	public Chien() {
	}

	public Chien(String surnom, Integer age) {
		this.surnom = surnom;
		this.age = age;
	}

	public Chien(String surnom, SexeChien sexeChien, Integer age, String photo, Integer poids, String race,
			int version) {
		this.surnom = surnom;
		this.sexeChien = sexeChien;
		this.age = age;
		this.photo = photo;
		this.poids = poids;
		this.race = race;
		this.version = version;
	}
	

	public Chien(Integer id, String surnom, SexeChien sexeChien, Integer age, String photo, Integer poids, String race,
			Annonce annonce, Personne personne, int version) {
		this.id = id;
		this.surnom = surnom;
		this.sexeChien = sexeChien;
		this.age = age;
		this.photo = photo;
		this.poids = poids;
		this.race = race;
		this.annonce = annonce;
		this.personne = personne;
		this.version = version;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSurnom() {
		return surnom;
	}

	public void setSurnom(String surnom) {
		this.surnom = surnom;
	}

	public SexeChien getSexeChien() {
		return sexeChien;
	}

	public void setSexeChien(SexeChien sexeChien) {
		this.sexeChien = sexeChien;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Integer getPoids() {
		return poids;
	}

	public void setPoids(Integer poids) {
		this.poids = poids;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public Annonce getAnnonce() {
		return annonce;
	}

	public void setAnnonce(Annonce annonce) {
		this.annonce = annonce;
	}

	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + version;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chien other = (Chien) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (version != other.version)
			return false;
		return true;
	}

}
