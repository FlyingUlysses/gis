//==============================================================================
// YxxfEntMtext.java
//
// MTEXT entity
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfEntMtext.java,v 1.8 2003/05/08 11:24:02 ekarlo Exp $
// $Log: YxxfEntMtext.java,v $
// Revision 1.8  2003/05/08 11:24:02  ekarlo
// Remove warnings.
//
// Revision 1.7  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.6  2002/09/12 20:03:26  ekarlo
// MTEXT development check-in.
//
// Revision 1.5  2000-10-17 01:43:53-06  ekarlo
// Change package paths to lower case.
//
// Revision 1.4  2000-02-23 20:29:09-07  ekarlo
// Fix style name display.
//
// Revision 1.3  1999-11-24 22:46:14-07  ekarlo
// Fix compile warnings.
//
// Revision 1.2  1999-10-26 10:32:43-06  ekarlo
// Deactivate for now.
//
// Revision 1.1  1999-10-26 10:20:34-06  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;


/**
 * MTEXT entity.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YxxfEntMtext extends YxxfEntHeader implements YxxfEnt
{
    /**
     * code   1 -
     *      Text string.
     *      If the text string is less than 255 characters, all characters
     *      appear in group 1.  If the text string is greater than 255
     *      characters, the string is divided into 255 character chunks,
     *      which appear in one or more group 3 codes.  If group 3 codes
     *      are used, the last group is a group 1 and has fewer than 255
     *      characters.
     * code   3 - Additional text (always in 255 character chunks).
     */
    public
    String                      text            = null;


    /**
     * code   7 - Text style name (optional; default = "STANDARD").
     *            Set to style table reference.
     */
    public
    YxxfTblStyle                style;


    /**
     * code  10,20,30 - Insertion point.
     */
    public
    YxxfGfxPointW               inspnt          = new YxxfGfxPointW();


    /**
     * code  11,21,31 -
     *            X-axis direction vector (in WCS).
     */
    public
    YxxfGfxPointW               xaxisdir        = new YxxfGfxPointW();


    /**
     * code  12,22,32 -
     *            Y-axis direction vector (in WCS).
     */
    public
    YxxfGfxPointW               yaxisdir        = new YxxfGfxPointW();


    /**
     * code  40 - Nominal (initial) text height.
     */
    public
    double                      height          = 0.0;

    /**
     * code  41 - Reference rectangle width.
     */
    public
    double                      refrectwidth    = 0.0;


    /**
     * code  42 - Horizontal width of the characters that make up the mtext entity.  This
     *            value will always be equal to or less than the value of group code 41
     *            (read only, ignored if supplied).
     */
    public
    double                      actualwidth     = 0.0;


    /**
     * code  43 - Vertical height of the mtext entity (read only, ignored if supplied).
     */
    public
    double                      actualheight    = 0.0;


    /**
     * code  44 - Line spacing factor (optional).
     *            Percentage of default (3-on-5) line spacing to be applied.  Valid values
     *            range from 0.25 to 4.00.
     */
    public
    double                      linespacingfactor
                                                = 0.0;


    /**
     * code  50 - Rotation angle in radians (optional; default = 0).
     */
    public
    double                      rotang          = 0.0;


    /**
     * code  71 - Attachment point.
     * <UL>
     *   <LI>1 = Top left
     *   <LI>2 = Top center
     *   <LI>3 = Top right
     *   <LI>4 = Middle left
     *   <LI>5 = Middle center
     *   <LI>6 = Middle right
     *   <LI>7 = Bottom left
     *   <LI>8 = Bottom center
     *   <LI>9 = Bottom right
     * </UL>
     *      See vertalignflags for clarification.
     */
    public
    int                         attachpointflag = 0;


    /**
     * code  72 - Drawing direction.
     * <UL>
     *   <LI>1 = Left to right
     *   <LI>2 = Right to left
     *   <LI>3 = Top to bottom
     *   <LI>4 = Bottom to top
     *   <LI>5 = By style (the flow direction is inherited from the associated text style)
     * </UL>
     */
    public
    int                         drawdirflag     = 0;


    /**
     * code  73 - Line spacing style (optional).
     * <UL>
     *   <LI>1 = At least (taller characters will override)
     *   <LI>2 = Exact (taller character will not override)
     * </UL>
     */
    public
    int                         linespacingflag = 0;


    // Calculated items

    /**
     * Transmat for text - computed during get or load.
     */
    private
    YxxfGfxMatrix               M_mtext         = null;


    /**
     * Parsed text entities insert.
     */
    public
    YxxfEntInsert               mtextInsert;

    /**
     * Parsed text entities block.
     */
    public
    YxxfEntBlock                mtextBlock;




    /**
     * Constructor (empty)
     */
    public
    YxxfEntMtext()
    {
    }


    /**
     * Draw text.
     * @param gc The graphics context.
     */
    public
    void draw(YxxfGfxContext gc)
    {
        if (gc.setupEntity(this, M_mtext) == false)
            return;

//d     System.out.println("MTEXT:t=[" + text + "]");
//d     System.out.println("     :inspnt=" + inspnt + ",xaxisdir=" + xaxisdir + ",yaxisdir=" + yaxisdir);
//d     System.out.println("     :style.name=" + ((style == null) ? "NULL" : style.getName()));
//d     System.out.println("     :h=" + height + ",refrectwidth=" + refrectwidth);
//d     System.out.println("     :attachpointflag=" + attachpointflag + ",drawdirflag=" + drawdirflag);


        //
        // Draw the mtext insert
        //
        mtextInsert.draw(gc);
    }


    /**
     * Calculate text transmat.
     * @param D The drawing.
     */
    public
    void calc(Yxxf D)
    {
        //
        // connect
        //
        hdr_calc(D);

        // connect style
        if (style == null)
            style = D.secTables.findStyle_add(YxxfTblStyleKey.STR_STYLENAME__STANDARD);
        D.secTables.findStyleShape_add(style);


        //
        // Initialize mtext matrix
        //
        if (M_mtext == null)
            M_mtext = new YxxfGfxMatrix();
        M_mtext.mtxSetIdentity();


        //
        // Create the mtext insert and block
        //

        // Init and clear block
        if (mtextBlock == null)
            mtextBlock = new YxxfEntBlock("MTEXTBLOCK");
        this.copyHeaderInto(mtextBlock);
        mtextBlock.basepnt.set(0, 0, 0);
        mtextBlock.clearEntities();
        mtextBlock.setBlockComplete(false);
        mtextBlock.clearAttdefs();

        // Init and clear insert
        if (mtextInsert == null)
            mtextInsert = new YxxfEntInsert(mtextBlock);
        this.copyHeaderInto(mtextInsert);
        mtextInsert.inspnt.set(0, 0, 0);
        mtextInsert.scale.set(1, 1, 1);
        mtextInsert.rotang = 0;
        mtextInsert.attFollow = 0;
        mtextInsert.xtruDir.set(0, 0, 1);
        mtextInsert.clearAttrib();

        // Set insert to match mtext
        mtextInsert.inspnt.set(inspnt);
        mtextInsert.calc(D);


        //
        // Parse text string and create list of individual
        // text and command entities.
        //
        parse_mtext(D);
    }


    /**
     * Parse text string.
     * @param D The drawing.
     */
    public
    void parse_mtext(Yxxf D)
    {
        StringBuffer accum = new StringBuffer(256); // accumulator
        char c1, c2;

        //
        // Pass 1
        // Parse text string into vector of words, whitespace and
        // MTEXT format codes.
        //

        // loop on each character
        int textlen = text.length();
        for (int textidx = 0; textidx < textlen; textidx++)
        {
            c1 = text.charAt(textidx);

            if (c1 == '\\') // format code
            {
                if (textidx + 1 < textlen && text.charAt(textidx + 1) == 'P') // Paragraph end
                {
                    emit_ent_text(accum, D);
                    accum.setLength(0);
                    // emit_ent_command(accum, D);
                    textidx += 1;
                    continue;
                }
            }

            accum.append(text.charAt(textidx));
        } // End loop on text


        // Finish the block
        if (accum.length() > 0)
        {
            emit_ent_text(accum, D);
        }

        mtextBlock.setBlockComplete(true);
    }


    /**
     * Emits a text entity from accumulated text characters.
     * @param D The drawing.
     */
    public
    void emit_ent_text(StringBuffer accum, Yxxf D)
    {
        // Create text entity
        YxxfEntText textent = new YxxfEntText();
        this.copyHeaderInto(mtextBlock);
        textent.inspnt.set(0, 0, 0);
        textent.height = height;
        textent.text = accum.toString();
        textent.calc(D);

        // Add to block
        mtextBlock.addEntity(textent);
    }
}


// === attic ===

/* ====
        YxxfGfxPointW Ax = new YxxfGfxPointW();
        YxxfGfxPointW Ay = new YxxfGfxPointW();
        YxxfGfxPointW Az = new YxxfGfxPointW();

//      YxxfGfxPointW B0 = new YxxfGfxPointW();
//      YxxfGfxPointW Bz = new YxxfGfxPointW();

//      YxxfGfxPointW Ip = new YxxfGfxPointW();


        //
        // get text shape values
        //
        shape = style.shape;

        shape_above_size     = shape.getShapeDesc_above_size();


        //
        // setup text transform
        //

        // Initialize text matrix
        if (M_mtext == null)
            M_mtext = new YxxfGfxMatrix();
        else
            M_mtext.mtxSetIdentity();


        // Calculate Ax, Ay, Az axes using x axis dir and y axis dir
        if (xaxisdir.x == 0.0 && xaxisdir.y == 0.0 && xaxisdir.z == 0.0)
            Ax.set(YxxfGfxPointW.Wx);
        else
            { Ax.set(xaxisdir); Ax.normalize(); }

        if (yaxisdir.x == 0.0 && yaxisdir.y == 0.0 && yaxisdir.z == 0.0)
            { Ay.crossProduct(YxxfGfxPointW.Wz, Ax); Ay.normalize(); }
        else
            { Ay.set(yaxisdir); Ay.normalize(); }

        Az.crossProduct(Ax, Ay); Az.normalize();
//d     System.out.println("CALC :Ax=" + Ax);
//d     System.out.println("     :Ay=" + Ay);
//d     System.out.println("     :Az=" + Az);


        // Apply new rotation
        M_mtext.mtxRotateAxes_World_to_Local(Ax, Ay, Az);


        // Calculate insertion point Ip from inspnt
        // Applies the current text rotation
//      Ip.set(inspnt);
//      M_mtext.mtxTransformPoint(Ip);


        // Apply new scale
        double xscale;
        double relxscale = 1.0;
        if ((textgenflags & 2) == 2) // Text is backward    (mirrored in X)
            xscale = -height / shape_above_size * relxscale;
        else
            xscale =  height / shape_above_size * relxscale;

        double yscale;
        if ((textgenflags & 4) == 4) // Text is upside down (mirrored in Y)
            yscale = -height / shape_above_size;
        else
            yscale =  height / shape_above_size;

        YxxfGfxPointW scale = new YxxfGfxPointW(xscale, yscale, 1);
        M_mtext.mtxScale(scale, YxxfGfxPointW.W0);


        // Apply new translate (insertion point)
//      M_mtext.mtxTranslate(Ip);
        M_mtext.mtxTranslate(inspnt);
==== */

/* ====
                if (textidx + 1 < textlen && text.charAt(textidx + 1) == '%')
                {
                    if (textidx + 2 < textlen)
                    {
                        c2 = text.charAt(textidx + 2);

                        if (c2 == 'd' || c2 == 'D') // degrees symbol
                            { c1 = '\u00B0'; textidx += 2; }
                        else

                        if (c2 == 'p' || c2 == 'P') // plus/minus tolerance symbol
                            { c1 = '\u00B1'; textidx += 2; }
                        else

                        if (c2 == 'c' || c2 == 'C') // circle diameters dimensioning symbol
                            { c1 = '\u2205'; textidx += 2; }
                        else

                        if (c2 == 'o' || c2 == 'O') // toggle overscoring - ToDo: ignore for now
                            { textidx += 2; continue; }
                        else

                        if (c2 == 'u' || c2 == 'U') // toggle underscoring - ToDo: ignore for now
                            { textidx += 2; continue; }
                        else

                        if (c2 == '%') // single percent sign
                            { c1 = '%';      textidx += 2; }
                        else

                        if (c2 >= '0' && c2 <= '9') // number
                        {
                            int cidx, accum = 0;
                            char c3;

                            for (cidx = 2; ; cidx++)
                            {
                                if (cidx > 4) // 3 char limit
                                {
                                    c1 = (char)accum;
                                    textidx += cidx - 1;
                                    break;
                                }
                                if ((textidx + cidx) >= textlen) // off the end
                                {
                                    c1 = (char)accum;
                                    textidx += cidx;
                                    break;
                                }
                                c3 = text.charAt(textidx + cidx);
                                if (c3 >= '0' && c3 <= '9') // number
                                {
                                    accum = (accum * 10) + (c3 - '0');
                                    continue;
                                }
                                else
                                {   // not a number
                                    c1 = (char)accum;
                                    textidx += cidx - 1;
                                    break;
                                }
                            }
                        }
                    }
                }
==== */
