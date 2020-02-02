package presentation.scenes;

import java.util.Observable;

import javafx.scene.layout.Pane;
	/**
	 * View der 2. Seite
	 *
	 */

public abstract class ViewController<T> extends Observable{ //extended Observable fuer Playlistupdate

	protected Pane rootView;
	protected T application;
	
	public ViewController() {
		
	}
	
	public ViewController (T application) {
		this.application = application;
	}
	
	public Pane getRootView() {
		return rootView;
	}
	
	abstract public void initialize();
	
	public void setApplication(T application) {
		this.application=application;
	}
}
