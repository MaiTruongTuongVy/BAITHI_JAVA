package com.example.mediaplayer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.R.array;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView txttenbai, txtten, txttg, txttongtg;
	SeekBar skSong;
	ImageButton btnpre, btnrew, btnplay, btnstop, btnff, btnnext;
	
	ArrayList<song> arraySong;
	MediaPlayer mediaPlayer;
	int position = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        AnhXa();
        AddSong();
        KhoitaoMediaPlayer();
		btnnext.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				position++;
				if(position > arraySong.size()- 1){
					position = 0;
				}
				if(mediaPlayer.isPlaying()){
					mediaPlayer.stop();
				}
				KhoitaoMediaPlayer();
				mediaPlayer.start();
				btnplay.setImageResource(R.drawable.pause1);
				
				Tongtg();
				tg();
			}
		});
		
		btnpre.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				position--;
				if(position <0){
					position = arraySong.size()-1;
				}
				if(mediaPlayer.isPlaying()){
					mediaPlayer.stop();
				}
				KhoitaoMediaPlayer();
				mediaPlayer.start();
				btnplay.setImageResource(R.drawable.pause1);
				
				Tongtg();
				tg();
			}
		
		});
		
        btnplay.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(mediaPlayer.isPlaying()){
					mediaPlayer.pause();
					btnplay.setImageResource(R.drawable.playbutton);
					
				}else{
					mediaPlayer.start();
					btnplay.setImageResource(R.drawable.pause1);
				}
				Tongtg();
				tg();
			}
		});
        
        btnstop.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				mediaPlayer.stop();
				mediaPlayer.release();
				btnplay.setImageResource(R.drawable.pause1);
				KhoitaoMediaPlayer();
			}
		});
        
        skSong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				mediaPlayer.seekTo(skSong.getProgress());
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				// TODO Auto-generated method stub
				
			}
		});
        
    }
    private void AddSong()
    {
    	arraySong = new ArrayList<song>();
    	
    	arraySong.add(new song("Chỉ còn lại tình yêu", R.raw.chi_con_lai_tinh_yeu));
    	arraySong.add(new song("Gọi mưa", R.raw.goi_mua));
    	arraySong.add(new song("Mùa ta đã yêu", R.raw.mua_ta_da_yeu));
    	arraySong.add(new song("Người ấy",R.raw.nguoi_ay));
    	arraySong.add(new song("Nơi tình yêu bắt đầu", R.raw.noi_tinh_yeu_bat_dau));
    	arraySong.add(new song("Sau chia tay", R.raw.sau_chia_tay));
    	arraySong.add(new song("Tìm được nhau khó thế nào", R.raw.tim_duoc_nhau_kho_the_nao));
    	arraySong.add(new song("Vết mưa", R.raw.vet_mua));
    	arraySong.add(new song("Xin lỗi anh", R.raw.xin_loi_anh));
    	arraySong.add(new song("Yêu xa", R.raw.yeu_xa));
    	
    	
    }
    
    private void AnhXa(){
    	txttg     =(TextView) findViewById(R.id.textView3);
    	txttongtg =(TextView) findViewById(R.id.textView4);
    	txtten    = (TextView) findViewById(R.id.textView2);
    	txttenbai = (TextView) findViewById(R.id.txttenbai);
    	skSong    = (SeekBar) findViewById(R.id.seekBar1);
    	btnpre    = (ImageButton) findViewById(R.id.imgpre);
       	btnplay   = (ImageButton) findViewById(R.id.imgplay);
       	btnstop   =  (ImageButton) findViewById(R.id.imageButton1);
      	btnnext   = (ImageButton) findViewById(R.id.imgnext);
    	
    }
    
    private void KhoitaoMediaPlayer()
    {
    	mediaPlayer = MediaPlayer.create(MainActivity.this, arraySong.get(position).getFile());
		txtten.setText(arraySong.get(position).getTitle());
    }
    private void Tongtg(){
    	SimpleDateFormat dinhDangGio = new SimpleDateFormat("mm:ss");
    	txttongtg.setText(dinhDangGio.format(mediaPlayer.getDuration()));
    	// gan max cua skSong =mediaPlayer.getDuration()
    	skSong.setMax(mediaPlayer.getDuration());
    }
    
    private void tg(){
    	final Handler handler = new Handler();
    	handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				SimpleDateFormat dinhDangGio = new SimpleDateFormat("mm:ss");
				txttg.setText(dinhDangGio.format(mediaPlayer.getCurrentPosition()));
				//update progess seekbar
				skSong.setProgress(mediaPlayer.getCurrentPosition());
				// kiem tra thoi luong bai hat
				mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
					
					@Override
					public void onCompletion(MediaPlayer mp) {
						// TODO Auto-generated method stub
						position++;
						if(position > arraySong.size()- 1){
							position = 0;
						}
						if(mediaPlayer.isPlaying()){
							mediaPlayer.stop();
						}
						KhoitaoMediaPlayer();
						mediaPlayer.start();
						btnplay.setImageResource(R.drawable.pause1);
						
						Tongtg();
						tg();
					}
				});
				handler.postDelayed(this, 500);
			}
		}, 100);
    }
    
}
