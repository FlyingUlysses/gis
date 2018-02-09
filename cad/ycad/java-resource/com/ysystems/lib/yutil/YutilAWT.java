//==============================================================================
// YutilAWT.java
//
// Miscellaneous AWT support methods
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/lib/yutil/YutilAWT.java,v 1.8 2003/04/14 12:37:03 ekarlo Exp $
// $Log: YutilAWT.java,v $
// Revision 1.8  2003/04/14 12:37:03  ekarlo
// Update source file header for OSI release.
//
// Revision 1.7  2000/10/17 07:44:10  ekarlo
// Change package paths to lower case.
//
// Revision 1.6  1999-10-21 21:38:43-06  walter
// Added JavaDoc comments.
//
// Revision 1.5  1999-06-20 16:33:43-06  ekarlo
// Rearrange package names.
//
// Revision 1.4  1999/06/15  05:01:51  ekarlo
// Fix comment.
//
// Revision 1.3  1997/08/30  14:26:40  ekarlo
// Add method to return Applet reference.
//
// Revision 1.2  1997/07/21  20:49:59  ekarlo
// Minor reformat.
// Make class static.
//
// Revision 1.1  1997/07/13  18:13:53  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.lib.yutil;


import java.applet.Applet;
import java.awt.*;


/**
 * Miscellaneous AWT support methods.
 * The constrain() methods are from - <I>Java in a Nutshell</I>.
 * <br>This class may not be instantiated.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YutilAWT
{
    /**
     * Constructor to prevent instantiation.
     */
    private // Defeat instantiation
    YutilAWT()
    {
    }


    /**
     * Get the Applet associated with a Component.
     * @param component An AWT Component.
     * @return The Applet or null if there isn't an 
     * Applet associated with this component.
     */
    public static
    Applet getApplet(Component component)
    {
        Component c = component;

        if (c instanceof Applet)
            return (Applet)c;

        while ((c = c.getParent()) != null)
        {
            if(c instanceof Applet)
                return (Applet)c;
        }
        return null;
    }


    /**
     * Main constrain() method.
     * It has arguments for all constraints.
     * @param gridx 
     *   Specifies the cell at the left of the component's display 
     * area, where the leftmost cell has gridx = 0. The value RELATIVE 
     * specifies that the component be placed just to the right of the 
     * component that was added to the container just before this component 
     * was added. Default = RELATIVE.
     * @param gridy 
     *   Specifies the cell at the top of the component's display 
     * area, where the topmost cell has gridy = 0. The value RELATIVE 
     * specifies that the component be placed just below the component that 
     * was added to the container just before this component was added. 
     * Default = RELATIVE.
     * @param gridwidth 
     *    Specifies the number of cells in a row for the 
     * component's display area. Use REMAINDER to specify that the component 
     * be the last one in its row. Use RELATIVE to specify that the component 
     * be the next-to-last one in its row. Default = 1.
     * @param gridheight 
     *    Specifies the number of cells in a column for the 
     * component's display area. Use REMAINDER to specify that the component 
     * be the last one in its column. Use RELATIVE to specify that the 
     * component be the next-to-last one in its column. Default = 1.
     * @param fill 
     *    This field is used when the component's display area is 
     * larger than the component's requested size. It determines whether 
     * to resize the component, and if so, how. Values: NONE, HORIZONTAL, 
     * VERTICAL, BOTH. Default = NONE.
     * @param anchor 
     *    This field is used when the component is smaller 
     * than its display area. It determines where, within the area, to 
     * place the component. Values: CENTER, NORTH, NORTHEAST, EAST, 
     * SOUTHEAST, SOUTH, SOUTHWEST, WEST, NORTHWEST. Default = CENTER.
     * @param weightx 
     *    This field specifies how to distribute extra horizontal 
     * space. The grid bag layout manager calculates the weight of a column 
     * to be the maximum weighty of all the components in a row.  If the 
     * resulting layout is smaller horizontally than the area it needs to 
     * fill, the exta space is distributed to each column in proportion to 
     * its weight.  A column that has weight 0 receives no extra space.
     * If all the weights are zero, all the extra space appears between 
     * the grids of the cell and the right and left edges. Default = 0.
     * @param weighty 
     *    This field specifies how to distribute extra 
     * vertical space. The grid bag layout manager calculates the weight of 
     * a row to be the maximum weightx of all the components in a row. If 
     * the resulting layout is smaller vertically than the area it needs to 
     * fill, the extra space is distributed to each row in proportion to 
     * its weight. A row that has weight 0 receives no extra space. If all 
     * the weights are zero, all the extra space appears between the grids of
     * the cell and the top and bottom edges. Default = 0.
     * @param ipadx 
     *    This field specifies the internal padding, that is, how 
     * much space to add to the minimum width of the component. The width 
     * of the component is at least its minimum width plus ipadx*2 pixels.
     * @param ipady 
     *    This field specifies the internal padding, that is, 
     * how much space to add to the minimum height of the component. The 
     * height of the component is at least its minimum height plus ipady*2 
     * pixels.
     * @param top
     *    This field specifies the external padding of the component, the 
     * minimum amount of space between the component and the edges of its 
     * display area. Default = Insets(0, 0, 0, 0)
     * @param left
     *    This field specifies the external padding of the component, the 
     * minimum amount of space between the component and the edges of its 
     * display area. Default = Insets(0, 0, 0, 0)
     * @param bottom
     *    This field specifies the external padding of the component, the 
     * minimum amount of space between the component and the edges of its 
     * display area. Default = Insets(0, 0, 0, 0)
     * @param right
     *    This field specifies the external padding of the component, the 
     * minimum amount of space between the component and the edges of its 
     * display area. Default = Insets(0, 0, 0, 0)
     */
    public static
    void constrain(Container container, Component component,

        int gridx,
        // Specifies the cell at the left of the component's display area, where the
        // leftmost cell has gridx = 0.  The value RELATIVE specifies that the component
        // be placed just to the right of the component that was added to the
        // container just before this component was added.
        // Default = RELATIVE
        int gridy,
        // Specifies the cell at the top of the component's display area, where the
        // topmost cell has gridy = 0.  The value RELATIVE specifies that the component
        // be placed just below the component that was added to the
        // container just before this component was added.
        // Default = RELATIVE


        int gridwidth,
        // Specifies the number of cells in a row for the component's display area.
        // Use REMAINDER to specify that the component be the last one in its row.
        // Use RELATIVE to specify that the component be the next-to-last one in its row.
        // Default = 1
        int gridheight,
        // Specifies the number of cells in a column for the component's display area.
        // Use REMAINDER to specify that the component be the last one in its column.
        // Use RELATIVE to specify that the component be the next-to-last one in its column.
        // Default = 1


        int fill,
        // This field is used when the component's display area is larger than the
        // component's requested size.
        // It determines whether to resize the component, and if so, how.
        // Values: NONE, HORIZONTAL, VERTICAL, BOTH
        // Default = NONE


        int anchor,
        // This field is used when the component is smaller than its display area.
        // It determines where, within the area, to place the component.
        // Values: CENTER, NORTH, NORTHEAST, EAST, SOUTHEAST, SOUTH, SOUTHWEST, WEST, NORTHWEST.
        // Default = CENTER


        double weightx,
        // This field specifies how to distribute extra horizontal space.
        // The grid bag layout manager calculates the weight of a column to be the
        // maximum weighty of all the components in a row.  If the resulting layout is
        // smaller horizontally than the area it needs to fill, the exta space is distributed
        // to each column in proportion to its weight.  A column that has weight 0
        // receives no extra space.
        // If all the weights are zero, all the extra space sppears between the grids of
        // the cell and the right and left edges.
        // Default = 0
        double weighty,
        // This field specifies how to distribute extra vertical space.
        // The grid bag layout manager calculates the weight of a row to be the maximum
        // weightx of all the components in a row.  If the resulting layout is
        // smaller vertically than the area it needs to fill, the extra space is distributed to
        // each row in proportion to its weight.  A row that has weight 0 receives no extra space.
        // If all the weights are zero, all the extra space appears between the grids of
        // the cell and the top and bottom edges.
        // Default = 0


        int ipadx,
        // This field specifies the internal padding, that is, how much space to add to
        // the minimum width of the component.  The width of the component is at
        // least its minimum width plus ipadx*2 pixels.
        int ipady,
        // This field specifies the internal padding, that is, how much space to add to
        // the minimum height of the component.  The height of the component is at
        // least its minimum height plus ipady*2 pixels.


        int top, int left, int bottom, int right
        // This field specifies the external padding of the component, the minimum
        // amount of space between the component and the edges of its display area.
        // Default = Insets(0, 0, 0, 0)

        )
    {
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = gridx;
        c.gridy = gridy;

        c.gridwidth  = gridwidth;
        c.gridheight = gridheight;

        c.fill = fill;

        c.anchor = anchor;

        c.weightx = weightx;
        c.weighty = weighty;

        if ((top + bottom + left + right) > 0)
            c.insets = new Insets(top, left, bottom, right);

        ((GridBagLayout)container.getLayout()).setConstraints(component, c);

        container.add(component);
    }


    /**
     * A version of constrain() that positions a component
     * that does not grow, but does have margins.
     * @param gridx
     * @param gridy
     * @param gridwidth
     * @param gridheight
     * @param top
     * @param left
     * @param bottom
     * @param right
     * @see YutilAWT#constrain(Container,Component,int,int,int,int,int,int,double,double,int,int,int,int,int,int) constrain
     */
    public static
    void constrain(Container container, Component component,
        int gridx, int gridy,
        int gridwidth, int gridheight,
        int top, int left, int bottom, int right)
    {
        constrain(container, component,
            gridx, gridy,
            gridwidth, gridheight,
            GridBagConstraints.NONE, // fill
            GridBagConstraints.NORTHWEST, // anchor
            0.0, 0.0, // weightx, weighty
            0, 0, // ipadx, ipady
            top, left, bottom, right); // Insets()
    }


    /**
     * A version of constrain() that positions a component
     * that does not grow and does not have margins.
     * @param gridx
     * @param gridy
     * @param gridwidth
     * @param gridheight
     * @see YutilAWT#constrain(Container,Component,int,int,int,int,int,int,double,double,int,int,int,int,int,int) constrain
     */
    public static
    void constrain(Container container, Component component,
        int gridx, int gridy,
        int gridwidth, int gridheight)
    {
        constrain(container, component,
            gridx, gridy,
            gridwidth, gridheight,
            GridBagConstraints.NONE, // fill
            GridBagConstraints.NORTHWEST, // anchor
            0.0, 0.0, // weightx, weighty
            0, 0, // ipadx, ipady
            0, 0, 0, 0); // Insets()
    }
}
