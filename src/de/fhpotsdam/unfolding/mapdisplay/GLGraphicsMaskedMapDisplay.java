package de.fhpotsdam.unfolding.mapdisplay;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import codeanticode.glgraphics.GLGraphicsOffScreen;
import codeanticode.glgraphics.GLTexture;
import codeanticode.glgraphics.GLTextureFilter;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.MarkerManager;
import de.fhpotsdam.unfolding.providers.AbstractMapProvider;

public class GLGraphicsMaskedMapDisplay extends GLGraphicsMapDisplay implements PConstants {

	protected GLTextureFilter maskFilter;
	protected GLGraphicsOffScreen maskOffscreen;

	public GLGraphicsMaskedMapDisplay(PApplet papplet, AbstractMapProvider provider, float offsetX, float offsetY,
			float width, float height) {
		super(papplet, provider, offsetX, offsetY, width, height);
		
		maskOffscreen = new GLGraphicsOffScreen(papplet, (int) width, (int) height);
		maskFilter = new GLTextureFilter(papplet, "Mask.xml");

	}

	@Override
	public void resize(float width, float height) {
		super.resize(width, height);
	}

	public GLGraphicsOffScreen getMask(){
		return maskOffscreen;
	}

	@Override
	protected void postDraw() {
		drawMarker();
		applyMask();
		drawCanvas();
	}
	
	protected void applyMask(){
		maskFilter.apply(new GLTexture[] { offscreenTex, maskOffscreen.getTexture() }, offscreenTex);
	}

}
