package mvc_assignment.model;

import mvc_assignment.view.ModelObserver;

public interface ModelObserverSource extends Model {

	void addObserver(ModelObserver obs);

}
