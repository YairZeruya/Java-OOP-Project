package id206914392_id315043117.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Set<T> implements Serializable{
	private ArrayList<T> values;

	public Set() {
		this.values = values;
		values = new ArrayList<T>();
	}
	
	public boolean add(T value) {
		if(values.contains(value)) {
			return false;
		}
		values.add(value);
		return true;
	}
	
	
	public ArrayList<T> getValues() {
		return values;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < values.size(); i++) {
			if (values.get(i) != null) {
				sb.append("        ").append(i + 1).append(")");
				sb.append(values.get(i).toString());
			}
		}
		return sb.toString();
	}
}
