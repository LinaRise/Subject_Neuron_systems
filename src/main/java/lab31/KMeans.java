package lab31;


import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class KMeans {
  static final Data dataNull = new Data(0, 0, 0, 0, 0);


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


  static Data[][] calculateClustersBelonging(int clustersNumber, List<Data> dataList, Data centroid[][]) {
//    Data[][] finalClusters = new Data[clustersNumber][dataList.size()];
    int[] cluster = new int[dataList.size()];
    int[] initialCluster = new int[dataList.size()];
    int clusterPointCount[] = new int[clustersNumber];
    Data[] prev = new Data[clustersNumber];

//    centroid[0] = centroid[1];
//    for(int i=0;i<centroid[1].length;i++){
//      centroid[1][i] = dataNull;
//    }
//    centroid[1] = new Data[]{dataNull, dataNull};
    System.out.println("========== Starting to get new centroid =========");

    //массив для хранения сумм
//    Data[] sum = new Data[clustersNumber];
    int iterationCount = 0;
    boolean flag;

    do {
//     for(int i=0;i<prev.length;i++){
//       prev[i] = centroid[1][i];
//     }
      centroid[0] = Arrays.copyOf(centroid[1],centroid[1].length);
//      System.arraycopy(centroid[1],0,centroid[0],0,centroid[1].length);

//      centroid[0] = centroid[1];
      for (int i = 0; i <centroid[0].length ; i++) {
        System.out.print(centroid[0][i]+"\t");

      }

      iterationCount++;
      //каждый раз занулаем сумму
      for (int i = 0; i < centroid[1].length; i++) {
        centroid[1][i] = dataNull;
        clusterPointCount[i] = 0;
      }
      System.out.println();

//      for (int i = 0; i <centroid[0].length ; i++) {
//        System.out.print(centroid[0][i]+"\t");
//
//      }
      for (int i = 0; i <centroid[0].length ; i++) {
        System.out.print(centroid[0][i]+"\t");

      }
      System.out.println();

      int clustersBelonging = -1;
      for (int i = 0; i < dataList.size(); i++) {
        double minDistance = 100000000;
        for (int j = 0; j < clustersNumber; j++) {
          double distance = getDistance(centroid[0][j], dataList.get(i));
          System.out.println(centroid[0][j]+"-"+dataList.get(i)+"="+distance);
          System.out.println(distance);
          if (distance < minDistance) {
            minDistance = distance;
            clustersBelonging = j;
          }
          cluster[i] = clustersBelonging;
          initialCluster[i] = dataList.get(i).getClusterBelonging();
          System.out.println("Минимальная дистанция между: " + j + " точкой и центроидом: " + clustersBelonging + " и равна = " + minDistance);
          System.out.println("точка: " + dataList.get(i) + " принадлежит кластеру: " + cluster[i]);
          centroid[1][clustersBelonging] = getSumCentroid(centroid[1][clustersBelonging], dataList.get(i));
          clusterPointCount[clustersBelonging]++;
        }
      }

      for (int i = 0; i < clustersNumber; i++) {
        if (clusterPointCount[i] != 0)
          centroid[1][i] = getDividedCentroid(centroid[1][i], clusterPointCount[i]);
      }

      flag = true;
      for (int j = 0; j < clustersNumber; j++) {
        //предыдущие центроиды сравниваем
        if (flag && centroid[0][j] == centroid[1][j]) {
          flag = true;
          continue;
        }
        flag = false;
      }


    } while (flag);


    System.out.println("======================================== ");
    System.out.println(" Final Cluster is ");
    for (int i = 0; i < clustersNumber; i++) {
      System.out.print("C" + (i + 1) + ":");
      for (int j = 0; j < dataList.size(); j++) {
        if (cluster[j] == i) {
          System.out.print(dataList.get(j) + " ,");
        }

      }
      System.out.println();
    }

    double errorCount = 0;
    for (int j = 0; j < dataList.size(); j++) {
//      System.out.println(cluster[j]+" "+dataList.get(j).getClusterBelonging());
      if (cluster[j] != dataList.get(j).getClusterBelonging()) errorCount++;
    }
    System.out.println(errorCount);
    System.out.println(dataList.size());
    double percentage = errorCount/dataList.size()*100;
    System.out.println("Процент ошибок = "+percentage+"%");

    return centroid;

  }


}


