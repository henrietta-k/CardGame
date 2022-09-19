

public class Cell {
		
		private String mark;
		
		Cell(int num)
		{
			mark = " ";
		}
		
		public String setX()
		{
			mark = "X";

			return mark; 
		}
		
		public void setO()
		{
			mark = "O";
		}
		
		public void setMark(String m)
		{
			this.mark = m;
		}
		public String getMark()
		{
			return mark;
		}
		
		
		public String paint()
		{
				return mark;
		}
	}