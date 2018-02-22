package rgbcircles.ru.myfirstcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends Activity {

    TextView textView;
    private StringBuilder a = new StringBuilder("0");
    private BigDecimal one = null, two = null, result = null;
    private boolean mult = false, min = false, plus = false, div = false, ones = false;
    private NumberFormat numberFormat = new DecimalFormat("#.###############");
    private Pattern p = Pattern.compile("-?\\d{15,}\\.");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
    }

    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.button0:
                setA("0");
                setOneOrTwo();
                textView.setText(a);
                break;

            case R.id.button1:
                setA("1");
                setOneOrTwo();
                textView.setText(a);
                break;

            case R.id.button2:
                setA("2");
                setOneOrTwo();
                textView.setText(a);
                break;

            case R.id.button3:
                setA("3");
                setOneOrTwo();
                textView.setText(a);
                break;

            case R.id.button4:
                setA("4");
                setOneOrTwo();
                textView.setText(a);
                break;

            case R.id.button5:
                setA("5");
                setOneOrTwo();
                textView.setText(a);
                break;

            case R.id.button6:
                setA("6");
                setOneOrTwo();
                textView.setText(a);
                break;

            case R.id.button7:
                setA("7");
                setOneOrTwo();
                textView.setText(a);
                break;

            case R.id.button8:
                setA("8");
                setOneOrTwo();
                textView.setText(a);
                break;

            case R.id.button9:
                setA("9");
                setOneOrTwo();
                textView.setText(a);
                break;

            case R.id.button_mult:
                setFlagOnes();
                min = false;
                mult = true;
                div = false;
                plus = false;
                a.replace(0, a.length(), "0");
                break;

            case R.id.button_div:
                setFlagOnes();
                min = false;
                mult = false;
                div = true;
                plus = false;
                a.replace(0, a.length(), "0");
                break;

            case R.id.button_min:
                setFlagOnes();
                min = true;
                mult = false;
                div = false;
                plus = false;
                a.replace(0, a.length(), "0");
                break;

            case R.id.button_plus:
                setFlagOnes();
                min = false;
                mult = false;
                div = false;
                plus = true;
                a.replace(0, a.length(), "0");
                break;

            case R.id.buttonC:
                one = null;
                two = null;
                result = null;
                ones = false;
                min = false;
                mult = false;
                div = false;
                plus = false;
                a.replace(0, a.length(), "0");
                textView.setText(a);
                break;

            case R.id.button_sqrt:
                if (result == null && one == null) {
                    break;
                } else {
                    if (result == null) {
                        result = BigDecimal.valueOf(Math.sqrt(one.doubleValue()));
                    } else {
                        result = BigDecimal.valueOf(Math.sqrt(result.doubleValue()));
                    }
                }
                if (!result.toString().contains(".") && result.toString().length() > 18) {
                    Toast.makeText(MainActivity.this, "Превышено максимальное число цифр", Toast.LENGTH_SHORT).show();
                    break;
                } else
                    one = result;
                textView.setText(numberFormat.format(Double.parseDouble(String.valueOf(result))));
                break;

            case R.id.button_negate:
                if (a.toString().equals("0")) {
                    break;
                } else if (a.toString().startsWith("-")) {
                    a = a.deleteCharAt(0);
                    textView.setText(a);
                    setOneOrTwo();
                } else {
                    a.insert(0, "-");
                    textView.setText(a);
                    setOneOrTwo();
                }
                break;

            case R.id.button_delet:
                if (a.length() == 1) {
                    a.replace(0, a.length(), "0");
                    setOneOrTwo();
                    textView.setText(a);
                } else {
                    a = a.deleteCharAt(a.length() - 1);
                    setOneOrTwo();
                    textView.setText(a);
                }
                break;

            case R.id.button_point:

                if (a.length() == 17) {
                    Toast.makeText(MainActivity.this, "Превышено максимальное число цифр", Toast.LENGTH_SHORT).show();
                } else if (a.toString().contains(".")) {
                    break;
                } else if (a.toString().equals("0") || a.toString().equals(String.valueOf(result))) {
                    a.replace(0, a.length(), "0.");
                    ;
                } else {
                    a.append(".");
                }
                textView.setText(a);
                break;

            case R.id.button_equls:

                if (mult == true) {
                    if (two == null) {
                        break;
                    } else if (one == null) {
                        result = result.multiply(two);
                    } else
                        result = one.multiply(two);
                } else if (min == true) {
                    if (two == null) {
                        break;
                    } else if (one == null) {
                        result = result.subtract(two);
                    } else
                        result = one.subtract(two);
                } else if (plus == true) {
                    if (two == null) {
                        break;
                    } else if (one == null) {
                        result = result.add(two);
                    } else
                        result = one.add(two);
                } else if (div == true) {
                    if (two == null) {
                        break;
                    } else if (0 == two.compareTo(new BigDecimal(0))) {
                        Toast.makeText(MainActivity.this, "Деление на ноль невозможно", Toast.LENGTH_SHORT).show();
                        one = null;
                        two = null;
                        result = null;
                        ones = false;
                        min = false;
                        mult = false;
                        div = false;
                        plus = false;
                        a.replace(0, a.length(), "0");
                        ;
                        break;
                    } else if (one == null) {
                        result = result.divide(two, 15, BigDecimal.ROUND_HALF_EVEN);
                    } else
                        result = one.divide(two, 15, BigDecimal.ROUND_HALF_EVEN);
                } else if (result == null) {
                    break;
                }
                one = null;
                ones = false;
                a.replace(0, a.length(), "0");
                Matcher m = p.matcher(result.toString());
                if (!result.toString().contains(".") && result.toString().length() > 18) {
                    Toast.makeText(MainActivity.this, "Превышено максимальное число цифр", Toast.LENGTH_SHORT).show();
                    break;
                } else if (m.find()) {
                    Toast.makeText(MainActivity.this, "Превышено максимальное число цифр", Toast.LENGTH_SHORT).show();
                    break;
                } else {
                    textView.setText(numberFormat.format(Double.parseDouble(String.valueOf(result))));
                    break;
                }
        }
    }


    public BigDecimal getNumber() {
        BigDecimal number;
        if (a.toString().contains(".")) {
            number = BigDecimal.valueOf(Double.parseDouble(a.toString()));
        } else {
            number = BigDecimal.valueOf(Long.parseLong(a.toString()));
        }
        return number;
    }

    public void setA(String b) {
        if (a.length() == 17) {
            Toast.makeText(MainActivity.this, "Превышено максимальное число цифр", Toast.LENGTH_SHORT).show();
        } else if (a.toString().equals("0") || a.toString().equals(String.valueOf(result))) {
            a.replace(0, a.length(), b);
            ;
        } else {
            a.append(b);
        }
    }

    public void setOneOrTwo() {
        if (!ones) {
            one = getNumber();
        } else two = getNumber();
    }

    public void setFlagOnes() {
        if (one == null && two == null) {
            setOneOrTwo();
        } else if (one != null) {
            two = one;
            ones = true;
        } else {
            two = result;
            ones = true;
        }
    }
}

