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
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import model.catalog.Model;
import model.issue.Incidencia;
import model.location.Hueco;
import model.net.Rack;
import model.supply.Operacion;


/**
 * The persistent class for the equip database table.
 * 
 */
@Entity
@NamedQuery(name="Equip.findAll", query="SELECT e FROM Equip e")
@Component
@Scope("request")
//@Inheritance( strategy = InheritanceType.SINGLE_TABLE )
//@DiscriminatorColumn(name = "type")
public class Equip implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(generator="equip_id_seq") //lo voy a llamar igual que la secuencia que he creado en postgreSQL -> menos confusión 
	@SequenceGenerator(name="equip_id_seq", sequenceName="equip_id_seq", allocationSize=1)
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name="cambio_estado_equip")
	private Date cambioEstadoEquip;

	@Temporal(TemporalType.DATE)
	@Column(name="cambio_ubicacion")
	private Date cambioUbicacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_recepcion")
	private Date fechaRecepcion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_fin_garantia")
	private Date fechaFinGarantia;

	@Column(name="et_sergas")
	private String etSergas;
	private String nombre;
	private String observaciones;
	
	@Column(name="serial_num")
	private String serialNum;

	//bi-directional many-to-one association to Equip
	@ManyToOne
	@JoinColumn(name="id_equip")
	private Equip equip;

	//bi-directional many-to-one association to Equip
	@OneToMany(mappedBy="equip")
	private List<Equip> equips;

	//bi-directional many-to-one association to EstadoEquip
	@ManyToOne
	@JoinColumn(name="id_estado_equip")
	private EstadoEquip estadoEquip;

	//bi-directional many-to-one association to Hueco
	@ManyToOne
	@JoinColumn(name="id_hueco")
	private Hueco hueco;

	//bi-directional many-to-one association to Model
	@ManyToOne
	@JoinColumn(name="id_model")
	private Model model;

	//bi-directional many-to-one association to Operacion
	@ManyToOne
	@JoinColumn(name="id_operacion")
	private Operacion operacion;

	//bi-directional many-to-one association to Rack
	@ManyToOne
	@JoinColumn(name="id_rack")
	private Rack rack;

	//bi-directional many-to-one association to Servicio
	@ManyToOne
	@JoinColumn(name="id_servicio")
	private Servicio servicio;

	//bi-directional many-to-one association to Incidencia
	@OneToMany(mappedBy="equip")
	private List<Incidencia> incidencias;

	//bi-directional many-to-one association to Interfaz
	@OneToMany(mappedBy="equip", cascade=CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval=true)
//	@OneToMany(mappedBy="equip", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Interfaz> interfazs;

	
	public Equip() {
	}
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getCambioEstadoEquip() {
		return this.cambioEstadoEquip;
	}
	public void setCambioEstadoEquip(Date cambioEstadoEquip) {
		this.cambioEstadoEquip = cambioEstadoEquip;
	}
	public Date getCambioUbicacion() {
		return this.cambioUbicacion;
	}
	public void setCambioUbicacion(Date cambioUbicacion) {
		this.cambioUbicacion = cambioUbicacion;
	}
	public Date getFechaRecepcion() {
		return fechaRecepcion;
	}
	public void setFechaRecepcion(Date fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}
	public Date getFechaFinGarantia() {
		return fechaFinGarantia;
	}
	public void setFechaFinGarantia(Date fechaFinGarantia) {
		this.fechaFinGarantia = fechaFinGarantia;
	}
	public String getEtSergas() {
		return this.etSergas;
	}
	public void setEtSergas(String etSergas) {
		this.etSergas = etSergas;
	}
	public String getNombre() {
		return this.nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getObservaciones() {
		return this.observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public String getSerialNum() {
		return this.serialNum;
	}
	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}
	public Equip getEquip() {
		return this.equip;
	}
	public void setEquip(Equip equip) {
		this.equip = equip;
	}
	public List<Equip> getEquips() {
		return this.equips;
	}
	public void setEquips(List<Equip> equips) {
		this.equips = equips;
	}
	public Equip addEquip(Equip equip) {
		getEquips().add(equip);
		equip.setEquip(this);
		return equip;
	}
	public Equip removeEquip(Equip equip) {
		getEquips().remove(equip);
		equip.setEquip(null);
		return equip;
	}
	public EstadoEquip getEstadoEquip() {
		return this.estadoEquip;
	}
	public void setEstadoEquip(EstadoEquip estadoEquip) {
		this.estadoEquip = estadoEquip;
	}
	public Hueco getHueco() {
		return this.hueco;
	}
	public void setHueco(Hueco hueco) {
		this.hueco = hueco;
	}
	public Model getModel() {
		return this.model;
	}
	public void setModel(Model model) {
		this.model = model;
	}
	public Operacion getOperacion() {
		return this.operacion;
	}
	public void setOperacion(Operacion operacion) {
		this.operacion = operacion;
	}
	public Rack getRack() {
		return this.rack;
	}
	public void setRack(Rack rack) {
		this.rack = rack;
	}
	public Servicio getServicio() {
		return this.servicio;
	}
	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}
	public List<Incidencia> getIncidencias() {
		return this.incidencias;
	}
	public void setIncidencias(List<Incidencia> incidencias) {
		this.incidencias = incidencias;
	}
	public Incidencia addIncidencia(Incidencia incidencia) {
		getIncidencias().add(incidencia);
		incidencia.setEquip(this);
		return incidencia;
	}
	public Incidencia removeIncidencia(Incidencia incidencia) {
		getIncidencias().remove(incidencia);
		incidencia.setEquip(null);
		return incidencia;
	}
	public List<Interfaz> getInterfazs() {
		return this.interfazs;
	}
	public void setInterfazs(List<Interfaz> interfazs) {
		this.interfazs = interfazs;
	}
	public Interfaz addInterfaz(Interfaz interfaz) {
		getInterfazs().add(interfaz);
		interfaz.setEquip(this);
		return interfaz;
	}
	public Interfaz removeInterfaz(Interfaz interfaz) {
		getInterfazs().remove(interfaz);
		interfaz.setEquip(null);
		return interfaz;
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
		Equip other = (Equip) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}