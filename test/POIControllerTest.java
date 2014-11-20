import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.Status.NOT_FOUND;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.callAction;
import static play.test.Helpers.charset;
import static play.test.Helpers.contentType;
import static play.test.Helpers.status;

import org.junit.Test;

import play.mvc.Result;
import play.test.WithApplication;


public class POIControllerTest extends WithApplication {
	@Test
	public void callQueryAll() {
	    Result result = callAction(
	      controllers.routes.ref.POIController.queryAll()
	    );
	    assertThat(status(result)).isEqualTo(OK);
	    assertThat(contentType(result)).isEqualTo("application/json");
	    assertThat(charset(result)).isEqualTo("utf-8");
	}
	
	@Test
	public void callQueryById() {
	    Result result = callAction(
	      controllers.routes.ref.POIController.queryById(1231241)
	    );
	    assertThat(status(result)).isEqualTo(NOT_FOUND);
	}
	
	@Test
	public void callQueryNeighborhood() {
	    Result result = callAction(
	      controllers.routes.ref.POIController.queryNeighborhood(0, 0, 2.0)
	    );
	    assertThat(status(result)).isEqualTo(OK);
	}
}