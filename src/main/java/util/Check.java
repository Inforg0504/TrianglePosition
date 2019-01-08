package util;

import entity.Point;
import entity.Triangle;

/**
 * check
 *
 * Created by liuyuchen on 2018/5/25.
 */
public class Check {

    private Check() {
        throw new IllegalStateException("Can't instance the class.");
    }

    /**
     * check 点是否符合要求
     *
     * @param tri   三角形
     * @param p     求得的点
     * @param pA    到点 a 的距离
     * @param pB    到点 b 的距离
     * @param pC    到点 c 的距离
     * @return      boolean
     */
    public static boolean check(Triangle tri, Point p, double pA, double pB, double pC) {
        if (checkDistance(tri, p, pA, pB, pC)) {
            if (checkInTriangle(tri, p)) {
                System.out.println("The p is correct.");
                return true;
            } else {
                System.out.println("The p is out of the triangle.");
                return false;
            }
        } else {
            System.out.println("The p is wrong.");
            return false;
        }
    }

    /**
     * 验证求出的点是不是满足到三点的距离
     *
     * @param tri   三角形
     * @param p     求出的点
     * @param pA    到点 a 的距离
     * @param pB    到点 b 的距离
     * @param pC    到点 c 的距离
     * @return      boolean
     */
    private static boolean checkDistance(Triangle tri, Point p, double pA, double pB, double pC) {
        //精度
        double acc = 0.01;

        if ((tri.length(p, tri.getA()) - pA) < acc
                && (tri.length(p, tri.getB()) - pB) < acc
                && (tri.length(p, tri.getC()) - pC) < acc) {

            return true;

        } else {
            return false;
        }
    }

    /**
     * 检查p点是不是在三角形内部
     * 方法：如果p点再三角形内部（或三角形上），则三角形APB、BPC、CPA的面积和等于三角形ABC的面积，
     * 否则为大于
     *
     * @param tri   三角形
     * @param p     点
     * @return      boolean
     */
    private static boolean checkInTriangle(Triangle tri, Point p) {
        double tPA = tri.length(p, tri.getA());
        double tPB = tri.length(p, tri.getB());
        double tPC = tri.length(p, tri.getC());

        double sPAB = tri.square(tPA, tPB, tri.getLc());
        double sPBC = tri.square(tPB, tPC, tri.getLa());
        double sPCA = tri.square(tPC, tPA, tri.getLb());

        // 精度
        double acc = Math.exp(-6);
        if (Math.abs((sPAB + sPBC + sPCA - tri.square())) < acc) {
            return true;
        } else {
            return false;
        }
    }
}
