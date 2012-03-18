package jp.gr.java_conf.konkonlab.game_of_life.models;

public enum Cell {
		AliveCell("ALIVE"), DeadCell("DEAD");
		private String status;
		
		private Cell(String status)
		{
			this.status = status;
		}
		
		@Override
		public String toString() {
			return status;
		}
}
