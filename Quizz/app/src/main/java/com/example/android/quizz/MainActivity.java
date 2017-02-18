package com.example.android.quizz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int marks=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    private int question1()
    {
        RadioButton r1=(RadioButton)findViewById(R.id.answer1);
        boolean chec=r1.isChecked();
        if(chec)
            return 1;
        else
            return 0;

    }
    private int question2()
    {
        RadioButton r1=(RadioButton)findViewById(R.id.answer2);
        boolean chec=r1.isChecked();
        if(chec)
            return 1;
        else
            return 0;

    }
    private int question3()
    {
        RadioButton r1=(RadioButton)findViewById(R.id.answer3);
        boolean chec=r1.isChecked();
        if(chec)
            return 1;
        else
            return 0;

    }
    private int question4()
    {
        RadioButton r1=(RadioButton)findViewById(R.id.answer4);
        boolean chec=r1.isChecked();
        if(chec)
            return 1;
        else
            return 0;

    }

    /** Question 5 is 2 mark question
     * 1 mark for each correct choice
     */
    private int question5()
    {
        CheckBox c1=(CheckBox)findViewById(R.id.check1);
        CheckBox c2=(CheckBox)findViewById(R.id.check2);
        int m=0;
        if(c1.isChecked())
            m=m+1;
        if(c2.isChecked())
            m=m+1;
        return m;

    }
    public void Marks(View view)
    {
        marks = 0;
        EditText name=(EditText) findViewById(R.id.name_field);
        String names=name.getText().toString();
        marks=marks+question1();
        marks=marks+question2();
        marks=marks+question3();
        marks=marks+question4();
        marks=marks+question5();
        Toast.makeText(this,names + "'s IQ is " + marks +" "+"out of 6",Toast.LENGTH_SHORT).show();
    }
}
