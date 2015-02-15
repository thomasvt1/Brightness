package me.thomasvt.brightness;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

public class WatchActivity extends Activity {

    SeekBar mSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        this.mSeekBar = (SeekBar) stub.findViewById(R.id.seekBar);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override public void onLayoutInflated(WatchViewStub stub) {
                mSeekBar = (SeekBar) stub.findViewById(R.id.seekBar);
                int curBrightnessValue = android.provider.Settings.System.getInt(getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS,-1);
                mSeekBar.setProgress(curBrightnessValue);
            }
        });
    }

    public void saveBrightness(View v) {
        Log.i("Button clicked", "It's real!");
        WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        mSeekBar = (SeekBar) stub.findViewById(R.id.seekBar);
        setBrightness(mSeekBar.getProgress());
    }

    void setBrightness(int brightness) {
        Log.i("Changing brightness: ", "Woohoo: " + brightness);
        android.provider.Settings.System.putInt(getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS, brightness);
    }
}