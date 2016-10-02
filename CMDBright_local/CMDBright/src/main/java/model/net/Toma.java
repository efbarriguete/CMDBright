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

import model.equip.Interfaz;
import model.issue.Incidencia;
import model.location.Hueco;


/**
 * The persistent class for the toma database table.
 * 
 */
@Entity
@NamedQuery(name="Toma.findAll", query="SELECT t FROM Toma t")
@Component
@Scope("request")
public class Toma implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(generator="toma_id_seq") //lo voy a llamar igual que la secuencia que he creado en postgreSQL -> menos confusión 
	@SequenceGenerator(name="toma_id_seq", sequenceName="toma_id_seq", allocationSize=1)
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name="cambio_toma_con_toma")
	private Date cambioTomaConToma;

	private String nombre;

	@Column(name="used_if")
	private Boolean usedIf;
	@Column(name="used_boca")
	private Boolean usedBoca;
	
	//bi-directional many-to-one association to BocaSw
//	@OneToOne (cascade=CascadeType.ALL)
	@OneToOne(mappedBy="toma", cascade=CascadeType.ALL)
	private BocaSw bocaSw;

	//bi-directional many-to-one association to Incidencia
	@OneToMany(mappedBy="toma")
	private List<Incidencia> incidencias;

	//bi-directional many-to-one association to BocaSw
/*	@OneToMany(mappedBy="toma")
	private List<BocaSw> bocaSws;

*/
//	@OneToOne (cascade=CascadeType.ALL)
	@OneToOne(fetch=FetchType.LAZY, mappedBy="toma")
	private Interfaz interfaz;

	//bi-directional many-to-one association to Hueco
	@ManyToOne
	@JoinColumn(name="id_hueco")
	private Hueco hueco;

	//bi-directional many-to-one association to PatchPanel
	@ManyToOne
	@JoinColumn(name="id_patch_panel")
	private PatchPanel patchPanel;

	//bi-directional many-to-one association to TipoToma
	@ManyToOne
	@JoinColumn(name="id_tipo_toma")
	private TipoToma tipoToma;

	//bi-directional many-to-one association to Toma
/*	@OneToOne
	private Toma toma;
*/
	public Toma() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCambioTomaConToma() {
		return this.cambioTomaConToma;
	}

	public void setCambioTomaConToma(Date cambioTomaConToma) {
		this.cambioTomaConToma = cambioTomaConToma;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Boolean getUsedIf() {
		return usedIf;
	}

	public void setUsedIf(Boolean usedIf) {
		this.usedIf = usedIf;
	}

	public Boolean getUsedBoca() {
		return usedBoca;
	}

	public void setUsedBoca(Boolean usedBoca) {
		this.usedBoca = usedBoca;
	}

	public List<Incidencia> getIncidencias() {
		return this.incidencias;
	}

	public void setIncidencias(List<Incidencia> incidencias) {
		this.incidencias = incidencias;
	}

	public Incidencia addIncidencia(Incidencia incidencia) {
		getIncidencias().add(incidencia);
		incidencia.setToma(this);

		return incidencia;
	}

	public Incidencia removeIncidencia(Incidencia incidencia) {
		getIncidencias().remove(incidencia);
		incidencia.setToma(null);

		return incidencia;
	}

	public Interfaz getInterfaz() {
		return this.interfaz;
	}

	public void setInterfaz(Interfaz interfaz) {
		this.interfaz = interfaz;
	}

	
	
	public BocaSw getBocaSw() {
		return this.bocaSw;
	}

	public void setBocaSw(BocaSw bocaSw) {
		this.bocaSw = bocaSw;
	}

	public Hueco getHueco() {
		return this.hueco;
	}

	public void setHueco(Hueco hueco) {
		this.hueco = hueco;
	}

	public PatchPanel getPatchPanel() {
		return this.patchPanel;
	}

	public void setPatchPanel(PatchPanel patchPanel) {
		this.patchPanel = patchPanel;
	}

	public TipoToma getTipoToma() {
		return this.tipoToma;
	}

	public void setTipoToma(TipoToma tipoToma) {
		this.tipoToma = tipoToma;
	}
/*
	public Toma getToma() {
		return this.toma;
	}

	public void setToma(Toma toma) {
		this.toma = toma;
	}
*/
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
		Toma other = (Toma) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

/*
public List<BocaSw> getBocaSws() {
	return this.bocaSws;
}

public void setBocaSws(List<BocaSw> bocaSws) {
	this.bocaSws = bocaSws;
}

public BocaSw addBocaSw(BocaSw bocaSw) {
	getBocaSws().add(bocaSw);
	bocaSw.setToma(this);

	return bocaSw;
}

public BocaSw removeBocaSw(BocaSw bocaSw) {
	getBocaSws().remove(bocaSw);
	bocaSw.setToma(null);

	return bocaSw;
}



public List<Interfaz> getInterfazs() {
	return this.interfazs;
}

public void setInterfazs(List<Interfaz> interfazs) {
	this.interfazs = interfazs;
}

public Interfaz addInterfaz(Interfaz interfaz) {
	getInterfazs().add(interfaz);
	interfaz.setToma(this);

	return interfaz;
}

public Interfaz removeInterfaz(Interfaz interfaz) {
	getInterfazs().remove(interfaz);
	interfaz.setToma(null);

	return interfaz;
}

public List<Toma> getTomas() {
	return this.tomas;
}

public void setTomas(List<Toma> tomas) {
	this.tomas = tomas;
}

public Toma addToma(Toma toma) {
	getTomas().add(toma);
	toma.setToma(this);

	return toma;
}

public Toma removeToma(Toma toma) {
	getTomas().remove(toma);
	toma.setToma(null);

	return toma;
}


*/
