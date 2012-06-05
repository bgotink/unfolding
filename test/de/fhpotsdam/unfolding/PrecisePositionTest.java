package de.fhpotsdam.unfolding;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.utils.GeoUtils;

/**
 * "What Every Computer Scientist Should Know About Floating-Point Arithmetic"
 * 
 * see http://docs.oracle.com/cd/E19957-01/806-3568/ncg_goldberg.html
 */
public class PrecisePositionTest {

	// fixtures
	Location location1;
	Location location2;
	Location location3;
	double l1lat = 1.283000f;
	double l1lon = 103.833000f;
	
	double l2lat = 1.283001f;
	double l2lon = 103.833001f;

	
	@Before
	public void setUp() {
		location1 = new Location(1.283000f, 103.833000f);
		location2 = new Location(1.283001f, 103.833001f);
		location3 = new Location(1.283018f, 103.833015f);
	}
	
	@Test
	public void testGetDistanceWithLocationForCloseLocations() {
		assertTrue(GeoUtils.getDistance(location2, location3) > 0.f);
		assertTrue(GeoUtils.getDistance(location1, location3) > 0.f);
	}
	
	@Test
	public void testGetDistanceWithLocationForVeryCloseLocations() {
		assertTrue(GeoUtils.getDistance(location1, location2) > 0.f);
	}
	
	@Test
	public void testGetDistanceWithDoubleForVeryCloseLocations() {
		// this should be about 13 cm
		double dist = GeoUtils.getDistance(l1lat, l1lon, l2lat, l2lon);
		System.out.println(dist);
		assertTrue(dist > 0.f);
	}
	
	@Test
	public void testGetDistance2ForVeryCloseLocations() {
		// this should be about 13 cm
		float dist = GeoUtils.getDistance2((float)l1lat, (float)l1lon, (float)l2lat, (float)l2lon);
		System.out.println(dist*1000 + " cm");
		assertTrue(dist > 0.f);
	}
}