//==============================================================================
// YdxfPut.java
//
// Main drawing output
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/ydxf/YdxfPut.java,v 1.8 2003/05/08 09:38:16 ekarlo Exp $
// $Log: YdxfPut.java,v $
// Revision 1.8  2003/05/08 09:38:16  ekarlo
// Remove warnings.
//
// Revision 1.7  2003/04/14 12:37:55  ekarlo
// Update source file header for OSI release.
//
// Revision 1.6  2000/10/17 07:43:36  ekarlo
// Change package paths to lower case.
//
// Revision 1.5  1999-10-06 19:49:35-06  walter
// Added JavaDoc comments.
//
// Revision 1.4  1999-07-09 14:09:43-06  ekarlo
// Rearrange package names.
//
// Revision 1.3  1999/06/27  08:28:14  ekarlo
// Rename class.
//
// Revision 1.2  1999/06/20  22:32:38  ekarlo
// Rearrange package names.
//
// Revision 1.1  1999/01/28  04:19:23  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.ydxf;


import com.ysystems.ycad.lib.yxxf.*;


/**
 * Main drawing output.
 * This class may not be instantiated.
 */
public class YdxfPut
{
    /**
     * Constructor to prevent instantiation.
     */
    private // Defeat instantiation
    YdxfPut()
    {
    }


    //==========================================================================
    /**
     * Output the Drawing.
     * @param putbfr The Drawing elements to be output.
     * @param D The Drawing.
     */
    static public
    void put(YdxfPutBuffer putbfr, Yxxf D)
    {
        YdxfPutSecHeader.put(putbfr, D);

//p     YdxfPutSecTables.put(putbfr, D);

        YdxfPutSecBlocks.put(putbfr, D);

//p     YdxfPutSecEntities.put(putbfr, D);

        putbfr.putKeywordValue(0, YdxfKeyword.KW_C_EOF);
    }
    //==========================================================================
}

