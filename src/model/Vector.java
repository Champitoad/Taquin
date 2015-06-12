package model;

public class Vector
{
    public int x, y;

    public Vector()
    {
        this(0, 0);
    }

    public Vector(int i)
    {
        x = i;
        y = i;
    }

    public Vector(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
}
