//==============================================================================
// YdxfSecTables.java
//
// TABLES section
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfSecTables.java,v 1.20 2003/05/08 11:24:03 ekarlo Exp $
// $Log: YxxfSecTables.java,v $
// Revision 1.20  2003/05/08 11:24:03  ekarlo
// Remove warnings.
//
// Revision 1.19  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.18  2002/09/29 05:39:47  ekarlo
// Fix stats display.
//
// Revision 1.17  2002-09-28 23:20:56-06  ekarlo
// Change prefix of entity print options to "list".
//
// Revision 1.16  2000-10-17 01:43:41-06  ekarlo
// Change package paths to lower case.
//
// Revision 1.15  2000-02-23 20:29:08-07  ekarlo
// Fix style name display.
//
// Revision 1.14  1999-09-08 13:19:15-06  walter
// Add JavaDoc comments.
//
// Revision 1.13  1999-07-09 14:10:50-06  ekarlo
// Rearrange package names.
//
// Revision 1.12  1999/07/08  02:58:44  ekarlo
// Fix shape load.
//
// Revision 1.11  1999/06/20  22:34:10  ekarlo
// Rearrange package names.
//
// Revision 1.10  1999/02/08  05:11:30  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.9  1999/01/28  04:31:28  ekarlo
// Text - phase 4.
//
// Revision 1.8  1998/11/24  19:53:50  ekarlo
// Text - phase 2.
//
// Revision 1.7  1997/08/30  14:19:42  ekarlo
// Add linetype table.
//
// Revision 1.6  1997/07/21  22:51:37  ekarlo
// Rename from YdxfSecTables.java to YdxfSecTables.java.
//
// Revision 1.5  1996/10/26  00:08:42  ekarlo
// Change layer list print.
//
// Revision 1.4  1996/09/27  09:37:03  ekarlo
// Add text - v1.
//
// Revision 1.3  1996/09/26  18:02:24  ekarlo
// Add lookup method using vportnum.
//
// Revision 1.2  1996/07/30  06:29:43  ekarlo
// Print full vport values.
//
// Revision 1.1  1996/07/02  01:58:26  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;


import java.util.*;


/**
 * TABLES section.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YxxfSecTables
{
    /**
     * VPORT Collection TODO
     */
    public
    Vector                      tblVport        = new Vector();

    /**
     * LTYPE Collection TODO
     */
    public
    Hashtable                   tblLtype        = new Hashtable();

    /**
     * LAYER Collection TODO
     */
    public
    Hashtable                   tblLayer        = new Hashtable();

    /**
     * STYLE Collection TODO
     */
    public
    Hashtable                   tblStyle        = new Hashtable();
    public
    YxxfTblStyle                tblStyle_default_style 
                                                = null;

    // VIEW

    // UCS

    // APPID

    // DIMSTYLE

    // APPID

    // BLOCK_RECORD


    //
    // V P O R T
    //
    /**
     * Find a Vport given a name.
     * @param name The Vport name.
     * @return A Vport reference or null if not in tblVport.
     */
    public
    YxxfTblVport findVport(String name)
    {
        Enumeration E = tblVport.elements();
        YxxfTblVport vport;

        while (E.hasMoreElements())
        {
            vport = (YxxfTblVport)E.nextElement();
            if (vport.name.equals(name))
            {
                return vport;
            }
        }

        // not found
        return null;
    }

    /**
     * Find a Vport given a name and number.
     * @param name The Vport name.
     * @param vptnum The Vport number.
     * @return The Vport reference with this name and number 
     * or null if not in tblVport.
     */
    public
    YxxfTblVport findVport(String name, int vptnum)
    {
        Enumeration E = tblVport.elements();
        YxxfTblVport vport;
        int matchcnt = 0;

        while (E.hasMoreElements())
        {
            vport = (YxxfTblVport)E.nextElement();
            if (vport.name.equals(name))
            {
                if (++matchcnt == vptnum)
                    return vport;
            }
        }

        // not found
        return null;
    }


    /**
     * Find a Vport given a YxxfTblVport.
     * @param vpt The Vport .
     * @return A Vport reference equal to vpt or null if not in tblVport.
     */
    public
    YxxfTblVport findVport(YxxfTblVport vpt)
    {
        Enumeration E = tblVport.elements();
        YxxfTblVport vport;

        while (E.hasMoreElements())
        {
            vport = (YxxfTblVport)E.nextElement();
            if (vport.name.equals(vpt.name))
            {
                return vport;
            }
        }

        // not found
        return null;
    }


    /**
     * List the Vports in the tblVport collection.
     */
    public
    void listVports()
    {
        System.out.println("Vport size=" + tblVport.size());
        Enumeration E = tblVport.elements();
        YxxfTblVport vport;

        while (E.hasMoreElements())
        {
            vport = (YxxfTblVport)E.nextElement();
            System.out.println("=== VPORT ===================================");
            System.out.println(vport);
            System.out.println("=============================================");
        }
    }




    //
    // L T Y P E
    //

    /**
     * Find a Ltype given a name.
     * @param name The name.
     * @return The Table LType with the name.
     */
    public
    YxxfTblLtype findLtype(String name)
    {
        return (YxxfTblLtype)tblLtype.get(new YxxfTblLtypeKey(name));
    }


    /**
     * Find a Ltype given a name or create and add it to the collection 
     * it if it doesn't exist.
     * @param name The Ltype name.
     * @return An Ltype with the name.
     */
    public
    YxxfTblLtype findLtype_add(String name)
    {
        YxxfTblLtype foundltype = (YxxfTblLtype)tblLtype.get(new YxxfTblLtypeKey(name));

        if (foundltype != null) // found - return it
            return foundltype;

        // not found - create a new ltype with default values
        foundltype = new YxxfTblLtype(name);
        tblLtype.put(foundltype, foundltype); // ltype is it's own key
        return foundltype;
    }

    /**
     * Find Ltype given a name.
     * @param name The name of a YxxfTblLtype.
     * @return The Ltype with the name.
     */
    public
    YxxfTblLtype findLtype(char[] name)
    {
        return (YxxfTblLtype)tblLtype.get(new YxxfTblLtypeKey(name));
    }


    /**
     * Find the Ltype given a name or create it if
     * it is not in the tblLtype collection.
     * @param name The name of a YxxfTblLtype.
     * @return The Ltype with the name.
     */
    public
    YxxfTblLtype findLtype_add(char[] name)
    {
        YxxfTblLtype foundltype = (YxxfTblLtype)tblLtype.get(new YxxfTblLtypeKey(name));

        if (foundltype != null) // found - return it
            return foundltype;

        // not found - create a new ltype with default values
        foundltype = new YxxfTblLtype(name);
        tblLtype.put(foundltype, foundltype); // ltype is it's own key
        return foundltype;
    }


    /**
     * Find an Ltype given a key.
     * @param ltype The key of the Ltype.
     * @return The Ltype with the name.
     */
    public
    YxxfTblLtype findLtype(YxxfTblLtypeKey ltype)
    {
        return (YxxfTblLtype)tblLtype.get(ltype);
    }


    /**
     * Find Ltype given a key or create it and add it to the collection if it 
     * doesn't exist.
     * @param ltype The key of the Ltype.
     * @return The Ltype with the key.
     */
    public
    YxxfTblLtype findLtype_add(YxxfTblLtypeKey ltype)
    {
        YxxfTblLtype foundltype = (YxxfTblLtype)tblLtype.get(ltype);

        if (foundltype != null) // found - return it
            return foundltype;

        // not found - create a new ltype with default values
        foundltype = new YxxfTblLtype(ltype.name);
        tblLtype.put(foundltype, foundltype); // ltype is it's own key
        return foundltype;
    }

    /**
     * List the values of the Ltypes in
     * the tblLtype collection.
     */
    public
    void listLtypes()
    {
        Enumeration E = tblLtype.elements();
        YxxfTblLtype ltype;

        while (E.hasMoreElements())
        {
            ltype = (YxxfTblLtype)E.nextElement();

            System.out.println("=== LTYPE ===================================");
            System.out.println("    name=[" + ltype.getName() + "]");
            System.out.println("    flags=" + ltype.flags +
                               ",desc=[" + ltype.desc + "]");
            System.out.println("    aligncode=" + ltype.aligncode +
                               ",dashlencount=" + ltype.dashlencount +
                               ",patternlen=" + ltype.patternlen);
            System.out.println("    lenlist=" + ltype.lenlist);
            System.out.println("    namelist=" + ltype.namelist);
            System.out.println("    scalelist=" + ltype.scalelist);
            System.out.println("    rotlist=" + ltype.rotlist);
            System.out.println("    xlist=" + ltype.xlist);
            System.out.println("    ylist=" + ltype.ylist);
            System.out.println("=============================================");
        }
    }


    //
    // L A Y E R
    //

    /**
     * Find the Layer given a name.
     * @param name The name.
     * @return The Layer with the name.
     */
    public
    YxxfTblLayer findLayer(String name)
    {
        return (YxxfTblLayer)tblLayer.get(new YxxfTblLayerKey(name));
    }


    /**
     * Find Layer given a name or create it and add it to the collection 
     * if it doesn't exist.
     * @param name The name of the Layer.
     * @return The Layer with the name.
     */
    public
    YxxfTblLayer findLayer_add(String name)
    {
        YxxfTblLayer foundlayer = (YxxfTblLayer)tblLayer.get(new YxxfTblLayerKey(name));

        if (foundlayer != null) // found - return it
            return foundlayer;

        // not found - create a new layer with default values
        foundlayer = new YxxfTblLayer(name);
        foundlayer.ltype = findLtype_add(YxxfTblLtypeKey.STR_LTYPENAME__CONTINUOUS);
        tblLayer.put(foundlayer, foundlayer); // layer is it's own key
        return foundlayer;
    }


    /**
     * Find Layer given a name.
     * @param name The name of the Layer.
     * @return The Layer with the name.
     */
    public
    YxxfTblLayer findLayer(char[] name)
    {
        return (YxxfTblLayer)tblLayer.get(new YxxfTblLayerKey(name));
    }


    /**
     * Find Layer given a name or create it and add it to the collection 
     * if it doesn't exist.
     * @param name The name of the Layer.
     * @return The Layer with the name.
     */
    public
    YxxfTblLayer findLayer_add(char[] name)
    {
        YxxfTblLayer foundlayer = (YxxfTblLayer)tblLayer.get(new YxxfTblLayerKey(name));

        if (foundlayer != null) // found - return it
            return foundlayer;

        // not found - create a new layer with default values
        foundlayer = new YxxfTblLayer(name);
        foundlayer.ltype = findLtype_add(YxxfTblLtypeKey.STR_LTYPENAME__CONTINUOUS);
        tblLayer.put(foundlayer, foundlayer); // layer is it's own key
        return foundlayer;
    }

    /**
     * Find Layer given a key.
     * @param layer The key of the YxxfTblLayer.
     * @return The Layer with the key.
     */
    public
    YxxfTblLayer findLayer(YxxfTblLayerKey layer)
    {
        return (YxxfTblLayer)tblLayer.get(layer);
    }


    /**
     * Find Layer given a key or create it and add it to the collection 
     * if it doesn't exist.
     * @param layer The key of the Layer.
     * @return The Layer with the key.
     */
    public
    YxxfTblLayer findLayer_add(YxxfTblLayerKey layer)
    {
        YxxfTblLayer foundlayer = (YxxfTblLayer)tblLayer.get(layer);

        if (foundlayer != null) // found - return it
            return foundlayer;

        // not found - create a new layer with default values
        foundlayer = new YxxfTblLayer(layer.name);
        foundlayer.ltype = findLtype_add(YxxfTblLtypeKey.STR_LTYPENAME__CONTINUOUS);
        tblLayer.put(foundlayer, foundlayer); // layer is it's own key
        return foundlayer;
    }


    /**
     * List the values of the Layers in
     * the tblLayer collection.
     */
    public
    void listLayers()
    {
        Enumeration E = tblLayer.elements();
        YxxfTblLayer layer;

        System.out.println("=== LAYERS ==================================");
        while (E.hasMoreElements())
        {
            layer = (YxxfTblLayer)E.nextElement();
            System.out.println("Layer=[\n" + layer + "]");
        }
        System.out.println("=============================================");
    }




    //
    // S T Y L E
    //

    /**
     * Find the Style given a name.
     * @param name The name of the Style.
     * @return The Style with the name.
     */
    public
    YxxfTblStyle findStyle(String name)
    {
        return (YxxfTblStyle)tblStyle.get(new YxxfTblStyleKey(name));
    }


    /**
     * Find the Style given a name or create it and add it to the collection 
     * if it doesn't exist.
     * @param name The name of the Style.
     * @return The Style with the name.
     */
    public
    YxxfTblStyle findStyle_add(String name)
    {
        YxxfTblStyle foundstyle = (YxxfTblStyle)tblStyle.get(new YxxfTblStyleKey(name));

        if (foundstyle != null) // found - return it
            return foundstyle;

        // not found - create a new style with default values
        foundstyle = new YxxfTblStyle(name);
        tblStyle.put(foundstyle, foundstyle); // style is it's own key
        return foundstyle;
    }


    /**
     * Find the Style given a name.
     * @param name The name of the Style.
     * @return The Style with the name.
     */
    public
    YxxfTblStyle findStyle(char[] name)
    {
        return (YxxfTblStyle)tblStyle.get(new YxxfTblStyleKey(name));
    }


    /**
     * Find the Style given a name or create it and add it to the collection
     * if it doesn't exist.
     * @param name The name of the Style.
     * @return The YxxfTblStyle with the name.
     */
    public
    YxxfTblStyle findStyle_add(char[] name)
    {
        YxxfTblStyle foundstyle = (YxxfTblStyle)tblStyle.get(new YxxfTblStyleKey(name));

        if (foundstyle != null) // found - return it
            return foundstyle;

        // not found - create a new style with default values
        foundstyle = new YxxfTblStyle(name);
        tblStyle.put(foundstyle, foundstyle); // style is it's own key
        return foundstyle;
    }


    /**
     * Find the Style given a key.
     * @param style The key of the Style.
     * @return The Style with the key.
     */
    public
    YxxfTblStyle findStyle(YxxfTblStyleKey style)
    {
        return (YxxfTblStyle)tblStyle.get(style);
    }


    /**
     * Find the Style given a key or create it and add it to the collection
     * if it doesn't exist.
     * @param style The key of the Style.
     * @return The Style with the key.
     */
    public
    YxxfTblStyle findStyle_add(YxxfTblStyleKey style)
    {
        YxxfTblStyle foundstyle = (YxxfTblStyle)tblStyle.get(style);

        if (foundstyle != null) // found - return it
            return foundstyle;

        // not found - create a new style with default values
        foundstyle = new YxxfTblStyle(style.name);
        tblStyle.put(foundstyle, foundstyle); // style is it's own key
        return foundstyle;
    }


    /**
     * Find the Style Shape given a style.
     * @param style The Style.
     * @return The Shape with this Style.
     */
    public
    YxxfShape findStyleShape_add(YxxfTblStyle style)
    {
        if (style == null)
            return null;

        // connect style.shape
        if (style.shape == null)
        {
            if (tblStyle_default_style == null)
            {
                tblStyle_default_style = findStyle_add(""); // find default
                if (tblStyle_default_style.shape == null) // and set empty shape if not set
                {
                    tblStyle_default_style.shape = new YxxfShape();
                    tblStyle_default_style.shape.setShapeReady(true);
                    tblStyle_default_style.shape.setShapeLoaded(true);
                }
            }
            style.shape = tblStyle_default_style.shape;
        }
        return style.shape;
    }


    /**
     * List the values of the Styles in the tblStyle collection.
     */
    public
    void listStyles()
    {
        Enumeration E = tblStyle.elements();
        YxxfTblStyle style;

        while (E.hasMoreElements())
        {
            style = (YxxfTblStyle)E.nextElement();

            System.out.println("=== STYLE ===================================");
            System.out.println("    name=[" + style.getName() + "]");
            System.out.println("    flags=" + style.flags +
                               ",textgenflags=" + style.textgenflags);
            System.out.println("    height=" + style.height +
                               ",widthfactor=" + style.widthfactor +
                               ",obliqueang=" + style.obliqueang);
            System.out.println("    fontfilename=[" + style.fontfilename + "]");
            System.out.println("    bigfontfilename=[" + style.bigfontfilename + "]");

            if (style.shape == null)
            {
                System.out.println("    SHAPE==null");
            }
            else
            {
                System.out.println("    SHAPE type=" + style.shape.type +
                                   ",elemsize=" + style.shape.elem.size() +
                                   ",r:l:w=[" + style.shape.getShapeReady() + ":" +
                                                style.shape.getShapeLoaded() + ":" +
                                                style.shape.getShapeWritten() + "]");
                System.out.println("    hdr_id     =[" + style.shape.hdr_id + "]");
                System.out.println("    hdr_c1     =[" + Integer.toHexString((int)style.shape.hdr_c1) + "]");
                System.out.println("    hdr_int1   =[" + style.shape.hdr_int1 + "]");
                System.out.println("    hdr_int2   =[" + style.shape.hdr_int2 + "]");
                System.out.println("    hdr_size   =[" + style.shape.hdr_size + "]");

/* ===
                for (Enumeration e = style.shape.elem.keys(); e.hasMoreElements(); )
                {
                    YxxfShapeChar key = (YxxfShapeChar)e.nextElement();

                    System.out.print  ("        elem=[" + Integer.toHexString((int)key.getValue()) + ":" + key.getDesc() + "],elemlen=" + key.getGeom().length() + ",{ ");
                    int j = Math.min(key.getGeom().length(), 20);
//                  int j = key.getGeom().length();
                    for (int i = 0; i < j; i++)
                    {
                        System.out.print  (Integer.toHexString((int)key.getGeom().charAt(i)) + " ");
                    }
                    if (j < key.getGeom().length())
                        System.out.println("... }");
                    else
                        System.out.println("}");
                }
=== */
            }

            System.out.println("=============================================");
        }
    }


}

