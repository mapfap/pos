package com.refresh.pos.ui;

import java.util.Observable;

public class Announcer extends Observable{
	public void announce(Object data) {
		super.setChanged();
		super.notifyObservers(data);
	}
}
