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

import dao.interfaces.IIpDAO;
import model.equip.Ip;
import model.equip.Rango;

@Service ("sIp")
@Transactional
public class SIp {

    @Autowired
    private IIpDAO dao;


    public SIp() {
        super();
    }
    public void create(final Ip entity) {
        dao.create(entity);
    }
    public Ip findOne(final Short id) {
        return dao.findOne(id);
    }
    public List<Ip> findAll() {
        return dao.findAll();
    }
    public void update(final Ip entity) {
        dao.update(entity);
    }
    public void delete(final Ip entity) {
    	Ip entidadParaBorrar = findOne(entity.getId());
        dao.delete(entidadParaBorrar);
    }
    public List<Ip> findByRango(Rango r) {
    	CriteriaBuilder cB = dao.getCriteriaBuilder();
    	
    	CriteriaQuery<Ip> cQ = cB.createQuery(Ip.class);
    	Root<Ip> root = cQ.from(Ip.class);
    	cQ.select(root);
    	
    	Predicate p = cB.equal(root.get("rango"), r);
    	cQ.where(p);
    	
    	return dao.findByCriteria(cQ);
    }

}