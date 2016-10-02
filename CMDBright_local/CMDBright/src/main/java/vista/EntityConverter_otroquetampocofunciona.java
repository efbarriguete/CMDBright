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

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

@FacesConverter(value = "entityConverter_kkk")
@Scope("request")
public class EntityConverter_otroquetampocofunciona implements Converter {

	@Autowired
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public Object getAsObject(FacesContext fc, UIComponent component, String string) {
		try {
			String[] split = string.split(":");
			@SuppressWarnings("rawtypes")
			Class clazz = Class.forName(split[0]);
			for (Field f : clazz.getDeclaredFields()) {
				if (f.isAnnotationPresent(Id.class)) {
					Method valueOfMethod = f.getType().getMethod("valueOf", String.class);
					return em.find(clazz, valueOfMethod.invoke(null, split[1]));
				}
			}
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
		}
		return null;
	}

	public String getAsString(FacesContext fc, UIComponent component, Object object) {
		try {
			@SuppressWarnings("rawtypes")
			Class clazz = object.getClass();
			for (Field f : clazz.getDeclaredFields()) {
				if (f.isAnnotationPresent(Id.class)) {
					f.setAccessible(true);
					return clazz.getCanonicalName() + ":" + f.get(object);
				}
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
		}
		return null;
	}
}
