package edu.csuft.yunyun.spider;

public class Film {
	//���ע���ֶ�
	
	/**
	 * ���
	 */
	int id;
	
	/**
	 * Ƭ��
	 */
	String title;
	
	/*
	 * ��Ϣ
	 */
	String info;
	
	/**
	 * ����
	 */
	double star;

	/**
	 * ����
	 */
	String rating;
	
	/**
	 * ����·��
	 */
	String poster;
	
	/**
	 * ��Ҫ
	 */
	String quote;
	
	public Film() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public double getStar() {
		return star;
	}

	public void setStar(double star) {
		this.star = star;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	@Override
	public String toString() {
		return "Film [id=" + id + ", title=" + title + ", info=" + info + ", star=" + star + ", rating=" + rating
				+ ", poster=" + poster + ", quote=" + quote + "]";
	}

	
}
