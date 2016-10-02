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

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.interfaces.ISeccionDAO;
import model.location.Planta;
import model.location.Seccion;

@Service ("sSeccion")
@Transactional
public class SSeccion {

    @Autowired
    private ISeccionDAO dao;


    public SSeccion() {
        super();
    }
    public void create(final Seccion entity) {
        dao.create(entity);
    }
    public Seccion findOne(final Short id) {
        return dao.findOne(id);
    }
    public List<Seccion> findAll() {
        return dao.findAll();
    }
    public void update(final Seccion entity) {
        dao.update(entity);
    }
    public void delete(final Seccion entity) {
    	Seccion entidadParaBorrar = findOne(entity.getId());
        dao.delete(entidadParaBorrar);
    }
    //Métodos para realizar búsquedas concretas:
    public List<Seccion> findByPlanta(Planta x) {
    	CriteriaBuilder cB = dao.getCriteriaBuilder();
    	CriteriaQuery<Seccion> cQ = cB.createQuery(Seccion.class);

    	Root<Seccion> root = cQ.from(Seccion.class);
    	cQ.select(root);
    	
    	Predicate p = cB.equal(root.get("planta"), x);
    	cQ.where(p);
    	
    	return dao.findByCriteria(cQ);
    }
}