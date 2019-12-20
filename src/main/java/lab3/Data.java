package lab3;

public  class Data {
  int id;
  int friends;
  int followers;
  int photos;
  int videos;
  int pages;


  public Data(int id, int friends, int followers, int photos, int videos, int pages) {
    this.id = id;
    this.friends = friends;
    this.followers = followers;
    this.photos = photos;
    this.videos = videos;
    this.pages = pages;
  }

  public Data() {

  }

  public Data(int friends, int followers, int photos, int videos, int pages) {
    this.friends = friends;
    this.followers = followers;
    this.photos = photos;
    this.videos = videos;
    this.pages = pages;
  }

  @Override
  public String toString() {
    return "Data{" +
            "friends=" + friends +
            ", followers=" + followers +
            ", photos=" + photos +
            ", videos=" + videos +
            ", pages=" + pages +
            '}';
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getFriends() {
    return friends;
  }

  public void setFriends(int friends) {
    this.friends = friends;
  }

  public int getFollowers() {
    return followers;
  }

  public void setFollowers(int followers) {
    this.followers = followers;
  }

  public int getPhotos() {
    return photos;
  }

  public void setPhotos(int photos) {
    this.photos = photos;
  }

  public int getVideos() {
    return videos;
  }

  public void setVideos(int videos) {
    this.videos = videos;
  }

  public int getPages() {
    return pages;
  }

  public void setPages(int pages) {
    this.pages = pages;
  }
}