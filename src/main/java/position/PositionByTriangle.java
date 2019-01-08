package position;

import entity.Point;
import entity.Triangle;
import util.Check;

import java.util.Formatter;

/**
 * 给定一个三角形，以及三角形内一点P到三个顶点的距离，求该点坐标
 *
 * 思路：
 * 列三个圆方程，求解
 * 通过两个圆方程相减获得直线方程
 * 不同的方程相减可以得到两个直线方程
 *
 * Created by liuyuchen on 2018/5/25.
 */
public class PositionByTriangle {
    private PositionByTriangle() {}

    /**
     * 根据三个点和到三点的距离定位
     *
     * @param a     点 a
     * @param b     点 b
     * @param c     点 c
     * @param pA    到点 a 距离
     * @param pB    到点 b 距离
     * @param pC    到点 c 距离
     * @return      Point
     */
    public static Point getPoint(Point a, Point b, Point c, double pA, double pB, double pC) {
        double x;
        double y;

        if(pA == 0) {	//p在A点
            return a;
        }
        if(pB == 0) {	//p在B点
            return b;
        }
        if(pC == 0) {	//p在C点
            return c;
        }

        if ((b.getX() - a.getX()) == 0) {
            y = (pA * pA - pB * pB + b.getY() * b.getY() - a.getY() * a.getY())
                    / (2 * (b.getY() - a.getY()));
            x = (pA*pA - pC*pC + c.getX()*c.getX() - a.getX()*a.getX()
                    - (c.getY() - a.getY())*(2*y - a.getY() - c.getY()))
                    / (2 * (c.getX() - a.getX()));
        } else if ((c.getX() - a.getX()) == 0) {
            y = (pA * pA - pC * pC + c.getY() * c.getY() - a.getY() * a.getY())
                    / (2 * (c.getY() - a.getY()));
            x = (pA*pA - pB*pB + b.getX()*b.getX() - a.getX()*a.getX()
                    - (b.getY() - a.getY())*(2*y - a.getY() - b.getY()))
                    / (2 * (b.getX() - a.getX()));
        } else if ((b.getY() - a.getY()) == 0) {
            x = (pA * pA - pB * pB + b.getX() * b.getX() - a.getX() * a.getX())
                    / (2 * (b.getX() - a.getX()));
            y = (pA*pA - pC*pC + c.getY()*c.getY() - a.getY()*a.getY()
                    - (c.getX() - a.getX())*(2*x - a.getX() - c.getX()))
                    / (2 * (c.getY() - a.getY()));
        } else if ((c.getY() - a.getY()) == 0) {
            x = (pA * pA - pC * pC + c.getX() * c.getX() - a.getX() * a.getX())
                    / (2 * (c.getX() - a.getX()));
            y = (pA*pA - pB*pB + b.getY()*b.getY() - a.getY()*a.getY()
                    - (b.getX() - a.getX())*(2*x - a.getX() - b.getX()))
                    / (2 * (b.getY() - a.getY()));
        } else {
            //四个中间量，方便式子表达
            double m1 = (pA*pA - pB*pB + b.getY()*b.getY() - a.getY()*a.getY())
                    * (c.getY() - a.getY());
            double m2 = (pA*pA - pC*pC + c.getY()*c.getY() - a.getY()*a.getY())
                    * (b.getY() - a.getY());
            double n1 = (c.getY() - a.getY()) * (b.getX() - a.getX());
            double n2 = (b.getY() - a.getY()) * (c.getX() - a.getX());

            x = (m1 - m2 + n1*(a.getX()+b.getX()) - n2*(a.getX()+c.getX()))
                    / (2 * ( n1 - n2));
            y = (pA*pA - pB*pB + b.getY()*b.getY() - a.getY()*a.getY()
                    - (b.getX() - a.getX())*(2*x - a.getX() - b.getX()))
                    / (2 * (b.getY() - a.getY()));
        }

        if (Check.check(new Triangle(a, b, c), new Point(x, y), pA, pB, pC)) {
            @SuppressWarnings("resource")
            Formatter f = new Formatter(System.out);
            // 格式化输出
            f.format("(Xp, Yp)=(%.2f, %.2f)\n", x, y);

            return new Point(x, y);
        } else {
            System.err.println("Cannot solve the point.");
            return null;
        }
    }

    /**
     * 根据三角形和到其三个定点的距离定位
     *
     * @param tri   三角形
     * @param pA    到点 a 距离
     * @param pB    到点 b 距离
     * @param pC    到点 c 距离
     * @return      Point
     */
    public static Point getPoint(Triangle tri, double pA, double pB, double pC) {
        return getPoint(tri.getA(), tri.getB(), tri.getC(), pA, pB, pC);
    }

    public Point getPoint(double x1, double y1,
                          double x2, double y2,
                          double x3, double y3,
                          double pA, double pB, double pC) {
        Point a = new Point(x1, y1);
        Point b = new Point(x2, y2);
        Point c = new Point(x3, y3);

        return getPoint(a, b, c, pA, pB, pC);
    }

    public static void main(String[] args) {
        PositionByTriangle gpi = new PositionByTriangle();
        Point a = new Point(180, 280);
        Point b = new Point(100, 100);
        Point c = new Point(200, 20);
        double pA = 160.3;
        double pB = 71.3;
        double pC = 104.4;

        Point po = PositionByTriangle.getPoint(a, b, c, pA, pB, pC);
        System.out.println(po);
    }
}
