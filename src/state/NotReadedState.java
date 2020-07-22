package state;

/**
 * Not readed state class
 * @author Yehonatan Hen-207630112
 * @author Rotem Librati-307903732
 */
public class NotReadedState implements  ReportReadedState {
    @Override
    public boolean isReaded() {
        return false;
    }
}
