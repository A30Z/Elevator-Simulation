package sample;
class RequestProcessor3 implements Runnable {
    private boolean isValidFloorNumber(String s) {
        return (s != null) && s.matches("\\d{1,2}") && Integer.parseInt(s) <= 15;
    }
    @Override
    public void run() {
        while (true) {
            Controller controller = new Controller();
            Elevator elevator3 = Elevator.getInstance("elevator3", controller);
            int floor3 = elevator3.nextFloor();
            int currentFloor3 = elevator3.getCurrentFloor();


            //elevator3
            try{
                if (floor3 >= 0) {
                    if (currentFloor3 > floor3) {
                        while (currentFloor3 > floor3) {
                            elevator3.setCurrentFloor(--currentFloor3);

                        }
                    } else {
                        while (currentFloor3 < floor3) {
                            elevator3.setCurrentFloor(++currentFloor3);

                        }
                    }
                    System.out.println("elevator 3 arrived: " + elevator3.getCurrentFloor());
                }

            }catch(InterruptedException e){

                if(elevator3.getCurrentFloor() != floor3){
                    elevator3.getRequestSet().add(floor3);
                }
            }


        }
    }
}