package de.fhpotsdam.unfolding.examples.mask;

import processing.core.PApplet;
import codeanticode.glgraphics.GLConstants;
import codeanticode.glgraphics.GLGraphicsOffScreen;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.ui.MaskUI;
import de.fhpotsdam.unfolding.utils.MapUtils;

public class SimpleMaskAppGl extends PApplet {
	
	public static final String JDBC_CONN_STRING_APPLICATION = "jdbc:sqlite:../data/unfolding3.mbtiles";

	UnfoldingMap map;
	MaskUI maskUI;
	GLGraphicsOffScreen mask;

	

	public void setup() {
		size(800, 600, GLConstants.GLGRAPHICS);
		map = new UnfoldingMap(this,  new MBTilesMapProvider(JDBC_CONN_STRING_APPLICATION));
		//map.zoomAndPanTo(new Location(52.5f, 13.4f), 10);
		maskUI = new MaskUI(this,map);

		MapUtils.createDefaultEventDispatcher(this, map);
	}

	public void draw() {
		// update the mask
		updateMask();
		//background(0, 255, 0);
		map.draw();

		maskUI.draw();

	}

	public void updateMask() {
		maskUI.c.beginDraw();
		//mask.c.background(0);
		maskUI.c.noStroke();
		maskUI.c.fill(255);
		maskUI.c.ellipse(mouseX, mouseY, 100, 100);
		maskUI.c.endDraw();

		// put the canvas into the texture
		// mask.setTexture(canvas.getTexture());
	}

	public void keyPressed() {
		if (key == '+')
			map.zoomIn();
		if (key == '-')
			map.zoomOut();
	}

	public static void main(String[] args) {
		PApplet.main(new String[] { "de.fhpotsdam.unfolding.examples.ui.SimpleMaskAppGl" });
	}

}