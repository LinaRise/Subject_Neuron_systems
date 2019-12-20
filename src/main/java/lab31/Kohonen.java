package lab31;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Kohonen {
  static double learningRate = 2;
  static double learningRateDelta = 0.05;
  static final Data dataNull = new Data(0, 0, 0, 0, 0);
  static final Weight weightNull = new Weight(0, 0, 0, 0, 0);
  Data[] output;


  static public Data returnRandomPointFromList(List<Data> dataList) {
    Random rand = new Random();
    return dataList.get(rand.nextInt(dataList.size()));
  }



  static List<Data> getNormalized(List<Data> dataList) {
    double[] minT= new double[5], maxT = new double[5];
//

    for (int j = 0; j < dataList.size(); j++) {

      if (dataList.get(j).getFriends() >= maxT[0]) {

        maxT[0] = dataList.get(j).getFriends();
      }
      if (dataList.get(j).getFollowers() >= maxT[1]) {
        maxT[1] = dataList.get(j).getFollowers();
      }
      if (dataList.get(j).getPhotos() >= maxT[2]) {
        maxT[2] = dataList.get(j).getPhotos();
      }
      if (dataList.get(j).getVideos() >= maxT[3]) {
        maxT[3] = dataList.get(j).getVideos();
      }
      if (dataList.get(j).getPages() >= maxT[4]) {
        maxT[4] = dataList.get(j).getPages();
      }
    }
    minT = Arrays.copyOf(maxT, maxT.length);


    for (int j = 0; j < dataList.size(); j++) {
      if (dataList.get(j).getFriends() <= minT[0]) {
        minT[0] = dataList.get(j).getFriends();
      }
      if (dataList.get(j).getFollowers() <= minT[1]) {
        minT[1] = dataList.get(j).getFollowers();
      }
      if (dataList.get(j).getPhotos() <= minT[2]) {
        minT[2] = dataList.get(j).getPhotos();
      }
      if (dataList.get(j).getVideos() <= minT[3]) {
        minT[3] = dataList.get(j).getVideos();
      }
      if (dataList.get(j).getPages() <= minT[4]) {
        minT[4] = dataList.get(j).getPages();
      }
    }


    for (int i = 0; i < dataList.size(); i++) {
      dataList.get(i).setFriends((dataList.get(i).getFriends() - minT[0]) / (maxT[0] - minT[0]));
      dataList.get(i).setFollowers((dataList.get(i).getFollowers() - minT[1]) / (maxT[1] - minT[1]));
      dataList.get(i).setPhotos((dataList.get(i).getPhotos() - minT[2]) / (maxT[2] - minT[2]));
      dataList.get(i).setVideos((dataList.get(i).getVideos() - minT[3]) / (maxT[3] - minT[3]));
      dataList.get(i).setPages((dataList.get(i).getPages() - minT[4]) / (maxT[4] - minT[4]));

    }
    return dataList;


  }

  static Weight[][] initializeWeight(int clustersNumber) {
    Weight[][] wih = new Weight[clustersNumber][5];
    for (int i = 0; i < wih.length; i++) {
      for (int j = 0; j < wih[i].length; j++) {
        wih[i][j] = weightNull;
      }
    }

    for (int i = 0; i < wih.length; i++) {
      for (int j = 0; j < wih[i].length; j++) {
        wih[i][j].setFriends(Math.random() * 0.3 + 0.1);
        wih[i][j].setFollowers(Math.random() * 0.3 + 0.1);
        wih[i][j].setPhotos(Math.random() * 0.3 + 0.1);
        wih[i][j].setVideos(Math.random() * 0.3 + 0.1);
        wih[i][j].setPages(Math.random() * 0.3 + 0.1);

      }

    }
    for (int i = 0; i < wih.length; i++) {
      for (int j = 0; j < wih[i].length; j++) {
        System.out.println(wih[i][j]+"\t");
      }
      System.out.println();
    }
//    List<Weight> weightList = new ArrayList<>();
//    for (int i = 0; i < clustersNumber; i++)
//      weightList.add(weightNull);
//
//    for (int i = 0; i < weightList.size(); i++) {
//      weightList.get(i).setFriends(Math.random() * 0.3 + 0.1);
//      weightList.get(i).setFollowers(Math.random() * 0.3 + 0.1);
//      weightList.get(i).setPhotos(Math.random() * 0.3 + 0.1);
//      weightList.get(i).setVideos(Math.random() * 0.3 + 0.1);
//      weightList.get(i).setPages(Math.random() * 0.3 + 0.1);
//    }
    return wih;
  }

  static public double getDistance(Data centroid, Weight element) {
    return Math.sqrt(Math.pow((centroid.getFriends() - element.getFriends()), 2) +
            Math.pow((centroid.getFollowers() - element.getFollowers()), 2) +
            Math.pow((centroid.getPhotos() - element.getPhotos()), 2) +
            Math.pow((centroid.getVideos() - element.getVideos()), 2) +
            Math.pow((centroid.getPages() - element.getPages()), 2));
  }

  static Weight subtractionData(Data data, Weight weight) {
    return new Weight(data.getFriends() - weight.getFriends(), data.getFollowers() - weight.getFollowers(),
            data.getPhotos() - weight.getPhotos(), data.getVideos() - weight.getVideos(), data.getPages() - weight.getPages());

  }

  static Weight summData(Weight weight1, Weight weight) {
    return new Weight(weight1.getFriends() + weight.getFriends(), weight1.getFollowers() + weight.getFollowers(),
            weight1.getPhotos() + weight.getPhotos(), weight1.getVideos() + weight.getVideos(), weight1.getPages() + weight.getPages());

  }

  static Weight multiplicationData(Weight weight, double number) {
    return new Weight(weight.getFriends() * number, weight.getFollowers() * number,
            weight.getPhotos() * number, weight.getVideos() * number, weight.getPages() * number);

  }


  static Weight[] calculateClustersWeightCentre(Weight[][] wih, int clustersNumber) {

    Weight[] clustersCentre = new Weight[clustersNumber];
    for (int i = 0; i < wih.length; i++) {
      double friends = 0;
      double followers = 0;
      double photos = 0;
      double videos = 0;
      double pages = 0;
      for (int j = 0; j < wih[i].length; j++) {
        friends += wih[i][j].getFriends();
        followers += wih[i][j].getFriends();
        photos += wih[i][j].getPhotos();
        videos += wih[i][j].getVideos();
        pages += wih[i][j].getPages();
      }
      clustersCentre[i] = new Weight(friends, followers, photos, videos, pages);
    }
    return clustersCentre;

  }

  static void getKohonenMap(List<Data> dataList, int clustersNumber) {
    Weight[][] wih = initializeWeight(clustersNumber);
    Weight[] weightList = calculateClustersWeightCentre(wih, clustersNumber);//центр кластеров
    int[] cluster = new int[dataList.size()];

    while (learningRate > 0) {
      int clustersBelonging = -1;
      for (int i = 0; i < dataList.size(); i++) {
        double minDistance = 100000000;
        for (int j = 0; j < weightList.length; j++) {
          double distance = getDistance(dataList.get(i), weightList[j]);
          if (distance < minDistance) {
            minDistance = distance;
            clustersBelonging = j;
          }
        }
        //корректировка весовых коэффициентов
        for (int k = 0; k < wih[clustersBelonging].length; k++) {
          Weight weight = subtractionData(dataList.get(i), wih[clustersBelonging][k]);
          wih[clustersBelonging][k] = summData(wih[clustersBelonging][k], multiplicationData(weight, learningRate));
        }

      }
      learningRate -= learningRateDelta;
    }

    int clustersBelonging = -1;

    for (int i = 0; i < dataList.size(); i++) {
      double minDistance = 100000000;
      for (int j = 0; j < weightList.length; j++) {
        double distance = getDistance(dataList.get(i), weightList[j]);
        if (distance < minDistance) {
          minDistance = distance;
          clustersBelonging = j;
        }
        cluster[i] = clustersBelonging;
      }

    }
    double errorCount = 0;
    for (int j = 0; j < dataList.size(); j++) {
//      System.out.println(cluster[j]+" "+dataList.get(j).getClusterBelonging());
      if (cluster[j] != dataList.get(j).getClusterBelonging()) errorCount++;
    }
    double percentage = errorCount / dataList.size() * 100;
    System.out.println("Процент ошибок = " + percentage + "%");


  }
}

