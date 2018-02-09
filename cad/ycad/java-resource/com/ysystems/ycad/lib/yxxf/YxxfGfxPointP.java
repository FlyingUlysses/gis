//==============================================================================
// YxxfGfxPointP.java
//
// 3D Point, polar
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfGfxPointP.java,v 1.7 2003/04/14 12:38:42 ekarlo Exp $
// $Log: YxxfGfxPointP.java,v $
// Revision 1.7  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.6  2000/10/17 07:43:45  ekarlo
// Change package paths to lower case.
//
// Revision 1.5  1999-09-29 17:02:47-06  walter
// Added JavaDoc comments.
//
// Revision 1.4  1999-07-09 14:10:50-06  ekarlo
// Rearrange package names.
//
// Revision 1.3  1999/06/20  22:34:10  ekarlo
// Rearrange package names.
//
// Revision 1.2  1996/07/30  06:57:42  ekarlo
// Add set functions.
//
// Revision 1.1  1996/07/16  00:51:57  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;


/**
 * 3D Point, polar.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YxxfGfxPointP implements Cloneable
{
    /**
     * The distance TODO.
     */
    public
    double                      dist;
    /**
     * The azimuth TODO.
     */
    public
    double                      azim;
    /**
     * The elevation TODO.
     */
    public
    double                      elev;


    /**
     * Constructor
     */
    public
    YxxfGfxPointP()
    {
        dist = azim = elev = 0.0;
    }


    /**
     * Constructor TODO
     * @param dist The distance.
     * @param azim The azimuth.
     * @param elev The elevation.
     */
    public
    YxxfGfxPointP(double dist, double azim, double elev)
    {
        this.dist   = dist;
        this.azim   = azim;
        this.elev   = elev;
    }


    /**
     * Clone point.
     */
    protected
    Object clone()
    {
        try
        {
            YxxfGfxPointP zclone = (YxxfGfxPointP)super.clone();
            return zclone;
        }
        catch (CloneNotSupportedException e)
        {
            throw new InternalError(e.toString());
        }
    }


    /**
     * Copy a 3D point into this 3D point.
     * @param pnt The source 3D point
     */
    public
    void copyInto(YxxfGfxPointP pnt)
    {
        pnt.dist    = dist;
        pnt.azim    = azim;
        pnt.elev    = elev;
    }


    /**
     * Set the values of this point TODO
     * @param dist The distance.
     * @param azim The azimuth.
     * @param elev The elevation.
     */
    public
    void set(double dist, double azim, double elev)
    {
        this.dist   = dist;
        this.azim   = azim;
        this.elev   = elev;
    }


    /**
     * Set the values of this 3D point.
     * @param PntW The source 3D point.
     */
    public
    void set(YxxfGfxPointW PntW)
    {
        dist        = Math.sqrt(PntW.x * PntW.x + PntW.y * PntW.y + PntW.z * PntW.z);
        azim        = Math.atan2(PntW.y, PntW.x + Double.MIN_VALUE);
        elev        = Math.asin(PntW.z / (dist + Double.MIN_VALUE));
    }


    /**
     * Set the values of this 3D point.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param z The z coordinate.
     */
    public
    void setW(double x, double y, double z)
    {
        dist        = Math.sqrt(x * x + y * y + z * z);
        azim        = Math.atan2(y, x + Double.MIN_VALUE);
        elev        = Math.asin(z / (dist + Double.MIN_VALUE));
    }


    /**
     * Stringify this 3D point.
     * @return The String value.
     */
    public
    String toString()
    {
        return "YxxfGfxPointP[" + dist + ":" + azim + ":" + elev + "]";
    }
}

