package chickencode;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;

import javax.swing.JPanel;

public class ContentPanel extends JPanel implements MouseListener, MouseMotionListener {

	private HashMap<String, ResolutionPanel> registered_panels = new HashMap<String, ResolutionPanel>();
	private HashMap<String, MouseListener> registered_mouse_listener = new HashMap<String, MouseListener>();
	private HashMap<String, MouseMotionListener> registered_mouse_motion_listener = new HashMap<String, MouseMotionListener>();
	private ResolutionPanel active;
	private boolean scale_up = true;
	private Dimension max_size;
	private int width, height, xminus = 0, yminus = 0;

	public void setScale_up(boolean scale_up) {
		this.scale_up = scale_up;
	}

	public boolean isScale_up() {
		return scale_up;
	}

	public void registerResolutionPanel(ResolutionPanel panel) {
		registered_panels.put(panel.getName(), panel);
	}

	public void setActive(String name) {
		active = registered_panels.get(name);
		max_size = null;
	}

	private MouseEvent transformMouseEvent(MouseEvent e) {

		if (scale_up && max_size != null) {
			width = getWidth();
			height = getHeight();
		} else {
			width = max_size.width;
			height = max_size.height;
			xminus = (getWidth() - max_size.width) / 2;
			yminus = (getHeight() - max_size.height) / 2;
		}

		int newx = (int) (active.getWidth() / ((float) (width) / (e.getX() - xminus)));
		int newy = (int) (active.getHeight() / ((float) (height) / (e.getY() - yminus)));

		if (newx < 0 || newx > active.getWidth() || newy < 0 || newy > active.getHeight())
			return null;

		return new MouseEvent((Component) e.getSource(), e.getID(), e.getWhen(), e.getModifiers(), newx, newy, 0, 0,
				e.getClickCount(), e.isPopupTrigger(), e.getButton());
	}

	public void recalculateSize() {
		int ggt = MathTools.ggt(active.getWidth(), active.getHeight());
		int format_width = active.getWidth() / ggt;
		int format_height = active.getHeight() / ggt;

		if (getWidth() > getHeight()) {
			for (int i = getHeight(); i > 0; i--) {
				if ((i % format_height) == 0) {
					int z = i / format_height;
					if (format_width * z <= getWidth()) {
						max_size = new Dimension(format_width * z, format_height * z);
						break;
					}
				}
			}
		}
	}

	@Override
	protected void paintComponent(Graphics g) {

		if (max_size == null)
			recalculateSize();

		if (active != null) {
			if (scale_up) {
				g.drawImage(active.getImage(), 0, 0, getWidth(), getHeight(), null);
			} else {
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, getWidth(), getHeight());
				g.drawImage(active.getImage(), (getWidth() - max_size.width) / 2, (getHeight() - max_size.height) / 2,
						max_size.width, max_size.height, null);
			}
		}
	}

	public void registerMouseListener(String name, MouseListener listener) {
		if (registered_mouse_listener.size() == 0)
			addMouseListener(this);
		registered_mouse_listener.put(name, listener);
	}

	public void registerMouseMotionListener(String name, MouseMotionListener listener) {
		if (registered_mouse_motion_listener.size() == 0)
			addMouseMotionListener(this);
		registered_mouse_motion_listener.put(name, listener);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		MouseEvent transformed = transformMouseEvent(e);
		if (transformed != null && active != null && registered_mouse_listener.containsKey(active.getName()))
			registered_mouse_listener.get(active.getName()).mouseClicked(transformed);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		MouseEvent transformed = transformMouseEvent(e);
		if (transformed != null && active != null && registered_mouse_listener.containsKey(active.getName()))
			registered_mouse_listener.get(active.getName()).mouseEntered(transformed);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		MouseEvent transformed = transformMouseEvent(e);
		if (transformed != null && active != null && registered_mouse_listener.containsKey(active.getName()))
			registered_mouse_listener.get(active.getName()).mouseExited(transformed);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		MouseEvent transformed = transformMouseEvent(e);
		if (transformed != null && active != null && registered_mouse_listener.containsKey(active.getName()))
			registered_mouse_listener.get(active.getName()).mousePressed(transformed);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		MouseEvent transformed = transformMouseEvent(e);
		if (transformed != null && active != null && registered_mouse_listener.containsKey(active.getName()))
			registered_mouse_listener.get(active.getName()).mouseReleased(transformed);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		MouseEvent transformed = transformMouseEvent(e);
		if (transformed != null && active != null && registered_mouse_motion_listener.containsKey(active.getName()))
			registered_mouse_motion_listener.get(active.getName()).mouseDragged(transformed);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		MouseEvent transformed = transformMouseEvent(e);
		if (transformed != null && active != null && registered_mouse_motion_listener.containsKey(active.getName()))
			registered_mouse_motion_listener.get(active.getName()).mouseMoved(transformed);
	}

}
