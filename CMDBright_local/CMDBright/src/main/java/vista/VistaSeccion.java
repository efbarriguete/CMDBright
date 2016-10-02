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
import org.primefaces.event.FileUploadEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import model.location.Centro;
import model.location.Planta;
import model.location.Seccion;
import service.SCentro;
import service.SPlanta;
import service.SSeccion;

@Component("vistaSeccion")
@Scope("session")
public class VistaSeccion implements Serializable {
	private static final long serialVersionUID = 3895369471017824663L;

	private List<Seccion> seccions;
	private List<Seccion> seccionsFiltrados;
	private List<Centro> centros;
	private List<Planta> plantas;
	
	@Autowired
	private SCentro sC;
	@Autowired
	private SPlanta sP;
	@Autowired
	private SSeccion s;
	
	private Centro centro;

	private Seccion datosForm;
	private Seccion seleccionado;
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

	public List<Seccion> getSeccions() {
		return seccions;
	}
	public void setSeccions(List<Seccion> seccions) {
		this.seccions = seccions;
	}
	public List<Seccion> getSeccionsFiltrados() {
		return seccionsFiltrados;
	}
	public void setSeccionsFiltrados(List<Seccion> seccionsFiltrados) {
		this.seccionsFiltrados = seccionsFiltrados;
	}
	public List<Centro> getCentros() {
		centros = sC.findAll();
		return centros;
	}
	public List<Planta> getPlantas() {
		return plantas;
	}
	public SSeccion getS() {
		return s;
	}
	public void setS(SSeccion s) {
		this.s = s;
	}
	public void setsC(SCentro sC) {
		this.sC = sC;
	}
	public void setsP(SPlanta sP) {
		this.sP = sP;
	}
	public Centro getCentro() {
		return centro;
	}
	public void setCentro(Centro centro) {
		this.centro = centro;
	}
	public Seccion getDatosForm() {
		return datosForm;
	}
	public void setDatosForm(Seccion datosForm) {
		this.datosForm = datosForm;
	}
	public Seccion getSeleccionado() {
		return seleccionado;
	}
	public void setSeleccionado(Seccion seleccionado) {
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
		seccions = s.findAll();
		total = seccions.size();
		actualizarPanel("formVer:tablaMain");
	}
	public void resetForm() {
		RequestContext.getCurrentInstance().reset("formAdd:datosNuevos");
	}
	public void vaciaValoresForm() {
		datosForm=null;
	}
	public void resetBean() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("vistaSeccion");
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
		datosForm = new Seccion(); //Instancio un nuevo modelo, ya que de momento m apunta a null.
		centro = new Centro();
		mostrarSavBtn = true;
		mostrarModBtn = false;
		mostrarDelBtn = false;
		estadoPanel = true;
		headerPanel = "--- AÑADIR ---";
		seleccionado = new Seccion();
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
		centro = seleccionado.getPlanta().getCentro();
		seleccionado = new Seccion(); //para que no se me borre una línea en la tabla de datos, antes de poner el objeto a null, lo instancio.
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
		centro = seleccionado.getPlanta().getCentro();
		seleccionado = new Seccion(); //para que no se me borre una línea en la tabla de datos, antes de poner el objeto a null, lo instancio.
		seleccionado = null;
	}
		
	public String grabarDatosNew() {
		try {
			s.create(datosForm);
			FacesContext.getCurrentInstance().addMessage("mensajes", new FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", "NUEVO seccion guardado: " + datosForm.getNombre()));		
			init(); //inicio la vista
		} catch (DataIntegrityViolationException ex) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Imposible agregar: " + datosForm.getNombre() + ": ", "Ya existe un seccion con ese nombre"));
			datosForm = new Seccion(); //Instancio un nuevo modelo, ya que de momento m apunta a null.
		}
		return null; //¿puse esto porque me saltaba algún error???
	}
	public String grabarDatosMod() {
		try {
			s.update(datosForm);
			FacesContext.getCurrentInstance().addMessage("mensajes", new FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", "Seccion modificado: " + datosForm.getNombre()));		
			init(); //inicio la vista
		} catch (DataIntegrityViolationException ex) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Imposible actualizar: " + datosForm.getNombre() + ": ", "Ya existe un seccion con ese nombre"));
			datosForm = new Seccion(); //Instancio un nuevo modelo, ya que de momento m apunta a null.
		}
			return null; //¿puse esto porque me saltaba algún error???
	}
	public String borrar() {
		//Según la documentación de Spring, no tengo que controlar excepciones de no respuesta a la BD, ya que son runtime exceptions...
		s.delete(datosForm);
		FacesContext.getCurrentInstance().addMessage("mensajes", new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención:", "Seccion BORRADO: " + datosForm.getNombre()));		
		vaciaValoresForm(); //vacío el formulario
		init(); //inicio la vista
		return null; //¿puse esto porque me saltaba algún error???
	}
	public String cancelar() {
		init(); //inicio la vista
		return null; //¿puse esto porque me saltaba algún error???
	}
	public void onChangeCentro() {
		
		this.plantas = sP.findByCentro(centro);
	}
	public String getFicheroDelPlano(String newPath) {
		String path = new String("");
		path = datosForm.getPlano().getRutaPlano();
		if (path != null && path != "") {
			return path;
		} 
		else {return newPath;}
	}
	public void recogeArchivo(FileUploadEvent event) {
		/*try {
		    String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
		    System.out.println(path);

		    SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");
		    String name = fmt.format(new Date()) + event.getFile().getFileName().substring(event.getFile().getFileName().lastIndexOf('.'));
		    path = path + "img/planos/" + name;
		    
		    File file = new File(getFicheroDelPlano(path));//si ya existe uno, lo sobreescribiré, sino me devolverá una nueva ruta
		    
	
		    InputStream is = event.getFile().getInputstream();
		    OutputStream out = new FileOutputStream(file);
		    byte buf[] = new byte[1024];
		    int len;
		    while ((len = is.read(buf)) > 0)
		        out.write(buf, 0, len);
		    is.close();
		    out.close();
			
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Archivo subido: ", event.getFile().getFileName() + " a la ruta local: " + File.pathSeparator);
	        FacesContext.getCurrentInstance().addMessage(null, message);
		}catch (Exception e) {
			datosForm.getPlano().setRutaPlano("");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"Imposible subir archivo: " + event.getFile().getFileName() + " a: " + File.pathSeparator, "Error desconocido por ahora..."));
		}*/
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}