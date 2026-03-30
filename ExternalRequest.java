class ExternalRequest extends Request {
    Direction direction;

    ExternalRequest(int floor, Direction direction) {
        super(floor);
        this.direction = direction;
    }
}
