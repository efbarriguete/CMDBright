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

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import model.catalog.TipoModel;
import service.STipoModel;

@Component("vistaTipoModel")
@Scope("session")
public class VistaTipoModel implements Serializable {
	private static final long serialVersionUID = 3895369471017824663L;

	private List<TipoModel> tipoModels;
	private List<TipoModel> tipoModelsFiltrados;
	
	@Autowired
	private STipoModel s;
	
	private TipoModel datosForm;
	private TipoModel seleccionado;
	private Boolean btnAddDesactivado;
	private Boolean btnEditDesactivado;
	private Boolean estadoPanel;
	private Boolean mostrarSavBtn;
	private Boolean mostrarModBtn;
	private Boolean mostrarDelBtn;
	private String headerPanel;
	private Integer total;
	private String verTotal;

	/**********************************************************************************************/	
	/*************************   DEFINIENDO GETTERS Y SETTERS *************************************/	
	/**********************************************************************************************/

	public List<TipoModel> getTipoModels() {
		return tipoModels;
	}
	public void setTipoModels(List<TipoModel> tipoModels) {
		this.tipoModels = tipoModels;
	}
	public List<TipoModel> getTipoModelsFiltrados() {
		return tipoModelsFiltrados;
	}
	public void setTipoModelsFiltrados(List<TipoModel> tipoModelsFiltrados) {
		this.tipoModelsFiltrados = tipoModelsFiltrados;
	}
	public STipoModel getS() {
		return s;
	}
	public void setS(STipoModel s) {
		this.s = s;
	}
	public TipoModel getDatosForm() {
		return datosForm;
	}
	public void setDatosForm(TipoModel datosForm) {
		this.datosForm = datosForm;
	}
	public TipoModel getSeleccionado() {
		return seleccionado;
	}
	public void setSeleccionado(TipoModel seleccionado) {
		this.seleccionado = seleccionado;
	}
	public Boolean getBtnAddDesactivado() {
		return btnAddDesactivado;
	}
	public Boolean getBtnEditDesactivado() {
		btnEditDesactivado = (seleccionado == null); //compruebo si hay seleccionada alguna línea en la tabla de datos
		return btnEditDesactivado;
	}
	public Boolean getEstadoPanel() {
		return estadoPanel;
	}
	public Boolean getMostrarSavBtn() {
		return mostrarSavBtn;
	}
	public Boolean getMostrarModBtn() {
		return mostrarModBtn;
	}
	public Boolean getMostrarDelBtn() {
		return mostrarDelBtn;
	}
	public String getHeaderPanel() {
		return headerPanel;
	}
	public Integer getTotal() {
		return total;
	}
	public void setVerTotal(String verTotal) {
		this.verTotal = verTotal;
	}	
	public String getVerTotal() {
		verTotal = ("Total almacenados: " + total);
		return verTotal;
	}

	/**********************************************************************************************/	
	/*************************   FIN GETTERS Y SETTERS *************************************/	
	/**********************************************************************************************/	
	
	@PostConstruct
	public void init() {
		desbloquearPanel();
		estadoPanel = false;
		btnAddDesactivado = false;
		tipoModels = s.findAll();
		total = tipoModels.size();
		actualizarPanel("formVer:tablaMain");
	}
	public void resetForm() {
		RequestContext.getCurrentInstance().reset("formAdd:datosNuevos");
	}
	public void vaciaValoresForm() {
		datosForm=null;
	}
	public void resetBean() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("vistaTipoModel");
    }
	public void bloquearPanel() {
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('buiFV').show();");
	}
	public void desbloquearPanel() {
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('buiFV').hide();");
	}
	public void actualizarPanel(String panel) {
		RequestContext context = RequestContext.getCurrentInstance();
		context.addCallbackParam("saved", true); //no sé si es necesario
		context.update(panel);
	}
	public void add() {
		bloquearPanel();
		btnAddDesactivado=true;
		vaciaValoresForm();
		datosForm = new TipoModel(); //Instancio un nuevo modelo, ya que de momento m apunta a null.
		mostrarSavBtn = true;
		mostrarModBtn = false;
		mostrarDelBtn = false;
		estadoPanel = true;
		headerPanel = "--- AÑADIR ---";
		seleccionado = new TipoModel();
		seleccionado = null;
	}
	public void edit() {
		bloquearPanel();
		btnAddDesactivado = true;
		mostrarSavBtn = false;
		mostrarModBtn = true;
		mostrarDelBtn = false;
		//--- ya no me hace falta el IF porque controlo los botones---
		headerPanel = "--- EDITAR  ---";
		estadoPanel = true;
		setDatosForm(seleccionado);//Asigno al objeto m la entidad seleccionada
		seleccionado = new TipoModel(); //para que no se me borre una línea en la tabla de datos, antes de poner el objeto a null, lo instancio.
		seleccionado = null;
	}
	public void eliminar() {
		bloquearPanel();
		btnAddDesactivado = true;
		mostrarSavBtn = false;
		mostrarModBtn = false;
		mostrarDelBtn = true;
		//--- ya no me hace falta el IF porque controlo los botones---
		headerPanel = "--- ¡¡ATENCIÓN!! Estás a punto de BORRAR el siguiente elemento de la Base de datos: ---";
		estadoPanel = true;
		setDatosForm(seleccionado);//Asigno al objeto m la entidad seleccionada
		seleccionado = new TipoModel(); //para que no se me borre una línea en la tabla de datos, antes de poner el objeto a null, lo instancio.
		seleccionado = null;
	}
		
	public String grabarDatosNew() {
		try {
			s.create(datosForm);
			FacesContext.getCurrentInstance().addMessage("mensajes", new FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", "NUEVO tipoModel guardado: " + datosForm.getNombre()));		
			init(); //inicio la vista
		} catch (DataIntegrityViolationException ex) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Imposible agregar: " + datosForm.getNombre() + ": ", "Ya existe un tipoModel con ese nombre"));
			datosForm = new TipoModel(); //Instancio un nuevo modelo, ya que de momento m apunta a null.
		}
		return null; //¿puse esto porque me saltaba algún error???
	}
	public String grabarDatosMod() {
		try {
			s.update(datosForm);
			FacesContext.getCurrentInstance().addMessage("mensajes", new FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", "TipoModel modificado: " + datosForm.getNombre()));		
			init(); //inicio la vista
		} catch (DataIntegrityViolationException ex) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Imposible actualizar: " + datosForm.getNombre() + ": ", "Ya existe un tipoModel con ese nombre"));
			datosForm = new TipoModel(); //Instancio un nuevo modelo, ya que de momento m apunta a null.
		}
			return null; //¿puse esto porque me saltaba algún error???
	}
	public String borrar() {
		//Según la documentación de Spring, no tengo que controlar excepciones de no respuesta a la BD, ya que son runtime exceptions...
		s.delete(datosForm);
		FacesContext.getCurrentInstance().addMessage("mensajes", new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención:", "TipoModel BORRADO: " + datosForm.getNombre()));		
		vaciaValoresForm(); //vacío el formulario
		init(); //inicio la vista
		return null; //¿puse esto porque me saltaba algún error???
	}
	public String cancelar() {
		init(); //inicio la vista
		return null; //¿puse esto porque me saltaba algún error???
	}	
}