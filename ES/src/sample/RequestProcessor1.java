package sample;
class RequestProcessor1 implements Runnable {
    private boolean isValidFloorNumber(String s) {
        return (s != null) && s.matches("\\d{1,2}") && Integer.parseInt(s) <= 15;
    }
    @Override
    public void run() {
        while (true) {
            Controller controller = new Controller();
            Elevator elevator1 = Elevator.getInstance("elevator1", controller);
            int floor1 = elevator1.nextFloor();
            int currentFloor1 = elevator1.getCurrentFloor();
            try{
                if (floor1 >= 0) {
                    if (currentFloor1 > floor1) {
                        while (currentFloor1 > floor1) {
                            elevator1.setCurrentFloor(--currentFloor1);

                        }
                    } else {
                        while (currentFloor1 < floor1) {
                            elevator1.setCurrentFloor(++currentFloor1);

                        }
                    }
                    System.out.println("elevator 1 arrived: " + elevator1.getCurrentFloor());
                }

            }catch(InterruptedException e){

                if(elevator1.getCurrentFloor() != floor1){
                    elevator1.getRequestSet().add(floor1);
                }
            }





        }
    }
}