//==============================================================================
// YdxfGetEntPolyline.java
//
// Get POLYLINE from DXF file
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/ydxf/YdxfGetEntPolyline.java,v 1.18 2003/04/14 12:37:55 ekarlo Exp $
// $Log: YdxfGetEntPolyline.java,v $
// Revision 1.18  2003/04/14 12:37:55  ekarlo
// Update source file header for OSI release.
//
// Revision 1.17  2001/10/10 03:37:27  ekarlo
// Update copyright.
//
// Revision 1.16  2000-10-17 01:44:25-06  ekarlo
// Change package paths to lower case.
//
// Revision 1.15  1999-10-21 22:14:24-06  walter
// Added JavaDoc comments.
//
// Revision 1.14  1999-07-09 14:09:43-06  ekarlo
// Rearrange package names.
//
// Revision 1.13  1999/06/27  08:28:14  ekarlo
// Rename class.
//
// Revision 1.12  1999/06/20  22:32:38  ekarlo
// Rearrange package names.
//
// Revision 1.11  1999/02/08  04:57:28  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.10  1998/12/22  14:42:54  ekarlo
// Renames - add "Get" to name.
//
// Revision 1.9  1998/07/12  00:00:47  ekarlo
// Add width coded flags.
//
// Revision 1.8  1998/02/12  17:47:51  ekarlo
// Implement polyface mesh.
//
// Revision 1.7  1997/07/21  22:26:46  ekarlo
// Make static get.
//
// Revision 1.6  1997/07/13  18:06:18  ekarlo
// MVC-VH rework - phase 1.
// Rename get class.
//
// Revision 1.5  1996/09/14  03:26:01  ekarlo
// Connect layer in add entity methods.
//
// Revision 1.4  1996/09/13  06:16:24  ekarlo
// Use new base entity class structure.
//
// Revision 1.3  1996/08/18  02:27:52  ekarlo
// Begin change to use new transmat - use point class.
//
// Revision 1.2  1996/07/30  06:20:44  ekarlo
// Use point class.
//
// Revision 1.1  1996/07/02  01:58:25  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.ydxf;


import com.ysystems.ycad.lib.yxxf.*;


/**
 * Get POLYLINE from DXF file.
 * This class may not be instantiated.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YdxfGetEntPolyline
{
    /**
     * Constructor to prevent instantiation.
     */
    private // Defeat instantiation
    YdxfGetEntPolyline()
    {
    }


    /**
     * Get polyline
     * @param getbfr The Get buffer.
     * @param The Polyline.
     */
    static public
    void get(YdxfGetBuffer getbfr, YxxfEntPolyline ent)
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
                ent.pnt.x = getbfr.doubleValue();
            } else

            if (cod == 20)
            {
                ent.pnt.y = getbfr.doubleValue();
            } else

            if (cod == 30)
            {
                ent.pnt.z = getbfr.doubleValue();
            } else

            if (cod == 39)
            {
                ent.thickness = getbfr.doubleValue();
            } else

            if (cod == 40)
            {
                ent.begwidth = getbfr.doubleValue();
                ent.begwidth_set = true;
            } else

            if (cod == 41)
            {
                ent.endwidth = getbfr.doubleValue();
                ent.endwidth_set = true;
            } else

            if (cod == 66)
            {
                ent.vtxFollow = getbfr.intValue();
            } else

            if (cod == 70)
            {
                ent.flags = getbfr.intValue();
                
                // If this polyline is a polyface mesh then add a
                // dummy vertex to occupy index 0.
                if ((ent.flags & 64) == 64)
                {
                    if (ent.getVtxFacesSize() == 0)
                    {
                        YxxfEntVertex vtx = new YxxfEntVertex();
                        vtx.flags = 64 + 128;
                        ent.addVertex(vtx);
                    }
                }
            } else

            if (cod == 71)
            {
                ent.meshcntM = getbfr.intValue();
            } else

            if (cod == 72)
            {
                ent.meshcntN = getbfr.intValue();
            } else

            if (cod == 73)
            {
                ent.smoothM = getbfr.intValue();
            } else

            if (cod == 74)
            {
                ent.smoothN = getbfr.intValue();
            } else

            if (cod == 75)
            {
                ent.surfType = getbfr.intValue();
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

