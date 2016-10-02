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

package service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.interfaces.IEstadoEquipDAO;
import model.equip.EstadoEquip;

@Service ("sEstadoEquip")
@Transactional
public class SEstadoEquip implements Serializable {
	private static final long serialVersionUID = 1L;

    @Autowired
    private IEstadoEquipDAO dao;

    
    public SEstadoEquip() {
        super();
    }
    public void create(final EstadoEquip entity) {
        dao.create(entity);
    }
    public EstadoEquip findOne(final Short id) {
        return dao.findOne(id);
    }
    public List<EstadoEquip> findAll() {
        return dao.findAll();
    }
    public void update(final EstadoEquip entity) {
        dao.update(entity);
    }
    public void delete(final EstadoEquip entity) {
    	EstadoEquip entidadParaBorrar = findOne(entity.getId()); //para evitar error "atached object"
        dao.delete(entidadParaBorrar);
    }    
}