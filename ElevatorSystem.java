public class ElevatorSystem {
    public static void main(String[] args) {

        ElevatorController controller =
                new ElevatorController(new SmartElevatorStrategy());

        controller.addElevator(new ElevatorCar(1, 0, 700));
        controller.addElevator(new ElevatorCar(2, 5, 1000));
        controller.addElevator(new ElevatorCar(3, 10, 500));

        controller.requestElevator(3, Direction.UP);
        controller.requestElevator(8, Direction.DOWN);

        controller.runStep();

        controller.selectFloor(1, 7, 60);
        controller.selectFloor(2, 1, 80);

        controller.runStep();
        controller.runStep();
        controller.runStep();
    }
}
