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

package model.net;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import model.equip.Interfaz;


/**
 * The persistent class for the tipo_toma database table.
 * 
 */
@Entity
@Table(name="tipo_toma")
@NamedQuery(name="TipoToma.findAll", query="SELECT t FROM TipoToma t")
@Component
@Scope("request")
public class TipoToma implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(generator="tipo_toma_id_seq") //lo voy a llamar igual que la secuencia que he creado en postgreSQL -> menos confusión 
	@SequenceGenerator(name="tipo_toma_id_seq", sequenceName="tipo_toma_id_seq", allocationSize=1)
	private Short id;
	private String nombre;
	private String descrip;

	//bi-directional many-to-one association to Interfaz
	@OneToMany(mappedBy="tipoToma")
	private List<Interfaz> interfazs;

	//bi-directional many-to-one association to Toma
	@OneToMany(mappedBy="tipoToma")
	private List<Toma> tomas;

	
	public TipoToma() {
	}
	public Short getId() {
		return this.id;
	}
	public void setId(Short id) {
		this.id = id;
	}
	public String getNombre() {
		return this.nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescrip() {
		return this.descrip;
	}
	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}
	public List<Interfaz> getInterfazs() {
		return this.interfazs;
	}
	public void setInterfazs(List<Interfaz> interfazs) {
		this.interfazs = interfazs;
	}
	public Interfaz addInterfaz(Interfaz interfaz) {
		getInterfazs().add(interfaz);
		interfaz.setTipoToma(this);
		return interfaz;
	}
	public Interfaz removeInterfaz(Interfaz interfaz) {
		getInterfazs().remove(interfaz);
		interfaz.setTipoToma(null);
		return interfaz;
	}
	public List<Toma> getTomas() {
		return this.tomas;
	}
	public void setTomas(List<Toma> tomas) {
		this.tomas = tomas;
	}
	public Toma addToma(Toma toma) {
		getTomas().add(toma);
		toma.setTipoToma(this);
		return toma;
	}
	public Toma removeToma(Toma toma) {
		getTomas().remove(toma);
		toma.setTipoToma(null);
		return toma;
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
		TipoToma other = (TipoToma) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}