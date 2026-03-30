import java.util.ArrayList;
import java.util.List;

class ElevatorController {
    private List<ElevatorCar> elevators;
    private ElevatorSelectionStrategy strategy;

    ElevatorController(ElevatorSelectionStrategy strategy) {
        this.strategy = strategy;
        this.elevators = new ArrayList<>();
    }

    public void addElevator(ElevatorCar elevator) {
        elevators.add(elevator);
    }

    public void requestElevator(int floor, Direction direction) {
        ExternalRequest request = new ExternalRequest(floor, direction);
        ElevatorCar selectedElevator = strategy.selectElevator(elevators, request);

        if (selectedElevator == null) {
            System.out.println("No elevator available");
            return;
        }

        System.out.println("\nRequest at floor " + floor + " for " + direction);
        System.out.println("Assigned Elevator: " + selectedElevator.getId());

        selectedElevator.addExternalRequest(request);
    }

    public void selectFloor(int elevatorId, int destinationFloor, int passengerWeight) {
        for (ElevatorCar elevator : elevators) {
            if (elevator.getId() == elevatorId) {
                elevator.addInternalRequest(
                        new InternalRequest(destinationFloor, passengerWeight)
                );
                return;
            }
        }
        System.out.println("Invalid elevator id");
    }

    public void runStep() {
        System.out.println("\n----- SYSTEM STEP -----");
        for (ElevatorCar elevator : elevators) {
            elevator.step();
        }
    }
}
