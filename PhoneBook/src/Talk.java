import java.util.ArrayList;
import java.util.Scanner;

/**코멘트 하나와 그것이 내 메세지인지 상대 메세지인지를 알려주는 boolean 변수를 함께 묶어 저장합니다*/
class Comment
{
	String comment;
	boolean isMine;//내 메세지이면 true, 아니면 false
	
	public Comment(String comment, boolean isMine)
	{
		this.comment = comment;
		this.isMine = isMine;
	}
}

/**
 * 통신기록을 모두 저장합니다.
 * 메세지와 전화 기능을 관리합니다.
 * @author createrlee
 */
class Talk
{
	private ArrayList<Comment> messageList;
	private Comment tempCom;
	
	public Talk()
	{
		messageList = new ArrayList<Comment>();
		Comment tempCom;
		
		tempCom = new Comment("안녕", false);
		messageList.add(tempCom);
		tempCom = new Comment("안녕 넌 잘 지내?", true);
		messageList.add(tempCom);
		tempCom = new Comment("난 잘 지내지", false);
		messageList.add(tempCom);
		tempCom = new Comment("난 잘 못지내는데...", true);
		messageList.add(tempCom);
		tempCom = new Comment("응 그렇구나", false);
		messageList.add(tempCom);
		tempCom = new Comment("안녕", false);
		messageList.add(tempCom);
	}
	
	/**메세지 대화창을 띄워줍니다*/
	public void message()
	{
		String input = "";
		Scanner sc=new Scanner(System.in);
		while (!input.equals("exit"))
		{
			System.out.println("*********************************");
			
			for (Comment iComment : messageList)
			{
				if (!iComment.isMine)//상대방의 메세지인 경우
					System.out.format("*%s\n", iComment.comment);
				else//내 메세지인 경우
					System.out.format("%29s*\n", iComment.comment);
			}
			
			System.out.println("*********************************");
			System.out.print("메세지입력->");
			input=sc.nextLine();
			this.tempCom=new Comment(input,true);
			messageList.add(this.tempCom);
		}
		
	}
}