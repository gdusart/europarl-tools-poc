package be.gdusart.europarltools.app.ui.helpers;

import com.google.gson.JsonObject;

public class DisplayableObject {

	private JsonObject object;
	private String title;


	public DisplayableObject(String title, JsonObject object) {
		super();
		this.object = object;
		this.title = title;
	}

	public JsonObject getObject() {
		return object;
	}

	public void setObject(JsonObject object) {
		this.object = object;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
