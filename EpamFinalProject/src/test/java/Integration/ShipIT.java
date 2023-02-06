package Integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.epamfinalproject.Database.ConnectionPool;
import com.example.epamfinalproject.Database.Implementations.ShipImplementation;
import com.example.epamfinalproject.Entities.Ship;
import com.example.epamfinalproject.Services.ShipService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ShipIT {

  private ShipService shipService;

  @BeforeAll
  void beforeAll() {
    postgresContainer.start();

    ConnectionPool.setHostPort(postgresContainer.getHost(), postgresContainer.getFirstMappedPort());

    shipService = new ShipService(new ShipImplementation());
  }

  @Container
  private static final PostgreSQLContainer<?> postgresContainer =
      new PostgreSQLContainer<>("postgres:11.1")
          .withExposedPorts(5432)
              .withDatabaseName("postgres")
          .withUsername("user")
          .withPassword("password")
          .withInitScript("init_script.sql");

  @Test
  void registerShipTest() {
    Ship currentShip = new Ship(1, "Name", 12);
    shipService.registerShip(currentShip);
    Ship ship = shipService.getShipByName("Name");
    assertTrue(new ReflectionEquals(currentShip, "id","staff").matches(ship));
  }

  @Test
  void updateShipByIDTest() {
    Ship currentShip = new Ship(2, "Ship", 25);
    shipService.registerShip(new Ship("Ship", 10));
    shipService.updateShipByID(currentShip, shipService.getShipByName("Ship").getId());
    Ship ship = shipService.getShipByName("Ship");
    assertTrue(new ReflectionEquals(currentShip, "id","staff").matches(ship));
  }

  @ParameterizedTest
  @MethodSource("shipStream")
  void getAllShipsTest(List<Ship> shipList) {
    for (Ship ship : shipList) {
      shipService.registerShip(ship);
    }
    List<Ship> allShips = shipService.getAllShips();
    assertEquals(shipList.size() + 1, allShips.size());
  }

  @AfterAll
  static void close() {
    postgresContainer.close();
  }

  Stream<Arguments> shipStream() {
    List<Ship> shipList = new ArrayList<>();
    shipList.add(new Ship(1, "Donna", 100));
    shipList.add(new Ship(2, "Eola", 200));
    shipList.add(new Ship(3, "Elisabeth", 300));
    shipList.add(new Ship(4, "Elena", 400));
    return Stream.of(Arguments.of(shipList));
  }
}
