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

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the vlan_en_boca database table.
 * 
 */
@Embeddable
public class VlanEnBocaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_vlan")
	private Integer idVlan;

	@Column(name="id_boca_sw")
	private Integer idBocaSw;

	public VlanEnBocaPK() {
	}
	public Integer getIdVlan() {
		return this.idVlan;
	}
	public void setIdVlan(Integer idVlan) {
		this.idVlan = idVlan;
	}
	public Integer getIdBocaSw() {
		return this.idBocaSw;
	}
	public void setIdBocaSw(Integer idBocaSw) {
		this.idBocaSw = idBocaSw;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof VlanEnBocaPK)) {
			return false;
		}
		VlanEnBocaPK castOther = (VlanEnBocaPK)other;
		return 
			this.idVlan.equals(castOther.idVlan)
			&& this.idBocaSw.equals(castOther.idBocaSw);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idVlan.hashCode();
		hash = hash * prime + this.idBocaSw.hashCode();
		
		return hash;
	}
}