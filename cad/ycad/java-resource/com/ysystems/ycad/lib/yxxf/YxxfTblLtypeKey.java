//==============================================================================
// YxxfTblLtypeKey.java
//
// LTYPE table key
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfTblLtypeKey.java,v 1.7 2003/05/08 11:24:02 ekarlo Exp $
// $Log: YxxfTblLtypeKey.java,v $
// Revision 1.7  2003/05/08 11:24:02  ekarlo
// Remove warnings.
//
// Revision 1.6  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.5  2000/10/17 07:43:38  ekarlo
// Change package paths to lower case.
//
// Revision 1.4  1999-09-08 13:16:01-06  walter
// Add JavaDoc comments.
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
 * LTYPE table key.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YxxfTblLtypeKey
{
    /**
     * BLAYER Linetype table key name.
     */
    public static final char[]  CHR_LTYPENAME__BYLAYER      = { 'B','Y','L','A','Y','E','R' };
    /**
     * BYBLOCK Linetype table key name.
     */
    public static final char[]  CHR_LTYPENAME__BYBLOCK      = { 'B','Y','B','L','O','C','K' };
    /**
     * CONTINUOUS Linetype table key name.
     */
    public static final char[]  CHR_LTYPENAME__CONTINUOUS   = { 'C','O','N','T','I','N','U','O','U','S' };
    /**
     * BLAYER Linetype table key name.
     */
    public static final String  STR_LTYPENAME__BYLAYER      = "BYLAYER";
    /**
     * BYBLOCK Linetype table key name.
     */
    public static final String  STR_LTYPENAME__BYBLOCK      = "BYBLOCK";
    /**
     * CONTINUOUS Linetype table key name.
     */
    public static final String  STR_LTYPENAME__CONTINUOUS   = "CONTINUOUS";


    /**
     * code   2 - Ltype name.
     */
    protected
    char[]                      name;
    /**
     * The length of the Linetype name.
     */
    protected
    int                         namelen;


    /**
     * Constructor
     */
    public
    YxxfTblLtypeKey()
    {
        name = new char[0]; // set really empty
        namelen = -1;
    }


    /**
     * Constructor
     * @param arg_name The name of the Linetype.
     */
    public
    YxxfTblLtypeKey(String arg_name)
    {
        namelen = arg_name.length();
        name = new char[namelen];
        for (int i = 0; i < namelen; i++)
            name[i] = arg_name.charAt(i);
    }


    /**
     * Constructor
     * @param arg_name The name of the Linetype.
     */
    public
    YxxfTblLtypeKey(char[] arg_name)
    {
        namelen = arg_name.length;
        name = new char[namelen];
        for (int i = 0; i < namelen; i++)
            name[i] = arg_name[i];
    }


    /**
     * Set the name of the Linetype.
     * @param arg_name The name of the Linetype.
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
     * Set the name of the Linetype.
     * @param arg_name The name of the Linetype.
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
     * Set the name of the Linetype.
     * @param arg_name The name of the Linetype.
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
     * Set the name of the Linetype.
     * @param arg_name The name of the Linetype.
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
     * Get the name of the Linetype.
     * @return The name of the Linetype.
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
     * Generate a hashcode for this Linetype based on its name.
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
     * Compare this Linetype to an Object of type YxxfTblLtypeKey, String 
     * or StringBuffer.
     * @return true if the names are the same, false if the names are different
     * or the Object type is not YxxfTblLtypeKey, String orStringBuffer.
     */
    public
    boolean equals(Object obj)
    {
        if (obj == null)
            return false;

        if (obj instanceof YxxfTblLtypeKey)
        {
            YxxfTblLtypeKey ltypekey = (YxxfTblLtypeKey)obj;
            if (namelen == ltypekey.namelen)
            {
                for (int i = 0; i < namelen; i++)
                {
                    if (name[i] != ltypekey.name[i])
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
            String ltypekey = (String)obj;
            if (namelen == ltypekey.length())
            {
                for (int i = 0; i < namelen; i++)
                {
                    if (name[i] != ltypekey.charAt(i))
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
            StringBuffer ltypekey = (StringBuffer)obj;
            if (namelen == ltypekey.length())
            {
                for (int i = 0; i < namelen; i++)
                {
                    if (name[i] != ltypekey.charAt(i))
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
     * Copy a Linetype into this Linetype.
     * @param arg_ltypekey An Linetype.
     */
    public
    void copyInto(YxxfTblLtypeKey arg_ltypekey)
    {
        int len = arg_ltypekey.name.length;

        // check capacity
        if (namelen > len)
            arg_ltypekey.name = new char[namelen];

        for (int i = 0; i < namelen; i++)
            arg_ltypekey.name[i] = name[i];

        arg_ltypekey.namelen = namelen;
    }
}



