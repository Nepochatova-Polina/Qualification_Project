package Integration;

import com.example.epamfinalproject.Database.Implementations.CruiseImplementation;
import com.example.epamfinalproject.Database.Implementations.RouteImplementation;
import com.example.epamfinalproject.Entities.Cruise;
import com.example.epamfinalproject.Entities.Route;
import com.example.epamfinalproject.Entities.Ship;
import com.example.epamfinalproject.Services.CruiseService;
import com.example.epamfinalproject.Services.RouteService;
import com.example.epamfinalproject.Utility.QueryBuilder;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CruiseIT {
    private CruiseService cruiseService;
    private static Cruise cruise;

    @BeforeAll
    void beforeAll() {
        postgresContainer.start();
        cruiseService = new CruiseService(new CruiseImplementation());
        cruise =
                new Cruise(
                        1,
                        null,
                        new Ship(1, "Name", 10),
                        new Route(1, "Dep", "Dest", 10),
                        0,
                        false,
                        false,
                        LocalDate.parse("2022-12-12"),
                        LocalDate.parse("2022-12-12"));
    }
@BeforeEach
void beforeEach(){
        cruise.setDeleted(false);
        cruise.setConfirmed(false);
}
    @Container
    private static final PostgreSQLContainer<?> postgresContainer =
            new PostgreSQLContainer<>("postgres:11.1")
                    .withExposedPorts(5432)
                    .withCreateContainerCmdModifier(
                            cmd ->
                                    cmd.withHostConfig(
                                            new HostConfig()
                                                    .withPortBindings(
                                                            new PortBinding(
                                                                    Ports.Binding.bindPort(1988), new ExposedPort(5432)))))
                    .withDatabaseName("postgres")
                    .withUsername("user")
                    .withPassword("password")
                    .withInitScript("init_script.sql");

    @Test
    void createCruiseTest(){
        cruiseService.createCruise(cruise);
        assertThat(cruiseService.getCruiseByID(1)).isEqualToComparingFieldByFieldRecursively(cruise);

    }
    @Test
    void confirmCruiseTest(){
        cruiseService.createCruise(cruise);
        cruiseService.confirmCruiseByID(1);
        cruise.setConfirmed(true);
        assertThat(cruiseService.getCruiseByID(1))
                .usingComparatorForFields((x, y) -> 0, "deleted")
                .isEqualToComparingFieldByFieldRecursively(cruise);
    }
    @Test
    void updateCruiseTest(){
        cruise.setDeleted(false);
        cruiseService.createCruise(cruise);
        cruise.setConfirmed(false);
        cruise.setPrice(200);
        cruiseService.updateCruiseByID(cruise,1);
        assertThat(cruiseService.getCruiseByID(1))
                .usingComparatorForFields((x, y) -> 0, "deleted")
                .isEqualToComparingFieldByFieldRecursively(cruise);    }
    @Test
    void deleteCruiseTest(){
        cruiseService.createCruise(cruise);
        cruiseService.deleteCruiseByID(1);
        assertTrue(cruiseService.getCruiseByID(1).isDeleted());
    }
}
