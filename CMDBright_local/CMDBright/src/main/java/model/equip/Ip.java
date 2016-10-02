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

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 * The persistent class for the ip database table.
 * 
 */
@Entity
@NamedQuery(name="Ip.findAll", query="SELECT i FROM Ip i")
@Component
@Scope("request")
public class Ip implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(generator="ip_id_seq") //lo voy a llamar igual que la secuencia que he creado en postgreSQL -> menos confusión 
	@SequenceGenerator(name="ip_id_seq", sequenceName="ip_id_seq", allocationSize=1)
	private Short id;
	private String ip;
	private Boolean used;

	//bi-directional many-to-one association to Interfaz
/*	@OneToOne(mappedBy="ip")
	private Interfaz interfaz;
	*/
	//private List<Interfaz> interfazs;

	//bi-directional many-to-one association to Rango
	@ManyToOne
	@JoinColumn(name="id_rango")
	private Rango rango;

	public Ip() {
	}
	public Short getId() {
		return this.id;
	}
	public void setId(Short id) {
		this.id = id;
	}
	public String getIp() {
		return this.ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Boolean getUsed() {
		return used;
	}
	public void setUsed(Boolean used) {
		this.used = used;
	}
/*	public Interfaz getInterfaz() {
		return interfaz;
	}
	public void setInterfaz(Interfaz interfaz) {
		this.interfaz = interfaz;
	}
	*/
/*	public List<Interfaz> getInterfazs() {
		return this.interfazs;
	}
	public void setInterfazs(List<Interfaz> interfazs) {
		this.interfazs = interfazs;
	}
	public Interfaz addInterfaz(Interfaz interfaz) {
		getInterfazs().add(interfaz);
		interfaz.setIp(this);
		return interfaz;
	}
	public Interfaz removeInterfaz(Interfaz interfaz) {
		getInterfazs().remove(interfaz);
		interfaz.setIp(null);
		return interfaz;
	}
	public Interfaz getInterfaz() {
		return interfaz;
	}
	public void setInterfaz(Interfaz interfaz) {
		this.interfaz = interfaz;
	}*/
	public Rango getRango() {
		return this.rango;
	}
	public void setRango(Rango rango) {
		this.rango = rango;
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
		Ip other = (Ip) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}