package in.co.movie.review.bean;

public class MoviesCategoryBean extends BaseBean{

	private String categoryName;
	private long movieId;
	

	
	

	public long getMovieId() {
		return movieId;
	}

	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public String getKey() {
		return id+"";
	}
	public String getValue() {
		return categoryName;
	}

}
