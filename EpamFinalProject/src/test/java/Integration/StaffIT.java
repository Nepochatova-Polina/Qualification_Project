package Integration;

import com.example.epamfinalproject.Database.Implementations.ShipImplementation;
import com.example.epamfinalproject.Database.Implementations.StaffImplementation;
import com.example.epamfinalproject.Entities.Ship;
import com.example.epamfinalproject.Entities.Staff;
import com.example.epamfinalproject.Services.ShipService;
import com.example.epamfinalproject.Services.StaffService;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;


@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StaffIT {

    private StaffService staffService;
    private ShipService shipService;

    @BeforeAll
    void beforeAll() {
        postgresContainer.start();

        staffService = new StaffService(new StaffImplementation());
        shipService = new ShipService(new ShipImplementation());
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
                                                                    Ports.Binding.bindPort(1988),
                                                                    new ExposedPort(5432)))))
                    .withDatabaseName("postgres")
                    .withUsername("user")
                    .withPassword("password")
                    .withInitScript("init_script.sql");

    @Test
    void registerStaff(){
        shipService.registerShip(new Ship("Name",200));
        Staff staff = new Staff(1,"Ivan","Ivanov",1);
        staffService.registerStaff(staff);
        assertThat(staffService.getStaffByShipID(1).get(0)).isEqualToComparingFieldByFieldRecursively(staff);
        assertThat(staffService.getStaffByShipID(2)).isEmpty();
    }
    @AfterAll
    static void close() {
        postgresContainer.close();
    }
}
