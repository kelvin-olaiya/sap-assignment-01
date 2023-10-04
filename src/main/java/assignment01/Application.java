package assignment01;

import assignment01.controller.Controller;
import assignment01.model.ModelImpl;
import assignment01.view.InputUI;
import assignment01.view.SwingView;
import assignment01.view.WebView;

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
