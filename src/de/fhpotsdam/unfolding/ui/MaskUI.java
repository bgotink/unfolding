package de.fhpotsdam.unfolding.ui;

import javax.media.opengl.GL;

import processing.core.PApplet;
import processing.opengl.PGraphicsOpenGL;
import codeanticode.glgraphics.GLGraphicsOffScreen;
import codeanticode.glgraphics.GLTexture;
import codeanticode.glgraphics.GLTextureFilter;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.mapdisplay.AbstractMapDisplay;

public class MaskUI {
	
	private PApplet p;
	
	public GLGraphicsOffScreen mask;
	private GLTextureFilter maskFilter;
	private GLTexture maskedTex;
	private UnfoldingMap map;

	public MaskUI(PApplet p, UnfoldingMap map) {
		this.p = p;
		this.map = map;
		
		mask = new GLGraphicsOffScreen(p, (int) map.mapDisplay.getWidth(), (int) map.mapDisplay.getHeight(), true);
		mask.smooth();
		maskedTex = new GLTexture(p, (int) map.mapDisplay.getWidth(), (int) map.mapDisplay.getHeight());
		maskFilter = new GLTextureFilter(p, "Mask.xml");
	}

	
	public void draw() {
		/*
		maskFilter.apply(new GLTexture[] { map.mapDisplay.getOuterPG(), mask.getTexture() }, maskedTex);

		outerPG.image(maskedTex, 0, 0);
		*/
		
		/*
		maskTex = c.getTexture();
		gl.glBlendFunc(gl.GL_DST_COLOR, gl.GL_ZERO);
		p.image(maskTex, 0, 0, p.width, p.height);
		gl.glBlendFunc(gl.GL_SRC_ALPHA, gl.GL_ONE_MINUS_SRC_ALPHA);
		*/
	}
	
	public GLGraphicsOffScreen getMask() {
		return mask;
	}

}
