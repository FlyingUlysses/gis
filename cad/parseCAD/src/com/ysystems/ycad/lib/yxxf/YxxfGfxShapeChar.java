//==============================================================================
// YxxfGfxShapeChar.java
//
// Shape drawer
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfGfxShapeChar.java,v 1.10 2003/05/29 13:27:26 ekarlo Exp $
// $Log: YxxfGfxShapeChar.java,v $
// Revision 1.10  2003/05/29 13:27:26  ekarlo
// Add shx commands:
// 0x0C: arc defined by x-y displacement and bulge
// 0x0D: multiple bulge specified arcs.
// All basic commands are now complete.
//
// Revision 1.9  2003/05/08 11:24:02  ekarlo
// Remove warnings.
//
// Revision 1.8  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.7  2000/10/17 07:43:44  ekarlo
// Change package paths to lower case.
//
// Revision 1.6  2000-02-23 20:33:30-07  ekarlo
// Fix shx subshape draw scale problem.
// Fix shx draw calculation error.
//
// Revision 1.5  1999-10-25 11:03:53-06  ekarlo
// Implement shx shapes 1.0 (like bold.shx).
//
// Revision 1.4  1999-09-29 17:02:17-06  walter
// Added JavaDoc comments.
//
// Revision 1.3  1999-07-09 14:10:50-06  ekarlo
// Rearrange package names.
//
// Revision 1.2  1999/06/20  22:34:10  ekarlo
// Rearrange package names.
//
// Revision 1.1  1999/01/28  04:29:54  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;


/**
 * Shape drawer.
 * @author Ed Karlo - Y Systems, LLC
 */

public class YxxfGfxShapeChar
{
    /**
     * Shape associated with the shape char being drawn.
     * Used for lookup of shapechars in geom lists (code 0x07)
     */
    private
    YxxfShape                   shape           = null;

    /**
     * Text is vertical.
     */
    private
    boolean                     textvert        = false;


    /**
     * Work point 1.
     */
    private
    YxxfGfxPointW               tmppnt1         = new YxxfGfxPointW();

    /**
     * Work point 2.
     */
    private
    YxxfGfxPointW               tmppnt2         = new YxxfGfxPointW();

    /**
     * Center work point.
     */
    private
    YxxfGfxPointW               center          = new YxxfGfxPointW();


    //==========================================================================
    // Location stack - for push and pop location codes 0x05 and 0x06

    /**
     * Stack for push and pop location
     * The shx documentation states that the location stack is limited to
     * 4 levels.  It is made larger to allow for nested subshape draws but
     * this may need to be changed if errors occur.
     */
    private
    YxxfGfxPointW[]             locstack                = { new YxxfGfxPointW(),
                                                            new YxxfGfxPointW(),
                                                            new YxxfGfxPointW(),
                                                            new YxxfGfxPointW(),
                                                            new YxxfGfxPointW(),
                                                            new YxxfGfxPointW(),
                                                            new YxxfGfxPointW(),
                                                            new YxxfGfxPointW(),
                                                            new YxxfGfxPointW(),
                                                            new YxxfGfxPointW(),
                                                            new YxxfGfxPointW(),
                                                            new YxxfGfxPointW() };
    /**
     * Location stack top idx.
     */
    private
    int                         locstack_topidx         = -1;
    /**
     * Location stack size.
     */
    private
    int                         locstack_size           = 12;
    //==========================================================================


    //==========================================================================

    /**
     * Constructor (empty)
     */
    public
    YxxfGfxShapeChar()
    {
    }

    /**
     * Constructor
     * @param shape The shape.
     * @param textvert true if the text is vertical.
     */
    public
    YxxfGfxShapeChar(YxxfShape shape, boolean textvert)
    {
        this.shape = shape;
        this.textvert = textvert;
    }

    /**
     * Set the value of shape.
     * @param shape The shape.
     * @return The shape.
     */
    public
    YxxfShape setShape(YxxfShape shape)
    {
        return this.shape = shape;
    }

    /**
     * Set the value of the textvert flag.
     * @param textvert true if text is displayed vertically.
     * @return The value of textvert.
     */
    public
    boolean setTextvert(boolean textvert)
    {
        return this.textvert = textvert;
    }
    //==========================================================================


    /**
     * Draw text.
     * @param gc The graphics context.
     * @param shapechar The character Shape description.
     * @param currpnt The position to begin the current character. 
     * @param nextpnt The position to begin the next character.
     */
    public void draw(YxxfGfxContext gc, YxxfShapeChar shapechar,
                     double geomscale,
                     YxxfGfxPointW currpnt, YxxfGfxPointW nextpnt)
    {
        // Draw it
        char[] geom = shapechar.getGeom();
        int geomlen = geom.length;

        boolean pendown     = true;
        boolean cmndprocess = true;


//d*/   if ((int)shapechar.getValue() != 0x55 && (int)shapechar.getValue() != 0x42) // U && B
//d*/   if ((int)shapechar.getValue() != 0x42) // B
//d*/   if ((int)shapechar.getValue() != 0x55) // U
//d*/   if ((int)shapechar.getValue() != 0x4A) // J
//d*/   if ((int)shapechar.getValue() != 0x51) // Q
//d*/   if ((int)shapechar.getValue() != 0x53) // S
//d*/   if ((int)shapechar.getValue() < 0x41 || (int)shapechar.getValue() > 0x5A || (int)shapechar.getValue() == 0x53) // A - Z !S
//d*/   if ((int)shapechar.getValue() < 0x61 || (int)shapechar.getValue() > 0x7A || (int)shapechar.getValue() == 0x73) // a - z !s
//d*/   if ((int)shapechar.getValue() == 0x53 || (int)shapechar.getValue() == 0x73) // !S !s
//d*/       return;
//d     System.out.println("    :elem=[" + Integer.toHexString((int)shapechar.getValue()) + ":" + shapechar.getDesc() + "],elemlen=" + shapechar.getGeom().length + ",currpnt=" + currpnt + ",geomscale=" + geomscale + " { ");
//d     geomlen = Math.min(geomlen, 20);
        locstack_topidx = -1;
//d     int dbacicolor = 0;
        for (int geomidx = 0; geomidx < geomlen; )
        {
            char geomchar = geom[geomidx];
//d         System.out.print  (Integer.toHexString((int)geomchar) + " ");

            if (geomchar == '\0') // End of shape definition
            {
//d             System.out.println("        tcmd0x00:done,nextpnt=" + nextpnt);
                break; // out of geom loop
            }
//d         gc.setEntity_acolor(YxxfGfxPalette.ACI_WHITE);

            switch (geomchar)
            {
                case 0x01:  // activate draw mode (pen down)
                    geomidx++;
                    if (cmndprocess)
                    {
                        pendown = true;
//d                     System.out.println("        tcmd0x01:pendown,currpnt=" + currpnt + ",nextpnt=" + nextpnt);
                    }
                    cmndprocess = true;
                    break;


                case 0x02:  // deactivate draw mode (pen up)
                    geomidx++;
                    if (cmndprocess)
                    {
                        pendown = false;
//d                     System.out.println("        tcmd0x02:penup,currpnt=" + currpnt + ",nextpnt=" + nextpnt);
                    }
                    cmndprocess = true;
                    break;


                case 0x03:  // divide vector length by next byte
                    geomidx++;
                    if (cmndprocess)
                    {
                        geomscale /= (double)geom[geomidx];
//d                     System.out.println("        tcmd0x03:divval=" + (double)geom[geomidx]);
                    }
                    geomidx++;
                    cmndprocess = true;
                    break;


                case 0x04:  // multiply vector length by next byte
                    geomidx++;
                    if (cmndprocess)
                    {
                        geomscale *= (double)geom[geomidx];
//d                     System.out.println("        tcmd0x04:mulval=" + (double)geom[geomidx]);
                    }
                    geomidx++;
                    cmndprocess = true;
                    break;


                case 0x05:  // push current location onto stack
                    geomidx++;
                    if (cmndprocess)
                    {
                        if (locstack_topidx >= (locstack_size - 1))
                        {
                            // error - stack overflow - ignore it
                        }
                        else
                        {
                            locstack[++locstack_topidx].set(currpnt);
//d                         System.out.println("        tcmd0x05:push location [" + currpnt + "]"); //d
                        }
                    }
                    cmndprocess = true;
                    break;


                case 0x06:  // pop current location from stack
                    geomidx++;
                    if (cmndprocess)
                    {
                        if (locstack_topidx < 0)
                        {
                            // error - stack underflow - ignore it
                        }
                        else
                        {
                            currpnt.set(locstack[locstack_topidx--]);
//d                         System.out.println("        tcmd0x06:pop  location [" + currpnt + "]"); //d
                        }
                    }
                    cmndprocess = true;
                    break;


                case 0x07:  // draw subshape number given by next byte
                    geomidx++;
                    if (cmndprocess)
                    {
                        // pendown ignored - subshapes always drawn
//d                     System.out.println("        tcmd0x07:DRAW subshape=[" + Integer.toHexString((int)geom[geomidx]) + "]"); //d
                        // find the shape element
                        YxxfShapeChar lookupshapechar = new YxxfShapeChar(); // ToDo - check temp usage
                        YxxfShapeChar foundshapechar = shape.findShapeChar(lookupshapechar.setValue(geom[geomidx]));
                        if (foundshapechar == null)
                        {   // not found - draw question mark
                            foundshapechar = shape.findShapeChar(lookupshapechar.setValue('?'));
                        }
                        if (foundshapechar == null)
                            continue; // forget it

                        // draw the character
                        draw(gc, foundshapechar,
                             geomscale,
                             currpnt, nextpnt);
                        pendown = false;
                    }
                    geomidx++;
                    cmndprocess = true;
                    break;


                case 0x08:  // x-y displacement given by next two bytes
                    geomidx++;
                    if (cmndprocess)
                    {
                        // calculate nextpnt
                        nextpnt.set(currpnt.x + (double)((byte)geom[geomidx]    ) * geomscale,
                                    currpnt.y + (double)((byte)geom[geomidx + 1]) * geomscale, 0);
                        if (pendown)
                        {
//d                         System.out.println("        tcmd0x08:DRAW x-y disp=(" + nextpnt.x + "," + nextpnt.y + ")");
                            gc.drawLine__ECS__cont__flat__wid_none(currpnt, nextpnt);
                        }
                        else
                        {
//d                        System.out.println("        tcmd0x08:MOVE x-y disp=(" + nextpnt.x + "," + nextpnt.y + ")");
                        }
                        currpnt.set(nextpnt);
                    }
                    geomidx += 2;
                    cmndprocess = true;
                    break;


                case 0x09:  // multiple x-y displacements, terminated by (0,0)
                    geomidx++;
                    while (true)
                    {
                        if (geom[geomidx] == 0 && geom[geomidx + 1] == 0)
                        {
                            geomidx += 2;
                            break;
                        }
                        if (cmndprocess)
                        {
                            // calculate nextpnt
                            nextpnt.set(currpnt.x + (double)((byte)geom[geomidx]    ) * geomscale,
                                        currpnt.y + (double)((byte)geom[geomidx + 1]) * geomscale, 0);
                            if (pendown)
                            {
//d                             System.out.println("        tcmd0x09:DRAW mult x-y disp=(" + nextpnt.x + "," + nextpnt.y + ")");
                                gc.drawLine__ECS__cont__flat__wid_none(currpnt, nextpnt);
                            }
                            else
                            {
//d                             System.out.println("        tcmd0x09:MOVE mult x-y disp=(" + nextpnt.x + "," + nextpnt.y + ")");
                            }
                            currpnt.set(nextpnt);
                        }
                        geomidx += 2;
                    }
                    cmndprocess = true;
                    break;


                case 0x0A:  // octant arc defined by next two bytes
                    geomidx++;
                    if (cmndprocess)
                    {
                        int radius = (char)geom[geomidx];
                        int octdesc = (byte)geom[geomidx + 1]; // octant descriptor - sign indictates swing dir
                        byte octdescabs = (byte)((octdesc < 0) ?  octdesc : octdesc);
                        int begoct = (octdescabs & 0x70) >>> 4; // beginning octant
                        int incoct = (octdescabs & 0x07); // included octants
                        if (incoct == 0) incoct = 8;
                        double begang = begoct * 45.0;
                        double swgang = (octdesc < 0) ? -incoct * 45.0 : incoct * 45.0;

                        // find center
                        center.set(currpnt.x - (Math.cos(begang * (Math.PI / 180.0)) * radius * geomscale),
                                   currpnt.y - (Math.sin(begang * (Math.PI / 180.0)) * radius * geomscale), 0);

                        if (pendown)
                        {
//d                         System.out.println("        tcmd0x0A:DRAW octant arc=(" + (int)geom[geomidx    ] + "," + //d
//d                                                                                   (int)geom[geomidx + 1] + ")");
                            gc.drawCircle__ECS__cont__flat__wid_none(center,
                                                                     radius * geomscale,
                                                                     begang * (Math.PI / 180.0), swgang * (Math.PI / 180.0));
                        }
                        else
                        {
//d                         System.out.println("        tcmd0x0B:MOVE octant arc=(" + (int)geom[geomidx    ] + "," + //d
//d                                                                                   (int)geom[geomidx + 1] + ")");
                        }
//d                     System.out.println("        tcmd0x0A:         radius=" + radius +
//d                                                                 ",octdesc=" + octdesc +
//d                                                                 ",octdescabs=" + octdescabs +
//d                                                                 ",begoct=" + begoct +
//d                                                                 ",incoct=" + incoct +
//d                                                                 ",begang=" + begang +
//d                                                                 ",swgang=" + swgang +
//d                                                                 ",center=" + center +
//d                                                                 ",wise=" + ((octdesc < 0) ? "(<0 CW)" : "(>=0 CCW)"));

                        // set currpnt
                        currpnt.set(center.x + (Math.cos((begang + swgang) * (Math.PI / 180.0)) * radius * geomscale),
                                    center.y + (Math.sin((begang + swgang) * (Math.PI / 180.0)) * radius * geomscale), 0);
                    }
                    geomidx += 2;
                    cmndprocess = true;
                    break;


                case 0x0B:  // fractional arc defined by next five bytes
                    geomidx++;
                    if (cmndprocess)
                    {
//d                     gc.setEntity_acolor(++dbacicolor);
                        int begoffset = (char)geom[geomidx];
                        int endoffset = (char)geom[geomidx + 1];
                        int radius = (char)geom[geomidx + 2] * 256 + (char)geom[geomidx + 3];
                        int octdesc = (byte)geom[geomidx + 4]; // octant descriptor - sign indictates swing dir
                        byte octdescabs = (byte)((octdesc < 0) ?  octdesc : octdesc);
                        int begoct = (octdescabs & 0x70) >>> 4; // beginning octant
                        int incoct = (octdescabs & 0x07); // included octants
                        if (incoct == 0) incoct = 8;
                        double begang = 0, swgang = 0;
                        if (octdesc < 0)
                        {   // neg - clockwise
                            begang = (begoct * 45.0) - (begoffset * (45.0/256.0));
                            if (endoffset != 0)
                                swgang = ((incoct - 1) * 45.0) + (endoffset * (45.0/256.0)) - (begoffset * (45.0/256.0));
                            else
                                swgang = (incoct * 45.0) - (begoffset * (45.0/256.0));
                            swgang = -swgang;
                        }
                        else
                        {   // pos - counterclockwise
                            begang = (begoct * 45.0) + (begoffset * (45.0/256.0));
                            if (endoffset != 0)
                                swgang = ((incoct - 1) * 45.0) + (endoffset * (45.0/256.0)) - (begoffset * (45.0/256.0));
                            else
                                swgang = (incoct * 45.0) - (begoffset * (45.0/256.0));
                        }

                        // find center
                        center.set(currpnt.x - (Math.cos(begang * (Math.PI / 180.0)) * radius * geomscale),
                                   currpnt.y - (Math.sin(begang * (Math.PI / 180.0)) * radius * geomscale), 0);

                        if (pendown)
                        {
//d                         System.out.println("        tcmd0x0B:DRAW fractional arc=(" + (int)geom[geomidx    ] + "," + //d
//d                                                                                       (int)geom[geomidx + 1] + "," +
//d                                                                                       (int)geom[geomidx + 2] + "," +
//d                                                                                       (int)geom[geomidx + 3] + "," +
//d                                                                                       (int)geom[geomidx + 4] + ")");
                            gc.drawCircle__ECS__cont__flat__wid_none(center,
                                                                     radius * geomscale,
                                                                     begang * (Math.PI / 180.0), swgang * (Math.PI / 180.0));
                        }
                        else
                        {
//d                         System.out.println("        tcmd0x0B:MOVE fractional arc=(" + (int)geom[geomidx    ] + "," + //d
//d                                                                                       (int)geom[geomidx + 1] + "," +
//d                                                                                       (int)geom[geomidx + 2] + "," +
//d                                                                                       (int)geom[geomidx + 3] + "," +
//d                                                                                       (int)geom[geomidx + 4] + ")");
                        }
//d                     System.out.println("                      radius=" + radius +
//d                                                             ",begoffset=" + begoffset +
//d                                                             ",endoffset=" + endoffset +
//d                                                             ",octdesc=" + octdesc +
//d                                                             ",octdescabs=" + Integer.toHexString((int)octdescabs) +
//d                                                             ",begoct=" + begoct +
//d                                                             ",incoct=" + incoct +
//d                                                             ",begang=" + begang +
//d                                                             ",swgang=" + swgang +
//d                                                             ",currpnt=" + currpnt +
//d                                                             ",wise=" + ((octdesc < 0) ? "(<0 CW)" : "(>=0 CCW)"));

                        // set currpnt
                        currpnt.set(center.x + (Math.cos((begang + swgang) * (Math.PI / 180.0)) * radius * geomscale),
                                    center.y + (Math.sin((begang + swgang) * (Math.PI / 180.0)) * radius * geomscale), 0);
                    }
                    geomidx += 5;
                    cmndprocess = true;
                    break;


                case 0x0C:  // arc defined by x-y displacement and bulge
                    geomidx++;
                    if (cmndprocess)
                    {
                        // calculate nextpnt
                        nextpnt.set(currpnt.x + (double)((byte)geom[geomidx]    ) * geomscale,
                                    currpnt.y + (double)((byte)geom[geomidx + 1]) * geomscale, 0);
                        if (pendown)
                        {
//d                         System.out.println("        tcmd0x0C:DRAW x-y arc=(" + (byte)geom[geomidx    ] + "," + //d
//d                                                                                (byte)geom[geomidx + 1] + "," +
//d                                                                                (byte)geom[geomidx + 2] + ")");

                            drawArc_XY_Bulge(gc, currpnt, nextpnt, (byte)geom[geomidx + 2] * geomscale);
                        }
                        else
                        {
//d                         System.out.println("        tcmd0x0C:MOVE x-y arc=(" + (byte)geom[geomidx    ] + "," + //d
//d                                                                                (byte)geom[geomidx + 1] + "," +
//d                                                                                (byte)geom[geomidx + 2] + ")");
                        }
                        currpnt.set(nextpnt);
                    }
                    geomidx += 3;
                    cmndprocess = true;
                    break;


                case 0x0D:  // multiple bulge specified arcs
                    geomidx++;
                    while (true)
                    {
                        if (geom[geomidx] == 0 && geom[geomidx + 1] == 0)
                        {
                            geomidx += 2;
                            break;
                        }
                        if (cmndprocess)
                        {
                            // calculate nextpnt
                            nextpnt.set(currpnt.x + (double)((byte)geom[geomidx]    ) * geomscale,
                                        currpnt.y + (double)((byte)geom[geomidx + 1]) * geomscale, 0);
                            if (pendown)
                            {
//d                             System.out.println("        tcmd0x0D:DRAW mult x-y arc=(" + (byte)geom[geomidx    ] + "," + //d
//d                                                                                         (byte)geom[geomidx + 1] + "," +
//d                                                                                         (byte)geom[geomidx + 2] + ")");

                                drawArc_XY_Bulge(gc, currpnt, nextpnt, (byte)geom[geomidx + 2] * geomscale);
                            }
                            else
                            {
//d                             System.out.println("        tcmd0x0D:MOVE mult x-y arc=(" + (byte)geom[geomidx    ] + "," + //d
//d                                                                                         (byte)geom[geomidx + 1] + "," +
//d                                                                                         (byte)geom[geomidx + 2] + ")");
                            }
                            currpnt.set(nextpnt);
                        }
                        geomidx += 3;
                    }
                    cmndprocess = true;
                    break;


                case 0x0E:  // process next command only if vertical text
                    geomidx++;
                    if (textvert) // vertical
                        cmndprocess = true;
                    else
                        cmndprocess = false;
//d                 System.out.println("        tcmd0x0E:textvert=" + textvert + ",cmndprocess=" + cmndprocess);
                    break;


                case 0x0F:  // not used (?)
                    geomidx++;
                    System.out.println("        tcmd0x0F:0x0F NOT USED");
                    cmndprocess = true;
                    break;


                default:    // Draw or Move
                    if (cmndprocess)
                    {
                        // calculate nextpnt
                        double x, y;
                        char veclen = (char)(geomchar >>> 4); // vector length
                        switch (geomchar & 0x000F) // direction code
                        {
                            case 0x0:   x =  veclen * geomscale;        y =  0;                         break;
                            case 0x1:   x =  veclen * geomscale;        y =  veclen * geomscale / 2.0;  break;
                            case 0x2:   x =  veclen * geomscale;        y =  veclen * geomscale;        break;
                            case 0x3:   x =  veclen * geomscale / 2.0;  y =  veclen * geomscale;        break;
                            case 0x4:   x =  0;                         y =  veclen * geomscale;        break;
                            case 0x5:   x = -veclen * geomscale / 2.0;  y =  veclen * geomscale;        break;
                            case 0x6:   x = -veclen * geomscale;        y =  veclen * geomscale;        break;
                            case 0x7:   x = -veclen * geomscale;        y =  veclen * geomscale / 2.0;  break;
                            case 0x8:   x = -veclen * geomscale;        y =  0;                         break;
                            case 0x9:   x = -veclen * geomscale;        y = -veclen * geomscale / 2.0;  break;
                            case 0xA:   x = -veclen * geomscale;        y = -veclen * geomscale;        break;
                            case 0xB:   x = -veclen * geomscale / 2.0;  y = -veclen * geomscale;        break;
                            case 0xC:   x =  0;                         y = -veclen * geomscale;        break;
                            case 0xD:   x =  veclen * geomscale / 2.0;  y = -veclen * geomscale;        break;
                            case 0xE:   x =  veclen * geomscale;        y = -veclen * geomscale;        break;
                            case 0xF:   x =  veclen * geomscale;        y = -veclen * geomscale / 2.0;  break;
                            default:    x =  0;                         y =  0;                         break;
                        }
                        nextpnt.set(currpnt.x + x, currpnt.y + y, 0);
                        if (pendown)
                        {
//d                         System.out.println("        tcmd0x00:DRAW " +
//d                                            Integer.toHexString((int)(geomchar >>> 4)) +
//d                                            Integer.toHexString((int)(geomchar & 0x000F)) +
//d                                            " [" + nextpnt.x + ":" + nextpnt.y + "],delta=[" + x + ":" + y + "]");
                            gc.drawLine__ECS__cont__flat__wid_none(currpnt, nextpnt);
                        }
                        else
                        {
//d                         System.out.println("        tcmd0x00:MOVE " +
//d                                            Integer.toHexString((int)(geomchar >>> 4)) +
//d                                            Integer.toHexString((int)(geomchar & 0x000F)) +
//d                                            " [" + nextpnt.x + ":" + nextpnt.y + "],delta=[" + x + ":" + y + "]");
                        }
                        currpnt.set(nextpnt);
                    }
                    geomidx++;
                    cmndprocess = true;
            }   // end of switch on geomchar
        }   // end of geom array loop
//d     if (geomlen < geom.length)
//d         System.out.println("... }");
//d     else
//d         System.out.println("}");
    }   // end of draw method


    /**
     * Draw arc specified by x-y and bulge.
     * Used by commands 0x0C and 0x0D.
     * @param gc The graphics context.
     * @param currpnt Chord start.
     * @param nextpnt Chord end.
     * @param bulge The arc bulge.
     */
    public void drawArc_XY_Bulge(YxxfGfxContext gc,
                                 YxxfGfxPointW currpnt, YxxfGfxPointW nextpnt, double bulge)
    {
        if (bulge == 0.0)
        {
//d         System.out.println("                 bulge=" + bulge);
            gc.drawLine__ECS__cont__flat__wid_none(currpnt, nextpnt);
            return;
        }

        boolean angdircw;
        if (bulge < 0.0)
        {
            bulge = -bulge;
            angdircw = true; // CW
        }
        else
        {
            angdircw = false; // CCW
        }

        double chordlen = YxxfGfxContext.calcdis(currpnt.x, currpnt.y, nextpnt.x, nextpnt.y);
        double sagitta  = bulge * chordlen / 254.0; // chord midpoint to arc
        double radius   = ((chordlen * chordlen / 4) + (sagitta * sagitta)) / (sagitta + sagitta); // arc radius
        double apothem  = radius - sagitta; // chord minpoint to arc center
        double midpntx  = (currpnt.x + nextpnt.x) / 2.0; // chord midpoint
        double midpnty  = (currpnt.y + nextpnt.y) / 2.0; // chord midpoint
//d     double chordang = YxxfGfxContext.calcangle(currpnt.x, currpnt.y, nextpnt.x, nextpnt.y);

//d     System.out.println("                 bulge=" + bulge + ",angdircw=" + angdir);
//d     System.out.println("                 chordlen=" + chordlen);
//d     System.out.println("                 sagitta=" + sagitta);
//d     System.out.println("                 radius=" + radius);
//d     System.out.println("                 apothem=" + apothem);
//d     System.out.println("                 midpnt=(" + midpntx + " " + midpnty + ")");
//d     System.out.println("                 chordang_r=" + chordang + ",chordang_d=" + (chordang / (Math.PI / 180.0)));

        // begin finding center - develop normal to chord
        tmppnt1.set(nextpnt.x - currpnt.x, nextpnt.y - currpnt.y, nextpnt.z - currpnt.z);
//d     System.out.println("                 01:tmppnt1=" + tmppnt1);
        tmppnt1.normalize(); // vector along chord
//d     System.out.println("                 02:tmppnt1=" + tmppnt1);
        tmppnt2.crossProduct(tmppnt1, YxxfGfxPointW.Wz);
//d     System.out.println("                 03:tmppnt2=" + center);
        tmppnt2.normalize(); // normal vector to chord
//d     System.out.println("                 04:tmppnt2=" + center);

        double begang, endang, swgang;
        if (angdircw)
        {   // CW - negative swgang

            // find center
            center.set(midpntx + (apothem * tmppnt2.x), midpnty + (apothem * tmppnt2.y), 0);
//d         System.out.println("                 05:center =" + center);
//d         gc.drawCross_ECS(center);

            begang = YxxfGfxContext.calcangle(center.x, center.y, currpnt.x, currpnt.y);
//d         System.out.println("                 begang_r=" + begang + ",begang_d=" + (begang / (Math.PI / 180.0)));
            endang = YxxfGfxContext.calcangle(center.x, center.y, nextpnt.x, nextpnt.y);
//d         System.out.println("                 endang_r=" + endang + ",endang_d=" + (endang / (Math.PI / 180.0)));
            if (endang > begang)
                swgang = endang - begang - Math.PI - Math.PI;
            else
                swgang = endang - begang;
//d         System.out.println("                 swgang_r=" + swgang + ",swgang_d=" + (swgang / (Math.PI / 180.0)));
        }
        else
        {   // CCW - positive swgang

            // find center
            center.set(midpntx - (apothem * tmppnt2.x), midpnty - (apothem * tmppnt2.y), 0);
//d         System.out.println("                 05:center =" + center);
//d         gc.drawCross_ECS(center);

            begang = YxxfGfxContext.calcangle(center.x, center.y, currpnt.x, currpnt.y);
//d         System.out.println("                 begang_r=" + begang + ",begang_d=" + (begang / (Math.PI / 180.0)));
            endang = YxxfGfxContext.calcangle(center.x, center.y, nextpnt.x, nextpnt.y);
//d         System.out.println("                 endang_r=" + endang + ",endang_d=" + (endang / (Math.PI / 180.0)));
            if (endang < begang)
                swgang = endang - begang + Math.PI + Math.PI;
            else
                swgang = endang - begang;
//d         System.out.println("                 swgang_r=" + swgang + ",swgang_d=" + (swgang / (Math.PI / 180.0)));
        }

        gc.drawCircle__ECS__cont__flat__wid_none(center, radius, begang, swgang);
    }
}

