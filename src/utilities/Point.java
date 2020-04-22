package utilities;

public abstract class Point implements Utilities {
    final int minVal=0;//Minimal values of coordinates X and Y
    final int maxX=800;//Maximal value of X
    final int maxY=600;//Maximal value of Y
    //x and y are coordinates of some point on the map in cartesian axis system
    int x;
    int y;

    Point(double x,double y){
        //TODO:check if we need to import x and y from double to int
        if(checkValue(x,minVal,maxX)) this.x=(int)x;
        else {
           double wrongVal=x;
           x=getRandomDouble(minVal,maxX);
           correctingMessage(wrongVal,x,"x");
        }
        if(checkValue(y,minVal,maxY)) this.y=(int)y;
        else {
            double wrongVal=y;
            x=getRandomDouble(minVal,maxY);
            correctingMessage(wrongVal,y,"y");
        }
        successMessage(this.toString());
    }
}
