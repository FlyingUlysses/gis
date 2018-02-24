//==============================================================================
// YdxfPutEntLine.java
//
// Put LINE entity
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/ydxf/YdxfPutEntLine.java,v 1.7 2003/04/14 12:37:55 ekarlo Exp $
// $Log: YdxfPutEntLine.java,v $
// Revision 1.7  2003/04/14 12:37:55  ekarlo
// Update source file header for OSI release.
//
// Revision 1.6  2000/10/17 07:44:14  ekarlo
// Change package paths to lower case.
//
// Revision 1.5  1999-10-06 19:50:04-06  walter
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
 * Put LINE entity.
 * This class may not be instantiated.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YdxfPutEntLine
{
    /**
     * Constructor to prevent instantiation.
     */
    private // Defeat instantiation
    YdxfPutEntLine()
    {
    }


    /**
     * Put Line Entity into the put buffer.
     * @param putbfr The put buffer.
     * @param ent The Line Entity.
     */
    static public
    void put(YdxfPutBuffer putbfr, YxxfEntLine ent)
    {
        putbfr.putKeywordValue (  0, YdxfKeyword.KW_C_LINE);


        if (ent.begpnt.x != 0.0)
        {
            putbfr.putDoubleValue  ( 10, ent.begpnt.x);
        }
        if (ent.begpnt.y != 0.0)
        {
            putbfr.putDoubleValue  ( 20, ent.begpnt.y);
        }
        if (ent.begpnt.z != 0.0)
        {
            putbfr.putDoubleValue  ( 30, ent.begpnt.z);
        }

        if (ent.endpnt.x != 0.0)
        {
            putbfr.putDoubleValue  ( 11, ent.endpnt.x);
        }
        if (ent.endpnt.y != 0.0)
        {
            putbfr.putDoubleValue  ( 21, ent.endpnt.y);
        }
        if (ent.endpnt.z != 0.0)
        {
            putbfr.putDoubleValue  ( 31, ent.endpnt.z);
        }
    }
}

