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
import javax.persistence.*;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * The persistent class for the tipo_operacion database table.
 * 
 */
@Entity
@Table(name="tipo_operacion")
@NamedQuery(name="TipoOperacion.findAll", query="SELECT t FROM TipoOperacion t")
@Component
@Scope("request")
public class TipoOperacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(generator="tipo_operacion_id_seq") //lo voy a llamar igual que la secuencia que he creado en postgreSQL -> menos confusión 
	@SequenceGenerator(name="tipo_operacion_id_seq", sequenceName="tipo_operacion_id_seq", allocationSize=1)
	private Short id;
	private String nombre;
	private String descrip;
	private String documento;

	//bi-directional many-to-one association to Operacion
	@OneToMany(mappedBy="tipoOperacion")
	private List<Operacion> operacions;


	public TipoOperacion() {
	}
	public Short getId() {
		return this.id;
	}
	public void setId(Short id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getDescrip() {
		return this.descrip;
	}
	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}
	public List<Operacion> getOperacions() {
		return this.operacions;
	}
	public void setOperacions(List<Operacion> operacions) {
		this.operacions = operacions;
	}
	public Operacion addOperacion(Operacion operacion) {
		getOperacions().add(operacion);
		operacion.setTipoOperacion(this);
		return operacion;
	}
	public Operacion removeOperacion(Operacion operacion) {
		getOperacions().remove(operacion);
		operacion.setTipoOperacion(null);
		return operacion;
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
		TipoOperacion other = (TipoOperacion) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}