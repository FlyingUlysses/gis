//==============================================================================
// YcadvPane.java
//
// Ycadv JavaBean
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/app/ycadv/YcadvPane.java,v 1.4 2003/05/08 08:46:23 ekarlo Exp $
// $Log: YcadvPane.java,v $
// Revision 1.4  2003/05/08 08:46:23  ekarlo
// Remove warnings.
//
// Revision 1.3  2003/04/14 12:37:26  ekarlo
// Update source file header for OSI release.
//
// Revision 1.2  2001/10/18 05:16:42  ekarlo
// Change package paths to lower case.
//
// Revision 1.1  2001-10-17 22:13:38-06  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.app.ycadv;


import java.awt.*;
import java.util.*;
import java.beans.*;

import javax.swing.*;

import com.ysystems.lib.yutil.*;
import com.ysystems.ycad.lib.ydxf.*;
import com.ysystems.ycad.lib.yxxf.*;


/**
 * Main class for the Ycad viewer JavaBean.
 *
 * @author Ed Karlo - Y Systems, LLC
 */
public class YcadvPane extends JPanel
{
    //==========================================================================
    /**
     * Argument array.
     */
    private
    String                      argv[];
    //==========================================================================


    //==========================================================================
    // Constructors

    /**
     * Constructor - Application (empty).
     */
    public
    YcadvPane()
    {
//d     System.out.println("++YcadvPane:APPLICATION CONSTRUCTOR");
    }

    public
    void setArgv(String argv[])
    {
        System.out.println("++YcadvPane:setArgv|BEG");
        this.argv = argv;
        System.out.println("++YcadvPane:setArgv|END");
    }
    //==========================================================================


    //==========================================================================
    // Properties

    /** 
     * All user supplied properties.
     */
    private
    YutilProperties             props_user              = new YutilProperties();

    /**
     * Application window width.
     */
    private
    int                         param_width             = 0;
    /**
     * Application window height.
     */
    private
    int                         param_height            = 0;
    //==========================================================================


    //==========================================================================
    // Drawing

    /**
     * Main drawing.
     */
    private
    Yxxf                        D                       = null;
    //==========================================================================


    //==========================================================================
    /**
     * Get Handler.
     */
    private
    YdxfGetHandler              gethandler              = null;
    //==========================================================================


    //==========================================================================
    // View

    /**
     * View Handler.
     */
    public
    YxxfDrwViewHandler          vhandler                = null;

    /**
     * Viewer.
     */
    private
    YxxfDrwView                 viewer                  = null;
    //==========================================================================


    //==========================================================================
    // Beans related

    // Property booleanValue
    private boolean booleanValue;
    public boolean isBooleanValue() {
        return booleanValue;
    } 
    public void setBooleanValue(boolean newValue) {
        boolean oldValue = booleanValue;
        booleanValue = newValue;
        changes.firePropertyChange("booleanValue", new Boolean(oldValue), new Boolean(newValue));
    }


    // Property baseURL
    private String baseURL;
    public String getBaseURL() {
        if (baseURL == null) {
            return "";
        } else
            return baseURL;
    }
    public void setBaseURL(String newValue) {
        String oldValue = baseURL;
        baseURL = newValue;
        changes.firePropertyChange("baseURL", oldValue, newValue);
    }


    // Property src
    private String src;
    public String getSrc() {
        if (src == null) {
            return "";
        } else
            return src;
    }
    public void setSrc(String newValue) {
        String oldValue = src;
        src = newValue;
        changes.firePropertyChange("src", oldValue, newValue);
    }


    // Property srcFile
    private String srcFile;
    public String getSrcFile() {
        if (srcFile == null) {
            return "";
        } else
            return srcFile;
    }
    public void setSrcFile(String newValue) {
        String oldValue = srcFile;
        srcFile = newValue;
        changes.firePropertyChange("srcFile", oldValue, newValue);
    }


    // Property srcURL
    private String srcURL;
    public String getSrcURL() {
        if (srcURL == null) {
            return "";
        } else
            return srcURL;
    }
    public void setSrcURL(String newValue) {
        String oldValue = srcURL;
        srcURL = newValue;
        changes.firePropertyChange("srcURL", oldValue, newValue);
    }


    // Property printHeader
    private boolean printHeader;
    public boolean isPrintHeader() {
        return printHeader;
    } 
    public void setPrintHeader(boolean newValue) {
        boolean oldValue = printHeader;
        printHeader = newValue;
        changes.firePropertyChange("printHeader", new Boolean(oldValue), new Boolean(newValue));
    }


    // Property printLayers
    private boolean printLayers;
    public boolean isPrintLayers() {
        return printLayers;
    } 
    public void setPrintLayers(boolean newValue) {
        boolean oldValue = printLayers;
        printLayers = newValue;
        changes.firePropertyChange("printLayers", new Boolean(oldValue), new Boolean(newValue));
    }


    // Property printLtypes
    private boolean printLtypes;
    public boolean isPrintLtypes() {
        return printLtypes;
    } 
    public void setPrintLtypes(boolean newValue) {
        boolean oldValue = printLtypes;
        printLtypes = newValue;
        changes.firePropertyChange("printLtypes", new Boolean(oldValue), new Boolean(newValue));
    }


    // Property printVports
    private boolean printVports;
    public boolean isPrintVports() {
        return printVports;
    } 
    public void setPrintVports(boolean newValue) {
        boolean oldValue = printVports;
        printVports = newValue;
        changes.firePropertyChange("printVports", new Boolean(oldValue), new Boolean(newValue));
    }


    // Property printStyles
    private boolean printStyles;
    public boolean isPrintStyles() {
        return printStyles;
    } 
    public void setPrintStyles(boolean newValue) {
        boolean oldValue = printStyles;
        printStyles = newValue;
        changes.firePropertyChange("printStyles", new Boolean(oldValue), new Boolean(newValue));
    }


    // Property printBlocks
    private boolean printBlocks;
    public boolean isPrintBlocks() {
        return printBlocks;
    } 
    public void setPrintBlocks(boolean newValue) {
        boolean oldValue = printBlocks;
        printBlocks = newValue;
        changes.firePropertyChange("printBlocks", new Boolean(oldValue), new Boolean(newValue));
    }


    // Property printEntities
    private boolean printEntities;
    public boolean isPrintEntities() {
        return printEntities;
    } 
    public void setPrintEntities(boolean newValue) {
        boolean oldValue = printEntities;
        printEntities = newValue;
        changes.firePropertyChange("printEntities", new Boolean(oldValue), new Boolean(newValue));
    }


    // Property drawDspMinMax
    private boolean drawDspMinMax;
    public boolean isDrawDspMinMax() {
        return drawDspMinMax;
    } 
    public void setDrawDspMinMax(boolean newValue) {
        boolean oldValue = drawDspMinMax;
        drawDspMinMax = newValue;
        changes.firePropertyChange("drawDspMinMax", new Boolean(oldValue), new Boolean(newValue));
    }


    // Property drawExtMinMax
    private boolean drawExtMinMax;
    public boolean isDrawExtMinMax() {
        return drawExtMinMax;
    } 
    public void setDrawExtMinMax(boolean newValue) {
        boolean oldValue = drawExtMinMax;
        drawExtMinMax = newValue;
        changes.firePropertyChange("drawExtMinMax", new Boolean(oldValue), new Boolean(newValue));
    }


    // Property drawLimMinMax
    private boolean drawLimMinMax;
    public boolean isDrawLimMinMax() {
        return drawLimMinMax;
    } 
    public void setDrawLimMinMax(boolean newValue) {
        boolean oldValue = drawLimMinMax;
        drawLimMinMax = newValue;
        changes.firePropertyChange("drawLimMinMax", new Boolean(oldValue), new Boolean(newValue));
    }




    // Display methods
    public java.awt.Dimension getPreferredSize() {
        return new java.awt.Dimension(150,150);
    }


    /**
     * @deprecated provided for backward compatibility with old layout managers.
     */
    public Dimension preferredSize() {
        return getPreferredSize();
    }


    // Some utility objects
    private PropertyChangeSupport changes = new PropertyChangeSupport(this);
    private VetoableChangeSupport vetos = new VetoableChangeSupport(this);
    private java.util.Vector listeners = new java.util.Vector();
    private java.util.Vector actionListeners = new java.util.Vector();
    //==========================================================================


    /**
     * Called when applet is loaded (before the first time start() is called).
     *<UL>
     *   <LI> Create drawing
     *   <LI> Create I/O handler and name in drawing
     *   <LI> Create Get handler
     *   <LI> Process properties
     *   <LI> Create View Handler and get ref to viewer panel
     *   <LI> Start Input
     *   <LI> Start viewer rolling
     *</UL>
     */
    public
    void init()
    {
        System.out.println("++YcadvPane:init|BEG");
//d     System.out.println("++YcadvPane:init|YcadvPane=[" + this + "]");


        // ============================================================
        // Set up drawing
        //
        // Create drawing
        // Create I/O handler and name in drawing
        // Create Get handler
        // ============================================================

        // Create drawing
        D = new Yxxf();

        // Create I/O Handler
        D.iohandler = new YutilIOHandler();

        // Create empty main drawing ioname
        D.ioname = new YutilIOHandlerName("main");


        // Create Get Handler
        gethandler =  new YdxfGetHandler();




        // ============================================================
        // Set props_user - sets I/O name values
        // ============================================================
        processProperties();




        // ============================================================
        // Set up view handler and view
        // ============================================================
//d     System.out.println("++YcadvPane:init|setting up view");

        //
        // Create view handler (Observer)
        //
        vhandler = new YxxfDrwViewHandler();

        // Set parameters in view handler
        // Copy in all properties from props_user that are not used here
        for (Enumeration ep = props_user.propertyNames(); ep.hasMoreElements(); )
        {
            String key = ((String)ep.nextElement()).toLowerCase();
            String keylower = key.toLowerCase();

            if (!keylower.equals("width") &&
                !keylower.equals("height") &&
                !keylower.equals("baseurl") &&
                !keylower.equals("src") &&
                !keylower.equals("srcfile") &&
                !keylower.equals("srcurl") &&
                !keylower.startsWith("stylesrc") &&
                !keylower.startsWith("stylesrcfile") &&
                !keylower.startsWith("stylesrcurl"))
            {
                String val = key + "=" + props_user.getProperty(key);
                vhandler.setProperties(val, false);
            }
        }
        vhandler.commandViewHandler_command_init(D);
        vhandler.commandViewHandler_command_start();


        //
        // Create viewer panel and add to Applet
        //
        setLayout(new GridBagLayout());

        viewer = vhandler.getViewer();

        // Position and add the viewer to Applet
        YutilAWT.constrain(this, viewer,
            0, // gridx
            GridBagConstraints.RELATIVE, // gridy

            GridBagConstraints.REMAINDER, // gridwidth
            GridBagConstraints.REMAINDER, // gridheight

            GridBagConstraints.BOTH, // fill
            GridBagConstraints.NORTHWEST, // anchor

            1, 1, // weightx, weighty
            0, 0, // ipadx, ipady
            0, 0, 0, 0); // Insets T,L,B,R

        validate();

//d     System.out.println("++YcadvPane:init|viewer:printState=[" + viewer.printState() + "]");


        //
        // Force press of Start button
        //
        vhandler.commandViewHandler_toolbar_start();




        // ============================================================
        // Start Input
        // ============================================================
        // Start get drawing
//d     System.out.println("++YcadvPane:init|BEG gethandler.commandGetStart");
        gethandler.commandGetStart(D);
//d     System.out.println("++YcadvPane:init|END gethandler.commandGetStart");

        System.out.println("++YcadvPane:init|END");
    }


    /**
     * Called after init() and each time the applet becomes visible.
     */
    public
    void start()
    {
//d     System.out.println("++YcadvPane:BEG START");
//d     System.out.println("++YcadvPane:END START");
    }


    /**
     * Called before destroy() and each time the applet becomes invisible.
     */
    public
    void stop()
    {
//d     System.out.println("++YcadvPane:BEG STOP");
//d     System.out.println("++YcadvPane:END STOP");
    }


    /**
     * Called when applet is being eliminated.
     */
    public
    void destroy()
    {
//d     System.out.println("++YcadvPane:BEG DESTROY");
//d     System.out.println("++YcadvPane:END DESTROY");
    }


    /**
     * Get user parameter width value.
     * @return The width
     */
    public
    int getWidth()
    {
        return param_width;
    }


    /**
     * Get user parameter height value.
     * @return The height
     */
    public
    int getHeight()
    {
        return param_height;
    }


    /**
     * Process properties.
     *<UL>
     *  <LI>Set defaults (file and url have none).
     *  <LI>Load properties.
     *  <LI>Set local params.
     *  <LI>All user params examined - do post processing of source names.
     *</UL>
     */
    private
    void processProperties()
    {
        // Set defaults (file and url have none)
        props_user.setPropertiesDefaults("width=640", false);
        props_user.setPropertiesDefaults("height=480", false);


        // Load properties
        props_user.setProperties(argv);

        // Load bean properties
        if (src != null)
            props_user.setProperties("src=" + src, false);
        if (srcFile != null)
            props_user.setProperties("srcFile=" + srcFile, false);
        if (srcURL != null)
            props_user.setProperties("srcURL=" + srcURL, false);


        // Set local params
        System.out.println("++YcadvPane:processProperties|props_user BEG");
        for (Enumeration e = props_user.propertyNames(); e.hasMoreElements(); )
        {
            String key = (String)e.nextElement();
            String val = props_user.getProperty(key);
            System.out.println("++YcadvPane:processProperties|key=[" + key + "],val=[" + val + "]");

            String keylower = key.toLowerCase();


            // Application window size
            if (keylower.equals("width"))
            {
                param_width = props_user.getProperty_int(key);
            }
            else
            if (keylower.equals("height"))
            {
                param_height = props_user.getProperty_int(key);
            }
            else



            //
            // Main drawing source
            //
            if (keylower.equals("baseurl"))
            {   // Base URL for all sources
                D.ioname.baseurl = val;
            }
            else


            if (keylower.equals("src"))
            {
                D.ioname.src = val;
            }
            else

            if (keylower.equals("srcfile"))
            {
                D.ioname.srcfile = val;
            }
            else

            if (keylower.equals("srcurl"))
            {
                D.ioname.srcurl = val;
            }
            else


            //
            // Style shape source
            //
            if (keylower.startsWith("stylesrc") ||
                keylower.startsWith("stylesrcfile") ||
                keylower.startsWith("stylesrcurl"))
            {
                String stylename = ""; // Default style name is empty string

                // Extract style name from key name
                if (keylower.startsWith("stylesrc"))
                {
                    if (keylower.length() > 8)
                        stylename = keylower.substring(8).toUpperCase();
                }
                else
                if (keylower.startsWith("stylesrcfile"))
                {
                    if (keylower.length() > 12)
                        stylename = keylower.substring(12).toUpperCase();
                }
                else
                if (keylower.startsWith("stylesrcurl"))
                {
                    if (keylower.length() > 11)
                        stylename = keylower.substring(11).toUpperCase();
                }


                // Find matching style element
                YxxfTblStyle style = D.secTables.findStyle_add(stylename);

                if (style.shape == null)
                {
                    style.shape = new YxxfShape(); // all styles will use shapes
                }

                if (style.shape.ioname == null)
                {   // not set yet, create
                    style.shape.ioname = new YutilIOHandlerName(stylename);
                }


                // Set proper value
                if (keylower.startsWith("stylesrc"))
                    style.shape.ioname.src = val;
                else
                if (keylower.startsWith("stylesrcfile"))
                    style.shape.ioname.srcfile = val;
                else
                if (keylower.startsWith("stylesrcurl"))
                    style.shape.ioname.srcurl = val;
            }

        }
        System.out.println("++YcadvPane:processProperties|props_user END");


        // All user params examined - do post processing of source names
//d     System.out.println("++YcadvPane:processProperties|D.ioname=[" + D.ioname + "]");

        // For each style
        // 1) Set iohandler in each style to match main iohandler
        // 2) Set baseurl in each style ioname to match main ioname
//d     System.out.println("++YcadvPane:processProperties|style ioname.e_ioname BEG");
        for (Enumeration e_ioname = D.secTables.tblStyle.elements(); e_ioname.hasMoreElements(); ) // eieio
        {
            YxxfTblStyle style = (YxxfTblStyle)e_ioname.nextElement();

            if (style.shape == null)
            {
                continue;
            }

            if (style.shape.ioname == null)
            {
                continue;
            }

            // 1)
            style.shape.iohandler = D.iohandler;

            // 2)
            style.shape.ioname.baseurl = D.ioname.baseurl;
//d         System.out.println("++YcadvPane:processProperties|style ioname:name=[" + style.getName() + "],ioname=[" + style.shape.ioname + "]");
        }
//d     System.out.println("++YcadvPane:processProperties|style ioname.e_ioname END");
    }




    //==========================================================================
    /**
     * Driver for JavaBean.
     *<UL>
     *  <LI>Instantiate the Application.
     *  <LI>Create a Frame for the Application.
     *  <LI>Add the Application to the Frame (Frame's use BorderLayout).
     *  <LI>Init and start the Application.
     *  <LI>Resize the Frame according to user parameters.
     *  <LI>Show and start the Application.
     *<UL>
     * @param argv Command line arguments.
     */
    public static
    void main(String argv[])
    {
        // Instantiate the Application
        YcadvPane vpane = new YcadvPane();
        vpane.setArgv(argv);


        // Create a Frame for the Applet
        YcadvPaneFrame vframe = new YcadvPaneFrame("YcadvPane", vpane);

        // Add the Applet to the Frame (Frame's use BorderLayout)
        vframe.add("Center", vpane);


        // Init and start the Applet
        // TODO: bail if init errors
        vpane.init();

        // Resize the Frame according to user parameters
//d     System.out.println("++YcadvPane:w=" + vapplet.getWidth() + ",h=" + vapplet.getHeight());
        vframe.resize(vpane.getWidth(), vpane.getHeight()); // calls Component.resize(int width, int height)

        // Show and start the Applet
        vframe.show();
        vpane.start();
    }
    //==========================================================================
}




/**
 * Extend Frame to handle the window-close event.
 */
class YcadvPaneFrame extends Frame
{
    /**
     * A Ycadv Pane.
     */
    YcadvPane vpane;

    public YcadvPaneFrame(String s, YcadvPane vpane)
    {
        super(s);
        this.vpane = vpane;
    }

     /**
      * Handle close events by simply exiting.
      * @return false = failure
      */
    public boolean handleEvent(Event e)
    {
//d     System.out.println("++YcadvPaneFrame:handleEvent" + e);
        if (e.id == Event.WINDOW_DESTROY)
        {
            vpane.stop();
            vpane.destroy();
            System.exit(0);
        }
        return false;
    }
}
