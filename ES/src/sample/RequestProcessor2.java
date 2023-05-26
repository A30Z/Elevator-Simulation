package sample;
class RequestProcessor2 implements Runnable {
    private boolean isValidFloorNumber(String s) {
        return (s != null) && s.matches("\\d{1,2}") && Integer.parseInt(s) <= 15;
    }
    @Override
    public void run() {
        while (true) {
            Controller controller = new Controller();
            Elevator elevator2 = Elevator.getInstance("elevator2", controller);
            int floor2 = elevator2.nextFloor();
            int currentFloor2 = elevator2.getCurrentFloor();



            //elevator2
            try{
                if (floor2 >= 0) {
                    if (currentFloor2 > floor2) {
                        while (currentFloor2 > floor2) {
                            elevator2.setCurrentFloor(--currentFloor2);

                        }
                    } else {
                        while (currentFloor2 < floor2) {
                            elevator2.setCurrentFloor(++currentFloor2);

                        }
                    }
                    System.out.println("elevator 2 arrived: " + elevator2.getCurrentFloor());
                }

            }catch(InterruptedException e){

                if(elevator2.getCurrentFloor() != floor2){
                    elevator2.getRequestSet().add(floor2);
                }
            }



        }
    }
}