//==============================================================================
// YdxfGetEntCircle.java
//
// Get CIRCLE from DXF file
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/ydxf/YdxfGetEntCircle.java,v 1.13 2003/04/14 12:37:55 ekarlo Exp $
// $Log: YdxfGetEntCircle.java,v $
// Revision 1.13  2003/04/14 12:37:55  ekarlo
// Update source file header for OSI release.
//
// Revision 1.12  2000/10/17 07:44:28  ekarlo
// Change package paths to lower case.
//
// Revision 1.11  1999-10-21 22:17:57-06  walter
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
// Revision 1.7  1999/02/08  04:59:35  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.6  1998/12/22  14:42:23  ekarlo
// Renames - add "Get" to name.
//
// Revision 1.5  1997/07/21  22:25:16  ekarlo
// Make static get.
//
// Revision 1.4  1997/07/13  18:03:43  ekarlo
// MVC-VH rework - phase 1.
// Rename get class.
//
// Revision 1.3  1996/09/13  06:15:24  ekarlo
// Use new base entity class structure.
//
// Revision 1.2  1996/08/18  02:27:52  ekarlo
// Begin change to use new transmat - use point class.
//
// Revision 1.1  1996/07/02  01:58:24  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.ydxf;


import com.ysystems.ycad.lib.yxxf.*;


/**
 * Get CIRCLE from DXF file.
 * This class may not be instantiated.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YdxfGetEntCircle
{
    /**
     * Constructor to prevent instantiation.
     */
    private // Defeat instantiation
    YdxfGetEntCircle()
    {
    }


    /**
     * Get the Circle.
     * @param getbfr The Get buffer.
     * @param ent The Circle.
     */
    static public
    void get(YdxfGetBuffer getbfr, YxxfEntCircle ent)
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
                ent.center.x = getbfr.doubleValue();
            } else

            if (cod == 20)
            {
                ent.center.y = getbfr.doubleValue();
            } else

            if (cod == 30)
            {
                ent.center.z = getbfr.doubleValue();
            } else

            if (cod == 39)
            {
                ent.thickness = getbfr.doubleValue();
            } else

            if (cod == 40)
            {
                ent.radius = getbfr.doubleValue();
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

