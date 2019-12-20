package lab2;

public  class Data {
  double time;
  double closeSum;
  double trendCloseSum;
  double groupNumber;

  Data(double time, double close) {
    this.time += time;
    this.closeSum += close;
  }

  @Override
  public String toString() {
    return time + "," + closeSum + ", " + trendCloseSum;
  }
}
