package state;


/**
 * Implementation of report readed context= true/false changed respectively if reports read or not
 * @author Yehonatan Hen
 * @author Rotem Librati
 */
public class ReportReadedContext {
    private ReportReadedState state;
    public  ReportReadedContext(){
        state=new ReadedState();
    }
    public void setState(ReportReadedState state){
        this.state=state;
    }

    public ReportReadedState getState() {
        return state;
    }
}
