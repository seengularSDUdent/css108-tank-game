package application;

public class BulletTask implements Runnable{
	MyPlayer player;
	Bullet bullet = new Bullet(null, null, null);
	Map map;
	public BulletTask(MyPlayer player, Map map) {
		this.player = player;
		this.map = map;
	}
	public synchronized void run() {
		bullet.doFire(player.getPosition(), player.getDirection(), map);
	}
}
