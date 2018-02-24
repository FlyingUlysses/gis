//==============================================================================
// YxxfEnt3Dface.java
//
// 3DFACE entity
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfEnt3Dface.java,v 1.10 2003/05/08 11:24:03 ekarlo Exp $
// $Log: YxxfEnt3Dface.java,v $
// Revision 1.10  2003/05/08 11:24:03  ekarlo
// Remove warnings.
//
// Revision 1.9  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.8  2000/10/17 07:44:00  ekarlo
// Change package paths to lower case.
//
// Revision 1.7  1999-09-22 22:56:35-06  walter
// Added JavaDoc comments.
//
// Revision 1.6  1999-08-07 15:19:14-06  ekarlo
// Use $SPLFRAME.
//
// Revision 1.5  1999/07/09  20:10:50  ekarlo
// Rearrange package names.
//
// Revision 1.4  1999/06/29  19:53:22  ekarlo
// User Interface - phase 2.
//
// Revision 1.3  1999/06/20  22:34:10  ekarlo
// Rearrange package names.
//
// Revision 1.2  1999/02/08  05:10:53  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.1  1999/01/28  04:27:45  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;


/**
 * 3DFACE entity.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YxxfEnt3Dface extends YxxfEntHeader implements YxxfEnt
{
    /**
     * code  10,20,30.
     *            First corner (in WCS).
     */
    public
    YxxfGfxPointW               pnt1            = new YxxfGfxPointW();

    /**
     * code  11,21,31.
     *            Second corner (in WCS).
     */
    public
    YxxfGfxPointW               pnt2            = new YxxfGfxPointW();

    /**
     * code  12,22,32.
     *            Third corner (in WCS).
     */
    public
    YxxfGfxPointW               pnt3            = new YxxfGfxPointW();

    /**
     * code  13,23,33.
     *            Fourth corner (if only three corners are entered, this is the
     *            same as the third corner) (in WCS).
     */
    public
    YxxfGfxPointW               pnt4            = new YxxfGfxPointW();

    /**
     * code  70 - Invisible edge flags. (optional, default = 0)
     * <UL>
     *   <LI>1 = First  edge is invisible.
     *   <LI>2 = Second edge is invisible.
     *   <LI>4 = Third  edge is invisible.
     *   <LI>8 = Fourth edge is invisible.
     * </UL>
     */
    public
    int                         flags           = 0;




    // Calculated items

    /**
     * Number of points in 3dface - 3 or 4.
     */
    private
    int                         numpoints;


    /**
     * Draw 3dface.
     * @param gc The Graphics Context.
     */
    public
    void draw(YxxfGfxContext gc)
    {
        if (gc.setupEntity(this, null) == false)
            return;

        if (numpoints == 3)
        {
            if (gc.getDrawing().secHeader.splframe != 0)
            {  // all edges visible
                gc.drawLine__MCS__cont__flat__wid_none(pnt1, pnt2);
                gc.drawLine__MCS__cont__flat__wid_none(pnt2, pnt3);
                gc.drawLine__MCS__cont__flat__wid_none(pnt3, pnt1);
            }
            else
            {
                if ((flags & 1) == 0) // 1st edge
                {
                    gc.drawLine__MCS__cont__flat__wid_none(pnt1, pnt2);
                }
                if ((flags & 2) == 0) // 2nd edge
                {
                    gc.drawLine__MCS__cont__flat__wid_none(pnt2, pnt3);
                }
                if ((flags & 4) == 0) // 3rd edge
                {
                    gc.drawLine__MCS__cont__flat__wid_none(pnt3, pnt1);
                }
            }
        }
        else
        {
            if (gc.getDrawing().secHeader.splframe != 0)
            {  // all edges visible
                gc.drawLine__MCS__cont__flat__wid_none(pnt1, pnt2);
                gc.drawLine__MCS__cont__flat__wid_none(pnt2, pnt3);
                gc.drawLine__MCS__cont__flat__wid_none(pnt3, pnt4);
                gc.drawLine__MCS__cont__flat__wid_none(pnt4, pnt1);
            }
            else
            {
                if ((flags & 1) == 0) // 1st edge
                {
                    gc.drawLine__MCS__cont__flat__wid_none(pnt1, pnt2);
                }
                if ((flags & 2) == 0) // 2nd edge
                {
                    gc.drawLine__MCS__cont__flat__wid_none(pnt2, pnt3);
                }
                if ((flags & 4) == 0) // 3rd edge
                {
                    gc.drawLine__MCS__cont__flat__wid_none(pnt3, pnt4);
                }
                if ((flags & 8) == 0) // 4th edge
                {
                    gc.drawLine__MCS__cont__flat__wid_none(pnt4, pnt1);
                }
            }
        }
    }


    /**
     * Calculate 3dface transmat.
     */
    public
    void calc(Yxxf D)
    {
        //
        // connect
        //
        hdr_calc(D);


        // Set 3dface type (3 or 4 points)
        if (pnt3.x != pnt4.x || pnt3.y != pnt4.y || pnt3.z != pnt4.z)
        {   // 4 pointer
            numpoints = 4;
        }
        else
        {   // 3 pointer
            numpoints = 3;
        }
    }
}

