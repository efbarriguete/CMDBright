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
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the vlan_en_boca database table.
 * 
 */
@Entity
@Table(name="vlan_en_boca")
@NamedQuery(name="VlanEnBoca.findAll", query="SELECT v FROM VlanEnBoca v")
public class VlanEnBoca implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private VlanEnBocaPK id;

	@Temporal(TemporalType.DATE)
	@Column(name="cambio_vlan")
	private Date cambioVlan;

	//bi-directional many-to-one association to BocaSw
	@MapsId("idBocaSw")
	@ManyToOne
	@JoinColumn(name="id_boca_sw", insertable=false, updatable=false)
	private BocaSw bocaSw;

	//bi-directional many-to-one association to Vlan
	@MapsId("idVlan")
	@ManyToOne
	@JoinColumn(name="id_vlan", insertable=false, updatable=false)
	private Vlan vlan;

	public VlanEnBoca() {
	}

	public VlanEnBocaPK getId() {
		return this.id;
	}

	public void setId(VlanEnBocaPK id) {
		this.id = id;
	}

	public Date getCambioVlan() {
		return this.cambioVlan;
	}

	public void setCambioVlan(Date cambioVlan) {
		this.cambioVlan = cambioVlan;
	}

	public BocaSw getBocaSw() {
		return this.bocaSw;
	}

	public void setBocaSw(BocaSw bocaSw) {
		this.bocaSw = bocaSw;
	}

	public Vlan getVlan() {
		return this.vlan;
	}

	public void setVlan(Vlan vlan) {
		this.vlan = vlan;
	}

}