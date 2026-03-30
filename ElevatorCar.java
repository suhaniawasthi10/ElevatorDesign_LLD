import java.util.*;

class ElevatorCar {
    private int id;
    private int currentFloor;
    private Direction direction;
    private int maxWeight;
    private int currentWeight;

    private TreeSet<Integer> upStops;
    private TreeSet<Integer> downStops;

    ElevatorCar(int id, int currentFloor, int maxWeight) {
        this.id = id;
        this.currentFloor = currentFloor;
        this.maxWeight = maxWeight;
        this.currentWeight = 0;
        this.direction = Direction.IDLE;

        upStops = new TreeSet<>();
        downStops = new TreeSet<>(Collections.reverseOrder());
    }

    public int getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public boolean isIdle() {
        return direction == Direction.IDLE;
    }

    public void addExternalRequest(ExternalRequest request) {
        if (request.floor > currentFloor) {
            upStops.add(request.floor);
        } else if (request.floor < currentFloor) {
            downStops.add(request.floor);
        } else {
            System.out.println("Elevator " + id + " already at floor " + currentFloor);
        }
    }

    public void addInternalRequest(InternalRequest request) {
        if (currentWeight + request.weight > maxWeight) {
            System.out.println("Elevator " + id + " overloaded!");
            return;
        }

        currentWeight += request.weight;

        if (request.floor > currentFloor) {
            upStops.add(request.floor);
        } else if (request.floor < currentFloor) {
            downStops.add(request.floor);
        } else {
            System.out.println("Already at destination floor");
        }
    }

    public boolean hasRequests() {
        return !upStops.isEmpty() || !downStops.isEmpty();
    }

    public void step() {
        if (!upStops.isEmpty()) {
            direction = Direction.UP;
            int nextFloor = upStops.pollFirst();
            moveTo(nextFloor);

        } else if (!downStops.isEmpty()) {
            direction = Direction.DOWN;
            int nextFloor = downStops.pollFirst();
            moveTo(nextFloor);

        } else {
            direction = Direction.IDLE;
            System.out.println("Elevator " + id + " is idle at floor " + currentFloor);
        }
    }

    private void moveTo(int floor) {
        System.out.println("Elevator " + id + " moving from " + currentFloor + " to " + floor);
        currentFloor = floor;
        System.out.println("Elevator " + id + " reached floor " + currentFloor);
        currentWeight = 0;

        if (!hasRequests()) {
            direction = Direction.IDLE;
        }
    }
}
