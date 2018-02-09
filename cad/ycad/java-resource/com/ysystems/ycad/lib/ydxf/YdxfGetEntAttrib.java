//==============================================================================
// YdxfGetEntAttrib.java
//
// Get ATTRIB from DXF file
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/ydxf/YdxfGetEntAttrib.java,v 1.8 2003/04/14 12:37:55 ekarlo Exp $
// $Log: YdxfGetEntAttrib.java,v $
// Revision 1.8  2003/04/14 12:37:55  ekarlo
// Update source file header for OSI release.
//
// Revision 1.7  2000/10/17 07:44:29  ekarlo
// Change package paths to lower case.
//
// Revision 1.6  1999-10-21 22:19:03-06  walter
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
// Revision 1.2  1999/02/08  04:58:27  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.1  1999/01/28  04:12:21  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.ydxf;


import com.ysystems.ycad.lib.yxxf.*;


/**
 * Get ATTRIB from DXF file.
 * This class may not be instantiated.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YdxfGetEntAttrib
{
    /**
     * Constructor to prevent instantiation.
     */
    private // Defeat instantiation
    YdxfGetEntAttrib()
    {
    }


    /**
     * Get the Attrib
     * @param getbfr The Get buffer.
     * @param ent The ATTRIB.
     */
    static public
    void get(YdxfGetBuffer getbfr, YxxfEntAttrib ent)
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

            if (cod == 40)
            {
                ent.height = getbfr.doubleValue();
            } else

            if (cod == 1)
            {
                ent.text = getbfr.stringValue();
            } else

            if (cod == 2)
            {
                ent.tag = getbfr.stringValue();
            } else

            if (cod == 70)
            {
                ent.flags = getbfr.intValue();
            } else

            if (cod == 73)
            {
                ent.fieldlen = getbfr.intValue();
            } else

            if (cod == 50)
            {
                ent.rotang = getbfr.doubleValue();
            } else

            if (cod == 41)
            {
                ent.relxscale = getbfr.doubleValue();
            } else

            if (cod == 51)
            {
                ent.obliqueang = getbfr.doubleValue();
            } else

            if (cod == 7)
            {
                ent.style = getbfr.styleValue();
            } else

            if (cod == 71)
            {
                ent.textgenflags = getbfr.intValue();
            } else

            if (cod == 72)
            {
                ent.horzalignflags = getbfr.intValue();
            } else

            if (cod == 74)
            {
                ent.vertalignflags = getbfr.intValue();
            } else


            if (cod == 11)
            {
                ent.alnpnt.x = getbfr.doubleValue();
            } else

            if (cod == 21)
            {
                ent.alnpnt.y = getbfr.doubleValue();
            } else

            if (cod == 31)
            {
                ent.alnpnt.z = getbfr.doubleValue();
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



