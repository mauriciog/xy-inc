package services;

import java.util.ArrayList;
import java.util.List;

import models.POI;

import org.springframework.stereotype.Service;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

/**
 * 
 * @author mauricio
 *
 */
@Service
public class POIService {
	private Model.Finder<Long,POI> repository;
	
	public POIService(){
		this(new Model.Finder<Long,POI>(Long.class, POI.class));
	}
	
	public POIService(Finder<Long, POI> repository) {
		this.repository = repository;
	}

	public List<POI> queryAll(){
		return repository.all();
	}
	
	public POI queryById(Long id){
		return repository.byId(id);
	}
	
	public List<POI> queryNeighborhood(Integer x, Integer y, Double distance){
		List<POI> result = new ArrayList<POI>();
		for(POI poi : this.queryAll()){
			if(Math.pow(x - poi.getX(), 2) + Math.pow(y - poi.getY(), 2) <= Math.pow(distance,2)){
				result.add(poi);
			}
		}
		return result;
	}

	public void save(POI poi) {
		poi.save();
	}
}
