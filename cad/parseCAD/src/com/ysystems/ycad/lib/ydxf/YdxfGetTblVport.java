//==============================================================================
// YdxfGetTblVport.java
//
// Get VPORT table
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/ydxf/YdxfGetTblVport.java,v 1.12 2003/04/14 12:37:55 ekarlo Exp $
// $Log: YdxfGetTblVport.java,v $
// Revision 1.12  2003/04/14 12:37:55  ekarlo
// Update source file header for OSI release.
//
// Revision 1.11  2000/10/17 07:44:17  ekarlo
// Change package paths to lower case.
//
// Revision 1.10  1999-10-21 21:30:03-06  walter
// Added JavaDoc comments.
//
// Revision 1.9  1999-07-09 14:09:43-06  ekarlo
// Rearrange package names.
//
// Revision 1.8  1999/06/27  08:28:14  ekarlo
// Rename class.
//
// Revision 1.7  1999/06/20  22:32:38  ekarlo
// Rearrange package names.
//
// Revision 1.6  1999/02/08  04:54:07  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.5  1998/12/22  14:43:39  ekarlo
// Renames - add "Get" to name.
//
// Revision 1.4  1997/07/21  22:31:21  ekarlo
// Changes for static gets.
//
// Revision 1.3  1997/07/13  18:11:30  ekarlo
// MVC-VH rework - phase 1.
// Rename get class.
//
// Revision 1.2  1996/07/30  06:31:48  ekarlo
// Use point class.
//
// Revision 1.1  1996/07/02  01:58:27  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.ydxf;


import com.ysystems.ycad.lib.yxxf.*;


/**
 * Get VPORT table.
 * This class may not be instantiated.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YdxfGetTblVport
{
    /**
     * Constructor to disable instantiation.
     */
    private // Defeat instantiation
    YdxfGetTblVport()
    {
    }


    /**
     * Get vport
     * @param getbfr The Get buffer.
     * @param tbl The Viewport.
     */
    static public
    void get(YdxfGetBuffer getbfr, YxxfTblVport tbl)
    {
        getbfr.get();
        while (true)
        {
            int cod = getbfr.getCodValue();

            if (cod == 0)
                break;

            if (cod == 2)
            {
                tbl.name = getbfr.stringValue();
            } else

            if (cod == 5)
            {
                tbl.handle = getbfr.stringValue();
            } else

            if (cod == 100)
            {
                tbl.subClassMarker = getbfr.stringValue();
            } else

            if (cod == 70)
            {
                tbl.flags = getbfr.intValue();
            } else

            if (cod == 10)
            {
                tbl.llx = getbfr.doubleValue();
            } else

            if (cod == 20)
            {
                tbl.lly = getbfr.doubleValue();
            } else

            if (cod == 11)
            {
                tbl.urx = getbfr.doubleValue();
            } else

            if (cod == 21)
            {
                tbl.ury = getbfr.doubleValue();
            } else

            if (cod == 12)
            {
                tbl.vcpx = getbfr.doubleValue();
            } else

            if (cod == 22)
            {
                tbl.vcpy = getbfr.doubleValue();
            } else

            if (cod == 13)
            {
                tbl.sbpx = getbfr.doubleValue();
            } else

            if (cod == 23)
            {
                tbl.sbpy = getbfr.doubleValue();
            } else

            if (cod == 14)
            {
                tbl.ssx = getbfr.doubleValue();
            } else

            if (cod == 24)
            {
                tbl.ssy = getbfr.doubleValue();
            } else

            if (cod == 15)
            {
                tbl.gsx = getbfr.doubleValue();
            } else

            if (cod == 25)
            {
                tbl.gsy = getbfr.doubleValue();
            } else

            if (cod == 16)
            {
                tbl.vdir.x = getbfr.doubleValue();
            } else

            if (cod == 26)
            {
                tbl.vdir.y = getbfr.doubleValue();
            } else

            if (cod == 36)
            {
                tbl.vdir.z = getbfr.doubleValue();
            } else

            if (cod == 17)
            {
                tbl.vtgt.x = getbfr.doubleValue();
            } else

            if (cod == 27)
            {
                tbl.vtgt.y = getbfr.doubleValue();
            } else

            if (cod == 37)
            {
                tbl.vtgt.z = getbfr.doubleValue();
            } else

            if (cod == 40)
            {
                tbl.vheight = getbfr.doubleValue();
            } else

            if (cod == 41)
            {
                tbl.vaspect = getbfr.doubleValue();
            } else

            if (cod == 42)
            {
                tbl.lensLength = getbfr.doubleValue();
            } else

            if (cod == 43)
            {
                tbl.fcp = getbfr.doubleValue();
            } else

            if (cod == 44)
            {
                tbl.bcp = getbfr.doubleValue();
            } else

            if (cod == 50)
            {
                tbl.snaprotang = getbfr.doubleValue();
            } else

            if (cod == 51)
            {
                tbl.viewtwistang = getbfr.doubleValue();
            } else

            if (cod == 68)
            {
                tbl.status = getbfr.intValue();
            } else

            if (cod == 69)
            {
                tbl.id = getbfr.intValue();
            } else

            if (cod == 71)
            {
                tbl.viewmode = getbfr.intValue();
            } else

            if (cod == 72)
            {
                tbl.circleZoomPercent = getbfr.intValue();
            } else

            if (cod == 73)
            {
                tbl.fastZoomSetting = getbfr.intValue();
            } else

            if (cod == 74)
            {
                tbl.ucsicon = getbfr.intValue();
            } else

            if (cod == 75)
            {
                tbl.snaponoff = getbfr.intValue();
            } else

            if (cod == 76)
            {
                tbl.gridonoff = getbfr.intValue();
            } else

            if (cod == 77)
            {
                tbl.snapStyle = getbfr.intValue();
            } else

            if (cod == 78)
            {
                tbl.snapIsopair = getbfr.intValue();
            }

            getbfr.get();
        }
    }
}

