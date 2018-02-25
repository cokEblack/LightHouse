
	package de.cau.infprogoo.lighthouse;

	import java.awt.Canvas;
	import java.awt.Color;
	import java.awt.Graphics;
	import java.awt.Rectangle;
	import java.awt.image.BufferStrategy;

	public class Game extends Canvas implements Runnable {
	public boolean running =false;
	public Thread thread;
	private Handler handler;
	static int WIDTH = 640;
	static int HEIGHT = WIDTH / 12 * 9;
	Window window;
	
		public static void main(String args[]) {
			new Game();
		}
	
	
	
	
		public Game() {
			window = new Window(WIDTH, HEIGHT, this);
			
			handler = new Handler();
			this.addKeyListener(new KeyInput(handler));
			
			handler.addObject(new Paddel(320-16,HEIGHT-50,ID.Paddel));
			handler.addObject(new Ball(320-12, HEIGHT/2+6,ID.Ball,handler));
			for(int i = 0; i<2;i++) {
				for(int j = 0; j<10;j++ ) {
					handler.addObject(new Bricks(16+(j*35),4+(11*i),ID.Bricks,handler));
				}
			}
			
		}

		

		private void tick() {
			handler.tick();
			
		}

		private void render() {
			BufferStrategy bs = this.getBufferStrategy();
			if(bs==null) {
				this.createBufferStrategy(3);
				return;
			}
			Graphics g = bs.getDrawGraphics();
			
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, window.getWidth(), window.getHeight());
			
			
			handler.render(g);
			
			g.dispose();
			bs.show();
			
		}

		

		

		public synchronized void start() {
			thread = new Thread(this);
			thread.start();
			running = true;
		}
		
		public synchronized void stop() {
			try {
				thread.join();
				running = false;
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		@Override
		public void run() {
			long lastTime = System.nanoTime();
			double amountOfTicks = 60.0;
			double ns = 1000000000 / amountOfTicks;
			double delta = 0;
			long timer = System.currentTimeMillis();
			int frames = 0;
					while(running)
					{
						long now = System.nanoTime();
						delta += (now-lastTime) /ns;
						lastTime = now;
						while(delta >=1)
						{
							tick();
							delta--;
						}
						if(running) {
							render();
						}
						frames++;
						
					if(System.currentTimeMillis() - timer > 1000)
					{
						timer += 1000;
						System.out.println("FPS: "+frames);
						frames = 0;
					}
			}
					stop();
		}
			
			
		public static double clamp (double x,int min, int max) {
			if(x >= max)
				return x = max;
			else if(x<=min)
				return x = min;
			else
				return x;
		}
			
			
			
			
		}

