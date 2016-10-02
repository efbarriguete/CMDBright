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

package model.location;

import java.io.Serializable;
import javax.persistence.*;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import model.equip.Equip;
import model.net.Rack;
import model.net.Toma;

import java.util.List;


/**
 * The persistent class for the hueco database table.
 * 
 */
@Entity
@NamedQuery(name="Hueco.findAll", query="SELECT h FROM Hueco h")
@Component
@Scope("request")
public class Hueco implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(generator="hueco_id_seq") //lo voy a llamar igual que la secuencia que he creado en postgreSQL -> menos confusión 
	@SequenceGenerator(name="hueco_id_seq", sequenceName="hueco_id_seq", allocationSize=1)
	private Integer id;
	private String descrip;
	private String nombre;
	@Column(name="nombre_armario")
	private String nombreArmario;

	//bi-directional many-to-one association to Equip
	@OneToMany(mappedBy="hueco")
	private List<Equip> equips;

	//bi-directional many-to-one association to Seccion
	@ManyToOne
	@JoinColumn(name="id_seccion")
	private Seccion seccion;

	//bi-directional many-to-one association to Rack
	@OneToMany(mappedBy="hueco")
	private List<Rack> racks;

	//bi-directional many-to-one association to Toma
	@OneToMany(mappedBy="hueco")
	private List<Toma> tomas;


	public Hueco() {
	}
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
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
	public String getNombreArmario() {
		return nombreArmario;
	}
	public void setNombreArmario(String nombreArmario) {
		this.nombreArmario = nombreArmario;
	}
	public List<Equip> getEquips() {
		return this.equips;
	}
	public void setEquips(List<Equip> equips) {
		this.equips = equips;
	}
	public Equip addEquip(Equip equip) {
		getEquips().add(equip);
		equip.setHueco(this);
		return equip;
	}
	public Equip removeEquip(Equip equip) {
		getEquips().remove(equip);
		equip.setHueco(null);
		return equip;
	}
	public Seccion getSeccion() {
		return this.seccion;
	}
	public void setSeccion(Seccion seccion) {
		this.seccion = seccion;
	}
	public List<Rack> getRacks() {
		return this.racks;
	}
	public void setRacks(List<Rack> racks) {
		this.racks = racks;
	}
	public Rack addRack(Rack rack) {
		getRacks().add(rack);
		rack.setHueco(this);
		return rack;
	}
	public Rack removeRack(Rack rack) {
		getRacks().remove(rack);
		rack.setHueco(null);
		return rack;
	}
	public List<Toma> getTomas() {
		return this.tomas;
	}
	public void setTomas(List<Toma> tomas) {
		this.tomas = tomas;
	}
	public Toma addToma(Toma toma) {
		getTomas().add(toma);
		toma.setHueco(this);
		return toma;
	}
	public Toma removeToma(Toma toma) {
		getTomas().remove(toma);
		toma.setHueco(null);
		return toma;
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
		Hueco other = (Hueco) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}