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

package model.issue;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import model.equip.Equip;
import model.net.Toma;


/**
 * The persistent class for the incidencia database table.
 * 
 */
@Entity
@NamedQuery(name="Incidencia.findAll", query="SELECT i FROM Incidencia i")
@Component
@Scope("request")
public class Incidencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(generator="incidencia_id_seq") //lo voy a llamar igual que la secuencia que he creado en postgreSQL -> menos confusión 
	@SequenceGenerator(name="incidencia_id_seq", sequenceName="incidencia_id_seq", allocationSize=1)
	private Long id;
	private String descrip;
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_fin")
	private Date fechaFin;
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_ini")
	private Date fechaIni;

	//bi-directional many-to-one association to Equip
	@ManyToOne
	@JoinColumn(name="id_equip")
	private Equip equip;

	//bi-directional many-to-one association to EstadoIncidencia
	@ManyToOne
	@JoinColumn(name="id_estado_incidencia")
	private EstadoIncidencia estadoIncidencia;

	//bi-directional many-to-one association to TipoIncidencia
	@ManyToOne
	@JoinColumn(name="id_tipo_incidencia")
	private TipoIncidencia tipoIncidencia;

	//bi-directional many-to-one association to Toma
	@ManyToOne
	@JoinColumn(name="id_toma")
	private Toma toma;


	public Incidencia() {
	}
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescrip() {
		return this.descrip;
	}
	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}
	public Date getFechaFin() {
		return this.fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public Date getFechaIni() {
		return this.fechaIni;
	}
	public void setFechaIni(Date fechaIni) {
		this.fechaIni = fechaIni;
	}
	public Equip getEquip() {
		return this.equip;
	}
	public void setEquip(Equip equip) {
		this.equip = equip;
	}
	public EstadoIncidencia getEstadoIncidencia() {
		return this.estadoIncidencia;
	}
	public void setEstadoIncidencia(EstadoIncidencia estadoIncidencia) {
		this.estadoIncidencia = estadoIncidencia;
	}
	public TipoIncidencia getTipoIncidencia() {
		return this.tipoIncidencia;
	}
	public void setTipoIncidencia(TipoIncidencia tipoIncidencia) {
		this.tipoIncidencia = tipoIncidencia;
	}
	public Toma getToma() {
		return this.toma;
	}
	public void setToma(Toma toma) {
		this.toma = toma;
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
		Incidencia other = (Incidencia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}