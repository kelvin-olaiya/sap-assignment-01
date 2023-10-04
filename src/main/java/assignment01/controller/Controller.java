package assignment01.controller;


import assignment01.model.Model;

public class Controller implements UserInputObserver {
	
	private Model model;

	public Controller(Model model){
		this.model = model;
	}
	
	public void notifyNewUpdateRequested() {
		log("New update requested by the user");
		model.update();
	}

	private void log(String msg) {
		System.out.println("[Controller] " + msg);
	}
}
