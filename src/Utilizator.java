
public class Utilizator {
	private String username;
	private String password;
	private int id;
	private int nivelDeAcces;
	private boolean administrator;
	private boolean superAdministrator;
	public Utilizator(String username, String password, int id, int nivelDeAcces, boolean administrator,
			boolean superAdministrator) {
		super();
		this.setUsername(username);
		this.setPassword(password);
		this.setId(id);
		this.setNivelDeAcces(nivelDeAcces);
		this.setAdministrator(administrator);
		this.setSuperAdministrator(superAdministrator);
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNivelDeAcces() {
		return nivelDeAcces;
	}
	public void setNivelDeAcces(int nivelDeAcces) {
		this.nivelDeAcces = nivelDeAcces;
	}
	public boolean isAdministrator() {
		return administrator;
	}
	public void setAdministrator(boolean administrator) {
		this.administrator = administrator;
	}
	public boolean isSuperAdministrator() {
		return superAdministrator;
	}
	public void setSuperAdministrator(boolean superAdministrator) {
		this.superAdministrator = superAdministrator;
	}
	

}
