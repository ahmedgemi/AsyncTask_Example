package com.example.ahmed.asynctask_example;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Button b1;
    Button b2;
    EditText e1 ;
    TextView t1 ;


    Task task ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button2);

        e1 = (EditText) findViewById(R.id.editText);
        t1 = (TextView) findViewById(R.id.textView);



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String data = e1.getText().toString();

                int x = Integer.parseInt(data);


                 task = new Task();

                task.execute(x);

            }
        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                task.cancel(true);
            }
        });
    }




    public class Task extends AsyncTask<Integer,String,Void>{


        int count ;

        @Override
        protected Void doInBackground(Integer... integers) {

            count = integers[0];

            while (count >=0){

                publishProgress( String.valueOf(count));

                count -- ;


                if (isCancelled())
                    return null ;

                try {

                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            return null;
        }


        @Override
        protected void onProgressUpdate(String... x){

            t1.setText(x[0]);

        }


        protected void onPreExecute(){

            Toast.makeText(MainActivity.this,"Timer Stared",Toast.LENGTH_SHORT).show();
        }


        @Override
        protected void onPostExecute(Void v) {

            Toast.makeText(MainActivity.this,"finish",Toast.LENGTH_SHORT).show();
        }

    }

}
