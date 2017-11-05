package com.mygdx.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import com.mygdx.actorStuff.Actor;
import com.mygdx.actorStuff.Devil;
import com.mygdx.actorStuff.Projectile;
import com.mygdx.actorStuff.Ram;

public class MascotServer{
	protected static ArrayList<ServerThread> threads = new ArrayList<ServerThread>();
	protected static ArrayList<String> inputs = new ArrayList<String>();
	public static ArrayList<Actor> players = new ArrayList<Actor>();
	public static LinkedList<Projectile> projectiles = new LinkedList<Projectile>();
	protected static Actor devil = new Devil(2000,2000);
	public static long curtime = System.currentTimeMillis();
	public static long deltatime = 0;
	public static long cake = 1;
	//protected static String data = "";
	
	public static void main(String[] args) throws IOException, InterruptedException{
        ServerSocket listeningSocket = new ServerSocket(6789);
        
        System.out.println("Server Started!");
        Socket connectionSocket = null;
        
        //listen, connect, and create a new Server thread
        while (threads.size() < 1) {
            try {
                connectionSocket = listeningSocket.accept(); 
            } catch (IOException e) {
                System.out.println("I/O error");
            }
            ServerThread st = new ServerThread(connectionSocket);
            threads.add(st);
            inputs.add("");
            players.add(new Ram(0,0));
            st.start();
        }
        while (threads.size() > 0) {
	        for (int i = 0; i < threads.size(); i++) {
	        		ServerThread thread = threads.get(i);
	        		thread.isDone = false;
	        		inputs.set(i, thread.data);
	        }	        
	        
	        for (int i = 0; i < inputs.size(); i++) {
	        	p(inputs.get(i),players.get(i));
	        }
	        
	        deltatime = System.currentTimeMillis() - curtime;
	        curtime = System.currentTimeMillis();
        
	        aiLogic();
	        
	        Iterator<Projectile> it = projectiles.iterator();
	        while (it.hasNext()) {
	        	Projectile p = it.next();
	        	if (p.lifetime > 0) {
	        		p.lifetime -= deltatime;
	        		p.posx += p.vx;
	        		p.posy += p.vy;
	        	} else {
	        		it.remove();
	        	}
	        }
	        
	        String tmp = process();
	        
        	for (int i = 0; i < threads.size(); i++) {
        		ServerThread thread = threads.get(i);
        		
        		thread.temp = tmp;
        		thread.isDone = true;
        	}
        	Thread.sleep(5);
        }
        
    }
	
	private static void p(String input, Actor a) {
		
		String[] splitted = input.split("\\s+");
		
		int x = 0;
		int y = 0;
		int ability = 0;
		
		
		for (String s : splitted) {
			switch (s) {
				case "W":
					y += 1;
					break;
				case "A":
					x -= 1;
					break;
				case "S":
					y -= 1;
					break;
				case "D":
					x += 1;
					break;
				case "1":
					ability = 0;
					break;
				case "2":
					ability = 1;
					break;
				case "3":
					ability = 2;
					break;
				case "4":
					ability = 3;
					break;
				default:
					ability = -1;
					break;
			}
		}
		
		if (ability != -1) {
			a.useAbility(ability, null);
			a.standard();
		} else {
			if (x != 0 || y != 0) {
				a.move((float) Math.atan2((double)y, (double)x));
			} else {
				a.standard();
			}
		}

	}
	
	public static boolean checkIfPlayerInBounds(float x, float y) {
		if (x >= 80f && x <= 4167f && y >= 80f && y <= 2700f) {
			return true;
		}
		return false;
	}
	
	private static void aiLogic() {
		float angle = devil.getAngle();
		float playerx = players.get(0).getPosx();
		float playery = players.get(0).getPosy();
		playerx = devil.getPosx() - playerx;
		playery = devil.getPosy() - playery;
		float tmpa = (float) Math.atan2((double)-playery, (double)-playerx);
		devil.move(tmpa);
		
		devil.useAbility((int) Math.round(Math.random()), null);
	}
	
	private static String process() {
		String ret = "";
		
		for (Actor a : players) {
			ret += "p ";
			if (checkIfPlayerInBounds(a.getPosx(), a.getPosy())) {
				ret += a.getPosx() + " ";
				ret += a.getPosy() + " ";
				ret += a.getAngle() + " ";
			}
			else {
				ret += getClosestX(a.getPosx()) + " ";
				ret += getClosestY(a.getPosy()) + " ";
				ret += a.getAngle() + " ";
			}
			
			//System.out.println("XY:" + "(" + a.getPosx() + ", "	+ a.getPosy() + ")");
		}
		
		ret += "e ";
		ret += devil.getPosx() + " ";
		ret += devil.getPosy() + " ";
		ret += devil.getAngle() + " ";
		
		for (Projectile p : projectiles) {
			ret += "s ";
			ret += p.posx + " ";
			ret += p.posy + " ";
		}

		return ret;
	}
	
	public static float getClosestX(float currentX) {
		if (currentX - 4167f > 0) {
			return 4167f;
		}
		else if (currentX <= 0) {
			return 0;
		}
		return currentX;
	}
	
	public static float getClosestY(float currentY) {
		if (currentY - 2700f > 0) {
			return 2700f;
		}
		else if (currentY <= 0) {
			return 0;
		}
		return currentY;
	}

}
