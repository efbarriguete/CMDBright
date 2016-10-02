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

package vista;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import model.catalog.Model;
import model.equip.Equip;
import model.equip.EstadoEquip;
import service.SEquip;
import service.SEstadoEquip;
import service.SModel;

//@ManagedBean(name = "nuevoModel")
//@SessionScoped
//@RequestScoped
@Component("nuevoEquip")
@Scope("request")
public class NuevoEquip {/* implements Serializable {
	private static final long serialVersionUID = 4333183461325707701L;*/
	private List<EstadoEquip> estados;
	private List<Model> models;
	
	@Autowired
	private SEquip s;
	@Autowired
	private SModel sM;
	@Autowired
	private SEstadoEquip sEst;
	@Autowired
	private Equip e;

	public Equip getE() {
		return e;
	}
	public void setE(Equip e) {
		this.e = e;
	}
	public void setsM(SModel sM) {
		this.sM = sM;
	}
	public void setsEst(SEstadoEquip sEst) {
		this.sEst = sEst;
	}
	public void setS(SEquip s) {
		this.s = s;
	}
	public List<EstadoEquip> getEstados() {
		estados = sEst.findAll();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("En getEstados. Estados descargados: " + estados.size()));
		return estados;
	}
	public List<Model> getModels() {
		models = sM.findAll();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("En getModels. Modelos encontrados: " + models.size()));
		return models;
	}
	public void infoModel() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
				("Tipo: \"" + e.getModel().getTipoModel() +"\" |  Fabricante: " + e.getModel().getFabricante().getNombre() + " |  Modelo: " + e.getModel().getNombre()));
	}
	public void resetForm() {
		//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("resetForm"));
		RequestContext.getCurrentInstance().reset("form:formAdd:panel");
	}
	public void vaciaValoresForm() {
		/*m.setTipoModel(null);
		m.setFabricante(null);
		m.setNombre(null);
		m.setPartNum(null);
		m.setEnrackable(null);*/
		// e=null; 
		//Únicamente vaciaremos los valores que nos interese, ya que podemos introducir otro equipo con el mismo modelo:
		
		e.setNombre(null);
		e.setEtSergas(null);
		e.setSerialNum(null);
	}
	public String grabarDatos() {
		//TODO control de excepciones
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
				("Nuevo equipo guardado: \"" + e.getNombre() +"\".  Estado: " + e.getEstadoEquip().getNombre() + ".  Modelo: " + e.getModel().getNombre()));

		
		s.create(e);
		
		vaciaValoresForm();
		return null;
	}
	public void resetBean() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("nuevoEquip");
    }	
}