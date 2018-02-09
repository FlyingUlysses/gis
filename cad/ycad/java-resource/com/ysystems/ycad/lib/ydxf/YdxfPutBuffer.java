//==============================================================================
// YdxfPutBuffer.java
//
// Put group code/group value pair
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/ydxf/YdxfPutBuffer.java,v 1.10 2003/05/08 09:38:16 ekarlo Exp $
// $Log: YdxfPutBuffer.java,v $
// Revision 1.10  2003/05/08 09:38:16  ekarlo
// Remove warnings.
//
// Revision 1.9  2003/04/14 12:37:55  ekarlo
// Update source file header for OSI release.
//
// Revision 1.8  2000/10/17 07:44:15  ekarlo
// Change package paths to lower case.
//
// Revision 1.7  1999-10-06 20:11:14-06  walter
// Added JavaDoc comments.
//
// Revision 1.6  1999-07-09 14:09:43-06  ekarlo
// Rearrange package names.
//
// Revision 1.5  1999/06/27  08:26:45  ekarlo
// Rename from YdxfPutGrpCodVal to YdxfPutBuffer.
//
// Revision 1.4  1999/06/20  22:32:38  ekarlo
// Rearrange package names.
//
// Revision 1.3  1999/06/15  04:53:38  ekarlo
// User Interface - phase 1.
//
// Revision 1.2  1999/02/08  04:53:14  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.1  1999/01/28  04:19:23  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.ydxf;


import java.io.*;
import java.util.*;


/**
 * Put group code/group value pair.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YdxfPutBuffer
{
    /**
     * The maximum number of fractional digits in a double TODO.
     */
    private static final int    MAX_FRACTION_DIGITS = 6;
    /**
     * Type of put - Main, Font or Other.
     */
    public int                  put_type            = PUT_TYPE_MAIN;
    /**
     * Main Put
     */
    public static final int     PUT_TYPE_MAIN       = 1;
    /**
     * Font Put
     */
    public static final int     PUT_TYPE_FONT       = 2;
    /**
     * What put is doing right now - Other.
     */
    public static final int     PUT_DOING_OTHER             = 0;
    /**
     * What put is doing right now - Blocks Section
     */
    public static final int     PUT_DOING_BLOCKS_SECTION    = 1;
    /**
     * What put is doing right now - Entity Section.
     */
    public static final int     PUT_DOING_ENTITY_SECTION    = 2;
    /**
     * What the put is doing right now.
     */
    public
    int                         put_doing                   = PUT_DOING_OTHER;
    /**
     * Write buffer.
     */
    private OutputStream        os;
    /**
     * Write buffer.
     */
    private PrintStream         osp;
    /**
     * Work values.
     */
    private StringBuffer        sb          = new StringBuffer(1024);
    /**
     * Keyword lookup table.
     */
    private Hashtable           kw_cod_str          = YdxfKeyword.create_kw_cod_str();


    /**
     * Constructor (empty)
     */
    public
    YdxfPutBuffer()
    {
    }


    /**
     * Set the output type.
     * @param put_type The put type.
     * @param os The output stream.
     */
    public void setOutput(int put_type, OutputStream os)
    {
        this.put_type = put_type;
        this.os = os;
        osp = new PrintStream(this.os);
    }


    /**
     * Close the output stream.
     */
    public void close()
    {
        osp.close();
        osp = null;
        
        try
        {
            os.close();
        }
        catch (IOException e)
        {
            System.out.println("YdxfPutBuffer:close()|IOException");
            System.out.println("YdxfPutBuffer:close()|message=[" + e.getMessage() + "]");
            e.printStackTrace();
        }
        os = null;
    }


    //==========================================================================
    // put functions
    //==========================================================================

    //
    // puts
    //

    /**
     * Put a Keyword value.
     * @param grpcod The group code.
     * @param val The value.
     */
    public
    void putKeywordValue(int grpcod, Integer val)
    {
        String ws = (String)kw_cod_str.get(val);

        if (ws == null)
        {
            System.out.println("YdxfPutBuffer.putKeyworkdValue: bad keyword val (" + val + "]");
            return;
        }

        osp.print(grpcod);      putLineSep();
        osp.print(ws);          putLineSep();
    }


    /**
     * Put a String value.
     * @param grpcod The group code.
     * @param val The value.
     */
    public
    void putStringValue(int grpcod, String val)
    {
        osp.print(grpcod);      putLineSep();
        osp.print(val);         putLineSep();
    }


    /**
     * Put an int value.
     * @param grpcod The group code.
     * @param val The value.
     */
    public
    void putIntValue(int grpcod, int val)
    {
        osp.print(grpcod);      putLineSep();
        osp.print(val);         putLineSep();
    }


    /**
     * Put a double value.
     * @param grpcod The group code.
     * @param val The value.
     */
    public
    void putDoubleValue(int grpcod, double val)
    {
        // put group code
        osp.print(grpcod);      putLineSep();

        // process double value
        // round to 6 fraction digits

        // set work buffer empty
        sb.setLength(0);

        // round at 6th fraction digit and place in work buffer
        sb.append(val + 0.0000005);
        int sblen = sb.length();
        
        // find decimal point
        int dpidx = -1;
        for (int i = 0; i < sblen; i++)
        {
            if (sb.charAt(i) == '.')
            {
                dpidx = i;
                break;
            }
        }
        if (dpidx == -1)
        {   // no dp just put
            osp.print(val);         putLineSep();
            return;
        }

        // truncate to 6 fraction digits
        if (sblen > (dpidx + 7))
            sb.setLength(sblen = dpidx + 7);
        // remove training zeros
        for (int i = sblen - 1; i > (dpidx + 1); i--)
        {
            if (sb.charAt(i) != '0')
                break;
            sb.setLength(--sblen);
        }
        // put reformated work buffer
        for (int i = 0; i < sblen; i++)
            osp.print(sb.charAt(i));   
        putLineSep();
    }


    /**
     * Put a line separator.
     */
    public
    void putLineSep()
    {
        osp.print("\n");
    }


    /**
     * Flush the stream.
     */
    public
    void putFlush()
    {
        osp.flush();
    }
}

