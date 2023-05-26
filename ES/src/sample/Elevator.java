package sample;
import java.util.TreeSet;

class  Elevator {

    private static Elevator elevator1 ;
    private static Elevator elevator2 ;
    private static Elevator elevator3 ;

    private TreeSet requestSet = new TreeSet();

    private int currentFloor = 0;

    Controller controller;
    private  String name = "";
    private Direction direction = Direction.UP;

    private Elevator(String name , Controller controller ) {
        this.name = name;
        this.controller = controller;
    };

    private Thread requestProcessorThread;

    static Elevator getInstance(String name , Controller controller ) {
        if(name == "elevator1") {
            if (elevator1 == null)
                elevator1 = new Elevator("elevator1" , controller );
            else if (controller != null)
                elevator1.controller = controller;
            return elevator1;
        }
        else  if(name == "elevator2") {
            if (elevator2 == null)
                elevator2 = new Elevator("elevator2" ,  controller);
            else if(controller != null)
                elevator2.controller = controller;

            return elevator2;
        }
        else{
            if (elevator3 == null)
                elevator3 = new Elevator("elevator3" ,  controller);
            else if(controller!=null)
                elevator3.controller = controller;
            return elevator3;
        }
    }


    /**
     * Add request to Set
     *
     *
     */
    public synchronized void addFloor(int f) {
        this.requestSet.add(f);

        if(this.requestProcessorThread.getState() == Thread.State.WAITING){
            // Notify processor thread that a new request has come if it is waiting
            notify();
        }else{
            // Interrupt Processor thread to check if new request should be processed before current request or not.
            this.requestProcessorThread.interrupt();
        }

    }

    /**
     * @return next request to process based on elevator current floor and direction
     */
    public synchronized int nextFloor() {

        Integer floor  = null;

        if (this.direction == Direction.UP) {
            if (this.requestSet.ceiling(this.currentFloor) != null) {

                floor = (Integer) this.requestSet.ceiling(this.currentFloor);
            } else {
                floor = (Integer) this.requestSet.floor(this.currentFloor);
            }
        } else {
            if (this.requestSet.floor(this.currentFloor) != null) {
                floor = (Integer) this.requestSet.floor(this.currentFloor);
            } else {
                floor = (Integer) this.requestSet.ceiling(this.currentFloor);
            }
        }
        if (floor == null) {
            try {
                System.out.println(this.name+" : waiting in " + this.getCurrentFloor());
                if(this.getCurrentFloor() == 15)
                    this.setDirection(Direction.DOWN);
                if(this.getCurrentFloor() == 0)
                    this.setDirection(Direction.UP);

                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {

            this.requestSet.remove(floor);
        }
        return (floor == null) ? -1 : floor;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    /**
     * Set current floor and direction based on requested floor
     *
     * @param currentFloor
     * @throws InterruptedException
     */
    public void setCurrentFloor(int currentFloor) throws InterruptedException {
        if (this.currentFloor > currentFloor) {
            this.setDirection(Direction.DOWN);
        } else {
            this.setDirection(Direction.UP);
        }
        this.currentFloor = currentFloor;

        System.out.println(this.getName() + " : " + currentFloor);


        Thread.sleep(3000);
    }

    public Direction getDirection() {
        return this.direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Thread getRequestProcessorThread() {
        return this.requestProcessorThread;
    }

    public void setRequestProcessorThread(Thread requestProcessorThread) {
        this.requestProcessorThread = requestProcessorThread;
    }
    public String getName(){
        return this.name;
    }
    public TreeSet getRequestSet() {
        return this.requestSet;
    }

    public void setRequestSet(TreeSet requestSet) {
        this.requestSet = requestSet;
    }

}