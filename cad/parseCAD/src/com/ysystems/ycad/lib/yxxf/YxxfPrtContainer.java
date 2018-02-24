//==============================================================================
// YxxfPrtContainer.java
//
// A collection of components
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfPrtContainer.java,v 1.8 2003/06/03 09:53:14 ekarlo Exp $
// $Log: YxxfPrtContainer.java,v $
// Revision 1.8  2003/06/03 09:53:14  ekarlo
// Fix xoffset= and yoffset= Prt imprinter funtionality.
//
// Revision 1.7  2003/05/08 11:24:03  ekarlo
// Remove warnings.
//
// Revision 1.6  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.5  2002/09/26 07:51:24  ekarlo
// Imprinter development check-in.
//
// Revision 1.4  2001-05-21 02:34:05-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.3  2001-05-20 14:38:12-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.2  2001-05-17 23:33:05-06  ekarlo
// Development check-in.
//
// Revision 1.1  2001-05-17 02:52:23-06  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;


import java.awt.*;
import java.util.*;


/**
 * A collection of components.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YxxfPrtContainer extends YxxfPrtComponent
{
    /**
     * Sub component list.
     */
    private
    Vector                      subcomps        = new Vector();

    /**
     * Insets (inside border).
     */
    private
    Insets                      insets          = new Insets(0, 0, 0, 0);


    /**
     * Layout Manager.
     */
    private
    YxxfPrtViewLayout           layoutman       = new YxxfPrtViewLayout();


    //==========================================================================
    /**
     * Constructor (empty)
     */
    public
    YxxfPrtContainer()
    {
        super();
    }

    /**
     * Constructor
     * @param jgc The Java Graphics Context.
     */
    public
    YxxfPrtContainer(Yxxf D, Graphics jgc)
    {
        super(D, jgc);
    }

    /**
     * Constructor
     * @param jgc The Java Graphics Context.
     */
    public
    YxxfPrtContainer(Yxxf D, Graphics jgc, int x, int y, int width, int height)
    {
        super(D, jgc, x, y, width, height);
    }
    //==========================================================================


    //==========================================================================
    /**
     * Add a component.
     */
    public
    void add(YxxfPrtComponent comp)
    {
        // System.out.println("++++++++++++YxxfPrtContainer:add(" + comp + ")");

        comp.parent = this;
        
        comp.getGC().setParentGC(this.getGC());

        subcomps.addElement(comp);

        layoutman.addLayoutComponent("", comp);
    }

    /**
     * Return count of sub components.
     */
    public
    int getComponentCount()
    {
        return subcomps.size();
    }

    /**
     * Gets component n of this container.
     * @param n The index of the component to return.
     * @return The component.
     */
    public
    YxxfPrtComponent getComponent(int n)
    {
        if ((n < 0) || (n >= subcomps.size()))
            throw new ArrayIndexOutOfBoundsException("Invalid component number (" + n + ")");
        return (YxxfPrtComponent)subcomps.elementAt(n);
    }
    //==========================================================================


    //==========================================================================
    /**
     * Set insets.
     * @param insets The insets.
     */
    public
    void setInsets(Insets insets)
    {
        this.insets.top     = insets.top;
        this.insets.left    = insets.left;
        this.insets.bottom  = insets.bottom;
        this.insets.right   = insets.right;
    }

    /**
     * Get copy of Insets.
     * @return The Insets copy.
     */
    public
    Insets getInsets()
    {
        return (Insets)insets.clone();
    }
    //==========================================================================


    //==========================================================================
    public
    void validateLayout()
    {
        super.validateLayout(); // validate the super.component

        // Layout container
        layoutman.layoutContainer(this);

        // Layout subcomponents
        for (Enumeration E1 = subcomps.elements(); E1.hasMoreElements(); )
        {
            YxxfPrtComponent comp = (YxxfPrtComponent)E1.nextElement();
            comp.validateLayout();
        }
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
        // System.out.println("++++++++++++YxxfPrtContainer:paint(" + jgc + ")");
        super.paint(jgc);
    }


    /**
     * Paints this component and all of its subcomponents.
     * @param jgc The Java Graphics object.
     */
    public
    void paintAll(Graphics jgc)
    {
        // System.out.println("++++++++++++YxxfPrtContainer:paintAll(" + jgc + ")");

        // paint this
        paint(jgc);

        // paint subs
        Enumeration E1 = subcomps.elements();
        while (E1.hasMoreElements())
        {
            YxxfPrtComponent comp = (YxxfPrtComponent)E1.nextElement();
            comp.paintAll(jgc);
        }
    }
    //==========================================================================
}

