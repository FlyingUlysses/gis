//==============================================================================
// YutilIOHandler.java

// Open InputStreams and OutputStreams
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/lib/yutil/YutilIOHandler.java,v 1.11 2003/05/08 08:46:24 ekarlo Exp $
// $Log: YutilIOHandler.java,v $
// Revision 1.11  2003/05/08 08:46:24  ekarlo
// Remove warnings.
//
// Revision 1.10  2003/04/14 12:37:04  ekarlo
// Update source file header for OSI release.
//
// Revision 1.9  2000/10/17 07:43:35  ekarlo
// Change package paths to lower case.
//
// Revision 1.8  1999-09-08 13:14:41-06  walter
// Add JavaDoc comments.
//
// Revision 1.7  1999-07-09 13:47:50-06  ekarlo
// Rearrange package names.
//
// Revision 1.6  1999/07/08  03:00:28  ekarlo
// Reformat print.
//
// Revision 1.5  1999/06/20  22:33:43  ekarlo
// Rearrange package names.
//
// Revision 1.4  1999/02/09  14:46:38  ekarlo
// Deactivate console print.
//
// Revision 1.3  1999/01/28  04:23:42  ekarlo
// Text - phase 4.
//
// Revision 1.2  1998/12/21  15:37:44  ekarlo
// Text - phase 3.
//
// Revision 1.1  1998/11/24  19:32:19  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.lib.yutil;


import java.applet.*;
import java.io.*;
import java.net.*;


/**
 * Utilities for opening input and output streams based
 * on user input and type of application.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YutilIOHandler
{
    //==========================================================================
    /**
     * Applet reference.
     */
    private Applet              applet                  = null;
    //==========================================================================


    //==========================================================================

    /**
     * Constructor (empty).
     */
    public
    YutilIOHandler()
    {
//d     System.out.println("++YutilIOHandler:CONSTRUCTOR 1");
    }
    
    /**
     * Constructor
     * @param applet Reference to an applet.
     */
    public
    YutilIOHandler(Applet applet)
    {
//d     System.out.println("++YutilIOHandler:CONSTRUCTOR 2");
        setApplet(applet);
    }
   
   /**
     * Set the value of the reference to applet.
     * @param applet A reference to an applet.
     * @return A reference to an applet.
     */
    private
    Applet setApplet(Applet applet)
    {
        this.applet = applet;
//d     System.out.println("++YutilIOHandler:setApplet|applet=[" + this.applet + "]");
        return this.applet;
    }
    //==========================================================================




    //==========================================================================
    //= I N P U T ==============================================================
    //==========================================================================
    
    //==========================================================================
    /**
     * Open an InputStream.
     * @param argstr_name TODO
     * @param argstr_baseurl TODO
     * @param argstr_src TODO
     * @param argstr_srcfile TODO
     * @param argstr_srcurl TODO
     * @return An InputStream.
     */
    public
    InputStream openInputStream(String argstr_name,
        String argstr_baseurl, String argstr_src, String argstr_srcfile, String argstr_srcurl)
    {
        System.out.println("++YutilIOHandler:==================================================");
        System.out.println("++YutilIOHandler:openInputStream|argstr_name=[" + argstr_name + "]");

        InputStream retis = openInputStreamAction(argstr_name,
                                                  argstr_baseurl,
                                                  argstr_src, argstr_srcfile, argstr_srcurl);

        System.out.println("++YutilIOHandler:==================================================");
        return retis;
    }
    //==========================================================================
    
    
    //==========================================================================

    /**
     * Open an input stream.
     * @param arg_ioname An IO Handler Name.
     * @return An InputStream.
     */
    public
    InputStream openInputStream(com.ysystems.lib.yutil.YutilIOHandlerName arg_ioname)
    {
        System.out.println("++YutilIOHandler:==================================================");
        System.out.println("++YutilIOHandler:openInputStream|arg_ioname=[" + arg_ioname + "]");

        InputStream retis = arg_ioname.is = openInputStreamAction(arg_ioname.name,
                                                                  arg_ioname.baseurl,
                                                                  arg_ioname.src, arg_ioname.srcfile, arg_ioname.srcurl);

        System.out.println("++YutilIOHandler:==================================================");
        return retis;
    }
    //==========================================================================
    
    
    //==========================================================================
    //
    /**
     * Opens InputStream from file or url depending on
     * user set values and runtype.
     * <pre>
     *
     * Argument         Applet                      Application
     * ---------------- --------------------------- ------------------------
     * baseurl
     *   user set       user set value              user set value
     *   or
     *   null           applet.getDocumentBase()    null
     *
     * src              URL(baseurl, src)           FileInputStream(src)
     * srcfile          FileInputStream(src)        FileInputStream(src)
     * srcurl           URL(baseurl, src)           URL(baseurl, src)
     * </pre>
     * @param argstr_name TODO
     * @param argstr_baseurl TODO
     * @param argstr_src TODO
     * @param argstr_srcfile TODO
     * @param argstr_srcurl TODO
     * @return An InputStream 
     */
    public
    InputStream openInputStreamAction(
        String argstr_name,
        String argstr_baseurl, String argstr_src, String argstr_srcfile, String argstr_srcurl)
    {
        URL baseURL = null;
        URL openURL = null;
        
        InputStream is = null;


        System.out.println("++YutilIOHandler:openInputStream|argstr_name=[" + argstr_name + ",applet=[" + applet + "]");
        System.out.println("++YutilIOHandler:openInputStream|argstr_baseurl=[" + argstr_baseurl + "]" +
                                                            ",argstr_src=[" + argstr_src + "]" +
                                                            ",argstr_srcfile=[" + argstr_srcfile + "]" +
                                                            ",argstr_srcurl=[" + argstr_srcurl + "]");
        

        //
        // Develop the base URL to use
        //
        if (argstr_baseurl != null)
        {   // Use argument if specified
            try
            {
                baseURL = new URL(argstr_baseurl);
            }
            catch (MalformedURLException e_malbaseurl)
            {
                System.out.println("++YutilIOHandler:openInputStream|Malformed base URL argstr_baseurl=[" + argstr_baseurl + "],exception=[" + e_malbaseurl + "]");
                return null;
            }
        }
        else
        {
            if (applet != null)
            {   // If Applet use document base else nothing
                baseURL = applet.getDocumentBase();
            }
        }
        System.out.println("++YutilIOHandler:openInputStream|baseURL=[" + baseURL + "]");

        
        //
        // Open input stream
        //
        if (applet != null)
        {  // Open as Applet

            if (argstr_src != null)
            {
                try
                {
                    openURL = new URL(baseURL, argstr_src);
                    try
                    {
                        is = openURL.openConnection().getInputStream();
                    }
                    catch (IOException e_erropenstream)
                    {
                        System.out.println("++YutilIOHandler:openInputStream|open input stream error argstr_src=[" + argstr_src + "],exception=[" + e_erropenstream + "]");
                        return null;
                    }
                }
                catch (MalformedURLException e_malopenurl)
                {
                    System.out.println("++YutilIOHandler:openInputStream|Malformed src URL argstr_src=[" + argstr_src + "],exception=[" + e_malopenurl + "]");
                    return null;
                }
                System.out.println("++YutilIOHandler:openInputStream|openURL=[" + openURL + "]");
            }
            else
            
            if (argstr_srcfile != null)
            {
                try
                {
                    is = new FileInputStream(argstr_srcfile);
                }
                catch (IOException e_erropenstream)
                {
                    System.out.println("++YutilIOHandler:openInputStream|open input stream error argstr_srcfile[" + argstr_srcfile + "],exception=[" + e_erropenstream + "]");
                    return null;
                }
            }
            else

            if (argstr_srcurl != null)
            {
                try
                {
                    openURL = new URL(baseURL, argstr_srcurl);
                    try
                    {
                        is = openURL.openConnection().getInputStream();
                    }
                    catch (IOException e_erropenstream)
                    {
                        System.out.println("++YutilIOHandler:openInputStream|open input stream error argstr_srcurl=[" + argstr_srcurl + "],exception=[" + e_erropenstream + "]");
                        return null;
                    }
                }
                catch (MalformedURLException e_malopenurl)
                {
                    System.out.println("++YutilIOHandler:openInputStream|Malformed src URL argstr_srcurl=[" + argstr_srcurl + "],exception=[" + e_malopenurl + "]");
                    return null;
                }
                System.out.println("++YutilIOHandler:openInputStream|openURL=[" + openURL + "]");
            }
            else
            
            {
                System.out.println("++YutilIOHandler:ERROR - neither src nor srcfile nor srcurl property set");
                return null;
            }
            
        }
        else
        {   // Open as Application

            if (argstr_src != null)
            {
                try
                {
                    is = new FileInputStream(argstr_src);
                }
                catch (IOException e_erropenstream)
                {
                    System.out.println("++YutilIOHandler:openInputStream|open input stream error argstr_src[" + argstr_src + "],exception=[" + e_erropenstream + "]");
                    return null;
                }
            }
            else

            if (argstr_srcfile != null)
            {
                try
                {
                    is = new FileInputStream(argstr_srcfile);
                }
                catch (IOException e_erropenstream)
                {
                    System.out.println("++YutilIOHandler:openInputStream|open input stream error argstr_srcfile[" + argstr_srcfile + "],exception=[" + e_erropenstream + "]");
                    return null;
                }
            }
            else

            if (argstr_srcurl != null)
            {
                try
                {
                    openURL = new URL(baseURL, argstr_srcurl);
                    try
                    {
                        is = openURL.openConnection().getInputStream();
                    }
                    catch (IOException e_erropenstream)
                    {
                        System.out.println("++YutilIOHandler:openInputStream|open input stream error argstr_srcurl=[" + argstr_srcurl + "],exception=[" + e_erropenstream + "]");
                        return null;
                    }
                }
                catch (MalformedURLException e_malopenurl)
                {
                    System.out.println("++YutilIOHandler:openInputStream|Malformed src URL argstr_srcurl=[" + argstr_srcurl + "],exception=[" + e_malopenurl + "]");
                    return null;
                }
                System.out.println("++YutilIOHandler:openInputStream|openURL=[" + openURL + "]");
            }
            else
            
            {
                System.out.println("++YutilIOHandler:ERROR - neither src nor srcfile nor srcurl property set");
                return null;
            }
            
        }

        return is;
    }
    //==========================================================================




    //==========================================================================
    //= O U T P U T ============================================================
    //==========================================================================
    
    //==========================================================================
    /**
     * Open an output stream.
     * @param argstr_name TODO
     * @param argstr_baseurl  TODO
     * @param argstr_dst TODO
     * @param argstr_dstfile TODO
     * @param argstr_dsturl TODO
     * @return An OutputStream
     */
    public
    OutputStream openOutputStream(String argstr_name,
        String argstr_baseurl, String argstr_dst, String argstr_dstfile, String argstr_dsturl)
    {
        System.out.println("++YutilIOHandler:==================================================");
        System.out.println("++YutilIOHandler:openOutputStream|argstr_name=[" + argstr_name + "]");

        OutputStream retos = openOutputStreamAction(argstr_name,
                                                    argstr_baseurl,
                                                    argstr_dst, argstr_dstfile, argstr_dsturl);

        System.out.println("++YutilIOHandler:==================================================");
        return retos;
    }
    //==========================================================================
    
    
    //==========================================================================
    /**
     * Open an output stream
     * @param arg_ioname A YutilIOHandlerName.
     * @return An output stream.
     */
    public
    OutputStream openOutputStream(com.ysystems.lib.yutil.YutilIOHandlerName arg_ioname)
    {
        System.out.println("++YutilIOHandler:==================================================");
        System.out.println("++YutilIOHandler:openInputStream|arg_ioname=[" + arg_ioname + "]");

        OutputStream retos = arg_ioname.os = openOutputStreamAction(arg_ioname.name,
                                                                    arg_ioname.baseurl,
                                                                    arg_ioname.dst, arg_ioname.dstfile, arg_ioname.dsturl);

        System.out.println("++YutilIOHandler:==================================================");
        return retos;
    }
    //==========================================================================
    
    
    //==========================================================================
    /**
     * Opens OutputStream for file or url depending on
     * user set values and runtype.
     * <pre>
     *
     * Argument         Applet                      Application
     * ---------------- --------------------------- ------------------------
     * baseurl
     *   user set       user set value              user set value
     *   or
     *   null           applet.getDocumentBase()    null
     *
     * dst              URL(baseurl, dst)           FileInputStream(dst)
     * dstfile          FileInputStream(dst)        FileInputStream(dst)
     * dsturl           URL(baseurl, dst)           URL(baseurl, dst)
     * </pre>
     * @param argstr_name TODO
     * @param argstr_baseurl  TODO
     * @param argstr_dst TODO
     * @param argstr_dstfile TODO
     * @param argstr_dsturl TODO
     * @return An OutputStream.
     */
    public
    OutputStream openOutputStreamAction(
        String argstr_name,
        String argstr_baseurl, String argstr_dst, String argstr_dstfile, String argstr_dsturl)
    {
        URL baseURL = null;
        URL openURL = null;
        
        OutputStream os = null;


        System.out.println("++YutilIOHandler:openOutputStream|argstr_name=[" + argstr_name + ",applet=[" + applet + "]");
        System.out.println("++YutilIOHandler:openOutputStream|argstr_baseurl=[" + argstr_baseurl + "]" +
                                                            ",argstr_dst=[" + argstr_dst + "]" +
                                                            ",argstr_dstfile=[" + argstr_dstfile + "]" +
                                                            ",argstr_dsturl=[" + argstr_dsturl + "]");
        

        //
        // Develop the base URL to use
        //
        if (argstr_baseurl != null)
        {   // Use argument if specified
            try
            {
                baseURL = new URL(argstr_baseurl);
            }
            catch (MalformedURLException e_malbaseurl)
            {
                System.out.println("++YutilIOHandler:openOutputStream|Malformed base URL argstr_baseurl=[" + argstr_baseurl + "],exception=[" + e_malbaseurl + "]");
                return null;
            }
        }
        else
        {
            if (applet != null)
            {   // If Applet use document base else nothing
                baseURL = applet.getDocumentBase();
            }
        }
        System.out.println("++YutilIOHandler:openOutputStream|baseURL=[" + baseURL + "]");

        
        //
        // Open output stream
        //
        if (applet != null)
        {  // Open as Applet

            if (argstr_dst != null)
            {
                try
                {
                    openURL = new URL(baseURL, argstr_dst);
                    try
                    {
                        os = openURL.openConnection().getOutputStream();
                    }
                    catch (IOException e_erropenstream)
                    {
                        System.out.println("++YutilIOHandler:openOutputStream|open output stream error argstr_dst=[" + argstr_dst + "],exception=[" + e_erropenstream + "]");
                        return null;
                    }
                }
                catch (MalformedURLException e_malopenurl)
                {
                    System.out.println("++YutilIOHandler:openOutputStream|Malformed dst URL argstr_dst=[" + argstr_dst + "],exception=[" + e_malopenurl + "]");
                    return null;
                }
                System.out.println("++YutilIOHandler:openOutputStream|openURL=[" + openURL + "]");
            }
            else
            
            if (argstr_dstfile != null)
            {
                try
                {
                    os = new FileOutputStream(argstr_dstfile);
                }
                catch (IOException e_erropenstream)
                {
                    System.out.println("++YutilIOHandler:openOutputStream|open output stream error argstr_dstfile[" + argstr_dstfile + "],exception=[" + e_erropenstream + "]");
                    return null;
                }
            }
            else

            if (argstr_dsturl != null)
            {
                try
                {
                    openURL = new URL(baseURL, argstr_dsturl);
                    try
                    {
                        os = openURL.openConnection().getOutputStream();
                    }
                    catch (IOException e_erropenstream)
                    {
                        System.out.println("++YutilIOHandler:openOutputStream|open output stream error argstr_dsturl=[" + argstr_dsturl + "],exception=[" + e_erropenstream + "]");
                        return null;
                    }
                }
                catch (MalformedURLException e_malopenurl)
                {
                    System.out.println("++YutilIOHandler:openOutputStream|Malformed dst URL argstr_dsturl=[" + argstr_dsturl + "],exception=[" + e_malopenurl + "]");
                    return null;
                }
                System.out.println("++YutilIOHandler:openOutputStream|openURL=[" + openURL + "]");
            }
            else
            
            {
                System.out.println("++YutilIOHandler:ERROR - neither dst nor dstfile nor dsturl property set");
                return null;
            }
            
        }
        else
        {   // Open as Application

            if (argstr_dst != null)
            {
                try
                {
                    os = new FileOutputStream(argstr_dst);
                }
                catch (IOException e_erropenstream)
                {
                    System.out.println("++YutilIOHandler:openOutputStream|open input stream error argstr_dst[" + argstr_dst + "],exception=[" + e_erropenstream + "]");
                    return null;
                }
            }
            else

            if (argstr_dstfile != null)
            {
                try
                {
                    os = new FileOutputStream(argstr_dstfile);
                }
                catch (IOException e_erropenstream)
                {
                    System.out.println("++YutilIOHandler:openOutputStream|open input stream error argstr_dstfile[" + argstr_dstfile + "],exception=[" + e_erropenstream + "]");
                    return null;
                }
            }
            else

            if (argstr_dsturl != null)
            {
                try
                {
                    openURL = new URL(baseURL, argstr_dsturl);
                    try
                    {
                        os = openURL.openConnection().getOutputStream();
                    }
                    catch (IOException e_erropenstream)
                    {
                        System.out.println("++YutilIOHandler:openOutputStream|open output stream error argstr_dsturl=[" + argstr_dsturl + "],exception=[" + e_erropenstream + "]");
                        return null;
                    }
                }
                catch (MalformedURLException e_malopenurl)
                {
                    System.out.println("++YutilIOHandler:openOutputStream|Malformed dst URL argstr_dsturl=[" + argstr_dsturl + "],exception=[" + e_malopenurl + "]");
                    return null;
                }
                System.out.println("++YutilIOHandler:openOutputStream|openURL=[" + openURL + "]");
            }
            else
            
            {
                System.out.println("++YutilIOHandler:ERROR - neither dst nor dstfile nor dsturl property set");
                return null;
            }
            
        }

        return os;
    }
    //==========================================================================


}

