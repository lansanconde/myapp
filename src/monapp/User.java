package monapp;

import java.io.Serializable;



public class User  implements Serializable{

	private static final long serialVersionUID = 1L;

    private String email;
    private String password;
    private String admin;
 
    
    public String getAdmin() {
		return admin;
	}


	public void setAdmin(String admin) {
		this.admin = admin;
	}


	public User() {
		super();
	}
   
   
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}