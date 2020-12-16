
package spacegame.animation;

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

	public void drawAnimation(Graphics g, double x, double y, int offsetX, int offsetY) {
		g.drawImage(currentImg, (int) x - offsetX, (int) y - offsetY, null);
	}

	public void drawAnimation(Graphics g, double x, double y) {
		g.drawImage(currentImg, (int) x, (int) y, null);
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

	public int getFrames() {
		return this.frames;
	}

	public ArrayList<BufferedImage> getImages() {
		return this.images;
	}

	public void setImages(ArrayList<BufferedImage> images) {
		this.images = images;
	}

}