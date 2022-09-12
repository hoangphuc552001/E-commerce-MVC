package nn.estore.jpa.entity;

import java.io.Serializable;

public interface ReportItem {
	Serializable getGroup();
	int getCount();
	double getValue();
	double getMin();
	double getMax();
	double getAvg();
}
