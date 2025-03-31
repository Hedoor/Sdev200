//class Excersise11_01
public class Excersise11_01 {
    public static void main(String[] args) {
        Triangle triangle = new Triangle(1, 1.5, 1);
        triangle.setColor("yellow");
        triangle.setFilled(true);

        System.out.println(triangle);
        System.out.println("The area is " + triangle.getArea());
        System.out.println("The perimeter is "
                + triangle.getPerimeter());
        System.out.println(triangle);
    }
}
//definition of class "GeometricObject"
class GeometricObject
{
    /* declare the required variables and initialize it */
    private String color = "white";
    private boolean filled;
    private java.util.Date dateCreated;
    //definition of default constructor
    public GeometricObject()
    {
        //create an object
        dateCreated = new java.util.Date();
    }
    //definition of constructor
    public GeometricObject(String color, boolean filled)
    {
        //create an object
        dateCreated = new java.util.Date();
        //set the value
        this.color = color;
        this.filled = filled;
    }
    //definition of accessor
    public String getColor()
    {
        //return the color
        return color;
    }
    //definition of mutator
    public void setColor (String color)
    {
        //set the color
        this.color = color;
    }
    //definition of the "isFilled" method
    public boolean isFilled()
    {
        //return the value
        return filled;
    }
    //definition of the "setFilled" method
    public void setFilled(boolean filled)
    {
        //set the value
        this.filled = filled;
    }
    //definition of the "getDateCreated" method
    public java.util.Date getDateCreated()
    {
        //return the value
        return dateCreated;
    }
    //definition of the "toString" method
    public String toString()
    {
        //return the value
        return "created on " + dateCreated + "\ncolor: " + color + " and filled: " + filled;
    }
}
//definition of derived class "Triangle"
class Triangle extends GeometricObject
{
    /* declare the required variables and initialize it */
    private double side1 = 1.0, side2 = 1.0, side3 = 1.0;
    /*definition of Constructor */
    public Triangle()
    {
    }
    /* definition of Constructor */
    public Triangle(double side1, double side2, double side3)
    {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }
    //definition of accessor
    public double getSide1()
    {
        //return the value
        return side1;
    }
    //definition of accessor
    public double getSide2()
    {
        //return the value
        return side2;
    }
    //definition of accessor
    public double getSide3()
    {
        //return the value
        return side3;
    }
    /*override method of "getArea" in GeometricObject */
    public double getArea()
    {
        //declare and calculate the value
        double s = (side1 + side2 + side3) / 2;
        //return the value
        return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
    }
    /*override method of "getPerimeter" in GeometricObject */
    public double getPerimeter()
    {
        //return the value
        return side1 + side2 + side3;
    }
    //definition of "toString" method
    public String toString()
    {
        // return the three sides
        return "Triangle: side1 = " + side1 + " side2 = " + side2 +" side3 = " + side3;
    }
}
