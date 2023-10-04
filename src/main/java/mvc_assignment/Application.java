package mvc_assignment;

import mvc_assignment.controller.Controller;
import mvc_assignment.model.ModelImpl;
import mvc_assignment.view.InputUI;
import mvc_assignment.view.SwingView;
import mvc_assignment.view.WebView;

public class Application {
  static public void main(String[] args) throws Exception {
	  
	ModelImpl model = new ModelImpl();
    SwingView view = new SwingView(model);
	WebView webView = new WebView(model);
    InputUI inputUI = new InputUI();
	Controller controller = new Controller(model);
	inputUI.addObserver(controller);
	view.display();
	inputUI.display();
  }	
  
}
