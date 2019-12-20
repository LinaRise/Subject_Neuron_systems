package lab3;

import java.util.List;
import java.util.Random;

public class KMeans {
  double distance[][];

  public static void main(String[] args) {



  }

  static public double getDistance(Data centroid, Data element) {
    return Math.sqrt(Math.pow((centroid.getFriends() - element.getFriends()), 2) +
            Math.pow((centroid.getFollowers() - element.getFollowers()), 2) +
            Math.pow((centroid.getPhotos() - element.getPhotos()), 2) +
            Math.pow((centroid.getVideos() - element.getVideos()), 2) +
            Math.pow((centroid.getPages() - element.getPages()), 2));
  }

  static public Data getSumCentroid(Data centroid, Data element) {
    return new Data(centroid.getFriends() + element.getFriends(), centroid.getFollowers() + element.getFollowers(), centroid.getPhotos() +
            element.getPhotos(), centroid.getVideos() + element.getVideos(), centroid.getPages() + element.getPages());
  }

  static public Data getDividedCentroid(Data centroid, int divisor) {
    return new Data(centroid.getFriends() / divisor, centroid.getFollowers() / divisor, centroid.getPhotos() / divisor, centroid.getVideos() / divisor, centroid.getPages() / divisor);
  }

  static public Data returnRandomPointFromList(List<Data> dataList) {
    Random rand = new Random();
    return dataList.get(rand.nextInt(dataList.size()));
  }

  public static Data[][] getCentroid(List<Data> dataList, Data centroid[][], int clustersNum) {
    double distance[][] = new double[clustersNum][dataList.size()];//расстояние от центроида до каждой точки
    int cluster[] = new int[dataList.size()];//записываем занчение кластера для кадой точки
    int clusterPointCount[] = new int[clustersNum];

    centroid[0] = centroid[1];
    Data dataNull = new Data(0,0,0,0,0);
    centroid[1] = new Data[]{dataNull,dataNull};
    System.out.println("========== Starting to get new centroid =========");

    for (int i = 0; i < clustersNum; i++) {
      for (int j = 0; j < dataList.size(); j++) {
        //System.out.println(distance[i][j]+"("+i+","+j+")="+data[j]+"("+j+")-"+centroid[0][i]+"="+(data[j]-centroid[0][i]));
        distance[i][j] = getDistance(centroid[0][i], dataList.get(j));//расстояние между всеми точками и центроидами
        System.out.print(distance[i][j] + " ,");
        System.out.println("Centroid: "+centroid[0][i]);
      }
      System.out.println();
    }
    for (int j = 0; j < dataList.size(); j++) {
      int minDistance = 0;
      if (clustersNum==2){
       minDistance = getSmallerDistanceFor2Clusters(j, distance);
        System.out.println(minDistance);
      }
      if (clustersNum==3){
        minDistance = getSmallerDistanceFor3Clusters(j, distance);
        System.out.println(minDistance);
      }

      centroid[1][minDistance] =  getSumCentroid(centroid[1][minDistance], dataList.get(j));
      clusterPointCount[minDistance] = clusterPointCount[minDistance] + 1;//сичтаем количество точек относячихся к жтому кластеру,
      //для того чтобы потом сделать пресчет
      cluster[j] = minDistance;
    }

    System.out.println("======================================== ");

    System.out.println("Новые кластеры ");
    // cluster[]= { 0  1   0  1  0  2  2  1}
    // data[]={2,4,-10,12,3,20,30,11};
    for (int i = 0; i < clustersNum; i++) {
      System.out.print("C" + (i + 1) + ": ");
      for (int l = 0; l < dataList.size(); l++) {
        if (cluster[l] == i)
          System.out.print(dataList.get(l) + " ,");

      }
      System.out.println();
    }

    System.out.println("======================================== ");

    System.out.println("Новые центроиды ");

    for (int j = 0; j < clustersNum; j++) {
      if(clusterPointCount[j]!=0) {
        centroid[1][j] = getDividedCentroid(centroid[1][j], clusterPointCount[j]);
        System.out.print(centroid[1][j] + ",");
      }
    }
    System.out.println();

    boolean isAchived = true;
    for (int j = 0; j < clustersNum; j++) {
      //предыдущие центроиды сравниваем
      if (isAchived && centroid[0][j] == centroid[1][j]) {
        isAchived = true;
        continue;
      }
      isAchived = false;
    }


    if (!isAchived) {
      getCentroid(dataList, centroid, clustersNum);
    }

    if (isAchived) {
      System.out.println("======================================== ");
      System.out.println(" Final Cluster is ");
      for (int i = 0; i < clustersNum; i++) {
        System.out.print("C" + (i + 1) + ":");
        for (int j = 0; j < dataList.size(); j++) {
          if (cluster[j] == i)
            System.out.print(dataList.get(j) + " ,");

        }
        System.out.println();
      }
    }

    return centroid;

  }


  static int getSmallerDistanceFor3Clusters(int j, double distance[][]) {
    int smallerDistance = 0;
    if (distance[0][j] < distance[1][j] && distance[0][j] < distance[2][j])
      smallerDistance = 0;
    if (distance[1][j] < distance[0][j] && distance[1][j] < distance[2][j])
      smallerDistance = 1;
    if (distance[2][j] < distance[0][j] && distance[2][j] < distance[1][j])
      smallerDistance = 2;//
    return smallerDistance;
  }

  static int getSmallerDistanceFor2Clusters(int j, double distance[][]) {
    int smallerDistance = 0;
    if (distance[0][j] < distance[1][j])
      smallerDistance = 0;
    if (distance[1][j] < distance[0][j])
      smallerDistance = 1;
    return smallerDistance;
  }
}


//  public static int[][] getCentroid(int data[], int clustresNum, int centroid[][]) {
//
//    int distance[][] = new int[clustresNum][data.length];
//    int cluster[] = new int[data.length];
//    double clusterNodeCount[] = new double[clustresNum];
//
//
//    centroid[0] = centroid[1];
//    for (int i = 0; i < clustresNum; i++) {
//
//    }
//    centroid[1] = new int[]{0, 0, 0};
//
//
//    for (int i = 0; i < clustresNum; i++) {
//      for (int j = 0; j < data.length; j++) {
//        //System.out.println(distance[i][j]+"("+i+","+j+")="+data[j]+"("+j+")-"+centroid[0][i]+"="+(data[j]-centroid[0][i]));
//        distance[i][j] = Math.abs(data[j] - centroid[0][i]);
//        System.out.print(distance[i][j] + " ,");
//        //System.out.println("Centroid: "+centroid[0][i]);
//      }
//      System.out.println();
//    }
//
//    for (int j = 0; j < data.length; j++) {
//      int smallerDistance = 0;
//      if (distance[0][j] < distance[1][j] && distance[0][j] < distance[2][j])
//        smallerDistance = 0;
//      if (distance[1][j] < distance[0][j] && distance[1][j] < distance[2][j])
//        smallerDistance = 1;
//      if (distance[2][j] < distance[0][j] && distance[2][j] < distance[1][j])
//        smallerDistance = 2;//
//
//      centroid[1][smallerDistance] = centroid[1][smallerDistance] + data[j];
//      clusterNodeCount[smallerDistance] = clusterNodeCount[smallerDistance] + 1;
//      cluster[j] = smallerDistance;
//
//      //System.out.println("Centerid at 1:  "+centroid[1][smallerDistance]);
//      //System.out.print(cluster[j]+", ");
//
//    }
//    //for(int j=0;j<data.length;j++)
//    //System.out.println("c at out: "+cluster[j]);
//
//    System.out.println("======================================== ");
//
//    System.out.println("New clusters are ");
//    // cluster[]= { 0  1   0  1  0  2  2  1}
//    // data[]={2,4,-10,12,3,20,30,11};
//    for (int i = 0; i < clustresNum; i++) {
//      System.out.print("C" + (i + 1) + ": ");
//      for (int l = 0; l < data.length; l++) {
//        if (cluster[l] == i)
//          System.out.print(data[l] + " ,");
//
//      }
//      System.out.println();
//    }
//    System.out.println("======================================== ");
//
//    System.out.println("New centroid is ");
//
//    for (int j = 0; j < clustresNum; j++) {
//      centroid[1][j] = centroid[1][j] / clusterNodeCount[j];
//      System.out.print(centroid[1][j] + ",");
//    }
//    System.out.println();
//
//    boolean isAchived = true;
//    for (int j = 0; j < clustresNum; j++) {
//      if (isAchived && centroid[0][j] == centroid[1][j]) {
//        isAchived = true;
//        continue;
//      }
//      isAchived = false;
//    }
//
//    if (!isAchived) {
//
//      getCentroid(data, clustresNum, centroid);
//    }
//
//    if (isAchived) {
//      System.out.println("======================================== ");
//      System.out.println(" Final Cluster is ");
//      for (int i = 0; i < clustresNum; i++) {
//        System.out.print("C" + (i + 1) + ":");
//        for (int j = 0; j < data.length; j++) {
//          if (cluster[j] == i)
//            System.out.print(data[j] + " ,");
//
//        }
//        System.out.println();
//      }
//    }
//
//    return centroid;
//
//  }
//}