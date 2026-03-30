class InternalRequest extends Request {
    int weight;

    InternalRequest(int floor, int weight) {
        super(floor);
        this.weight = weight;
    }
}
