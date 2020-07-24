package state;


/**
 * Readed state class
 * @author Yehonatan Hen
 * @author Rotem Librati
 */
public class ReadedState implements ReportReadedState {

    @Override
    public boolean isReaded() {
        return true;
    }
}
