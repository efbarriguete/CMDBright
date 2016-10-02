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

package dao.implJpa;

import org.springframework.stereotype.Repository;

import dao.generic.GenericDAOJpa;
import dao.interfaces.IFabricanteDAO;
import model.catalog.Fabricante;

@Repository
public class FabricanteDAOJpa extends GenericDAOJpa<Fabricante, Short> implements IFabricanteDAO{
		
	public FabricanteDAOJpa(){
		super();
		setClazz(Fabricante.class);
	}
}
