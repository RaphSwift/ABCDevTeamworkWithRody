package utils;

import java.util.ArrayList;

import javafx.scene.media.AudioClip;

public class SoundManager {
	private ArrayList<AudioClip> sounds;
	private ArrayList<String> name;
	
	public SoundManager() {
		sounds = new ArrayList<AudioClip>();
		name = new ArrayList<String>();
	}
	
	public SoundManager(ArrayList<AudioClip> _sounds,
						ArrayList<String> _name) {
		sounds = _sounds;
		name = _name;
	}
	
	public SoundManager(SoundManager from) {
		this(from.sounds,from.name);
	}
	
	public boolean addSound(String soundPath, String soundName) {
		if (soundPath == null || soundName == null)
			return false;
		boolean rt = true;
		rt = sounds.add(new AudioClip(getClass().getResource(soundPath).toExternalForm()));
		if (rt)
			rt = name.add(soundName);
		return rt;
	}
	
	public boolean playSoundByName(String soundName) {
		ArrayList<AudioClip> rt = new ArrayList<AudioClip>();
		for (int i = 0; i < name.size(); i++) {
			if (name.get(i).equals(soundName))
				rt.add(sounds.get(i));;
		}
		if (rt.size() > 0) {
			rt.get(Utils.generateRandom(0,rt.size())).play();
			
		}
		return rt.size()==0;
	}
	
}
