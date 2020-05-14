package game.map;
import java.util.ArrayList;


public class MapData {
	
	private ArrayList<Map> maps;

	public MapData() {
		maps = new ArrayList<Map>();
		initMapData();
	}
	

	public int numLevels() {
		return maps.size();
	}
	

	public Map mapWithLevel(int level) {
		return maps.get(level);
	}


	private void initMapData() {
		Map map1 = new Map(tankPos1, brickPos1, stonePos1, waterPos1, grassPos1, homePos1, playerPos1);

		maps.add(map1);

	}
	private static final int[][] tankPos1 =
			{
					{0, 0}, {320, 0}, {640, 0}
			};
	private static final int[][] brickPos1 =
			{
					{280, 600, 120, 40}, {280, 640, 40, 40}, {360, 640, 40, 40},//home
					{280, 360, 120, 40}, {60, 320, 40, 180}, {160, 360, 40, 140}, {580, 320, 40, 180},
					{480, 360, 40, 140},{0, 580, 100, 40}, {60, 620, 40, 60},
					{200, 360, 280, 40}, {160, 240, 40, 440}, {480, 240, 40, 440}
			};
	private static final int[][] stonePos1 =
			{
			};
	private static final int[][] waterPos1 =
			{
					{160, 160, 40, 120}, {480, 160, 40, 120}
			};
	private static final int[][] grassPos1 =
			{
					{200, 160, 280, 60}
			};
	private static final int[] homePos1 = {320, 640};
	private static final int[] playerPos1 = {240, 640};
}
