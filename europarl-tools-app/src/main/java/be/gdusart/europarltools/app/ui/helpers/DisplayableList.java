package be.gdusart.europarltools.app.ui.helpers;

public class DisplayableList {

	public DisplayableList() {
		super();
	}

	public DisplayableList(String title, String[] headers, Iterable<DisplayableRow> rows) {
		super();
		this.title = title;
		this.headers = headers;
		this.rows = rows;
	}

	private String title;
	private String[] headers;
	private Iterable<DisplayableRow> rows;

	public static class DisplayableRow {
		public static enum RowLevel {
			DANGER, SUCCESS, INFO
		}

		private Object[] columns;
		private String link;
		private RowLevel level;

		public DisplayableRow(String link, Object... columns) {
			this.link = link;
			this.columns = columns;
		}

		public Object[] getColumns() {
			return columns;
		}

		public void setColumns(Object[] columns) {
			this.columns = columns;
		}

		public String getLink() {
			return link;
		}

		public void setLink(String link) {
			this.link = link;
		}

		public RowLevel getLevel() {
			return level;
		}

		public void setLevel(RowLevel level) {
			this.level = level;
		}
	}

	public String[] getHeaders() {
		return headers;
	}

	public void setHeaders(String[] headers) {
		this.headers = headers;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Iterable<DisplayableRow> getRows() {
		return rows;
	}

	public void setRows(Iterable<DisplayableRow> rows) {
		this.rows = rows;
	}

}
