import java.util.List;

interface ElevatorSelectionStrategy {
    ElevatorCar selectElevator(List<ElevatorCar> elevators, ExternalRequest request);
}
