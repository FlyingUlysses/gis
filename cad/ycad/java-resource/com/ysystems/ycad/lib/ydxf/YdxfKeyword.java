//==============================================================================
// YdxfKeyword.java
//
// DXF Keyword tables
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/ydxf/YdxfKeyword.java,v 1.11 2003/04/14 12:37:55 ekarlo Exp $
// $Log: YdxfKeyword.java,v $
// Revision 1.11  2003/04/14 12:37:55  ekarlo
// Update source file header for OSI release.
//
// Revision 1.10  2001/10/10 03:37:27  ekarlo
// Update copyright.
//
// Revision 1.9  2000-10-17 01:44:16-06  ekarlo
// Change package paths to lower case.
//
// Revision 1.8  1999-10-26 10:19:10-06  ekarlo
// Add MTEXT.
//
// Revision 1.7  1999-10-25 17:57:43-06  ekarlo
// Add TRACE.
//
// Revision 1.6  1999-09-22 22:57:00-06  walter
// Added JavaDoc comments.
//
// Revision 1.5  1999-08-07 15:16:02-06  ekarlo
// Add $SPLFRAME.
// Add LWPOLYLINE.
//
// Revision 1.4  1999/07/09  20:09:43  ekarlo
// Rearrange package names.
//
// Revision 1.3  1999/06/20  22:32:38  ekarlo
// Rearrange package names.
//
// Revision 1.2  1999/02/08  04:53:14  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.1  1999/01/28  04:16:08  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.ydxf;


import java.util.*;

import com.ysystems.lib.yutil.*;


/**
 * DXF Keyword tables.
 * This class may not be instantiated.
 * Ed Karlo - Y Systems, LLC
 */
public class YdxfKeyword
{
    /**
     * Constructor - May not be instantiated.
     */
    private // Defeat instantiation
    YdxfKeyword()
    {
    }


    //
    // Keyword table types
    //

    /**
     * Keyword lookup table - key/element is: String/code.
     */
    public static final int     CREATE_KW_STR_COD   =    1;

    /**
     * Keyword lookup table - key/element is: YutilCharBuffer/code.
     */
    public static final int     CREATE_KW_CHR_COD   =    2;

    /**
     * Keyword lookup table - key/element is: code/String.
     */
    public static final int     CREATE_KW_COD_STR   =    3;


    //
    // Keyword Strings
    //

    /**
     * Keyword Strings - General, Bad.
     */
    public static final String  KW_S__BAD           = "";
    /**
     * Keyword Strings - General, Bad.
     */
    public static final int     KW_I__BAD           =    0;
    /**
     * Keyword Strings - General, Bad.
     */
    public static final Integer KW_C__BAD           = new Integer(KW_I__BAD);

    /**
     * Keyword Strings - General, EOF.
     */
    public static final String  KW_S_EOF            = "EOF";
    /**
     * Keyword Strings - General, EOF.
     */
    public static final int     KW_I_EOF            =   10;
    /**
     * Keyword Strings - General, EOF.
     */
    public static final Integer KW_C_EOF            = new Integer(KW_I_EOF);

    /**
     * Keyword Strings - General, Section.
     */
    public static final String  KW_S_SECTION        = "SECTION";
    /**
     * Keyword Strings - General, Section.
     */
    public static final int     KW_I_SECTION        =   20;
    /**
     * Keyword Strings - General, Section.
     */
    public static final Integer KW_C_SECTION        = new Integer(KW_I_SECTION);

    /**
     * Keyword Strings - General, Header.
     */
    public static final String  KW_S_HEADER         = "HEADER";
    /**
     * Keyword Strings - General, Header.
     */
    public static final int     KW_I_HEADER         =   30;
    /**
     * Keyword Strings - General, Header.
     */
    public static final Integer KW_C_HEADER         = new Integer(KW_I_HEADER);

    /**
     * Keyword Strings - General, Tables.
     */
    public static final String  KW_S_TABLES         = "TABLES";
    /**
     * Keyword Strings - General, Tables.
     */
    public static final int     KW_I_TABLES         =   40;
    /**
     * Keyword Strings - General, Tables.
     */
    public static final Integer KW_C_TABLES         = new Integer(KW_I_TABLES);

    /**
     * Keyword Strings - General, Blocks.
     */
    public static final String  KW_S_BLOCKS         = "BLOCKS";
    /**
     * Keyword Strings - General, Blocks.
     */
    public static final int     KW_I_BLOCKS         =   50;
    /**
     * Keyword Strings - General, Blocks.
     */
    public static final Integer KW_C_BLOCKS         = new Integer(KW_I_BLOCKS);

    /**
     * Keyword Strings - General, Entities.
     */
    public static final String  KW_S_ENTITIES       = "ENTITIES";
    /**
     * Keyword Strings - General, Entities.
     */
    public static final int     KW_I_ENTITIES       =   60;
    /**
     * Keyword Strings - General, Entities.
     */
    public static final Integer KW_C_ENTITIES       = new Integer(KW_I_ENTITIES);

    /**
     * Keyword Strings - General, EndSec.
     */
    public static final String  KW_S_ENDSEC         = "ENDSEC";
    /**
     * Keyword Strings - General, EndSec.
     */
    public static final int     KW_I_ENDSEC         =   70;
    /**
     * Keyword Strings - General, EndSec.
     */
    public static final Integer KW_C_ENDSEC         = new Integer(KW_I_ENDSEC);

    /**
     * Keyword Strings - General, Table.
     */
    public static final String  KW_S_TABLE          = "TABLE";
    /**
     * Keyword Strings - General, Table.
     */
    public static final int     KW_I_TABLE          =   80;
    /**
     * Keyword Strings - General, Table.
     */
    public static final Integer KW_C_TABLE          = new Integer(KW_I_TABLE);

    /**
     * Keyword Strings - General, Vport.
     */
    public static final String  KW_S_VPORT          = "VPORT";
    /**
     * Keyword Strings - General, Vport.
     */
    public static final int     KW_I_VPORT          =   91;
    /**
     * Keyword Strings - General, Vport.
     */
    public static final Integer KW_C_VPORT          = new Integer(KW_I_VPORT);

    /**
     * Keyword Strings - General, LType.
     */
    public static final String  KW_S_LTYPE          = "LTYPE";
    /**
     * Keyword Strings - General, LType.
     */
    public static final int     KW_I_LTYPE          =   92;
    /**
     * Keyword Strings - General, LType.
     */
    public static final Integer KW_C_LTYPE          = new Integer(KW_I_LTYPE);

    /**
     * Keyword Strings - General, Layer.
     */
    public static final String  KW_S_LAYER          = "LAYER";
    /**
     * Keyword Strings - General, Layer.
     */
    public static final int     KW_I_LAYER          =   93;
    /**
     * Keyword Strings - General, Layer.
     */
    public static final Integer KW_C_LAYER          = new Integer(KW_I_LAYER);

    /**
     * Keyword Strings - General, Style.
     */
    public static final String  KW_S_STYLE          = "STYLE";
    /**
     * Keyword Strings - General, Style.
     */
    public static final int     KW_I_STYLE          =   94;
    /**
     * Keyword Strings - General, Style.
     */
    public static final Integer KW_C_STYLE          = new Integer(KW_I_STYLE);

    /**
     * Keyword Strings - General, Endtab.
     */
    public static final String  KW_S_ENDTAB         = "ENDTAB";
    /**
     * Keyword Strings - General, Endtab.
     */
    public static final int     KW_I_ENDTAB         =  100;
    /**
     * Keyword Strings - General, Endtab.
     */
    public static final Integer KW_C_ENDTAB         = new Integer(KW_I_ENDTAB);


    // HEADER
    /**
     * Keyword Strings - Header, $ACADVER TODO.
     */
    public static final String  KW_S_$ACADVER       = "$ACADVER";
    /**
     * Keyword Strings - Header, $ACADVER TODO.
     */
    public static final int     KW_I_$ACADVER       =  210;
    /**
     * Keyword Strings - Header, $ACADVER TODO.
     */
    public static final Integer KW_C_$ACADVER       = new Integer(KW_I_$ACADVER);

    /**
     * Keyword Strings - Header, $AUNITS TODO.
     */
    public static final String  KW_S_$AUNITS        = "$AUNITS";
    /**
     * Keyword Strings - Header, $AUNITS TODO.
     */
    public static final int     KW_I_$AUNITS        =  220;
    /**
     * Keyword Strings - Header, $AUNITS TODO.
     */
    public static final Integer KW_C_$AUNITS        = new Integer(KW_I_$AUNITS);

    /**
     * Keyword Strings - Header, $CECOLOR TODO.
     */
    public static final String  KW_S_$CECOLOR       = "$CECOLOR";
    /**
     * Keyword Strings - Header,$CECOLOR TODO.
     */
    public static final int     KW_I_$CECOLOR       =  230;
    /**
     * Keyword Strings - Header,$CECOLOR TODO.
     */
    public static final Integer KW_C_$CECOLOR       = new Integer(KW_I_$CECOLOR);

    /**
     * Keyword Strings - Header, $CELTYPE TODO.
     */
    public static final String  KW_S_$CELTYPE       = "$CELTYPE";
    /**
     * Keyword Strings - Header, $CELTYPE TODO.
     */
    public static final int     KW_I_$CELTYPE       =  240;
    /**
     * Keyword Strings - Header, $CELTYPE TODO.
     */
    public static final Integer KW_C_$CELTYPE       = new Integer(KW_I_$CELTYPE);

    /**
     * Keyword Strings - Header, $CLAYER TODO.
     */
    public static final String  KW_S_$CLAYER        = "$CLAYER";
    /**
     * Keyword Strings - Header, $CLAYER TODO.
     */
    public static final int     KW_I_$CLAYER        =  250;
    /**
     * Keyword Strings - Header, $CLAYER TODO.
     */
    public static final Integer KW_C_$CLAYER        = new Integer(KW_I_$CLAYER);

    /**
     * Keyword Strings - Header, $EXTMIN TODO.
     */
    public static final String  KW_S_$EXTMIN        = "$EXTMIN";
    /**
     * Keyword Strings - Header, $EXTMIN TODO.
     */
    public static final int     KW_I_$EXTMIN        =  260;
    /**
     * Keyword Strings - Header, $EXTMIN TODO.
     */
    public static final Integer KW_C_$EXTMIN        = new Integer(KW_I_$EXTMIN);

    /**
     * Keyword Strings - Header, $EXTMAX TODO.
     */
    public static final String  KW_S_$EXTMAX        = "$EXTMAX";
    /**
     * Keyword Strings - Header, $EXTMAX TODO.
     */
    public static final int     KW_I_$EXTMAX        =  270;
    /**
     * Keyword Strings - Header, $EXTMAX TODO.
     */
    public static final Integer KW_C_$EXTMAX        = new Integer(KW_I_$EXTMAX);

    /**
     * Keyword Strings - Header, $FILLMODE TODO.
     */
    public static final String  KW_S_$FILLMODE      = "$FILLMODE";
    /**
     * Keyword Strings - Header, $FILLMODE TODO.
     */
    public static final int     KW_I_$FILLMODE      =  275;
    /**
     * Keyword Strings - Header, $FILLMODE TODO.
     */
    public static final Integer KW_C_$FILLMODE      = new Integer(KW_I_$FILLMODE);

    /**
     * Keyword Strings - Header, $LIMMIN TODO.
     */
    public static final String  KW_S_$LIMMIN        = "$LIMMIN";
    /**
     * Keyword Strings - Header, $LIMMIN TODO.
     */
    public static final int     KW_I_$LIMMIN        =  280;
    /**
     * Keyword Strings - Header, $LIMMIN TODO.
     */
    public static final Integer KW_C_$LIMMIN        = new Integer(KW_I_$LIMMIN);

    /**
     * Keyword Strings - Header, $LIMMAX TODO.
     */
    public static final String  KW_S_$LIMMAX        = "$LIMMAX";
    /**
     * Keyword Strings - Header, $LIMMAX TODO.
     */
    public static final int     KW_I_$LIMMAX        =  290;
    /**
     * Keyword Strings - Header, $LIMMAX TODO.
     */
    public static final Integer KW_C_$LIMMAX        = new Integer(KW_I_$LIMMAX);

    /**
     * Keyword Strings - Header, $LTSCALE TODO.
     */
    public static final String  KW_S_$LTSCALE       = "$LTSCALE";
    /**
     * Keyword Strings - Header, $LTSCALE TODO.
     */
    public static final int     KW_I_$LTSCALE       =  295;
    /**
     * Keyword Strings - Header, $LTSCALE TODO.
     */
    public static final Integer KW_C_$LTSCALE       = new Integer(KW_I_$LTSCALE);

    /**
     * Keyword Strings - Header, $SPLFRAME TODO.
     */
    public static final String  KW_S_$SPLFRAME      = "$SPLFRAME";
    /**
     * Keyword Strings - Header, $SPLFRAME TODO.
     */
    public static final int     KW_I_$SPLFRAME      =  297;
    /**
     * Keyword Strings - Header, $SPLFRAME TODO.
     */
    public static final Integer KW_C_$SPLFRAME      = new Integer(KW_I_$SPLFRAME);

    /**
     * Keyword Strings - Header, $TILEMODE TODO.
     */
    public static final String  KW_S_$TILEMODE      = "$TILEMODE";
    /**
     * Keyword Strings - Header, $TILEMODE TODO.
     */
    public static final int     KW_I_$TILEMODE      =  300;
    /**
     * Keyword Strings - Header, $TILEMODE TODO.
     */
    public static final Integer KW_C_$TILEMODE      = new Integer(KW_I_$TILEMODE);


    // ENTITIES
    /**
     * Keyword Strings - Entities, 3DFace.
     */
    public static final String  KW_S_3DFACE         = "3DFACE";
    /**
     * Keyword Strings - Entities, 3DFace.
     */
    public static final int     KW_I_3DFACE         =  402;
    /**
     * Keyword Strings - Entities, 3DFace.
     */
    public static final Integer KW_C_3DFACE         = new Integer(KW_I_3DFACE);

    /**
     * Keyword Strings - Entities, Attribute Definition.
     */
    public static final String  KW_S_ATTDEF         = "ATTDEF";
    /**
     * Keyword Strings - Entities, Attribute Definition.
     */
    public static final int     KW_I_ATTDEF         =  403;
    /**
     * Keyword Strings - Entities, Attribute Definition.
     */
    public static final Integer KW_C_ATTDEF         = new Integer(KW_I_ATTDEF);

    /**
     * Keyword Strings - Entities, Attrib.
     */
    public static final String  KW_S_ATTRIB         = "ATTRIB";
    /**
     * Keyword Strings - Entities, Attrib.
     */
    public static final int     KW_I_ATTRIB         =  405;
    /**
     * Keyword Strings - Entities, Attrib.
     */
    public static final Integer KW_C_ATTRIB         = new Integer(KW_I_ATTRIB);

    /**
     * Keyword Strings - Entities, Arc.
     */
    public static final String  KW_S_ARC            = "ARC";
    /**
     * Keyword Strings - Entities, Arc.
     */
    public static final int     KW_I_ARC            =  410;
    /**
     * Keyword Strings - Entities, Arc.
     */
    public static final Integer KW_C_ARC            = new Integer(KW_I_ARC);

    /**
     * Keyword Strings - Entities, Block.
     */
    public static final String  KW_S_BLOCK          = "BLOCK";
    /**
     * Keyword Strings - Entities, Block.
     */
    public static final int     KW_I_BLOCK          =  420;
    /**
     * Keyword Strings - Entities, Block.
     */
    public static final Integer KW_C_BLOCK          = new Integer(KW_I_BLOCK);

    /**
     * Keyword Strings - Entities, Circle.
     */
    public static final String  KW_S_CIRCLE         = "CIRCLE";
    /**
     * Keyword Strings - Entities, Circle.
     */
    public static final int     KW_I_CIRCLE         =  430;
    /**
     * Keyword Strings - Entities, Circle.
     */
    public static final Integer KW_C_CIRCLE         = new Integer(KW_I_CIRCLE);

    /**
     * Keyword Strings - Entities, Dimension.
     */
    public static final String  KW_S_DIMENSION      = "DIMENSION";
    /**
     * Keyword Strings - Entities, Dimension.
     */
    public static final int     KW_I_DIMENSION      =  432;
    /**
     * Keyword Strings - Entities, Dimension.
     */
    public static final Integer KW_C_DIMENSION      = new Integer(KW_I_DIMENSION);

    /**
     * Keyword Strings - Entities, EndBlock.
     */
    public static final String  KW_S_ENDBLK         = "ENDBLK";
    /**
     * Keyword Strings - Entities, EndBlock.
     */
    public static final int     KW_I_ENDBLK         =  440;
    /**
     * Keyword Strings - Entities, EndBlock.
     */
    public static final Integer KW_C_ENDBLK         = new Integer(KW_I_ENDBLK);

    /**
     * Keyword Strings - Entities, Insert.
     */
    public static final String  KW_S_INSERT         = "INSERT";
    /**
     * Keyword Strings - Entities, Insert.
     */
    public static final int     KW_I_INSERT         =  450;
    /**
     * Keyword Strings - Entities, Insert.
     */
    public static final Integer KW_C_INSERT         = new Integer(KW_I_INSERT);

    /**
     * Keyword Strings - Entities, Line.
     */
    public static final String  KW_S_LINE           = "LINE";
    /**
     * Keyword Strings - Entities, Line.
     */
    public static final int     KW_I_LINE           =  460;
    /**
     * Keyword Strings - Entities, Line.
     */
    public static final Integer KW_C_LINE           = new Integer(KW_I_LINE);

    /**
     * Keyword Strings - Entities, Light weight PolyLine.
     */
    public static final String  KW_S_LWPOLYLINE     = "LWPOLYLINE";
    /**
     * Keyword Strings - Entities, Light weight PolyLine.
     */
    public static final int     KW_I_LWPOLYLINE     =  462;
    /**
     * Keyword Strings - Entities, Light weight PolyLine.
     */
    public static final Integer KW_C_LWPOLYLINE     = new Integer(KW_I_LWPOLYLINE);

    /**
     * Keyword Strings - Entities, Mtext.
     */
    public static final String  KW_S_MTEXT          = "MTEXT";
    /**
     * Keyword Strings - Entities, Mtext.
     */
    public static final int     KW_I_MTEXT          =  463;
    /**
     * Keyword Strings - Entities, Mtext.
     */
    public static final Integer KW_C_MTEXT          = new Integer(KW_I_MTEXT);

    /**
     * Keyword Strings - Entities, Point..
     */
    public static final String  KW_S_POINT          = "POINT";
    /**
     * Keyword Strings - Entities, Point.
     */
    public static final int     KW_I_POINT          =  465;
    /**
     * Keyword Strings - Entities, Point.
     */
    public static final Integer KW_C_POINT          = new Integer(KW_I_POINT);

    /**
     * Keyword Strings - Entities, PolyLine.
     */
    public static final String  KW_S_POLYLINE       = "POLYLINE";
    /**
     * Keyword Strings - Entities, PolyLine.
     */
    public static final int     KW_I_POLYLINE       =  470;
    /**
     * Keyword Strings - Entities, PolyLine.
     */
    public static final Integer KW_C_POLYLINE       = new Integer(KW_I_POLYLINE);

    /**
     * Keyword Strings - Entities, SeqEnd.
     */
    public static final String  KW_S_SEQEND         = "SEQEND";
    /**
     * Keyword Strings - Entities, SeqEnd.
     */
    public static final int     KW_I_SEQEND         =  480;
    /**
     * Keyword Strings - Entities, SeqEnd.
     */
    public static final Integer KW_C_SEQEND         = new Integer(KW_I_SEQEND);

    /**
     * Keyword Strings - Entities, Solid.
     */
    public static final String  KW_S_SOLID          = "SOLID";
    /**
     * Keyword Strings - Entities, Solid.
     */
    public static final int     KW_I_SOLID          =  490;
    /**
     * Keyword Strings - Entities, Solid.
     */
    public static final Integer KW_C_SOLID          = new Integer(KW_I_SOLID);

    /**
     * Keyword Strings - Entities, Text.
     */
    public static final String  KW_S_TEXT           = "TEXT";
    /**
     * Keyword Strings - Entities, Text.
     */
    public static final int     KW_I_TEXT           =  495;
    /**
     * Keyword Strings - Entities, Text.
     */
    public static final Integer KW_C_TEXT           = new Integer(KW_I_TEXT);

    /**
     * Keyword Strings - Entities, Trace.
     */
    public static final String  KW_S_TRACE          = "TRACE";
    /**
     * Keyword Strings - Entities, Trace.
     */
    public static final int     KW_I_TRACE          =  496;
    /**
     * Keyword Strings - Entities, Trace.
     */
    public static final Integer KW_C_TRACE          = new Integer(KW_I_TRACE);

    /**
     * Keyword Strings - Entities, ViewPort.
     */
    public static final String  KW_S_VIEWPORT       = "VIEWPORT";
    /**
     * Keyword Strings - Entities, ViewPort.
     */
    public static final int     KW_I_VIEWPORT       =  497;
    /**
     * Keyword Strings - Entities, ViewPort.
     */
    public static final Integer KW_C_VIEWPORT       = new Integer(KW_I_VIEWPORT);

    /**
     * Keyword Strings - Entities, Vertex.
     */
    public static final String  KW_S_VERTEX         = "VERTEX";
    /**
     * Keyword Strings - Entities, Vertex.
     */
    public static final int     KW_I_VERTEX         =  500;
    /**
     * Keyword Strings - Entities, Vertex.
     */
    public static final Integer KW_C_VERTEX         = new Integer(KW_I_VERTEX);


    /**
     * Create Keyword lookup table - key/element is: String/code.
     * @return The lookup table.
     */
    public static
    Hashtable
    create_kw_str_cod()
    {
        Hashtable kw_str_cod = new Hashtable();
        create_kw_table(CREATE_KW_STR_COD, kw_str_cod);
        return kw_str_cod;
    }


    /**
     * Create Keyword lookup table - key/element is: YutilCharBuffer/code.
     * @return The lookup table.
     */
    public static
    Hashtable
    create_kw_chr_cod()
    {
        Hashtable kw_chr_cod = new Hashtable();
        create_kw_table(CREATE_KW_CHR_COD, kw_chr_cod);
        return kw_chr_cod;
    }

    /**
     * Create Keyword lookup table - key/element is: code/String.
     * @return The lookup table.
     */
    public static
    Hashtable
    create_kw_cod_str()
    {
        Hashtable kw_cod_str = new Hashtable();
        create_kw_table(CREATE_KW_COD_STR, kw_cod_str);
        return kw_cod_str;
    }


    /**
     * Populate a Keyword lookup table.
     * @param which The type of key/element encoding.
     * @param kw The lookup table.
     */
    private static
    void create_kw_table(int which, Hashtable kw)
    {
        kw_put(which, kw, KW_S_EOF,           KW_C_EOF);
        kw_put(which, kw, KW_S_SECTION,       KW_C_SECTION);
        kw_put(which, kw, KW_S_HEADER,        KW_C_HEADER);
        kw_put(which, kw, KW_S_TABLES,        KW_C_TABLES);
        kw_put(which, kw, KW_S_BLOCKS,        KW_C_BLOCKS);
        kw_put(which, kw, KW_S_ENTITIES,      KW_C_ENTITIES);
        kw_put(which, kw, KW_S_ENDSEC,        KW_C_ENDSEC);
        kw_put(which, kw, KW_S_TABLE,         KW_C_TABLE);
        kw_put(which, kw, KW_S_VPORT,         KW_C_VPORT);
        kw_put(which, kw, KW_S_LTYPE,         KW_C_LTYPE);
        kw_put(which, kw, KW_S_LAYER,         KW_C_LAYER);
        kw_put(which, kw, KW_S_STYLE,         KW_C_STYLE);
        kw_put(which, kw, KW_S_ENDTAB,        KW_C_ENDTAB);

        kw_put(which, kw, KW_S_$ACADVER,      KW_C_$ACADVER);
        kw_put(which, kw, KW_S_$AUNITS,       KW_C_$AUNITS);
        kw_put(which, kw, KW_S_$CECOLOR,      KW_C_$CECOLOR);
        kw_put(which, kw, KW_S_$CELTYPE,      KW_C_$CELTYPE);
        kw_put(which, kw, KW_S_$CLAYER,       KW_C_$CLAYER);
        kw_put(which, kw, KW_S_$EXTMIN,       KW_C_$EXTMIN);
        kw_put(which, kw, KW_S_$EXTMAX,       KW_C_$EXTMAX);
        kw_put(which, kw, KW_S_$FILLMODE,     KW_C_$FILLMODE);
        kw_put(which, kw, KW_S_$LIMMIN,       KW_C_$LIMMIN);
        kw_put(which, kw, KW_S_$LIMMAX,       KW_C_$LIMMAX);
        kw_put(which, kw, KW_S_$LTSCALE,      KW_C_$LTSCALE);
        kw_put(which, kw, KW_S_$SPLFRAME,     KW_C_$SPLFRAME);
        kw_put(which, kw, KW_S_$TILEMODE,     KW_C_$TILEMODE);

        kw_put(which, kw, KW_S_3DFACE,        KW_C_3DFACE);
        kw_put(which, kw, KW_S_ATTDEF,        KW_C_ATTDEF);
        kw_put(which, kw, KW_S_ATTRIB,        KW_C_ATTRIB);
        kw_put(which, kw, KW_S_ARC,           KW_C_ARC);
        kw_put(which, kw, KW_S_BLOCK,         KW_C_BLOCK);
        kw_put(which, kw, KW_S_CIRCLE,        KW_C_CIRCLE);
        kw_put(which, kw, KW_S_DIMENSION,     KW_C_DIMENSION);
        kw_put(which, kw, KW_S_ENDBLK,        KW_C_ENDBLK);
        kw_put(which, kw, KW_S_INSERT,        KW_C_INSERT);
        kw_put(which, kw, KW_S_LINE,          KW_C_LINE);
        kw_put(which, kw, KW_S_LWPOLYLINE,    KW_C_LWPOLYLINE);
        kw_put(which, kw, KW_S_MTEXT,         KW_C_MTEXT);
        kw_put(which, kw, KW_S_POINT,         KW_C_POINT);
        kw_put(which, kw, KW_S_POLYLINE,      KW_C_POLYLINE);
        kw_put(which, kw, KW_S_SEQEND,        KW_C_SEQEND);
        kw_put(which, kw, KW_S_SOLID,         KW_C_SOLID);
        kw_put(which, kw, KW_S_TEXT,          KW_C_TEXT);
        kw_put(which, kw, KW_S_TRACE,         KW_C_TRACE);
        kw_put(which, kw, KW_S_VIEWPORT,      KW_C_VIEWPORT);
        kw_put(which, kw, KW_S_VERTEX,        KW_C_VERTEX);
    }


    /**
     * Add a key/element pair to a lookup table.
     * @param which The type of key/element encoding.
     * @param kw The lookup table.
     * @param kw_str The key or element as a String.
     * @param kw_cod The key or element as an Integer.
     * @param
     */
    private static
    void kw_put(int which, Hashtable kw, String kw_str, Integer kw_cod)
    {
        if (which == CREATE_KW_STR_COD)
        {   // create a String/code table element
            kw.put(kw_str, kw_cod);
        }
        else
        

        if (which == CREATE_KW_CHR_COD)
        {   // create a YutilCharBuffer/code table element
            YutilCharBuffer kw_chr = new YutilCharBuffer(kw_str);
            kw.put(kw_chr, kw_cod);
        }
        else
        

        if (which == CREATE_KW_COD_STR)
        {   // create a code/String table element
            kw.put(kw_cod, kw_str);
        }
    }
}

