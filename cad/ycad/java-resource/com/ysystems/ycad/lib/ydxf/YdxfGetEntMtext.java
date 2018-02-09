//==============================================================================
// YdxfGetEntMtext.java
//
// Get MTEXT from DXF file
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/ydxf/YdxfGetEntMtext.java,v 1.4 2003/04/14 12:37:55 ekarlo Exp $
// $Log: YdxfGetEntMtext.java,v $
// Revision 1.4  2003/04/14 12:37:55  ekarlo
// Update source file header for OSI release.
//
// Revision 1.3  2002/09/12 20:03:27  ekarlo
// MTEXT development check-in.
//
// Revision 1.2  2000-10-17 01:44:26-06  ekarlo
// Change package paths to lower case.
//
// Revision 1.1  1999-10-26 10:21:42-06  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.ydxf;


import com.ysystems.ycad.lib.yxxf.*;


/**
 * Get MTEXT from DXF file.
 * This class may not be instantiated.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YdxfGetEntMtext
{
    /**
     * Constructor to prevent instantiation.
     */
    private // Defeat instantiation
    YdxfGetEntMtext()
    {
    }


    /**
     * Get mtext
     * @param getbfr The Get buffer.
     * @param ent The Mtext.
     */
    static public
    void get(YdxfGetBuffer getbfr, YxxfEntMtext ent)
    {
        int gctextcnt = 0;

        getbfr.get();
        while (true)
        {
            int cod = getbfr.getCodValue();

            if (cod == 0)
                break;

            if (YdxfGetEntHeader.get(getbfr, ent))
            {
            } else

            if (cod == 1 || cod == 3)
            {
                if (gctextcnt++ == 0)
                    ent.text  = getbfr.stringValue(); // initialize
                else
                    ent.text += getbfr.stringValue(); // cat
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

            if (cod == 11)
            {
                ent.xaxisdir.x = getbfr.doubleValue();
            } else

            if (cod == 21)
            {
                ent.xaxisdir.y = getbfr.doubleValue();
            } else

            if (cod == 31)
            {
                ent.xaxisdir.z = getbfr.doubleValue();
            } else

            if (cod == 12)
            {
                ent.yaxisdir.x = getbfr.doubleValue();
            } else

            if (cod == 22)
            {
                ent.yaxisdir.y = getbfr.doubleValue();
            } else

            if (cod == 32)
            {
                ent.yaxisdir.z = getbfr.doubleValue();
            } else

            if (cod == 40)
            {
                ent.height = getbfr.doubleValue();
            } else

            if (cod == 41)
            {
                ent.refrectwidth = getbfr.doubleValue();
            } else

            if (cod == 42)
            {
                ent.actualwidth = getbfr.doubleValue();
            } else

            if (cod == 43)
            {
                ent.actualheight = getbfr.doubleValue();
            } else

            if (cod == 44)
            {
                ent.linespacingfactor = getbfr.doubleValue();
            } else

            if (cod == 50)
            {
                ent.rotang = getbfr.doubleValue();
            } else

            if (cod == 7)
            {
                ent.style = getbfr.styleValue();
            } else

            if (cod == 71)
            {
                ent.attachpointflag = getbfr.intValue();
            } else

            if (cod == 72)
            {
                ent.drawdirflag = getbfr.intValue();
            } else

            if (cod == 73)
            {
                ent.linespacingflag = getbfr.intValue();
            }

            getbfr.get();
        }
    }
}



