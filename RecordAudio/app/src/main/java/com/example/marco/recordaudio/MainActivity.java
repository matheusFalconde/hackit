package com.example.marco.recordaudio;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MediaRecorder mediaRecorder;
    private String outputFile = "";
    private Button btnGravar,btnParar;
    private TextView tvCaminho;
    private String fileName = "audio";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnParar = (Button)findViewById(R.id.btn_stop);
        btnGravar = (Button)findViewById(R.id.btn_gravar);
        tvCaminho = (TextView)findViewById(R.id.tv_caminho);

       // btnGravar.setEnabled(false);
        btnParar.setEnabled(false);





    }



    public void Gravar(View view)
    {
        try
        {


            File folder = new File(Environment.getExternalStorageDirectory().toString()+"/teste");
            folder.mkdirs();

            if (folder.isDirectory()){

                File[] files = folder.listFiles();

                if (files.length > 0){
                    fileName = "audio" + files.length;
                }
            }

            outputFile = Environment.getExternalStorageDirectory().getAbsolutePath()+"/teste/"+fileName+".wav";


            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_WB);
            mediaRecorder.setOutputFile(outputFile);

            tvCaminho.setText(outputFile);



            mediaRecorder.prepare();
            mediaRecorder.start();



            btnGravar.setEnabled(false);
            btnParar.setEnabled(true);
            Toast.makeText(MainActivity.this, "Iniciando gravação", Toast.LENGTH_LONG).show();
        }
        catch (Exception ex)
        {
            Log.e("Erro ao gravar!", ex.getMessage());
        }

    }


    public void Parar(View view)
    {
        try
        {
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;

            btnParar.setEnabled(false);
            btnGravar.setEnabled(true);
            Toast.makeText(MainActivity.this, "Gravação efetuada com sucesso!", Toast.LENGTH_LONG).show();
        }
        catch (Exception ex)
        {

        }
    }



    public void Escutar(View view)
    {
        try
        {

            MediaPlayer m = new MediaPlayer();

            m.setDataSource(outputFile);
            m.prepare();

            Toast.makeText(MainActivity.this, "Executando Auio Biirl", Toast.LENGTH_LONG).show();


        }
        catch (Exception ex)
        {

        }
    }

    public void Enviar(View view) { 
        SpeechToText service = new SpeechToText();
        service.setUsernameAndPassword("{username}", "{password}");
    }
}
