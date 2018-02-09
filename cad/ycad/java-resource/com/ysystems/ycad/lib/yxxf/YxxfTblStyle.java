//==============================================================================
// YxxfTblStyle.java
//
// STYLE table
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfTblStyle.java,v 1.13 2003/05/08 11:24:03 ekarlo Exp $
// $Log: YxxfTblStyle.java,v $
// Revision 1.13  2003/05/08 11:24:03  ekarlo
// Remove warnings.
//
// Revision 1.12  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.11  2000/10/17 07:43:37  ekarlo
// Change package paths to lower case.
//
// Revision 1.10  1999-09-08 13:17:29-06  walter
// Add JavaDoc comments.
//
// Revision 1.9  1999-07-09 14:10:50-06  ekarlo
// Rearrange package names.
//
// Revision 1.8  1999/07/08  02:58:44  ekarlo
// Fix shape load.
//
// Revision 1.7  1999/07/07  03:15:49  ekarlo
// Create default vport if none found and zoom to extents.
//
// Revision 1.6  1999/06/20  22:34:10  ekarlo
// Rearrange package names.
//
// Revision 1.5  1999/02/08  05:11:39  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.4  1999/01/28  04:32:33  ekarlo
// Text - phase 4.
//
// Revision 1.3  1998/12/21  15:43:26  ekarlo
// Text - phase 3.
//
// Revision 1.2  1998/11/24  19:53:50  ekarlo
// Text - phase 2.
//
// Revision 1.1  1996/09/27  09:33:41  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;


/**
 * STYLE table.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YxxfTblStyle extends YxxfTblStyleKey
{
    // code   2 - Style name - (in key class).

    /**
     * code   5 - Handle.
     */
    public
    String                      handle          = null;

    /**
     * code  70 - Standard flags.
     *            (bit-coded values).
     * <UL>
     *   <LI>1 = If set, this entry describes a shape.
     *   <LI>4 = Vertical text.
     *</UL>
     */
    public
    int                         flags           = 0;

    /**
     * code  40 - Fixed text height; 0 if not fixed.
     */
    public
    double                      height          = 0.0;

    /**
     * code  41 - Width factor.
     */
    public
    double                      widthfactor     = 0.0;

    /**
     * code  50 - Oblique angle (optional default = 0).
     */
    public
    double                      obliqueang      = 0.0;

    /**
     * code  71 - Text generation flags.
     * <UL>
     *   <LI>2 = Text is backward    (mirrored in X).
     *   <LI>4 = Text is upside down (mirrored in Y).
     * <UL>
     */
    public
    int                         textgenflags    = 0;

    /**
     * code  43 - Last height used.
     */
    public
    double                      lastheight      = 0.0;

    /**
     * code   3 - Primary font file name.
     */
    public
    String                      fontfilename    = null;

    /**
     * code   4 - Bigfont file name; blank if none.
     */
    public
    String                      bigfontfilename = null;


    /**
     * Calculated items.
     */
    public
    YxxfShape                   shape           = null;

    
    /**
     * Constructor
     */
    public
    YxxfTblStyle()
    {
        super();
    }


    /**
     * Constructor
     * @param stylename The Style name.
     */
    public
    YxxfTblStyle(String stylename)
    {
        super(stylename);
    }


    /**
     * Constructor
     * @param stylename The Style name.
     */
    public
    YxxfTblStyle(char[] stylename)
    {
        super(stylename);
    }


    /**
     * Set the Style shape.
     * @param shape The shape.
     * @return The shape.
     */
    public
    YxxfShape setShape(YxxfShape shape)
    {
        return this.shape = shape;
    }

    /**
     * Get the Style shape.
     * @return The shape.
     */
    public
    YxxfShape getShape()
    {
        return shape;
    }


    /**
     * Copy a Style this.
     * @param sty The Style.
     */
    public
    void copyInto(YxxfTblStyle sty)
    {
        super.copyInto(sty);
        sty.handle              = handle;
        sty.flags               = flags;
        
        sty.height              = height;
        sty.widthfactor         = widthfactor;
        sty.obliqueang          = obliqueang;
        sty.textgenflags        = textgenflags;
        sty.widthfactor         = widthfactor;
        sty.lastheight          = lastheight;
        sty.fontfilename        = fontfilename;
        sty.bigfontfilename     = bigfontfilename;

        if (shape != null)
            sty.shape           = shape;
    }
}



