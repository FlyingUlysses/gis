//==============================================================================
// YdxfGetEntTrace.java
//
// Get TRACE from DXF file
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/ydxf/YdxfGetEntTrace.java,v 1.3 2003/04/14 12:37:55 ekarlo Exp $
// $Log: YdxfGetEntTrace.java,v $
// Revision 1.3  2003/04/14 12:37:55  ekarlo
// Update source file header for OSI release.
//
// Revision 1.2  2000/10/17 07:44:23  ekarlo
// Change package paths to lower case.
//
// Revision 1.1  1999-10-25 17:52:57-06  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.ydxf;


import com.ysystems.ycad.lib.yxxf.*;


/**
 * Get TRACE from DXF file.
 * This class may not be instantiated.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YdxfGetEntTrace
{
    /**
     * Constructor to prevent instantiation.
     */
    private // Defeat instantiation
    YdxfGetEntTrace()
    {
    }


    /**
     * Get the Trace
     * @param getbfr The Get buffer.
     * @param ent The Trace.
     */
    static public
    void get(YdxfGetBuffer getbfr, YxxfEntTrace ent)
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
                ent.pnt1.x = getbfr.doubleValue();
            } else

            if (cod == 20)
            {
                ent.pnt1.y = getbfr.doubleValue();
            } else

            if (cod == 30)
            {
                ent.pnt1.z = getbfr.doubleValue();
            } else

            if (cod == 11)
            {
                ent.pnt2.x = getbfr.doubleValue();
            } else

            if (cod == 21)
            {
                ent.pnt2.y = getbfr.doubleValue();
            } else

            if (cod == 31)
            {
                ent.pnt2.z = getbfr.doubleValue();
            } else

            if (cod == 12)
            {
                ent.pnt3.x = getbfr.doubleValue();
            } else

            if (cod == 22)
            {
                ent.pnt3.y = getbfr.doubleValue();
            } else

            if (cod == 32)
            {
                ent.pnt3.z = getbfr.doubleValue();
            } else

            if (cod == 13)
            {
                ent.pnt4.x = getbfr.doubleValue();
            } else

            if (cod == 23)
            {
                ent.pnt4.y = getbfr.doubleValue();
            } else

            if (cod == 33)
            {
                ent.pnt4.z = getbfr.doubleValue();
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

