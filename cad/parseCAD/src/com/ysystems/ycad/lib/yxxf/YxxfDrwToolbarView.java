//==============================================================================
// YxxfDrwToolbarView.java
//
// Default simple tool bar
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfDrwToolbarView.java,v 1.25 2003/05/17 12:42:20 ekarlo Exp $
// $Log: YxxfDrwToolbarView.java,v $
// Revision 1.25  2003/05/17 12:42:20  ekarlo
// Add option to control width of border around views.
//
// Revision 1.24  2003/05/08 11:24:02  ekarlo
// Remove warnings.
//
// Revision 1.23  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.22  2002/09/26 07:51:22  ekarlo
// Imprinter development check-in.
//
// Revision 1.21  2001-05-18 23:14:25-06  ekarlo
// Print test.
//
// Revision 1.20  2001-05-11 22:58:46-06  ekarlo
// Make var name consistent.
//
// Revision 1.19  2000-10-17 01:44:04-06  ekarlo
// Change package paths to lower case.
//
// Revision 1.18  2000-10-16 17:49:32-06  ekarlo
// Browser printing test code.
//
// Revision 1.17  1999-10-22 01:28:00-06  ekarlo
// API rework - phase 1.
//
// Revision 1.16  1999-10-06 20:07:45-06  walter
// Added JavaDoc comments.
//
// Revision 1.15  1999-07-09 14:10:50-06  ekarlo
// Rearrange package names.
//
// Revision 1.14  1999/07/09  15:21:07  ekarlo
// Improve param properties scan.
//
// Revision 1.13  1999/07/06  02:45:37  ekarlo
// Add calc and zoom extents.
//
// Revision 1.12  1999/06/29  19:44:34  ekarlo
// Move dbprint command to general toolbar line.
//
// Revision 1.11  1999/06/20  22:34:10  ekarlo
// Rearrange package names.
//
// Revision 1.10  1999/06/15  04:58:33  ekarlo
// User Interface - phase 1.
//
// Revision 1.9  1999/02/09  14:50:20  ekarlo
// Deactivate console print.
//
// Revision 1.8  1998/08/24  20:41:06  ekarlo
// Add status display.
//
// Revision 1.7  1998/08/19  01:18:43  ekarlo
// Rename classes to change "Panel" to "View" in name.
//
// Revision 1.6  1998/02/02  19:07:19  ekarlo
// Explicitly set background color of buttons to light gray.
// (Was wrong on UNIX systems.)
//
// Revision 1.5  1997/12/26  21:29:14  ekarlo
// Implement paper space - phase 1.
// Rename properties object.
// Rename from YcadvAppButtonPanel to YxxfDrwButtonPanel.
//
// Revision 1.4  1997/08/30  14:01:30  ekarlo
// Redo initialization action.
// Change user properties.
//
// Revision 1.3  1997/07/22  12:45:42  ekarlo
// Rename from YcadvAppButtons.java to YcadvAppButtonPanel.java.
//
// Revision 1.2  1997/07/21  20:43:05  ekarlo
// MVC-VH rework - phase 2.
//
// Revision 1.1  1997/07/13  17:04:03  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;


import java.awt.*;

import com.ysystems.lib.yutil.*;


/**
 * Default simple tool bar.
 */
public class YxxfDrwToolbarView extends Panel
{
    //==========================================================================
    /**
     * The Properties collection.
     */
    private YutilProperties     props_DrwToolbarView    = new YutilProperties();
    //==========================================================================


    //==========================================================================
    // Interface items

    private Color               toolbar_bgcolor         = new Color(0xFF, 0xCC, 0x99);
    /**
     * The start button.
     */
    private Button              btn_start;

    /**
     * The command button.
     */
    private Button              btn_cmd;
    private Rectangle           btn_cmd_bounds          = null;


    /**
     * cmd 0.
     */
//  private Label               lab_text;


    /**
     * cmd 1 - Redraw.
     */
    private Button              btn_redraw;
    /**
     * cmd 1 - Restore.
     */
    private Button              btn_restore;
    /**
     * cmd 1 - Calculate Extents.
     */
    private Button              btn_calc_extents;
    /**
     * cmd 1 - List Stats.
     */
    private Button              btn_liststats;
    /**
     * cmd 1 - Print.
     */
    private Button              btn_print;


    /**
     * cmd 2 - Zoom Label.
     */
    private Label               lab_zoom_1;
    /**
     * cmd 2 - Zoom In.
     */
    private Button              btn_zoom_in;
    /**
     * cmd 2 - Zoom Out.
     */
    private Button              btn_zoom_out;
    /**
     * cmd 2 - Zoom to Head Extents.
     */
    private Button              btn_zoom_to_head_extents;
    /**
     * cmd 2 - Zoom to Calc Extents.
     */
    private Button              btn_zoom_to_calc_extents;
    /**
     * cmd 2 - Second Zoom label 
     */
    private Label               lab_zoom_2;


    /**
     * cmd 3 - Pan Label.
     */
    private Label               lab_pan_1;
    /**
     * cmd 3 - Pan Left.
     */
    private Button              btn_pan_l;
    /**
     * cmd 3 - Pan Right.
     */
    private Button              btn_pan_r;
    /**
     * cmd 3 - Pan Up.
     */
    private Button              btn_pan_u;
    /**
     * cmd 3 - Pan Down.
     */
    private Button              btn_pan_d;
    /**
     * cmd 3 - Second Pan Label.
     */
    private Label               lab_pan_2;


    /**
     * cmd 4 - Rotate Label.
     */
    private Label               lab_rotate;
    /**
     * cmd 4 - Rotate around the x+ axis.
     */
    private Button              btn_rotate_x_p;
    /**
     * cmd 4 - Rotate around the x- axis.
     */
    private Button              btn_rotate_x_m;
    /**
     * cmd 4 - Rotate around the y+ axis.
     */
    private Button              btn_rotate_y_p;
    /**
     * cmd 4 - Rotate around the y- axis.
     */
    private Button              btn_rotate_y_m;
    /**
     * cmd 4 - Rotate around the z+ axis.
     */
    private Button              btn_rotate_z_p;
    /**
     * cmd 4 - Rotate around the z- axis.
     */
    private Button              btn_rotate_z_m;

    /**
     * Status line text.
     */
    private String              status_txt              = "";

    /**
     * Status line text font metrics.
     */
    private FontMetrics         status_txt_fontmetrics  = null;

    /**
     * Status line text height from font metrics.
     */
    private int                 status_txt_height       = 0;
    //==========================================================================


    //==========================================================================
    // View Control

    /**
     * Start command bar as int. 
     */
    public static int           CMD_I_START     = 0;
    /**
     * Text command bar as int. 
     */
    public static int           CMD_I_TEXT      = 1;
    /**
     * Draw command bar as int.
     */
    public static int           CMD_I_DRAW      = 2;
    /**
     * Zoom command bar as int.
     */
    public static int           CMD_I_ZOOM      = 3;
    /**
     * Pan command bar as int.
     */
    public static int           CMD_I_PAN       = 4;
    /**
     * Rotate command bar as int.
     */
    public static int           CMD_I_ROTATE    = 5;

    /**
     * Start command bar as Integer. 
     */
    public static Integer       CMD_C_START     = new Integer(CMD_I_START);
    /**
     * Text command bar as Integer. 
     */
    public static Integer       CMD_C_TEXT      = new Integer(CMD_I_TEXT);
    /**
     * Draw command bar as Integer. 
     */
    public static Integer       CMD_C_DRAW      = new Integer(CMD_I_DRAW);
    /**
     * Zoom command bar as Integer. 
     */
    public static Integer       CMD_C_ZOOM      = new Integer(CMD_I_ZOOM);
    /**
     * Pan command bar as Integer. 
     */
    public static Integer       CMD_C_PAN       = new Integer(CMD_I_PAN);
    /**
     * Rotate command bar as Integer. 
     */
    public static Integer       CMD_C_ROTATE    = new Integer(CMD_I_ROTATE);

    /**
     * Maximum number of Commands.
     */
    public static int           CMD_MAX         = 6;

    /**
     * The current command bar setting.
     */
    private int                 cmd_curr        = -1; // initial state unknown
    //==========================================================================


    //==========================================================================
    /**
     * The View Handler.
     */
    private
    YxxfDrwViewHandler          vhandler        = null;

    /**
     * View Handler Event Sender.
     */
    private
    YxxfDrwViewMonitor          vhandlermon     = new YxxfDrwViewMonitor();
    //==========================================================================


    //==========================================================================
    /**
     * Constructor (empty)
     */
    public
    YxxfDrwToolbarView()
    {
//d     System.out.println("++++++YxxfDrwToolbarView:BEG constructor1");

//d     System.out.println("++++++YxxfDrwToolbarView:END constructor1");
    }


    /**
     * Constructor
     * @param vhandler The View Handler.
     */
    public
    YxxfDrwToolbarView(YxxfDrwViewHandler vhandler)
    {
        this();

//d     System.out.println("++++++YxxfDrwToolbarView:BEG constructor2");

        setViewHandler(vhandler);

//d     System.out.println("++++++YxxfDrwToolbarView:END constructor2");
    }


    /**
     * Set the View Handler.
     * @param vhandler The View Handler.
     * @return A reference to the View Handler.
     */
    public
    void setViewHandler(YxxfDrwViewHandler vhandler)
    {
        vhandlermon.deleteObservers();

        this.vhandler = vhandler;

        if (vhandler != null)
            vhandlermon.addObserver(vhandler);
    }
    //==========================================================================


    //==========================================================================
    // Set properties

    /**
     * Set properties from args string.
     * @param args The property collection.
     * @param scanout TODO.
     */
    public
    void setProperties(String args, boolean scanout)
    {
        props_DrwToolbarView.setProperties(args, scanout);
    }

    /**
     * Set properties from argv array
     * @param args The property collection.
     */
    public
    void setProperties(String argv[])
    {
        props_DrwToolbarView.setProperties(argv);
    }

    /**
     * Set properties from argprops YutilProperties
     * @param args The property collection.
     */
    public
    void setProperties(YutilProperties argprops)
    {
        props_DrwToolbarView.setProperties(argprops);
    }
    //==========================================================================


    //==========================================================================
    /**
     * Initialize the Toolbar Panel.
     */
    public
    void commandInit()
    {
//d     System.out.println("++++++YxxfDrwToolbarView:BEG commandInit");

//d     // Display local params
//d     System.out.println("++++++YxxfDrwToolbarView:props_DrwToolbarView.e BEG");
//d     for (Enumeration e = props_DrwToolbarView.propertyNames(); e.hasMoreElements(); )
//d     {
//d         String key = (String)e.nextElement();
//d         String val = props_DrwToolbarView.getProperty(key);
//d         System.out.println("++++++YxxfDrwToolbarView:key=[" + key + "],val=[" + val + "]");
//d     }
//d     System.out.println("++++++YxxfDrwToolbarView:props_DrwToolbarView.e END");

        // Set background color
        setBackground(toolbar_bgcolor);

        // Set font
        setFont(new Font("Helvetica", Font.BOLD, 11));

        // Set layout
        setLayout(new GridBagLayout());


        btn_cmd             = new Button("Cmd");
        btn_cmd             .setBackground(Color.lightGray);

        btn_start           = new Button("Start");
        btn_start           .setBackground(Color.lightGray);

//      lab_text            = new Label();
//      lab_text            .setForeground(Color.black);
//      lab_text            .setBackground(new Color(0, 0xFF, 0xFF, 0xFF));

        btn_redraw          = new Button("Redraw");
        btn_redraw          .setBackground(Color.lightGray);
        btn_restore         = new Button("Restore");
        btn_restore         .setBackground(Color.lightGray);
        btn_calc_extents    = new Button("Calc Extents");
        btn_calc_extents    .setBackground(Color.lightGray);
        btn_liststats       = new Button("ListStats");
        btn_liststats       .setBackground(Color.lightGray);
        btn_print           = new Button("Print");
        btn_print           .setBackground(Color.lightGray);

        lab_zoom_1          = new Label("Zoom");
        lab_zoom_1          .setAlignment(Label.CENTER);
        lab_zoom_1          .setBackground(toolbar_bgcolor);
        btn_zoom_in         = new Button("In");
        btn_zoom_in         .setBackground(Color.lightGray);
        btn_zoom_out        = new Button("Out");
        btn_zoom_out        .setBackground(Color.lightGray);
        btn_zoom_to_head_extents    = new Button("To Head Extents");
        btn_zoom_to_head_extents    .setBackground(Color.lightGray);
        btn_zoom_to_calc_extents    = new Button("To Calc Extents");
        btn_zoom_to_calc_extents    .setBackground(Color.lightGray);
        lab_zoom_2          = new Label("[use mouse for zoom window]");
        lab_zoom_2          .setAlignment(Label.CENTER);
        lab_zoom_2          .setBackground(toolbar_bgcolor);

        lab_pan_1           = new Label("Pan");
        lab_pan_1           .setAlignment(Label.CENTER);
        lab_pan_1           .setBackground(toolbar_bgcolor);
        btn_pan_l           = new Button("L");
        btn_pan_l           .setBackground(Color.lightGray);
        btn_pan_r           = new Button("R");
        btn_pan_r           .setBackground(Color.lightGray);
        btn_pan_u           = new Button("U");
        btn_pan_u           .setBackground(Color.lightGray);
        btn_pan_d           = new Button("D");
        btn_pan_d           .setBackground(Color.lightGray);
        lab_pan_2           = new Label("[use mouse for pan line]");
        lab_pan_2           .setAlignment(Label.CENTER);
        lab_pan_2           .setBackground(toolbar_bgcolor);

        lab_rotate          = new Label("Rotate");
        lab_rotate          .setAlignment(Label.CENTER);
        lab_rotate          .setBackground(toolbar_bgcolor);
        btn_rotate_x_p      = new Button("x+");
        btn_rotate_x_p      .setBackground(Color.lightGray);
        btn_rotate_x_m      = new Button("x-");
        btn_rotate_x_m      .setBackground(Color.lightGray);
        btn_rotate_y_p      = new Button("y+");
        btn_rotate_y_p      .setBackground(Color.lightGray);
        btn_rotate_y_m      = new Button("y-");
        btn_rotate_y_m      .setBackground(Color.lightGray);
        btn_rotate_z_p      = new Button("z+");
        btn_rotate_z_p      .setBackground(Color.lightGray);
        btn_rotate_z_m      = new Button("z-");
        btn_rotate_z_m      .setBackground(Color.lightGray);

//d     System.out.println("++++++YxxfDrwToolbarView:END commandInit");
    }
    //==========================================================================


    //==========================================================================
    /**
     * Start the Toolbar Panel.
     */
    public
    void commandStart()
    {
//d     System.out.println("++++++YxxfDrwToolbarView:BEG commandStart");

        setCommandBar(CMD_I_START);

//d     System.out.println("++++++YxxfDrwToolbarView:END commandStart");
    }
    //==========================================================================


    //==========================================================================
    /**
     * Format the Toolbar.
     * @param cmd The command.
     */
    public
    void setCommandBar(int cmd)
    {
        int gridpos = 0;

        removeAll();
        btn_cmd_bounds = null;
        
        cmd_curr = cmd;


        // start
        if (cmd_curr == CMD_I_START)
        {   // start bar has only start button - note weightx value
            YutilAWT.constrain(this, btn_start,
                gridpos++, // gridx
                0, // gridy

                1, // gridwidth
                1, // gridheight

                GridBagConstraints.NONE, // fill
                GridBagConstraints.NORTHWEST, // anchor

                1, 0, // weightx, weighty
                0, 0, // ipadx, ipady
                0, 0, 0, 0); // Insets T,L,B,R
        }
        else


        // status text
        if (cmd_curr == CMD_I_TEXT)
        {   // text bar has only command button - note weightx value
            YutilAWT.constrain(this, btn_cmd,
                gridpos++, // gridx
                0, // gridy

                1, // gridwidth
                1, // gridheight

                GridBagConstraints.NONE, // fill
                GridBagConstraints.NORTHWEST, // anchor

                1, 0, // weightx, weighty
                0, 0, // ipadx, ipady 
                0, 0, 0, 0); // Insets T,L,B,R

            /* ===
            YutilAWT.constrain(this, lab_text,
                gridpos++, // gridx
                0, // gridy
  
                1, // gridwidth
                1, // gridheight
  
                GridBagConstraints.NONE, // fill
                GridBagConstraints.NORTHWEST, // anchor
  
                1, 0, // weightx, weighty
                0, 0, // ipadx, ipady 
                0, 6, 0, 0); // Insets T,L,B,R
            === */
        }
        else


        // draw
        if (cmd_curr == CMD_I_DRAW)
        {
            YutilAWT.constrain(this, btn_cmd,
                gridpos++, // gridx
                0, // gridy

                1, // gridwidth
                1, // gridheight

                GridBagConstraints.NONE, // fill
                GridBagConstraints.NORTHWEST, // anchor

                0, 0, // weightx, weighty
                0, 0, // ipadx, ipady 
                0, 0, 0, 0); // Insets T,L,B,R

            YutilAWT.constrain(this, btn_redraw,
                gridpos++, // gridx
                0, // gridy

                1, // gridwidth
                1, // gridheight

                GridBagConstraints.NONE, // fill
                GridBagConstraints.NORTHWEST, // anchor

                0, 0, // weightx, weighty
                0, 0, // ipadx, ipady
                0, 0, 0, 0); // Insets T,L,B,R

            YutilAWT.constrain(this, btn_restore,
                gridpos++, // gridx
                0, // gridy

                1, // gridwidth
                1, // gridheight

                GridBagConstraints.NONE, // fill
                GridBagConstraints.NORTHWEST, // anchor

                0, 0, // weightx, weighty
                0, 0, // ipadx, ipady
                0, 0, 0, 0); // Insets T,L,B,R

            YutilAWT.constrain(this, btn_calc_extents,
                gridpos++, // gridx
                0, // gridy

                1, // gridwidth
                1, // gridheight

                GridBagConstraints.NONE, // fill
                GridBagConstraints.NORTHWEST, // anchor

                0, 0, // weightx, weighty
                0, 0, // ipadx, ipady
                0, 0, 0, 0); // Insets T,L,B,R

            YutilAWT.constrain(this, btn_liststats,
                gridpos++, // gridx
                0, // gridy

                1, // gridwidth
                1, // gridheight

                GridBagConstraints.NONE, // fill
                GridBagConstraints.NORTHWEST, // anchor

                0, 0, // weightx, weighty
                0, 0, // ipadx, ipady
                0, 0, 0, 0); // Insets T,L,B,R

            YutilAWT.constrain(this, btn_print,
                gridpos++, // gridx
                0, // gridy

                1, // gridwidth
                1, // gridheight

                GridBagConstraints.NONE, // fill
                GridBagConstraints.NORTHWEST, // anchor

                1, 0, // weightx, weighty
                0, 0, // ipadx, ipady
                0, 0, 0, 0); // Insets T,L,B,R
        }
        else


        // zoom
        if (cmd_curr == CMD_I_ZOOM)
        {
            YutilAWT.constrain(this, btn_cmd,
                gridpos++, // gridx
                0, // gridy

                1, // gridwidth
                1, // gridheight

                GridBagConstraints.NONE, // fill
                GridBagConstraints.NORTHWEST, // anchor

                0, 0, // weightx, weighty
                0, 0, // ipadx, ipady 
                0, 0, 0, 0); // Insets T,L,B,R

            YutilAWT.constrain(this, lab_zoom_1,
                gridpos++, // gridx
                0, // gridy

                1, // gridwidth
                1, // gridheight

                GridBagConstraints.NONE, // fill
                GridBagConstraints.NORTHWEST, // anchor

                0, 0, // weightx, weighty
                0, 0, // ipadx, ipady
                0, 0, 0, 0); // Insets T,L,B,R

            YutilAWT.constrain(this, btn_zoom_in,
                gridpos++, // gridx
                0, // gridy

                1, // gridwidth
                1, // gridheight

                GridBagConstraints.NONE, // fill
                GridBagConstraints.NORTHWEST, // anchor

                0, 0, // weightx, weighty
                0, 0, // ipadx, ipady
                0, 0, 0, 0); // Insets T,L,B,R

            YutilAWT.constrain(this, btn_zoom_out,
                gridpos++, // gridx
                0, // gridy

                1, // gridwidth
                1, // gridheight

                GridBagConstraints.NONE, // fill
                GridBagConstraints.NORTHWEST, // anchor

                0, 0, // weightx, weighty
                0, 0, // ipadx, ipady
                0, 0, 0, 0); // Insets T,L,B,R

            YutilAWT.constrain(this, btn_zoom_to_head_extents,
                gridpos++, // gridx
                0, // gridy

                1, // gridwidth
                1, // gridheight

                GridBagConstraints.NONE, // fill
                GridBagConstraints.NORTHWEST, // anchor

                0, 0, // weightx, weighty
                0, 0, // ipadx, ipady
                0, 0, 0, 0); // Insets T,L,B,R

            YutilAWT.constrain(this, btn_zoom_to_calc_extents,
                gridpos++, // gridx
                0, // gridy

                1, // gridwidth
                1, // gridheight

                GridBagConstraints.NONE, // fill
                GridBagConstraints.NORTHWEST, // anchor

                0, 0, // weightx, weighty
                0, 0, // ipadx, ipady
                0, 0, 0, 0); // Insets T,L,B,R

            YutilAWT.constrain(this, lab_zoom_2,
                gridpos++, // gridx
                0, // gridy

                1, // gridwidth
                1, // gridheight

                GridBagConstraints.NONE, // fill
                GridBagConstraints.NORTHWEST, // anchor

                1, 0, // weightx, weighty
                0, 0, // ipadx, ipady
                0, 0, 0, 0); // Insets T,L,B,R
        }
        else


        // pan
        if (cmd_curr == CMD_I_PAN)
        {
            YutilAWT.constrain(this, btn_cmd,
                gridpos++, // gridx
                0, // gridy

                1, // gridwidth
                1, // gridheight

                GridBagConstraints.NONE, // fill
                GridBagConstraints.NORTHWEST, // anchor

                0, 0, // weightx, weighty
                0, 0, // ipadx, ipady 
                0, 0, 0, 0); // Insets T,L,B,R

            YutilAWT.constrain(this, lab_pan_1,
                gridpos++, // gridx
                0, // gridy

                1, // gridwidth
                1, // gridheight

                GridBagConstraints.NONE, // fill
                GridBagConstraints.NORTHWEST, // anchor

                0, 0, // weightx, weighty
                0, 0, // ipadx, ipady
                0, 0, 0, 0); // Insets T,L,B,R

            YutilAWT.constrain(this, btn_pan_l,
                gridpos++, // gridx
                0, // gridy

                1, // gridwidth
                1, // gridheight

                GridBagConstraints.NONE, // fill
                GridBagConstraints.NORTHWEST, // anchor

                0, 0, // weightx, weighty
                0, 0, // ipadx, ipady
                0, 0, 0, 0); // Insets T,L,B,R

            YutilAWT.constrain(this, btn_pan_r,
                gridpos++, // gridx
                0, // gridy

                1, // gridwidth
                1, // gridheight

                GridBagConstraints.NONE, // fill
                GridBagConstraints.NORTHWEST, // anchor

                0, 0, // weightx, weighty
                0, 0, // ipadx, ipady
                0, 0, 0, 0); // Insets T,L,B,R

            YutilAWT.constrain(this, btn_pan_u,
                gridpos++, // gridx
                0, // gridy

                1, // gridwidth
                1, // gridheight

                GridBagConstraints.NONE, // fill
                GridBagConstraints.NORTHWEST, // anchor

                0, 0, // weightx, weighty
                0, 0, // ipadx, ipady
                0, 0, 0, 0); // Insets T,L,B,R

            YutilAWT.constrain(this, btn_pan_d,
                gridpos++, // gridx
                0, // gridy

                1, // gridwidth
                1, // gridheight

                GridBagConstraints.NONE, // fill
                GridBagConstraints.NORTHWEST, // anchor

                0, 0, // weightx, weighty
                0, 0, // ipadx, ipady
                0, 0, 0, 0); // Insets T,L,B,R

            YutilAWT.constrain(this, lab_pan_2,
                gridpos++, // gridx
                0, // gridy

                1, // gridwidth
                1, // gridheight

                GridBagConstraints.NONE, // fill
                GridBagConstraints.NORTHWEST, // anchor

                1, 0, // weightx, weighty
                0, 0, // ipadx, ipady
                0, 0, 0, 0); // Insets T,L,B,R
        }
        else


        // rotate
        if (cmd_curr == CMD_I_ROTATE)
        {
            YutilAWT.constrain(this, btn_cmd,
                gridpos++, // gridx
                0, // gridy

                1, // gridwidth
                1, // gridheight

                GridBagConstraints.NONE, // fill
                GridBagConstraints.NORTHWEST, // anchor

                0, 0, // weightx, weighty
                0, 0, // ipadx, ipady 
                0, 0, 0, 0); // Insets T,L,B,R

            YutilAWT.constrain(this, lab_rotate,
                gridpos++, // gridx
                0, // gridy

                1, // gridwidth
                1, // gridheight

                GridBagConstraints.NONE, // fill
                GridBagConstraints.NORTHWEST, // anchor

                0, 0, // weightx, weighty
                0, 0, // ipadx, ipady
                0, 0, 0, 0); // Insets T,L,B,R

            YutilAWT.constrain(this, btn_rotate_x_p,
                gridpos++, // gridx
                0, // gridy

                1, // gridwidth
                1, // gridheight

                GridBagConstraints.NONE, // fill
                GridBagConstraints.NORTHWEST, // anchor

                0, 0, // weightx, weighty
                0, 0, // ipadx, ipady
                0, 0, 0, 0); // Insets T,L,B,R

            YutilAWT.constrain(this, btn_rotate_x_m,
                gridpos++, // gridx
                0, // gridy

                1, // gridwidth
                1, // gridheight

                GridBagConstraints.NONE, // fill
                GridBagConstraints.NORTHWEST, // anchor

                0, 0, // weightx, weighty
                0, 0, // ipadx, ipady
                0, 0, 0, 0); // Insets T,L,B,R

            YutilAWT.constrain(this, btn_rotate_y_p,
                gridpos++, // gridx
                0, // gridy

                1, // gridwidth
                1, // gridheight

                GridBagConstraints.NONE, // fill
                GridBagConstraints.NORTHWEST, // anchor

                0, 0, // weightx, weighty
                0, 0, // ipadx, ipady
                0, 0, 0, 0); // Insets T,L,B,R

            YutilAWT.constrain(this, btn_rotate_y_m,
                gridpos++, // gridx
                0, // gridy

                1, // gridwidth
                1, // gridheight

                GridBagConstraints.NONE, // fill
                GridBagConstraints.NORTHWEST, // anchor

                0, 0, // weightx, weighty
                0, 0, // ipadx, ipady
                0, 0, 0, 0); // Insets T,L,B,R

            YutilAWT.constrain(this, btn_rotate_z_p,
                gridpos++, // gridx
                0, // gridy

                1, // gridwidth
                1, // gridheight

                GridBagConstraints.NONE, // fill
                GridBagConstraints.NORTHWEST, // anchor

                0, 0, // weightx, weighty
                0, 0, // ipadx, ipady
                0, 0, 0, 0); // Insets T,L,B,R

            YutilAWT.constrain(this, btn_rotate_z_m,
                gridpos++, // gridx
                0, // gridy

                1, // gridwidth
                1, // gridheight

                GridBagConstraints.NONE, // fill
                GridBagConstraints.NORTHWEST, // anchor

                1, 0, // weightx, weighty
                0, 0, // ipadx, ipady
                0, 0, 0, 0); // Insets T,L,B,R
        }


        validate();

        repaint();
    }
    //==========================================================================


    //==========================================================================
    /**
     * Get the current command bar setting.
     * @return The value of the current command bar.
     */
    public
    int getCommandBar()
    {
        return cmd_curr;
    }
    //==========================================================================


    //==========================================================================
    /**
     * Display Command text
     * @param txt The text.
     */
    public
    void commandShowText(String txt)
    {
        status_txt = txt;
        repaint();

        // lab_text.setText(txt);
        // lab_text.invalidate();
        // validate();
        // /* ===
        // if (cmd_curr == CMD_I_TEXT)
        // {
        //     validate();
        //     repaint();
        // }
        // === */
        // System.out.println("commandShowText:getText=[" + lab_text.getText() + "]");
    }
    //==========================================================================


    //==========================================================================
    /**
     * Respond to a button press/release.
     * @param evt The button that was pressed.
     * @param arg The button lable.
     * @return true if an action was performed.
     */
    public
    boolean action(Event evt, Object arg)
    {
//d     System.out.println("++++++YxxfDrwToolbarView:IN action,evt=[" + evt + "],modifiers=" + evt.modifiers);

        if (evt.target == btn_cmd) // spin bar
        {
            int cmd = (getCommandBar() + 1) % CMD_MAX;
            if (cmd == CMD_I_START) // can't go start again
                cmd = (cmd + 1) % CMD_MAX;
            setCommandBar(cmd);
            return true;
        }
        else

        if (evt.target == btn_start)
        {
            vhandlermon.notifyViewHandler(new YxxfDrwViewHandlerEvent(
                                          YxxfDrwViewHandlerEvent.TOOLBAR_START));
            return true;
        }
        else

        if (evt.target == btn_redraw)
        {
            vhandlermon.notifyViewHandler(new YxxfDrwViewHandlerEvent(
                                          YxxfDrwViewHandlerEvent.TOOLBAR_REDRAW));
            return true;
        }
        else

        if (evt.target == btn_restore)
        {
            vhandlermon.notifyViewHandler(new YxxfDrwViewHandlerEvent(
                                          YxxfDrwViewHandlerEvent.TOOLBAR_RESTORE));
            return true;
        }
        else

        if (evt.target == btn_calc_extents)
        {
            vhandlermon.notifyViewHandler(new YxxfDrwViewHandlerEvent(
                                          YxxfDrwViewHandlerEvent.TOOLBAR_CALC_EXTENTS));
            return true;
        }
        else

        if (evt.target == btn_liststats)
        {
            vhandlermon.notifyViewHandler(new YxxfDrwViewHandlerEvent(
                                          YxxfDrwViewHandlerEvent.TOOLBAR_LISTSTATS));
            return true;
        }
        else

        if (evt.target == btn_print)
        {
            vhandlermon.notifyViewHandler(new YxxfDrwViewHandlerEvent(
                                          YxxfDrwViewHandlerEvent.TOOLBAR_PRINT));
            return true;
        }
        else

        if (evt.target == btn_zoom_in)
        {
            vhandlermon.notifyViewHandler(new YxxfDrwViewHandlerEvent(
                                          YxxfDrwViewHandlerEvent.TOOLBAR_ZOOM_IN));
            return true;
        }
        else

        if (evt.target == btn_zoom_out)
        {
            vhandlermon.notifyViewHandler(new YxxfDrwViewHandlerEvent(
                                          YxxfDrwViewHandlerEvent.TOOLBAR_ZOOM_OUT));
            return true;
        }
        else

        if (evt.target == btn_zoom_to_head_extents)
        {
            vhandlermon.notifyViewHandler(new YxxfDrwViewHandlerEvent(
                                          YxxfDrwViewHandlerEvent.TOOLBAR_ZOOM_TO_HEAD_EXTENTS));
            return true;
        }
        else

        if (evt.target == btn_zoom_to_calc_extents)
        {
            vhandlermon.notifyViewHandler(new YxxfDrwViewHandlerEvent(
                                          YxxfDrwViewHandlerEvent.TOOLBAR_ZOOM_TO_CALC_EXTENTS));
            return true;
        }
        else

        if (evt.target == btn_pan_l)
        {
            vhandlermon.notifyViewHandler(new YxxfDrwViewHandlerEvent(
                                          YxxfDrwViewHandlerEvent.TOOLBAR_PAN_L));
            return true;
        }
        else

        if (evt.target == btn_pan_r)
        {
            vhandlermon.notifyViewHandler(new YxxfDrwViewHandlerEvent(
                                          YxxfDrwViewHandlerEvent.TOOLBAR_PAN_R));
            return true;
        }
        else

        if (evt.target == btn_pan_u)
        {
            vhandlermon.notifyViewHandler(new YxxfDrwViewHandlerEvent(
                                          YxxfDrwViewHandlerEvent.TOOLBAR_PAN_U));
            return true;
        }
        else

        if (evt.target == btn_pan_d)
        {
            vhandlermon.notifyViewHandler(new YxxfDrwViewHandlerEvent(
                                          YxxfDrwViewHandlerEvent.TOOLBAR_PAN_D));
            return true;
        }
        else

        if (evt.target == btn_rotate_x_p)
        {
            vhandlermon.notifyViewHandler(new YxxfDrwViewHandlerEvent(
                                          YxxfDrwViewHandlerEvent.TOOLBAR_ROTATE_X_P));
            return true;
        }
        else

        if (evt.target == btn_rotate_x_m)
        {
            vhandlermon.notifyViewHandler(new YxxfDrwViewHandlerEvent(
                                          YxxfDrwViewHandlerEvent.TOOLBAR_ROTATE_X_M));
            return true;
        }
        else

        if (evt.target == btn_rotate_y_p)
        {
            vhandlermon.notifyViewHandler(new YxxfDrwViewHandlerEvent(
                                          YxxfDrwViewHandlerEvent.TOOLBAR_ROTATE_Y_P));
            return true;
        }
        else

        if (evt.target == btn_rotate_y_m)
        {
            vhandlermon.notifyViewHandler(new YxxfDrwViewHandlerEvent(
                                          YxxfDrwViewHandlerEvent.TOOLBAR_ROTATE_Y_M));
            return true;
        }
        else

        if (evt.target == btn_rotate_z_p)
        {
            vhandlermon.notifyViewHandler(new YxxfDrwViewHandlerEvent(
                                          YxxfDrwViewHandlerEvent.TOOLBAR_ROTATE_Z_P));
            return true;
        }
        else

        if (evt.target == btn_rotate_z_m)
        {
            vhandlermon.notifyViewHandler(new YxxfDrwViewHandlerEvent(
                                          YxxfDrwViewHandlerEvent.TOOLBAR_ROTATE_Z_M));
            return true;
        }

        return super.action(evt, arg);
    }

    /**
     * Issue a Command button press event.
     */
    public
    void forceStart()
    {
        action(new Event(btn_start, Event.ACTION_EVENT, null), null);
    }

    /**
     * Issue a Redraw button press event.
     */
    public
    void forceRedraw()
    {
        action(new Event(btn_redraw, Event.ACTION_EVENT, null), null);
    }
    //==========================================================================




    //==========================================================================
    /**
     * Draws the toolbar.
     */
    public
    void paint(Graphics jgc)
    {
        super.paint(jgc);

        if (cmd_curr == CMD_I_TEXT)
        {
            // Draw status_txt positioned after cmd button
            if (btn_cmd_bounds == null)
                btn_cmd_bounds = btn_cmd.bounds();

            if (status_txt_fontmetrics == null)
            {
                status_txt_fontmetrics = jgc.getFontMetrics();
                status_txt_height = status_txt_fontmetrics.getHeight();
            }

            jgc.setColor(Color.black);
            jgc.drawString(status_txt,
                           btn_cmd_bounds.x +
                           btn_cmd_bounds.width + 6,
                           btn_cmd_bounds.y +
                           (btn_cmd_bounds.height / 2) +
                           (status_txt_height / 2) - 3);
        }
    }
    //==========================================================================


    //==========================================================================
    // Printing
    //==========================================================================
    /**
     * Hook for print().
     * @param jpc The Java Graphics object for printing.
     */
    public void print(Graphics jpc)
    {
        System.out.println("++++++YxxfDrwToolbarView:print,jpc=" + jpc);
        super.print(jpc);
    }

    /**
     * Hook for printAll().
     * @param jpc The Java Graphics object for printing.
     */
    public void printAll(Graphics jpc)
    {
        System.out.println("++++++YxxfDrwToolbarView:printAll,jpc=" + jpc);
        super.printAll(jpc);
    }
    //==========================================================================
}
