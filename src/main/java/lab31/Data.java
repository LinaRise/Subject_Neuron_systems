package lab31;

public  class Data {
  int id;
  double friends;
  double followers;
  double photos;
  double videos;
  double pages;
  int clusterBelonging;

  public Data(int id, double friends, double followers, double photos, double videos, double pages, int clusterBelonging) {
    this.id = id;
    this.friends = friends;
    this.followers = followers;
    this.photos = photos;
    this.videos = videos;
    this.pages = pages;
    this.clusterBelonging = clusterBelonging;
  }

  public Data(double friends, double followers, double photos, double videos, double pages) {
    this.friends = friends;
    this.followers = followers;
    this.photos = photos;
    this.videos = videos;
    this.pages = pages;
  }

  public Data(double friends, double followers, double photos, double videos, double pages, int clusterBelonging) {
    this.friends = friends;
    this.followers = followers;
    this.photos = photos;
    this.videos = videos;
    this.pages = pages;
    this.clusterBelonging = clusterBelonging;
  }

  public Data() {
  }

  @Override
  public String toString() {
    return "Data{" +
            "id=" + id +
            ", friends=" + friends +
            ", followers=" + followers +
            ", photos=" + photos +
            ", videos=" + videos +
            ", pages=" + pages +
            ", clusterBelonging=" + clusterBelonging +
            '}';
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

  public int getClusterBelonging() {
    return clusterBelonging;
  }

  public void setClusterBelonging(int clusterBelonging) {
    this.clusterBelonging = clusterBelonging;
  }

  //  public Data(int id, int friends, int followers, int photos, int videos, int pages) {
//    this.id = id;
//    this.friends = friends;
//    this.followers = followers;
//    this.photos = photos;
//    this.videos = videos;
//    this.pages = pages;
//  }
//
//  public Data() {
//
//  }
//
//  public Data(int id, int friends, int followers, int photos, int videos, int pages, int clusterBelonging) {
//    this.id = id;
//    this.friends = friends;
//    this.followers = followers;
//    this.photos = photos;
//    this.videos = videos;
//    this.pages = pages;
//    this.clusterBelonging = clusterBelonging;
//  }
//
//  public Data(int friends, int followers, int photos, int videos, int pages) {
//    this.friends = friends;
//    this.followers = followers;
//    this.photos = photos;
//    this.videos = videos;
//    this.pages = pages;
//  }
//
//  @Override
//  public String toString() {
//    return "Data{" +
//            "id=" + id +
//            ", friends=" + friends +
//            ", followers=" + followers +
//            ", photos=" + photos +
//            ", videos=" + videos +
//            ", pages=" + pages +
//            ", clusterBelonging=" + clusterBelonging +
//            '}';
//  }
//
//  public int getClusterBelonging() {
//    return clusterBelonging;
//  }
//
//  public void setClusterBelonging(int clusterBelonging) {
//    this.clusterBelonging = clusterBelonging;
//  }
//
//  public int getId() {
//    return id;
//  }
//
//  public void setId(int id) {
//    this.id = id;
//  }
//
//  public int getFriends() {
//    return friends;
//  }
//
//  public void setFriends(int friends) {
//    this.friends = friends;
//  }
//
//  public int getFollowers() {
//    return followers;
//  }
//
//  public void setFollowers(int followers) {
//    this.followers = followers;
//  }
//
//  public int getPhotos() {
//    return photos;
//  }
//
//  public void setPhotos(int photos) {
//    this.photos = photos;
//  }
//
//  public int getVideos() {
//    return videos;
//  }
//
//  public void setVideos(int videos) {
//    this.videos = videos;
//  }
//
//  public int getPages() {
//    return pages;
//  }
//
//  public void setPages(int pages) {
//    this.pages = pages;
//  }
}