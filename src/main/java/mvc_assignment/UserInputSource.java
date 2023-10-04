package mvc_assignment;

import mvc_assignment.controller.UserInputObserver;

public interface UserInputSource {

	void addObserver(UserInputObserver obs);

}
