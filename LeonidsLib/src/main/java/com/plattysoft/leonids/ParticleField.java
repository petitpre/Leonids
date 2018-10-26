package com.plattysoft.leonids;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class ParticleField extends View {

    public interface ParticleClickListener {
        boolean onTouch(Particle particle, MotionEvent event);
    }


    private ArrayList<Particle> mParticles;
    private ParticleClickListener mParticleClickListener;

    public ParticleField(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ParticleField(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ParticleField(Context context) {
        super(context);
    }

    public void setParticles(ArrayList<Particle> particles) {
        mParticles = particles;
    }

    public void setParticleClickListener(ParticleClickListener particleClickListener) {
        this.mParticleClickListener = particleClickListener;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mParticleClickListener != null  && event.getAction() == MotionEvent.ACTION_DOWN) {
            Rect rect = new Rect();
            synchronized (mParticles) {
                for (Particle particle : mParticles) {
                    rect = particle.getDimension(rect);
                    if (rect.contains((int) event.getX(), (int) event.getY())) {
                        return mParticleClickListener.onTouch(particle, event);
                    }
                }
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // Draw all the particles
        synchronized (mParticles) {
            for (Particle particle : mParticles) {
                particle.draw(canvas);
            }
        }
    }
}
