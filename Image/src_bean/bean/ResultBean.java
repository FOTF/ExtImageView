package bean;

public class ResultBean {
	private int id;
	private String name;
	private double credit;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	@Override
	public String toString() {
		return "ResultBean [id=" + id + ", name=" + name + ", credit=" + credit
				+ "]";
	}
}
