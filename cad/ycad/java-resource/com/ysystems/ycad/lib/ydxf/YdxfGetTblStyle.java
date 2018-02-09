//==============================================================================
// YdxfGetTblStyle.java
//
// Get STYLE table
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/ydxf/YdxfGetTblStyle.java,v 1.11 2003/04/14 12:37:55 ekarlo Exp $
// $Log: YdxfGetTblStyle.java,v $
// Revision 1.11  2003/04/14 12:37:55  ekarlo
// Update source file header for OSI release.
//
// Revision 1.10  2000/10/17 07:44:17  ekarlo
// Change package paths to lower case.
//
// Revision 1.9  1999-10-21 21:32:57-06  walter
// Added JavaDoc comments.
//
// Revision 1.8  1999-07-09 14:09:43-06  ekarlo
// Rearrange package names.
//
// Revision 1.7  1999/06/27  08:28:14  ekarlo
// Rename class.
//
// Revision 1.6  1999/06/20  22:32:38  ekarlo
// Rearrange package names.
//
// Revision 1.5  1999/02/08  04:54:07  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.4  1998/12/22  14:43:39  ekarlo
// Renames - add "Get" to name.
//
// Revision 1.3  1997/07/21  22:31:21  ekarlo
// Changes for static gets.
//
// Revision 1.2  1997/07/13  18:10:09  ekarlo
// MVC-VH rework - phase 1.
// Rename get class.
//
// Revision 1.1  1996/09/27  09:40:10  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.ydxf;


import com.ysystems.ycad.lib.yxxf.*;


/**
 * Get STYLE table.
 * This class may not be instantiated.
 */
public class YdxfGetTblStyle
{
    /**
     * Constructor to prevent instantiation.
     */
    private // Defeat instantiation
    YdxfGetTblStyle()
    {
    }


    /**
     * Get style
     * @param getbfr The getbuffer
     * @param tbl The Style table
     */
    static public
    void get(YdxfGetBuffer getbfr, YxxfTblStyle tbl)
    {
        getbfr.get();
        while (true)
        {
            int cod = getbfr.getCodValue();

            if (cod == 0)
                break;

            if (cod == 2)
            {
                tbl.setName(getbfr.chararrValue());
            } else

            if (cod == 5)
            {
                tbl.handle = getbfr.stringValue();
            } else

            if (cod == 70)
            {
                tbl.flags = getbfr.intValue();
            } else

            if (cod == 40)
            {
                tbl.height = getbfr.doubleValue();
            } else

            if (cod == 41)
            {
                tbl.widthfactor = getbfr.doubleValue();
            } else

            if (cod == 50)
            {
                tbl.obliqueang = getbfr.doubleValue();
            } else

            if (cod == 71)
            {
                tbl.textgenflags = getbfr.intValue();
            } else

            if (cod == 43)
            {
                tbl.lastheight = getbfr.doubleValue();
            } else

            if (cod == 3)
            {
                tbl.fontfilename = getbfr.stringValue();
            } else

            if (cod == 4)
            {
                tbl.bigfontfilename = getbfr.stringValue();
            }

            getbfr.get();
        }
    }
}



