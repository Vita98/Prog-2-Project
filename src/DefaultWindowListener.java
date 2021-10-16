import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/**
 * A Normal WindowListener that close the application on windowClosing
 * @author studente
 *
 */
public class DefaultWindowListener extends WindowAdapter {
	
	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		super.windowClosing(arg0);
		System.exit(0);
	}
}
