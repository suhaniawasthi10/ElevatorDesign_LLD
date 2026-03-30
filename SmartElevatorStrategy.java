import java.util.List;

class SmartElevatorStrategy implements ElevatorSelectionStrategy {

    @Override
    public ElevatorCar selectElevator(List<ElevatorCar> elevators, ExternalRequest request) {

        ElevatorCar bestElevator = null;
        int minScore = Integer.MAX_VALUE;

        for (ElevatorCar elevator : elevators) {

            int score = Integer.MAX_VALUE;

            if (elevator.isIdle()) {
                score = Math.abs(elevator.getCurrentFloor() - request.floor);
            }

            else if (elevator.getDirection() == Direction.UP
                    && request.direction == Direction.UP
                    && elevator.getCurrentFloor() <= request.floor) {
                score = request.floor - elevator.getCurrentFloor();
            }

            else if (elevator.getDirection() == Direction.DOWN
                    && request.direction == Direction.DOWN
                    && elevator.getCurrentFloor() >= request.floor) {
                score = elevator.getCurrentFloor() - request.floor;
            }

            if (score < minScore) {
                minScore = score;
                bestElevator = elevator;
            }
        }

        return bestElevator;
    }
}
