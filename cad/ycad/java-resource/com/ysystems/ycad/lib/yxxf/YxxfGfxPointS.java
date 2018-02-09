//==============================================================================
// YxxfGfxPointS.java
//
// 2D Point, screen
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfGfxPointS.java,v 1.8 2003/05/08 11:24:03 ekarlo Exp $
// $Log: YxxfGfxPointS.java,v $
// Revision 1.8  2003/05/08 11:24:03  ekarlo
// Remove warnings.
//
// Revision 1.7  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.6  2000/10/17 07:43:45  ekarlo
// Change package paths to lower case.
//
// Revision 1.5  1999-09-29 17:02:36-06  walter
// Added JavaDoc comments.
//
// Revision 1.4  1999-07-09 14:10:50-06  ekarlo
// Rearrange package names.
//
// Revision 1.3  1999/06/20  22:34:10  ekarlo
// Rearrange package names.
//
// Revision 1.2  1996/07/30  06:59:15  ekarlo
// Reformat.
//
// Revision 1.1  1996/07/12  12:11:39  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;


/**
 * 2D Point, screen.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YxxfGfxPointS implements Cloneable
{
    /**
     * code  x coordinate - Point
     */
    public
    int                         x;
    /**
     * code  y coordinate - Point
     */
    public
    int                         y;


    /**
     * Constructor
     */
    public
    YxxfGfxPointS()
    {
        x = y = 0;
    }


    /**
     * Constructor
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public
    YxxfGfxPointS(int x, int y)
    {
        this.x      = x;
        this.y      = y;
    }


    /**
     * Clone point.
     */
    protected
    Object clone()
    {
        try
        {
            YxxfGfxPointS zclone = (YxxfGfxPointS)super.clone();
            return zclone;
        }
        catch (CloneNotSupportedException e)
        {
            throw new InternalError(e.toString());
        }
    }


    /**
     * Copy the values of a 2D point into this 2D point.
     * @param pnt The source 2D point.
     */
    public
    void copyInto(YxxfGfxPointS pnt)
    {
        pnt.x       = x;
        pnt.y       = y;
    }


    /**
     * Set the coordinates of this 2D point.
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public
    void set(int x, int y)
    {
        this.x      = x;
        this.y      = y;
    }


    /**
     * Stringify this 2D point.
     * @return The value of this point.
     */
    public
    String toString()
    {
        return "YxxfGfxPointS[" + x + ":" + y + "]";
    }
}

