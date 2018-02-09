//==============================================================================
// YdxfGetEntDimension.java
//
// Get DIMENSION from DXF file
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/ydxf/YdxfGetEntDimension.java,v 1.7 2003/04/14 12:37:55 ekarlo Exp $
// $Log: YdxfGetEntDimension.java,v $
// Revision 1.7  2003/04/14 12:37:55  ekarlo
// Update source file header for OSI release.
//
// Revision 1.6  2000/10/17 07:44:28  ekarlo
// Change package paths to lower case.
//
// Revision 1.5  1999-10-21 22:17:25-06  walter
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
// Revision 1.1  1999/02/08  05:01:16  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.ydxf;


import com.ysystems.ycad.lib.yxxf.*;


/**
 * Get DIMENSION from DXF file.
 * This class may not be instantiated.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YdxfGetEntDimension
{
    /**
     * Constructor to prevent instantiation.
     */
    private // Defeat instantiation
    YdxfGetEntDimension()
    {
    }


    /**
     * Get the Dimension.
     * @param getbfr The Get buffer.
     * @param ent The Dimension.
     */
    static public
    void get(YdxfGetBuffer getbfr, YxxfEntDimension ent)
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

            if (cod == 3)
            {
                ent.dimstylename = getbfr.stringValue();
            } else

            if (cod == 10)
            {
                ent.defpnt.x = getbfr.doubleValue();
            } else
            if (cod == 20)
            {
                ent.defpnt.y = getbfr.doubleValue();
            } else
            if (cod == 30)
            {
                ent.defpnt.z = getbfr.doubleValue();
            } else

            if (cod == 11)
            {
                ent.midpnt.x = getbfr.doubleValue();
            } else
            if (cod == 21)
            {
                ent.midpnt.y = getbfr.doubleValue();
            } else
            if (cod == 31)
            {
                ent.midpnt.z = getbfr.doubleValue();
            } else

            if (cod == 12)
            {
                ent.insclonepnt.x = getbfr.doubleValue();
            } else

            if (cod == 22)
            {
                ent.insclonepnt.y = getbfr.doubleValue();
            } else

            if (cod == 32)
            {
                ent.insclonepnt.z = getbfr.doubleValue();
            } else

            if (cod == 70)
            {
                ent.flags = getbfr.intValue();
            } else

            if (cod == 1)
            {
                ent.dimtextuser = getbfr.stringValue();
            } else

            if (cod == 13)
            {
                ent.deflinangpnt1.x = getbfr.doubleValue();
            } else
            if (cod == 23)
            {
                ent.deflinangpnt1.y = getbfr.doubleValue();
            } else
            if (cod == 33)
            {
                ent.deflinangpnt1.z = getbfr.doubleValue();
            } else

            if (cod == 15)
            {
                ent.defdiaradangpnt.x = getbfr.doubleValue();
            } else
            if (cod == 25)
            {
                ent.defdiaradangpnt.y = getbfr.doubleValue();
            } else
            if (cod == 35)
            {
                ent.defdiaradangpnt.z = getbfr.doubleValue();
            } else

            if (cod == 16)
            {
                ent.defarcangpnt.x = getbfr.doubleValue();
            } else
            if (cod == 26)
            {
                ent.defarcangpnt.y = getbfr.doubleValue();
            } else
            if (cod == 36)
            {
                ent.defarcangpnt.z = getbfr.doubleValue();
            } else

            if (cod == 40)
            {
                ent.leaderlen = getbfr.doubleValue();
            } else

            if (cod == 50)
            {
                ent.dimang = getbfr.doubleValue();
            } else

            if (cod == 51)
            {
                ent.horzang = getbfr.doubleValue();
            } else

            if (cod == 52)
            {
                ent.extang = getbfr.doubleValue();
            } else

            if (cod == 53)
            {
                ent.textang = getbfr.doubleValue();
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


