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

package model.supply;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import model.equip.Equip;


/**
 * The persistent class for the operacion database table.
 * 
 */
@Entity
@NamedQuery(name="Operacion.findAll", query="SELECT o FROM Operacion o")
@Component
@Scope("request")
public class Operacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(generator="operacion_id_seq") //lo voy a llamar igual que la secuencia que he creado en postgreSQL -> menos confusión 
	@SequenceGenerator(name="operacion_id_seq", sequenceName="operacion_id_seq", allocationSize=1)
	private Integer id;
	private String detalles;

	@Column(name="num_operacion")
	private String numOperacion;

	//bi-directional many-to-one association to Equip
	@OneToMany(mappedBy="operacion", fetch=FetchType.EAGER)
	private List<Equip> equips;

	//bi-directional many-to-one association to Proveedor
	@ManyToOne
	@JoinColumn(name="id_proveedor")
	private Proveedor proveedor;

	//bi-directional many-to-one association to TipoOperacion
	@ManyToOne
	@JoinColumn(name="id_tipo_operacion")
	private TipoOperacion tipoOperacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_operacion")
	private Date fechaOperacion;


	public Operacion() {
	}
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDetalles() {
		return this.detalles;
	}
	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}
	public Date getFechaOperacion() {
		return this.fechaOperacion;
	}
	public void setFechaOperacion(Date fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}
	public String getNumOperacion() {
		return this.numOperacion;
	}
	public void setNumOperacion(String numOperacion) {
		this.numOperacion = numOperacion;
	}
	public List<Equip> getEquips() {
		return this.equips;
	}
	public void setEquips(List<Equip> equips) {
		this.equips = equips;
	}
	public Equip addEquip(Equip equip) {
		getEquips().add(equip);
		equip.setOperacion(this);
		return equip;
	}
	public Equip removeEquip(Equip equip) {
		getEquips().remove(equip);
		equip.setOperacion(null);
		return equip;
	}
	public Proveedor getProveedor() {
		return this.proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	public TipoOperacion getTipoOperacion() {
		return this.tipoOperacion;
	}
	public void setTipoOperacion(TipoOperacion tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
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
		Operacion other = (Operacion) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}