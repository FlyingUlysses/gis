//==============================================================================
// YxxfPrtViewLayout.java
//
// Viewport layout manager
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfPrtViewLayout.java,v 1.4 2003/04/14 12:38:42 ekarlo Exp $
// $Log: YxxfPrtViewLayout.java,v $
// Revision 1.4  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.3  2002/09/26 07:31:57  ekarlo
// Imprinter development check-in.
//
// Revision 1.2  2001-05-20 14:38:10-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.1  2001-05-17 02:52:30-06  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;


import java.awt.*;
import java.util.Hashtable;


/**
 * Viewport layout manager.
 *
 * Made from: BulletinLayout.java
 *            <i>Graphic Java </i>by David M. Geary, 1st and 2nd editions
 *
 * @author Ed Karlo - Y Systems, LLC
 */
public class YxxfPrtViewLayout
{
    /**
     * A hashtable
     */
    private
    Hashtable                   hash                = new Hashtable();
    
    
    /**
     * Constructor (empty)
     */
    public
    YxxfPrtViewLayout()
    {
    }


    //==========================================================================
    /**
     * Add a component to the view TODO
     * @param name
     * @param comp
     */
    public
    void addLayoutComponent(String name, YxxfPrtComponent comp)
    {
//d     System.out.println("YxxfPrtViewLayout|addLayoutComponent");
    }


    /**
     * Remove a component to the view TODO
     * @param comp
     */
    public
    void removeLayoutComponent(YxxfPrtComponent comp)
    {
//d     System.out.println("YxxfPrtViewLayout|removeLayoutComponent");
    }


    /**
     * Calculate the dimensions of the rectangle based on the size of the
     * components in the target. TODO
     * @param target The target set of components.
     * @return The dimension of the rectangle to be displayed. TODO
     */
    public
    Dimension preferredLayoutSize(YxxfPrtContainer target)
    {
//d     System.out.println("YxxfPrtViewLayout|preferredLayoutSize");
        Insets    insets      = target.getInsets();
        Dimension dim         = new Dimension(0,0);
        int       ncomponents = target.getComponentCount();
        YxxfPrtComponent comp;
        Dimension d;
        Rectangle size        = new Rectangle(0,0);
        Rectangle compSize;

        for (int i = 0 ; i < ncomponents ; i++) {
                                       comp = target.getComponent(i);

            if (comp.isVisible()) {
                d = comp.getSize();
                compSize = new Rectangle(comp.getLocation());
                compSize.width  = d.width;
                compSize.height = d.height;

                size = size.union(compSize);
            }
        }
        dim.width  += size.width  + insets.right;
        dim.height += size.height + insets.bottom;

        return dim;
    }


    /**
     * Calculate the dimensions of the rectangle based on the minimum size 
     * of the components in the target. TODO
     * @param target The collection of components.
     * @return The dimension of the rectangle to be displayed. TODO
     */
    public
    Dimension minimumLayoutSize(YxxfPrtContainer target)
    {
//d     System.out.println("YxxfPrtViewLayout|minimumLayoutSize");
        Insets    insets      = target.getInsets();
        Dimension dim         = new Dimension(0,0);
        int       ncomponents = target.getComponentCount();
        YxxfPrtComponent comp;
        Dimension d;
        Rectangle minBounds   = new Rectangle(0,0);
        Rectangle compMinBounds;

        for (int i = 0 ; i < ncomponents ; i++) {
            comp = target.getComponent(i);

            if (comp.isVisible()) {
                d = comp.getMinimumSize();
                compMinBounds = new Rectangle(comp.getLocation());
                compMinBounds.resize(d.width, d.height);

                minBounds = minBounds.union(compMinBounds);
            }
        }
        dim.width  += minBounds.width  + insets.right;
        dim.height += minBounds.height + insets.bottom;

        return dim;
    }


    /**
     * Resize the components in the target based on their preferred size.
     * @param target The collection of components.
     */
    public
    void layoutContainer(YxxfPrtContainer target)
    {
//d     System.out.println("YxxfPrtViewLayout|layoutContainer");
//      Insets    insets      = target.insets();
        int       ncomponents = target.getComponentCount();
        YxxfPrtComponent comp;
        Dimension sz, ps;
        Point     loc;

        for (int i = 0 ; i < ncomponents ; i++) {
            comp = target.getComponent(i);

            if (comp.isVisible()) {
                sz  = comp.getSize();
                ps  = comp.getPreferredSize();
                loc = getComponentLocation(comp);

//              if (sz.width < ps.width || sz.height < ps.height)
                if (sz.width != ps.width || sz.height != ps.height)
                    sz = ps;

                comp.setBounds(loc.x, loc.y,
                               sz.width, sz.height);
            }
        }
    }

    
    
    
    /**
     * Update the location coordinates of the component in the Layout 
     * hashtable or add it to the hashtable if it isn't there. 
     * TODO Is this named correctly? It appears to be a mutator of the hashtable.
     * @param comp The component.
     * @return The component location.
     */
    private Point getComponentLocation(YxxfPrtComponent comp)
    {
//      Insets insets = comp.getParent().insets();
        Point  loc    = comp.getLocation();

        if (!hash.containsKey(comp))
        {
            addComponentToHashtable(comp);
        }
        else
        {
            Point oldLocation = (Point)hash.get(comp);
            if (oldLocation.x != loc.x ||
                oldLocation.y != loc.y)
            {
                addComponentToHashtable(comp);
            }
        }
        return comp.getLocation();
    }


    /**
     * Add a component to the Layout hashtable.
     * @param comp The component.
     */
    private void addComponentToHashtable(YxxfPrtComponent comp)
    {
        Insets insets = comp.getParent().getInsets();
        Point  loc    = comp.getLocation();

        comp.setLocation(loc.x + insets.left, 
                         loc.y + insets.top);
        hash.put(comp, comp.getLocation());
    }
    //==========================================================================
}

