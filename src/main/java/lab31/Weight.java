package lab31;

public class Weight {
  double friends;
  double followers;
  double photos;
  double videos;
  double pages;

  public Weight(double friends, double followers, double photos, double videos, double pages) {
    this.friends = friends;
    this.followers = followers;
    this.photos = photos;
    this.videos = videos;
    this.pages = pages;
  }

  public double getFriends() {
    return friends;
  }

  public void setFriends(double friends) {
    this.friends = friends;
  }

  public double getFollowers() {
    return followers;
  }

  public void setFollowers(double followers) {
    this.followers = followers;
  }

  public double getPhotos() {
    return photos;
  }

  public void setPhotos(double photos) {
    this.photos = photos;
  }

  public double getVideos() {
    return videos;
  }

  public void setVideos(double videos) {
    this.videos = videos;
  }

  public double getPages() {
    return pages;
  }

  public void setPages(double pages) {
    this.pages = pages;
  }

  @Override
  public String toString() {
    return "Weight{" +
            "friends=" + friends +
            ", followers=" + followers +
            ", photos=" + photos +
            ", videos=" + videos +
            ", pages=" + pages +
            '}';
  }
}
