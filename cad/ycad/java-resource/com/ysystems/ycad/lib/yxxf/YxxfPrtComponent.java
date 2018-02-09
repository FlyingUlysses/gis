//==============================================================================
// YxxfPrtComponent.java
//
// Represents a single graphical area - similar to the AWT Canvas
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfPrtComponent.java,v 1.10 2003/06/03 09:53:14 ekarlo Exp $
// $Log: YxxfPrtComponent.java,v $
// Revision 1.10  2003/06/03 09:53:14  ekarlo
// Fix xoffset= and yoffset= Prt imprinter funtionality.
//
// Revision 1.9  2003/05/08 11:24:03  ekarlo
// Remove warnings.
//
// Revision 1.8  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.7  2002/09/27 08:36:36  ekarlo
// Fix missing method error on MSIE with built in VM.
//
// Revision 1.6  2002-09-26 01:51:24-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.5  2001-05-21 02:34:05-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.4  2001-05-20 14:38:12-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.3  2001-05-20 02:43:00-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.2  2001-05-17 23:33:05-06  ekarlo
// Development check-in.
//
// Revision 1.1  2001-05-17 02:52:31-06  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;


import java.awt.*;


/**
 * Represents a single graphical area - similar to the AWT Canvas.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YxxfPrtComponent
{
    //==========================================================================
    // Structure items.

    /**
     * The parent of the object. It may be null for top-level components.
     * @see #getParent
     */
    YxxfPrtContainer            parent;
    //==========================================================================


    //==========================================================================
    // Display items.

    /**
     * Graphics context for this view.
     */
    protected
    YxxfGfxContext              gc                  = new YxxfGfxContext();
    //==========================================================================


    //==========================================================================
    /**
     * Constructor (empty)
     */
    public
    YxxfPrtComponent()
    {
    }

    /**
     * Constructor
     * @param jgc The Java Graphics Context.
     */
    public
    YxxfPrtComponent(Yxxf D, Graphics jgc)
    {
        setDrawing(D);
        setJGC(jgc);
    }

    /**
     * Constructor
     * @param jgc The Java Graphics Context.
     */
    public
    YxxfPrtComponent(Yxxf D, Graphics jgc, int x, int y, int width, int height)
    {
        setDrawing(D);
        setJGC(jgc);
        setDspwin(x, y, width, height);
    }


    /**
     * Set drawing reference.
     * @param D The drawing reference.
     */
    public
    void setDrawing(Yxxf D)
    {
        gc.setDrawing(D);
    }

    /**
     * Get drawing reference.
     * @return The drawing reference.
     */
    public
    Yxxf getDrawing()
    {
        return gc.getDrawing();
    }


    /**
     * Set Java Graphics Context
     * @param jgc The context.
     */
    public
    void setJGC(Graphics jgc)
    {
        gc.setJGC(jgc);
    }

    /**
     * Get Java Graphics Context.
     * @return The context.
     */
    public
    Graphics getJGC()
    {
        return gc.getJGC();
    }


    /**
     * Set Java Graphics Context shared flag
     * @param sharedjgcflag Boolean flag.
     */
    public
    void setSharedJGCFlag(boolean sharedjgcflag)
    {
        gc.setSharedJGCFlag(sharedjgcflag);
    }

    /**
     * Get Java Graphics Context shared flag.
     * @return Current sharedjgcflag value.
     */
    public
    boolean getSharedJGCFlag()
    {
        return gc.getSharedJGCFlag();
    }


    /**
     * Set display window x and y from ints.
     * @param x X.
     * @param x Y.
     */
    public
    void setDspwinXY(int x, int y)
    {
        gc.setDspwinXY(x, y);
    }

    /**
     * Set display window width and height from ints.
     * @param width Width.
     * @param height Height.
     */
    public
    void setDspwinWidthHeight(int width, int height)
    {
        gc.setDspwinWidthHeight(width, height);
    }

    /**
     * Set display window x and y from a Point.
     * @param p Point.
     */
    public
    void setDspwin(Point p)
    {
        gc.setDspwin(p);
    }

    /**
     * Set display window width and height from a Dimension.
     * @param d Dimension.
     */
    public
    void setDspwin(Dimension d)
    {
        gc.setDspwin(d);
    }

    /**
     * Set display window x, y, width and height from ints.
     * @param x X.
     * @param y Y.
     * @param width Width.
     * @param height Height.
     */
    public
    void setDspwin(int x, int y, int width, int height)
    {
        gc.setDspwin(x, y, width, height);
    }

    /**
     * Set display window x, y, width and height from a Rectangle.
     * @param r Rectangle.
     */
    public
    void setDspwin(Rectangle r)
    {
        gc.setDspwin(r);
    }

    /**
     * Set display window x.
     * @param x X.
     */
    public
    void setDspwinX(int x)
    {
        gc.setDspwinX(x);
    }

    /**
     * Set display window y.
     * @param y Y.
     */
    public
    void setDspwinY(int y)
    {
        gc.setDspwinY(y);
    }

    /**
     * Set display window width.
     * @param width Width.
     */
    public
    void setDspwinWidth(int width)
    {
        gc.setDspwinWidth(width);
    }

    /**
     * Set display window height.
     * @param height Height.
     */
    public
    void setDspwinHeight(int height)
    {
        gc.setDspwinHeight(height);
    }

    /**
     * Get display window x and y as a Point.
     */
    public
    Point getDspwinXY()
    {
        return gc.getDspwinXY();
    }

    /**
     * Get display window width and height as a Dimension.
     */
    public
    Dimension getDspwinWidthHeight()
    {
        return gc.getDspwinWidthHeight();
    }

    /**
     * Get display window x, y, width and height as a Rectangle.
     */
    public
    Rectangle getDspwin()
    {
        return gc.getDspwin();
    }

    /**
     * Get display window x.
     */
    public
    int getDspwinX()
    {
        return gc.getDspwinX();
    }

    /**
     * Get display window y.
     */
    public
    int getDspwinY()
    {
        return gc.getDspwinY();
    }

    /**
     * Get display window width.
     */
    public
    int getDspwinWidth()
    {
        return gc.getDspwinWidth();
    }

    /**
     * Get display window height.
     */
    public
    int getDspwinHeight()
    {
        return gc.getDspwinHeight();
    }


    /**
     * Get the Graphics context.
     */
    public
    YxxfGfxContext getGC()
    {
        return gc;
    }
    //==========================================================================


    //==========================================================================
    /**
     * Set parent.
     * @return The parent.
     */
    public
    void setParent(YxxfPrtContainer comp)
    {
        parent = comp;
    }

    /**
     * Get parent.
     * @return The parent.
     */
    public
    YxxfPrtContainer getParent()
    {
        return parent;
    }
    //==========================================================================


    //==========================================================================
    /**
     * Get visibility (always visible).
     * @return visibilty state.
     */
    public
    boolean isVisible()
    {
        return true;
    }
    //==========================================================================


    //==========================================================================
    /**
     * Get background as Color.
     */
    public
    Color getBGColor_Color()
    {
        return gc.getBGColor_Color();
    }

    /**
     * Get background as String.
     */
    public
    String getBGColor_String()
    {
        return gc.getBGColor_String();
    }

    /**
     * Set background as Color.
     */
    public
    void setBGColor(Color colval)
    {
        gc.setBGColor(colval);
    }

    /**
     * Set background as String.
     */
    public
    void setBGColor(String strval)
    {
        gc.setBGColor(strval);
    }
    //==========================================================================


    //==========================================================================
    /**
     * Set location.
     */
    public
    void setLocation(int x, int y)
    {
        // System.out.println("++++++++++++YxxfPrtComponent:setLocation [" + x + ":" + y + "]");

        gc.setDspwinXY(x, y);
    }

    /**
     * Get location.
     * @return The location Point.
     */
    public
    Point getLocation()
    {
        // System.out.println("++++++++++++YxxfPrtComponent:getLocation [" + gc.getDspwinX() + ":" + gc.getDspwinY() + "]");

        return gc.getDspwinXY();
    }

    /**
     * Get x.
     */
    public
    int getX()
    {
        return gc.getDspwinX();
    }

    /**
     * Get y.
     */
    public
    int getY()
    {
        return gc.getDspwinY();
    }
    //==========================================================================


    //==========================================================================
    /**
     * Set size.
     */
    public
    void setSize(int width, int height)
    {
        // System.out.println("++++++++++++YxxfPrtComponent:setSize [" + width + ":" + height + "]");

        gc.setDspwinWidthHeight(width, height);
    }

    /**
     * Get size.
     * @return The size Dimension.
     */
    public
    Dimension getSize()
    {
        // System.out.println("++++++++++++YxxfPrtComponent:getSize [" + gc.getDspwinWidth() + ":" + gc.getDspwinHeight() + "]");

        return getDspwinWidthHeight();
    }

    /**
     * Get width.
     */
    public
    int getWidth()
    {
        return gc.getDspwinWidth();
    }

    /**
     * Get height.
     */
    public
    int getHeight()
    {
        return gc.getDspwinHeight();
    }
    //==========================================================================


    //==========================================================================
    /**
     * Set bounds.
     */
    public
    void setBounds(int x, int y, int width, int height)
    {
        // System.out.println("++++++++++++YxxfPrtComponent:setBounds [" + x + ":" + y + ":" + width + ":" + height + "]");

        gc.setDspwin(x, y, width, height);
    }

    /**
     * Calculate bounds.
     * @return The bounds.
     */
    public
    Rectangle getBounds()
    {
        // System.out.println("++++++++++++YxxfPrtComponent:getBounds [" + gc.getDspwinX() + ":" + gc.getDspwinY() + ":" + gc.getDspwinWidth() + ":" + gc.getDspwinHeight() + "]");

        return getDspwin();
    }
    //==========================================================================


    //==========================================================================
    /**
     * Calculate the preferred dimensions of the Viewport based 
     * on the parent Viewport dimensions.
     * @return The Viewport dimensions.
     */
    public
    Dimension getPreferredSize()
    {
        // System.out.println("++++++++++++YxxfPrtComponent:getPreferredSize");

        return new Dimension(gc.getDspwinWidth(), gc.getDspwinHeight());
    }


    /**
     * Calculate the minimum size of the Viewport based on the parent Viewport.
     * @return The minimum Viewport dimensions.
     */
    public
    Dimension getMinimumSize()
    {
        // System.out.println("++++++++++++YxxfPrtComponent:getMinimumSize");

        return new Dimension(gc.getDspwinWidth(), gc.getDspwinHeight());
    }


    /**
     * Validate the components layout.
     * Synchronizes the gc.jgc to the gc.Dspwin layout.
     */
    public
    void validateLayout()
    {
        // System.out.println("++++++++++++YxxfPrtComponent:validateLayout(" + gc.getDspwinX() + "," +
        //                                                                     gc.getDspwinY() + "," +
        //                                                                     gc.getDspwinWidth() + "," +
        //                                                                     gc.getDspwinHeight() + ")");

        gc.syncJGCtoDspwin();
    }
    //==========================================================================


    //==========================================================================
    /**
     * Paints this component.
     * @param jgc The Java Graphics object.
     */
    public
    void paint(Graphics jgc)
    {
        // System.out.println("++++++++++++YxxfPrtComponent:paint(" + jgc + ")");
    }


    /**
     * Paints this component and all of its subcomponents.
     * @param jgc The Java Graphics object.
     */
    public
    void paintAll(Graphics jgc)
    {
        // System.out.println("++++++++++++YxxfPrtComponent:paintAll(" + jgc + ")");

        // paint this
        paint(jgc);
    }
    //==========================================================================
}

