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

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class Pages implements Serializable{
	private static final long serialVersionUID = 1L;

	private String mainPanel;
	private String modelPanel;
	private Integer tamanoScroll = 330; //Dejo definida la variable por si consigo modificar dinámicamente el tamaño del panel con scroll
	

	public String getMainPanel() {
		return mainPanel;
	}
	public void setMainPanel(String mainPanel) {
		this.mainPanel = mainPanel;
	}
	public String getModelPanel() {
		return modelPanel;
	}
	public void setModelPanel(String modelPanel) {
		this.modelPanel = modelPanel;
	}
	public Integer getTamanoScroll() {
		return tamanoScroll;
	}

/* Para cuando vuelva a intentar el scroll dinámico:
	public Integer getTamanoScroll() {
		if (tamanoScroll<100) return 100;
		else return tamanoScroll;
	}
	public void setTamanoScroll(Integer tamanoScroll) {
		this.tamanoScroll = tamanoScroll - 400;
	}
	public void actualizarPag() {
		RequestContext context = RequestContext.getCurrentInstance();
		context.addCallbackParam("tamanoScroll", true); //no sé si es necesario
		context.update("layoutMain");
	}*/

}