package de.fhpotsdam.unfolding.mapdisplay;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import codeanticode.glgraphics.GLGraphicsOffScreen;
import codeanticode.glgraphics.GLTexture;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.MarkerManager;
import de.fhpotsdam.unfolding.providers.AbstractMapProvider;

public class GLGraphicsMapDisplay extends ProcessingMapDisplay implements PConstants {

	// Inner map (and inner marker) will be drawn on this.
	protected GLGraphicsOffScreen offscreenPG;
	// Outer marker will be drawn on this
	protected GLGraphicsOffScreen offscreenCutoffPG;
	protected GLTexture offscreenTex;


	protected float opacity = 255;

	public GLGraphicsMapDisplay(PApplet papplet, AbstractMapProvider provider, float offsetX, float offsetY,
			float width, float height) {
		super(papplet, provider, offsetX, offsetY, width, height);

		offscreenPG = new GLGraphicsOffScreen(papplet, (int) width, (int) height);
		offscreenCutoffPG = new GLGraphicsOffScreen(papplet, (int) width, (int) height);
		offscreenTex = new GLTexture(papplet, (int) width, (int) height);

	}

	@Override
	public void resize(float width, float height) {
		super.resize(width, height);
		offscreenPG = new GLGraphicsOffScreen(papplet, (int) width, (int) height);
		offscreenCutoffPG = new GLGraphicsOffScreen(papplet, (int) width, (int) height);
	}

	@Override
	public PGraphics getInnerPG() {
		return offscreenPG;
	}

	@Override
	public PGraphics getOuterPG() {
		return offscreenCutoffPG;
	}

	@Override
	protected void postDraw() {
		drawMarker();
		drawCanvas();
	}
	
	protected void drawMarker(){
		// Draws inner map (with inner marker) and outer marker
		offscreenCutoffPG.beginDraw();
		offscreenCutoffPG.image(offscreenPG.getTexture(), 0, 0);
		for (MarkerManager<Marker> mm : markerManagerList) {
			mm.draw();
		}
		offscreenCutoffPG.endDraw();
		offscreenTex=offscreenCutoffPG.getTexture();
	}
	
	protected void drawCanvas() {
		PGraphics canvasPG = papplet.g;
		canvasPG.pushMatrix();
		canvasPG.translate(offsetX, offsetY);
		canvasPG.applyMatrix(matrix);
		canvasPG.image(offscreenTex, 0, 0);
		canvasPG.popMatrix();
	}

}
