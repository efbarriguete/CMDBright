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

import java.util.Date;


/**
 * The persistent class for the telefono database table.
 * 
 */
@Entity
@NamedQuery(name="Telefono.findAll", query="SELECT t FROM Telefono t")
@Component
@Scope("request")
//@DiscriminatorValue( value="TLF" )
//public class Telefono extends Equip implements Serializable{
public class Telefono implements Serializable{
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

	@Column(name="et_sergas")
	private String etSergas;

	@Column(name="extension_ext")
	private Integer extensionExt;

	@Column(name="extension_int")
	private Integer extensionInt;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_fin_garantia")
	private Date fechaFinGarantia;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_recepcion")
	private Date fechaRecepcion;

	@Column(name="id_equip")
	private Integer idEquip;

	@Column(name="id_estado_equip")
	private Integer idEstadoEquip;

	@Column(name="id_hueco")
	private Integer idHueco;

	@Column(name="id_model")
	private Integer idModel;

	@Column(name="id_operacion")
	private Integer idOperacion;

	@Column(name="id_rack")
	private Integer idRack;

	@Column(name="id_servicio")
	private Integer idServicio;

	private String nombre;

	private String observaciones;

	@Column(name="serial_num")
	private String serialNum;

	public Telefono() {
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

	public String getEtSergas() {
		return this.etSergas;
	}

	public void setEtSergas(String etSergas) {
		this.etSergas = etSergas;
	}

	public Integer getExtensionExt() {
		return this.extensionExt;
	}

	public void setExtensionExt(Integer extensionExt) {
		this.extensionExt = extensionExt;
	}

	public Integer getExtensionInt() {
		return this.extensionInt;
	}

	public void setExtensionInt(Integer extensionInt) {
		this.extensionInt = extensionInt;
	}

	public Date getFechaFinGarantia() {
		return this.fechaFinGarantia;
	}

	public void setFechaFinGarantia(Date fechaFinGarantia) {
		this.fechaFinGarantia = fechaFinGarantia;
	}

	public Date getFechaRecepcion() {
		return this.fechaRecepcion;
	}

	public void setFechaRecepcion(Date fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}

	public Integer getIdEquip() {
		return this.idEquip;
	}

	public void setIdEquip(Integer idEquip) {
		this.idEquip = idEquip;
	}

	public Integer getIdEstadoEquip() {
		return this.idEstadoEquip;
	}

	public void setIdEstadoEquip(Integer idEstadoEquip) {
		this.idEstadoEquip = idEstadoEquip;
	}

	public Integer getIdHueco() {
		return this.idHueco;
	}

	public void setIdHueco(Integer idHueco) {
		this.idHueco = idHueco;
	}

	public Integer getIdModel() {
		return this.idModel;
	}

	public void setIdModel(Integer idModel) {
		this.idModel = idModel;
	}

	public Integer getIdOperacion() {
		return this.idOperacion;
	}

	public void setIdOperacion(Integer idOperacion) {
		this.idOperacion = idOperacion;
	}

	public Integer getIdRack() {
		return this.idRack;
	}

	public void setIdRack(Integer idRack) {
		this.idRack = idRack;
	}

	public Integer getIdServicio() {
		return this.idServicio;
	}

	public void setIdServicio(Integer idServicio) {
		this.idServicio = idServicio;
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

}