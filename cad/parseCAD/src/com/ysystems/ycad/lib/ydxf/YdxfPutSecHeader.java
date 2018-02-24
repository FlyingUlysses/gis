//==============================================================================
// YdxfPutSecHeader.java
//
// Put HEADER section
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/ydxf/YdxfPutSecHeader.java,v 1.8 2003/04/14 12:37:55 ekarlo Exp $
// $Log: YdxfPutSecHeader.java,v $
// Revision 1.8  2003/04/14 12:37:55  ekarlo
// Update source file header for OSI release.
//
// Revision 1.7  2000/10/17 07:44:13  ekarlo
// Change package paths to lower case.
//
// Revision 1.6  1999-09-22 22:56:47-06  walter
// Added JavaDoc comments.
//
// Revision 1.5  1999-08-07 15:17:40-06  ekarlo
// Add $SPLFRAME.
//
// Revision 1.4  1999/07/09  20:09:43  ekarlo
// Rearrange package names.
//
// Revision 1.3  1999/06/27  08:28:14  ekarlo
// Rename class.
//
// Revision 1.2  1999/06/20  22:32:38  ekarlo
// Rearrange package names.
//
// Revision 1.1  1999/01/28  04:20:42  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.ydxf;


import com.ysystems.ycad.lib.yxxf.*;


/**
 * Put HEADER section.
 * This class may not be instantiated.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YdxfPutSecHeader
{
    /**
     * Constructor - may not be instantiated.
     */
    private 
    YdxfPutSecHeader()
    {
    }


    /**
     * Write the Drawing header section to the output stream. TODO
     * @param putbfr The Put Buffer with name/value pairs from dxf file.
     * @param D The Drawing.
     */
    static public
    void put(YdxfPutBuffer putbfr, Yxxf D)
    {
        YxxfSecHeader secHeader = D.secHeader;

        putbfr.putKeywordValue (  0, YdxfKeyword.KW_C_SECTION);
        putbfr.putKeywordValue (  2, YdxfKeyword.KW_C_HEADER);


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

        putbfr.putKeywordValue (  9, YdxfKeyword.KW_C_$SPLFRAME);
        putbfr.putDoubleValue  ( 70, secHeader.splframe);

        putbfr.putKeywordValue (  9, YdxfKeyword.KW_C_$TILEMODE);
        putbfr.putIntValue     ( 70, secHeader.tilemode);


        putbfr.putKeywordValue (  0, YdxfKeyword.KW_C_ENDSEC);
    }
}

