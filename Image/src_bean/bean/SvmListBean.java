package bean;

public class SvmListBean {
	private int id;
	private String text;
	private String url;
	private boolean leaf;

	public SvmListBean(String text, boolean leaf) {
		super();
		this.text = text;
		this.leaf = leaf;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "SvmListBean [id=" + id + ", text=" + text + ", url=" + url
				+ ", leaf=" + leaf + "]";
	}

}
