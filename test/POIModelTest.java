import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;

import java.util.List;

import models.POI;

import org.junit.Before;
import org.junit.Test;

import play.test.WithApplication;

import com.avaje.ebean.Ebean;

public class POIModelTest extends WithApplication {
	
	@Before
	public void setUp() {
		fakeApplication(inMemoryDatabase());
		startPlay();
	}
	
	@Test
    public void createAndRetrieve() {
		//Creating and Saving
        new POI("Subway", 5,10).save();
        //Retrieving
        POI subway = Ebean.find(POI.class).where().eq("name", "Subway").findUnique();
        //Asserting
        assertThat(subway).isNotNull();;
        assertThat(subway.getId()).isNotNull();
        assertThat(subway.getName()).isEqualTo("Subway");
    }
	
	@Test
    public void createMultipleAndRetrieveAll() {
		//Creating
		POI subway = new POI("Subway", 1,2);
        POI leroy = new POI("Leroy", 5,10);
        POI carrefour = new POI("Carrefour", 1,0);
		//Saving
		subway.save();
        leroy.save();
        carrefour.save();
        //Retrieving
        List<POI> list = Ebean.find(POI.class).findList();
        //Asserting
        assertThat(list).isNotNull();
        assertThat(list).hasSize(3);
        assertThat(list).contains(subway,leroy,carrefour);
    }
	
	@Test
    public void createAndRemove() {
		//Creating
		POI subway = new POI("Subway", 1,2);
		//Saving
		subway.save();
        //Retrieving
        POI result = Ebean.find(POI.class).where().eq("id", subway.getId()).findUnique();
        //Asserting
        assertThat(result).isNotNull();
        //Removing
        Ebean.delete(subway);
        //Retrieving
        result = Ebean.find(POI.class).where().eq("id", subway.getId()).findUnique();
        //Asserting
        assertThat(result).isNull();
    }
}