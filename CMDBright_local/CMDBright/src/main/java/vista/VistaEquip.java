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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import model.catalog.Fabricante;
import model.catalog.Model;
import model.catalog.TipoModel;
import model.equip.Equip;
import model.equip.EstadoEquip;
import model.equip.Interfaz;
import model.equip.Ip;
import model.equip.Rango;
import model.equip.Servicio;
import model.location.Hueco;
import model.net.Toma;
import model.supply.Operacion;
import service.SEquip;
import service.SEstadoEquip;
import service.SFabricante;
import service.SHueco;
import service.SIp;
import service.SModel;
import service.SOperacion;
import service.SRango;
import service.SServicio;
import service.STipoModel;
import service.SToma;

@Component("vistaEquip")
@Scope("session")
public class VistaEquip implements Serializable {
	private static final long serialVersionUID = 3895369471017824663L;
	private static final Logger _log = LoggerFactory.getLogger(VistaEquip.class);
	
	private List<Equip> equips;
	private List<Equip> equipsFiltrados;
	private List<Model> models;
	private List<TipoModel> tipoModels;
	private List<Fabricante> fabricantes;
	private List<Rango> rangos;
	private List<Interfaz> interfazs;
	private List<Ip> ips;
	private List<EstadoEquip> estados;
	private List<Servicio> servicios;
	private List<Hueco> huecos;
	private List<Operacion> operacions;
	private List<Toma> tomas;
	
	@Autowired
	private SEquip s;
	@Autowired
	private SModel sM;
	@Autowired
	private STipoModel sTM;
	@Autowired
	private SFabricante sF;
	@Autowired
	private SRango sR;
	@Autowired
	private SIp sIp;
	@Autowired
	private SToma sT;
	@Autowired
	private SEstadoEquip sE;
	@Autowired
	private SServicio sServ;
	@Autowired
	private SHueco sH;
	@Autowired
	private SOperacion sO;

	private String btnPorDefecto;
	private Interfaz interfaz;
	private Equip datosForm;
	private Equip seleccionado;
	private Interfaz iSeleccionada;
	private Rango rango;
	private Ip ip;
	private Hueco hueco;
	private Toma toma;
	private Toma tomaAux;
	private Boolean btnAddDesactivado;
	private Boolean btnEditDesactivado;
	private Boolean btnEspecialesDesactivado;
	private Boolean estadoPanel;
	private Boolean mostrarSavBtn;
	private Boolean mostrarModBtn;
	private Boolean mostrarDelBtn;
	private Boolean mostrarInterfazForm;
	private Boolean noHayInterfazSeleccionada;
	private Boolean mostrarSavInterfazNew;
	private Boolean mostrarSavInterfazMod;
	private Boolean ipModificada;
	private Boolean tomaModificada;
	private String headerPanel;
	private Integer total;
	private String verTotal;

	/**********************************************************************************************/	
	/*************************   DEFINIENDO GETTERS Y SETTERS *************************************/	
	/**********************************************************************************************/
	public List<Interfaz> getInterfazs() {
		if (datosForm != null) {
			interfazs = datosForm.getInterfazs();
		} else {interfazs = null;}
		return interfazs;
	}
	public List<Ip> getIps() {
		return ips;
	}
	public List<Rango> getRangos() {
		rangos = sR.findAll();
		return rangos;
	}
	public List<EstadoEquip> getEstados() {
		estados = sE.findAll();
		return estados;
	}
	public List<Servicio> getServicios() {
		servicios = sServ.findAll();
		return servicios;
	}
	public List<Hueco> getHuecos() {
		huecos = sH.findAll();
		return huecos;
	}
	public List<Operacion> getOperacions() {
		operacions = sO.findAll();
		return operacions;
	}
	public List<Toma> getTomas() {
		tomas = sT.findAll();
		return tomas;
	}
	public List<Model> getModels() {
		models = sM.findAll();
		return models;
	}
	public List<TipoModel> getTipoModels() {
		tipoModels = sTM.findAll();
		return tipoModels;
	}
	public List<Fabricante> getFabricantes() {
		fabricantes = sF.findAll();
		return fabricantes;
	}
	public List<Equip> getEquips() {
		return equips;
	}
	public void setEquips(List<Equip> equips) {
		this.equips = equips;
	}
	public List<Equip> getEquipsFiltrados() {
		return equipsFiltrados;
	}
	public void setEquipsFiltrados(List<Equip> equipsFiltrados) {
		this.equipsFiltrados = equipsFiltrados;
	}
	public SEquip getS() {
		return s;
	}
	public void setS(SEquip s) {
		this.s = s;
	}
	public void setsM(SModel sM) {
		this.sM = sM;
	}
	public void setsTM(STipoModel sTM) {
		this.sTM = sTM;
	}
	public void setsF(SFabricante sF) {
		this.sF = sF;
	}
	public void setsR(SRango sR) {
		this.sR = sR;
	}
	public void setsIp(SIp sIp) {
		this.sIp = sIp;
	}
	public void setsE(SEstadoEquip sE) {
		this.sE = sE;
	}
	public void setsServ(SServicio sServ) {
		this.sServ = sServ;
	}
	public void setsH(SHueco sH) {
		this.sH = sH;
	}
	public void setsO(SOperacion sO) {
		this.sO = sO;
	}

	public Interfaz getInterfaz() {
		return interfaz;
	}
	public void setInterfaz(Interfaz interfaz) {
		this.interfaz = interfaz;
	}
	public Equip getDatosForm() {
		return datosForm;
	}
	public void setDatosForm(Equip datosForm) {
		this.datosForm = datosForm;
	}
	public Equip getSeleccionado() {
		return seleccionado;
	}
	public void setSeleccionado(Equip seleccionado) {
		this.seleccionado = seleccionado;
	}
	public Interfaz getiSeleccionada() {
		return iSeleccionada;
	}
	public void setiSeleccionada(Interfaz iSeleccionada) {
		this.iSeleccionada = iSeleccionada;
	}
	public Rango getRango() {
		return rango;
	}
	public void setRango(Rango rango) {
		this.rango = rango;
	}
	public Ip getIp() {
		return ip;
	}
	public void setIp(Ip ip) {
		this.ip = ip;
	}
	public Hueco getHueco() {
		return hueco;
	}
	public void setHueco(Hueco hueco) {
		this.hueco = hueco;
	}
	public Toma getToma() {
		return toma;
	}
	public void setToma(Toma toma) {
		this.toma = toma;
	}
	public String getBtnPorDefecto() {
		return btnPorDefecto;
	}
	public Boolean getBtnAddDesactivado() {
		return btnAddDesactivado;
	}
	public Boolean getBtnEditDesactivado() {
		btnEditDesactivado = (seleccionado == null); //compruebo si hay seleccionada alguna línea en la tabla de datos
		return btnEditDesactivado;
	}
	public Boolean getBtnEspecialesDesactivado() {
		return btnEspecialesDesactivado;
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
	public Boolean getMostrarInterfazForm() {
		return mostrarInterfazForm;
	}
	public Boolean getNoHayInterfazSeleccionada() {
		noHayInterfazSeleccionada = (iSeleccionada == null); //compruebo si hay seleccionada alguna interfaz
		return noHayInterfazSeleccionada;
	}
	public Boolean getMostrarSavInterfazNew() {
		return mostrarSavInterfazNew;
	}
	public Boolean getMostrarSavInterfazMod() {
		return mostrarSavInterfazMod;
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
		ipModificada = false;
		tomaModificada = false;
		btnPorDefecto = "addBtn";
		iSeleccionada = null;
		equips = s.findAll();
		total = equips.size();
		datosForm = new Equip(); //instancio los datos del formulario para tener disponible el objeto
		//interfaz = new Interfaz(); //instancio la interfaz para tener disponible este objeto
		actualizarPanel("formVer:tablaMain");
	}
	public void resetForm() {
		RequestContext.getCurrentInstance().reset("formAdd:datosNuevos");
	}
	public void vaciaValoresForm() {
		datosForm=null;
	}
	public void resetBean() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("vistaEquip");
    }
	public void bloquearPanel() {
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('buiFV').show();");
	}
	public void desbloquearPanel() {
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('buiFV').hide();");
	}
	public void bloquearPanelInterfaz() {
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('buiPanelInterfazs').show();");
	}
	public void desbloquearPanelInterfaz() {
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('buiPanelInterfazs').hide();");
	}
	public void actualizarPanel(String panel) {
		RequestContext context = RequestContext.getCurrentInstance();
		context.addCallbackParam("saved", true); //no sé si es necesario
		context.update(panel);
	}
	public void add() {
		bloquearPanel();
		vaciaValoresForm();
		resetForm();
		datosForm = new Equip(); //Instancio un nuevo modelo, ya que de momento m apunta a null.
		btnAddDesactivado=true;
		btnEspecialesDesactivado=true;
		mostrarSavBtn = true;
		mostrarModBtn = false;
		mostrarDelBtn = false;
		estadoPanel = true;
		headerPanel = "--- AÑADIR ---";
		seleccionado = new Equip();
		seleccionado = null;
		btnPorDefecto = "savBtn"; //asigno el botón que actuará por defecto al presionar "ENTER"
		//iniciando panel de interfaces:
		bloquearPanelInterfaz();
		mostrarInterfazForm = false;
	}
	public void edit() {
		bloquearPanel();
		resetForm();
		btnAddDesactivado = true;
		btnEspecialesDesactivado=false;
		mostrarSavBtn = false;
		mostrarModBtn = true;
		mostrarDelBtn = false;
		//--- ya no me hace falta el IF porque controlo los botones---
		headerPanel = "--- EDITAR  ---";
		estadoPanel = true;
		datosForm = new Equip();
		setDatosForm(seleccionado);//Asigno al objeto m la entidad seleccionada
		seleccionado = new Equip(); //para que no se me borre una línea en la tabla de datos, antes de poner el objeto a null, lo instancio.
		seleccionado = null;
		btnPorDefecto = "modBtn"; //asigno el botón que actuará por defecto al presionar "ENTER"
		//iniciando panel de interfaces:
		desbloquearPanelInterfaz();
		mostrarInterfazForm = false;
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
		seleccionado = new Equip(); //para que no se me borre una línea en la tabla de datos, antes de poner el objeto a null, lo instancio.
		seleccionado = null;
		btnPorDefecto = "cancelBtn"; //asigno el botón que actuará por defecto al presionar "ENTER"
		//iniciando panel de interfaces:
		desbloquearPanelInterfaz();
		mostrarInterfazForm = false;
	}
		
	public String grabarDatosNew() {
		try {
			s.create(datosForm);
			FacesContext.getCurrentInstance().addMessage("mensajes", new FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", "NUEVO equip guardado: " + datosForm.getNombre()));		
//			init(); //inicio la vista
			seleccionado = s.findOne(datosForm.getId()); //para pasar a edit con este mismo equipo "seleccionado" --> lo vuelvo a recuperar de la base de datos pq sino está detached?? creo...
			edit(); //no salgo del panel de modificación
		} catch (DataIntegrityViolationException ex) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Imposible agregar: " + datosForm.getNombre() + ": ", "Ya existe un equip con ese nombre"));
			datosForm = new Equip(); //Instancio un nuevo modelo, ya que de momento m apunta a null.
		}
		return null; //¿puse esto porque me saltaba algún error???
	}
	public String grabarDatosMod() {
		try {
			if (ipModificada){ //sólo persisto los cambios si he modificado la ip
				sIp.update(ip); //persisto los cambios en la ip antigua modificada
				ipModificada = false;
			}
			s.update(datosForm); // Primero actualizo el equipo y en cascada interfaz y desconecto toma. Después actualizo la toma desconectada a false:
			if (tomaModificada){ //igual que en el if anterior, pero con toma
				sT.update(tomaAux); 
				tomaModificada = false;
			}
			FacesContext.getCurrentInstance().addMessage("mensajes", new FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", "Equip modificado: " + datosForm.getNombre()));		
			init(); //inicio la vista
		} catch (DataIntegrityViolationException ex) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Imposible actualizar: " + datosForm.getNombre() + ": ", " Ya existe un equip con ese nombre -- " + ex.getMessage()));
//			datosForm = new Equip(); //Instancio un nuevo modelo, ya que de momento m apunta a null.
		} catch (IllegalStateException ex) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Imposible actualizar: " + datosForm.getNombre() + ": ", " Problema en conexión: -- " + ex.getMessage()));
		}
			return null; //¿puse esto porque me saltaba algún error???
	}
	public String borrar() {
		//Según la documentación de Spring, no tengo que controlar excepciones de no respuesta a la BD, ya que son runtime exceptions...
		//TODO Borrar todas las interfaces primero para que se actualicen las ip's
		vaciarListaInterfaces(); 
//		if  (datosForm.getInterfazs() != null) {datosForm.getInterfazs().clear();} //antes de borrar un equipo, tengo que vaciar su lista de interfaces y así liberar las ip's en uso.
		s.delete(datosForm);
		FacesContext.getCurrentInstance().addMessage("mensajes", new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención:", "Equip BORRADO: " + datosForm.getNombre()));		
		vaciaValoresForm(); //vacío el formulario
		init(); //inicio la vista
		return null; //¿puse esto porque me saltaba algún error???
	}
	//antes de borrar un equipo, tengo que vaciar su lista de interfaces y así liberar las ip's en uso.
	private void vaciarListaInterfaces(){
		Interfaz interfaz = new Interfaz();
		int iii = 0;
		_log.info("num de interfaces: {}", datosForm.getInterfazs().size());
		for (int i=0; i<datosForm.getInterfazs().size();i++) {
			interfaz = datosForm.getInterfazs().get(i);
			iii = i;
			_log.info("Interfaz seleccionada: {}", interfaz.getMac());
			if (interfaz != null) { 
				_log.info("Iteración: {}", i);
				if (interfaz.getIp() != null) {
					_log.info("Borrando ip: {}", interfaz.getIp().getIp());
					ip = interfaz.getIp();
					ip.setUsed(false); //la vieja ip asignada la marco como NO usada... la persisto manualmente desde grabarDatosMod()
					sIp.update(ip);
				}
				if (interfaz.getToma() != null) {
					_log.info("Borrando toma: {}", interfaz.getToma().getNombre());
					toma = interfaz.getToma();
					toma.setUsedIf(false);
					sT.update(toma);
				}
				//datosForm.removeInterfaz(interfaz); no quitar interfaces una a una, se sale del bucle y además no es necesario, porque al remover posteriormente el elemento, se borran en cascada.
			}
		}
		_log.info("Fuera del bucle for --> i = {}", iii);
	}
	public String cancelar() {
		init(); //inicio la vista
		return null; //¿puse esto porque me saltaba algún error???
	}
	
/**********************************************************************
 *  Manipulando interfaces de red:
 **********************************************************************/
	public void onChangeRango() {
		//this.ips = sIp.findAll(); TODO Filtrar por rango y si no está usada:
		this.ips = sIp.findByRango(rango);
	}
	public void onChangeHueco() {
		this.tomas = sT.findByHueco(hueco);
	}
	public void inicializaDialog() {
		toma = new Toma();
//		tomas.clear();
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('connInterfazDlg').show();");
	}
	public void newInterfaz() {
		interfaz = new Interfaz();
//		if (ips != null) {ips.clear();}
		rango = new Rango();
		iSeleccionada = new Interfaz(); //elimino selección
		iSeleccionada = null;
		mostrarSavInterfazNew = true;
		mostrarSavInterfazMod = false;
		mostrarInterfazForm = true;
	}
	public void modInterfaz() {
		//if (ips != null) {ips.clear();}
		interfaz = new Interfaz();
		rango = new Rango();
		if (iSeleccionada.getIp() != null){
			rango = iSeleccionada.getIp().getRango(); //Si ya tiene asignada una ip actualmente, busco su rango para mostrarlo
			onChangeRango();
		}
		interfaz.setId(iSeleccionada.getId());
		interfaz.setMac(iSeleccionada.getMac());//Asigno al objeto m la entidad seleccionada
		interfaz.setIp(iSeleccionada.getIp()); //una vez cargado el rango, puedo cargar su ip
		interfaz.setToma(iSeleccionada.getToma());
		mostrarSavInterfazNew = false;
		mostrarSavInterfazMod = true;
		mostrarInterfazForm = true;
	}
	public void delInterfaz() {
//		if (iSeleccionada.getId() != -1) {
		ip = iSeleccionada.getIp(); //obtengo la ip actualmente configurada en esta interfaz 
		ip.setUsed(false); //la vieja ip asignada la marco como NO usada... la persisto manualmente desde grabarDatosMod()
		ipModificada = true; //indico que he modificado la ip
		
		datosForm.removeInterfaz(iSeleccionada);
		iSeleccionada = new Interfaz();
		iSeleccionada = null;
		mostrarInterfazForm = false;
	}
	public void cancelInterfaz() {
		mostrarInterfazForm = false;
		ip = new Ip();
		ipModificada = false;
	}

	public void savInterfazNew() {
		interfaz.setId(-1); //lo inicializo a -1 para que "id" no sea null y no me dé error DataModel ... cuando lo persista, postgreSQL debería darle el valor correcto.
		if (interfaz.getIp() != null) {
			interfaz.getIp().setUsed(true); //la nueva ip asignada la marco como usada... se persistirá en cascada
		}
		datosForm.addInterfaz(interfaz); //añado la nueva interfaz
		mostrarInterfazForm = false;
	}
	public void savInterfazMod() {
//		iSeleccionada.getIp().setUsed(false); //la vieja ip asignada la marco como NO usada... la persisto manualmente en la siguiente línea
//		ip = new Ip();
		ip = iSeleccionada.getIp(); 
		ip.setUsed(false); //la vieja ip asignada la marco como NO usada... la persisto manualmente desde grabarDatosMod()
		ipModificada = true; //indico que he modificado la ip
		iSeleccionada.setMac(interfaz.getMac());
		iSeleccionada.setIp(interfaz.getIp());
		if (iSeleccionada.getIp() != null) {
			iSeleccionada.getIp().setUsed(true); //la nueva ip asignada la marco como usada... se persistirá en cascada
		}
		//iSeleccionada.setToma(interfaz.getToma()); la toma se modifica en otro apartado

		mostrarInterfazForm = false;
	}
	public void conectaInterfaz() {
		if (iSeleccionada.getToma() != null){
			tomaAux = new Toma();
			tomaAux = iSeleccionada.getToma();
			tomaAux.setUsedIf(false);
			tomaModificada = true;
		}
		toma.setUsedIf(true);
		iSeleccionada.setToma(toma);
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('connInterfazDlg').hide();");
	}
	public void desconectaInterfaz() {
		if (iSeleccionada.getToma() != null){
			tomaAux = new Toma();
			tomaAux = iSeleccionada.getToma();
			tomaAux.setUsedIf(false);
			tomaAux.setInterfaz(null); //para que no nos dé un error al no encontrar la interfaz que hemos desconectado y ya no hace referencia a ella. 
			tomaModificada = true;
			iSeleccionada.setToma(null); //elimino la toma conectada actual
		}
	}

	/**********************************************************************
	 *  Funciones especiales:
	 **********************************************************************/
	public void intercambio() {
	 
	}
}