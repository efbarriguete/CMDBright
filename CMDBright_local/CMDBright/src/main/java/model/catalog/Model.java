/*****************************************************************************
 * CMDBright - Clear and nimble Configuration Management DataBase 
 * Copyright (C) 2016  Eladio Fernández Barrigüete
 * 
 * This file is part of CMDBright.
 * 
 * CMDBright is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * CMDBright is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with CMDBright.  If not, see <http://www.gnu.org/licenses/>.
******************************************************************************/

package model.catalog;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import model.equip.Equip;


/**
 * The persistent class for the model database table.
 * 
 */
@Entity
@NamedQuery(name="Model.findAll", query="SELECT m FROM Model m")
@Component
@Scope("request")
public class Model implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(generator="model_id_seq") //lo voy a llamar igual que la secuencia que he creado en postgreSQL -> menos confusión 
	@SequenceGenerator(name="model_id_seq", sequenceName="model_id_seq", allocationSize=1)
	private Integer id;
	private Boolean enrackable;
	private String nombre;
	@Column(name="part_num")
	private String partNum;

	//bi-directional many-to-one association to AtributoModel
	@OneToMany(mappedBy="model", cascade=CascadeType.ALL) //revisar CASCADE ALL para este List
	private List<AtributoModel> atributoModels;

	//bi-directional many-to-one association to Equip
	@OneToMany(mappedBy="model", cascade=CascadeType.ALL) //revisar CASCADE ALL para este List
	private List<Equip> equips;

	//bi-directional many-to-one association to Fabricante
	@ManyToOne //(cascade = CascadeType.ALL)
	@JoinColumn(name="id_fabricante")
	private Fabricante fabricante;

	//bi-directional many-to-one association to TipoModel
	@ManyToOne //(cascade = CascadeType.ALL)
	@JoinColumn(name="id_tipo_model")
	private TipoModel tipoModel;


	public Model() {
	}
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Boolean getEnrackable() {
		return this.enrackable;
	}
	public void setEnrackable(Boolean enrackable) {
		this.enrackable = enrackable;
	}
	public String getNombre() {
		return this.nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPartNum() {
		return this.partNum;
	}
	public void setPartNum(String partNum) {
		this.partNum = partNum;
	}
	public List<AtributoModel> getAtributoModels() {
		return this.atributoModels;
	}
	public void setAtributoModels(List<AtributoModel> atributoModels) {
		this.atributoModels = atributoModels;
	}
	public AtributoModel addAtributoModel(AtributoModel atributoModel) {
		getAtributoModels().add(atributoModel);
		atributoModel.setModel(this);
		return atributoModel;
	}
	public AtributoModel removeAtributoModel(AtributoModel atributoModel) {
		getAtributoModels().remove(atributoModel);
		atributoModel.setModel(null);
		return atributoModel;
	}
	public List<Equip> getEquips() {
		return this.equips;
	}
	public void setEquips(List<Equip> equips) {
		this.equips = equips;
	}
	public Equip addEquip(Equip equip) {
		getEquips().add(equip);
		equip.setModel(this);
		return equip;
	}
	public Equip removeEquip(Equip equip) {
		getEquips().remove(equip);
		equip.setModel(null);
		return equip;
	}
	public Fabricante getFabricante() {
		return this.fabricante;
	}
	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}
	public TipoModel getTipoModel() {
		return this.tipoModel;
	}
	public void setTipoModel(TipoModel tipoModel) {
		this.tipoModel = tipoModel;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Model other = (Model) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}