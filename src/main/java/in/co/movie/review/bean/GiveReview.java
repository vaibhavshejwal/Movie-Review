package in.co.movie.review.bean;


public class GiveReview extends BaseBean{

	private String comment;
	private String category;
	private String movieName;
	private String actorName;
	private String image;
	private long movieNameId;
	private long actorNameId;
	private long userId;
    private long star;
	private String status;
	private String userName;
    
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getStar() {
		return star;
	}

	public void setStar(long star) {
		this.star = star;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getMovieNameId() {
		return movieNameId;
	}

	public void setMovieNameId(long movieNameId) {
		this.movieNameId = movieNameId;
	}

	public long getActorNameId() {
		return actorNameId;
	}

	public void setActorNameId(long actorNameId) {
		this.actorNameId = actorNameId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getActorName() {
		return actorName;
	}

	public void setActorName(String actorName) {
		this.actorName = actorName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public String getKey() {
		return null;
	}

	@Override
	public String getValue() {
		return null;
	}

}
