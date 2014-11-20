import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;

import java.util.List;

import models.POI;

import org.junit.Before;
import org.junit.Test;

import play.test.WithApplication;
import services.POIService;

public class POIServiceTest extends WithApplication {
	private POIService service;

	@Before
	public void setUp() {
		fakeApplication(inMemoryDatabase());
		startPlay();
		this.service = new POIService();
	}

	@Test
	public void createAndRetrieve() {
		// Creating
		POI subway = new POI("Subway", 5, 10);
		// Saving
		service.save(subway);
		// Retrieving
		service.queryById(subway.getId());
		// Asserting
		assertThat(subway).isNotNull();

		assertThat(subway.getId()).isNotNull();
		assertThat(subway.getName()).isEqualTo("Subway");
	}

	@Test
	public void createMultipleAndRetrieveAll() {
		// Creating
		POI subway = new POI("Subway", 1, 2);
		POI leroy = new POI("Leroy", 5, 10);
		POI carrefour = new POI("Carrefour", 1, 0);
		// Saving
		service.save(subway);
		service.save(leroy);
		service.save(carrefour);
		// Retrieving
		List<POI> list = service.queryAll();
		// Asserting
		assertThat(list).isNotNull();
		assertThat(list).hasSize(3);
		assertThat(list).contains(subway, leroy, carrefour);
	}

	@Test
	public void retrieveNeighborhood() {
		// Creating
		POI subway = new POI("Subway", 1, 1);
		POI leroy = new POI("Leroy", 2, 2);
		POI carrefour = new POI("Carrefour", 3, 3);
		// Saving
		service.save(subway);
		service.save(leroy);
		service.save(carrefour);
		
		List<POI> neighborhood = service.queryNeighborhood(0, 0, 1.0);
		assertThat(neighborhood).isNotNull();
		assertThat(neighborhood).isEmpty();

		neighborhood = service.queryNeighborhood(0, 0, 2.0);
		assertThat(neighborhood).isNotNull();
		assertThat(neighborhood).hasSize(1);
		assertThat(neighborhood).containsExactly(subway);
		
		neighborhood = service.queryNeighborhood(0, 0, 3.0);
		assertThat(neighborhood).isNotNull();
		assertThat(neighborhood).hasSize(2);
		assertThat(neighborhood).containsExactly(subway,leroy);
		
		neighborhood = service.queryNeighborhood(0, 0, 4.0);
		assertThat(neighborhood).isNotNull();
		assertThat(neighborhood).hasSize(2);
		assertThat(neighborhood).containsExactly(subway,leroy);

		neighborhood = service.queryNeighborhood(0, 0, 5.0);
		assertThat(neighborhood).isNotNull();
		assertThat(neighborhood).hasSize(3);
		assertThat(neighborhood).containsExactly(subway,leroy,carrefour);
	}
}