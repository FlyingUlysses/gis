//==============================================================================
// YdxfGetEntHeader.java
//
// Get Entity common header items from DXF file
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/ydxf/YdxfGetEntHeader.java,v 1.14 2003/04/14 12:37:55 ekarlo Exp $
// $Log: YdxfGetEntHeader.java,v $
// Revision 1.14  2003/04/14 12:37:55  ekarlo
// Update source file header for OSI release.
//
// Revision 1.13  2001/10/10 03:37:27  ekarlo
// Update copyright.
//
// Revision 1.12  2000-10-17 01:44:27-06  ekarlo
// Change package paths to lower case.
//
// Revision 1.11  1999-10-21 22:16:52-06  walter
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
// Revision 1.5  1998/02/02  21:11:31  ekarlo
// Deactivate header handle.
//
// Revision 1.4  1997/07/21  22:25:16  ekarlo
// Make static get.
//
// Revision 1.3  1997/07/13  18:03:43  ekarlo
// MVC-VH rework - phase 1.
// Rename get class.
//
// Revision 1.2  1996/09/13  06:15:24  ekarlo
// Use new base entity class structure.
//
// Revision 1.1  1996/07/02  01:58:24  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.ydxf;


import com.ysystems.ycad.lib.yxxf.*;


/**
 * Get Entity common header items from DXF file.
 * This class may not be instantiated.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YdxfGetEntHeader
{
    /**
     * Constructor to prevent instantiation.
     */
    private // Defeat instantiation
    YdxfGetEntHeader()
    {
    }


    /**
     * Get the Entity Header items.
     * @param getbfr The Get buffer.
     * @param hdr The Entity Header items.
     */
    static public
    boolean get(YdxfGetBuffer getbfr, YxxfEntHeader hdr)
    {
        int cod = getbfr.getCodValue();

//      if (cod == 5)
//      {
//          hdr.hdr_handle = getbfr.stringValue();
//          return true;
//      } else
//
        if (cod == 6)
        {
            hdr.hdr_ltype = getbfr.ltypeValue();
            return true;
        } else

        if (cod == 8)
        {
            hdr.hdr_layer = getbfr.layerValue();
            return true;
        } else

        if (cod == 48)
        {
            hdr.hdr_ltypescale = getbfr.doubleValue();
            return true;
        } else

        if (cod == 60)
        {
            if (getbfr.intValue() == 1)
                hdr.hdr_visible = false;
            else
                hdr.hdr_visible = true;
            return true;
        } else

        if (cod == 62)
        {
            hdr.hdr_aci = getbfr.intValue();
            return true;
        } else

        if (cod == 67)
        {
            hdr.hdr_space = getbfr.intValue();
            return true;
        }

        return false;
    }
}

