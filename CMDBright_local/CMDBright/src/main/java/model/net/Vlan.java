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

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 * The persistent class for the vlan database table.
 * 
 */
@Entity
@NamedQuery(name="Vlan.findAll", query="SELECT v FROM Vlan v")
@Component
@Scope("request")
public class Vlan implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(generator="vlan_id_seq") //lo voy a llamar igual que la secuencia que he creado en postgreSQL -> menos confusión 
	@SequenceGenerator(name="vlan_id_seq", sequenceName="vlan_id_seq", allocationSize=1)
	private Short id;
	private String descrip;
	private String nombre;

	//bi-directional many-to-one association to VlanEnBoca
	@OneToMany(mappedBy="vlan")
	private List<VlanEnBoca> vlanEnBocas;


	public Vlan() {
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
	public List<VlanEnBoca> getVlanEnBocas() {
		return this.vlanEnBocas;
	}
	public void setVlanEnBocas(List<VlanEnBoca> vlanEnBocas) {
		this.vlanEnBocas = vlanEnBocas;
	}
	public VlanEnBoca addVlanEnBoca(VlanEnBoca vlanEnBoca) {
		getVlanEnBocas().add(vlanEnBoca);
		vlanEnBoca.setVlan(this);
		return vlanEnBoca;
	}
	public VlanEnBoca removeVlanEnBoca(VlanEnBoca vlanEnBoca) {
		getVlanEnBocas().remove(vlanEnBoca);
		vlanEnBoca.setVlan(null);
		return vlanEnBoca;
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
		Vlan other = (Vlan) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}