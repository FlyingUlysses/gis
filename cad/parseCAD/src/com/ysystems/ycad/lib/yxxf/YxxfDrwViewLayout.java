//==============================================================================
// YxxfDrwViewLayout.java
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfDrwViewLayout.java,v 1.10 2003/04/14 12:38:42 ekarlo Exp $
// $Log: YxxfDrwViewLayout.java,v $
// Revision 1.10  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.9  2000/10/17 07:44:01  ekarlo
// Change package paths to lower case.
//
// Revision 1.8  1999-09-15 10:08:45-06  walter
// Added JavaDoc comments.
//
// Revision 1.7  1999-08-10 08:07:39-06  ekarlo
// Remove unused var warning.
//
// Revision 1.6  1999/07/09  20:10:50  ekarlo
// Rearrange package names.
//
// Revision 1.5  1999/06/20  22:34:10  ekarlo
// Rearrange package names.
//
// Revision 1.4  1999/02/09  14:50:48  ekarlo
// Deactivate console print.
//
// Revision 1.3  1999/02/08  05:10:53  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.2  1998/02/02  19:14:13  ekarlo
// Update to latest Graphic Java code (w/ fix).
//
// Revision 1.1  1997/07/21  22:37:49  ekarlo
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
public class YxxfDrwViewLayout implements LayoutManager
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
    YxxfDrwViewLayout()
    {
    }


    //==========================================================================
    /**
     * Add a component to the view TODO
     * @param name
     * @param comp
     */
    public
    void addLayoutComponent(String name, Component comp)
    {
//d     System.out.println("YxxfDrwViewLayout|addLayoutComponent");
    }


    /**
     * Remove a component to the view TODO
     * @param comp
     */
    public
    void removeLayoutComponent(Component comp)
    {
//d     System.out.println("YxxfDrwViewLayout|removeLayoutComponent");
    }


    /**
     * Calculate the dimensions of the rectangle based on the size of the
     * components in the target. TODO
     * @param target The target set of components.
     * @return The dimension of the rectangle to be displayed. TODO
     */
    public
    Dimension preferredLayoutSize(Container target)
    {
//d     System.out.println("YxxfDrwViewLayout|preferredLayoutSize");
        Insets    insets      = target.insets();
        Dimension dim         = new Dimension(0,0);
        int       ncomponents = target.countComponents();
        Component comp;
        Dimension d;
        Rectangle size        = new Rectangle(0,0);
        Rectangle compSize;

        for (int i = 0 ; i < ncomponents ; i++) {
            comp = target.getComponent(i);

            if (comp.isVisible()) {
                d = comp.size();
                compSize = new Rectangle(comp.location());
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
    Dimension minimumLayoutSize(Container target)
    {
//d     System.out.println("YxxfDrwViewLayout|minimumLayoutSize");
        Insets    insets      = target.insets();
        Dimension dim         = new Dimension(0,0);
        int       ncomponents = target.countComponents();
        Component comp;
        Dimension d;
        Rectangle minBounds   = new Rectangle(0,0);
        Rectangle compMinBounds;

        for (int i = 0 ; i < ncomponents ; i++) {
            comp = target.getComponent(i);

            if (comp.isVisible()) {
                d = comp.minimumSize();
                compMinBounds = new Rectangle(comp.location());
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
    void layoutContainer(Container target)
    {
//d     System.out.println("YxxfDrwViewLayout|layoutContainer");
//      Insets    insets      = target.insets();
        int       ncomponents = target.countComponents();
        Component comp;
        Dimension sz, ps;
        Point     loc;

        for (int i = 0 ; i < ncomponents ; i++) {
            comp = target.getComponent(i);

            if (comp.isVisible()) {
                sz  = comp.size();
                ps  = comp.preferredSize();
                loc = getComponentLocation(comp);

//              if (sz.width < ps.width || sz.height < ps.height)
                if (sz.width != ps.width || sz.height != ps.height)
                    sz = ps;

                comp.reshape(loc.x, loc.y,
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
    private Point getComponentLocation(Component comp)
    {
//      Insets insets = comp.getParent().insets();
        Point  loc    = comp.location();

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
        return comp.location();
    }


    /**
     * Add a component to the Layout hashtable.
     * @param comp The component.
     */
    private void addComponentToHashtable(Component comp)
    {
        Insets insets = comp.getParent().insets();
        Point  loc    = comp.location();

        comp.move(loc.x + insets.left, 
                  loc.y + insets.top);
        hash.put(comp, comp.location());
    }
    //==========================================================================
}

