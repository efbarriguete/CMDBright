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
import javax.persistence.*;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * The persistent class for the plano database table.
 * 
 */
@Entity
@NamedQuery(name="Plano.findAll", query="SELECT p FROM Plano p")
@Component
@Scope("request")
public class Plano implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(generator="plano_id_seq") //lo voy a llamar igual que la secuencia que he creado en postgreSQL -> menos confusión 
	@SequenceGenerator(name="plano_id_seq", sequenceName="plano_id_seq", allocationSize=1)
	private Short id;
	@Column(name="ruta_plano")
	private String rutaPlano;

	//bi-directional many-to-one association to Seccion
	@OneToMany(mappedBy="plano")
	private List<Seccion> seccions;

	
	public Plano() {
	}
	public Short getId() {
		return this.id;
	}
	public void setId(Short id) {
		this.id = id;
	}
	public String getRutaPlano() {
		return this.rutaPlano;
	}
	public void setRutaPlano(String rutaPlano) {
		this.rutaPlano = rutaPlano;
	}
	public List<Seccion> getSeccions() {
		return this.seccions;
	}
	public void setSeccions(List<Seccion> seccions) {
		this.seccions = seccions;
	}
	public Seccion addSeccion(Seccion seccion) {
		getSeccions().add(seccion);
		seccion.setPlano(this);
		return seccion;
	}
	public Seccion removeSeccion(Seccion seccion) {
		getSeccions().remove(seccion);
		seccion.setPlano(null);
		return seccion;
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
		Plano other = (Plano) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}