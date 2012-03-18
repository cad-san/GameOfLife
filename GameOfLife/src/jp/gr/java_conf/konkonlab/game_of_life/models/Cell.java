package jp.gr.java_conf.konkonlab.game_of_life.models;

public class Cell {
		public static final String AliveCell = "ALIVE";
		public static final String DeadCell  = "DEAD";

		private String status = DeadCell;
		
		public Cell(String status)
		{
			this.status = status;
		}
		
		@Override
		public String toString() {
			return status;
		}
		
		@Override
		public boolean equals(Object another) {
		return status.equals(another);
		}
}
