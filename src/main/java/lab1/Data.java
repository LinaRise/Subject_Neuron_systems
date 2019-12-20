package lab1;

public  class Data {
  int time;
  double closeSum;
  double trendCloseSum;
  int groupNumber;

  public Data(int time, double close) {
    this.time = time;
    this.closeSum = close;
  }

  @Override
  public String toString() {
    return time + "," + closeSum + ", " + trendCloseSum;
  }
}
