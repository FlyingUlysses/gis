//==============================================================================
// YdxfPutEntBlock.java
//
// Put BLOCK entity
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/ydxf/YdxfPutEntBlock.java,v 1.8 2003/04/14 12:37:55 ekarlo Exp $
// $Log: YdxfPutEntBlock.java,v $
// Revision 1.8  2003/04/14 12:37:55  ekarlo
// Update source file header for OSI release.
//
// Revision 1.7  2000/10/17 07:44:15  ekarlo
// Change package paths to lower case.
//
// Revision 1.6  1999-10-06 20:10:44-06  walter
// Added JavaDoc comments.
//
// Revision 1.5  1999-07-09 14:09:43-06  ekarlo
// Rearrange package names.
//
// Revision 1.4  1999/06/27  08:28:14  ekarlo
// Rename class.
//
// Revision 1.3  1999/06/20  22:32:38  ekarlo
// Rearrange package names.
//
// Revision 1.2  1999/02/08  04:53:14  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.1  1999/01/28  04:20:42  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.ydxf;


import com.ysystems.ycad.lib.yxxf.*;


/**
 * Put BLOCK entity.
 * This class may not be instantiated.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YdxfPutEntBlock
{
    /**
     * Constructor disables instantiation.
     */
    private // Defeat instantiation
    YdxfPutEntBlock()
    {
    }


    /**
     * Put the Entity Block into the put buffer.
     * @param putbfr The Put buffer.
     * @param blk The Entity Block.
     */
    static public
    void put(YdxfPutBuffer putbfr, YxxfEntBlock blk)
    {
        putbfr.putKeywordValue (  0, YdxfKeyword.KW_C_BLOCK);
        putbfr.putStringValue  (  2, blk.getBlockname2());


        if (blk.basepnt.x != 0.0)
        {
            putbfr.putDoubleValue  ( 10, blk.basepnt.x);
        }
        if (blk.basepnt.y != 0.0)
        {
            putbfr.putDoubleValue  ( 20, blk.basepnt.y);
        }
        if (blk.basepnt.z != 0.0)
        {
            putbfr.putDoubleValue  ( 30, blk.basepnt.z);
        }

        if (blk.flags != 0)
        {
            putbfr.putIntValue     ( 70, blk.flags);
        }

        // Put entities list
        YxxfEnt ent = null;
        int nextToDraw = 0;

        while (true)
        {
            ent = (YxxfEnt)blk.nextEntity(nextToDraw);

            if (ent == null)
                break;

            if (ent instanceof YxxfEntLine)
            {
                YdxfPutEntLine.put(putbfr, (YxxfEntLine)ent);
            }

            nextToDraw++;
        }


        putbfr.putKeywordValue (  0, YdxfKeyword.KW_C_ENDBLK);
    }
}

