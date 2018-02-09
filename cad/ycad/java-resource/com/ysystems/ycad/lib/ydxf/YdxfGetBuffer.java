//==============================================================================
// YdxfGetBuffer.java
//
// Get group code/group value pair from DXF file
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/ydxf/YdxfGetBuffer.java,v 1.27 2003/05/08 09:38:16 ekarlo Exp $
// $Log: YdxfGetBuffer.java,v $
// Revision 1.27  2003/05/08 09:38:16  ekarlo
// Remove warnings.
//
// Revision 1.26  2003/04/14 12:37:55  ekarlo
// Update source file header for OSI release.
//
// Revision 1.25  2001/10/18 12:29:25  ekarlo
// Update Javadoc.
// Remove keyword value compare method so that only the value return methods
// get the value part of the code/value pair.
//
// Revision 1.24  2001-10-09 21:37:26-06  ekarlo
// Update copyright.
//
// Revision 1.23  2000-10-17 01:44:31-06  ekarlo
// Change package paths to lower case.
//
// Revision 1.22  2000-02-23 20:22:56-07  ekarlo
// Fix compiler warning.
//
// Revision 1.21  1999-10-21 21:33:31-06  walter
// Added JavaDoc comments.
//
// Revision 1.20  1999-07-09 14:09:43-06  ekarlo
// Rearrange package names.
//
// Revision 1.19  1999/06/27  08:24:23  ekarlo
// Rename from YdxfGetGrpCodVal to YdxfGetBuffer.
//
// Revision 1.18  1999/06/20  22:32:38  ekarlo
// Rearrange package names.
//
// Revision 1.17  1999/06/15  04:51:35  ekarlo
// User Interface - phase 1.
//
// Revision 1.16  1999/02/08  04:56:30  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.15  1999/01/28  04:13:33  ekarlo
// Text - phase 4.
//
// Revision 1.14  1998/07/12  00:01:19  ekarlo
// Add FILLMODE keyword.
//
// Revision 1.13  1998/02/12  17:47:19  ekarlo
// Add LTSCALE keyword.
//
// Revision 1.12  1997/12/26  21:22:45  ekarlo
// Implement VIEWPORT.
//
// Revision 1.11  1997/12/16  18:35:09  ekarlo
// Fix TILEMODE.
//
// Revision 1.10  1997/08/30  14:03:59  ekarlo
// Add LTYPE keyword.
//
// Revision 1.9  1997/07/21  22:27:29  ekarlo
// Add tilemode.
//
// Revision 1.8  1997/07/13  18:12:12  ekarlo
// MVC-VH rework - phase 1.
// Rename class.
//
// Revision 1.7  1997/06/20  19:57:51  ekarlo
// Rename from YdxfGrpCodVal to YdxfGetGrpCodVal.
//
// Revision 1.6  1996/09/27  09:37:03  ekarlo
// Add text - v1.
//
// Revision 1.5  1996/09/26  01:41:39  ekarlo
// Store header values of current properties.
//
// Revision 1.4  1996/08/13  01:50:03  ekarlo
// Add POINT keyword.
//
// Revision 1.3  1996/07/30  06:23:21  ekarlo
// Add $LIMMIN and $LIMMAX keywords.
//
// Revision 1.2  1996/07/02  03:44:42  ekarlo
// Eliminate class YdxfGrpKeyword.
//
// Revision 1.1  1996/07/02  01:58:25  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.ydxf;


import java.io.*;
import java.util.*;

import com.ysystems.lib.yutil.*;
import com.ysystems.ycad.lib.yxxf.*;


/**
 * Get group code/group value pair from DXF file.
 * A DXF file consists of code/value pairs.  Each code
 * and value is on a separate file line.  Lines are delimited
 * by NL or CR/NL characters.  All codes are integers and the
 * matching group value format depends on the code value.  This class
 * provides methods to parse these code/value pairs and return
 * them in a usable form.
 * <p>The key method is get() which will start the reading of one code/value pair.
 * <p>The code value is determined by a call to getCodValue() or codEquals().
 * After a call to get() and analysis of the code value, user methods
 * should then call one of the "value methods" to obtain the value:
 * <UL>
 *   <LI>keywordValue()
 *   <LI>stringValue()
 *   <LI>chararrValue()
 *   <LI>intValue()
 *   <LI>doubleValue()
 *   <LI>ltypeValue()
 *   <LI>layerValue()
 *   <LI>styleValue()
 *   <LI>blockValue()
 * </UL>
 * After processing a given code/value then another call to get() is done
 * to begin reading and parsing the next pair.  User methods do not need to get
 * the value part if it is determined that it is not needed.
 * The proper value method should be called called once and only once.
 * If value methods are called multiple times in
 * between calls to get() the return of the correct value is not
 * guaranteed.
 */
public class YdxfGetBuffer
{
    /**
     * Type of get
     */
    public int                  get_type            = GET_TYPE_MAIN;
    /**
     * Type of get - Drawing.
     */
    public static final int     GET_TYPE_MAIN       = 1;
    /**
     * Type of get - Font.
     */
    public static final int     GET_TYPE_FONT       = 2;

    /**
     * What get is doing right now - other.
     */
    public static final int     GET_DOING_OTHER             = 0;
    /**
     * What get is doing right now - blocks section.
     */
    public static final int     GET_DOING_BLOCKS_SECTION    = 1;
    /**
     * What get is doing right now - entity section.
     */
    public static final int     GET_DOING_ENTITY_SECTION    = 2;
    /**
     * What get is doing right now
     */
    public
    int                         get_doing                   = GET_DOING_OTHER;

    /**
     * Current entity block.
     * Set to indicate which block a given entity is to be added to.
     * Can be a block in the block section or one of the entity section blocks.
     */
    public
    YxxfEntBlock                currEntBlock        = null;

    /**
     * Current entity polyline (for adding vertex entities)
     */
    public
    YxxfEntPolyline             currEntPolyline     = null;

    /**
     * Current entity insert (for adding attrib entities)
     */
    public
    YxxfEntInsert               currEntInsert       = null;


    /**
     * Input stream.
     */
    private InputStream         is;

    /**
     * Read buffer size.
     */
    private static final int    BUFSIZ              = 2048;
    /**
     * Read buffer
     */
    private byte                buf[]               = new byte[BUFSIZ];
    /**
     * Number of bytes in read buffer.
     */
    private int                 buflen              = 0; // bytes in buf
    /**
     * Next unprocessed byte in read buffer
     */
    private int                 bufidx              = 0; // next unprocessed byte
    /**
     * Read buffer end of file flag.
     */
    private boolean             bufeof              = false;
    /**
     * Read buffer new line.
     */
    private byte                b                   = (byte)'\n';


    /**
     * Drawing
     */
    private
    Yxxf                        D;


    /**
     * Work character array.
     */
    private YutilCharBuffer     cb                  = new YutilCharBuffer(300);


    /**
     * Return value
     */
    private int                 cod;

    /**
     * Return value type - keyword.
     */
    private int                 retKeyword;
    /**
     * Return value type - char buffer.
     */
    private YutilCharBuffer     retCB;
    /**
     * Return value type - char array.
     */
    private char[]              retChararr;
    /**
     * Return value type - String.
     */
    private String              retString;
    /**
     * Return value type - Int.
     */
    private int                 retInt;
    /**
     * Return value type - Double.
     */
    private double              retDouble;

    /**
     * Return value - Value scanned flag
     */
    private boolean             retValScanned       = true;
    /**
     * Return value - Keyword scanned flag.
     */
    private boolean             retKeywordScanned   = true;


    /**
     * Keyword lookup table
     */
    private Hashtable           kw_chr_cod          = YdxfKeyword.create_kw_chr_cod();


    /**
     * Table lookup key - Linetype key.
     */
    private YxxfTblLtypeKey     lookupltypekey      = new YxxfTblLtypeKey();

    /**
     * Table lookup key - Layer key.
     */
    private YxxfTblLayerKey     lookuplayerkey      = new YxxfTblLayerKey();

    /**
     * Table lookup key - Style key.
     */
    private YxxfTblStyleKey     lookupstylekey      = new YxxfTblStyleKey();

    /**
     * Table lookup key - Entity block key.
     */
    private YxxfEntBlock        lookupblockkey      = new YxxfEntBlock();


    /**
     * Constructor (empty)
     */
    public
    YdxfGetBuffer()
    {
    }


    /**
     * Set input values.
     * @param get_type The type of get i.e. Drawing or Font.
     * @param is The input stream.
     * @param D The Drawing.
     */
    public void setInput(int get_type, InputStream is, Yxxf D)
    {
        this.get_type = get_type;
        this.is = is;
        this.D = D;

        getB(); // get the first byte into b
    }


    /**
     * Get the Drawing.
     */
    public Yxxf getDrawing()
    {
        return D;
    }


    /**
     * Close the input stream.
     */
    public void close()
    {
        try
        {
            is.close();
        }
        catch (IOException e)
        {
            System.out.println("YdxfGetBuffer:close()|IOException");
            System.out.println("YdxfGetBuffer:close()|message=[" + e.getMessage() + "]");
            e.printStackTrace();
        }
        is = null;
        bufeof = true;
    }


    /**
     * Fill the buffer.
     */
    public
    int get()
    {
        // Entry state: (bufeof = true) or (byte in b)
        //
        // Exit  state: bufeof = true, cod = 0 or
        //              cod = <int value of cod>
        //              returns cod

        // scan past previous val if it hasn't been scanned
        if (!retValScanned)
        {
            // scan to trailing cr or nl
            while (true)
            {
                if (bufeof)
                    break;

                if (b == '\r' || b == '\n')
                    break;

                getB();
            }


            // scan fully past trailing cr, nl, cr/nl
            scanLineEnd();
        }


        // scan in group code
        cod = scanInt();

        // mark group value not scanned
        retValScanned       = false;
        retKeywordScanned   = false;

        return cod;
    }




    //==========================================================================
    // cod methods
    //==========================================================================

    /**
     * Get the code part of a group code/group value pair.
     * @return The code value.
     */
    public
    int getCodValue()
    {
        return cod;
    }


    /**
     * Compare this group code to a group code.
     */
    public
    boolean codEquals(int cod)
    {
        return this.cod == cod;
    }




    //==========================================================================
    // val methods
    //==========================================================================

    //
    // val return functions
    //

    /**
     * Get the value part of the group code/group value pair as a keyword int.
     * @return The group value as a keyword.
     */
    public
    int keywordValue()
    {
        if (!retValScanned)
        {
            scanCB();
        }

        Integer I = (Integer)kw_chr_cod.get(cb);

        if (I == null)
            retKeyword = YdxfKeyword.KW_C__BAD.intValue();
        else
            retKeyword = I.intValue();

        retKeywordScanned = true;

        return retKeyword;
    }


    /**
     * Get the value part of the group code/group value pair as a String.
     * @return The group value.
     */
    public
    String stringValue()
    {
        if (!retValScanned)
        {
            scanCB();
        }

        // Convert cb to String val
        retString = cb.toString();
        return cb.toString();
    }


    /**
     * Get the value part of the group code/group value pair as a char array.
     * @return The group value.
     */
    public
    char[] chararrValue()
    {
        if (!retValScanned)
        {
            scanCB();
        }

        int len = cb.length();
        retChararr = new char[len];
        for (int i = 0; i < len; i++)
            retChararr[i] = cb.charAt(i);

        return retChararr;
    }


    /**
     * Get the value part of the group code/group value pair as an int.
     * @return The group value as an int.
     */
    public
    int intValue()
    {
        if (!retValScanned)
        {
            retInt = scanInt();
        }

        return retInt;
    }


    /**
     * Get the value part of the group code/group value pair as a double.
     * @return The group value.
     */
    public
    double doubleValue()
    {
        if (!retValScanned)
        {
            retDouble = scanDouble();
        }

        return retDouble;
    }


    /**
     * Get the value part of the group code/group value pair as a LTYPE table Object in the drawing.
     * @return The group value as a Linetype.
     */
    public
    YxxfTblLtype ltypeValue()
    {
        if (!retValScanned)
        {
            scanCB();
        }

        lookupltypekey.setName(cb);

        return D.secTables.findLtype_add(lookupltypekey);
    }


    /**
     * Get the value part of the group code/group value pair as a LAYER table Object in the drawing.
     * @return The group value as a Layer.
     */
    public
    YxxfTblLayer layerValue()
    {
        if (!retValScanned)
        {
            scanCB();
        }

        lookuplayerkey.setName(cb);

        return D.secTables.findLayer_add(lookuplayerkey);
    }


    /**
     * Get the value part of the group code/group value pair as a STYLE table Object in the drawing.
     * @return The group value as a Style.
     */
    public
    YxxfTblStyle styleValue()
    {
        if (!retValScanned)
        {
            scanCB();
        }

        lookupstylekey.setName(cb);

        return D.secTables.findStyle_add(lookupstylekey);
    }


    /**
     * Get the value part of the group code/group value pair as a BLOCK table Object in the drawing.
     * @return The group value as an Entity block.
     */
    public
    YxxfEntBlock blockValue()
    {
        if (!retValScanned)
        {
            scanCB();
        }

        lookupblockkey.setBlockname2(cb);

        return D.secBlocks.findBlock_add(lookupblockkey);
    }




    //==========================================================================
    // private support functions
    //==========================================================================

    //
    // get functions
    //

    /**
     * get a single byte from buf into b
     */
    private
    void getB()
    {
        while (true)
        {
            if (bufeof)
            {
                b = 0;
                return;
            }

            if (bufidx < buflen)
            {
                b = buf[bufidx++];
                return;
            }

            getBuf();
        }
    }


    /**
     * fill buf from input stream or set bufeof
     */
    private
    void getBuf()
    {
        bufidx = 0;
        try
        {
            buflen = is.read(buf, 0, BUFSIZ);
        }
        catch (IOException e)
        {
            System.out.println("YdxfGetBuffer:getBuf()|IOException");
            System.out.println("YdxfGetBuffer:getBuf()|message=[" + e.getMessage() + "]");
            e.printStackTrace();
            buflen = -1;
        }
        if (buflen == -1)
        {
            buflen = 0;
            bufeof = true;
            // ToDo: send default EOF and check for EOF in TGet code
        }
    }




    //==========================================================================
    // basic scan functions
    //==========================================================================

    /**
     * Scan past initial spaces
     */
    private
    void scanSpace()
    {
        while (true)
        {
            if (bufeof)
                break;

            if (b != ' ')
                break;

            getB();
        }
    }


    /**
     * Scan fully past trailing cr, nl, cr/nl
     */
    private
    void scanLineEnd()
    {
        byte bcrnl;

        if (bufeof)
            return;

        bcrnl = b;
        getB();
        if (bcrnl == '\r')
        {
            if (bufeof)
                return;

            if (b == '\n')
                getB();
        }
    }




    //==========================================================================
    // val scan functions
    //==========================================================================

    /**
     * Scan characters into work array.
     */
    private
    void scanCB()
    {
        // accumulate bytes into cb until cr or nl or EOF
        cb.setLength(0);

        while (true)
        {
            if (bufeof)
                break;

            if (b == '\r' || b == '\n')
                break;

            cb.append((char)b);

            getB();
        }

        // scan fully past trailing cr, nl, cr/nl
        scanLineEnd();

        retValScanned = true;
    }


    /**
     * Scan out an int
     */
    private
    int scanInt()
    {
        int reti = 0;
        int sign = 1;

        // Scan past leading space
        scanSpace();


        // check for - sign
        if (!bufeof)
        {
            if (b == '-')
            {
                sign = -1;
                getB();
            }
        }


        // accumulate integer value until non numeric found or EOF
        while (true)
        {
            if (bufeof)
                break;

            if (b < '0' || b > '9')
                break;

            reti = (reti * 10) + (b - '0');

            getB();
        }


        // scan to trailing cr or nl
        while (true)
        {
            if (bufeof)
                break;

            if (b == '\r' || b == '\n')
                break;

            getB();
        }


        // scan fully past trailing cr, nl, cr/nl
        scanLineEnd();


        // Return value
        retValScanned = true;
        return reti * sign;
    }


    /**
     * Scan out a double
     */
    private
    double scanDouble()
    {
        double retd = 0;
        int fact = 1;
        int sign = 1;

        // Scan past leading space
        scanSpace();


        // check for - sign
        if (!bufeof)
        {
            if (b == '-')
            {
                sign = -1;
                getB();
            }
        }


        // accumulate integer value until non numeric found or EOF
        while (true)
        {
            if (bufeof)
                break;

            if (b < '0' || b > '9')
                break;

            retd = (retd * 10) + (b - '0');

            getB();
        }


        // check for fraction part
        if (!bufeof)
        {
            if (b == '.')
            {
                getB();

                // accumulate fraction value until non numeric found or EOF
                while (true)
                {
                    if (bufeof)
                        break;

                    if (b < '0' || b > '9')
                        break;

                    retd = retd + ((double)(b - '0') / (double)(fact *= 10));

                    getB();
                }
            }
        }


        // scan to trailing cr or nl
        while (true)
        {
            if (bufeof)
                break;

            if (b == '\r' || b == '\n')
                break;

            getB();
        }


        // scan fully past trailing cr, nl, cr/nl
        scanLineEnd();


        // Return value
        retValScanned = true;
        return retd * sign;
    }




    //==========================================================================
    //
    /**
     * Set global currEntBlock for paper space or model space based on 
     * entity flags.
     *
     * If loading block section:
     *    Leave currEntBlock alone - set by the last block entity gotten.
     *
     * If loading entity section:
     *    Examines the entity header to determine if it belongs in paper 
     *    space or model space.
     *
     * It is assumed that all paper space entities are read, followed by all 
     * model space entities.
     * Thus, the PSpace block is marked done as soon as the first model space 
     * entity is encountered.
     * TODO: check this - deactivated for now
     * @param hdr The Entity block header.
     *
     */
    public
    void setCurrEntBlock(YxxfEntHeader hdr)
    {
        if (get_doing == GET_DOING_ENTITY_SECTION)
        {
            if (hdr.hdr_space == 0)
            {
                currEntBlock = D.secEntities.insMSpace.block;
//              secEntities.insPSpace.block.setComplete(true);
            }
            else
            {
                currEntBlock = D.secEntities.insPSpace.block;
            }
        }
    }
    //==========================================================================
}

