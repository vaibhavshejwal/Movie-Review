package in.co.movie.review.bean;

import java.util.Date;

public class MoviesBean extends BaseBean{

	private String movieName;
	private String ActorName;
	private Date Realeasedate;
	private String image;
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	private String category;
	private Long categoryId;
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getActorName() {
		return ActorName;
	}
	public void setActorName(String actorName) {
		ActorName = actorName;
	}
	public Date getRealeasedate() {
		return Realeasedate;
	}
	public void setRealeasedate(Date realeasedate) {
		Realeasedate = realeasedate;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getKey() {
		return id+"";
	}
	public String getValue() {
		return category+" "+categoryId;
	}

}
