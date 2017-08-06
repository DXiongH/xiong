package Demo.jdbc;

import java.io.Serializable;

public class UserBean implements Serializable {
	 private static final long serialVersionUID = 5548279324472937805L;
	String id;
	String name;
	public UserBean(){
        
    }
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "id="+id+", name="+name;
	}
	
	
}
