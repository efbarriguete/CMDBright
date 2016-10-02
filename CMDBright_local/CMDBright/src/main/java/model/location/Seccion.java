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

package model.location;

import java.io.Serializable;
import java.util.List;

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


/**
 * The persistent class for the seccion database table.
 * 
 */
@Entity
@NamedQuery(name="Seccion.findAll", query="SELECT s FROM Seccion s")
@Component
@Scope("request")
public class Seccion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(generator="seccion_id_seq") //lo voy a llamar igual que la secuencia que he creado en postgreSQL -> menos confusión 
	@SequenceGenerator(name="seccion_id_seq", sequenceName="seccion_id_seq", allocationSize=1)
	private Short id;
	private String descrip;
	private String nombre;

	//bi-directional many-to-one association to Hueco
	@OneToMany(mappedBy="seccion")
	private List<Hueco> huecos;

	//bi-directional many-to-one association to Plano
	@ManyToOne
	@JoinColumn(name="id_plano")
	private Plano plano;

	//bi-directional many-to-one association to Planta
	@ManyToOne
	@JoinColumn(name="id_planta")
	private Planta planta;


	public Seccion() {
	}
	public Short getId() {
		return this.id;
	}
	public void setId(Short id) {
		this.id = id;
	}
	public String getDescrip() {
		return this.descrip;
	}
	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}
	public String getNombre() {
		return this.nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<Hueco> getHuecos() {
		return this.huecos;
	}
	public void setHuecos(List<Hueco> huecos) {
		this.huecos = huecos;
	}
	public Hueco addHueco(Hueco hueco) {
		getHuecos().add(hueco);
		hueco.setSeccion(this);
		return hueco;
	}
	public Hueco removeHueco(Hueco hueco) {
		getHuecos().remove(hueco);
		hueco.setSeccion(null);
		return hueco;
	}
	public Plano getPlano() {
		return this.plano;
	}
	public void setPlano(Plano plano) {
		this.plano = plano;
	}
	public Planta getPlanta() {
		return this.planta;
	}
	public void setPlanta(Planta planta) {
		this.planta = planta;
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
		Seccion other = (Seccion) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}