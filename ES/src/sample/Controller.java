package sample;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;



public class Controller {
    //It is set to public because the elevator needs to update the UI component state during operation
    public AnchorPane root;
    public Button[] insideFloorButtons = new Button[16];
    public Button[] outsideUpButtons = new Button[16];
    public Button[] outsideDownButtons = new Button[16];
    public Elevator[] elevators = new Elevator[4];
    // public Slider[] elevatorSliders = new Slider[4];
    public Label[] eachFloorDisplay = new Label[16];
    public ToggleGroup  chooseEles = new ToggleGroup();
    public RadioButton[] allChooseEle = new RadioButton[4];
    //public Label[] eachElevatorDisplay = new Label[4];
    public Button openButton = new Button();
    public Button alert = new Button();
    public Label alertDisplay = new Label();

    Elevator elevator1 = Elevator.getInstance("elevator1" , this);
    Elevator elevator2 = Elevator.getInstance("elevator2" , this);
    Elevator elevator3 = Elevator.getInstance("elevator3" , this);

    private void initInsideFloorButtons() {

        for (int i = 1; i <= 15; i++) {
            insideFloorButtons[i] = (Button) root.lookup("#floor" + i);
            if (insideFloorButtons[i] != null) {
                final int chosenFloor = i;
                insideFloorButtons[i].setOnAction((event) -> {
                    int elevatorIndex = 0;
                    for (int j = 1; j <= 3; j++) {
                        if (allChooseEle[j].isSelected() == true) {
                            elevatorIndex = j;
                        }
                    }
                    if(elevatorIndex==1)
                        elevator1.addFloor(chosenFloor);
                    else  if(elevatorIndex==2)
                        elevator2.addFloor(chosenFloor);
                    else
                        elevator3.addFloor(chosenFloor);
                    ;
                });
            }
        }
    }
    private void handleInsideOrder(int chosenFloor) {
        int elevatorIndex = 0;
        for (int i = 1; i <= 3; i++) {
            if (allChooseEle[i].isSelected() == true) {
                elevatorIndex = i;
            }
        }
    }
    private void initOutsideOrderButtons() {
        for(int i =1;i <= 15; i++) {

            outsideUpButtons[i] = (Button)root.lookup("#up"+i);
            if(outsideUpButtons[i] != null) {
                final int outsideCurFloor = i;
                outsideUpButtons[i].setOnAction((event) -> {
                    System.out.println(outsideCurFloor);
                });
            }

            outsideDownButtons[i] = (Button)root.lookup("#down"+i);
            if(outsideDownButtons[i] != null) {
                final int floorNumberStr = i;
                outsideDownButtons[i].setOnAction((event) -> {
                    int p1 = 0 , p2 = 0 , p3 = 0;
                    if( (elevator1.getDirection() == Direction.UP && elevator1.getCurrentFloor() < (floorNumberStr) ) || ( elevator1.getDirection() == Direction.DOWN && elevator1.getCurrentFloor() > (floorNumberStr) ) ) {
                        p1 += 50;
                    }
                    if( (elevator2.getDirection() == Direction.UP && elevator2.getCurrentFloor() < (floorNumberStr) ) || (elevator2.getDirection() == Direction.DOWN && elevator2.getCurrentFloor() > (floorNumberStr) ))
                        p2 += 50;
                    if( (elevator3.getDirection() == Direction.UP && elevator3.getCurrentFloor() < (floorNumberStr) ) || (elevator3.getDirection() == Direction.DOWN && elevator3.getCurrentFloor() > (floorNumberStr) ))
                        p3 += 50;
                    p1 -= Math.abs( elevator1.getCurrentFloor() - (floorNumberStr) );
                    p2 -= Math.abs( elevator2.getCurrentFloor() - (floorNumberStr) );
                    p3 -= Math.abs( elevator3.getCurrentFloor() - (floorNumberStr) );

                    if(p1 >= p2 && p1 >= p3) {
                        elevator1.addFloor((floorNumberStr));
                    }
                    else if(p2 >= p1 && p2 >= p3) {
                        elevator2.addFloor((floorNumberStr));
                    }
                    else {
                        elevator3.addFloor((floorNumberStr));
                    }
                });
            }
        }
    }
    public void initialize() {


        for(int i = 1; i <=3;i++) {
            allChooseEle[i]=(RadioButton)root.lookup("#chooseEle"+i);
            allChooseEle[i].setToggleGroup(chooseEles);
        }
        allChooseEle[1].setSelected(true);

        initInsideFloorButtons();
        initOutsideOrderButtons();

        for(int i = 1; i <= 15; i++) {
            eachFloorDisplay[i]= (Label)root.lookup("#display"+i);
            eachFloorDisplay[i].setText("closed");
        }


    }


}
