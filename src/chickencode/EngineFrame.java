package chickencode;

import javax.swing.JFrame;

public class EngineFrame extends JFrame {

	private ContentPanel content_panel = new ContentPanel();

	public EngineFrame() {
		setContentPane(content_panel);
	}

	public ContentPanel getContentPanel() {
		return content_panel;
	}

}
