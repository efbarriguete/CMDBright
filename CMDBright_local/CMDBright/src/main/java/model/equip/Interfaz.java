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
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import model.net.BocaSw;
import model.net.TipoToma;
import model.net.Toma;


/**
 * The persistent class for the interfaz database table.
 * 
 */
@Entity
@NamedQuery(name="Interfaz.findAll", query="SELECT i FROM Interfaz i")
@Component
@Scope("request")
public class Interfaz implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(generator="interfaz_id_seq") //lo voy a llamar igual que la secuencia que he creado en postgreSQL -> menos confusión 
	@SequenceGenerator(name="interfaz_id_seq", sequenceName="interfaz_id_seq", allocationSize=1)
	private Integer id;
	@Temporal(TemporalType.DATE)
	@Column(name="cambio_ip")
	private Date cambioIp;
	@Temporal(TemporalType.DATE)
	@Column(name="cambio_toma")
	private Date cambioToma;
	private String mac;

	//bi-directional many-to-one association to BocaSw
/*	@OneToMany(mappedBy="interfaz")
	private List<BocaSw> bocaSws;
*/
	@OneToOne(mappedBy="interfaz")
	private BocaSw bocaSw;

	//bi-directional many-to-one association to Equip NO NECESARIO BIDIRECCIONAL???
	@ManyToOne
	@JoinColumn(name="id_equip", referencedColumnName = "id")
	private Equip equip;

	//bi-directional many-to-one association to Interfaz
	@ManyToOne
	@JoinColumn(name="id_interfaz")
	private Interfaz interfaz;

	//bi-directional many-to-one association to Interfaz
	@OneToMany(mappedBy="interfaz")
	private List<Interfaz> interfazs;

	//bi-directional many-to-one association to Ip
	@OneToOne (fetch=FetchType.EAGER, cascade= {CascadeType.MERGE, CascadeType.REFRESH}) // para actualizar el uso de las ip's -- si uso ALL, al borrar el objeto padre, se borran también las ip's y no nos interesa
	@JoinColumn(name="id_ip")
	private Ip ip;

	//bi-directional many-to-one association to TipoToma
	@ManyToOne
	@JoinColumn(name="id_tipo_toma")
	private TipoToma tipoToma;

	//bi-directional many-to-one association to Toma
//	@OneToOne (cascade=CascadeType.ALL)
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
	@JoinColumn(name="id_toma")
	private Toma toma;

	public Interfaz() {
	}
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getCambioIp() {
		return this.cambioIp;
	}
	public void setCambioIp(Date cambioIp) {
		this.cambioIp = cambioIp;
	}
	public Date getCambioToma() {
		return this.cambioToma;
	}
	public void setCambioToma(Date cambioToma) {
		this.cambioToma = cambioToma;
	}
	public String getMac() {
		return this.mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
/*	public List<BocaSw> getBocaSws() {
		return this.bocaSws;
	}
	public void setBocaSws(List<BocaSw> bocaSws) {
		this.bocaSws = bocaSws;
	}
	public BocaSw addBocaSw(BocaSw bocaSw) {
		getBocaSws().add(bocaSw);
		bocaSw.setInterfaz(this);
		return bocaSw;
	}
	public BocaSw removeBocaSw(BocaSw bocaSw) {
		getBocaSws().remove(bocaSw);
		bocaSw.setInterfaz(null);
		return bocaSw;
	}
	*/
	public BocaSw getBocaSw() {
		return bocaSw;
	}
	public void setBocaSw(BocaSw bocaSw) {
		this.bocaSw = bocaSw;
	}
	public Toma getToma() {
		return toma;
	}
	public void setToma(Toma toma) {
		this.toma = toma;
	}
	
	public Equip getEquip() {
		return this.equip;
	}
	public void setEquip(Equip equip) {
		this.equip = equip;
	}

	public Interfaz getInterfaz() {
		return this.interfaz;
	}
	public void setInterfaz(Interfaz interfaz) {
		this.interfaz = interfaz;
	}
	public List<Interfaz> getInterfazs() {
		return this.interfazs;
	}
	public void setInterfazs(List<Interfaz> interfazs) {
		this.interfazs = interfazs;
	}
	public Interfaz addInterfaz(Interfaz interfaz) {
		getInterfazs().add(interfaz);
		interfaz.setInterfaz(this);
		return interfaz;
	}
	public Interfaz removeInterfaz(Interfaz interfaz) {
		getInterfazs().remove(interfaz);
		interfaz.setInterfaz(null);
		return interfaz;
	}
	public Ip getIp() {
		return this.ip;
	}
	public void setIp(Ip ip) {
		this.ip = ip;
	}
	public TipoToma getTipoToma() {
		return this.tipoToma;
	}

	public void setTipoToma(TipoToma tipoToma) {
		this.tipoToma = tipoToma;
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
		Interfaz other = (Interfaz) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}