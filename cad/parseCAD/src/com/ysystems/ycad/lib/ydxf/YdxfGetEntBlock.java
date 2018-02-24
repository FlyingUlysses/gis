//==============================================================================
// YdxfGetEntBlock.java
//
// Get BLOCK from DXF file
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/ydxf/YdxfGetEntBlock.java,v 1.14 2003/04/14 12:37:55 ekarlo Exp $
// $Log: YdxfGetEntBlock.java,v $
// Revision 1.14  2003/04/14 12:37:55  ekarlo
// Update source file header for OSI release.
//
// Revision 1.13  2000/10/17 07:44:29  ekarlo
// Change package paths to lower case.
//
// Revision 1.12  1999-10-21 22:18:30-06  walter
// Added JavaDoc comments.
//
// Revision 1.11  1999-07-09 14:09:43-06  ekarlo
// Rearrange package names.
//
// Revision 1.10  1999/06/27  08:28:14  ekarlo
// Rename class.
//
// Revision 1.9  1999/06/20  22:32:38  ekarlo
// Rearrange package names.
//
// Revision 1.8  1999/02/08  04:59:35  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.7  1998/12/22  14:43:27  ekarlo
// Renames - add "Get" to name.
//
// Revision 1.6  1997/07/21  22:31:21  ekarlo
// Changes for static gets.
//
// Revision 1.5  1997/07/13  18:10:09  ekarlo
// MVC-VH rework - phase 1.
// Rename get class.
//
// Revision 1.4  1996/09/13  06:16:24  ekarlo
// Use new base entity class structure.
//
// Revision 1.3  1996/07/30  06:30:44  ekarlo
// Use point class.
//
// Revision 1.2  1996/07/02  04:46:14  ekarlo
// Add basepntz.
//
// Revision 1.1  1996/07/02  01:58:27  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.ydxf;


import com.ysystems.ycad.lib.yxxf.*;


/**
 * Get BLOCK from DXF file.
 * This class may not be instantiated.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YdxfGetEntBlock
{
    /**
     * Constructor to prevent instantiation.
     */
    private // Defeat instantiation
    YdxfGetEntBlock()
    {
    }


    /**
     * Get the Block.
     * @param getbfr The Get buffer.
     * @param ent The Block.
     */
    static public
    void get(YdxfGetBuffer getbfr, YxxfEntBlock tbl)
    {
        getbfr.get();
        while (true)
        {
            int cod = getbfr.getCodValue();

            if (cod == 0)
                break;

            if (YdxfGetEntHeader.get(getbfr, tbl))
            {
            } else

            if (cod == 1)
            {
                tbl.blockname1 = getbfr.stringValue();
            } else

            if (cod == 2)
            {
                tbl.setBlockname2(getbfr.chararrValue());
            } else

            if (cod == 3)
            {
                tbl.blockname3 = getbfr.stringValue();
            } else

            if (cod == 10)
            {
                tbl.basepnt.x= getbfr.doubleValue();
            } else

            if (cod == 20)
            {
                tbl.basepnt.y= getbfr.doubleValue();
            } else

            if (cod == 30)
            {
                tbl.basepnt.z= getbfr.doubleValue();
            } else

            if (cod == 70)
            {
                tbl.flags = getbfr.intValue();
            }

            getbfr.get();
        }
    }
}

