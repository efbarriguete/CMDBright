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

package prueba;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import model.catalog.Fabricante;
import model.catalog.Model;
import model.catalog.TipoModel;
import service.SFabricante;
import service.SModel;


public class PruebaApp {
	public static void crearFabricante(ApplicationContext c, String nombre) {
		SFabricante xxxServicio= (SFabricante) c.getBean("sFabricante");
		Fabricante xxx = (Fabricante) c.getBean("fabricante");
				
		xxx.setNombre(nombre);
		System.out.println(xxx.getNombre());

		System.out.println(xxxServicio.getClass().toString());
	//	return(xxx);
		xxxServicio.create(xxx);
	}

	public static TipoModel crearTipoModel(ApplicationContext c, String nombre) {
		//STipoModel xxxServicio= (STipoModel) c.getBean("sTipoModel");
		TipoModel xxx = (TipoModel) c.getBean("tipoModel");
				
		xxx.setNombre(nombre);
		System.out.println(xxx.getNombre());
		return xxx;
		//xxxServicio.create(xxx);
	}

	public static void crearModel(ApplicationContext c) {
		SModel mServicio= (SModel) c.getBean("sModel");
		Model m = (Model) c.getBean("model");
/*		STipoModel tmServicio= (STipoModel) c.getBean("sTipoModel");
		TipoModel tm = (TipoModel) c.getBean("TipoModel");
		SFabricante fServicio= (SFabricante) c.getBean("sFabricante");
		Fabricante f = (Fabricante) c.getBean("fabricante");

		//Recupero dos objetos:
		tm = tmServicio.findOne(1L);
		f = fServicio.findOne(1L);
*/
		//Fabricante f = crearFabricante(c,"HP");
		TipoModel tm = crearTipoModel(c,"Ordenador");

		//Completo el resto de datos para esta instancia de Model:
		m.setNombre("One111");
		m.setPartNum("1111111111");
		m.setEnrackable(false);
		//System.out.println(f.getNombre()+"  "+f.getId());
		//m.setFabricante(f);
		//System.out.println(tm.getNombre()+"  "+tm.getId());
		m.setTipoModel(tm);
		//muestro objeto y persisto:		
		//System.out.println(m.toString());
		mServicio.create(m);	
	}

	public static void crearAtributoModel(ApplicationContext c) {
/*		SModel mServicio= (SModel) c.getBean("sModel");
		Model m = (Model) c.getBean("model");
		SAtributoModel aServicio= (SAtributoModel) c.getBean("sAtributoModel");
		AtributoModel a = (AtributoModel) c.getBean("atributoModel");
*/		
		
	/*	
		m = mServicio.findOne(3);
		
		a.setNombre("atributo1");
		a.setValor("valor1");
		a.setModel(m);

		m.addAtributoModel(a);
		
		mServicio.create(m);*/
	}

	public static void actualizarModel(ApplicationContext c) {
		SModel mServicio= (SModel) c.getBean("sModel");
		Model m = new Model();
		m = mServicio.findOne(12);
		
		m.setNombre("AAAAAAAAAAAAAAA");
		
		mServicio.update(m);	
	}
	public static void buscarYborrar(ApplicationContext c) {
		SModel mServicio= (SModel) c.getBean("sModel");
		Model m = new Model();
		m = mServicio.findOne(12);
		
		mServicio.delete(m);	
	}
	
	
	
	
	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		
		//crearFabricante(context,"Primax");
		//crearTipoModel(context,"Ordenador");
		//crearModel(context);
		//crearAtributoModel(context);
		//actualizarModel(context);
		buscarYborrar(context);
		
		

		
		((ConfigurableApplicationContext) context).close(); 
	}
}

