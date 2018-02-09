//==============================================================================
// YutilProperties.java
//
// Handle command line arguments and parameters
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/lib/yutil/YutilProperties.java,v 1.14 2003/04/14 12:37:04 ekarlo Exp $
// $Log: YutilProperties.java,v $
// Revision 1.14  2003/04/14 12:37:04  ekarlo
// Update source file header for OSI release.
//
// Revision 1.13  2002/09/25 19:36:26  ekarlo
// Imprinter development check-in.
//
// Revision 1.12  2002-09-12 15:13:14-06  ekarlo
// Imprinter development check-in.
// Make utility methods static.
// Add static methods to convert between Color and String.
//
// Revision 1.11  2001-05-21 02:21:19-06  ekarlo
// Redo alpha color change.
//
// Revision 1.10  2001-05-18 23:15:52-06  ekarlo
// Add alpha to color property.
//
// Revision 1.9  2000-10-17 01:44:09-06  ekarlo
// Change package paths to lower case.
//
// Revision 1.8  1999-10-06 20:06:57-06  walter
// Added JavaDoc comments.
//
// Revision 1.7  1999-09-08 13:14:08-06  walter
// Add JavaDoc comments.
//
// Revision 1.6  1999-07-09 09:16:08-06  ekarlo
// Improve param properties scan.
//
// Revision 1.5  1999/06/20  22:33:43  ekarlo
// Rearrange package names.
//
// Revision 1.4  1998/07/12  00:03:11  ekarlo
// Deactivate debug print.
//
// Revision 1.3  1997/08/30  14:27:08  ekarlo
// Change Color return [debug - in progress]
//
// Revision 1.2  1997/07/21  20:50:22  ekarlo
// Add methods to set properties from existing properties.
//
// Revision 1.1  1997/07/13  18:15:24  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.lib.yutil;


import java.io.*;
import java.util.*;
import java.awt.*;


/** 
 * Routines to handle Command Line Arguments (Applications) and 
 * Parameters (Applets). Extends functionality of java.util.Properties.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YutilProperties extends Properties
{
    //==========================================================================
    // Constructors

    /** 
     * Constructor - no properties.
     */
    public
    YutilProperties()
    {
        super(new Properties());
    }


    /**
     * Constructor - set properties (defaults) from args String.
     * @param args A collection of properties.
     * @param scanout Parse the arguments flag. 
     */
    public
    YutilProperties(String args, boolean scanout)
    {
        super(new Properties());
        setPropertiesDefaults(args, scanout);
    }


    /**
     * Constructor - set properties (defaults) from argv array.
     * @param argv A collection of properties.
     */
    public
    YutilProperties(String argv[])
    {
        super(new Properties());
        setPropertiesDefaults(argv);
    }


    /**
     * Constructor - set properties (defaults) from InputStream.
     * @param in A collection of properties.
     */
    public
    YutilProperties(InputStream in)
    {
        super(new Properties());
        setPropertiesDefaults(in);
    }


    /**
     * Constructor - set properties (defaults) from argprops YutilProperties.
     * @param argprops A collection of properties.
     */
    public
    YutilProperties(YutilProperties argprops)
    {
        super(new Properties());
        setPropertiesDefaults(argprops);
    }
    //==========================================================================


    //==========================================================================
    // Set default properties

    /**
     * Set properties (defaults) from args String.
     * @param args A collection of properties.
     * @param scanout Parse the arguments flag.
     */
    public
    void setPropertiesDefaults(String args, boolean scanout)
    {
        if (args == null)
            return;

        if (scanout)
        {
            StringBuffer sb = new StringBuffer(args.length() + 32); // length of string + a little
            try {
                defaults.load(new StringBufferInputStream(YutilProperties.scanOutProperties(args, sb).toString()));
            }
            catch (IOException excp) {
                System.out.println(excp);
            }
        }
        else
        {
            try {
                defaults.load(new StringBufferInputStream(args));
            }
            catch (IOException excp) {
                System.out.println(excp);
            }
        }

        try {
            defaults.load(new StringBufferInputStream(args));
        }
        catch (IOException excp) {
            System.out.println(excp);
        }
    }

    /**
     * Set properties (defaults) from argv array.
     * @param argv A collection of properties.
     */
    public
    void setPropertiesDefaults(String argv[])
    {
        if (argv == null)
            return;

        for (int i = 0; i < argv.length; i++)
        {
            try {
                defaults.load(new StringBufferInputStream(argv[i]));
            }
            catch (IOException excp) {
                System.out.println(excp);
            }
        }
    }

    /**
     * Set properties (defaults) from InputStream.
     * @param in A collection of properties.
     */
    public
    void setPropertiesDefaults(InputStream in)
    {
        if (in == null)
            return;

        try {
            defaults.load(in);
        }
        catch (IOException excp) {
            System.out.println(excp);
        }
    }


    /**
     * Set properties (defaults) from argprops YutilProperties.
     * @param argprops A collection of properties.
     */
    public
    void setPropertiesDefaults(YutilProperties argprops)
    {
        if (argprops == null)
            return;

        // Copy all key/val pairs
        for (Enumeration ep = argprops.propertyNames(); ep.hasMoreElements(); )
        {
            String key = (String)ep.nextElement();
            String val = key + "=" + argprops.getProperty(key);
            setPropertiesDefaults(val, false);
        }
    }
    //==========================================================================


    //==========================================================================
    // Set properties

    /**
     * Set properties from args String.
     * @param args A collection of properties.
     * @param scanout Parse the arguments flag.
     */
    public
    void setProperties(String args, boolean scanout)
    {
        if (args == null)
            return;

        if (scanout)
        {
            StringBuffer sb = new StringBuffer(args.length() + 32); // length of string + a little
            try {
                load(new StringBufferInputStream(YutilProperties.scanOutProperties(args, sb).toString()));
            }
            catch (IOException excp) {
                System.out.println(excp);
            }
        }
        else
        {
            try {
                load(new StringBufferInputStream(args));
            }
            catch (IOException excp) {
                System.out.println(excp);
            }
        }
    }


    /**
     * Set properties from argv array.
     * @param argv A collection of properties.
     */
    public
    void setProperties(String argv[])
    {
        if (argv == null)
            return;

        for (int i = 0; i < argv.length; i++)
        {
            try {
                load(new StringBufferInputStream(argv[i]));
            }
            catch (IOException excp) {
                System.out.println(excp);
            }
        }
    }


    /**
     * Set properties from InputStream.
     * @param in A collection of properties.
     */
    public
    void setProperties(InputStream in)
    {
        if (in == null)
            return;

        try {
            load(in);
        }
        catch (IOException excp) {
            System.out.println(excp);
        }
    }


    /**
     * Set properties from argprops YutilProperties.
     * @param argprops A collection of properties.
     */
    public
    void setProperties(YutilProperties argprops)
    {
        if (argprops == null)
            return;

        // Copy all key/val pairs
        for (Enumeration ep = argprops.propertyNames(); ep.hasMoreElements(); )
        {
            String key = (String)ep.nextElement();
            String val = key + "=" + argprops.getProperty(key);
            setProperties(val, false);
        }
    }
    //==========================================================================


    //==========================================================================
    // Get properties

    /**
     * Get properties as int.
     * @param key The name part of name/value pair.
     * @return The value of the property.
     */
    public
    int getProperty_int(String key)
    {
        if (key == null)
            return 0;

        String val = getProperty(key);

        if (val == null)
            return 0;

        int ret_int = 0;
        try
        {
            Integer workint = new Integer(val);
            ret_int = workint.intValue();
        }
        catch(NumberFormatException e)
        {
            ret_int = 0;
        }

        return ret_int;
    }


    /**
     * Get properties as double.
     * @param key The name part of name/value pair.
     * @return The value of the property.
     */
    public
    double getProperty_double(String key)
    {
        if (key == null)
            return 0.0;

        String val = getProperty(key);

        if (val == null)
            return 0.0;

        double ret_double = 0.0;
        try
        {
            Double workdouble = new Double(val);
            ret_double = workdouble.doubleValue();
        }
        catch(NumberFormatException e)
        {
            ret_double = 0.0;
        }

        return ret_double;
    }


    /**
     * Get properties as boolean.
     * @param key The name part of name/value pair.
     * @return The value of a property.
     */
    public
    boolean getProperty_boolean(String key)
    {
        if (key == null)
            return false;

        String val = getProperty(key);

        if (val == null)
            return false;

        boolean ret_boolean = false;
        if (val.equalsIgnoreCase("ON") ||
            val.equalsIgnoreCase("YES") ||
            val.equalsIgnoreCase("TRUE"))
        {
            ret_boolean = true;
        }
        else
        if (val.equalsIgnoreCase("OFF") ||
            val.equalsIgnoreCase("NO") ||
            val.equalsIgnoreCase("FALSE"))
        {
            ret_boolean = false;
        }

        return ret_boolean;
    }


    /**
     * Get properties as String.
     * @param key The name part of name/value pair.
     * @return The value of a property.
     */
    public
    String getProperty_String(String key)
    {
        if (key == null)
            return null;

        String val = getProperty(key);

        return val;
    }


    /**
     * Get properties as Color.
     * @param key The name part of name/value pair.
     * @return The value of a property.
     */
    public
    Color getProperty_Color(String key)
    {
        if (key == null)
            return Color.black;

        String val = getProperty(key);

        if (val == null)
            return Color.black;

        Color ret_Color = convertStringToColor(val);

        return ret_Color;
    }
    //==========================================================================


    //==========================================================================
    /**
     * Convert String value to Color value.
     * @param val The color as a String.
     * @return The Color value of the String.
     */
    public static
    Color convertStringToColor(String val)
    {
        Color ret_Color;

        try
        {
            String workstr1;
            int slen = val.length();
            if (val.startsWith("0x") || val.startsWith("0X"))
            {
                workstr1 = val.substring(2);
                slen -= 2;
            }
            else
            if (val.startsWith("#"))
            {
                workstr1 = val.substring(1);
                slen -= 1;
            }
            else
            {   // decimal integer
                return new Color(Integer.parseInt(val));
            }

            // process hex string
            if (slen <= 6)
            {   // no alpha
                int ival = Integer.parseInt(workstr1, 16);
                ret_Color = new Color(ival);
            }
            else
            {   // has alpha of some sort
                String workstr2;
                if (slen == 8)
                {
                    workstr2 = workstr1;
                }
                else
                if (slen == 7)
                {
                    workstr2 = "0" + workstr1;
                }
                else
                {
                    workstr2 = workstr1.substring(slen - 8); // get rightmost 8
                }

                // System.out.println("Color,val=[" + val + "],key=[" + key + "],slen=" + slen + "]");
                // System.out.println("      workstr1=[" + workstr1 + "],workstr2=[" + workstr2 + "]");
                int a = Integer.parseInt(workstr2.substring(0, 2), 16); // a
                int r = Integer.parseInt(workstr2.substring(2, 4), 16); // r
                int g = Integer.parseInt(workstr2.substring(4, 6), 16); // g
                int b = Integer.parseInt(workstr2.substring(6, 8), 16); // b
                // System.out.println("      ret_Color1=[" + r + ":" + g + ":" + b +":" + a + "]");
                // int ival = Integer.parseInt(workstr2, 16);
                // ret_Color = new Color(ival, true);
                // System.out.println("      ival=" + ival);
                try {
                    ret_Color = new Color(r, g, b, a);
                }
                catch (NoSuchMethodError excp1) {
                    System.out.println("YutilProperties:convertStringToColor|excp1=[" + excp1 + "]");
                    ret_Color = new Color(r, g, b);
                }
                // System.out.println("      ret_Color1=[" + ret_Color + "]");
            }
        }
        catch(NumberFormatException e)
        {
            ret_Color = Color.black;
            // System.out.println("Color,ret_Color3=[" + ret_Color + "]");
        }

        return ret_Color;
    }


    /**
     * Convert Color value to String value.
     * @param val The color as a Color.
     * @return The String value of the Color.
     */
    public
    static
    String convertColorToString(Color val)
    {
        String ret_String;

        int a;
        try {
            a = val.getAlpha();
        }
        catch (NoSuchMethodError excp) {
            System.out.println("YutilProperties:convertColorToString:excp=[" + excp + "]");
            a = 0xFF;
        }
        int r = val.getRed(), g = val.getGreen(), b = val.getBlue();
        String as, rs, gs, bs;

        rs = Integer.toHexString(r); if (rs.length() == 1) rs = "0" + rs;
        gs = Integer.toHexString(g); if (gs.length() == 1) gs = "0" + gs;
        bs = Integer.toHexString(b); if (bs.length() == 1) bs = "0" + bs;

        if (a == 0xFF)
        {
            ret_String = "#" + rs + gs + bs;
        }
        else
        {
            as = Integer.toHexString(a); if (as.length() == 1) as = "0" + as;
            ret_String = "#" + as + rs + gs + bs;
        }            

        return ret_String;
    }
    //==========================================================================


    //==========================================================================
    /**
     * Parse argument String into separate newline delimited Property name/value pairs.
     * @param args Arguments to the method.
     * @param sb The ouput StringBuffer containing newline delimited Properties.
     * @return Newline delimited Properties.
     */

    public
    static
    StringBuffer scanOutProperties(String args, StringBuffer sb)
    {
        int si; // source Index
 
        int len = args.length();
        char c;

        int nambeg, namlen;
        int valbeg, vallen;

        // set output sb empty
        sb.setLength(0);

        // scan entire args for nam/val pairs
        si = 0;

        mainscanloop: // outermost scan loop
        for (;;)
        {
            if (si >= len)
                break mainscanloop; // totally done

            namvalscanloop: // scan single nam/val pair
            for (;;)
            {
                // ====== begin scan on one pair
                nambeg = -1;
                namlen = 0;

                valbeg = -1;
                vallen = 0;


                // ====== scan past white space before nam
                for (;;)
                {
                    if (si >= len)
                        break namvalscanloop; // done with this pair

                    c = args.charAt(si);

                    if (c == ' ' || c == '\t' || c == '\n' || c == '\r')
                    {
                        si++;
                        continue;
                    }
                    break;
                }


                // ====== Start of nam
                // scan len of nam, up to '='
                nambeg = si;
                for (;;)
                {
                    if (si >= len)
                        break namvalscanloop; // done with this pair

                    c = args.charAt(si);

                    if (c == '\n')
                    {
                        si++;
                        break namvalscanloop; // done with this pair
                    }

                    if (c == '=') // Found delimiter - go on to scan val
                    {
                        si++;
                        break;
                    }

                    namlen++;

                    si++;
                }


                // ====== Start of val
                // scan len of val
                // handle " and ' bounded values
                if (si >= len)
                    break namvalscanloop; // done with this pair

                c = args.charAt(si);

                // === scan to matching " or '
                if (c == '\"' || c == '\'')
                {
                    char matchc = c;
                    si++;
                    if (si >= len)
                        break namvalscanloop; // done with this pair

                    c = args.charAt(si);

                    valbeg = si;
                    for (;;)
                    {
                        if (c == '\n')
                        {
                            si++;
                            break namvalscanloop; // done with this pair
                        }

                        if (c == '\\') // Check for escaped " or '
                        {
                            if (si + 1 < len)
                            {
                                if (args.charAt(si + 1) == '\"' || args.charAt(si + 1) == '\'')
                                {
                                    vallen += 2;
                                    si += 2;
                                    if (si >= len)
                                        break namvalscanloop; // done with this pair
                                    c = args.charAt(si);
                                    continue;
                                }
                            }
                        }

                        if (c == matchc)
                        {
                            si++;
                            break namvalscanloop; // done with this pair
                        }

                        vallen++;

                        si++;
                        if (si >= len)
                            break namvalscanloop; // done with this pair
                        c = args.charAt(si);
                    }
                }
                else

                // === scan normal value - c is valid upon first entry
                {
                    valbeg = si;
                    for (;;)
                    {
                        if (c == '\n')
                        {
                            si++;
                            break namvalscanloop; // done with this pair
                        }

                        if (c == ' ')
                        {
                            si++;
                            break namvalscanloop; // done with this pair
                        }

                        vallen++;

                        si++;
                        if (si >= len)
                            break namvalscanloop; // done with this pair
                        c = args.charAt(si);
                    }
                }

            } // end of namvalscanloop

            // append anything accumulated in output sb and go for another pair
            YutilProperties.scanOutPropertiesNamValAppend(args, nambeg, namlen, valbeg, vallen, sb);

        } // end of for ever

        return sb;
    }

    /**
     * Append a name/value pair to a StringBuffer.
     * @param args The input args .
     * @param nambeg The beginning position of the name.
     * @param namlen The length of the name.
     * @param valbeg The beginning position of the value.
     * @param vallen The length of the value.
     * @param sb The StringBuffer with the name/value pair appended.
     */
    private
    static
    void scanOutPropertiesNamValAppend(String args,
                                       int nambeg, int namlen,
                                       int valbeg, int vallen,
                                       StringBuffer sb)
    {
        int si; // source Index
 
        int len = args.length();

        if (nambeg < 0 || nambeg >= len || (nambeg + namlen - 1) >= len)
            return;
        if (valbeg < 0 || valbeg >= len || (valbeg + vallen - 1) >= len)
            return;

        // append nam
        for (si = nambeg; si < (nambeg + namlen); si++)
        {
            sb.append(args.charAt(si));
        }

        // append deliminator
        sb.append('=');

        // append val
        for (si = valbeg; si < (valbeg + vallen); si++)
        {
            sb.append(args.charAt(si));
        }

        // append terminator
        sb.append('\n');
    }
    //==========================================================================
}
