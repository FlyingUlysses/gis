//==============================================================================
// YxxfTblLtype.java
//
// LTYPE table
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfTblLtype.java,v 1.8 2003/04/14 12:38:42 ekarlo Exp $
// $Log: YxxfTblLtype.java,v $
// Revision 1.8  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.7  2000/10/17 07:43:38  ekarlo
// Change package paths to lower case.
//
// Revision 1.6  1999-09-08 13:17:42-06  walter
// Add JavaDoc comments.
//
// Revision 1.5  1999-07-09 14:10:50-06  ekarlo
// Rearrange package names.
//
// Revision 1.4  1999/06/20  22:34:10  ekarlo
// Rearrange package names.
//
// Revision 1.3  1999/02/08  05:11:39  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.2  1999/01/28  04:32:33  ekarlo
// Text - phase 4.
//
// Revision 1.1  1997/08/30  14:23:14  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;


import java.util.*;


/**
 * LTYPE table.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YxxfTblLtype extends YxxfTblLtypeKey
{
    // code   2 - Linetype name - (in key class).

    /**
     * code   5 - Handle.
     */
    public
    String                      handle          = null;

    /**
     * code  70 - Standard flags.
     */
    public
    int                         flags           = 0;

    /**
     * code   3 - Descriptive text for linetype.
     */
    public
    String                      desc            = null;

    /**
     * code  72 - Alignment code; value is always 65, the ASCII code for A.
     */
    public
    int                         aligncode       = 'A';

    /**
     * code  73 - The number of dash length items.
     */
    public
    int                         dashlencount    = 0;

    /**
     * code  40 - Total pattern length.
     */
    public
    double                      patternlen      = 0.0;

    /**
     * code  49 - Dash, dot or space length (optional).
     * <UL>
     *   <LI>Multiple entries can exist.
     *   <LI>Vector of double.
     * </UL>
     */
    public
    Vector                      lenlist         = new Vector(1, 1);

    /**
     * code   4 - Name of shape or style (optional).
     * <UL>
     *   <LI>Multiple entries can exist.
     *   <LI>Vector of String.
     * </UL>
     */
    public
    Vector                      namelist        = new Vector(1, 1);

    /**
     * code  46 - S = scale value (optional).
     * <UL>
     *   <LI>Multiple entries can exist.
     *   <LI>Vector of double.
     * </UL>
     */
    public
    Vector                      scalelist       = new Vector(1, 1);

    /**
     * code  50 - R = rotation value (optional).
     * <UL>
     *   <LI>Multiple entries can exist.
     *   <LI>Vector of double.
     * </UL>
     */
    public
    Vector                      rotlist         = new Vector(1, 1);

    /**
     * code  44 - X = x offset value (optional).
     * <UL>
     *   <LI>Multiple entries can exist.
     *   <LI>Vector of double.
     * </UL>
     */
    public
    Vector                      xlist           = new Vector(1, 1);

    /**
     * code  45 - Y = y offset value (optional).
     * <UL>
     *   <LI>Multiple entries can exist.
     *   <LI>Vector of double.
     * </UL>
     */
    public
    Vector                      ylist           = new Vector(1, 1);


    /**
     * Constructor
     */
    public
    YxxfTblLtype()
    {
        super();
    }


    /**
     * Constructor
     * @param ltypename A Linetype name.
     */
    public
    YxxfTblLtype(String ltypename)
    {
        super(ltypename);
    }


    /**
     * Constructor
     * @param ltypename A Linetype name.
     */
    public
    YxxfTblLtype(char[] ltypename)
    {
        super(ltypename);
    }


    /**
     * Copy a Linetype table into this.
     * @param lty A Linetype table.
     */
    public
    void copyInto(YxxfTblLtype lty)
    {
        super.copyInto(lty);
        lty.handle              = handle;
        lty.flags               = flags;
        lty.desc                = desc;
        lty.aligncode           = aligncode;
        lty.dashlencount        = dashlencount;
        lty.patternlen          = patternlen;
        lty.lenlist             = (Vector)lenlist.clone();
        lty.namelist            = (Vector)namelist.clone();
        lty.scalelist           = (Vector)scalelist.clone();
        lty.rotlist             = (Vector)rotlist.clone();
        lty.xlist               = (Vector)xlist.clone();
        lty.ylist               = (Vector)ylist.clone();
    }
}



