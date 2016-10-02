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
import javax.persistence.*;

import model.equip.Interfaz;
import model.equip.Swtch;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the boca_sw database table.
 * 
 */
@Entity
@Table(name="boca_sw")
@NamedQuery(name="BocaSw.findAll", query="SELECT b FROM BocaSw b")
public class BocaSw implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(generator="boca_sw_id_seq") //lo voy a llamar igual que la secuencia que he creado en postgreSQL -> menos confusión 
	@SequenceGenerator(name="boca_sw_id_seq", sequenceName="boca_sw_id_seq", allocationSize=1)
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name="cambio_boca")
	private Date cambioBoca;

	@Column(name="num_boca")
	private Integer numBoca;

	//bi-directional many-to-one association to Interfaz
	@OneToOne
	@JoinColumn(name="id_interfaz")
	private Interfaz interfaz;

	//bi-directional many-to-one association to Swtch
	@ManyToOne
	@JoinColumn(name="id_swtch")
	private Swtch swtch;

	//bi-directional many-to-one association to Toma
	@OneToOne
	@JoinColumn(name="id_toma")
	private Toma toma;

	//bi-directional many-to-one association to VlanEnBoca
	@OneToMany(mappedBy="bocaSw")
	private List<VlanEnBoca> vlanEnBocas;

	public BocaSw() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCambioBoca() {
		return this.cambioBoca;
	}

	public void setCambioBoca(Date cambioBoca) {
		this.cambioBoca = cambioBoca;
	}

	public Integer getNumBoca() {
		return this.numBoca;
	}

	public void setNumBoca(Integer numBoca) {
		this.numBoca = numBoca;
	}

	public Interfaz getInterfaz() {
		return this.interfaz;
	}

	public void setInterfaz(Interfaz interfaz) {
		this.interfaz = interfaz;
	}

	public Swtch getSwtch() {
		return this.swtch;
	}

	public void setSwtch(Swtch swtch) {
		this.swtch = swtch;
	}

	public Toma getToma() {
		return this.toma;
	}

	public void setToma(Toma toma) {
		this.toma = toma;
	}

	public List<VlanEnBoca> getVlanEnBocas() {
		return this.vlanEnBocas;
	}

	public void setVlanEnBocas(List<VlanEnBoca> vlanEnBocas) {
		this.vlanEnBocas = vlanEnBocas;
	}

	public VlanEnBoca addVlanEnBoca(VlanEnBoca vlanEnBoca) {
		getVlanEnBocas().add(vlanEnBoca);
		vlanEnBoca.setBocaSw(this);

		return vlanEnBoca;
	}

	public VlanEnBoca removeVlanEnBoca(VlanEnBoca vlanEnBoca) {
		getVlanEnBocas().remove(vlanEnBoca);
		vlanEnBoca.setBocaSw(null);

		return vlanEnBoca;
	}

}