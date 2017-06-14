package genericSerDeser.strategy;

import java.util.List;

public interface SerStrategy {
	
	void doSerialize(List<Object> firstSecondClassOb);

}
