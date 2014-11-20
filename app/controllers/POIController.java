package controllers;

import org.springframework.beans.factory.annotation.Autowired;

import models.POI;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.POIService;

/**
 * 
 * @author mauricio
 *
 */

@org.springframework.stereotype.Controller
public class POIController extends Controller {
	
	@Autowired
	private POIService service;
	
	public Result queryAll() {
		return ok(Json.toJson(service.queryAll()));
	}

	public Result queryById(Long id) {
		POI poi = service.queryById(id);
		if(poi != null){
			return ok(Json.toJson(poi));
		}else{
			return notFound();
		}
	}

	public Result queryNeighborhood(Integer x, Integer y, Double distance) {
		return ok(Json.toJson(service.queryNeighborhood(x, y, distance)));
	}

	public Result save() {
		Form<POI> poiForm = Form.form(POI.class).bindFromRequest();
	
		if(poiForm.hasErrors() || poiForm.hasGlobalErrors()) {
		    return badRequest();
		}else{
			POI poi = poiForm.get();
			service.save(poi);
			return ok();
		}
	}
}
