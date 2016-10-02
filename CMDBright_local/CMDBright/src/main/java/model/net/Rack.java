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
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import model.equip.Equip;
import model.location.Hueco;


/**
 * The persistent class for the rack database table.
 * 
 */
@Entity
@NamedQuery(name="Rack.findAll", query="SELECT r FROM Rack r")
@Component
@Scope("request")
public class Rack implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(generator="rack_id_seq") //lo voy a llamar igual que la secuencia que he creado en postgreSQL -> menos confusión 
	@SequenceGenerator(name="rack_id_seq", sequenceName="rack_id_seq", allocationSize=1)
	private Short id;
	private String descrip;
	private String nombre;

	//bi-directional many-to-one association to Equip
	@OneToMany(mappedBy="rack")
	private List<Equip> equips;

	//bi-directional many-to-one association to PatchPanel
	@OneToMany(mappedBy="rack")
	private List<PatchPanel> patchPanels;

	//bi-directional many-to-one association to Hueco
	@ManyToOne
	@JoinColumn(name="id_hueco")
	private Hueco hueco;

	
	public Rack() {
	}
	public Short getId() {
		return this.id;
	}
	public void setId(Short id) {
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
	public List<Equip> getEquips() {
		return this.equips;
	}
	public void setEquips(List<Equip> equips) {
		this.equips = equips;
	}
	public Equip addEquip(Equip equip) {
		getEquips().add(equip);
		equip.setRack(this);
		return equip;
	}
	public Equip removeEquip(Equip equip) {
		getEquips().remove(equip);
		equip.setRack(null);
		return equip;
	}
	public List<PatchPanel> getPatchPanels() {
		return this.patchPanels;
	}
	public void setPatchPanels(List<PatchPanel> patchPanels) {
		this.patchPanels = patchPanels;
	}
	public PatchPanel addPatchPanel(PatchPanel patchPanel) {
		getPatchPanels().add(patchPanel);
		patchPanel.setRack(this);
		return patchPanel;
	}
	public PatchPanel removePatchPanel(PatchPanel patchPanel) {
		getPatchPanels().remove(patchPanel);
		patchPanel.setRack(null);
		return patchPanel;
	}
	public Hueco getHueco() {
		return this.hueco;
	}
	public void setHueco(Hueco hueco) {
		this.hueco = hueco;
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
		Rack other = (Rack) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}