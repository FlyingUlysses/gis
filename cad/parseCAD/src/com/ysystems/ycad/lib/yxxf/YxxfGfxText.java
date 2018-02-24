//==============================================================================
// YxxfGfxText.java
//
// Text Drawer
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfGfxText.java,v 1.11 2003/05/08 11:24:02 ekarlo Exp $
// $Log: YxxfGfxText.java,v $
// Revision 1.11  2003/05/08 11:24:02  ekarlo
// Remove warnings.
//
// Revision 1.10  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.9  2000/10/17 07:43:43  ekarlo
// Change package paths to lower case.
//
// Revision 1.8  2000-02-23 20:32:22-07  ekarlo
// Fix shx subshape draw scale problem.
//
// Revision 1.7  1999-10-25 11:03:53-06  ekarlo
// Implement shx shapes 1.0 (like bold.shx).
//
// Revision 1.6  1999-09-08 13:16:31-06  walter
// Add JavaDoc comments.
//
// Revision 1.5  1999-08-11 19:55:29-06  ekarlo
// Fix %% coding.
// Add %%nnn handling.
//
// Revision 1.4  1999/08/10  14:04:32  ekarlo
// Remove unused var warning.
//
// Revision 1.3  1999/07/09  20:10:50  ekarlo
// Rearrange package names.
//
// Revision 1.2  1999/06/20  22:34:10  ekarlo
// Rearrange package names.
//
// Revision 1.1  1999/02/08  05:16:11  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;


/**
 * Text drawer.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YxxfGfxText
{
    /**
     * Constructor (empty)
     */
    public
    YxxfGfxText()
    {
    }


    /**
     * Run the text TODO Explain method and args.
     * @param gc Graphics Context
     * @param text
     * @param shape
     * @param shapechardrawer
     * @param lookupshapechar
     * @param currpnt
     * @param nextpnt
     */
    public void draw(YxxfGfxContext gc, String text, 
                     YxxfShape shape, YxxfGfxShapeChar shapechardrawer,
                     YxxfShapeChar lookupshapechar,
                     YxxfGfxPointW currpnt, YxxfGfxPointW nextpnt)
    {
        YxxfShapeChar foundshapechar;
        char c1, c2;


        // loop on each character
        int textlen = text.length();
        for (int textidx = 0; textidx < textlen; textidx++)
        {
            c1 = text.charAt(textidx);

            if (c1 == '%')
            {
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
            }

            // find the shape element
            foundshapechar = shape.findShapeChar(lookupshapechar.setValue(c1));
            if (foundshapechar == null)
            {   // not found - draw question mark
                foundshapechar = shape.findShapeChar(lookupshapechar.setValue('?'));
            }
            if (foundshapechar == null)
                continue; // forget it

            // draw the character
            shapechardrawer.draw(gc, foundshapechar, 1.0, currpnt, nextpnt);
        } // end of loop on each text character
    }
}
