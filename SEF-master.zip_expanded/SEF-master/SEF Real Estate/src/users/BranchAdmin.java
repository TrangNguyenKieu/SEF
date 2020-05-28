package users;

import java.io.Serializable;

public class BranchAdmin extends Employee implements Serializable{

	public BranchAdmin(String name,boolean isFulltime) {
		super(name, isFulltime);
	}
}
