package com.example.harryvu_hwcustomcolor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

/**
 *
 * This class is a version of the surfaceView and it where the view is being drawn and
 * displayed.
 *
 * @author Harry Vu
 * @version Winter 2022
 *
 */

/**
 * External Citation
 *      Date: 2/8/2022
 *      Problem: I did not know how to create custom SurfaceView.
 *
 *      Resource:
 *          Lab 3, BirthdayCake-Master.
 *          link: https://github.com/cs301up/BirthdayCake
 *
 *      Solution: I looked at the start for that code to see how they
 *                set u theirs
 *
 */

public class DrawView extends SurfaceView
{

    //Instance Variables for colors that will be used to create the elements.
    public int frontRimColor;
    public int sunColor;
    public int carColor;
    public int windowColor;
    public int rearRimColor;
    public int headlightColor;

    //Instance variables for all the CustomElements that will be modified.
    public CustomElement frontRim;
    public CustomElement rearRim;
    public CustomElement carBodyOne;
    public CustomElement carBodyTwo;
    public CustomElement window;
    public CustomElement sun;
    public CustomElement headlight;

    //Instance of a list of all the modifiable Elements.
    public CustomElement[] changeElementList;


    //Constructor creates the new surface view
    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);

        //Starting colors for the change able object shapes.
        frontRimColor = 0xFF646464;
        sunColor = 0xFFFFC400;
        carColor = 0xFF900C3F;
        windowColor= 0xFF2e86c1;
        rearRimColor = 0xFF646464;
        headlightColor= 0xFFf0b17a;

        //This Creates the customElement objects
        frontRim = new CustomCircle("Front Rim",frontRimColor,800,550,50);
        rearRim = new CustomCircle("Rear Rim",rearRimColor,1200,550,50);
        carBodyOne = new CustomRect("Car body Bottom",carColor,750,400,1250,500);
        carBodyTwo = new CustomRect("Car body Top",carColor,850,300,1250,400);
        window = new CustomRect("Window",windowColor,860,310,950,390);
        sun = new CustomCircle("Sun",sunColor,1800,100,100);
        headlight = new CustomRect("Headlight",headlightColor,750,410,800,450);
        changeElementList = new CustomElement[]{frontRim, rearRim, window, sun, headlight,carBodyOne, carBodyTwo};

        //setBackGround color to sky blue
        setBackgroundColor(0xFF87CEEB);
    }


    //This Overrides the original onDraw Method.
    @Override
    public void onDraw(Canvas canvas)
    {
        //This draws the ground
        CustomElement ground = new CustomRect("fRim",0xFF00FF00,0,600,8000,1500);

        //This draws all the objects that were initialized in the constructor
        ground.drawMe(canvas);
        frontRim.drawMe(canvas);
        rearRim.drawMe(canvas);
        carBodyOne.drawMe(canvas);
        carBodyTwo.drawMe(canvas);
        window.drawMe(canvas);
        sun.drawMe(canvas);
        headlight.drawMe(canvas);


    }




}
