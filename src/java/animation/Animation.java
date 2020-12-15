
package java.animation;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

public class Animation {

	private int speed;
	private int frames;
	private int index = 60;
	private int count = -1;

	private BufferedImage currentImg;

	private ArrayList<BufferedImage> images = new ArrayList<>();

	public Animation(int speed, BufferedImage... images) {
		this.speed = speed;
		this.frames = images.length - 1;
		this.images.addAll(Arrays.asList(images));
	}

	public void runAnimation() {
		index++;
		if (index > speed) {
			index = 0;
			nextFrame();
		}
	}

	public void nextFrame() {

		if (count < frames) {
			count++;
		} else {
			count = 0;
		}
		currentImg = images.get(count);
	}

	public void drawAnimation(Graphics g, double x, double y, int offset) {
		g.drawImage(currentImg, (int) x - offset, (int) y, null);
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCount() {
		return count;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

}