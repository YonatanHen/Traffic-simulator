package state;

/**
 * Not readed state class
 * @author Yehonatan Hen
 * @author Rotem Librati
 */
public class NotReadedState implements  ReportReadedState {
    @Override
    public boolean isReaded() {
        return false;
    }
}
