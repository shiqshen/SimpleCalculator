package capstone.simplecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText Scr;
    private float NumberBf=0, NumAf, result=0;
    private String Operation, mod="replace";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Scr = (EditText) findViewById(R.id.txtScreen);
        Scr.setText("");
        int idList[] = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn2, R.id.btn3, R.id.btn4,
                R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9,
                R.id.btnAdd, R.id.btnSubtract, R.id.btnMultiply, R.id.btnDivide,
                R.id.btnClear,R.id.btnEquals, R.id.btnDot, };
        for(int id:idList) {
            View v = (View) findViewById(id);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onButtonClick(v);
                }
            });
        }
    }
    public void mMath(String str) {
        mResult();
        try {
            NumberBf = Float.parseFloat(Scr.getText().toString());
            Operation = str;
        }catch (Exception e) {
            Toast.makeText(getApplicationContext(),(CharSequence) e, Toast.LENGTH_SHORT).show();
            Scr.setText("SYNAX ERROR");
            mod="replace";
        }
    }
    public void mResult() {
        NumAf = 0;
        if(!Scr.getText().toString().trim().isEmpty())
            NumAf = Float.parseFloat(Scr.getText().toString());
        result = NumAf;

        try {
            switch (Operation) {
                case "+":
                    result = NumAf + NumberBf;
                    break;
                case "-":
                    result = NumberBf - NumAf;
                    break;
                case "*":
                    result = NumAf * NumberBf;
                    break;
                case "/":
                    result = NumberBf / NumAf;
                    break;
                default:
                    result = NumAf;
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Scr.setText(String.valueOf(result));
        mod = "replace";
    }
    public void getKeyboard(String str) {
        String ScrTxt = Scr.getText().toString();
        ScrTxt += str;
        if(mod.equals("add"))
            Scr.setText(ScrTxt);
        else
            Scr.setText(str);
        mod = "add";
    }
    public void onButtonClick(View v) {
        switch (v.getId()) {
            case R.id.btnClear:
                Scr.setText("");
                NumberBf = 0;
                Operation = "";
                break;
            case R.id.btnAdd:
                mMath("+");
                break;
            case R.id.btnSubtract:
                mMath("-");
                break;
            case R.id.btnMultiply:
                mMath("*");
                break;
            case R.id.btnDivide:
                mMath("/");
                break;
            case R.id.btnEquals:
                mResult();
                Operation = "";
                NumberBf = 0;
                break;
            default:
                String numb = ((Button) v).getText().toString();
                getKeyboard(numb);
                break;
        }
    }
}