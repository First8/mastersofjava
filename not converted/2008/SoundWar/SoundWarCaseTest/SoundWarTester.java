import nl.moj.model.Tester;

public class SoundWarTester implements Tester.Testable {
	
    private static final int SILENT=9;
    
	private static final String[] NAMES=new String[] {
			"First Effect",
	        "Lower priority",
	        "Higher priority",
	        "Equal priority",
			//
	        "Two Channels",
	        "Silence of the Lambs",
	        "Priority Report",
	        //
	        "System Shock"
	};
	
	public int getTestCount() {
		return NAMES.length;
	}

	public String getTestName(int nr) {
		return NAMES[nr];
	}
	
	//
	//
	//
	
	private static final int[] NR_OF_CHANNELS=new int[] { 1,1,1,1,2,2,2,4 };
	
	private static final SoundEffectImpl[] EFFECTS=new SoundEffectImpl[] {
	  new SoundEffectImpl("Warning: Destruction Imminent",0),  
      new SoundEffectImpl("A-Bomb launch detected.",0),
	  new SoundEffectImpl("Enemy approaching from the west.",0),
	  new SoundEffectImpl("Harkonen harvester deployed.",1),
	  new SoundEffectImpl("Cannot deploy here, building in progress.",1),
	  new SoundEffectImpl("Set Sail!",1),
	  new SoundEffectImpl("Look at you, Hacker, mortal creature of flesh and bone, panting and sweating as you run through my corridors. How can you challenge a perfect immortal machine?",2),
      new SoundEffectImpl("You never touch the other elves like that",2),
      new SoundEffectImpl("Your will sire?",2),
      new SoundEffectImpl("Tjing!",3),
      new SoundEffectImpl("Kaboom!",3),
      new SoundEffectImpl("Splatter!",3),
      new SoundEffectImpl("Twiep twiep, flutter, twiep",4),
      new SoundEffectImpl("[water flowing]",4),
      new SoundEffectImpl("Beeehehhh",4),
	};
	
	private static final String[][] PLAYLIST=new String[][] {
	    { "P00","T000","R0" },  
	    { "P00","T000","P10","S000","T000","R0" },
        { "P10","T010","P00","T000","R0" },
	    { "P02","T002","P01","S000","T002","R0" },
	    //
	    { "P30","T030","P40","T140","P11","T111","R0","R1" },
	    { "P42","T042","P42","T142","P42","S000","R0","P42","T042","R1","P42","T142","R0","R1"},
	    { "P21","T021","P22","T122","P12","T012","R1","R0"},
	    //
	    { "P20","T020","P30","T130","P31","T231","P32","T332","P41","S000","R1","R3","P40","T140","P00","T300","P32","T132","R2","P30","T230","P00","T100","R0","R2","R1","R3"}
	};
	
	//
	//
	//
	
	public String getTestDescription(int nr) {
		StringBuffer sb=new StringBuffer();
		sb.append("There ");
		sb.append(NR_OF_CHANNELS[nr]==1?"is ":"are ");
		sb.append(NR_OF_CHANNELS[nr]);
        sb.append(NR_OF_CHANNELS[nr]==1?" channel ":" channels ");
		sb.append("available.\n");
		//
		String[] playlist=PLAYLIST[nr];
		for (int t=0;t<playlist.length;t++) {
            String cmd=playlist[t].substring(0,1);
            int val=Integer.parseInt(playlist[t].substring(1,2));
            if ("P".equals(cmd)) {
                int eff=Integer.parseInt(playlist[t].substring(2,3));
                SoundEffect sfx=EFFECTS[val*3+eff];
                sb.append("A request is made to play "+sfx+"\n");
            } else if ("R".equals(cmd)) {
                sb.append("Channel "+val+" finishes playing.\n");
            } else if ("S".equals(cmd)) {
                sb.append("- This sound effect is skipped.\n");
            } else if ("T".equals(cmd)) {
                if (playlist[t-1].startsWith("P")) {
                    sb.append("- This sound effect is played on channel ");
                    sb.append(val);
                    sb.append(".\n");
                }
            }
		}
		//
		// TODO: Generate useful test description.
		//
		return sb.toString();
	}
	
	public boolean performTest(int nr) throws Throwable {
	    //
		// Assume the worst
		//
		boolean result=false;
		//
		// Create a new Instance for each test.
		//
		try {
		    ChannelImpl[] ch=new ChannelImpl[NR_OF_CHANNELS[nr]];
		    Channel[] copy=new Channel[NR_OF_CHANNELS[nr]];
		    for (int t=0;t<ch.length;t++) {
		        ch[t]=new ChannelImpl(t);
		        copy[t]=ch[t];
		    }
		    System.out.println("> There are "+ch.length+" channels.");
			SoundChooserImpl instance=new SoundChooserImpl(copy);
			String[] playlist=PLAYLIST[nr];
			for (int t=0;t<playlist.length;t++) {
			    String cmd=playlist[t].substring(0,1);
			    int val=Integer.parseInt(playlist[t].substring(1,2));
			    if ("P".equals(cmd)) {
                    int eff=Integer.parseInt(playlist[t].substring(2,3));
			        SoundEffect sfx=EFFECTS[val*3+eff];
			        System.out.println("> play("+sfx+") ");
			        for (int y=0;y<ch.length;y++) {
			            ch[y].reset();
			        }
			        instance.play(sfx);
			    } else if ("R".equals(cmd)) {
			        if (ch[val].isPlaying()) {
	                    System.out.println("> The sound on channel "+ch[val].getNumber()+" finishes.");
	                    ch[val].stop();
			        }
			    } else if ("S".equals(cmd)) {
                    for (int y=0;y<ch.length;y++) {
                        if (ch[y].isPlayCalled()||ch[y].isStopCalled()) {
                            System.out.println("Expected no operation on any channels, but on channel "+y+" play() or stop() was called.");
                            return false;
                        }
                    }
			    } else if ("T".equals(cmd)) {
			        int pri=Integer.parseInt(playlist[t].substring(2,3));
                    int eff=Integer.parseInt(playlist[t].substring(3,4));
			        SoundEffectImpl expected=null;
			        if (eff!=SILENT) expected=EFFECTS[pri*3+eff]; 
			        if (ch[val].isPlaying()) {
	                    if (!ch[val].getCurrentEffect().equals(expected)) {
	                        System.out.println("Failed: channel "+val+" shoud be playing "+expected+" and not "+ch[val]);
	                        return false;
	                    }
			        } else {
			            if (eff!=SILENT) {
			                System.out.println("Failed: channel "+val+" shoud be playing "+expected+" and not "+ch[val]);
                            return false;
			            }
			        }
			    } else {
			        throw new RuntimeException("Bad instruction '"+playlist[t]+"'");			        
			    }
			}
			//
			result=true;
			//
		} catch (Exception ex) {
			//
			// Catch the exception, so that other tests may
			// still be executed. Do print the stacktrace. 
			//
			ex.printStackTrace();
			//
			return false;
		}
		//
		return result;
	}
	
}
