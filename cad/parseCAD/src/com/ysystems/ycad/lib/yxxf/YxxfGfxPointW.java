//==============================================================================
// YxxfGfxPointW.java
//
// 3D Point, world
//==============================================================================

//==============================================================================
// Ycad - Java CAD library
// Copyright (c) 2003 - Ed Karlo - mailto:ekarlo@ysystems.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
//==============================================================================

//==============================================================================
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfGfxPointW.java,v 1.15 2003/05/08 11:24:03 ekarlo Exp $
// $Log: YxxfGfxPointW.java,v $
// Revision 1.15  2003/05/08 11:24:03  ekarlo
// Remove warnings.
//
// Revision 1.14  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.13  2000/10/17 07:43:44  ekarlo
// Change package paths to lower case.
//
// Revision 1.12  1999-10-13 00:30:11-06  ekarlo
// Fix comment.
//
// Revision 1.11  1999-09-29 17:08:55-06  walter
// Added JavaDoc comments.
//
// Revision 1.10  1999-09-08 13:16:14-06  walter
// Add JavaDoc comments.
//
// Revision 1.9  1999-07-09 14:10:50-06  ekarlo
// Rearrange package names.
//
// Revision 1.8  1999/06/29  19:55:25  ekarlo
// User Interface - phase 2.
//
// Revision 1.7  1999/06/20  22:34:10  ekarlo
// Rearrange package names.
//
// Revision 1.6  1997/07/23  14:25:39  ekarlo
// Change method type.
//
// Revision 1.5  1996/08/19  04:54:08  ekarlo
// Move in calcAAA() from YxxfGfxContext.
//
// Revision 1.4  1996/08/13  02:27:30  ekarlo
// 1) Add world constants.
// 2) Add set, normalize, angle methods.
//
// Revision 1.3  1996/07/30  06:59:52  ekarlo
// 1) Reformat.
// 2) Add set functions.
//
// Revision 1.2  1996/07/12  11:29:34  ekarlo
// 1) Add copyInto method.
// 2) Change toSting format.
//
// Revision 1.1  1996/07/11  22:50:17  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;


/**
 * 3D Point, world.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YxxfGfxPointW implements Cloneable
{
    /**
     * TODO
     */
    public static final
    YxxfGfxPointW W0            = new YxxfGfxPointW(0, 0, 0);
    /**
     * TODO
     */
    public static final
    YxxfGfxPointW Wx            = new YxxfGfxPointW(1, 0, 0);
    /**
     * TODO
     */
    public static final
    YxxfGfxPointW Wy            = new YxxfGfxPointW(0, 1, 0);
    /**
     * TODO
     */
    public static final
    YxxfGfxPointW Wz            = new YxxfGfxPointW(0, 0, 1);

    /**
     * code  x - Point.
     */
    public
    double                      x;
    /**
     * code  y - Point.
     */
    public
    double                      y;
    /**
     * code  z - Point.
     */
    public
    double                      z;


    /**
     * Constructor
     */
    public
    YxxfGfxPointW()
    {
        x = y = z = 0.0;
    }


    /**
     * Constructor
     * @param p The graphics point object
     */
    public
    YxxfGfxPointW(YxxfGfxPointW p)
    {
        this.x      = p.x;
        this.y      = p.y;
        this.z      = p.z;
    }

    /**
     * Constructor
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param z The z coordinate.
     */
    public
    YxxfGfxPointW(double x, double y, double z)
    {
        this.x      = x;
        this.y      = y;
        this.z      = z;
    }

    /**
     * Copy the values of one YxxfGfxPointW into
     * another.
     * @param p The 3D point source object.
     */
    public
    void copyInto(YxxfGfxPointW p)
    {
        p.x         = x;
        p.y         = y;
        p.z         = z;
    }

    /**
     * Set the coordinate values.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param z The z coordinate.
     * @return reference to this 3D point.
     */
    public
    YxxfGfxPointW set(double x, double y, double z)
    {
        this.x      = x;
        this.y      = y;
        this.z      = z;

        return this;
    }

    /**
     * Set the values of this 3D point based on polar 
     * coordinate values TODO.
     * @param dist The distance.
     * @param azim The azimuth.
     * @param elev The elevation.
     * @return Reference to this 3D point.
     */
    public
    YxxfGfxPointW setP(double dist, double azim, double elev)
    {
        double radius = dist * Math.cos(elev);
        x           = radius    * Math.cos(azim);
        y           = radius    * Math.sin(azim);
        z           = dist      * Math.sin(elev);

        return this;
    }

    /**
     * Set the coordinates based on the coordinate values of a 
     * 3D point object.
     * @param PntW The source 3D point.
     * @return Reference to this 3D point.
     */
    public
    YxxfGfxPointW set(YxxfGfxPointW PntW)
    {
        x           = PntW.x;
        y           = PntW.y;
        z           = PntW.z;

        return this;
    }


    /**
     * Set the coordinates based on the value of a 
     * 3D polar coordinate point.
     * @param PntP The source 3D polar coordinate.
     * @return Reference to this 3D point.
     */
    public
    YxxfGfxPointW set(YxxfGfxPointP PntP)
    {
        double radius = PntP.dist * Math.cos(PntP.elev);
        x           = radius    * Math.cos(PntP.azim);
        y           = radius    * Math.sin(PntP.azim);
        z           = PntP.dist * Math.sin(PntP.elev);

        return this;
    }


    /**
     * Calculate cross product.
     * From <I>Fast Algorithms for 3D-Graphics</I>.<br>
     * <CODE>
     * #define Cross_product(n, a, b)\
     *     ((n)[X] = (a)[Y] * (b)[Z] - (a)[Z] * (b)[Y],\
     *      (n)[Y] = (a)[Z] * (b)[X] - (a)[X] * (b)[Z],\
     *      (n)[Z] = (a)[X] * (b)[Y] - (a)[Y] * (b)[X])
     * </CODE>
     * @param a A 3D point.
     * @param b A 3D point.
     */
    public
    void crossProduct(YxxfGfxPointW a, YxxfGfxPointW b)
    {
        //System.out.println("PointW:a=" + a + ",dist=" + a.distance());
        //System.out.println("PointW:b=" + b + ",dist=" + b.distance());
        x = a.y * b.z - a.z * b.y;
        y = a.z * b.x - a.x * b.z;
        z = a.x * b.y - a.y * b.x;
        //System.out.println("PointW:c=" + this + ",dist=" + this.distance());
    }


    /**
     * Calculate Ax_out and Ay_out axes from Az_in using the 
     * <I>Arbitrary Axis Algorithrm</I>.
     * It is assumed that Az_in has already been normalized<br>
     * 1.0 / 64.0 == .015625 exactly
     * @param Ax_out An axis.
     * @param Ay_out An axis.
     * @param Az_in TODO
     */
    public static
    void calcAAA(YxxfGfxPointW Ax_out, YxxfGfxPointW Ay_out, YxxfGfxPointW Az_in)
    {
        if (Math.abs(Az_in.x) < 1.0/64.0 && Math.abs(Az_in.y) < 1.0/64.0)
        {
            //System.out.println("calcAAA: Wy x Az_in");
            Ax_out.crossProduct(YxxfGfxPointW.Wy, Az_in);
        } else
        {
            //System.out.println("calcAAA: Wz x Az_in");
            Ax_out.crossProduct(YxxfGfxPointW.Wz, Az_in);
        }
        Ax_out.normalize();
        //System.out.println("calcAAA: Az_in x Ax_out");
        Ay_out.crossProduct(Az_in, Ax_out);
        Ay_out.normalize();
        //System.out.println("calcAAA: Ax_out  =" + Ax_out + ",dist=" + Ax_out.distance());
        //System.out.println("calcAAA: Ay_out  =" + Ay_out + ",dist=" + Ay_out.distance());
        //System.out.println("calcAAA: Az_in   =" + Az_in  + ",dist=" + Az_in .distance());
    }


    /**
     * Calculate Axis angles.
     */
    public
    void angle()
    {
        double vecLength = Math.sqrt(x * x + y * y + z * z);

        x = Math.acos(x / vecLength);
        y = Math.acos(y / vecLength);
        z = Math.acos(z / vecLength);

        //double tx = Math.atan2(z, y + Double.MIN_VALUE);
        //double ty = Math.atan2(x, z + Double.MIN_VALUE);
        //double tz = Math.atan2(y, x + Double.MIN_VALUE);

        //x = tx; y = ty; z = tz;
    }


    /**
     * Calculate axis angles.
     * @param p The other 3D point.
     */
    public
    void angle(YxxfGfxPointW p)
    {
        double vecLength = Math.sqrt(p.x * p.x + p.y * p.y + p.z * p.z);

        x = Math.acos(p.x / vecLength);
        y = Math.acos(p.y / vecLength);
        z = Math.acos(p.z / vecLength);
    }


    /**
     * Calculate axis angles.
     * @param p The other 3D point.
     */
    public
    void angleX(YxxfGfxPointW p)
    {
        x = calcangle(0, 0, p.y, p.z);
        y = calcangle(0, 0, p.z, p.x);
        z = calcangle(0, 0, p.x, p.y);
    }


    /**
     * Find angle of 2D vector in plane.
     * @param x1 TODO
     * @param y1 TODO
     * @param x2 TODO
     * @param y2 TODO
     * @return Angle of vector with x axis in radians.
     */
    public static
    double calcangle(double x1, double y1, double x2, double y2)
    {
        double tmpx = x2 - x1;
        double tmpy = y2 - y1;
        double hypt = Math.sqrt(tmpx * tmpx + tmpy * tmpy);

        if (tmpx < 0)
        {
            if (tmpy < 0)
            {   // 3rd quad
                return Math.PI + Math.acos(-tmpx / hypt);
            }
            else
            {   // 2nd quad
                return Math.PI - Math.acos(-tmpx / hypt);
            }
        }
        else
        {
            if (tmpy < 0)
            {   // 4th quad
                return (2.0 * Math.PI) - Math.acos(tmpx / hypt);
            }
            else
            {   // 1st quad
                return Math.acos(tmpx / hypt);
            }
        }
    }


    /**
     * Make unit vector.
     */
    public
    void normalize()
    {
        double vecLength = Math.sqrt(x * x + y * y + z * z);

        x = x / vecLength;
        y = y / vecLength;
        z = z / vecLength;
    }


    /**
     * Make unit vector.
     * @param p The 3D point.
     */
    public
    void normalize(YxxfGfxPointW p)
    {
        double vecLength = Math.sqrt(p.x * p.x + p.y * p.y + p.z * p.z);

        x = p.x / vecLength;
        y = p.y / vecLength;
        z = p.z / vecLength;
    }


    /**
     * Distance from origin.
     * @return The distance.
     */
    public
    double distance()
    {
        return Math.sqrt(x * x + y * y + z * z);
    }


    /**
     * Distance from point.
     * @param p1 The 3D point.
     * @return The distance.
     */
    public
    double distance(YxxfGfxPointW p1)
    {
        return Math.sqrt((x - p1.x) * (x - p1.x) + (y - p1.y) * (y - p1.y) + (z - p1.z) * (z - p1.z));
    }


    /**
     * Clone point.
     * @exception InternalError If CloneNotSupportedException.
     * @return Reference to a new point.
     */
    protected
    Object clone()
    {
        try
        {
            YxxfGfxPointW zclone = (YxxfGfxPointW)super.clone();
            return zclone;
        }
        catch (CloneNotSupportedException e)
        {
            throw new InternalError(e.toString());
        }
    }


    /**
     * Stringify this 3D point.
     * @return this 3D point.
     */
    public
    String toString()
    {
        return "YxxfGfxPointW[" + x + " " + y + " " + z + "]";
    }
}

