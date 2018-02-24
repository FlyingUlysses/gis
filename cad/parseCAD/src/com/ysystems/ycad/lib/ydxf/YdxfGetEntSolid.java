//==============================================================================
// YdxfGetEntSolid.java
//
// Get SOLID from DXF file
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/ydxf/YdxfGetEntSolid.java,v 1.13 2003/04/14 12:37:55 ekarlo Exp $
// $Log: YdxfGetEntSolid.java,v $
// Revision 1.13  2003/04/14 12:37:55  ekarlo
// Update source file header for OSI release.
//
// Revision 1.12  2000/10/17 07:44:24  ekarlo
// Change package paths to lower case.
//
// Revision 1.11  1999-10-21 22:13:45-06  walter
// Added JavaDoc comments.
//
// Revision 1.10  1999-07-09 14:09:43-06  ekarlo
// Rearrange package names.
//
// Revision 1.9  1999/06/27  08:28:14  ekarlo
// Rename class.
//
// Revision 1.8  1999/06/20  22:32:38  ekarlo
// Rearrange package names.
//
// Revision 1.7  1999/02/08  04:57:28  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.6  1998/12/22  14:43:07  ekarlo
// Renames - add "Get" to name.
//
// Revision 1.5  1997/07/21  22:26:46  ekarlo
// Make static get.
//
// Revision 1.4  1997/07/13  18:06:18  ekarlo
// MVC-VH rework - phase 1.
// Rename get class.
//
// Revision 1.3  1996/09/13  06:16:24  ekarlo
// Use new base entity class structure.
//
// Revision 1.2  1996/08/18  02:27:52  ekarlo
// Begin change to use new transmat - use point class.
//
// Revision 1.1  1996/07/02  01:58:25  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.ydxf;


import com.ysystems.ycad.lib.yxxf.*;


/**
 * Get SOLID from DXF file.
 * This class may not be instantiated.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YdxfGetEntSolid
{
    /**
     * Constructor to prevent instantiation.
     */
    private // Defeat instantiation
    YdxfGetEntSolid()
    {
    }


    /**
     * Get the Solid
     * @param getbfr The Get buffer.
     * @param ent The Solid.
     */
    static public
    void get(YdxfGetBuffer getbfr, YxxfEntSolid ent)
    {
        getbfr.get();
        while (true)
        {
            int cod = getbfr.getCodValue();

            if (cod == 0)
                break;

            if (YdxfGetEntHeader.get(getbfr, ent))
            {
            } else

            if (cod == 10)
            {
                ent.pnt1.x = getbfr.doubleValue();
            } else

            if (cod == 20)
            {
                ent.pnt1.y = getbfr.doubleValue();
            } else

            if (cod == 30)
            {
                ent.pnt1.z = getbfr.doubleValue();
            } else

            if (cod == 11)
            {
                ent.pnt2.x = getbfr.doubleValue();
            } else

            if (cod == 21)
            {
                ent.pnt2.y = getbfr.doubleValue();
            } else

            if (cod == 31)
            {
                ent.pnt2.z = getbfr.doubleValue();
            } else

            if (cod == 12)
            {
                ent.pnt3.x = getbfr.doubleValue();
            } else

            if (cod == 22)
            {
                ent.pnt3.y = getbfr.doubleValue();
            } else

            if (cod == 32)
            {
                ent.pnt3.z = getbfr.doubleValue();
            } else

            if (cod == 13)
            {
                ent.pnt4.x = getbfr.doubleValue();
            } else

            if (cod == 23)
            {
                ent.pnt4.y = getbfr.doubleValue();
            } else

            if (cod == 33)
            {
                ent.pnt4.z = getbfr.doubleValue();
            } else

            if (cod == 39)
            {
                ent.thickness = getbfr.doubleValue();
            } else

            if (cod == 210)
            {
                ent.xtruDir.x = getbfr.doubleValue();
            } else

            if (cod == 220)
            {
                ent.xtruDir.y = getbfr.doubleValue();
            } else

            if (cod == 230)
            {
                ent.xtruDir.z = getbfr.doubleValue();
            }

            getbfr.get();
        }
    }
}

