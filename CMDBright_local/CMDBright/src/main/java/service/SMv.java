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

import dao.interfaces.IMvDAO;
import model.equip.Mv;

@Service ("sMv")
@Transactional
public class SMv {

    @Autowired
    private IMvDAO dao;


    public SMv() {
        super();
    }
    public void create(final Mv entity) {
        dao.create(entity);
    }
    public Mv findOne(final Short id) {
        return dao.findOne(id);
    }
    public List<Mv> findAll() {
        return dao.findAll();
    }
    public void update(final Mv entity) {
        dao.update(entity);
    }
    public void delete(final Mv entity) {
    	Mv entidadParaBorrar = findOne(entity.getId());
        dao.delete(entidadParaBorrar);
    }
}