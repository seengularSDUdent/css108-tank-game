package application;

import java.util.ArrayList;

public class BotGeneratorTask implements Runnable{
	
	private int timeInterval = 20000;
	private int counter = 0;
	private int limit = 20;
	private ArrayList<Position> basePositions = new ArrayList<>();
	private ArrayList<BotPlayer> botPlayers = new ArrayList<>();

	public BotGeneratorTask(Map map){
		for(int i = 0; i < map.getY(); i++) {
			for(int j = 0; j < map.getX(); j++) {
				if(map.getValueAt(j, i) == 'B') {
					basePositions.add(new Position(j, i));
				}
			}
		}
	}
	
	public void run() {
		generateBots();
	}
	
	private synchronized void generateBots() {
		while(true) {
			try {
				Thread.sleep(timeInterval);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			counter++;
			
			if(counter == limit) {
				timeInterval = timeInterval - 200;
				limit = limit + 10;
				counter = 0;
			}
			
			System.out.println("Bot spawned!");
			System.out.println("Base Positions are - " + basePositions);
		}
	}
}
