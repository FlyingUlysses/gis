//==============================================================================
// YdxfGetEntViewport.java
//
// Get VIEWPORT from DXF file
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/ydxf/YdxfGetEntViewport.java,v 1.10 2003/04/14 12:37:55 ekarlo Exp $
// $Log: YdxfGetEntViewport.java,v $
// Revision 1.10  2003/04/14 12:37:55  ekarlo
// Update source file header for OSI release.
//
// Revision 1.9  2000/10/17 07:44:22  ekarlo
// Change package paths to lower case.
//
// Revision 1.8  1999-10-21 22:11:51-06  walter
// Added JavaDoc comments.
//
// Revision 1.7  1999-07-09 14:09:43-06  ekarlo
// Rearrange package names.
//
// Revision 1.6  1999/07/05  00:08:57  ekarlo
// Fix comment.
//
// Revision 1.5  1999/06/27  08:28:14  ekarlo
// Rename class.
//
// Revision 1.4  1999/06/20  22:32:38  ekarlo
// Rearrange package names.
//
// Revision 1.3  1999/02/08  04:56:30  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.2  1998/12/22  14:43:07  ekarlo
// Renames - add "Get" to name.
//
// Revision 1.1  1997/12/26  21:22:20  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.ydxf;


import com.ysystems.ycad.lib.yxxf.*;


/**
 * Get VIEWPORT from DXF file.
 * This class may not be instantiated.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YdxfGetEntViewport
{
    /**
     * Constructor to prevent instantiation.
     */
    private // Defeat instantiation
    YdxfGetEntViewport()
    {
    }


    /**
     * Get viewport
     * @param getbfr The Get Buffer.
     * @param ent The Viewport.
     */
    static public
    void get(YdxfGetBuffer getbfr, YxxfEntViewport ent)
    {
        int gc1010cnt = 0;
        int gc1020cnt = 0;
        int gc1030cnt = 0;
        int gc1040cnt = 0;
        int gc1070cnt = 0;
        
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
                ent.vpcp.x = getbfr.doubleValue();
            } else

            if (cod == 20)
            {
                ent.vpcp.y = getbfr.doubleValue();
            } else

            if (cod == 30)
            {
                ent.vpcp.z = getbfr.doubleValue();
            } else

            if (cod == 40)
            {
                ent.vpwidth = getbfr.doubleValue();
            } else

            if (cod == 41)
            {
                ent.vpheight = getbfr.doubleValue();
            } else

            if (cod == 68)
            {
                ent.vpstatus = getbfr.intValue();
            } else

            if (cod == 69)
            {
                ent.vpid = getbfr.intValue();
            } else

            if (cod == 1010)
            {
                if (gc1010cnt == 0)
                {
                    ent.vtgt.x = getbfr.doubleValue(); gc1010cnt++;
                } else

                if (gc1010cnt == 1)
                {
                    ent.vdir.x = getbfr.doubleValue(); gc1010cnt++;
                }
            } else

            if (cod == 1020)
            {
                if (gc1020cnt == 0)
                {
                    ent.vtgt.y = getbfr.doubleValue(); gc1020cnt++;
                } else

                if (gc1020cnt == 1)
                {
                    ent.vdir.y = getbfr.doubleValue(); gc1020cnt++;
                }
            } else

            if (cod == 1030)
            {
                if (gc1030cnt == 0)
                {
                    ent.vtgt.z = getbfr.doubleValue(); gc1030cnt++;
                } else

                if (gc1030cnt == 1)
                {
                    ent.vdir.z = getbfr.doubleValue(); gc1030cnt++;
                }
            } else
            
            if (cod == 1040)
            {
                if (gc1040cnt == 0)
                {
                    ent.viewtwistang = getbfr.doubleValue(); gc1040cnt++;
                } else

                if (gc1040cnt == 1)
                {
                    ent.vheight = getbfr.doubleValue(); gc1040cnt++;
                } else

                if (gc1040cnt == 2)
                {
                    ent.vcpx = getbfr.doubleValue(); gc1040cnt++;
                } else

                if (gc1040cnt == 3)
                {
                    ent.vcpy = getbfr.doubleValue(); gc1040cnt++;
                } else

                if (gc1040cnt == 4)
                {
                    ent.lensLength = getbfr.doubleValue(); gc1040cnt++;
                } else

                if (gc1040cnt == 5)
                {
                    ent.fcp = getbfr.doubleValue(); gc1040cnt++;
                } else

                if (gc1040cnt == 6)
                {
                    ent.bcp = getbfr.doubleValue(); gc1040cnt++;
                } else

                if (gc1040cnt == 7)
                {
                    ent.snaprotang = getbfr.doubleValue(); gc1040cnt++;
                } else

                if (gc1040cnt == 8)
                {
                    ent.sbpx = getbfr.doubleValue(); gc1040cnt++;
                } else

                if (gc1040cnt == 9)
                {
                    ent.sbpy = getbfr.doubleValue(); gc1040cnt++;
                } else

                if (gc1040cnt == 10)
                {
                    ent.ssx = getbfr.doubleValue(); gc1040cnt++;
                } else

                if (gc1040cnt == 11)
                {
                    ent.ssy = getbfr.doubleValue(); gc1040cnt++;
                } else

                if (gc1040cnt == 12)
                {
                    ent.gsx = getbfr.doubleValue(); gc1040cnt++;
                } else

                if (gc1040cnt == 13)
                {
                    ent.gsy = getbfr.doubleValue(); gc1040cnt++;
                }
            } else
            
            if (cod == 1070)
            {
                if (gc1070cnt == 0)
                {
                    ent.xdatavers = getbfr.intValue(); gc1070cnt++;
                } else

                if (gc1070cnt == 1)
                {
                    ent.viewmode = getbfr.intValue(); gc1070cnt++;
                } else

                if (gc1070cnt == 2)
                {
                    ent.circleZoomPercent = getbfr.intValue(); gc1070cnt++;
                } else

                if (gc1070cnt == 3)
                {
                    ent.fastZoomSetting = getbfr.intValue(); gc1070cnt++;
                } else

                if (gc1070cnt == 4)
                {
                    ent.ucsicon = getbfr.intValue(); gc1070cnt++;
                } else

                if (gc1070cnt == 5)
                {
                    ent.snaponoff = getbfr.intValue(); gc1070cnt++;
                } else

                if (gc1070cnt == 6)
                {
                    ent.gridonoff = getbfr.intValue(); gc1070cnt++;
                } else

                if (gc1070cnt == 7)
                {
                    ent.snapStyle = getbfr.intValue(); gc1070cnt++;
                } else

                if (gc1070cnt == 8)
                {
                    ent.snapIsopair = getbfr.intValue(); gc1070cnt++;
                }

                if (gc1070cnt == 9)
                {
                    ent.hiddeninplotflag = getbfr.intValue(); gc1070cnt++;
                }
            }
                
            getbfr.get();
        }
    }
}

