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

/**통화기록을 시간을 기반으로 저장하고 발신인지 수신인지를 알려주는 boolean 변수를 함께 묶어 저장합니다*/
class PhoneCall
{
	String phoneCall;
	boolean isMyCall;//발신이면 true, 아니면 false
	
	public PhoneCall(String phoneCall, boolean isMyCall)
	{
		this.phoneCall=phoneCall;
		this.isMyCall=isMyCall;
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
	private ArrayList<PhoneCall> callList;
	
	public Talk()
	{
		messageList = new ArrayList<Comment>();
		callList = new ArrayList<PhoneCall>();
		Comment tempCom;
		PhoneCall tempCall;
		
		tempCom = new Comment("안녕", false);
		messageList.add(tempCom);
		tempCom = new Comment("안녕 넌 잘 지내?", true);
		messageList.add(tempCom);
		tempCom = new Comment("난 잘 지내지", false);
		messageList.add(tempCom);
		tempCom = new Comment("난 잘 못지내는데...", true);
		messageList.add(tempCom);
		tempCom = new Comment("응 그렇구나...", false);
		messageList.add(tempCom);
		tempCom = new Comment("그럼 안녕", false);
		messageList.add(tempCom);
		
		tempCall = new PhoneCall("15.3.18 16:38",false);
		callList.add(tempCall);
		tempCall = new PhoneCall("15.3.21 12:13",true);
		callList.add(tempCall);
		tempCall = new PhoneCall("15.3.30 20:40",true);
		callList.add(tempCall);
		tempCall = new PhoneCall("15.4.1 12:02",false);
		callList.add(tempCall);
		tempCall = new PhoneCall("15.4.6 19:48",true);
		callList.add(tempCall);
		tempCall = new PhoneCall("15.4.15 11:57",false);
		callList.add(tempCall);
	}
	
	/**메세지 대화창을 띄워줍니다. exit를 치면 대화창에서 나갑니다.*/
	public void message()
	{
		String input = "";
		Scanner sc=new Scanner(System.in);
		Comment tempCom;//보내는 메세지의 내용을 임시로 저장
		while (true)
		{
			System.out.println("*********************************");
			
			for (Comment iComment : messageList)
			{
				if (!iComment.isMine)//상대방의 메세지인 경우
					System.out.format("*%s\n", iComment.comment);
				else//내 메세지인 경우
					System.out.format("%29s*\n", iComment.comment);
			}
			
			System.out.println("*********************************  exit를 치면 대화창에서 나갑니다");
			System.out.print("메세지입력->");
			input=sc.nextLine();
			if(input.equals("exit"))
				break;
			tempCom=new Comment(input,true);
			messageList.add(tempCom);
		}	
	}
	
	/**
	 * 통화기록을 출력합니다.
	 */
	public void call()
	{
		System.out.println("*********************************");
		for(PhoneCall iPhoneCall:callList)
		{
			if(iPhoneCall.isMyCall)//발신전화일 경우
				System.out.println(iPhoneCall.phoneCall+"-> 발신");
			else//수신전화일 경우
				System.out.println(iPhoneCall.phoneCall+"<- 수신");
			System.out.println("---------------------------------");
		}
		System.out.println("*********************************");
	}
}