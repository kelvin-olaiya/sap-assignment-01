package assignment01.model;

import assignment01.view.ModelObserver;

public interface ModelObserverSource extends Model {

	void addObserver(ModelObserver obs);

}
