package chapter3;
import java.io.Serializable;

public class Bean implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String name;

	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}