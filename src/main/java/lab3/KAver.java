package lab3;

import java.util.List;
import java.util.Random;

public class KAver {
  static boolean flag; //дальше нет сымсла делать тк центры уже не меняются


  //евкидово расстояние между центром кластера и точкой
  static public double getDistance(Data centroid, Data element) {
    double distance = Math.sqrt(Math.pow((centroid.getFriends() - element.getFriends()), 2) +
            Math.pow((centroid.getFollowers() - element.getFollowers()), 2) +
            Math.pow((centroid.getPhotos() - element.getPhotos()), 2) +
            Math.pow((centroid.getVideos() - element.getVideos()), 2) +
            Math.pow((centroid.getPages() - element.getPages()), 2));
    return distance;
  }

  //сумма точек по кластерам
  static public Data getSumCentroid(Data centroid, Data element) {
    return new Data(centroid.getFriends() + element.getFriends(), centroid.getFollowers() + element.getFollowers(), centroid.getPhotos() +
            element.getPhotos(), centroid.getVideos() + element.getVideos(), centroid.getPages() + element.getPages());
  }

  //делим суммирванные точки на их кол-во в кластере
  static public Data getDividedCentroid(Data centroid, int divisor) {
    return new Data(
            centroid.getFriends() / divisor, centroid.getFollowers() / divisor, centroid.getPhotos() / divisor, centroid.getVideos() / divisor, centroid.getPages() / divisor);
  }

  //рандомная инициализация точек для центра в начале
  static public Data returnRandomPointFromList(List<Data> dataList) {
    Random rand = new Random();
    return dataList.get(rand.nextInt(dataList.size()));
  }

  public static Data[][] getCentroid(List<Data> dataList, Data centroid[][], int clustersNum) {
    Data m1, m2;
    int n = 0;
    boolean flag; //дальше нет сымсла делать тк центры уже не меняются
    Data sum1, sum2;//сумма массивов
    centroid[0] = centroid[1];//запомнили тек для сравнения
    Data dataNull = new Data(0,0,0,0,0);
    for(int i=0;i<centroid[1].length;i++){
      centroid[1][i] = dataNull; //занулили тек
    }
//    centroid[1] = new Data[]{dataNull,dataNull};
//    int a = dataList.get(0); //предыдущее состояние центра масс
//    int b = dataList.get(1);

    //здесь поменять для 3 кластеров
    m1 = centroid[0][0];
    m2 = centroid[0][1];

    Data cluster1[], cluster2[];
    do {
      sum1 = dataNull;
      sum2 = dataNull;
      cluster1 = new Data[dataList.size()];
      cluster2 = new Data[dataList.size()];
      n++;
      int k = 0, j = 0;
      for (int i = 0; i < dataList.size(); i++) {
        ///здесь поменять для 3 кластеров
        if (getDistance(m1,dataList.get(i)) <= getDistance(m2,dataList.get(i))) {
          cluster1[k] = dataList.get(i);
          k++;
        } else {
          cluster2[j] = dataList.get(i);
          j++;
        }
      }

      for (int i = 0; i < k; i++) {
        sum1 = getSumCentroid(sum1,cluster1[i]);
      }

      for (int i = 0; i < j; i++) {
        sum2 = getSumCentroid(sum2,cluster2[i]);
      }
      ////////////////////////////////////
      centroid[1][0] = m1;
      centroid[1][1] = m2;
      if(k!=0)
      m1 = getDividedCentroid(sum1,k);

      if(j!=0)
      m2 =getDividedCentroid(sum2,j);

      flag = (m1 == centroid[1][0] && m2 == centroid[1][1]);
      System.out.println();
      System.out.println("---------------------------------");
      System.out.println("m1=" + m1 + "   m2=" + m2);
      System.out.println("после итерации " + n + "cluster 1\n");
      for (int i = 0; i < cluster1.length; i++) {
        System.out.print(cluster1[i] + "\t");
      }
      System.out.println("\n");
      System.out.println("после итерации " + n + "cluster 2\n");
      for ( int i = 0; i < cluster2.length; i++) {
        System.out.print(cluster2[i] + "\t");
      }


    } while (!flag);

    System.out.println();
    System.out.println("----------------------------------------");
    System.out.println("Конечное значение центроидлов m1="+m1+"  m2="+m2);
    System.out.println();
    System.out.println("Конечный кластер 1\n");
    for (int i = 0; i < cluster1.length; i++) {
      System.out.print(cluster1[i] + "\t");
    }
    System.out.println();
    System.out.println("Конечный кластер 2\n");
    for (int i = 0; i < cluster2.length; i++) {
      System.out.print(cluster2[i] + "\t");
    }
return centroid;

  }


}
