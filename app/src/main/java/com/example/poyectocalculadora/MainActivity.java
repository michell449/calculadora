package com.example.poyectocalculadora;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private static final char SUMAR ='+';
    private static final char RESTAR ='-';
    private static final char MULTIPLICAR ='*';
    private static final char DIVIDIR ='/';

    private char simboloActual;
    private double primerValor = Double.NaN;
    private double segundoValor;
    private TextView inputDisplay, outputDisplay;
    private DecimalFormat decimalFormat;
    private MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9,
            buttonPunto, buttonSumar, buttonRestar, buttonMultiplicar, buttonDividir, buttonBorrar, buttonResultado;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        decimalFormat = new DecimalFormat("0.######");
        inputDisplay = findViewById(R.id.textViewNumeros);
        outputDisplay = findViewById(R.id.textViewCalculo);
        button0 = findViewById(R.id.buttonNumero0);
        button1 = findViewById(R.id.buttonNumero1);
        button2 = findViewById(R.id.buttonNumero2);
        button3 = findViewById(R.id.buttonNumero3);
        button4 = findViewById(R.id.buttonNumero4);
        button5 = findViewById(R.id.buttonNumero5);
        button6 = findViewById(R.id.buttonNumero6);
        button7 = findViewById(R.id.buttonNumero7);
        button8 = findViewById(R.id.buttonNumero8);
        button9 = findViewById(R.id.buttonNumero9);

        buttonPunto = findViewById(R.id.buttonPunto);
        buttonSumar = findViewById(R.id.buttonSumar);
        buttonRestar = findViewById(R.id.buttonRestar);
        buttonMultiplicar = findViewById(R.id.buttonMultiplicar);
        buttonDividir = findViewById(R.id.buttonDividir);
        buttonBorrar = findViewById(R.id.buttonBorrar);
        buttonResultado = findViewById(R.id.buttonResultado);

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputDisplay.setText(inputDisplay.getText() + "0");
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputDisplay.setText(inputDisplay.getText() + "1");
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputDisplay.setText(inputDisplay.getText() + "2");
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputDisplay.setText(inputDisplay.getText() + "3");
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputDisplay.setText(inputDisplay.getText() + "4");
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputDisplay.setText(inputDisplay.getText() + "5");
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputDisplay.setText(inputDisplay.getText() + "6");
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputDisplay.setText(inputDisplay.getText() + "7");
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputDisplay.setText(inputDisplay.getText() + "8");
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputDisplay.setText(inputDisplay.getText() + "9");
            }
        });
        buttonPunto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputDisplay.setText(inputDisplay.getText() + ".");
            }
        });
        buttonSumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operaciones();
                simboloActual = SUMAR;
                outputDisplay.setText(decimalFormat.format(primerValor) + "+");
                inputDisplay.setText(null);
            }
        });
        buttonRestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operaciones();
                simboloActual = RESTAR;
                outputDisplay.setText(decimalFormat.format(primerValor) + "-");
                inputDisplay.setText(null);
            }
        });
        buttonMultiplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operaciones();
                simboloActual = MULTIPLICAR;
                outputDisplay.setText(decimalFormat.format(primerValor) + "*");
                inputDisplay.setText(null);
            }
        });
        buttonDividir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operaciones();
                simboloActual = DIVIDIR;
                outputDisplay.setText(decimalFormat.format(primerValor) + "/");
                inputDisplay.setText(null);
            }
        });
        buttonBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (inputDisplay.getText().length() > 0){
                   CharSequence textoActual = inputDisplay.getText();
                   inputDisplay.setText(textoActual.subSequence(0, textoActual.length() - 1));
               }
               else{
                   primerValor = Double.NaN;
                   segundoValor = Double.NaN;
                   inputDisplay.setText("");
                   outputDisplay.setText("");
               }
            }
        });
        buttonResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operaciones();
                outputDisplay.setText(decimalFormat.format(primerValor));
                primerValor = Double.NaN;
                simboloActual = '0';
            }
        });
    }
    private void operaciones(){
        if (!Double.isNaN(primerValor)) {
            segundoValor = Double.parseDouble(inputDisplay.getText().toString());
            inputDisplay.setText(null);

            if (simboloActual == SUMAR)
                primerValor = this.primerValor + this.segundoValor;
            else if (simboloActual == RESTAR)
                primerValor = this.primerValor - this.segundoValor;
            else if (simboloActual == MULTIPLICAR)
                primerValor = this.primerValor * this.segundoValor;
            else if (simboloActual == DIVIDIR)
                primerValor = this.primerValor / this.segundoValor;
        } else{
            try {
                primerValor = Double.parseDouble(inputDisplay.getText().toString());
            } catch (Exception e){

            }
        }
    }
}