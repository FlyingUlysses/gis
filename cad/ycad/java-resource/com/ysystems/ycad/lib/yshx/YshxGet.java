//==============================================================================
// YshxGet.java
//
// Main shx get.
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yshx/YshxGet.java,v 1.11 2003/05/08 09:38:17 ekarlo Exp $
// $Log: YshxGet.java,v $
// Revision 1.11  2003/05/08 09:38:17  ekarlo
// Remove warnings.
//
// Revision 1.10  2003/04/14 12:38:24  ekarlo
// Update source file header for OSI release.
//
// Revision 1.9  2000/10/17 07:44:12  ekarlo
// Change package paths to lower case.
//
// Revision 1.8  1999-10-25 11:03:51-06  ekarlo
// Implement shx shapes 1.0 (like bold.shx).
//
// Revision 1.7  1999-09-15 10:15:33-06  walter
// Added JavaDoc comments.
//
// Revision 1.6  1999-08-10 08:03:24-06  ekarlo
// Remove unused var warning.
//
// Revision 1.5  1999/07/09  20:09:02  ekarlo
// Rearrange package names.
//
// Revision 1.4  1999/06/20  22:33:20  ekarlo
// Rearrange package names.
//
// Revision 1.3  1999/02/09  14:48:33  ekarlo
// Deactivate console print.
//
// Revision 1.2  1999/02/08  04:46:38  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.1  1999/01/28  04:22:14  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yshx;


import com.ysystems.ycad.lib.yxxf.*;


/**
 * Main shx get.
 * This class may not be instantiated.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YshxGet
{
    /**
     * Constructor to defeat instantiation
     */
    private 
    YshxGet()
    {
    }


    //==========================================================================
    /**
     * Get it
     * @param getbfr TODO
     * @param shape TODO
     */
    static public
    void get(YshxGetBuffer getbfr, YxxfShape shape)
    {
        // Work values
        char c;
        StringBuffer sb = new StringBuffer(256);


        //
        // Get shape id
        //


        // hdr_id
        // accumulate until newline or eof
        sb.setLength(0);
        while (true)
        {
            c = getbfr.getc();
            if (getbfr.eof())
                break;
            if (c == '\n')
                break;
            if (c != '\r')
                sb.append(c);
        }
        shape.hdr_id = new String(sb);


        // hdr_c1
        if (!getbfr.eof())
        {
            shape.hdr_c1 = getbfr.getc();
        }


        //
        // get remainder
        //
        if (shape.hdr_id.equalsIgnoreCase("AutoCAD-86 shapes 1.0"))
        {
            get_shapes_1_x(getbfr, shape);
        }
        else
        if (shape.hdr_id.equalsIgnoreCase("AutoCAD-86 shapes 1.1"))
        {
            get_shapes_1_x(getbfr, shape);
        }
        else
        if (shape.hdr_id.equalsIgnoreCase("AutoCAD-86 unifont 1.0"))
        {
            get_unifont_1_0(getbfr, shape);
        }
        else
        {
            System.out.println("++YshxGet:unknown shape type [" + shape.hdr_id + "]");
        }


        //
        // End it
        //

        getbfr.close();
    }
    //==========================================================================


    //==========================================================================
    /**
     * Get type shapes 1.0
     * @param getbfr TODO
     * @param shape TODO
     */
    static public
    void get_shapes_1_x(YshxGetBuffer getbfr, YxxfShape shape)
    {
        // Work values
        char c, c1, c2, c3, c4;

        // hdr_int1
        c1 = getbfr.getc();
        if (getbfr.eof())
            return;
        c2 = getbfr.getc();
        if (getbfr.eof())
            return;
        shape.hdr_int1 = (int)c1 + ((int)c2 << 8);


        // hdr_int2
        c1 = getbfr.getc();
        if (getbfr.eof())
            return;
        c2 = getbfr.getc();
        if (getbfr.eof())
            return;
        shape.hdr_int2 = (int)c1 + ((int)c2 << 8);


        // hdr_size
        c1 = getbfr.getc();
        if (getbfr.eof())
            return;
        c2 = getbfr.getc();
        if (getbfr.eof())
            return;
        shape.hdr_size = (int)c1 + ((int)c2 << 8);


        // Create and store element desc array
        char    chararr[]       = new char[shape.hdr_size];
        int     elemlenarr[]    = new int[shape.hdr_size];
        for (int sizeidx = 0; sizeidx < shape.hdr_size; sizeidx++)
        {
            // get 4 char element desc
            c1 = getbfr.getc();
            if (getbfr.eof())
                return;
            c2 = getbfr.getc();
            if (getbfr.eof())
                return;
            c3 = getbfr.getc();
            if (getbfr.eof())
                return;
            c4 = getbfr.getc();
            if (getbfr.eof())
                return;

            chararr[sizeidx]    = (char)((char)c1 | ((char)c2 << 8));
            elemlenarr[sizeidx] = (int)c3 + ((int)c4 << 8);
        }


        //
        // Get shape elements
        //

        //
        // Get shape descriptions
        //

        StringBuffer sbdesc = new StringBuffer();
        StringBuffer sbgeom = new StringBuffer(256);
        for (int sizeidx = 0; sizeidx < shape.hdr_size; sizeidx++)
        {
            // get element
            int elemlen = elemlenarr[sizeidx];
            int nullidx = -1; // record position of first null
            sbdesc.setLength(0);
            sbgeom.setLength(0);

            // Accumulate desc until null then accumlate geom

            // get elemlen chars
            for (int elemidx = 0; elemidx < elemlen; elemidx++)
            {
                c = getbfr.getc();
                if (getbfr.eof())
                    return;

                if (nullidx != -1) // null already encountered
                {   // accumulate geom
                    sbgeom.append(c);
                }
                else
                {   // accumulate desc
                    if (c == '\0')
                    {
                        if (nullidx == -1)
                            nullidx = elemidx;
                    }
                    else
                        sbdesc.append(c);
                }
            }

            // create shape element and add to hash - same elem is both key and val
            char[] geomarr = new char[sbgeom.length()];
            sbgeom.getChars(0, sbgeom.length(), geomarr, 0);
            YxxfShapeChar elem = new YxxfShapeChar(chararr[sizeidx],
                                                   sbdesc.toString(),
                                                   geomarr);
            shape.elem.put(elem, elem);
        }
    }
    //==========================================================================


    //==========================================================================
    /**
     * Get type unifont 1.0
     * @param getbfr TODO
     * @param shape TODO
     */
    static public
    void get_unifont_1_0(YshxGetBuffer getbfr, YxxfShape shape)
    {
        // Work values
        char c, c1, c2, c3, c4;

        // hdr_size
        // 1 more than actual number of elements
        c1 = getbfr.getc();
        if (getbfr.eof())
            return;
        c2 = getbfr.getc();
        if (getbfr.eof())
            return;
        shape.hdr_size = (int)c1 + ((int)c2 << 8);

    
        //
        // Get shape descriptions
        //

        StringBuffer sbdesc = new StringBuffer();
        StringBuffer sbgeom = new StringBuffer(256);
        for (int sizeidx = 0; sizeidx < shape.hdr_size; sizeidx++)
        {
            // get 4 char element desc
            c1 = getbfr.getc();
            if (getbfr.eof())
                return;
            c2 = getbfr.getc();
            if (getbfr.eof())
                return;
            c3 = getbfr.getc();
            if (getbfr.eof())
                return;
            c4 = getbfr.getc();
            if (getbfr.eof())
                return;

            // get element
            int elemlen = (int)c3 + ((int)c4 << 8);
            int nullidx = -1; // record position of first null
            sbdesc.setLength(0);
            sbgeom.setLength(0);

            // Accumulate desc until null then accumlate geom

            // get elemlen chars
            for (int elemidx = 0; elemidx < elemlen; elemidx++)
            {
                c = getbfr.getc();
                if (getbfr.eof())
                    return;

                if (nullidx != -1) // null already encountered
                {   // accumulate geom
                    sbgeom.append(c);
                }
                else
                {   // accumulate desc
                    if (c == '\0')
                    {
                        if (nullidx == -1)
                            nullidx = elemidx;
                    }
                    else
                        sbdesc.append(c);
                }
            }

            // create shape element and add to hash - same elem is both key and val
            char[] geomarr = new char[sbgeom.length()];
            sbgeom.getChars(0, sbgeom.length(), geomarr, 0);
            YxxfShapeChar elem = new YxxfShapeChar((char)((char)c1 | ((char)c2 << 8)),
                                                   sbdesc.toString(),
                                                   geomarr);
            shape.elem.put(elem, elem);
        }
    }
    //==========================================================================
}

