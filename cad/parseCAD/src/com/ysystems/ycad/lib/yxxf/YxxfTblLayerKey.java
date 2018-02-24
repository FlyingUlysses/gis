//==============================================================================
// YxxfTblLayerKey.java
//
// LAYER table key
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfTblLayerKey.java,v 1.7 2003/05/08 11:24:03 ekarlo Exp $
// $Log: YxxfTblLayerKey.java,v $
// Revision 1.7  2003/05/08 11:24:03  ekarlo
// Remove warnings.
//
// Revision 1.6  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.5  2000/10/17 07:43:39  ekarlo
// Change package paths to lower case.
//
// Revision 1.4  1999-09-29 17:09:54-06  walter
// Added JavaDoc comments.
//
// Revision 1.3  1999-07-09 14:10:50-06  ekarlo
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


import com.ysystems.lib.yutil.*;


/**
 * LAYER table key.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YxxfTblLayerKey
{
    /**
     * String Layer Name 0.
     */
    public static final String  STR_LAYERNAME__0            = "0";
    /**
     * char Layer Name 0.
     */
    public static final char[]  CHR_LAYERNAME__0            = { '0' };


    /**
     * code   2 - Layer name.
     */
    protected
    char[]                      name;
    /**
     * Layer name length.
     */
    protected
    int                         namelen;


    /**
     * Constructor
     */
    public
    YxxfTblLayerKey()
    {
        name = new char[0]; // set really empty
        namelen = -1;
    }


    /**
     * Constructor
     * @param arg_name The Layer name.
     */
    public
    YxxfTblLayerKey(String arg_name)
    {
        namelen = arg_name.length();
        name = new char[namelen];
        for (int i = 0; i < namelen; i++)
            name[i] = arg_name.charAt(i);
    }


    /**
     * Constructor
     * @param arg_name The Layer name.
     */
    public
    YxxfTblLayerKey(char[] arg_name)
    {
        namelen = arg_name.length;
        name = new char[namelen];
        for (int i = 0; i < namelen; i++)
            name[i] = arg_name[i];
    }


    /**
     * Set the value of name.
     * @param arg_name The name.
     */
    public
    void setName(char[] arg_name)
    {
        int len = arg_name.length;

        // check capacity
        if (len > namelen)
            name = new char[len];

        for (int i = 0; i < len; i++)
            name[i] = arg_name[i];

        namelen = len;
    }


    /**
     * Set the value of name.
     * @param arg_name The name.
     */
    public
    void setName(String arg_name)
    {
        int len = arg_name.length();

        // check capacity
        if (len > namelen)
            name = new char[len];

        for (int i = 0; i < len; i++)
            name[i] = arg_name.charAt(i);

        namelen = len;
    }


    /**
     * Set the value of name.
     * @param arg_name The name.
     */
    public
    void setName(StringBuffer arg_name)
    {
        int len = arg_name.length();

        // check capacity
        if (len > namelen)
            name = new char[len];

        for (int i = 0; i < len; i++)
            name[i] = arg_name.charAt(i);

        namelen = len;
    }


    /**
     * Set the value of name.
     * @param arg_name The name.
     */
    public
    void setName(YutilCharBuffer arg_name)
    {
        int len = arg_name.length();

        // check capacity
        if (len > namelen)
            name = new char[len];

        for (int i = 0; i < len; i++)
            name[i] = arg_name.charAt(i);

        namelen = len;
    }


    /**
     * Get the value of name.
     * @return The name.
     */
    public
    String getName()
    {
        if (namelen != name.length)
        {
            char[] newname = new char[namelen];
            for (int i = 0; i < namelen; i++)
                newname[i] = name[i];
            return new String(newname);
        }
        else
            return new String(name);
    }


    /**
     * Get the hashcode value of name.
     * @return The hashcode.
     */
    public
    int hashCode()
    {
        int hc = 0;

         for (int i = 0; i < namelen; i++)
            hc = (hc * 37) + name[i];

        return hc;
    }


    /**
     * Compare this Layer name to the name value of obj.
     * @param obj The object to compare to.
     * @return true if the names are the same.
     */
    public
    boolean equals(Object obj)
    {
        if (obj == null)
            return false;

        if (obj instanceof YxxfTblLayerKey)
        {
            YxxfTblLayerKey layerkey = (YxxfTblLayerKey)obj;
            if (namelen == layerkey.namelen)
            {
                for (int i = 0; i < namelen; i++)
                {
                    if (name[i] != layerkey.name[i])
                    {
                        return false;
                    }
                }
                return true;
            }
        }
        else

        if (obj instanceof String)
        {
            String layerkey = (String)obj;
            if (namelen == layerkey.length())
            {
                for (int i = 0; i < namelen; i++)
                {
                    if (name[i] != layerkey.charAt(i))
                    {
                        return false;
                    }
                }
                return true;
            }
        }
        else

        if (obj instanceof StringBuffer)
        {
            StringBuffer layerkey = (StringBuffer)obj;
            if (namelen == layerkey.length())
            {
                for (int i = 0; i < namelen; i++)
                {
                    if (name[i] != layerkey.charAt(i))
                    {
                        return false;
                    }
                }
                return true;
            }
        }

        return false;
    }


    /**
     * Copy the values of arg_layerkey into this Layer key.
     * @param arg_layerkey The Layer Key.
     */
    public
    void copyInto(YxxfTblLayerKey arg_layerkey)
    {
        int len = arg_layerkey.name.length;

        // check capacity
        if (namelen > len)
            arg_layerkey.name = new char[namelen];

        for (int i = 0; i < namelen; i++)
            arg_layerkey.name[i] = name[i];

        arg_layerkey.namelen = namelen;
    }
}



