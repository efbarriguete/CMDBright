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

package model.equip;

import java.io.Serializable;
import javax.persistence.*;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * The persistent class for the estado_equip database table.
 * 
 */
@Entity
@Table(name="estado_equip")
@NamedQuery(name="EstadoEquip.findAll", query="SELECT e FROM EstadoEquip e")
@Component
@Scope("request")
public class EstadoEquip implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(generator="estado_equip_id_seq") //lo voy a llamar igual que la secuencia que he creado en postgreSQL -> menos confusión 
	@SequenceGenerator(name="estado_equip_id_seq", sequenceName="estado_equip_id_seq", allocationSize=1)
	private Short id;
	private String descrip;
	private String nombre;

	//bi-directional many-to-one association to Equip
	@OneToMany(mappedBy="estadoEquip")
	private List<Equip> equips;

	
	public EstadoEquip() {
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
	public List<Equip> getEquips() {
		return this.equips;
	}
	public void setEquips(List<Equip> equips) {
		this.equips = equips;
	}
	public Equip addEquip(Equip equip) {
		getEquips().add(equip);
		equip.setEstadoEquip(this);
		return equip;
	}
	public Equip removeEquip(Equip equip) {
		getEquips().remove(equip);
		equip.setEstadoEquip(null);
		return equip;
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
		EstadoEquip other = (EstadoEquip) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}