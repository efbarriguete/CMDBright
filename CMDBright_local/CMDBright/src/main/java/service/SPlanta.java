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

import dao.interfaces.IPlantaDAO;
import model.location.Centro;
import model.location.Planta;

@Service ("sPlanta")
@Transactional
public class SPlanta {

    @Autowired
    private IPlantaDAO dao;


    public SPlanta() {
        super();
    }
    public void create(final Planta entity) {
        dao.create(entity);
    }
    public Planta findOne(final Short id) {
        return dao.findOne(id);
    }
    public List<Planta> findAll() {
        return dao.findAll();
    }
    public void update(final Planta entity) {
        dao.update(entity);
    }
    public void delete(final Planta entity) {
    	Planta entidadParaBorrar = findOne(entity.getId());
        dao.delete(entidadParaBorrar);
    }
    //métodos para realizar la búsqueda por criteria:
/*    public CriteriaBuilder getCriteriaBuilder() { //este método debería quitarlo
    	return dao.getCriteriaBuilder();
    }
    public List<Planta> findByCriteria(CriteriaQuery<Planta> c) { //y este también
    	return dao.findByCriteria(c);
    }*/
    //Así las búsquedas las haré sólo desde cada servicio y serán concretas:
    public List<Planta> findByCentro(Centro c) {
    	CriteriaBuilder cB = dao.getCriteriaBuilder();
    	
    	CriteriaQuery<Planta> cQ = cB.createQuery(Planta.class);
    	Root<Planta> root = cQ.from(Planta.class);
    	cQ.select(root);
    	
    	Predicate p = cB.equal(root.get("centro"), c);
    	cQ.where(p);
    	
    	return dao.findByCriteria(cQ);
    }
}