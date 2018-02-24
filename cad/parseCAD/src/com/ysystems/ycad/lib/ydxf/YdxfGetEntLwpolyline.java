//==============================================================================
// YdxfGetEntLwpolyline.java
//
// Get LWPOLYLINE from DXF file
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/ydxf/YdxfGetEntLwpolyline.java,v 1.4 2003/04/14 12:37:55 ekarlo Exp $
// $Log: YdxfGetEntLwpolyline.java,v $
// Revision 1.4  2003/04/14 12:37:55  ekarlo
// Update source file header for OSI release.
//
// Revision 1.3  2000/10/17 07:44:26  ekarlo
// Change package paths to lower case.
//
// Revision 1.2  1999-09-22 22:58:33-06  walter
// Added JavaDoc comments.
//
// Revision 1.1  1999-08-07 14:20:23-06  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.ydxf;


import com.ysystems.ycad.lib.yxxf.*;


/**
 * Get LWPOLYLINE from DXF file.
 * This class may not be instantiated.
 * Ed Karlo - Y Systems, LLC
 */
public class YdxfGetEntLwpolyline
{
    /**
     * Constructor - may not be instantiated.
     */
    private // Defeat instantiation
    YdxfGetEntLwpolyline()
    {
    }


    /**
     * Get lwpolyline (fills internal polyline entity).
     * @param getbfr The buffer containing all possible types of drawing
     * information.
     * @param end The lightweight PolyLine.
     */
    static public
    void get(YdxfGetBuffer getbfr, YxxfEntLwpolyline ent)
    {
        // work vertex entity
        YxxfEntVertex               vtxEnt = null;

        getbfr.get();
        while (true)
        {
            int cod = getbfr.getCodValue();

            if (cod == 0)
            {
                if (vtxEnt != null) // last one
                {
                    vtxEnt.pnt.z = ent.pline.pnt.z;
                    ent.pline.addVertex(vtxEnt);
                }
                ent.copyHeaderInto(ent.pline);
                break;
            }

            if (YdxfGetEntHeader.get(getbfr, ent))
            {
            } else

            if (cod == 10)
            {
                if (vtxEnt != null)
                {
                    vtxEnt.pnt.z = ent.pline.pnt.z;
                    ent.pline.addVertex(vtxEnt);
                    vtxEnt = null;
                }

                if (vtxEnt == null)
                {
                    vtxEnt = createVertex(ent);
                }
                vtxEnt.pnt.x = getbfr.doubleValue();
            } else

            if (cod == 20)
            {
                if (vtxEnt == null)
                {
                    vtxEnt = createVertex(ent);
                }
                vtxEnt.pnt.y = getbfr.doubleValue();
            } else

            if (cod == 40)
            {
                if (vtxEnt == null)
                {
                    vtxEnt = createVertex(ent);
                }
                vtxEnt.begwidth = getbfr.doubleValue();
                vtxEnt.begwidth_set = true;
            } else

            if (cod == 41)
            {
                if (vtxEnt == null)
                {
                    vtxEnt = createVertex(ent);
                }
                vtxEnt.endwidth = getbfr.doubleValue();
                vtxEnt.endwidth_set = true;
            } else

            if (cod == 42)
            {
                if (vtxEnt == null)
                {
                    vtxEnt = createVertex(ent);
                }
                vtxEnt.bulge = getbfr.doubleValue();
            } else

            if (cod == 38)
            {
                ent.pline.pnt.z = getbfr.doubleValue();
            } else

            if (cod == 39)
            {
                ent.pline.thickness = getbfr.doubleValue();
            } else

            if (cod == 43)
            {
                ent.constwidth = getbfr.doubleValue();
                ent.constwidth_set = true;
                ent.pline.begwidth = ent.constwidth;
                ent.pline.begwidth_set = true;
                ent.pline.endwidth = ent.constwidth;
                ent.pline.endwidth_set = true;
            } else

            if (cod == 70)
            {
                ent.pline.flags = getbfr.intValue();
            } else

            if (cod == 90)
            {
                ent.numvertices = getbfr.intValue();
            } else

            if (cod == 210)
            {
                ent.pline.xtruDir.x = getbfr.doubleValue();
            } else

            if (cod == 220)
            {
                ent.pline.xtruDir.y = getbfr.doubleValue();
            } else

            if (cod == 230)
            {
                ent.pline.xtruDir.z = getbfr.doubleValue();
            }

            getbfr.get();
        }
    }


    /**
     * Create a vertex.
     * @param hdr The container of common entity header items.
     * @return The vertex.
     */
    static private
    YxxfEntVertex createVertex(YxxfEntHeader hdr)
    {
        YxxfEntVertex vtx = new YxxfEntVertex();
        hdr.copyHeaderInto(vtx);
        return vtx;
    }
}

