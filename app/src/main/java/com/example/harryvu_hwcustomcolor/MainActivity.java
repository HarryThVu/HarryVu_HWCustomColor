package com.example.harryvu_hwcustomcolor;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 *
 * This class is the MainActivity of the act and is where the everything is
 * brought together to compile the app.
 *
 * @author Harry Vu
 * @version Winter 2022
 *
 */
/**
 * External Citation 1
 *      Date: 2/8/2022
 *      Problem: I needed help on how to create a onTouch listener.
 *
 *      Resource:
 *          Youtube video tutorial
 *          link: https://www.youtube.com/watch?v=Rd89cVKrQBg
 *
 *      Solution: I modeled my after their example code.
 *
 */

/**
 * External Citation 2
 *      Date: 2/8/2022
 *      Problem: I did not know how to multiple seekbars in main activity.
 *
 *      Resource:
 *          Youtube video tutorial
 *          link: https://www.youtube.com/watch?v=AM6lJnze9nA
 *
 *      Solution: I saw how the set up their and make mine similar to theirs.
 *
 */

/**
 * External Citation 3
 *      Date: 2/8/2022
 *      Problem: I was struggling to get the .getColor() method to work.
 *
 *      Resource:
 *          Classmate: Anne-Marie
 *          Code that helped: Color.blue(selectedElement.getColor())
 *
 *      Solution: Just used that so that I can get the RGB values for element.
 *
 */

/**
 * External Citation 4
 *      Date: 2/8/2022
 *      Problem: Need to find how to set progress bar.
 *
 *      Resource:
 *          Stack overFlow
 *          Link: https://stackoverflow.com/questions/9792888/android-seekbar-set-progress-value
 *
 *      Solution: Found the .setProgress() and used it.
 *
 */

public class MainActivity extends AppCompatActivity {

    //Instance variables for the (x,y) of on touch, the current element and its' colors, and the DrawView surface.
    private int x;
    private int y;

    private CustomElement selectedElement = null;
    private int elementBlueVal;
    private int elementGreenVal;
    private int elementRedVal;

    private DrawView mainView;


    /**
     * This is basically the main method that contains all the listener. It is where the
     * DrawView will be modified based on the user input.
     *
     * @param savedInstanceState   Reqired for this to class to extend AppCompatActivity.
     *
     * @return void                Doesn't return anything.
     *
     */


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //This initializes the DrawView and sets up the seekbars
        //and textViews
        mainView = findViewById(R.id.drawView);
        SeekBar rSeekBar = findViewById(R.id.RedSeek);
        SeekBar gSeekBar = findViewById(R.id.GreenSeek);
        SeekBar bSeekBar = findViewById(R.id.BlueSeek);
        TextView redVal = findViewById(R.id.RedValue);
        TextView greenVal = findViewById(R.id.GreenValue);
        TextView blueVal = findViewById(R.id.BlueValue);

        //These create the seekbar listener and connects it with the text view
        //for each button. It also changes the save color values and
        //calls the colorSetter().
        /** External Citation 1*/
        rSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                redVal.setText(""+ i);
                elementRedVal = i;
                colorSetter();
                mainView.invalidate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        gSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                greenVal.setText(""+ i);
                elementGreenVal = i;
                colorSetter();
                mainView.invalidate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        bSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                blueVal.setText(""+ i);
                elementBlueVal = i;
                colorSetter();
                mainView.invalidate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Sets up the TextView and also creates the onTouchlistener.
        //It will save the (x,y) cords of the location that the user touches.
        TextView selectedElementName = findViewById(R.id.ObjectName);
        /** External Citation 2*/
        mainView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                x = (int)event.getX();
                y = (int)event.getY();
                //Uses the findSelected method to see if the (x,y) cord is
                //inside a CustomElement hit box.
                if(findSelected(x,y))
                {
                    findSelected(x,y);
                    String name = selectedElement.myName;
                    //it will get the Custom element, save it, and then use
                    //the data from the Custom element to modify the view.
                    selectedElementName.setText(name);
                    colorValueExtract();
                    /** External Citation 4*/
                    rSeekBar.setProgress(elementRedVal);
                    gSeekBar.setProgress(elementGreenVal);
                    bSeekBar.setProgress(elementBlueVal);
                    mainView.invalidate();
                }

                return true;
            }
        });


    }

    /**
     *
     * This method will take in an x,y values to loop through the changeElementlist
     * and check to see if that x,y falls into a Custom element hit box.
     *
     * @param xvalue takes in the x value of where the user touches
     * @param yvalue takes in the y value of where the user touches
     *
     * @return true if the x,y cords do land on a Custom element hit box.
     *
     */

    public boolean findSelected(int xvalue, int yvalue)
    {
        //set x and y value to the x,y of the class.
        xvalue = this.x;
        yvalue = this.y;

        //loops through the changeElementList array and see if x,y lands on any Elements.
        for(int i = 0; i < mainView.changeElementList.length;i++)
        {
            CustomElement ele = mainView.changeElementList[i];
            if(ele.containsPoint(xvalue,yvalue))
            {
                selectedElement = mainView.changeElementList[i];
                return true;
            }
        }
        return false;
    }

    //Sets the element colors to the RGB value of the selected element.
    /** External Citation 3*/
    public void colorValueExtract() {

        elementRedVal = Color.red(selectedElement.getColor());
        elementGreenVal = Color.green(selectedElement.getColor());
        elementBlueVal = Color.blue(selectedElement.getColor());

    }


    //
    public void colorSetter()
    {

        for(int i = 0; i < mainView.changeElementList.length;i++)
        {
            int hexInt = Color.argb(255,elementRedVal,elementGreenVal,elementBlueVal);
            if(selectedElement.equals(mainView.changeElementList[i]))
            {
                mainView.changeElementList[i].setColor(hexInt);
            }
        }
        mainView.invalidate();
    }



}