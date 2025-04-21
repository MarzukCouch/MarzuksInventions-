package DungeonExplorer;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.net.URL;
import java.util.ArrayList;
import java.awt.AlphaComposite;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JLabel;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;

public class playmenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	private static JLabel backgroundmenu;
	private static float opacity = 0.25f;
	private static Timer fadeTimer;
	private static Clip entryM;
	private static Clip mageM;
	static Timer prog = new Timer(30, null);
	static JProgressBar playerhp = new JProgressBar();
	static JLabel Player = new JLabel("");
	static JLabel levelbarrier1 = new JLabel("");
	static JLabel obj1 = new JLabel("");
	static JLabel obj2 = new JLabel("");
	static JLabel obj3 = new JLabel("");
	static JLabel obj4 = new JLabel("");
	static JLabel obj5 = new JLabel("");
	static JLabel enemy1 = new JLabel("");
	static JLabel enemy2 = new JLabel("");
	static JLabel enemy3 = new JLabel("");
	static JLabel su = new JLabel();
	static JLabel sl = new JLabel();
	static JLabel sd = new JLabel();
	static JLabel sr = new JLabel();
	static JLabel pressJ = new JLabel("New label");
	static JLabel slot0 = new JLabel("");
	static JLabel slot1 = new JLabel("");
	static JLabel slot2 = new JLabel("");
	static JLabel slot3 = new JLabel("");
	static JLabel slot4 = new JLabel("");
	static JLabel slot5 = new JLabel("");
	static JLabel hpdrop = new JLabel();
	static JLabel chest1 = new JLabel("");
	static int playerY = 335;
	static int playerX = 445;
	static int shift = 9;
	static int spX = 577;
	static int spY = 316;
	static int slashshiftX = 0;
	static int slashshiftY = 0;
	static int newplayerX = playerX;
	static int newplayerY = playerY;
	static int killcount = 0;
	static int floorcount = 1;
	static Timer mobtimer = new Timer(50, null);
	static Timer mobtimer2 = new Timer(50, null);
	static Timer timerattacks = new Timer(1400, null);
	static Timer attackmove = new Timer(10, null);
	static Timer attackstop = new Timer(260, null);
	static Timer magicattack = new Timer(2000, null);
	static JLabel[] mobs = {enemy1, enemy2};
	static JProgressBar hp[] = new JProgressBar[mobs.length];
	static JProgressBar hp2 = new JProgressBar();
	static JLabel[] collide = { obj1, obj2, obj3, obj4, obj5, levelbarrier1, chest1};
	static JLabel[] walls = { obj1, obj2, obj3, obj4, obj5 };
	static JLabel[] slots = { slot0, slot1, slot2, slot3, slot4, slot5 };
	static JLabel special = new JLabel("");
	static JLabel shield = new JLabel();
	static JLabel keys = new JLabel();
	static JLabel msg = new JLabel();
	static JLabel lives = new JLabel();
	static boolean cooldown = false;
	static boolean cooldown2 = false;
	static boolean block = false;
	static boolean slashl = false;
	static boolean magealive = true;
	static boolean slashu = false;
	static boolean slashr = false;
	static boolean slashd = false;
	static boolean hitonce2 = false;
	static boolean attacktype = true;
	static boolean collision = false;
	static boolean playable = true;
	static boolean slotfilled1 = false;
	static boolean slotfilled2 = false;
	static boolean slotfilled3 = false;
	static boolean slotfilled4 = false;
	static boolean slotfilled5 = false;
	static boolean slotfilled6 = false;
	static boolean enemycollision = false;
	static boolean shieldcd = false;
	static boolean alreadyexists1 = false;
	static boolean whichfade = false;
	static boolean[] enemycollision2 = new boolean[mobs.length];
	static boolean[] mobattack = new boolean[mobs.length];
	static boolean moveable = true;
	static boolean gameend = false;
	static boolean gameover = false;
	static Timer[] mobTimers = new Timer[mobs.length];
	static int mx;
	static int life = 3;
	static int my;
	static int moveu = 0;
	static int moved = 0;
	static int mover = 0;
	static int movel = 0;
	static boolean magemoving = false;
	static int Smoveu = 0;
	static int Smoved = 0;
	static int Smover = 0;
	static int Smovel = 0;
	static int trackmagehits = 0;
	static Timer knockbackTimer; // Timer for smooth knockback
	static Timer yAdjustTimer;
	static int knockbackSteps = 10;
	static int knockbackStep = 0;  
	static int knockbackDX2 = 0;    
	static int knockbackDY2 = 0;  
	static ArrayList<KeyListener> keylisteners = new ArrayList<>();
	static ArrayList<Timer> storetimer = new ArrayList<>();
	static int currentX;
	static boolean endable = false;
	static int currentY;
	static playmenu frame = new playmenu();


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public playmenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 817);
		setLocation(0, 0);
		// panel so we can use graphics
		contentPane = new JPanel() {
			// allows us to alter opacity, meaning fade effects are possible
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;
				// set the opacity of the graphics context
				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
			}
		};

		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(6, 6, 6, 6));
		contentPane.setFocusable(true);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// skeleton timers, set to 50ms so it can constantly update
		mobtimer = new Timer(50, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < mobs.length; i++) {
					enemyai(mobs[i], i);
				}
			}
		});
		
		// store the mage timer here, it plays enemy ai every second
		mobtimer2 = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enemyai2();
			}
		});

		loadscreen();
		backgroundmenu.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/startmenu.png")));


	}

	// loading screen
	public static void loadscreen() {

		contentPane.setBackground(Color.BLACK);
		backgroundmenu = new JLabel("");
		backgroundmenu.setBounds(0, 40, 1280, 817);
		contentPane.add(backgroundmenu);

		JButton startb = new JButton("");
		startb.setBounds(231, 233, 366, 400);
		contentPane.add(startb);

		// if game has not ended, means start menu should be up
		if(!gameover && !gameend) {
			backgroundmenu.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/startmenu.png")));
		}
		// if game has ended, disable start, end menu should be up
		if(gameend) {
			contentPane.remove(startb);
			backgroundmenu.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/endmenu.png")));
		}
		// if player loses, game over shows up, disable start
		if(gameover) {
			contentPane.remove(startb);
			backgroundmenu.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/gameover.png")));
		}

		JButton quitb = new JButton("");
		quitb.setBounds(672, 233, 375, 400);
		contentPane.add(quitb);

		// label displaying lives left, starts at 3 lives, 2 hearts means 3 lives, 1 heart means 2, 0 means 1 life, dying after would render the game over
		lives.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/lives.png")));

		startb.setEnabled(false); // Disable start button until fade-in is done

		quitb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0); // Quit the application
			}
		});

		// fade-in effect on load
		fadeTimer = new Timer(30, e -> {
			if (opacity != 1f) {
				opacity += 0.01f; // Increase opacity
				if (opacity > 1f) {
					opacity = 1f; // Cap opacity at 1.0
					startb.setEnabled(true); // Enable start button
					fadeTimer.stop(); // Stop the fade timer
				}
			}
			contentPane.repaint(); // Repaint the panel with new opacity
		});
		fadeTimer.start(); // Start the fade-in

		// play menu music
		try {
			// get the url from dimages, convert it to input stream, and play the clip
			URL musicURL = playmenu.class.getResource("/Dimages/dungeonmusic.wav");
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicURL);
			entryM = AudioSystem.getClip();
			entryM.open(audioInput);
			entryM.start();
		} catch (Exception e1) {
			System.out.println(e1);
		}

		startb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// fade to black when play is clicked
				fadeout();
			}
		});

		startb.setContentAreaFilled(false);  
		quitb.setContentAreaFilled(false);  


		// timer to check if player hits the red barrier
		prog = new Timer(10, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (newplayerX + 112 > levelbarrier1.getX()
						&& newplayerX < levelbarrier1.getX() + levelbarrier1.getX()
						&& newplayerY + 143 > levelbarrier1.getY()
						&& newplayerY < levelbarrier1.getY() + levelbarrier1.getHeight()) {
					nextfloor();
				}
			}
		});
	}

	// timer for fading out, and transitioning from life-life, or menu-life, or life-menu
	private static void fadeout() {

		Timer fadeouttimer = new Timer(30, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (opacity > 0f) {

					opacity -= 0.05f; // decrease opacity gradually

					opacity = Math.max(opacity, 0f); // this makes opacity stay in within it's max range [0.0f, 1.0f]

					contentPane.repaint(); // repaint with new opacity

				} else {
					// when fade out is complete, stop the timer
					// use getsource timer.stop to target the active timer, more useful for later (knockback)
					((Timer) e.getSource()).stop();

					// now clear the screen and perform the transition
					contentPane.removeAll();
					contentPane.repaint();
					entryM.close(); // stop the menu music

					// call the appropriate method based on whichfade, whichfade determines whether you go back to the menu, or the game
					if (whichfade == false) {
						game(); // call the game method to start the game
						fadeIn(); // fade back into the game
					} else {
						loadscreen(); // call loadscreen to get to the menu
						fadeIn(); // fade back into the loadscreen
					}        
				}
			}
		});
		fadeouttimer.start();
	}

	private static void fadeIn() {
		Timer fadeInTimer = new Timer(30, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (opacity < 1f) {
					opacity += 0.05f; // increase opacity gradually

					opacity = Math.min(opacity, 1f); // this makes opacity stay in within it's max range [0.0f, 1.0f]
					contentPane.repaint();
				} else {
					((Timer)e.getSource()).stop(); // stop fade when its fully visible
				}
			}
		});

		// refresh, set player coords and allow movement
		contentPane.repaint();
		contentPane.requestFocus();

		playerY = 335;
		playerX = 445;
		moveable = true;

		Player.setBounds(playerX, playerY, Player.getWidth(), Player.getHeight());

		newplayerX = playerX;
		newplayerY = playerY;

		fadeInTimer.start(); // start the fade effect

		// initialize and set hp bars for skeletons
		for (int i = 0; i < mobs.length; i++) {

			hp[i] = new JProgressBar();
			hp[i].setEnabled(false);
			hp[i].setBackground(new Color(255, 255, 255));
			hp[i].setForeground(new Color(255, 0, 0));
			contentPane.add(hp[i]);
			hp[i].setValue(99);
		}

		// boss's hp bar
		hp2 = new JProgressBar();
		hp2.setEnabled(false);
		hp2.setBackground(new Color(255, 255, 255));
		hp2.setForeground(new Color(255, 0, 0));
		contentPane.add(hp2);
		hp2.setValue(100);
		hp2.setBounds(10000,10000, 0 , 0);
		hp2.setVisible(false);

	}

	public static void game() {

		// refresh enemy's hp bar, make sure its invisible at first
		hp2.setValue(100);
		hp2.setVisible(false);
		contentPane.repaint();

		// set enemy bounds far so they do not make any moves
		enemy1.setBounds(10000, 10000, 0 , 0);
		enemy2.setBounds(10000, 10000, 0 , 0);
		enemy3.setBounds(10000, 10000, 0 , 0);

		contentPane.add(shield);
		contentPane.setBackground(Color.black);
		shield.setVisible(false);

		// enemy default posiitons
		enemy1.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/skeletondown1.png")));
		contentPane.add(enemy1);

		enemy2.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/skeletondown1.png")));
		contentPane.add(enemy2);

		enemy3.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/mageidle2.png")));
		contentPane.add(enemy3);

		// create the walls
		for (int i = 0; i < walls.length; i++) {
			walls[i].setBackground(new Color(41, 41, 41));
			walls[i].setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		}

		// player default position
		Player.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/idleup2.png")));
		Player.setBounds(445, 358, 110, 120);
		contentPane.add(Player);

		// player hp
		playerhp.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		playerhp.setForeground(new Color(255, 0, 0));
		playerhp.setBounds(26, 615, 703, 56);
		contentPane.add(playerhp);
		playerhp.setValue(100);

		obj1.setBounds(710, 30, 29, 580);
		contentPane.add(obj1);
		obj1.setOpaque(true);

		// controls
		keys.setBounds(760, 350, 900, 300);
		contentPane.add(keys);
		keys.setVisible(true);
		keys.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/controls.png")));

		// ingame msg
		msg.setBounds(760, 10, 900, 300);
		contentPane.add(msg);
		msg.setVisible(true);
		msg.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/msg1.png")));

		lives.setBounds(900, 230, 400, 200);
		contentPane.add(lives);
		lives.setVisible(true);

		obj2.setBounds(10, 30, 26, 575);
		contentPane.add(obj2);
		obj2.setOpaque(true);

		obj3.setBounds(10, 588, 729, 22);
		contentPane.add(obj3);
		obj3.setOpaque(true);

		obj4.setBounds(10, 30, 236, 22);
		contentPane.add(obj4);
		obj4.setOpaque(true);

		obj5.setOpaque(true);
		obj5.setBounds(486, 30, 253, 22);
		contentPane.add(obj5);

		levelbarrier1.setOpaque(true);
		levelbarrier1.setBounds(245, 30, 242, 22);
		levelbarrier1.setBackground(Color.RED);
		contentPane.add(levelbarrier1);

		contentPane.add(special);
		special.setVisible(false);

		// hitboxes
		su.setBounds(292, 298, 183, 86);
		contentPane.add(su);
		sl.setBounds(197, 392, 103, 156);
		contentPane.add(sl);
		sd.setBounds(292, 557, 183, 93);
		contentPane.add(sd);
		sr.setBounds(469, 392, 103, 156);
		contentPane.add(sr);

		// inventory slots
		slot0.setBounds(236-140, 685, 81, 56);
		contentPane.add(slot0);

		slot1.setBounds(328-140, 685, 81, 56);
		contentPane.add(slot1);

		slot2.setBounds(420-140, 685, 81, 56);
		contentPane.add(slot2);

		slot3.setBounds(511-140, 685, 81, 56);
		contentPane.add(slot3);

		slot4.setBounds(602-140, 685, 81, 56);
		contentPane.add(slot4);

		slot5.setBounds(693-140, 685, 81, 56);
		contentPane.add(slot5);

		// make sure floor count is 1 incase player goes through a 2nd or 3rd life
		floorcount = 1;

		chest1.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/chestclosed2.png")));
		chest1.setBounds(327, 220, 100, 107);
		contentPane.add(chest1);
		chest1.setVisible(true);

		// slots customization
		for (int i = 0; i < slots.length; i++) {
			slots[i].setBorder(new LineBorder(new Color(0, 0, 0), 2));
			slots[i].setOpaque(true);
			slots[i].setIcon(null);
		}

		// reset killcount
		killcount = 0;


		// initialize timers for each mob (skeletons only)
		// this timer allows them to attack seperatley, instead of one attack for all of them
		for (int i = 0; i < mobs.length; i++) {

			final int mobIndex = i; // capture the mob index

			mobattack[mobIndex] = true; // allow mobs to attack initially

			mobTimers[mobIndex] = new Timer(1400, new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					mobattack[mobIndex] = true; // enable attack for this mob

					mobTimers[mobIndex].stop(); // stop the timer after execution
				}
			});
			mobTimers[mobIndex].setRepeats(false); // ensure timers only execute once
		}



		// start the mob timers, if they arent already running
		if(!mobtimer.isRunning()) {
			mobtimer.start();
		}
		if(!mobtimer2.isRunning()) {
			mobtimer2.start();
		}

		// key listeners
		KeyListener listener = new KeyAdapter() {
			public void keyPressed(KeyEvent e) {

				// set collision to false each key click to make sure player isn't stuck
				collision = false;

				// update
				newplayerX = playerX;
				newplayerY = playerY;

				// call the method for player movement
				playermove(e);

				//end game function
				if ((e.getKeyChar() == KeyEvent.VK_ENTER) && endable) {
					// so that it can only be pressed once
					if(gameend == false) {
						// gameend so that we are greeted to the endmenu, and witch fade so it goes to menu not game
						gameend = true;
						whichfade = true;
						// call fadeout to menu
						fadeout();
					}
				}

				// block function
				if (e.getKeyChar() == 'l'|| e.getKeyChar() == 'L') {

					// if cd is over
					if(shieldcd == false) {

						// set block to true to nullify damage and knockback
						block = true;
						shieldcd = true;
						shield.setBounds(playerX - 70, playerY - 30, 200, 200);
						shield.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/blockcircle.png")));
						shield.setVisible(true);

						// constantly update shield bounds, to move with the player
						Timer shieldstart = new Timer(10, new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								shield.setBounds(playerX - 60, playerY - 30, 200, 200);
							}
						});
						shieldstart.start();

						//after a second, end shield
						Timer shieldend = new Timer(1000, new ActionListener() {
							public void actionPerformed(ActionEvent e) {

								shieldstart.stop();
								shield.setVisible(false);
								block = false;
							}
						});
						// only occurs once
						shieldend.setRepeats(false);
						shieldend.start();

						// cd ends after 2s
						Timer shieldcooldown = new Timer(2000, new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								shieldcd = false;
							}
						});
						shieldcooldown.setRepeats(false);
						shieldcooldown.start();

					}
				}

				// listener for attacks
				if (e.getKeyChar() == 'j' && cooldown == false || e.getKeyChar() == 'J' && cooldown == false) {

					// set attack type to true to track which attack is being registered
					attacktype = true;

					// set hitbox bounds
					sr.setBounds(Player.getX() + 115, Player.getY() - 30, 133, 156);
					sl.setBounds(Player.getX() - 115, Player.getY() - 30, 103, 156);
					su.setBounds(Player.getX() - 20, Player.getY() - 110, 183, 86);
					sd.setBounds(Player.getX() - 20, Player.getY() + 135, 183, 86);

					// change player image depending on which direction slash faces
					if(slashr == true) {
						Player.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/slashright1.png")));
					}
					if(slashl == true) {
						Player.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/slashleft1.png")));
					}
					if(slashd == true) {
						Player.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/slashdown1.png")));
					}
					if(slashu == true) {
						Player.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/slashup1.png")));
					}

					// cooldown start
					cooldown = true;

					// start the slash cooldown, and check if the slash hit
					slash();
					hitcheck(mobs);
					// make it so that mage cannot be hit during movement
					if(!magemoving) {
						hitcheck2();
					}
					chestopen();

					// play the slash sound
					try {
						URL musicURL = playmenu.class.getResource("/Dimages/sword slash.wav");
						AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicURL);
						Clip clip = AudioSystem.getClip();
						clip.open(audioInput);
						if (playable == true) {
							clip.start();
						}

					} catch (Exception e1) {
						System.out.println(e1);
					}
				}

				// K for special attack, magic is disabled on floor 5 to make the bossfight less difficult, and to avoid bounds conflicts
				if ((e.getKeyChar() == 'K' && cooldown2 == false || e.getKeyChar() == 'k' && cooldown2 == false) && floorcount != 5) {

					// attack type false for special
					attacktype = false;


					// update bounds method for special
					updateslashBounds(special);

					// variable that will move the spell
					spX = special.getX();
					spY = special.getY();

					// hitboxes are longer
					sr.setBounds(Player.getX() + 145, Player.getY() - 30, 700, 156);
					sl.setBounds(Player.getX() - 700, Player.getY() - 30, 700, 156);
					su.setBounds(Player.getX() - 20, Player.getY() - 700, 183, 700);
					sd.setBounds(Player.getX() - 20, Player.getY() + 40, 80, 700);

					special.setVisible(true);

					// direction of spell
					if (slashl == true) {
						slashshiftX = -40;
					}

					if (slashr == true) {
						slashshiftX = 40;
					}

					if (slashu == true) {
						slashshiftY = -40;
					}

					if (slashd == true) {
						slashshiftY = 40;
					}

					// play the spells sound
					try {

						URL musicURL = playmenu.class.getResource("/Dimages/specialslashsound.wav");
						AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicURL);
						Clip clip = AudioSystem.getClip();
						clip.open(audioInput);

						// start the clip if playable
						if (playable == true) {
							clip.start();
						}

					} catch (Exception e1) {
						System.out.println(e1);
					}

					// constantly update the spell's bounds
					Timer timer = new Timer(10, new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							cooldown2 = true;

							spX += slashshiftX;
							spY += slashshiftY;

							special.setBounds(spX, spY, 142, 143);

							// check for hits
							if (hitonce2 == false) {
								hitcheck(mobs);
								hitcheck2();
							}
						}
					});

					// 260ms later, the spell will end
					Timer timer2 = new Timer(260, new ActionListener() {
						public void actionPerformed(ActionEvent e) {

							timer.stop();

							special.setVisible(false);

							slashshiftX = 0;
							slashshiftY = 0;

							// reset bounds when spell is over
							special.setBounds(sr.getX(), sr.getY(), 142, 143);
						}
					});

					// 1.75 second cooldown
					Timer timer3 = new Timer(1750, new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							cooldown2 = false;
							hitonce2 = false;
						}
					});

					// start timers if no cooldown
					if (cooldown2 == false) {
						// start the timers if no cooldown
						timer.start();
						timer2.setRepeats(false);
						timer2.start();
						timer3.setRepeats(false);
						timer3.start();
					}
				}


				// only item drop is potions, so if a slot is taken, add 20 to hp and get rid of the potion
				if (e.getKeyChar() == '1' && slot0.getIcon() != null) {
					playerhp.setValue(playerhp.getValue() + 20);
					slot0.setIcon(null);
					slotfilled1 = false;
				}

				if (e.getKeyChar() == '2' && slot1.getIcon() != null) {
					playerhp.setValue(playerhp.getValue() + 20);
					slot1.setIcon(null);
					slotfilled2 = false;
				}

				if (e.getKeyChar() == '3' && slot2.getIcon() != null) {
					playerhp.setValue(playerhp.getValue() + 20);
					slot2.setIcon(null);
					slotfilled3 = false;
				}

				if (e.getKeyChar() == '4' && slot3.getIcon() != null) {
					playerhp.setValue(playerhp.getValue() + 20);
					slot3.setIcon(null);
					slotfilled4 = false;
				}

				if (e.getKeyChar() == '5' && slot4.getIcon() != null) {
					playerhp.setValue(playerhp.getValue() + 20);
					slot4.setIcon(null);
					slotfilled5 = false;
				}

				if (e.getKeyChar() == '6' && slot5.getIcon() != null) {
					playerhp.setValue(playerhp.getValue() + 20);
					slot5.setIcon(null);
					slotfilled6 = false;
				}
			}

		};

		// use a list to track how many listeners are functioning, so that only one runs at a time
		contentPane.addKeyListener(listener);

		keylisteners.add(listener);

		// to refrain from duplicating key listeners when user tries again
		if (keylisteners.size() > 1) {

			// remove the new listener
			contentPane.removeKeyListener(keylisteners.get(keylisteners.size() - 1));

		}

	}

	// int mob is the mob that was hit, passed on from i
	public static void updatemobHP(JLabel[] mobs2, int mob, int enemyLeft, int enemyTop) {

		// update bounds on hp so it keeps up with enemy movements
		hp[mob].setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		hp[mob].setBounds(mobs2[mob].getX(), mobs2[mob].getY() - 40, 150, 20);
		hp[mob].setVisible(true);

		int attackdmg = 0;

		// ult attacks deal 45, normal attacks deal 18
		if (attacktype == true) {
			attackdmg = 18;
		}

		if (attacktype == false) {
			hitonce2 = true;
			attackdmg = 35;
		}

		int newHP = hp[mob].getValue() - attackdmg;

		// update hp on each hit
		hp[mob].setValue(newHP);

		// if hp reaches zero, get rid of the mob and the hp bar
		if (newHP <= 0) {
			mobs2[mob].setBounds(-10000, -10000, 0, 0);
			hp[mob].setVisible(false);

			// update the kill count, so prog can start and or msg can update
			killcount = killcount + 1;

			// generate a random number to determine whether potion drops
			int dropchance = (int) (Math.random() * 10) + 1;

			// if number is greater than 6 drop
			if (dropchance > 6) {

				// create a new one each time
				JLabel hpdrop = new JLabel();
				hpdrop.setBounds(enemyLeft + 10, enemyTop + 15, 50, 40); // position it relative to the enemy
				hpdrop.setVisible(true);
				hpdrop.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/healpotion3.png")));
				contentPane.add(hpdrop);

				Timer timer2 = new Timer(10, new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// if player intersects the potion, pick it up

						if (hpdrop.getBounds().intersects(Player.getBounds())) {
							hpdrop.setBounds(0, 0, 0, 0);

							contentPane.remove(hpdrop);

							// pickup the potion
							potiondrop();
						}
					}
				});

				// constantly check for player intersection with the potion, then add it to inv
				timer2.start();
			}

			// otherwise do nothing
			else {
			}

			// stop the mob's timer
			mobTimers[mob].stop();
			mobattack[mob] = true;


			// when floors are clear, start prog so player can progress
			// update message based on kills and floor count (kill skeletons (1/2))
			if (killcount == 1 && floorcount == 2) {
				prog.start();
				msg.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/msg3.png")));

			}
			if(killcount == 1 && floorcount == 3) {
				msg.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/msg5.png")));
			}
			if(killcount == 2 && floorcount == 3) {
				msg.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/msg6.png")));
				prog.start();
			}
			if(killcount == 1 && floorcount == 5) {
				msg.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/msg9.png")));
				prog.start();
			}
		}
	}

	public static void potiondrop() {
		// store the potion in the closest empty slot, use booleans to check later if slots are filled

		if (slot0.getIcon() == null) {
			slot0.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/healpotion3.png")));
			slotfilled1 = true;
		}

		else if (slot1.getIcon() == null) {
			slot1.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/healpotion3.png")));
			slotfilled2 = true;
		}

		else if (slot2.getIcon() == null) {
			slot2.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/healpotion3.png")));
			slotfilled3 = true;
		}

		else if (slot3.getIcon() == null) {
			slot3.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/healpotion3.png")));
			slotfilled4 = true;
		}

		else if (slot4.getIcon() == null) {
			slot4.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/healpotion3.png")));
			slotfilled5 = true;
		}

		else if (slot5.getIcon() == null) {
			slot5.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/healpotion3.png")));
			slotfilled6 = true;
		}
	}

	public static void updateslashBounds(JLabel slash2) {

		// takes care of spell's starting position, and all character visuals
		// direction of the magic depends on direction character is facing

		if (slashl == true) {
			slash2.setBounds(playerX - 170, playerY, 142, 143);
			special.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/spleft.png")));
			Player.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/magicleft.png")));

		}

		if (slashr == true) {
			slash2.setBounds(playerX + 40, playerY, 142, 143);
			special.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/spright.png")));
			Player.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/magicright.png")));
		}

		if (slashu == true) {
			slash2.setBounds(playerX, playerY - 95, 142, 143);
			special.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/spup.png")));
			Player.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/magicup.png")));
		}

		if (slashd == true) {
			slash2.setBounds(playerX, playerY + 135, 142, 143);
			special.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/spdown.png")));
			Player.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/magicdown1.png")));
		}

		// restore character position after spell is cast
		Timer timer = new Timer(175, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(slashr) {
					Player.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/idleright.png")));
				}
				if(slashl) {
					Player.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/idleleft.png")));
				}
				if(slashd) {
					Player.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/idledown.png")));
				}
				if(slashu) {
					Player.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/idleup2.png")));
				}
			}
		});

		// occurs once
		timer.setRepeats(false);
		timer.start();
	}

	// method to progress to the next floor
	public static void nextfloor() {

		// stop timer when player progresses ( this timer checked if player touched the level barrier)
		prog.stop();

		// set hp for skeletons
		for (int i = 0; i < mobs.length; i++) {
			hp[i].setValue(99);
			mobattack[i] = true; // reset attack state for each mob
		}

		// reset cooldowns
		cooldown = false;
		cooldown2 = false;
		playable = true;

		// after floor 1 chest dissapears
		chest1.setVisible(false);
		chest1.setBounds(10000,10000, 0 , 0);

		// up the floor count and reset the kill count
		killcount = 0;

		floorcount = floorcount + 1;

		// if on floor 2, fight one skeleton and update the message
		if (floorcount == 2) {

			msg.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/msg2.png")));
			enemy1.setBounds(541, 150, 105, 121);

		}

		// if on floor 3, add two skeletons and update the message
		if (floorcount == 3) {

			msg.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/msg4.png")));
			enemy1.setBounds(541, 150, 105, 121);
			enemy2.setBounds(127, 150, 105, 121);

		}

		// floor 4 is a chest room, update the message
		if (floorcount == 4) {

			chest1.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/chestclosed2.png")));
			msg.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/msg7.png")));
			chest1.setBounds(327, 220, 100, 107);
			contentPane.add(chest1);
			chest1.setVisible(true);
		}

		// floor 5 start the boss, update the message
		if (floorcount == 5) {
			// make sure mage is set as alive, and unmoving
			magemoving = false;
			magealive = true;
			msg.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/msg8.png")));
			enemy3.setBounds(541, 150, 105, 121);
			// set hp2 to visible
			currentX = enemy3.getX();
			hp2.setVisible(true);
			currentY = enemy3.getY();
			contentPane.repaint();
			// always start mage at the same posiiton, so that movemage() is consistent after first run
			if(life != 3 && trackmagehits == 0) {
				enemy3.setBounds(541, 150, 105, 121);
				currentX = 541;
				currentY = 150;
			}

		}

		// refresh
		contentPane.repaint();
		contentPane.requestFocus();

		playerY = 335;
		playerX = 445;

		Player.setBounds(playerX, playerY, Player.getWidth(), Player.getHeight());

		newplayerX = playerX;
		newplayerY = playerY;

	}


	public static void hitcheck(JLabel[] mobs2) {
		for (int i = 0; i < mobs2.length; i++) {
			final int mobIndex = i; // create a final variable for use in the Timer

			int enemyLeft = mobs2[mobIndex].getX();
			int enemyTop = mobs2[mobIndex].getY();
			int mobWidth = mobs2[mobIndex].getWidth();
			int mobHeight = mobs2[mobIndex].getHeight();

			int knockbackDX = 0, knockbackDY = 0;

			int knockbackDistance = 50; // total knockback distance
			int knockbackSteps = 6; // number of knockback intervals

			int knockbackStepSize = knockbackDistance / knockbackSteps; // size of each interval

			// determine knockback direction based on attack, if right hitbox, DX will move to the right
			if (sr.getBounds().intersects(enemyLeft, enemyTop, mobWidth, mobHeight) && slashr) {
				knockbackDX = knockbackStepSize;

				// if left hitbox, DX will move to the left
			} else if (sl.getBounds().intersects(enemyLeft, enemyTop, mobWidth, mobHeight) && slashl) {
				knockbackDX = -knockbackStepSize;

				// if up hitbox, dy will move up
			} else if (su.getBounds().intersects(enemyLeft, enemyTop, mobWidth, mobHeight) && slashu) {
				knockbackDY = -knockbackStepSize;

				// if down hitbox, dy will move down    
			} else if (sd.getBounds().intersects(enemyLeft, enemyTop, mobWidth, mobHeight) && slashd) {
				knockbackDY = knockbackStepSize;
			}

			// if knockback isnt 0
			if (knockbackDX != 0 || knockbackDY != 0) {

				// final int so it works in this scenario
				// without final, inconsistensies may show up as it could change between the inner class and outer class
				final int finalKnockbackDX = knockbackDX;
				final int finalKnockbackDY = knockbackDY;

				// create a new Timer for each mob to avoid conflicts
				Timer knockbackTimer = new Timer(30, new ActionListener() {
					int knockbackStep = 0;

					public void actionPerformed(ActionEvent e) {
						if (knockbackStep < knockbackSteps) {
							// knock enemy back
							int newenemyX = mobs2[mobIndex].getX() + finalKnockbackDX;
							int newenemyY = mobs2[mobIndex].getY() + finalKnockbackDY;

							// check collision and only move if valid
							if (!checkCollision(newenemyX, newenemyY, mobWidth, mobHeight)) {
								mobs2[mobIndex].setBounds(newenemyX, newenemyY, mobWidth, mobHeight);
							}

							// up the steps so that knockback ends when the number of intervals is completed
							knockbackStep++;
						} else {
							// stop the timer once knockback is complete
							// use get source to avoid timer conflicts
							((Timer) e.getSource()).stop();
						}
					}
				});
				knockbackTimer.start();

				// update mob's HP if necessary
				updatemobHP(mobs2, mobIndex, enemyLeft, enemyTop);
			}
		}
	}
	public static void chestopen() {
		// open chest if hit
		if ((su.getBounds().intersects(chest1.getBounds())) || ((sr.getBounds().intersects(chest1.getBounds()))
				|| ((sr.getBounds().intersects(chest1.getBounds())) || ((sl.getBounds().intersects(chest1.getBounds())))))) {
			chest1.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/chestopen.png")));
			potiondrop();
			prog.start();
		}
	}

	public static void slash() {

		// make the slash disappear after 350ms
		Timer timer = new Timer(350, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(slashr) {
					Player.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/idleright.png")));
				}
				if(slashl) {
					Player.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/idleleft.png")));
				}
				if(slashd) {
					Player.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/idledown.png")));
				}
				if(slashu) {
					Player.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/idleup2.png")));
				}
			}
		});

		// end cooldown after slash disappears
		Timer timer2 = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cooldown = false;
			}
		});

		// start the timers and dont let them repeat
		timer.setRepeats(false);
		timer2.setRepeats(false);
		timer.start();
		timer2.start();
	}



	public static void playermove(KeyEvent e) {

		// WASD moves newplayer coords, if the new player coords do not collide with anything, players bounds will be updated and play will move
		if (e.getKeyChar() == 'w' || e.getKeyChar() == 'W') {

			// Move up
			// make attacks face up
			slashu = true;
			slashd = false;
			slashr = false;
			slashl = false;

			// animation system works like frames, starts at frame 1, at frame 4 one leg moves, at frame 7 the next leg moves,
			// and is reset to frame 2 to do it again

			moveu = moveu + 1;

			if(moveu == 1) {
				Player.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/idleup2.png")));
			}
			if(moveu == 4) {
				Player.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/moveup1.png")));
			}
			if(moveu == 7) {
				Player.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/moveup2.png")));

				moveu = 2;
			}

			// the new variable will be checked for collision and become the actual bounds -
			// only if it doesn't collide

			newplayerY -= shift;

		} else if (e.getKeyChar() == 's' || e.getKeyChar() == 'S') {

			// Move down
			// make attacks face down

			slashd = true;
			slashu = false;
			slashr = false;
			slashl = false;

			moved = moved + 1;

			if(moved == 1) {
				Player.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/idledown.png")));
			}
			if(moved == 4) {
				Player.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/movedown1.png")));
			}
			if(moved == 7) {
				Player.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/movedown2.png")));

				moved = 2;
			}


			newplayerY += shift;

		} else if (e.getKeyChar() == 'd' || e.getKeyChar() == 'D') {

			// Move right
			// make player and attacks face right

			slashr = true;
			slashd = false;
			slashu = false;
			slashl = false;

			mover = mover + 1;

			if(mover == 1) {
				Player.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/idleright.png")));
			}
			if(mover == 4) {
				Player.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/moveright1.png")));
			}
			if(mover == 7) {
				Player.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/moveright2.png")));

				mover = 2;
			}

			newplayerX += shift;

		} else if (e.getKeyChar() == 'a' || e.getKeyChar() == 'A') {

			// Move left
			// make player and attacks face left

			slashl = true;
			slashd = false;
			slashr = false;
			slashu = false;

			movel = movel + 1;

			if(movel == 1) {
				Player.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/idleleft.png")));
			}
			if(movel == 4) {
				Player.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/moveleft1.png")));
			}
			if(movel == 7) {
				Player.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/moveleft2.png")));

				movel = 2;
			}

			newplayerX -= shift;

		}

		// if player is allowed to move
		if(moveable) {
			if(!checkCollision(newplayerX, newplayerY, Player.getWidth(), Player.getHeight())) {
				// if no collision update the player's position
				playerX = newplayerX;
				playerY = newplayerY;

				Player.setBounds(playerX, playerY, Player.getWidth(), Player.getHeight());
			}
		}
	}


	// skeleton ai
	public static void enemyai(JLabel mobs1, int i) {

		// reset collision
		enemycollision = false;

		int enemyX = mobs1.getX();
		int enemyY = mobs1.getY();

		int detectionRangeX = 325;
		int detectionRangeY = 200;

		int newenemyX = mobs1.getX();
		int newenemyY = mobs1.getY();
		int width = mobs1.getWidth();
		int height = mobs1.getHeight();

		// if the player is within a vicinity of the detection range , the enemy will move towards the player

		if (playerX > enemyX - detectionRangeX && playerX < enemyX + detectionRangeX
				&& playerY > enemyY - detectionRangeY && playerY < enemyY + detectionRangeY) {

			if (enemyX + 10 < playerX && enemycollision2[i] == false) {
				// If player is right of enemy, move enemy right
				newenemyX += 5;

				// same animation system as the player

				Smover = Smover + 1;

				if(Smover == 2) {
					mobs1.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/skeletonright1.png")));
				}
				if(Smover == 9) {
					mobs1.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/skeletonright2.png")));

					Smover = 1;
				}

			}

			else if (enemyX - 10 > playerX && enemycollision2[i] == false) {
				// If player is left of enemy, move enemy left
				newenemyX -= 5;

				Smovel = Smovel + 1;

				if(Smovel == 2) {
					mobs1.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/skeletonleft1.png")));
				}
				if(Smovel == 9) {
					mobs1.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/skeletonleft2.png")));

					Smovel = 1;
				}

			}

			if (enemyY - 10 > playerY && enemycollision2[i] == false) {
				// If player is above enemy, move enemy up
				newenemyY -= 5;

				Smoveu = Smoveu + 1;

				if(Smoveu == 2) {
					mobs1.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/skeletonup1.png")));
				}
				if(Smoveu == 9) {
					mobs1.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/skeletonup2.png")));

					Smoveu = 1;
				}

			}

			else if (enemyY + 10 < playerY && enemycollision2[i] == false) {
				// If player is under enemy, move enemy down
				newenemyY += 5;

				Smoved = Smoved + 1;

				if(Smoved == 2) {
					mobs1.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/skeletondown1.png")));
				}
				if(Smoved == 9) {
					mobs1.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/skeletondown2.png")));

					Smoved = 1;
				}

			}

			// minimum distance between mob and other mobs
			int minDistance = 50;

			// function to make so mobs dont collide with eachother

			// check proximity to other mobs and adjust movement
			for (int j = 0; j < mobs.length; j++) {

				// gets the index of every mob , and adjusts
				if (j != i) { // skip itself

					// other mob bounds
					JLabel otherMob = mobs[j];
					int otherX = otherMob.getX();
					int otherY = otherMob.getY();

					// calculate distance to the other mob (pythagoreas theorem, c is the distance between them)
					double distance = Math.sqrt(Math.pow(newenemyX - otherX, 2) + Math.pow(newenemyY - otherY, 2));

					// if too close, adjust movement to avoid overlap
					if (distance < minDistance) {
						if (newenemyX > otherX)
							newenemyX += 5; // move right

						else if (newenemyX < otherX)
							newenemyX -= 5; // move left

						if (newenemyY > otherY)
							newenemyY += 5; // move down

						else if (newenemyY < otherY)
							newenemyY -= 5; // move up
					}
				}
			}

			// update bounds
			hp[i].setBounds(mobs1.getX(), mobs1.getY() - 40, 150, 20);

		}

		// enemy collision, using enemy bounds
		if (checkCollision(newenemyX,newenemyY,width,height)) {
			// return true, dont allow movement
			enemycollision = true;
		}

		// so that mobs dont get too close, to initiate attack
		if (newenemyX + 130 > playerX && newenemyX < playerX + 130 &&
				newenemyY + 130 > playerY && newenemyY < playerY + 130) {

			// collide with player, array so that other mobs can keep moving even if one already reached player
			enemycollision2[i] = true;

			// if mob attack is true, and the player is not blocking, attack player
			if (mobattack[i] && block == false) {

				// deduct 8 hp
				playerhp.setValue(playerhp.getValue() - 8);

				// play hit sound
				try {
					URL musicURL = playmenu.class.getResource("/Dimages/ghosthit.wav");
					AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicURL);
					Clip clip = AudioSystem.getClip();
					clip.open(audioInput);
					clip.start();
				} catch (Exception e1) {
					System.out.println(e1);
				}

				// after a attack, turn mobattack to false, and start up the timer again
				mobattack[i] = false;
				mobTimers[i].start();

				// calculate knockback direction, same premise as knocking back enemies

				int knockbackDistance = 42; // total knockback distance

				int knockbackSteps = 8; // knockback intervals

				int knockbackStepSize = knockbackDistance / knockbackSteps; // size of each knockback interval

				// gets DX and DY based on player position relative to the enemy
				if (playerX - enemyX > 0) {
					knockbackDX2 = knockbackStepSize; // move right

				} else if (playerX - enemyX < 0) {
					knockbackDX2 = -knockbackStepSize; // move left
				}

				if (playerY - enemyY > 0) {
					knockbackDY2 = knockbackStepSize; // move down

				} else if (playerY - enemyY < 0) {
					knockbackDY2 = -knockbackStepSize; // move up
				}


				knockbackStep = 0;

				// same timer as before, but player is knocked back
				knockbackTimer = new Timer(30, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (knockbackStep < knockbackSteps) {
							// knockback
							int newplayerX = Player.getX() + knockbackDX2;
							int newplayerY = Player.getY() + knockbackDY2;

							// check for collisions before moving, if no collision update the player's position and knock back
							if (!checkCollision(newplayerX, newplayerY, Player.getWidth(), Player.getHeight())) {
								// update playercoords
								playerX = newplayerX;
								playerY = newplayerY;
								Player.setBounds(playerX, playerY, Player.getWidth(), Player.getHeight());
							}

							// increase knockbackstep so that knockback can stop eventually
							knockbackStep++;
						} else {
							// stop when knockback completed
							knockbackTimer.stop();
						}
					}
				});
				knockbackTimer.start();
			}

			// Check if player's health drops to zero
			if (playerhp.getValue() <= 0) {
				// stop mob attack and timer
				// playable = false makes music stop playing
				// make it so player cannot move
				mobTimers[i].stop();
				playable = false;
				moveable = false;
				mobtimer.stop();

				// track lives, starts at 3  ands at 0
				life = life - 1;

				// update how many lives are left
				if(life == 2) {
					lives.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/lives2.png")));
				}
				if(life == 1) {
					lives.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/lives3.png")));

				}

				// if life is 0, return to menu using whichfade, and go to gameover menu using gameover=true
				if(life == 0) {
					gameover = true;
					whichfade = true;
				}

				// fadeout
				fadeout();

			}
		} else {
			//reset enemy collision
			enemycollision2[i] = false;
		}

		// update bounds if no collision

		if (enemycollision == false && enemycollision2[i] == false) {

			enemyX = newenemyX;
			enemyY = newenemyY;

			mobs1.setBounds(enemyX, enemyY, mobs1.getWidth(), mobs1.getHeight());
		}
	}


	// label for mage's magic attack, his attack speed is 12
	static JLabel magic = new JLabel();
	static int speed = 12;

	public static void enemyai2() {

		// if mage is dead, immidieatly return and stop timers
		if (!magealive) {
			magealive = false;
			magicattack.stop();
			attackmove.stop();
			magic.setVisible(false);
			mobtimer2.stop();
			contentPane.remove(magic);
			contentPane.repaint();
			return;
		}

		// make sure mage is in it's deault position at all times, unless hit
		if(trackmagehits == 0 && floorcount == 5) {
			enemy3.setBounds(541, 150, enemy3.getWidth(), enemy3.getHeight());
		}

		// attack timer
		magicattack = new Timer(1300, new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// boss coords
				int enemyX = enemy3.getX();
				int enemyY = enemy3.getY();

				// repeat here
				if (!magealive) {
					magicattack.stop();
					magealive = false;
					magicattack.stop();
					attackmove.stop();
					magic.setVisible(false);
					mobtimer2.stop();
					contentPane.remove(magic);
					contentPane.repaint();
					mageM.close();
					return;
				}

				// play attack sound
				try {
					URL musicURL = playmenu.class.getResource("/Dimages/mageattack.wav");
					AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicURL);
					mageM = AudioSystem.getClip();
					mageM.open(audioInput);
					mageM.start();
				} catch (Exception e1) {
					System.out.println(e1);
				}




				// mage attack stance
				enemy3.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/mage3.png")));

				// magic ball visible
				magic.setVisible(true);
				magic.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/magicmobattack.png")));
				contentPane.add(magic);

				// magic ball's starting position
				mx = enemyX + 40;
				my = enemyY + 60;
				magic.setBounds(mx, my, enemy3.getWidth() - 50, enemy3.getHeight() - 50);

				// timer for homing the magic towards the player
				attackmove = new Timer(5, new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						// repeat return here, make sure there's no room for timers working uneccessarily, as it lags out the program
						if (!magealive) {
							attackmove.stop();
							magic.setVisible(false);
							contentPane.remove(magic);
							mageM.close();
							return;
						}

						// calculate direction to the player every time magic moves
						int dx = playerX - mx;
						int dy = playerY - my;

						// find distance using pythagoreas again
						double distance = Math.sqrt(dx * dx + dy * dy);

						if (distance > 1) {

							// find how much to move in each direction
							double directionX = dx / distance;
							double directionY = dy / distance;

							// update magic position based on speed and direction/distance
							mx += directionX * speed;
							my += directionY * speed;

						} else {

							// if distance is too small, move directly to player's position
							mx = playerX;
							my = playerY;
						}

						// update magic bounds
						magic.setBounds(mx, my, enemy3.getWidth() - 50, enemy3.getHeight() - 50);

						// stop attack when magic reaches the player
						if ((mx - playerX) < 1 && (my - playerY) < 1) {
							// only damage if player isn't blocking
							if(block == false) {
								playerhp.setValue(playerhp.getValue() - 15);
							}

							// Check if player's health drops to zero
							if (playerhp.getValue() <= 0) {

								contentPane.requestFocus();

								// lives update onces again
								life = life - 1;

								if(life == 2) {
									lives.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/lives2.png")));

								}
								if(life == 1) {
									lives.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/lives3.png")));
								}
								if(life == 0) {
									gameover = true;
									whichfade = true;
								}

								// remove enemy from the frame, since it creates timer problems if still in vicinity
								// cease all mage activity
								magic.setVisible(false);
								contentPane.remove(magic);
								trackmagehits = 0;
								moveable = false;
								// to remove the mage
								magealive = false;
								hp2.setVisible(false);
								hp2.setBounds(10000,10000,0,0);
								contentPane.remove(hp2);
								contentPane.repaint();
								contentPane.revalidate();

								// fadeout
								fadeout();

								// reset statuses, and stop timers
								playable = false;
								mobtimer2.stop();
								hp2.setVisible(false);
								attackmove.stop();
								magicattack.stop();

							}

							attackmove.stop(); // stop the magic movement again, make no room for anything
							magic.setVisible(false); // hide magic
							contentPane.remove(magic); // remove magic from screen
							contentPane.repaint(); // refresh screen
						}
					}
				});



				// create a Timer for changing the enemy stance
				Timer stancechange = new Timer(300, new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						enemy3.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/mageidle2.png")));
					}
				});
				stancechange.setRepeats(false);
				stancechange.start();

				// start magic movement timer
				attackmove.start();

				// if mage dies, remove it from the screen and end the game
				int newhp = hp2.getValue();
				if(newhp <= 0) {

					msg.setIcon(new ImageIcon(playmenu.class.getResource("/Dimages/msg9.png")));

					hp2.setVisible(false);
					contentPane.remove(hp[1]);
					if(mageM.isRunning()) {
						mageM.close();
					}
					magealive = false;
					magicattack.stop();
					attackmove.stop();
					magic.setVisible(false);
					contentPane.remove(magic);
					contentPane.repaint();

					// allow the game to end
					endable = true;

					// from here player can press enter to end the game

				}

			}
		});



		int detectionRangeX = 500;
		int detectionRangeY = 500;

		int enemyX = enemy3.getX();
		int enemyY = enemy3.getY();
		// If the player is within the attack range, start the attack
		if (playerX > enemyX - detectionRangeX && playerX < enemyX + detectionRangeX
				&& playerY > enemyY - detectionRangeY && playerY < enemyY + detectionRangeY) {
			// use a list so only one timer can be active, no timer conflicts
			if (storetimer.size() < 1) {
				storetimer.add(magicattack);
				storetimer.get(storetimer.size() - 1).start(); // start the attack timer

			}
		}
	}

	public static void hitcheck2() {

		// if mage is hit, up mage hit by 1, update its hp, and move him
		if (sr.getBounds().intersects(enemy3.getBounds()) && slashr) {
			updatebosshp();
			trackmagehits++;
			magemove();

		} else if (sl.getBounds().intersects(enemy3.getBounds()) && slashl) {
			updatebosshp();
			trackmagehits++;
			magemove();

		} else if (su.getBounds().intersects(enemy3.getBounds()) && slashu) {
			updatebosshp();
			trackmagehits++;
			magemove();

		} else if (sd.getBounds().intersects(enemy3.getBounds()) && slashd) {
			updatebosshp();
			trackmagehits++;
			magemove();
		}
	}

	// collision method
	private static boolean checkCollision(int x, int y, int width, int height) {
		// get all collidable object bounds
		for (int i = 0; i < collide.length; i++) {
			int objLeft = collide[i].getX();
			int objRight = collide[i].getX() + collide[i].getWidth();
			int objTop = collide[i].getY();
			int objBottom = collide[i].getY() + collide[i].getHeight();

			// to check if anything collides with new coords
			if (x + width > objLeft && x < objRight && y + height > objTop && y < objBottom) {
				return true; // collision detected, dont allow movement
			}
		}
		return false; // no collision, proceed with movement
	}

	// mage movement
	public static void magemove() {

		//set the mage to moving
		magemoving = true;

		// timer for moving the mage when hit
		if (trackmagehits >= 1 && trackmagehits <= 5) {  
			yAdjustTimer = new Timer(10, new ActionListener() {
				//  position variables
				int targetY1 = 439;
				int targetX2 = 56;  
				int targetY3 = 90;  
				int targetX4 = 561;
				int targetX5 = 321;  
				int targetY5 = 264;  

				public void actionPerformed(ActionEvent e) {
					// adjust position for trackmagehits == 1 (Y = 439)
					if (trackmagehits == 1) {
						if (currentY < targetY1) {
							currentY += 10; // move down quickly

							if (currentY >= targetY1) {
								currentY = targetY1;  // stop at the target position
							}
						}
					}

					// adjust position for trackmagehits == 2 (X = 56)
					if (trackmagehits == 2) {
						if (currentX > targetX2) {
							currentX -= 10;  // move left quickly

							if (currentX <= targetX2) {
								currentX = targetX2;  // stop at the target position
							}
						}
					}

					// adjust position for trackmagehits == 3 (Y = 90)
					if (trackmagehits == 3) {
						if (currentY > targetY3) {
							currentY -= 10;  // move up quickly

							if (currentY <= targetY3) {
								currentY = targetY3;  // stop at the target position
							}
						}
					}

					// adjust position for trackmagehits == 4 (X = 561)
					if (trackmagehits == 4) {
						if (currentX < targetX4) {
							currentX += 10; // move right quickly

							if (currentX >= targetX4) {
								currentX = targetX4;  // stop at the target position
							}
						}
					}

					// adjust position for trackmagehits == 5 (X = 321, Y = 264)
					if (trackmagehits == 5) {

						if (currentX > targetX5) {  // if already past the target X
							currentX -= 10; // move left towards X = 321

							if (currentX < targetX5) {  // don't overshoot the target
								currentX = targetX5;  // stop at the target position
							}
						}

						// move Y towards targetY5 (264) after X is complete (if X movement finished)
						if (currentX == targetX5) {  // only move Y once X is in place

							if (currentY < targetY5) {
								currentY += 10; // move down towards Y = 264

								if (currentY > targetY5) {  // don't overshoot the target
									currentY = targetY5;  // stop at the target position
								}

							}
						}
					}

					// update position (either X or Y or both, depending on trackmagehits)
					hp2.setBounds(enemy3.getX(), enemy3.getY() - 40, 150, 20);
					enemy3.setBounds(currentX, currentY, enemy3.getWidth(), enemy3.getHeight());

					// stop the timer once the position has been adjusted (only for current movement)
					if ((trackmagehits == 1 && currentY == targetY1) ||
							(trackmagehits == 2 && currentX == targetX2) ||
							(trackmagehits == 3 && currentY == targetY3) ||
							(trackmagehits == 4 && currentX == targetX4) ||
							(trackmagehits == 5 && currentX == targetX5 && currentY == targetY5)) {
						yAdjustTimer.stop();
						// end magemoving
						magemoving = false;
					}
				}
			});

			yAdjustTimer.setRepeats(true);  // keep repeating the position adjustment
			yAdjustTimer.start();  // start the timer to adjust position quickly
		}
	}

	// update bosshp on hit
	public static void updatebosshp() {
		int newHP = hp2.getValue() - 18;
		hp2.setValue(newHP);
		hp2.setVisible(true);
		hp2.setBounds(enemy3.getX(), enemy3.getY() - 40, 150, 20);

		if (newHP <= 0) {
			enemy3.setBounds(-10000, -10000, 0, 0);
			enemy3.setVisible(false);
		}
	}
}