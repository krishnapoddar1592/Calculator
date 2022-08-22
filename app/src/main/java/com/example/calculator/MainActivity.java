package com.example.calculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    public void calc(View view){
        MathEval math=new MathEval();
        Button pressed=(Button) view;
        TextView textView=findViewById(R.id.textView);
        String typed= pressed.getTag().toString();
        String current_text=textView.getText().toString();
        String [] func={"+","-","%","×","÷","."};

        boolean  can_be_typed;
        if(current_text.equals("")){
            can_be_typed=false;
        }
        else if(Arrays.asList(func).contains(current_text.substring(current_text.length() - 1))){
            can_be_typed=false;
        }
        else{
            can_be_typed=true;
        }
        if(current_text.contains("\n")){
            current_text="";
        }
        if (typed.equals("AC")) {
            textView.setText("");
        }
        else if(typed.equals("cut")){
            if(textView.getText().toString().contains("\n")){
                current_text="";
                textView.setText(current_text);
            }
            else if(textView.getText()!=null){
                try {
                    StringBuffer sb = new StringBuffer(current_text);
                    sb.deleteCharAt(current_text.length() - 1);
                    current_text = String.valueOf(sb);
                    textView.setText(current_text);
                }catch (Exception e){
                    System.out.println("error");
                }

            }
        }
        else if (typed.equals("equal")) {
            try {
                for( int i =current_text.length()-1;i>=0;i--){
                    if(  Arrays.asList(func).contains(current_text.substring(i))){
                        StringBuffer sb = new StringBuffer(current_text);
                        sb.deleteCharAt(i);
                        current_text= String.valueOf(sb);
                    }
                }
                System.out.println(current_text);
                float result = (float) math.evaluate(current_text);
                textView.setText(current_text + "\n = " + result );
            }
            catch(Exception e){
                System.out.println("Error");
            }

        }
        else if(current_text.equals("")) {
            if ((typed.equals("×") || typed.equals("+") || typed.equals("÷")|| typed.equals("%"))) {
                textView.setText(current_text);
            }
            else{
                textView.setText(current_text + typed);
            }
        }
        else {
            if(!Arrays.asList(func).contains(typed)){ textView.setText(current_text + typed);}
            else if (typed.equals(".")){
                if(current_text.contains(".")){
                    int last_index=current_text.lastIndexOf(".");
                    String x="";
                    for(int a =last_index+1;a<=current_text.length()-1;a++){
                        System.out.println(current_text.substring(a));
                        if(Arrays.asList(func).contains(current_text.substring(a,a+1))) {
                            x = ".";
                            break;
                        }
                        else{
                            x="";
                        }

                    }
                    textView.setText(current_text + x);
                }
                else{
                    textView.setText(current_text + typed);
                }
            }
            else if(Arrays.asList(func).contains(typed) && can_be_typed){
                textView.setText(current_text + typed);
            }

            else{
                textView.setText(current_text);
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}