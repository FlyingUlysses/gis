//==============================================================================
// YxxfEntPoint.java
//
// POINT entity
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfEntPoint.java,v 1.16 2003/05/08 11:24:03 ekarlo Exp $
// $Log: YxxfEntPoint.java,v $
// Revision 1.16  2003/05/08 11:24:03  ekarlo
// Remove warnings.
//
// Revision 1.15  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.14  2000/10/17 07:43:53  ekarlo
// Change package paths to lower case.
//
// Revision 1.13  1999-09-29 17:04:46-06  walter
// Added JavaDoc comments.
//
// Revision 1.12  1999-07-09 14:10:50-06  ekarlo
// Rearrange package names.
//
// Revision 1.11  1999/06/29  19:53:22  ekarlo
// User Interface - phase 2.
//
// Revision 1.10  1999/06/20  22:34:10  ekarlo
// Rearrange package names.
//
// Revision 1.9  1999/02/08  05:11:14  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.8  1997/07/21  22:41:47  ekarlo
// Make fields public for static get.
//
// Revision 1.7  1996/10/26  00:42:38  ekarlo
// Correct color and layer handling.
//
// Revision 1.6  1996/09/26  02:02:31  ekarlo
// Implement proper color activity.
//
// Revision 1.5  1996/09/13  05:58:05  ekarlo
// Use new base entity class structure.
//
// Revision 1.4  1996/08/25  05:26:19  ekarlo
// Draw ellipse - continuing.
//
// Revision 1.3  1996/08/18  02:16:32  ekarlo
// Begin change to use new transmat.
//
// Revision 1.2  1996/08/14  19:33:07  ekarlo
// Add constructor with arguments to generate point.
//
// Revision 1.1  1996/08/03  04:16:33  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;


/**
 * POINT entity.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YxxfEntPoint extends YxxfEntHeader implements YxxfEnt
{
    /**
     * code  10,20,30 -
     *            Point location (in WCS).
     */
    public
    YxxfGfxPointW               pnt             = new YxxfGfxPointW();


    /**
     * code  39 - Thickness (optional; default = 0).
     */
    public
    double                      thickness       = 0.0;

    /**
     * code  50 - Angle of the X axis for the UCS in effect when the point
     *            was drawn (optional, default = 0). Used when PDMODE
     *            is nonzero.
     */
    public
    double                      xang            = 0.0;

    /**
     * code 210,220,230 -
     *            Extrusion direction. Present if the extrusion direction is
     *            not parallel to the world Z axis.
     */
    public
    YxxfGfxPointW               xtruDir         = new YxxfGfxPointW(0, 0, 1);


    /**
     * Constructor (empty).
     */
    public
    YxxfEntPoint()
    {
    }


    /**
     * Constructor create point and set location.
     * @param The 3D point.
     */
    public
    YxxfEntPoint(YxxfGfxPointW pnt)
    {
        this.pnt = pnt;
    }


    /**
     * Draw point
     * @param gc The graphics context.
     */
    public
    void draw(YxxfGfxContext gc)
    {
        if (gc.setupEntity(this, null) == false)
            return;

        gc.drawCross_MCS(pnt);
    }


    /**
     * Calculate point transmat.
     * @param D The drawing.
     */
    public
    void calc(Yxxf D)
    {
        //
        // connect
        //
        hdr_calc(D);
    }
}

