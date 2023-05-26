package sample;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class RequestListener implements Runnable {
    private boolean isValidFloorNumber(String s) {
        return (s != null) && s.matches("\\d{1,2}") && Integer.parseInt(s) <= 15;
    }
    @Override
    public void run() {

        while (true) {
            String floorNumberStr = null;
            try {
                // Read input from console
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                floorNumberStr = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (isValidFloorNumber(floorNumberStr)) {
                int p1 = 0 , p2 = 0 , p3 = 0;
                System.out.println("User Pressed : " + floorNumberStr);
                Controller controller = new Controller();
                Elevator elevator1 = Elevator.getInstance("elevator1", controller);
                Elevator elevator2 = Elevator.getInstance("elevator2", controller);
                Elevator elevator3 = Elevator.getInstance("elevator3", controller);



                if( (elevator1.getDirection() == Direction.UP && elevator1.getCurrentFloor() < Integer.parseInt(floorNumberStr) ) || ( elevator1.getDirection() == Direction.DOWN && elevator1.getCurrentFloor() > Integer.parseInt(floorNumberStr) ) ) {
                    p1 += 50;
                }
                if( (elevator2.getDirection() == Direction.UP && elevator2.getCurrentFloor() < Integer.parseInt(floorNumberStr) ) || (elevator2.getDirection() == Direction.DOWN && elevator2.getCurrentFloor() > Integer.parseInt(floorNumberStr) ))
                    p2 += 50;
                if( (elevator3.getDirection() == Direction.UP && elevator3.getCurrentFloor() < Integer.parseInt(floorNumberStr) ) || (elevator3.getDirection() == Direction.DOWN && elevator3.getCurrentFloor() > Integer.parseInt(floorNumberStr) ))
                    p3 += 50;
                p1 -= Math.abs( elevator1.getCurrentFloor() - Integer.parseInt(floorNumberStr) );
                p2 -= Math.abs( elevator2.getCurrentFloor() - Integer.parseInt(floorNumberStr) );
                p3 -= Math.abs( elevator3.getCurrentFloor() - Integer.parseInt(floorNumberStr) );

                if(p1 >= p2 && p1 >= p3) {
                    elevator1.addFloor(Integer.parseInt(floorNumberStr));
                }
                else if(p2 >= p1 && p2 >= p3) {
                    elevator2.addFloor(Integer.parseInt(floorNumberStr));
                }
                else {
                    elevator3.addFloor(Integer.parseInt(floorNumberStr));
                }
            } else {
                System.out.println("Floor Request Invalid : " + floorNumberStr);
            }
        }

    }
}