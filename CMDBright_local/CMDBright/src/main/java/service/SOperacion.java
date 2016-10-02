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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.interfaces.IOperacionDAO;
import model.supply.Operacion;

@Service ("sOperacion")
@Transactional
public class SOperacion {

    @Autowired
    private IOperacionDAO dao;


    public SOperacion() {
        super();
    }
    public void create(final Operacion entity) {
        dao.create(entity);
    }
    public Operacion findOne(final Integer id) {
        return dao.findOne(id);
    }
    public List<Operacion> findAll() {
        return dao.findAll();
    }
    public void update(final Operacion entity) {
        dao.update(entity);
    }
    public void delete(final Operacion entity) {
    	Operacion entidadParaBorrar = findOne(entity.getId());
        dao.delete(entidadParaBorrar);
    }
}