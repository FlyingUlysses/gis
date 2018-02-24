//==============================================================================
// YdxfPutSecBlocks.java
//
// Put BLOCKS section
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/ydxf/YdxfPutSecBlocks.java,v 1.10 2003/05/08 09:38:16 ekarlo Exp $
// $Log: YdxfPutSecBlocks.java,v $
// Revision 1.10  2003/05/08 09:38:16  ekarlo
// Remove warnings.
//
// Revision 1.9  2003/04/14 12:37:55  ekarlo
// Update source file header for OSI release.
//
// Revision 1.8  2000/10/17 07:44:13  ekarlo
// Change package paths to lower case.
//
// Revision 1.7  1999-10-06 19:51:14-06  walter
// Added JavaDoc comments.
//
// Revision 1.6  1999-07-09 14:09:43-06  ekarlo
// Rearrange package names.
//
// Revision 1.5  1999/06/27  08:28:14  ekarlo
// Rename class.
//
// Revision 1.4  1999/06/20  22:32:38  ekarlo
// Rearrange package names.
//
// Revision 1.3  1999/06/15  04:54:28  ekarlo
// User Interface - phase 1.
//
// Revision 1.2  1999/02/08  04:53:14  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.1  1999/01/28  04:20:42  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.ydxf;


import java.util.*;

import com.ysystems.ycad.lib.yxxf.*;


/**
 * Put BLOCKS section.
 * This class may not be instantiated.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YdxfPutSecBlocks
{
    /**
     * Constructor to prevent instantiation.
     */
    private // Defeat instantiation
    YdxfPutSecBlocks()
    {
    }


    /**
     * Put section blocks.
     * @param putbfr The information to output.
     * @param D The Drawing
     */
    static public
    void put(YdxfPutBuffer putbfr, Yxxf D)
    {
        putbfr.putKeywordValue (  0, YdxfKeyword.KW_C_SECTION);
        putbfr.putKeywordValue (  2, YdxfKeyword.KW_C_BLOCKS);

        putbfr.put_doing = YdxfPutBuffer.PUT_DOING_BLOCKS_SECTION;

        for (Enumeration E = D.secBlocks.tblBlocks.elements(); E.hasMoreElements(); )
        {
            YxxfEntBlock blk = (YxxfEntBlock)E.nextElement();

            YdxfPutEntBlock.put(putbfr, blk);

/* ===
            if (get_font_postprocess(blk))
            {
                D.secBlocks.addBlock(blk);

//              view.commandPrintln("++YcadFontControl:commandConvert|Block process, name=[" + blk.blockname2 + "],basepnt=[" +
//                  blk.basepnt.x + ":" + blk.basepnt.y + ":" + blk.basepnt.z + "],size=" + blk.size());
            }
            else
            {
                view.commandPrintln("++YcadFontControl:commandConvert|Block skipped, name=[" + blk.blockname2 + "],size=" + blk.size());
            }
=== */
        }


        putbfr.putKeywordValue (  0, YdxfKeyword.KW_C_ENDSEC);

/* ===
        putbfr.putKeywordValue (  9, YdxfKeyword.KW_C_$ACADVER);
        putbfr.putStringValue  (  1, secHeader.acadver);

        putbfr.putKeywordValue (  9, YdxfKeyword.KW_C_$AUNITS);
        putbfr.putIntValue     ( 70, secHeader.aunits);

        putbfr.putKeywordValue (  9, YdxfKeyword.KW_C_$CECOLOR);
        putbfr.putIntValue     ( 62, secHeader.cecolor);

        putbfr.putKeywordValue (  9, YdxfKeyword.KW_C_$CELTYPE);
        putbfr.putStringValue  (  6, secHeader.celtype);

        putbfr.putKeywordValue (  9, YdxfKeyword.KW_C_$CLAYER);
        putbfr.putStringValue  (  8, secHeader.clayer);

        putbfr.putKeywordValue (  9, YdxfKeyword.KW_C_$EXTMIN);
        putbfr.putDoubleValue  ( 10, secHeader.extmin.x);
        putbfr.putDoubleValue  ( 20, secHeader.extmin.y);
        putbfr.putDoubleValue  ( 30, secHeader.extmin.z);

        putbfr.putKeywordValue (  9, YdxfKeyword.KW_C_$EXTMAX);
        putbfr.putDoubleValue  ( 10, secHeader.extmax.x);
        putbfr.putDoubleValue  ( 20, secHeader.extmax.y);
        putbfr.putDoubleValue  ( 30, secHeader.extmax.z);

        putbfr.putKeywordValue (  9, YdxfKeyword.KW_C_$FILLMODE);
        putbfr.putIntValue     ( 70, secHeader.fillmode);

        putbfr.putKeywordValue (  9, YdxfKeyword.KW_C_$LIMMIN);
        putbfr.putDoubleValue  ( 10, secHeader.limmin.x);
        putbfr.putDoubleValue  ( 20, secHeader.limmin.y);
        putbfr.putDoubleValue  ( 30, secHeader.limmin.z);

        putbfr.putKeywordValue (  9, YdxfKeyword.KW_C_$LIMMAX);
        putbfr.putDoubleValue  ( 10, secHeader.limmax.x);
        putbfr.putDoubleValue  ( 20, secHeader.limmax.y);
        putbfr.putDoubleValue  ( 30, secHeader.limmax.z);

        putbfr.putKeywordValue (  9, YdxfKeyword.KW_C_$LTSCALE);
        putbfr.putDoubleValue  ( 40, secHeader.ltscale);

        putbfr.putKeywordValue (  9, YdxfKeyword.KW_C_$TILEMODE);
        putbfr.putIntValue     ( 70, secHeader.tilemode);
=== */
    }
}

