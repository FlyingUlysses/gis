//==============================================================================
// YdxfGetEntInsert.java
//
// Get INSERT from DXF file
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/ydxf/YdxfGetEntInsert.java,v 1.15 2003/04/14 12:37:55 ekarlo Exp $
// $Log: YdxfGetEntInsert.java,v $
// Revision 1.15  2003/04/14 12:37:55  ekarlo
// Update source file header for OSI release.
//
// Revision 1.14  2000/10/17 07:44:27  ekarlo
// Change package paths to lower case.
//
// Revision 1.13  1999-10-21 22:16:12-06  walter
// Added JavaDoc comments.
//
// Revision 1.12  1999-07-09 14:09:43-06  ekarlo
// Rearrange package names.
//
// Revision 1.11  1999/06/27  08:28:14  ekarlo
// Rename class.
//
// Revision 1.10  1999/06/20  22:32:38  ekarlo
// Rearrange package names.
//
// Revision 1.9  1999/02/08  04:59:35  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.8  1998/12/22  14:42:54  ekarlo
// Renames - add "Get" to name.
//
// Revision 1.7  1997/07/21  22:25:16  ekarlo
// Make static get.
//
// Revision 1.6  1997/07/13  18:06:18  ekarlo
// MVC-VH rework - phase 1.
// Rename get class.
//
// Revision 1.5  1996/09/13  06:15:24  ekarlo
// Use new base entity class structure.
//
// Revision 1.4  1996/08/19  15:52:40  ekarlo
// Don't calculate insert transform automatically.
//
// Revision 1.3  1996/08/19  04:59:31  ekarlo
// Calculate insert transform.
//
// Revision 1.2  1996/07/30  06:19:21  ekarlo
// Use point class.
//
// Revision 1.1  1996/07/02  01:58:24  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.ydxf;


import com.ysystems.ycad.lib.yxxf.*;


/**
 * Get INSERT from DXF file.
 * This class may not be instantiated.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YdxfGetEntInsert
{
    /**
     * Constructor to prevent instantiation.
     */
    private // Defeat instantiation
    YdxfGetEntInsert()
    {
    }


    /**
     * Get the Insert
     * @param getbfr The Get buffer.
     * @param ent The Insert.
     */
    static public
    void get(YdxfGetBuffer getbfr, YxxfEntInsert ent)
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

            if (cod == 2)
            {
                ent.block = getbfr.blockValue();
            } else

            if (cod == 10)
            {
                ent.inspnt.x = getbfr.doubleValue();
            } else

            if (cod == 20)
            {
                ent.inspnt.y = getbfr.doubleValue();
            } else

            if (cod == 30)
            {
                ent.inspnt.z = getbfr.doubleValue();
            } else

            if (cod == 41)
            {
                ent.scale.x = getbfr.doubleValue();
            } else

            if (cod == 42)
            {
                ent.scale.y = getbfr.doubleValue();
            } else

            if (cod == 43)
            {
                ent.scale.z = getbfr.doubleValue();
            } else

            if (cod == 44)
            {
                ent.colSpacing = getbfr.doubleValue();
            } else

            if (cod == 45)
            {
                ent.rowSpacing = getbfr.doubleValue();
            } else

            if (cod == 50)
            {
                ent.rotang = getbfr.doubleValue();
            } else

            if (cod == 66)
            {
                ent.attFollow = getbfr.intValue();
            } else

            if (cod == 70)
            {
                ent.colCount = getbfr.intValue();
            } else

            if (cod == 71)
            {
                ent.rowCount = getbfr.intValue();
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


