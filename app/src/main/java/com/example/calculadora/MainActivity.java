package com.example.calculadora;
import org.mariuszgromada.math.mxparser.*;

import static com.example.calculadora.R.*;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

import com.example.calculadora.R.string;

public class MainActivity extends AppCompatActivity {

    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        display= findViewById(id.displayTXT);
        display.setShowSoftInputOnFocus(false);

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getString(R.string.Display).equals(display.getText().toString())){
                    display.setText("");
                }
            }
        });
    }

    private void updateText(String strToAdd){
        String oldstr = display.getText().toString();
        int cursorpos=display.getSelectionStart();
        String leftstr = oldstr.substring(0,cursorpos);
        String rightstr = oldstr.substring(cursorpos);
        if(getString(R.string.Display).equals(display.getText().toString())){
            display.setText(strToAdd);
            display.setSelection(cursorpos + 1);
        }
        else{
            display.setText(String.format("%s%s%s",leftstr,strToAdd,rightstr));
            display.setSelection(cursorpos + 1);
        }
    }

    public void zerobtn(View view){
        updateText("0");
    }
    public void onebtn(View view){
        updateText("1");
    }
    public void twobtn(View view){
        updateText("2");
    }
    public void threebtn(View view){
        updateText("3");
    }
    public void fourbtn(View view){
        updateText("4");
    }
    public void fivebtn(View view){
        updateText("5");
    }
    public void sixbtn(View view){
        updateText("6");
    }
    public void sevenbtn(View view){
        updateText("7");
    }
    public void eightbtn(View view){
        updateText("8");
    }
    public void ninebtn(View view){
        updateText("9");
    }
    public void multbtn(View view){
        updateText("*");
    }
    public void divbtn(View view){
        updateText("/");
    }
    public void somabtn(View view){
        updateText("+");
    }
    public void subbtn(View view){
        updateText("-");
    }
    public void maismenosbtn(View view){
        updateText("0");
    }
    public void clearbtn(View view){
        display.setText("");
    }
    public void expoentebtn(View view){
        updateText("^");
    }
    public void igualbtn(View view){
        String userexp=display.getText().toString();
        Expression exp = new Expression(userexp);
        String reslt = String.valueOf(exp.calculate());
        display.setText(reslt);
        display.setSelection(reslt.length());
    }
    public void pantesesbtn(View view){
        int cursorPos = display.getSelectionStart();
        int openPar=0;
        int closePar=0;
        int txtLen=display.getText().length();

        for (int i=0;i<cursorPos;i++){
            if(display.getText().toString().substring(i, i+1).equals("(")){
                openPar +=1;
            }
            if(display.getText().toString().substring(i, i+1).equals(")")){
                closePar +=1;
            }
        }
        if (openPar == closePar || display.getText().toString().substring(txtLen-1,txtLen).equals("(")){
            updateText("(");
        }
        else if (closePar > openPar && !display.getText().toString().substring(txtLen-1,txtLen).equals("(")){
            updateText(")");
        }
        display.setSelection(cursorPos+1);
    }
    public void pontobtn(View view){
        updateText(".");

    }
    public void backspacebtn(View view){
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();
        if (cursorPos!=0 && textLen!=0){
            SpannableStringBuilder selection= (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos-1,cursorPos,"");
            display.setText(selection);
            display.setSelection(cursorPos-1);
        }
    }

}